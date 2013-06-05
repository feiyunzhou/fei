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
    backto = function (page) {
        location.href = page;
    },
    getUserId = function(){
        return "20";
    },
     getParameterByName = function(name) {
        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
        var url = window.location.toString();
        var regex = new RegExp("#.*[\\?&]" + name + "=([^&#]*)"), results = regex.exec(url);
        return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
    };
    
    
    return{
        init:init,
        getUserId:getUserId,
        backto:backto,
        getParameterByName: getParameterByName
    };
    
    
})(jQuery,window);


