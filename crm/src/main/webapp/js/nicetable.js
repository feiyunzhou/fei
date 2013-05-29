(function($) {
  //
  // plugin definition
  //
  $.fn.nicetable = function(options) {
    debug(this);
    // build main options before element iteration
    var opts = $.extend({}, $.fn.nicetable.defaults, options);
    // iterate and reformat each matched element
    return this.each(function() {
      $this = $(this);
      // build element specific options;
      var tableString = "";
      var table = opts.tData;
      if(!jQuery.isEmptyObject(table.cols) && table.cols !=null && table.cols != 'undefined'){
          var tableColumn = "<thead><tr>";
          var columnWidth = table.cols.length;
          var column = "";
          var cols = table.cols;
          var num =0;
          console.log(cols);
          for(var i=0;i<columnWidth;i++){
              if(cols[i].isVisible === false) continue;
              if(cols[i].priority != 1) continue;
              if(num==0){
                  column +="<th data-class=\"expand\" >"+cols[i].display+"</th>";
              }else{
                  column +="<th data-hide=\"phone\">"+cols[i].display+"</th>";
            
              
              }
              num++;
          }
         if (opts.extraCols.length > 0) {
                    var len = opts.extraCols.length;
                    var ec = opts.extraCols;
                    for ( var k = 0; k < len; k++) {
                        column += "<th data-hide=\"phone\">" + ec[k].display + "</th>";
                    }
          }
          tableColumn = tableColumn + column+ "</tr></thead>";
          tableString += tableColumn;
          

           if (table.tData != undefined) {
                    var tableData = table.tData;
                    var tr = "";
                    $.each(tableData, function(key, value) {
                        if (value.length > 0) {
                            var rowdata = value[0];
                            var tds = "";
                            for ( var j = 0; j < columnWidth; j++) {
                                
                               if(cols[j].isVisible === false) continue;
                               if(cols[j].priority != 1) continue;
                                tds = tds + "<td>" + rowdata[j] + "</td>";
                            }
                            if (opts.extraCols.length > 0) {
                                var len = opts.extraCols.length;
                                var ec = opts.extraCols;
                                for ( var k = 0; k < len; k++) {

                                    tds = tds + "<td>" + ec[k].renderCol(rowdata) + "</td>";
                                }
                            }

                            tr = tr + "<tr>" + tds + "</tr>";
                        }
                    });

                    var tableRows = "<tbody>" + tr + "</tbody>";

                    tableString = tableString + tableRows;

          }
          
          $this.append(tableString);
      }
      
    });
  };
  //
  // private function for debugging
  //
  function debug($obj) {
    console.log('nicetable selection count: ' + $obj.size());
  };
  //
  // define and expose our format function
  //
  $.fn.nicetable.format = function(txt) {
    return '<strong>' + txt + '</strong>';
  };
  //
  // plugin defaults
  //
  $.fn.nicetable.defaults = {
          tData:{},
          extraCols:[]
  };
//
// end of closure
//
})(jQuery);