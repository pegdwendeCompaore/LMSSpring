var springSpace = springSpace || {};

(function(){
	
	if (!window.console) {
		var noOp = function(){}; // no-op function
		console = {
			log: noOp,
			warn: noOp,
			error: noOp
		}
	}

	var chat_div, chat_load, chat_timer, chat_self_triggered, chat_button;
	var libchat_options = {"id":"3967","iid":"1100","hash":"f96e0a9926aed78ed5fae69e34dba64a","name":"Modx Libnet","ts":"2014-08-19T12:52:22.638Z","uid":195,"ref":"","key":"b0a7e4f03e53ebf","chat_title":"Ask Us - Chat<!p><small><!--<i class=\"fa fa-mobile fa-lg\"><\/i> (SMS):  <a href=\"sms:4055924128\">405-592-4128<\/a>--><br><a href=\"http:\/\/answers.library.okstate.edu\/\" target=\"_top\">Need more help?<\/a><\/small><\/p><hr\/>","byeMsg":"Thanks for chatting!<\/h2><p><em>Still need some help?<\/em><\/p><h2 >Contact Us<\/h2><p>    <i class=\"fa fa-envelope-o\"><\/i> <a href=\"http:\/\/libapp.okstate.edu\/machform\/view.php?id=11016\" onclick=\"window.open(this.href,  null, 'height=565, width=800, toolbar=0, location=0, status=0, scrollbars=1, resizable=1'); return false;\" target=\"_parent\"> \t Email us<\/a>    <\/p><p><i class=\"fa fa-phone\"><\/i><span> <span id=\"gc-number-0\" class=\"gc-cs-link\" title=\"Call with Google Voice\">405.744.9775<\/span><\/span><\/p><br\/><hr\/>","dept_label":"","name_label":"Name","name_default":"","guest_label":"Guest","width":"225","height":"390","is_personal":false,"chat_button":"Start Chat","done_button":"Chat again","press_enter":"Press ENTER to send","submit_button":"Submit","email_trans":"View\/Email Transcript","offline_text":"","offline_url":"","slidebutton_url":"","slidebutton_url_off":"","slidebutton_text":"Ask Us","slidebutton_text_off":"Offline","slidebutton_position":"r","slidebutton_bcolor":"#3278e0","slidebutton_color":"#ffffff","slidebutton_width":"34","slidebutton_height":"125","la_hide":true,"la_hide_msg":"<h2>Ask Us<\/h2><p>Sorry, chat is offline but you can still get help.<\/p><hr\/><p><a href=\"http:\/\/libnet.library.okstate.edu\/about\/contact-us\" class=\"btn btn-default btn-block\">Contact Us<\/a><\/p>","la_hide_msg2":"<h2 >Call Us<\/h2><p><i class=\"fa fa-phone\"><\/i> 405.744.9775<\/p>","la_search_opt":{"group_id":0,"button":"Search","placeholder":"","label":""},"la_search_box":"","sound_on":"Sound is On (click to toggle)","sound_off":"Sound is Off (click to toggle)","star_text":"Please rate this chat:","rate_1":"Bad","rate_2":"So-so","rate_3":"Good","rate_4":"Great","trans":"Enter an email address to send this chat transcript to:","error_sess":"Error starting session.","error_send":"Error sending this message.","error_tran":"Error sending transcript.","left":" has left the chat","typing":" is typing...","joined":" has joined the chat","initial_question":true,"initial_question_label":"Your Question","comments_label":"Any comments?","comments_button_text":"Submit Feedback","enable_anon":true,"enable_comments":true,"enable_sound":false,"star_ratings":false,"file_uploads":false,"file_title":"Upload File","file_intro":"Note: Maximum file size is 5MB. File is removed after one month, it is not kept permanently.","file_label":"Attach a file","file_action":"Upload","cancel_button":"Cancel","css":"","custom_css":".ls-button{background-color: #ff7700;}\nbody{\n   background-color: transparent !important;\n   font-family: Arial,Helvetica,sans-serif;\n}\n#ques {\nheight: 130px;\n}\n\n.s-la-widget .btn-block {\n    background-color: #ff6000;\n    border-color: #cc4c00;\n    color: #fff;\n    font-size: 1em;\n    font-weight: bold;\n    margin: 1em 0;\n}","color_backg":"","color_head":"#333333","color_btn":"#FFFFFF","color_border":"","user1":{"tag":1,"name":"Email","id":0,"show":1,"required":0,"type":"t","val":""},"user2":{"tag":2,"name":"click to edit","id":0,"show":0,"required":0,"type":"t","val":""},"user3":{"tag":3,"name":"click to edit","id":0,"show":0,"required":0,"type":"t","val":""},"user4":{"tag":4,"name":"click to edit","id":0,"show":0,"required":0,"type":"t","val":""},"user5":{"tag":5,"name":"click to edit","id":0,"show":0,"required":0,"type":"t","val":""},"error_off":"Sorry it doesn't appear any librarians are online... Please try again later.","wait":"Please wait... A librarian will connect shortly!","depart_id":"11","depart_dedicated":true,"depart_default_id":"11","widget_type":2,"autoload_time":0,"autoload_head":"Do you need help?","autoload_text":"A librarian is online ready to help.","autoload_yes":"Chat Now","autoload_no":"No Thanks","wtype":2,"isBuilding":true,"missedchat_time":30,"missedchat_message":"We apologize for the delay. Don't want to wait?","missedchat_link":"Submit your question.","missedchat_queue":0,"fbwidget":false,"autopop":false,"peel":"","skip_login":false,"nologin_message":"Type your question in the box below and press Enter to start chatting.","error_message":"Sorry, it looks like you're having a connection issue. Would you like to submit your question for email follow-up?","error_link":"Submit your question.","error_queue":0,"away_message":"Chat is online but the operator is temporarily away. If you don't want to wait, you can submit your question for email follow-up.","away_link":"Submit your question.","away_queue":0,"reload_button":"Recheck Status","base_domain":"v2.libanswers.com","onlinerules":[{"d":11,"u":0}]};
	var cascadeServer = "https:\/\/cascade2.libchat.com:443";
	
		
	//!check jquery version up to second decimal
	//is the current version >= minimum version
	function minVersion(minv, curr) {
		curr = curr || window.jQuery.fn.jquery;
		var c = curr.split('.');
		var m = minv.split('.');
		
		if (parseInt(c[0], 10) > parseInt(m[0], 10)) { return true; }
		else if (parseInt(c[0], 10) < parseInt(m[0], 10)) { return false; }
		else {
			if (typeof c[1] == 'undefined') { c[1] = 0; }
			if (typeof m[1] == 'undefined') { m[1] = 0; }
			if (parseInt(c[1], 10) > parseInt(m[1], 10)) { return true; }
			else if (parseInt(c[1], 10) < parseInt(m[1], 10)) { return false; }
			else { return true; }
		}
	}

	//get jquery either from namespace, window, or by loading it
	if (typeof springSpace.jq == "undefined") {
		if (window.jQuery === undefined) {
			loadJquery();
		} else {
			if (minVersion('1.7', window.jQuery.fn.jquery)) {
				springSpace.jq = window.jQuery;
				main();
			} else {
				loadJquery();
			}
		}
	} else {
		main();
	}		
	
	//!Load jQuery
	function loadJquery(){
		var script_tag = document.createElement('script');
		script_tag.setAttribute("type","text/javascript");
		script_tag.setAttribute("src", "//code.jquery.com/jquery-1.12.2.min.js");
		if (script_tag.readyState) { // for IE
			script_tag.onreadystatechange = function () {
				if (this.readyState == 'complete' || this.readyState == 'loaded') {
					scriptLoadHandler();
				}
			};
		} else {
			script_tag.onload = scriptLoadHandler;
		}
		(document.getElementsByTagName("head")[0] || document.documentElement).appendChild(script_tag);
	}		
		
	//!Called once jQuery has loaded
	function scriptLoadHandler() {
		springSpace.jq = window.jQuery.noConflict(true);
		main();
	}		

	//!Check online status
	function checkStatus() {
					showChat(false);
			}
			
	function main() {
	
		springSpace.jq(document).ready(function(){
		
				
		
		//only load a stylesheet if there was a custom one set
		if (libchat_options.css !== '') {
			if(document.createStyleSheet) {
				try { document.createStyleSheet(libchat_options.css); } catch (e) { }
			}
			else {
				var css_tag = document.createElement("link");
				css_tag.setAttribute("rel", "stylesheet");
				css_tag.setAttribute("type", "text/css");
				css_tag.setAttribute("href", libchat_options.css);
				(document.getElementsByTagName("head")[0] || document.documentElement).appendChild(css_tag);
			}
		}			
		if (!libchat_options.color_border || libchat_options.color_border == '') { libchat_options.color_border = 'transparent'; }
		
					
			//default to libchat_hash, but fallback for early v2 widgets
			chat_div = springSpace.jq('#libchat_'+libchat_options.hash);
			if (chat_div.length == 0) {
				libchat_options.containerID = 'libchat_inline_widget';				chat_div = springSpace.jq('#'+libchat_options.containerID);
			} else {
				libchat_options.containerID = 'libchat_'+libchat_options.hash;
			}
		
			//Get Status for non-slide-outs
			// @todo I don't think we need to do that for embed widgets. They are just checking the status AGAIN in chati.php
			checkStatus();
				
					
			}); //end docready
	}//end main

	function showChat(online){
		var qs = window.location.protocol+'//'+libchat_options.base_domain+'/chati.php?';
		qs += "iid=" + libchat_options.iid + 
			 "&hash=" + libchat_options.hash;

		if (typeof libchat_options['template'] !== 'undefined') {
			qs += "&template="+encodeURIComponent(libchat_options['template']);
		}
		
		if (typeof libchat_options['template_css'] !== 'undefined') {
			qs += "&template_css="+encodeURIComponent(libchat_options['template_css']);
		}		
		qs += "&online="+online;
		
		try {
			if ( typeof libchat_options.width === 'string' && libchat_options.width.indexOf("%") == -1 )
				libchat_options.width = parseInt(libchat_options.width,10);
		} catch(e){}

		try {
			if ( typeof libchat_options.height === 'string' && libchat_options.height.indexOf("%") == -1 )
				libchat_options.height = parseInt(libchat_options.height,10);
		} catch(e){}
		
		if (window.document.title) {
			qs += '&referer_title='+encodeURIComponent(window.document.title);
		}
		
						//embedded
				chat_load = springSpace.jq('<div class="lci_chat_load"></div>').css({'width': libchat_options.width, 'height': libchat_options.height, 'background-color': libchat_options.color_backg, 'border': '1px solid '+libchat_options.color_border, 'box-sizing': 'content-box' });
				chat_div.html(chat_load);
				
				var $iframe = springSpace.jq('<iframe></iframe>').attr({ 'id': 'iframe_'+libchat_options.hash, 'name': 'iframe_'+libchat_options.hash, 'title': 'Chat widget', 'src': qs, 'frameborder': 0, 'scrolling': 'no' }).css({ 'border': '0px', boxSizing: 'border-box', 'width': '100%', 'height': libchat_options.height, 'background-color': libchat_options.color_backg });
		
				chat_load.html($iframe);
				
						
		
	}//end showchat

})(); //end anonymous function
