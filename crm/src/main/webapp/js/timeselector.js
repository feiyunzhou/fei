(function($) {
  //
  // plugin definition
  //
  $.fn.timeselector = function(options) {
    debug(this);
    // build main options before element iteration
    var opts = $.extend({}, $.fn.timeselector.defaults, options);
    // iterate and reformat each matched element
    return this.each(function() {
      $this = $(this);
      // build element specific options;
      var optionHtml = "";
      for(var i=0;i<24;i++){
          optionHtml += "<option value=\""+i+":00\">" + i + ":00</option>";
      }
      
      $this.append(optionHtml);
      
    });
  };
  //
  // private function for debugging
  //
  function debug($obj) {
    console.log('timeselector selection count: ' + $obj.size());
  };
  //
  // define and expose our format function
  //
  $.fn.timeselector.format = function(txt) {
    return '<strong>' + txt + '</strong>';
  };
  //
  // plugin defaults
  //
  $.fn.timeselector.defaults = {
  };
//
// end of closure
//
})(jQuery);