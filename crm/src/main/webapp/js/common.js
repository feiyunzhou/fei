function ajaxPost(args, onComplete) {
	$.ajax({
		url : ajaxURL,
		type : 'post',
		cache : false,

		data : JSON.stringify(args),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		complete : function(xhr, status) {
			//console.log("completed: " + xhr.responseText);

		},
		success: function(json) {
			console.log("Success data returned:");
			onComplete(json);

        },
		error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("error :"+XMLHttpRequest.responseText);
			console.log(errorThrown);
         }
    
	});
}
var flag = true;
function chengeicon(v){
	console.info("flag:"+flag);
	console.info("flag:"+v.id);
	if(flag){
			$("#" + v.id + "").removeClass("icon-angle-down");
			$("#" + v.id + "").addClass("icon-angle-right");	
		flag = false;
	}else{
		$("#" + v.id + "").removeClass("icon-angle-right");
		$("#" + v.id + "").addClass("icon-angle-down");
		flag = true;
	
	}
};
