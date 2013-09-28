function isEmail(strEmail) {
	var prompt = "您输入的邮箱不正确!";
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
		return true;
	else
		alert(prompt);
}
function checkMobile(s) {
	var regu = /^[1][358][0-9]{9}$/;
	var prompt = "您输入的移动电话号码不正确!";
	var re = new RegExp(regu);
	if (re.test(s)) {
		return true;
	} else {
		alert(prompt);
	}
}
function checkPhone( strPhone ) 
{
    var phoneRegWithArea = /^[0][1-9]{2,3}-[0-9]{5,10}$/;
    var phoneRegNoArea = /^[1-9]{1}[0-9]{5,8}$/;
    var prompt = "您输入的电话号码不正确!";
    	if ( strPhone.length > 9 ) {
        if ( phoneRegWithArea.test(strPhone) ) {
            return true;
        }
        else {
            alert( prompt );
            return false;
        }
    }
    else {
        if ( phoneRegNoArea.test( strPhone ) ) {
            return true;
        }
        else {
            alert( prompt );
            return false;
        }
    }
};
function isNull( str )
{
	var prompt ="请输入姓名！";
	var prompt1 ="姓名不能为空格！";
    if ( str == "" ) {
    	alert(prompt);
    	return false;
    }
    var regu = /^[ ]+$/;
    var re = new RegExp(regu);
    if(re.test(str)){
    	alert(prompt1);
    	return false;
    }else{
    	return true;
    }
};
function isTel(object)
 {
 var prompt ="传真号码不能为空！";
 var s ="请输入正确的电话号码:电话号码格式为国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"; 
 var pattern =/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
 var re = new RegExp(pattern);
 
      if(object!=""){
          if(!re.test(object))
          {
           alert(s);
          }
      }else{
    	  alert(prompt);  
      }
 };
 function isNumber( s )
 {
     var regu = "^[0-9]+$";
     var prompt = "输入有误！请输入数字！";
     var re = new RegExp(regu);
     if (s.search(re) != - 1) {
         return true;
     }
     else {
    	 alert(prompt);
         return false;
     }
 };

