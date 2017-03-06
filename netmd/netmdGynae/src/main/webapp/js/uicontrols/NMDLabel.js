function label(button) {
	this.result = function () {
		var pTag = jQuery('<p/>');	
		var spanTag;
		if(button.title) {
			spanTag = jQuery('<span />');
			spanTag.html(button.title);
			}
			if(button.spanstyle)
			spanTag.attr('style',button.spanstyle);
			pTag.append(spanTag);
	
		
		spanTag = jQuery('<span />');
		if(button.name) spanTag.attr('name',button.name);
		if(button.id) spanTag.attr('id',button.id);
		if(button.className) spanTag.attr('class',button.className);
		if(button.style) spanTag.attr('style',button.style);
		pTag.append(spanTag);
		return pTag;
	};
}