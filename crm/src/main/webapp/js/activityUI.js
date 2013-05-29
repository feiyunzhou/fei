$(document).ready(function() {
    $('#active_back_btn').click(function(e) {
        $('#main_container').toggle();
        $('#activity_container').toggle();

    });

});

function renderActivityTableOnPage(id) {
    console.log("Start to render account detaile page now");
    console.log("activity id is:" + id);

    //get accountTable from local storage
    var table = ACTIVITY_UTIL.getRemoteActivitesFromLocalStorage();
    console.log(table);
    var tbobj = $("#detail_tb");
    tbobj.empty();
    var isEventLocated = false;
    if (table != null && table.cols != undefined && table.tData != undefined) {

        if (table.cols.length > 0 && (table.tData[id] != undefined) && (table.tData[id] != null)) {
            isEventLocated = true;
            var cols = table.cols;
            var colWidth = table.cols.length;
            var trs = "";
            var rowData = table.tData[id][0];
            $('#detail_title').append("<span>" + rowData[1] + "</span>");
            for ( var i = 1; i < colWidth; i++) {
                if (cols[i].isVisible === false)
                    continue;
                if (i == 2 || i == 3) {
                    var date = new Date(parseInt(rowData[i]));
                    console.log(date);
                    var str_date = $.fullCalendar.formatDate(date, "yyyy-MM-dd HH:mm:ss");
                    trs = trs + "<tr>" + "<td style=\"text-align:left;font-weight:bold\">" + cols[i].display + "</td>" + "<td style=\"text-align:right\">" + str_date + "</td>" + "</tr>";
                } else {
                    trs = trs + "<tr>" + "<td style=\"text-align:left;font-weight:bold\">" + cols[i].display + "</td>" + "<td style=\"text-align:right\">" + rowData[i] + "</td>" + "</tr>";
                }
            }

            tbobj.append("<tbody>" + trs + "</tbody>");
        }
    }

    //if we can't find the event in the remote data
    if (!isEventLocated) {
        var events = ACTIVITY_UTIL.getCalendarEventFromLocalStorage();
        if (events[id] != undefined) {
            var evt = events[id];
            var date = new Date(evt.startt*1000);
            var starttime = $.fullCalendar.formatDate(date, "yyyy-MM-dd HH:mm");
            date = new Date(evt.endt*1000);
            var endtime = $.fullCalendar.formatDate(date, "yyyy-MM-dd HH:mm");
            $('#detail_title').append("<span>拜访</span>");
            var trs = "<tbody>" + "<tr>" + "<td style=\"text-align:left;font-weight:bold\">开始时间:</td>" + "<td style=\"text-align:right\" >" + starttime + "</td></tr>" + "<tr>"
                    + "<td style=\"text-align:left;font-weight:bold\">结束时间:</td>" + "<td style=\"text-align:right\">" + endtime + "</td></tr>" + "<tr>"
                    + "<td style=\"text-align:left;font-weight:bold\">拜访类型:</td>" + "<td style=\"text-align:right\">" + ACTIVITY_UTIL.getEventTitle(evt.activity_type) + "</td></tr>" + "</tbody>";

            tbobj.append(trs);

        }
    }

    console.log("END of render activity detaile page now");
}