var CRM_OFFLINE = (function($, w, undefined) {
    
    
    var init = function(){
        if (w.applicationCache) {
            w.applicationCache.addEventListener('updateready', function() {
                if (confirm('An update is available. Reload now?')) {
                    w.location.reload();
                }
            });
        }

        w.applicationCache.addEventListener('checking', logEvent, false);
        w.applicationCache.addEventListener('noupdate', logEvent, false);
        w.applicationCache.addEventListener('downloading', logEvent, false);
        w.applicationCache.addEventListener('progress', logEvent, false);
        w.applicationCache.addEventListener('cached', logEvent, false);
        w.applicationCache.addEventListener('updateready', logEvent, false);
        w.applicationCache.addEventListener('obsolete', logEvent, false);
        w.applicationCache.addEventListener('error', logEvent, false);
    },
    logEvent = function(){
        console.log(event.type);
    },
    getUserId = function(){
        return "20";
    };
    
    return{
        init:init,
        getUserId:getUserId
    };
    
    
})(jQuery,window);