
function text(button) {
	this.result = function () {
		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		var spanTag = $j('<span/>'); // creating a span tag inside the ptag		
		var title = button.title;
		if(button.image)
			title += ' (<img src="' + button.image + '"/>)'; 
		spanTag.html(title); //Add data inside the span Tag
		var brTag = $j('<br/>'); // Create a br for new Line
		if(button.idforInner) {
			var innerSpanTag = $j('<span/>'); // creating a span tag inside the ptag
			innerSpanTag.attr('id',button.idforInner);
			spanTag.append(innerSpanTag);
		}	
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.required) {
			spanTag = $j('<span class="req_astrisk">*</span>');
			if(button.spanId)spanTag.attr('id',button.spanId);
			pTag.append(spanTag);
		}
		pTag.append(brTag); // Add the br line tag inside the P tag
		
		var inputTag = $j('<input/>');
		inputTag.attr('type',button.type);
		if (button.name) inputTag.attr('name',button.name);
		if(button.id) inputTag.attr('id',button.id);
		if(button.className) inputTag.attr('class',button.className);
		if(button.style) inputTag.attr('style',button.style);
		if(button.readonly) inputTag.attr('readonly',button.readonly);
		if(button.placeHolder)inputTag.attr('placeholder',button.placeHolder);
		if(button.tabIndex) inputTag.attr('tabIndex',button.tabIndex);
		pTag.append(inputTag);
		return pTag;
	}
	
}