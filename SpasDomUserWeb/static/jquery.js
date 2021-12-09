$(document).ready( function() {
        var expandable_elems = document.getElementsByClassName('expandable-elem');
        for (var i = 0; i < expandable_elems.length; i++) {
          expandable_elems[i].style.display = "none";
        }

        $('#houses_assigned').dblclick( function() {
            if ( $('#houses_assigned').val() ){
                houses_unassign_object();
            }
        });
        $('#houses_available').dblclick( function() {
            if( $('#houses_available').val() ) {
                var assigned_obj = document.getElementById("houses_available").value;
                $.ajax({
                    url: '/select_apart/' + assigned_obj,
                    type: 'POST',
                    data: $('#announcement-form').serialize(),
                    success: function(selectOptions){
                        $("#apartments_available").empty();
                        for (var i = 0; i < selectOptions.length; i++){
                            $("#apartments_available").append(
                                $("<option></option>")
                                .attr("value", selectOptions[i][0])
                                .text(selectOptions[i][1])
                            );
                        }
                    }
                });

                houses_assign_object();
                var pop = document.getElementById("pop");
                pop.style.visibility = "visible";
            }
        });

        $('#pop-close').click(function(){
            var pop = document.getElementById("pop");
            pop.style.visibility = "hidden";
        });

        $('#apartments_assigned').dblclick( apartments_unassign_object )
        $('#apartments_available').dblclick( apartments_assign_object )
    });

    function houses_assign_object() {
        return !$('#houses_available option:selected').remove().appendTo('#houses_assigned');
    };

    function houses_unassign_object() {
        return !$('#houses_assigned option:selected').remove().appendTo('#houses_available');
    };

    function apartments_assign_object() {
        return !$('#apartments_available option:selected').remove().appendTo('#apartments_assigned');
    };

    function apartments_unassign_object() {
        return !$('#apartments_assigned option:selected').remove().appendTo('#apartments_available');
    };

    function elem_expand(el_id) {
        var x = document.getElementById(el_id);
        if (x.style.display === "none") {
            x.style.display = "block";
        } else {
            x.style.display = "none";
        }
    };