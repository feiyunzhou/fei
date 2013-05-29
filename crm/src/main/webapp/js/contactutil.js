var CONTACT_UTIL = (function($, w, undefined) {

    var getContactTableDataRemotely = function(userId, onComp, onError) {
        var args = {};
        args.f = "getContactIndexTable";
        args.p = [ userId ];
        ajaxPost2(args, function onComplete(data) {
            if (jQuery.isEmptyObject(data) === false) {
                setContactTable2Localstorage(data);
                if (onComp != undefined)
                    onComp(data);
            }
        }, function onError(status) {
            isOnline = false;
            renderContactTableWithDataLocal();
        });
    },

    setContactTable2Localstorage = function(data) {
        localStorage["contacttable"] = JSON.stringify(data);
    },

    getContactTableFromLocalstorage = function() {
        return JSON.parse(localStorage["contacttable"] || '{}');
    };
    
    return {
        getContactTableDataRemotely : getContactTableDataRemotely,
        setContactTable2Localstorage : setContactTable2Localstorage,
        getContactTableFromLocalstorage : getContactTableFromLocalstorage

    };
}(jQuery, window));