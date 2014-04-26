/**
 * 加载树的方式
 *    1、一次性全部加载
 *    2、点击事件加载
 *    		1、当加载jsp页面的时候，应该加载该树的根节点
 *    		2、当点击一个节点的时候，加载该节点的子节点
 */
var tree = {
    zTreePlugIn: '',
    setting: {
        isSimpleData: true,
        treeNodeKey: "mid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        },
        callback: {
            /**
             *
             * @param {Object} event  鼠标事件
             * @param {Object} treeId   树的容器ID
             * @param {Object} treeNode   当前点击的节点
             */
            expand: function(event, treeId, treeNode){
                /**
                 * 1、获取该节点的mid值
                 * 2、从后台把子节点加载出来
                 * 3、把子节点追加到当前的父节点上
                 */
                var parameter = {
                    pid: treeNode.mid
                };
                if (!tree.zTreePlugIn.getNodeByParam("pid", treeNode.mid)) {//如果当前点击的节点没有子节点
                    $.post("menuitemAction_showMeuitemsByPid.action", parameter, function(data){
                        tree.zTreePlugIn.addNodes(treeNode, data.menuitemList, true);
                    });
                }
            }
        }
    },
    loadTree: function(){
        $.post("menuitemAction_showMenuitems.action", null, function(data){
            tree.zTreePlugIn = $("#tree").zTree(tree.setting, data.menuitemList);
        });
    },
    /**
     * 加载根节点
     */
    loadRoot: function(){
        var parameter = {
            pid: 0
        };
        $.post("menuitemAction_showMeuitemsByPid.action", parameter, function(data){
            tree.zTreePlugIn = $("#tree").zTree(tree.setting, data.menuitemList);
        });
    }
};
$().ready(function(){
    //tree.loadTree();
    tree.loadRoot();
});
