/**
 * User: feiyunzhou
 * Date: 13-6-22
 * Time: PM9:14
 */


var LOGIN_UTIL = (function($, w, undefined) {

    var login = function(loginName,password,onCompl, onError){

        var args = {};
        args.f = "login";
        args.p = [ loginName,password ];

        ajaxPost2(args, function(data) {
            if (jQuery.isEmptyObject(data) === false) {
                setLoginInfo2Localstorage(data);
                if (onCompl != undefined){onCompl(data);}
            }else{
                console.log ("failed to login");
                if (onError != undefined){onError(null);}
            }
        }, function(status) {
            if (onError != undefined){onError(status);}

        });

        },

        setLoginInfo2Localstorage = function(loginInfo){
            localStorage["loginInfo"] = JSON.stringify(loginInfo);
        },


        getLoginInfoFromLocalstorage = function(){
            var data = localStorage["loginInfo"];
            data = data || "{}";
            return JSON.parse(data);
        };


    return {
        login:login,
        getLoginInfoFromLocalstorage: getLoginInfoFromLocalstorage
    };

}(jQuery, window));