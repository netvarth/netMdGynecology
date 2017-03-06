
function radio(button) {
	this.result = function () {
		var pTag = jQuery('<p/>'); // Create a  ptag [outer-1]
		if(!button.style) pTag.attr('style','margin:0 0 5px 0;');
		var spanTag = jQuery('<span/>'); // creating a span tag inside the ptag
		spanTag.html(button.title); //Add data inside the span Tag
		var brTag = jQuery('<br/>'); // Create a br for new Line
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.required) {
			spanTag = jQuery('<span class="req_astrisk"> *</span>');
			pTag.append(spanTag);
		}
		pTag.append(brTag); // Add the br line tag inside the P tag
		
		jQuery(button.value).each(function(index,item) {

			var inputTag = jQuery('<input/>');
			inputTag.attr('type',button.type);
			if(button.name) inputTag.attr('name',button.name);
			if(button.id) inputTag.attr('id',button.name + index);
			if(item.checked) inputTag.attr('checked',item.checked);
			if(item.value)inputTag.attr('value',item.value);
			if(item.id)inputTag.attr('id',item.id);
			if(item.tabIndex) inputTag.attr('tabIndex',item.tabIndex);
			if(button.className) inputTag.attr('class',button.className);
			pTag.append(inputTag);
			
			var labelTag=jQuery('<label/>');
			labelTag.attr('for',button.name + index);
			labelTag.attr('style','margin:0 8px 0 3px;');
			
			labelTag.html(item.title);
			pTag.append(labelTag);
		});
		return pTag;
	};
}