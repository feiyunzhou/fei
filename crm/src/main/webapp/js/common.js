function ajaxPost(args, onComplete) {
	$.ajax({
		url : ajaxURL,
		type : 'post',
		cache : false,

		data : JSON.stringify(args),
		contentType : 'application/json; charset=utf-8',
		dataType : 'json',
		complete : function(xhr, status) {
			//console.log("completed: " + xhr.responseText);

		},
		success: function(json) {
			console.log("Success data returned:");
			onComplete(json);

        },
		error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log("error :"+XMLHttpRequest.responseText);
			console.log(errorThrown);
         }
    
	});

	
}

function prepareAutoComplete(){
	
	//auto complete for account name
	var accts = JSON.parse(allAccounts);
	for(var i=0;i<accts.length;i++){
		accts[i].label =  accts[i].name;
		accts[i].value = accts[i].id;
	}
	$( ".accountnameAutoComplete" ).bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).data( "ui-autocomplete" ).menu.active ) {
              event.preventDefault();
            }
          }).autocomplete({
	      minLength: 0,
	      source: accts,
	      focus: function( event, ui ) {
	    	  $( ".accountnameAutoComplete" ).val( ui.item.label );
	        return false;
	      },
	      select: function( event, ui ) {
	    	  $( ".accountnameAutoComplete" ).val( ui.item.label );
	    	  //onTreeNodeActivate("act",ui.item.id);
	    	  onAccountNameAutoComplete(ui.item);
	        return false;
	      },
	      onDblClick: function(node, event) {
	          node.toggleSelect();
	        }
	    })
	    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	    	return $( "<li>" )
	        .append( "<a>" + item.label + " - " + item.address + "</a>" )
	        .appendTo( ul );
	    };
	
	
	
	//auto complete
	var users = JSON.parse(allUsers);
	//console.log(users);
	for(var i=0;i<users.length;i++){
		users[i].label=users[i].name;
		users[i].value=users[i].id;
	}
	$( ".userNameAutoComplete" ).autocomplete({
	      minLength: 0,
	      source: users,
	      focus: function( event, ui ) {
	    	  $( ".userNameAutoComplete" ).val( ui.item.label );
	        return false;
	      },
	      select: function( event, ui ) {
	    	  $( ".userNameAutoComplete" ).val( ui.item.label );
	    	  console.log("selected");
	    	  onUserNameAutoCompleted(ui.item);

	        return false;
	      },
	      onDblClick: function(node, event) {
	          node.toggleSelect();
	        }
	    })
	    .data( "ui-autocomplete" )._renderItem = function( ul, item ) {
	    	return $( "<li>" )
	        .append( "<a>" + item.label + " - " + item.cellPhone + "</a>" )
	        .appendTo( ul );
	    };

	
	
}