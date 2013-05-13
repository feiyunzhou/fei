function ajaxPost(args, onComplete) {
	$.ajax({
		url : "DataFeederServlet",
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
