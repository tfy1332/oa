/**
 * 当加载kynamic.jsp完毕时，应该把知识管理树加载出来
 */
var kynamic = {
    data: {
        /**
         * 最常用的右键菜单保持默认值
         */
        rMenu: {
            addFolder: true,
            addFile: true,
            updateNode: true,
            deleteNode: true,
            x: 0,
            y: 0
        }
    },
    init: {
        initEvent: function(){
            /**
             * 当鼠标进入右键菜单时，做的事情和移出右键菜单时做的事情
             */
            $("#rMenu").hover(function(){
                /**
                 * 右键菜单事件的声明
                 */
                /**
                 * 添加文件夹事件
                 */
                $("#addFolder").unbind("click");
                $("#addFolder").bind("click", function(){
                    kynamic.kyanmicTreeOption.addFolder();
                });
                
                /**
                 * 添加文件事件
                 */
                $("#addFile").unbind("click");
                $("#addFile").bind("click", function(){
                    kynamic.kyanmicTreeOption.addFile();
                });
                
                /**
                 * 修改节点事件
                 */
                $("#updateNode").unbind("click");
                $("#updateNode").bind("click", function(){
                    kynamic.kyanmicTreeOption.updateNode();
                });
                
                /**
                 * 删除节点事件
                 */
                $("#deleteNode").unbind("click");
                $("#deleteNode").bind("click", function(){
                    kynamic.kyanmicTreeOption.deleteNode();
                });
            }, function(){
                $("#rMenu").hide();
            });
			/**
			 * 绑定checkin和checkout事件
			 */
			$("#checkin").unbind("click");
			$("#checkin").bind("click",function(){
				kynamic.versionOption.checkin();
			});
			
			$("#checkout").unbind("click");
			$("#checkout").bind("click",function(){
				
			});
        },
        initData: function(){
        
        }
    },
    /**
     * 树的所有的操作在该json对象中
     */
    kyanmicTreeOption: {
        pNode: '',
        kynamicTreePlugin: '',
        setting: {
            isSimpleData: true,
            treeNodeKey: "kid",
            treeNodeParentKey: "pid",
            showLine: true,
            root: {
                isRoot: true,
                nodes: []
            },
            /*
             *始终保持该节点的状态
             */
            keepParent: true,
            callback: {
                /**
                 * 右击事件
                 */
                rightClick: function(event, treeId, treeNode){
                    /**
                     * 把当前的节点赋值为pNode
                     */
                    kynamic.kyanmicTreeOption.pNode = treeNode;
                    kynamic.kyanmicTreeOption.showRMenu(event.clientX, event.clientY);
                    if (!treeNode.isParent) {//文件节点  文件夹节点保持默认值
                        kynamic.data.rMenu = $.extend(kynamic.data.rMenu, {
                            addFolder: false,
                            addFile: false
                        });
                    }
                    else {
                        $.extend(kynamic.data.rMenu, {
                            addFolder: true,
                            addFile: true
                        });
                    }
                    /**
                     * 在kynamic.data.rMenu的配置基础上给x和y赋值
                     */
                    $.extend(kynamic.data.rMenu, {
                        x: event.clientX,
                        y: event.clientY
                    });
                    kynamic.kyanmicTreeOption.showRMenu(kynamic.data.rMenu);
                },
                click: function(event, treeId, treeNode){
                    kynamic.kyanmicTreeOption.pNode = treeNode;
                    kynamic.versionOption.divShow();
                }
            }
        },
        loadKynamicTree: function(){
            $.post("kynamicAction_showKynamics.action", null, function(data){
                kynamic.kyanmicTreeOption.kynamicTreePlugin = $("#kynamicTree").zTree(kynamic.kyanmicTreeOption.setting, data.kynamicList);
            });
            //			$("#kynamicTree").createTree({
            //				setting:{
            //					treeNodeKey:'kid',
            //					callback:{
            //						"click":function(event, treeId, treeNode){
            //							$.tree.addNodes(treeNode,{
            //								kid:100,
            //								name:'aa',
            //								isParent:true,
            //								pid:1
            //							},false);	
            //						}
            //					}
            //				},
            //				url:'kynamicAction_showKynamics.action',
            //				callback:function(treeObj,data,setting){
            //					$.tree = treeObj.zTree(setting,data.kynamicList);
            //				}
            //			});
        },
        /**
         * 显示右键菜单的div
         */
        showRMenu: function(rMenuJSON){
            $("#rMenu").css({
                "top": rMenuJSON.y + "px",
                "left": rMenuJSON.x + "px",
                "display": "block"
            });
            if (rMenuJSON.addFolder) {
                $("#addFolder").show();
            }
            else {
                $("#addFolder").hide();
            }
            
            if (rMenuJSON.addFile) {
                $("#addFile").show();
            }
            else {
                $("#addFile").hide();
            }
            
            if (rMenuJSON.updateNode) {
                $("#updateNode").show();
            }
            else {
                $("#updateNode").hide();
            }
            
            if (rMenuJSON.deleteNode) {
                $("#deleteNode").show();
            }
            else {
                $("#deleteNode").hide();
            }
        },
        /**
         * 添加文件夹
         */
        addFolder: function(){
            kynamic.kyanmicTreeOption.addNode({
                title: '请输入文件夹的名称',
                error: '文件夹名称不能为空',
                isParent: true,
                message: '文件夹名已经存在'
            });
        },
        
        /***
         * 添加文件
         */
        addFile: function(){
            kynamic.kyanmicTreeOption.addNode({
                title: '请输入文件的名称',
                error: '文件名称不能为空',
                isParent: false,
                message: '文件名已经存在'
            });
        },
        /**
         * 增加文件和文件夹的通用方法
         */
        addNode: function(nodeJSON){
            /**
             * 思路：在数据库中的kynamic表中插入一行数据，在kynamic树上，把新建的节点追加到父节点上
             * 步骤：1、弹出一个输出框，输入文件的名称
             *       2、得到文件的名称，触发ajax请求，把isParent,name,pid传到后台
             *       3、从后台回调到前台新的节点的KID值
             *       4、创建一个新的节点，把新的节点追加到树上
             */
            var fileName = window.prompt(nodeJSON.title);
            /**
             * 如果一个变量为null,undefined,'',0,该变量放入到if语句中都为false
             */
            if (!fileName) {//特殊情况下的处理
                if (fileName == "") {
                    alert(nodeJSON.error);
                }
            }
            else {//正常情况
                /**
                 *  1、先检查该文件名称是否存在，如果存在，则提示该名称已经存在
                 *  2、如果不存在，则执行下面的操作
                 *            1、插入数据库数据
                 *            2、把新的节点追加到父节点上
                 */
                $.post("kynamicAction_showkynamicByName.action", {
                    name: fileName
                }, function(data){
                    if (data.message == "1") {//该文件的名称是可用的
                        /**
                         * 向后台发出ajax请求，保存到kynamic表中一行内容
                         */
                        var parameter = {
                            pid: kynamic.kyanmicTreeOption.pNode.kid,
                            isParent: nodeJSON.isParent,
                            name: fileName
                        };
                        $.post("kynamicAction_saveKynamic.action", parameter, function(data1){
                            var newNode = {
                                kid: data1.id,
                                name: fileName,
                                isParent: nodeJSON.isParent,
                                pid: kynamic.kyanmicTreeOption.pNode.kid
                            };
                            //把新的节点追加到父节点上
                            /*
                             * 最后一个参数
                             *    true 不展开
                             *    false 展开
                             */
                            kynamic.kyanmicTreeOption.kynamicTreePlugin.addNodes(kynamic.kyanmicTreeOption.pNode, newNode, false);
                        });
                    }
                    else {//文件的名称不可用
                        alert(nodeJSON.message);
                    }
                });
            }
        },
        /**
         * 修改节点
         */
        updateNode: function(){
            /**
             * 1、弹出一个框，这个框中有要修改的节点的名字
             * 2、在弹出框中，输入一个新的名字
             * 3、检查该名字是否可用
             * 4、修改
             *        数据库的修改
             *        树的修改
             */
            var fileName = window.prompt("请输入要修改的名字", kynamic.kyanmicTreeOption.pNode.name);
            if (!fileName) {//特殊情况下的处理
                if (fileName == "") {
                    alert("名字不能为空");
                }
            }
            else {
                $.post("kynamicAction_showkynamicByName.action", {
                    name: fileName
                }, function(data){
                    if (data.message == "1") {//该文件的名称是可用的
                        /**
                         * 向后台发出ajax请求，保存到kynamic表中一行内容
                         */
                        var parameter = {
                            kid: kynamic.kyanmicTreeOption.pNode.kid,
                            name: fileName
                        };
                        $.post("kynamicAction_updateKynamic.action", parameter, function(data1){
                            //把树上的节点的名称修改
                            kynamic.kyanmicTreeOption.pNode.name = fileName;
                            kynamic.kyanmicTreeOption.kynamicTreePlugin.refresh();
                        });
                    }
                    else {//文件的名称不可用
                        alert("该文件名称已经存在了");
                    }
                });
            }
        },
        /*
         * 删除节点
         */
        deleteNode: function(){
            /**
             * 判断当前删除的节点是文件节点还是文件夹节点
             *    *  如果是文件节点，则直接删除
             *    *  如果是文件夹节点，再判断该文件夹节点下是否存在子节点
             *         如果存在子节点，则提示不能删除
             *         如果不存在子节点，则删除
             */
            if (kynamic.kyanmicTreeOption.pNode.isParent) {//文件夹节点
                if (kynamic.kyanmicTreeOption.kynamicTreePlugin.getNodeByParam("pid", kynamic.kyanmicTreeOption.pNode.kid)) {//当前要删除的节点存在子节点
                    alert("存在子节点，不能删除");
                }
                else {
                    if (window.confirm("您确认要删除吗?")) {
                        /**
                         * 后台需要得到kid删除该节点，同时删除整个版本
                         */
                        $.post("kynamicAction_delete.action", {
                            kid: kynamic.kyanmicTreeOption.pNode.kid
                        }, function(data){
                            kynamic.kyanmicTreeOption.kynamicTreePlugin.removeNode(kynamic.kyanmicTreeOption.pNode);
                        });
                    }
                }
            }
            else {//文件节点
                if (window.confirm("您确认要删除吗?")) {
                    /**
                     * 后台需要得到kid删除该节点，同时删除整个版本
                     */
                    $.post("kynamicAction_delete.action", {
                        kid: kynamic.kyanmicTreeOption.pNode.kid
                    }, function(data){
                        kynamic.kyanmicTreeOption.kynamicTreePlugin.removeNode(kynamic.kyanmicTreeOption.pNode);
                    });
                }
            }
        }
    },
    /**
     * 所有的版本的操作
     */
    versionOption: {
        divShow: function(){
            /**
             * 根据知识管理节点查看该节点的版本号，如果有版本号，则显示版本号的列表，如果没有版本号，则显示版本添加的div
             */
            $.post("kynamicAction_showVersionsByKid.action", {
                kid: kynamic.kyanmicTreeOption.pNode.kid
            }, function(data){
                if (data.versionList.length == 0) {//没有版本号
                   kynamic.versionOption.controlShow({
				   		versionList:false,
						addVersion:true,
						checkin:true,
						checkout:false
				   });
                }
                else {//有版本号
                	kynamic.versionOption.controlShow({
				   		versionList:true,
						addVersion:false,
						checkin:false,
						checkout:false
				   });
				   /**
				    * <tr>
					    <td height="26" align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;"><a>3</a></td>
					    <td align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;">2010-5-24 09:56:33</td>
					    <td align="center" valign="middle" bgcolor="#FFFFFF" style="border-bottom:1px solid #f3f8fd;"><a>删除</a></td>
					  </tr>
				    */
					$("#versionContent").empty();
					for(var i=0;i<data.versionList.length;i++){
						var $aVersion = $("<a/>");
						$aVersion.text(data.versionList[i].versionid);
						$aVersion.css("cursor","pointer");
						var $tdVersion = $("<td/>");
						$tdVersion.attr("height","26");
						$tdVersion.attr("align","center");
						$tdVersion.attr("valign","middle");
						$tdVersion.attr("bgcolor","#FFFFFF");
						$tdVersion.attr("style","border-bottom:1px solid #f3f8fd;");
						$tdVersion.append($aVersion);
						
						var $tdDate = $("<td/>");
						$tdDate.attr("height","26");
						$tdDate.attr("align","center");
						$tdDate.attr("valign","middle");
						$tdDate.attr("bgcolor","#FFFFFF");
						$tdDate.attr("style","border-bottom:1px solid #f3f8fd;");
						$tdDate.text(data.versionList[i].updatetime);
						
						var $aDel = $("<a/>");
						$aDel.text("删除");
						var $tdDel = $("<td/>");
						$tdDel.attr("height","26");
						$tdDel.attr("align","center");
						$tdDel.attr("valign","middle");
						$tdDel.attr("bgcolor","#FFFFFF");
						$tdDel.attr("style","border-bottom:1px solid #f3f8fd;");
						$tdDel.append($aDel);
						
						var $tr = $("<tr/>");
						$tr.append($tdVersion);
						$tr.append($tdDate);
						$tr.append($tdDel);
						$("#versionContent").append($tr);
					}
                }
            });
        },
		/**
		 * 控制div和checkin和checkout的显示
		 */
		controlShow:function(versionJSON){
			if(versionJSON.addVersion){
				$("#addVersion").show();
			}else{
				$("#addVersion").hide();
			}
			
			if(versionJSON.versionList){
				$("#versionList").show();
			}else{
				$("#versionList").hide();
			}
			
			if(versionJSON.checkin){
				$("#checkin").show();	
			}else{
				$("#checkin").hide();
			}
			
			if(versionJSON.checkout){
				$("#checkout").show();
			}else{
				$("#checkout").hide();
			}
		},
		/**
		 * checkin操作
		 * 	1、检查该节点是否有版本号，如果没有，则添加一个版本号，并且版本为1
		 * 2、如果该节点有版本号，则版本号的最大值加1
		 */
		checkin:function(){
			var title = $("#title").val();
			var content = $("#content").val();
			var parameter = {
				title:title,
				content:content,
				kid:kynamic.kyanmicTreeOption.pNode.kid
			};
			$.post("kynamicAction_saveVersionByKid.action",parameter,function(data){
				
			});
		}
    }
};

$().ready(function(){
    kynamic.kyanmicTreeOption.loadKynamicTree();
    /**
     * 1、该方法写在这里，不能确保树加载出来
     * 2、事件分为两步：
     *     1、事件的声明
     *          声明写在哪都行
     *     2、事件的触发
     *          如果在事件触发的函数中用到回调函数中的值，那么触发的函数在执行的执行，回调函数必须执行了
     *
     * 3、说明：
     *    如果js的一段代码要用到回调函数中的数据，那么
     *         方案1、这段代码写在回调函数中
     *         方案2、可以写在触发事件的函数中，但是必须确保在事件触发的时候，毁掉函数已经执行完毕了
     */
    kynamic.init.initEvent();
});

/**
 * 作业：
 *    1、点击某一个版本号，显示增加版本的div,检查后台version表中相应的state的状态
 *         如果状态为true  说明上锁了，这个时候，页面上显示check out按钮  title和content处于不可编辑装填
 *         点击check out按钮，使后台的锁发生变化，页面上显示check in按钮，这个时候title和content处于可编辑状态
 *    2、删除某一个版本号
 *        超级链接的父亲的父亲的父亲干掉父亲的父亲
 */


