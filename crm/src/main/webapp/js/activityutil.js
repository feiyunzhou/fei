var ACTIVITY_UTIL = (function ($,w,undefined) {
    
    
    
    var queryRemoteActivities = function (onCompleted,onError) {
        var args = {};
        args.f = "getActivitiesTableDataByUserId";
        args.p = [ "20" ];
        ajaxPost2(args, function(data) {
           /* var event = jQuery.Event("on_activities_received");
            event.received_data =data;
            $(document).trigger(event);*/
            onCompleted(data);
            setRemoteActivities2LocalStorage(data);
        }, function(status) {
            onError(status);
        });
        
    },
    
    getActivityIdsOfContactId = function (onCompleted,onError) {
        var args = {};
        args.f = "getActivityIdsOfContactIdByUserId";
        args.p = [ "20" ];
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
    
    convertActivityTableData2CallendarEvents = function(data){
        var events = [];
        if(!$.isEmptyObject(data) &&  data.tData != undefined && data.tData != null){
            $.each(data.tData, function(key,value){
                if(value.length>0){
                    var event = {};
                        event.id = value[0][0];
                        event.title = getEventTitle(value[0][6]);
                        event.start = parseInt(value[0][2])/1000;
                        event.end = parseInt(value[0][3])/1000;
                        event.color = getEventColor(value[0][6]);
                        event.allDay = false;
                    
                        events.push(event);
                }
            });
        }
        
        return events;
    },
    
    getEventColor = function(type) {
        if (type == 1) {
            //#D06B64; #5484ed;#a4bdfc;rgb(255, 136, 124);                  
            return "#5484ed";   
        } else if (type == 2) {
            return "#D06B64";
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
   
    postCalendarEvent = function (userEvent, isEventInLocalStorage,onComplete,onError) {
        var args = {};
        args.f = "setEvent";
        args.p = [ userEvent.crmUserId,userEvent.contactId, userEvent.activity_type, userEvent.title,userEvent.startt, userEvent.endt ];
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

     postCalendarEvents = function(userEvents, isEventInLocalStorage, onComplete, onError) {
        if (userEvents.length === 0)
            return;
        for ( var i = 0; i < userEvents.length; i += 1) {
            if (i == (userEvents.length - 1)) {
                postCalendarEvent(userEvents[i], isEventInLocalStorage, function(data) {
                    onComplete(data);
                }, function(status) {
                    console.log("post error"+status);
                    onError(status);
                });
            } else {
                postCalendarEvent(userEvents[i], isEventInLocalStorage, function(data) {

                }, function(status) {
                    
                });
            }
        }

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
        getActivityIdsOfContactIdFromLocalStorage : getActivityIdsOfContactIdFromLocalStorage
        
    };
    
    

   
    
} (jQuery,window));

