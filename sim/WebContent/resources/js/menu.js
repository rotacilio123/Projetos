jQuery(document).ready(function() {
	jQuery('.submenu-container').hide();
	jQuery('.submenu-container').parent('li').find('a').click(function(event) {
		jQuery(this).parent('li').find('ul.submenu-container').slideToggle();
	});
});