/*
	FAQ search form for use in various places
	Added to: public js
	Used in: chat offline js
	Requires: jquery, bootstrap typeahead
*/

/**
 * {Object} config
 *		{Number} content_id searchbox id
 *		{String} iid system id
 *		{Number} group_id group to search
 *		{String} solrUrl server to search
 *		{Object} errormsg translated/customized error messages
 */
 
var springSpace = springSpace || {};
springSpace.la = springSpace.la || {};

springSpace.la.searchform = function(config) {
	
	this.id = config.content_id;
	this.iid = config.iid;
	this.solrUrl = '//'+config.solrUrl+'/la2/';
	this.group_id = config.group_id;
	this.errormsg = config.errormsg; //noquestion and repeatq
	this.divselector = '#s-la-content-search-'+this.id;
	this.$form = jQuery(this.divselector+' form');
	this.$input = jQuery('#s-la-content-search-query-'+this.id);
	this.rows = (config.rows) ? config.rows : 10;
	this.searchwt = { question: '6', details: '3', topics: '4', keywords: '5', allInOne: '1' };
	
	var that = this; //for inside event handlers
	
	/**
	 * Take the typed query and fancy it up for Solr
	 * @param {String} query the typed search
	 */
	this.convertSearch = function(typed) {	
		if (typed == '') { return '*:*'; }
		
		typed = typed.replace(/([\(\)\[\]\{\}\^~:\\\/])/ig, '\\$1');
		
		var qq = '';
		for (var k in that.searchwt) {
			if (that.searchwt.hasOwnProperty(k)) {
				qq += '('+k+':('+typed+'*))^'+that.searchwt[k]+' ';
			}
		}
		return qq;		
	};
	
	var typeahead_source = function(query, cb) {
		var results = [];
		query = jQuery.trim(query);
		query = that.convertSearch(query);
		jQuery.get(
			that.solrUrl + 'select?rows='+that.rows+'&json.nl=map&fq='+encodeURIComponent('s:'+that.iid)+'&fq='+encodeURIComponent('g:'+that.group_id)+'&q='+query+'&sort=score desc&wt=json&json.wrf=?',
			function (d) {
				results = jQuery.map(d.response.docs, function(item) {
					return { value: item.question, faqid: item.id, gid: item.g };
				});
				
				cb(results);
			},
			'jsonp'
		);
		
	}
	
	//enable autocomplete
	this.$input.typeahead({ minLength: 3 }, 
		{
			source: typeahead_source,
			displayKey: 'value'
		}
	).on('typeahead:selected', function(e, d){
		//when a suggestion is selected
		//add faqid input to the form		
		var faqi = that.$form.find('input[name=faqid]');
		if (faqi.length == 0) {
			faqi = jQuery('<input type="hidden" name="faqid" />');
			that.$form.prepend(faqi);
		}
		faqi.val(d.faqid);
				
		that.$form.trigger('submit');
		return;
	});
	
	//submit the form
	this.$form.submit(function(e){
		e.preventDefault();
		//some validation		
		var query_text = that.$input.val();
		if (query_text == "") { errorAlert(that.errormsg.noquestion); return false; }
		if (query_text == that.$input.attr('placeholder')) { errorAlert(that.errormsg.noquestion); return false; }
		
		this.submit();

	});
	//end submit event action
	
	
	//remove placeholder on focus
	this.$input.focus(function() {
		f = jQuery(this);
		f.addClass('searchselected');
		if( f.val() == f.attr('placeholder') ){ f.val(''); }
	});
	
	
} //end searchform
