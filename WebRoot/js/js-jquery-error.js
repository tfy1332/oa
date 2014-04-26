(function($){
    $.oaAjax = function(ajaxJSON){
        $.ajax({
            type: ajaxJSON.type,
            url: ajaxJSON.url,
            success: function(data){
                if (data.ss != "") {
                    alert(data.ss);
                }
                else {
                    ajaxJSON.success(data);
                }
            }
        });
    }
})($);
$().ready(function(){
    $("body").ajaxError(function(event, request, settings){
        $(this).append("<li>出错页面:" + settings.url + "</li>");
    });
});
