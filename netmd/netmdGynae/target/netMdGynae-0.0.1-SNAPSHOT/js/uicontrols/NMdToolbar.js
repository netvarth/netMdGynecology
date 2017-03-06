function leftpaneToolBarSub(sublist) {
this.result = function() {
		var toolBars=jQuery('<ul/>');
		toolBars.attr('class','collapsed-nav closed');
		if(sublist!=null)
		{
			jQuery(sublist).each(function(index,sub) {
			var liTag = jQuery('<li/>');
			var aTag = jQuery('<a/>');
			aTag.attr('href','#');
			if(sub.name) aTag.attr('id',sub.name);
			if(sub.title){
				var spanTag = jQuery('<span/>');
				spanTag.html(sub.title);
				aTag.append(spanTag); 
			}
			liTag.append(aTag);
			toolBars.append(liTag);
			});
		}
		return toolBars;

}
}