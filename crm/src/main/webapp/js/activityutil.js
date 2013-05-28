var ACTIVITY_UTIL = (function ($,w,undefined) {
    
    
    
    var queryRemoteActivities = function (onCompleted,onError) {
        var args = {};
        args.f = "getActivitieTableDataByUserId";
        args.p = [ "20" ];
        ajaxPost2(args, function onAjaxComplete(data) {
           /* var event = jQuery.Event("on_activities_received");
            event.received_data =data;
            $(document).trigger(event);*/
            onCompleted(data);
            setRemoteActivities2LocalStorage(data);
        }, function onError(status) {
            onError(status);
        });
        
    },
    
    convertActivityTableData2CallendarEvents = function(data){
        var events = [];
        if(!$.isEmptyObject(data) &&  data.tData != undefined && data.tData != null){
            $.each(data.tData, function(key,value){
                if(value.length>0){
                    var event = {};
                        event.id = value[0][0];
                        event.title = getEventTitle(value[0][6]);
                        event.start = value[0][2];
                        event.end = value[0][3];
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
        ed = JSON.parse(ed||[]);
        ed.push(event);
        localStorage["localevents"] = JSON.stringify(dupData);
    },
    
    getRemoteActivitesFromLocalStorage = function(){
        
    },
    
    getCalendarEventFromLocalStorage = function(){
        localStorage["localevents"] = JSON.stringify(dupData);
    };
    
    
    return {
        queryRemoteActivities: queryRemoteActivities,
        convertActivityTableData2CallendarEvents:convertActivityTableData2CallendarEvents
    };
    
    

   
    
} (jQuery,window));

