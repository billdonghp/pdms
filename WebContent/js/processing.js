//show processing dialog
var Dialog = {};
Dialog.show = function(message, resizeble){	
	//the whole div
	var width = 0, height = 0;
	if(resizeble){
		width = screen.availWidth;
		height = screen.availHeight;	
	}else{
		width = document.body.clientWidth;
		height = document.body.clientHeight;	
	}
	message = $.trim(message) == "" ? "Processing..." : message;
	//add div
	$(document.body).append($("<div class = 'whole-Div' id = 'whole-Div'><div><img src = 'blue-loading.gif'>" + message + "</div></div>"));
	//set width&height
	$("#whole-Div").width(width);
	$("#whole-Div").height(height);
	$("#whole-Div div").css("margin-top", resizeble ? height/4 : height/3);
}
//close processing dialog
Dialog.close = function(){
	$("#whole-Div").remove();
}