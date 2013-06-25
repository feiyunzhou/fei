var ACTIVITY_UTIL = (function ($,w,undefined) {
    
    
    
    var queryRemoteActivities = function (userInfo,onCompleted,onError) {
        var args = {};
        args.f = "getActivitiesTableDataByUserId";
        var userId = userInfo.id+"";
        args.p = [ userId ];
        args.s = {id:userId,key: userInfo.password};
        ajaxPost2(args, function(data) {
           /* var event = jQuery.Event("on_activities_received");
            event.received_data =data;
            $(document).trigger(event);*/
            if(onCompleted != undefined)onCompleted(data);
            setRemoteActivities2LocalStorage(data);
        }, function(status) {
            if(onError != undefined)onError(status);
        });
        
    },
    
    getActivityIdsOfContactId = function (userInfo,onCompleted,onError) {
        var args = {};
        args.f = "getActivityIdsOfContactIdByUserId";
        var userId = userInfo.id+"";
        args.p = [ userId ];
        args.s = {id:userId,key: userInfo.password};
        ajaxPost2(args, function(data) {
            if( onCompleted != undefined){
               onCompleted(data);
            }
            setActivityIdsOfContactId2LocalStorage(data);
        }, function(status) {
            if(onError != undefined){
              onError(status);
            }
        });
        
    },
    
    setActivityIdsOfContactId2LocalStorage = function(data){
        localStorage["activityIdsOfcontactId"] = JSON.stringify(data);
    },
    
    getActivityIdsOfContactIdFromLocalStorage = function(data){
        var acts =  localStorage["activityIdsOfcontactId"] || '{}';
        return JSON.parse(acts);
    },
    
    convertActivityArray2Object = function(cols,activityArray){
        var ret = {};
        if(cols.length>0 && activityArray.length>0){
            var len = cols.length;
            for(var i=0;i<len;i += 1){
                ret[cols[i].name] = activityArray[i];
            }
        }
        
        return ret;
    },
    
    convertActivityTableData2CallendarEvents = function(data){
        var events = [];
        if(!$.isEmptyObject(data) &&  data.tData != undefined && data.tData != null){
            $.each(data.tData, function(key,value){
                if(value.length>0){
                  var  act = convertActivityArray2Object(data.cols,value[0]);
                  var event = {};
                      event.id = act.id;
                      event.title = act.status;
                      event.start =  parseInt(act.starttime)/1000;
                      event.end = parseInt(act.endtime)/1000;
                      event.color = getEventByStatusAndType(act.act_status,act.act_type);
                      event.allDay = false;
                      events.push(event);
                }
            });
        }
        
        return events;
    },
    getEventByStatusAndType = function(status,type){
        if(status ==1){
            if(type==1){
                return "#2B60DE";
            }else if(type == 2){
                return "#E18B6B";
            }else{
                return "#6698FF";
            }
            
        }else{
            return "#659EC7";
        }
    },
    getEventColor = function(type) {
        if (type == 1) {
            //#5484e, #D06B64; #5484ed;#a4bdfc;rgb(255, 136, 124);                  
            return "#5484ed";   
        } else if (type == 2) {
            return "#5484e";
        }
         return "#a4bdfc";
        
    
    },
    
    getEventTitle = function(type) {
        if (type == 1) {
            //#D06B64; #5484ed;#a4bdfc;rgb(255, 136, 124);                  
            return "常规";
        } else if (type == 2) {
            return "会议";    
        }
        return "其他";
    
    },
    
    
    setRemoteActivities2LocalStorage = function(data){
        localStorage["remoteactivities"] = JSON.stringify(data);
    },
    
    setCalendarEvent2LocalStorage = function(event){
        var ed = localStorage["localevents"];
        ed = JSON.parse(ed||'{}');
        ed[event.id] = event;
        localStorage["localevents"] = JSON.stringify(ed);
    },
    
    getRemoteActivitesFromLocalStorage = function(){
        var acts =  localStorage["remoteactivities"] || '{}';
        return JSON.parse(acts);
    },
    
    getCalendarEventFromLocalStorage = function(){
        var ed = localStorage["localevents"];
        ed = ed||'{}';
        var obj = JSON.parse(ed);
        
        return obj;
    },
    
    resetCalendarEventFromLocalStroage = function(){
        localStorage["localevents"] = JSON.stringify({});
    },
   
    postCalendarEvent = function (userInfo,userEvent, isEventInLocalStorage,onComplete,onError) {
        var args = {};
        args.f = "addCalendarEvent";
        var userId = userInfo.id+"";
        args.s = {id:userId,key: userInfo.password};
        args.p = [ userEvent.crmUserId,userEvent.contactId, userEvent.activity_type, userEvent.title,userEvent.startt, userEvent.endt,userEvent.status ];
        ajaxPost2(args, function(resp) {
            isOnline = true;
            //console.log(resp);
            if (resp.code == 0) { 
                console.log("post calendar event successfully");
                onComplete();
            } else {
                console.log("failed to set event");
            }

            return resp.code;
        }, function(status) {
            onError(status);
            if (!isEventInLocalStorage){
                
            }
            
                
        });
    },

     postCalendarEvents = function(userInfo, userEvents, isEventInLocalStorage, onComplete, onError) {
        if (userEvents.length === 0)
            return;
        for ( var i = 0; i < userEvents.length; i += 1) {
            if (i == (userEvents.length - 1)) {
                postCalendarEvent(userInfo,userEvents[i], isEventInLocalStorage, function(data) {
                    onComplete(data);
                }, function(status) {
                    console.log("post error"+status);
                    onError(status);
                });
            } else {
                postCalendarEvent(userInfo,userEvents[i], isEventInLocalStorage, function(data) {

                }, function(status) {
                    
                });
            }
        }

    },
    updateStatusOfCalendarEventRemotely = function(userInfo,eventId, status, onComplete, onError){
        var args = {};
        args.f = "updateStatusOfCalendarEvent";
        var userId = userInfo.id+"";
        args.s = {id:userId,key: userInfo.password};
        args.p = [eventId,status];

        ajaxPost2(args, function(resp) {
            isOnline = true;
            //console.log(resp);
            if (resp.code == 0) { 
                console.log("updateStatusOfCalendarEventRemotely successfully");
                if(onComplete !=undefined)onComplete(resp);
            } else {
                console.log("failed to updateStatusOfCalendarEventRemotely");
                if(onError != undefined ) onError(status);  
            }

            return resp.code;
        }, function(status) {
            if(onError != undefined ) onError(status);    
        });
    },
    
    updateStatusOfCalendarEventLocally = function(eventId,status){
        
        var localevents = getCalendarEventFromLocalStorage();
        
        if(localevents[eventId] != undefined){
            localevents[eventId].status = status;
        }else{
            setStatusChangesInLocalstorage(eventId,status);
        }
    },
    
    isRemoteActivityInLocalstorage = function(activityId){
       var activities = getRemoteActivitesFromLocalStorage();
       if(activities.tData != undefined && activities.tData[activityId] != undefined){
           return true;
       }
       return false;
       
    },
    
    setStatusChangesInLocalstorage = function(activityId, status){
        var changes = localStorage["activitystatuschanges"];
        echangesd = JSON.parse(changes||'{}');
        changes[event.id] = status;
        localStorage["activitystatuschanges"] = JSON.stringify(changes);
    },
    
    getStatusChangesFromLocalstorage = function(activityId, status){
        var changes =  localStorage["activitystatuschanges"] || '{}';
        return JSON.parse(changes);
    },
    
    resetStatusChangesInLocalstorage = function(activityId, status){
        localStorage["activitystatuschanges"] = JSON.stringify({});
    },
    
    parseDate = function (input) {
        var parts = input.match(/(\d+)/g);
        return new Date(parts[0], parts[1]-1, parts[2]); // months are 0-based
      };
    
    
    return {
        queryRemoteActivities : queryRemoteActivities,
        convertActivityTableData2CallendarEvents : convertActivityTableData2CallendarEvents,
        getRemoteActivitesFromLocalStorage : getRemoteActivitesFromLocalStorage,
        getCalendarEventFromLocalStorage : getCalendarEventFromLocalStorage,
        postCalendarEvents : postCalendarEvents,
        getEventColor : getEventColor,
        getEventTitle : getEventTitle,
        setCalendarEvent2LocalStorage : setCalendarEvent2LocalStorage,
        postCalendarEvent : postCalendarEvent,
        parseDate : parseDate,
        resetCalendarEventFromLocalStroage : resetCalendarEventFromLocalStroage,
        getActivityIdsOfContactId : getActivityIdsOfContactId,
        setActivityIdsOfContactId2LocalStorage : setActivityIdsOfContactId2LocalStorage,
        getActivityIdsOfContactIdFromLocalStorage : getActivityIdsOfContactIdFromLocalStorage,
        updateStatusOfCalendarEventRemotely: updateStatusOfCalendarEventRemotely,
        updateStatusOfCalendarEventLocally: updateStatusOfCalendarEventLocally,
        isRemoteActivityInLocalstorage: isRemoteActivityInLocalstorage,
        setStatusChangesInLocalstorage: setStatusChangesInLocalstorage,
        getStatusChangesFromLocalstorage: getStatusChangesFromLocalstorage,
        resetStatusChangesInLocalstorage: resetStatusChangesInLocalstorage,
        getEventByStatusAndType : getEventByStatusAndType
        
    };
    
    

   
    
} (jQuery,window));

