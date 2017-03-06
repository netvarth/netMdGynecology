
function text(button) {
	this.result = function () {

		var pTag = jQuery('<div/>'); // Create a  ptag [outer-1]
		pTag.attr('class','control-group');
		var spanTag = jQuery('<span/>'); // creating a span tag inside the ptag
		spanTag.attr('class','control-label');
		spanTag.attr('for','basicround');
		spanTag.html(button.title); //Add data inside the span Tag
		var brTag = jQuery('<div/>'); // Create a br for new Line
		if(button.className) brTag.attr('class',button.className);
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.required) {
			spanTag = jQuery('<span class="req_astrisk"> *</span>');
			pTag.append(spanTag);
		}
		//pTag.append(brTag);
		 // Add the br line tag inside the P tag
		
		var inputTag = jQuery('<input/>');
		inputTag.attr('type',button.type);
		if (button.name) inputTag.attr('name',button.name);
		if(button.id) inputTag.attr('id',button.id);
		//if(button.className) inputTag.attr('class',button.className);
		if(button.style) inputTag.attr('style',button.style);
		if(button.readonly) inputTag.attr('readonly',button.readonly);
		if(button.tabIndex) inputTag.attr('tabIndex',button.tabIndex);
		brTag.append(inputTag);
		pTag.append(brTag);
		return pTag;
	}
	
}