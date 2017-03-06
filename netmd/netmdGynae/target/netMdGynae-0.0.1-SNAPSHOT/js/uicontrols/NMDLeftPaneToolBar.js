function leftpaneToolBar(buttons) {
	this.result = function() {
		var toolBar=jQuery('<ul/>');
		toolBar.attr('class','main-nav');
		if(buttons!=null)
		{
			//Create admin section
			jQuery(buttons).each(function(index,button) {	
				var liTag = jQuery('<li/>');
				var aTag = jQuery('<a/>');
				aTag.attr('href','#');
			    aTag.attr('class','light toggle-collapsed');
				var divTag=jQuery('<div/>');
				divTag.attr('class','ico');
				var liTag1 = jQuery('<i/>');
				liTag1.attr('class','icon-th-large icon-white');				
				if(button.name) aTag.attr('id',button.name);
				if(button.title){
					var spanTag = jQuery('<span/>');
					spanTag.html(button.title);
					aTag.append(spanTag); 
				}
				divTag.append(liTag1);
				if(button.sublist) {
					var imgTag=jQuery('<img/>')
					imgTag.attr('src','/netmd/images/toggle-subnav-down.png');
					aTag.append(imgTag); 
				}	
				
				aTag.append(divTag);
				liTag.append(aTag);
				if(button.sublist) {
					var sublist;
					var	sublist =new leftpaneToolBarSub(button.sublist);

					liTag.append(sublist.result);
				}
				toolBar.append(liTag);
			});
		}
		return toolBar;
	}
	
}