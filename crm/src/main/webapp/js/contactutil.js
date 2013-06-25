var CONTACT_UTIL = (function($, w, undefined) {

    var getContactTableDataRemotely = function(userInfo, onComp, onError) {
        var args = {};
        args.f = "getContactIndexTable";
        var userId = userInfo.id+"";
        args.p = [ userId ];
        args.s = {id:userId,key: userInfo.password};
        ajaxPost2(args, function(data) {
            if (jQuery.isEmptyObject(data) === false) {
                setContactTable2Localstorage(data);
                if (onComp != undefined)
                    onComp(data);
            }
        }, function(status) {
            if(onError != undefined){
                onError(status);
            }
        });
    },
    
    getContactIdsOfAccountIdRemotely = function(userInfo,onComp,onError){
        var args = {};        
        args.f = "getContactIdsOfAccountIdByUserId";
        var userId = userInfo.id+"";
        args.p = [ userId ];
        args.s = {id:userId,key: userInfo.password};
        ajaxPost2(args, function(data) {
            if (jQuery.isEmptyObject(data) === false) {
                setContactIdsOfAccountId2Localstorage(data);
                if (onComp != undefined)
                    onComp(data);
            }
        }, function(status) {
            if(onError != undefined){
                onError(status);
            }
        });
    },

    setContactIdsOfAccountId2Localstorage = function(data) {
        localStorage["contactIdsofaccountId"] = JSON.stringify(data);
    },

    getContactIdsOfAccountIdFromLocalstorage = function() {
        return JSON.parse(localStorage["contactIdsofaccountId"] || '{}');
    },
    

    getContactsByAccountIdLocally = function(id) {
        var contacts = [];

        var contactIds_by_accountId = getContactIdsOfAccountIdFromLocalstorage();
        var contactIds = contactIds_by_accountId[id];

        if (contactIds != undefined) {
            for ( var i = 0; i < contactIds.length; i += 1) {
                var ct = getContactByIdLocally(contactIds[i]);
                contacts.push(ct);
            }
        }
        return contacts;
    },
    
    getContactByIdLocally = function(contactId){
        var ct = {};
        var table = getContactTableFromLocalstorage();
        if(!$.isEmptyObject(table)){
            if(table.cols != undefined && table.tData != undefined && table.tData[contactId] != undefined && table.tData[contactId][0] != undefined){
                var length = table.cols.length;
                var row =  table.tData[contactId][0];
                for(var i=0;i<length;i += 1){
                    ct[table.cols[i].name] = row[i];
                }
            }
        }
        return ct;
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
        getContactTableFromLocalstorage : getContactTableFromLocalstorage,
        getContactIdsOfAccountIdRemotely:getContactIdsOfAccountIdRemotely,
        setContactIdsOfAccountId2Localstorage: setContactIdsOfAccountId2Localstorage,
        getContactIdsOfAccountIdFromLocalstorage:getContactIdsOfAccountIdFromLocalstorage,
        getContactsByAccountIdLocally : getContactsByAccountIdLocally,
        getContactByIdLocally:getContactByIdLocally

    };
}(jQuery, window));