(function($){
	var treePlugin = '';
	
	var setting = {
		isSimpleData: true,
        treeNodeKey: "mid",
        treeNodeParentKey: "pid",
        showLine: true,
        root: {
            isRoot: true,
            nodes: []
        }
	};
	$.fn.createTree = function(treeJSON){
		//在调用的时候，把程序员写的setting覆盖掉原来的setting
		var treeObj = $(this);
		var treePlugin = '';
		$.tree = function(){};
		$.extend(setting,treeJSON.setting);
		//createTree方法是由树的容器调用
		$.post(treeJSON.url,treeJSON.parameter,function(data){
			//treeObj.zTree(setting,data.menuitemList);
			treeJSON.callback(treeObj,data,setting);
		});	
		//setInterval("blackF(treePlugin)",50);
	}
})($);
