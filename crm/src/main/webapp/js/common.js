function ajaxPost(args, onComplete) {
	$.ajax({
		url : ajaxURL,
		type : 'post',
		cache : false,

		data : JSON.stringify(args),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		complete : function(xhr, status) {
			// console.log("completed: " + xhr.responseText);

		},
		success : function(json) {
			console.log("Success data returned:");
			onComplete(json);

		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log("error :" + XMLHttpRequest.responseText);
			console.log(errorThrown);
		}

	});
}
function chengeicon(v) {
	var str = $(v).attr('class');
	var va = v.id;
	if (va == 1) {
		if (str == "icon-angle-down" ) {
			$("#" + v.id + "").removeClass("icon-angle-down");
			$("#" + v.id + "").addClass("icon-angle-right");
		} else {
			$("#" + v.id + "").removeClass("icon-angle-right");
			$("#" + v.id + "").addClass("icon-angle-down");
		}
	} else if(va == 2){
		if (str == "icon-angle-right") {
			$("#" + v.id + "").removeClass("icon-angle-right");
			$("#" + v.id + "").addClass("icon-angle-down");
			flag = true;
		} else {
			$("#" + v.id + "").removeClass("icon-angle-down");
			$("#" + v.id + "").addClass("icon-angle-right");
			flag = false;
		}
	}else{
		if (str == "icon-angle-right") {
			$("#" + v.id + "").removeClass("icon-angle-right");
			$("#" + v.id + "").addClass("icon-angle-down");
		} else {
			$("#" + v.id + "").removeClass("icon-angle-down");
			$("#" + v.id + "").addClass("icon-angle-right");
		}
	}

};
