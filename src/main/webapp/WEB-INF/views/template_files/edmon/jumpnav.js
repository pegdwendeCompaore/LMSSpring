jQuery.noConflict();
(function($){
$(document).ready(function(){

	// If there is a '#' in the URL (someone linking directly to a page with an anchor), highlight that section and set focus to it.
	// The tabindex attribute is removed AFTER the user navigates away from the element to help address a nasty VoiceOver bug.
	
	if (document.location.hash) {
		var myAnchor = document.location.hash;
		$(myAnchor).attr('tabindex', -1).on('blur focusout', function () {
			$(this).removeAttr('tabindex');
		}).focus();
	}
	
	
	/* This function looks for a change in the hash (activation of an in-page link) and sets focus to and 
	highlights the target element. This is necessary because webkit does not set focus as it should. If 
	the hash is empty (the user hit the back button after activating an in-page link) focus is set to body.
	*/
	$(window).bind('hashchange', function() {
		var hash = "#"+window.location.hash.replace(/^#/,'');
		if (hash!="#") {
			$(hash).attr('tabindex', -1).on('blur focusout', function () {
				$(this).removeAttr('tabindex');
			}).focus().yellowFade();
		}
		else {
			$("#headcontainer").attr('tabindex', -1).on('blur focusout', function () {
				$(this).removeAttr('tabindex');
			}).focus();
		}
	});	
	
	
});
})(jQuery);