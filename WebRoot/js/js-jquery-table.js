/**
 * 1、动态的填充table的title
 * 2、动态的填充table中的内容
 * 3、如果table中是href或者button的处理方法
 */
var tableJSON = {
    target: 'mytable',
    title: ['aa', 'bb'],
    content: [{
        aa: {
			type:'text',
            text: '字'
        },
        bb: {
            type: 'a',//type=a  超级链接   type=button  按钮   type=text  显示文本内容
            text: '啊',
            handler: function(){
                alert("aaaa");
            }
        }
    }]
};

var createTable = function(tableJSON){
	/**
	 * 动态的拼接表头
	 */
    var titleArray = tableJSON.title;
    var $tr = $("<tr/>");
    for (var i = 0; i < titleArray.length; i++) {
        var $td = $("<td/>")
        $td.text(titleArray[i]);
        $tr.append($td);
    }
    $("#" + tableJSON.target).append($tr);
    
	/**
	 * 三层遍历
	 *    1、遍历所有的tr
	 *    2、遍历所有的td
	 *    3、遍历每一层td中的内容
	 */
    var contentArray = tableJSON.content;
    for (var j = 0; j < contentArray.length; j++) {
        var trJSON = contentArray[j];
        var $trContent = $("<tr/>");
        for (var a in trJSON) {
            //			var $td = $("<td/>");
            //			$td.text(trJSON[a]);
            //			$trContent.append($td);
            var trValueJSON = trJSON[a];
            for (var b in trValueJSON) {
                if (b == "type") {
                    if (trValueJSON[b] == "button") {//td的内容是button
                        var $button = $("<input/>");
                        $button.attr("type", "button");
                        $button.attr("value", trValueJSON['text']);
                        //回调函数的调用
                        $button.unbind("click");
                        $button.bind("click", function(){
                            trValueJSON['handler']();
                        });
                        var $td = $("<td/>");
                        $td.append($button);
                    }
                    else 
                        if (trValueJSON[b] == "a") {//td的内容是超级链接
                            var $a = $("<a/>");
                            $a.text(trValueJSON['text']);
							$a.css("cursor","pointer");
                            //回调函数的调用
                            $a.unbind("click");
                            $a.bind("click", function(){
                                trValueJSON['handler']();
                            });
                            var $td = $("<td/>");
                            $td.append($a);
							$trContent.append($td);
                        }
                        else {//文本内容
                            var $td = $("<td/>");
                            $td.text(trValueJSON['text']);
                            $trContent.append($td);
                        }
                }
            }
        }
        $("#" + tableJSON.target).append($trContent);
    }
}
$().ready(function(){
    createTable(tableJSON);
});

