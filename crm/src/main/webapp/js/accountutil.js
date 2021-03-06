var ACCOUNT_UTIL = (function($, w, undefined) {

    var getAccountTableDataRemotely = function(userInfo, onCmpl, onError) {
        var args = {};
        args.f = "getAccountIndexTable";
        var userId = userInfo.id+"";
        args.p = [ userId ];
        args.s = {id:userId,key: userInfo.password};
        ajaxPost2(args, function(data) {
            if (jQuery.isEmptyObject(data) === false) {
                setAccountTable2Localstorage(data);
                if (onCmpl != undefined){onCmpl(data);}
                    
            }
        }, function(status) {
            if (onError != undefined){onError(status);}
                
        });
    },
    getAccountByIdLocally = function(id){
        var acct = {};
        var table = getAccountTableFromLocalstorage();
        if(!$.isEmptyObject(table)){
            if(table.cols != undefined && table.tData != undefined && table.tData[id] != undefined && table.tData[id][0] != undefined){
                var length = table.cols.length;
                var row = table.tData[id][0];
                for(var i=0;i<length;i += 1){
                    acct[table.cols[i].name] = row[i];
                }
            }
        }
        return acct;
    },

    getAllAccountsLocally = function(){
        var accts = [];
        var table =  getAccountTableFromLocalstorage();
       if(!$.isEmptyObject(table)){
           if(table.cols != undefined && table.tData != undefined){
               var collength = table.cols.length;
               $.each(table.tData,function(key,value){
                   var row = value[0];
                   var acct = {};
                   for(var i=0;i<collength;i += 1){
                       acct[table.cols[i].name] = row[i];
                   }
                   accts.push(acct);
               });
           }
       }
       return accts;
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
        getAccountTableFromLocalstorage : getAccountTableFromLocalstorage,
        getAccountByIdLocally: getAccountByIdLocally,
        getAllAccountsLocally: getAllAccountsLocally
    };

}(jQuery, window));