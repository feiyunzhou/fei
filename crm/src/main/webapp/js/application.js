// NOTICE!! DO NOT USE ANY OF THIS JAVASCRIPT
// IT'S ALL JUST JUNK FOR OUR DOCS!
// ++++++++++++++++++++++++++++++++++++++++++

!function ($) {

  $(function(){

    var $window = $(window)

    // Disable certain links in docs
    // tooltip demo
    $('.tooltip-demo').tooltip({
      selector: "a[data-toggle=tooltip]"
    })

    $('.tooltip-test').tooltip({
    	html:true
    })
    $('.popover-test').popover()

    // popover demo
    $(".tooltip-test")
      .popover({
    	  html:true,
    	  placement:'top'
      })
      .click(function(e) {
        e.preventDefault()
      })

  })


}(window.jQuery)
