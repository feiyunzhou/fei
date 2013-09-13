/**
 * User: feiyunzhou
 * Date: 2013-7-3
 * Time: PM 9:51
 */

var CRMUSER_UTIL = (function($, w, undefined) {

    var getCoachTableDataRemotely = function(userInfo, onComp, onError) {
            var args = {};
            args.f = "getCoachIndexTable";
            var userId = userInfo.id+"";
            args.p = [ userId ];
            args.s = {id:userId,key: userInfo.password};
            ajaxPost2(args, function(data) {
                if (jQuery.isEmptyObject(data) === false) {
                    setCoachTable2Localstorage(data);
                    if (onComp != undefined)
                        onComp(data);
                }
            }, function(status) {
                if(onError != undefined){
                    onError(status);
                }
            });
        },


        getInferiorsRemotely = function(userId,onComp,onError){
          var args = {};
          args.f = "getInferiorsByManagerId";
          args.p = [userId];

            ajaxPost2(args, function(data) {
                if (jQuery.isEmptyObject(data) === false) {
                    //setCoachTable2Localstorage(data);
                    if (onComp != undefined)
                        onComp(data);
                }
            }, function(status) {
                if(onError != undefined){
                    onError(status);
                }
            });

        },

        getValidCoachesLocally = function(){
            var accts = [];
            var table =  getCoachTableFromLocalstorage();
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


        getCoachByIdLocally = function(userId){
            var ct = {};
            var table = getCoachTableFromLocalstorage();
            if(!$.isEmptyObject(table)){
                if(table.cols != undefined && table.tData != undefined && table.tData[userId] != undefined && table.tData[userId][0] != undefined){
                    var length = table.cols.length;
                    var row =  table.tData[userId][0];
                    for(var i=0;i<length;i += 1){
                        ct[table.cols[i].name] = row[i];
                    }
                }
            }
            return ct;
        },

        setCoachTable2Localstorage = function(data) {
            localStorage["coachtable"] = JSON.stringify(data);
        },

        getCoachTableFromLocalstorage = function() {
            return JSON.parse(localStorage["coachtable"] || '{}');
        };

    return {
        getCoachTableDataRemotely : getCoachTableDataRemotely,
        getCoachByIdLocally : getCoachByIdLocally,
        setCoachTable2Localstorage : setCoachTable2Localstorage,
        getCoachTableFromLocalstorage:getCoachTableFromLocalstorage,
        getValidCoachesLocally:getValidCoachesLocally,
        getInferiorsRemotely:getInferiorsRemotely

    };
}(jQuery, window));