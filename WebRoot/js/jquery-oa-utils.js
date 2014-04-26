(function($){
	$.fn.privilegeShow = function(){
		/**
		 * 因为jQuery方法返回的都是数组对象，所以这里的this代表数组
		 */
		var array = $(this);
		for(var i=0;i<array.length;i++){
			if($(array[i]).css("display")=="none"){
				$(array[i]).css("display","block");
			}
		}
	}
})($);
