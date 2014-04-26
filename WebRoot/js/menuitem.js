$().ready(function(){
	var treePlugin = $("#menuTree").createTree({
		url:'menuitemAction_showMenuitemsByUser.action',
		callback:function(treeObj,data,setting){
			alert(data.menuitemList);
			return treeObj.zTree(setting,data.menuitemList);
		}
	});
});
