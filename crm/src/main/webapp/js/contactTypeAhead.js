       // Begin of contact typeahead 
        function setContact2LocalStorage(data) {
            localStorage["crmcontacts"] = JSON.stringify(data);
        }

        function getContactsFromLocalStorage(data) {
            var data = localStorage["crmcontacts"];
            if (data == null || data === 'undefined')
                data = '[]';
            return JSON.parse(data);
        }

        var selected_contact_id;
        function renderContactTypeaheadWithDataRemote() {
            var args = {};
            args.f = "getContactData";
            args.p = [ '20' ];
            ajaxPost2(args, function onComplete(resp) {
                isOnline = true;
                setContact2LocalStorage(resp);
                renderContactTypeahead(resp);
            }, function onError(status) {
                isOnline = false;
                renderContactTypeaheadWithDataLocal();
            });
        }

        function renderContactTypeaheadWithDataLocal() {
            var contacts = getContactsFromLocalStorage();
            if (jQuery.isEmptyObject(contacts) === false) {
                renderContactTypeahead(contacts);
            }
        }

        function renderContactTypeahead(contacts_data) {
            //set type ahead
            var contacts = [];
            var map = {};
            $('#contact_search').typeahead({
                source : function(query, process) {
                    $.each(contacts_data, function(i, contact) {
                        map[contact.name] = contact;
                        contacts.push(contact.name);
                    });
                    process(contacts);
                },
                updater : function(item) {
                    selected_contact_id = map[item].id;
                    console.log("selected id:" + selected_contact_id);
                    return item;
                },

                highlighter : function(item) {
                    return item + " - " + map[item].accountBelongTo;
                },
            });
        }

        if (navigator.onLine) {
            renderContactTypeaheadWithDataRemote();

        } else {
            renderContactTypeaheadWithDataLocal();
        }

        //END of contact typeahead 