jQuery('document').ready(function($){

    var menuBtn = $('.menu-icon'),
    menu = $('.navigation ul');

    menuBtn.click(function() {
        if(menu.hasClass('show')) {
            menu.removeClass('show');
        } else {
            menu.addClass('show');
        }

    });
});

jQuery('document').ready(function($) {
	var editBtn = $('#edit1');
	editBtn.click(function() {
		if(document.getElementById("inputDireccion").readOnly = false){
		 	document.getElementById("inputDireccion").readOnly = true;
		 }else{
			document.getElementById("inputDireccion").readOnly = false;
		}
	});
});



