var ACCOUNT_UTIL = (function($, w, undefined) {

    var getAccountTableDataRemotely = function(userId, onCmpl, onError) {
        var args = {};
        args.f = "getAccountIndexTable";
        args.p = [ userId ];
        ajaxPost2(args, function onComplete(data) {
            if (jQuery.isEmptyObject(data) === false) {
                setAccountTable2Localstorage(data);
                if (onCmpl != undefined){onCmpl(data);}
                    
            }
        }, function onError(status) {
            if (onError != undefined){onError(status);}
                
        });
    },

    setAccountTable2Localstorage = function(data) {
        localStorage["accounttable"] = JSON.stringify(data);
    },

    getAccountTableFromLocalstorage = function() {
        var data = localStorage["accounttable"];
        data = data || "{}";
        return JSON.parse(data);
    };

    return {
        getAccountTableDataRemotely : getAccountTableDataRemotely,
        setAccountTable2Localstorage : setAccountTable2Localstorage,
        getAccountTableFromLocalstorage : getAccountTableFromLocalstorage
    };

}(jQuery, window));