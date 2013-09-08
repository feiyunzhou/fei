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


function ajaxPost2(args, onComplete,onError) {
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
        statusCode:{
            403: function (response) {
                console.log("auth error");
                //alert("密码已更改或过期！请重新登录！");
                //CRM_OFFLINE.gotoPage("login.html");
             }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("error :"+textStatus);
            onError(textStatus);
         }
    
    });
}