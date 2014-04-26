var privilege = {
    /**
     * 数据的json对象
     */
    data: {
        user: {
            uid: '',
            username: ''
        }
    },
    /**
     * 初始化部分
     *     初始化事件
     *     初始化数据
     */
    init: {
        initEvent: function(){
            /**
             * 给设置权限增加单击事件
             */
            $("a").each(function(){
                if ($(this).text() == "设置权限") {
                    $(this).unbind("click");
                    $(this).bind("click", function(){
                        //显示div
                        privilege.operation.divOption.divShow();
                        //初始化数据
                        //privilege.init.initData(this);
                        //initDat函数的调用者就是this(超级链接)
                        privilege.init.initData.call(this);
                        //动态的显示用户名称
                        privilege.operation.userOption.setUserName();
                        //加载权限树
                        privilege.operation.privilegeTreeOption.loadPrivilegeTree();
                        return false;
                    });
                }
            });
            /**
             * 全选复选框增加change事件
             */
            $("#allchecked").unbind("change");
            $("#allchecked").bind("change", function(){
                privilege.operation.privilegeTreeOption.controlCheckBox.call(this);
            });
            
            /**
             * 保存的单击事件
             */
            $("#InputDetailBar img").unbind("click");
            $("#InputDetailBar img").bind("click", function(){
                privilege.operation.privilegeTreeOption.savePrivilege();
            });
        },
        initData: function(){
            //var aObj = arguments[0];
            //alert($(aObj).parent().siblings("td:first").text());
            privilege.data.user.username = $(this).parent().siblings("td:first").text();
            privilege.data.user.uid = $(this).parent().siblings("input[type='hidden']:first").val();
        }
    },
    /**
     * 业务逻辑部分
     */
    operation: {
        /*
         * div的操作
         */
        divOption: {
            /**
             * 显示div
             */
            divShow: function(){
                //让页面上所有的隐藏的div显示
                $("div").privilegeShow();
            }
        },
        /**
         * 用户的操作
         */
        userOption: {
            //动态的赋值用户名
            setUserName: function(){
                $("#userImage").text(privilege.data.user.username);
            }
        },
        /**
         * 权限树的操作
         */
        privilegeTreeOption: {
            privilegeTreePlugin: '',
            setting: {
                isSimpleData: true,
                treeNodeKey: "mid",
                treeNodeParentKey: "pid",
                showLine: true,
                root: {
                    isRoot: true,
                    nodes: []
                },
                checkable: true,
                checkType: {
                    'Y': 'p',
                    'N': 's'
                },
                callback: {
                    /**
                     * 点击树上的复选框的时候，在复选框的状态没有改变之前，触发该事件
                     */
                    beforeChange: function(){
                        privilege.operation.privilegeTreeOption.changeCheckType({
                            checkType: {
                                'Y': 'p',
                                'N': 's'
                            }
                        });
                    },
                    change: function(){
                        privilege.operation.privilegeTreeOption.changeAllCheckedState();
                    }
                }
            },
            /**
             * 加载权限树
             */
            loadPrivilegeTree: function(){
                $.post("privilegeAction_showPrivilege.action", {
					uid:privilege.data.user.uid
				}, function(data){
                    privilege.operation.privilegeTreeOption.privilegeTreePlugin = $("#privilegeTree").zTree(privilege.operation.privilegeTreeOption.setting, data.privilegeList);
                    /**
                     * 当权限树加载完毕以后，使全选复选框生效
                     */
                    $("#allchecked").attr("disabled", "");
                    /**
                     * 根据加载出来的树上的复选框是否被全部选中来决定全选复选框的选中状态
                     *     设置全选复选框初始化的状态
                     */
                    privilege.operation.privilegeTreeOption.changeAllCheckedState();
                });
            },
            /**
             * 全选复选框控制zTree上的复选框是否全部选中
             */
            controlCheckBox: function(){
                /**
                 * 改变setting的checkType规则
                 *   必须是每一次点击全选复选框都要经过该代码
                 */
                privilege.operation.privilegeTreeOption.changeCheckType({
                    checkType: {
                        'Y': 'ps',
                        'N': 'ps'
                    }
                });
                if ($(this).attr("checked")) {//复选框全部选中
                    privilege.operation.privilegeTreeOption.privilegeTreePlugin.checkAllNodes(true);
                    //$("button[id$='check']").attr("class","chk checkbox_true_full");
                }
                else {//不选中
                    privilege.operation.privilegeTreeOption.privilegeTreePlugin.checkAllNodes(false);
                    //$("button[id$='check']").attr("class","chk checkbox_false_full");
                }
                
            },
            /**
             * 改变权限树中setting中checkType的规则
             */
            changeCheckType: function(checkTypeJSON){
                var setting = privilege.operation.privilegeTreeOption.privilegeTreePlugin.getSetting();
                setting.checkType = checkTypeJSON.checkType;
                privilege.operation.privilegeTreeOption.privilegeTreePlugin.updateSetting(setting);
            },
            /**
             * 当点击权限树的某一个节点的复选框的时候，判断当前权限树的复选框是否被全部选中，从而改变全选复选框的状态
             */
            changeAllCheckedState: function(){
                var uncheckedNodes = privilege.operation.privilegeTreeOption.privilegeTreePlugin.getCheckedNodes(false);
                if (uncheckedNodes.length == 0) {//说明全部选中了
                    $("#allchecked").attr("checked", "checked");
                }
                else {//没有全部选中
                    $("#allchecked").attr("checked", "");
                }
            },
            /**
             * 保存
             *    建立用户和权限之间的关系
             */
            savePrivilege: function(){
                /**
                 *获取被选中的checkbox,把id放入到数组中
                 */
                var checkedNodes = privilege.operation.privilegeTreeOption.privilegeTreePlugin.getCheckedNodes(true);
                var checkedIDS = new Array();
                for (var i = 0; i < checkedNodes.length; i++) {
                    checkedIDS.push(checkedNodes[i].mid);
                }
                var parameter = {
                    uid: privilege.data.user.uid,
                    mids: checkedIDS
                };
				var para = $.toJSON(parameter);
                $.ajax({
                    type: "POST",
                    data: para,
                    url: "privilegeAction_savePrivilege.action?number=" + Math.random(),
                    contentType: "application/json",
                    success: function(data){
                        alert("aaa");
                    },
                    error: function(){
                    
                    }
                });
            }
        }
    }
};

$().ready(function(){
    privilege.init.initEvent();
});
