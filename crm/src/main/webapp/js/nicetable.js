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
      var tData = opts.tData;
      if(tData.aoColumns != 'undefined'){
          var tableColumn = "<thead><tr>";
          var columnWidth = tData.aoColumns.length;
          var column = "";
          for(var i=0;i<columnWidth;i++){
              if(i==0){
                  column +="<th data-class=\"expand\" >"+tData.aoColumns[i].sTitle+"</th>";
              }else{
                  column +="<th data-hide=\"phone\">"+tData.aoColumns[i].sTitle+"</th>";
              }
          }
          tableColumn = tableColumn + column+ "</tr></thead>";
          tableString += tableColumn;
          
          if(tData.aaData != 'undefined'){
             
              var rowLength = tData.aaData.length;
              var tr = "";
              for(var i=0;i<rowLength;i++){
                  
                  var tds="";
                  for(var j=0;j<columnWidth;j++){
                      tds = tds+ "<td>" +tData.aaData[i][j]+ "</td>";
                  }
                  tr = tr + "<tr>"+tds+"</tr>";
              }
              
              var tableRows = "<tbody>"+tr+"</tbody>";
              
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
          tData:{}
  };
//
// end of closure
//
})(jQuery);