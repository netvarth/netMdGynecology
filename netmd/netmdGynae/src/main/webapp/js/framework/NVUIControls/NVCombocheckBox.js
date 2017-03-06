
function combocheckbox(button) {
	this.result = function () {

		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		$j(button.value).each(function(index,item) {
		var checkboxTag=$j('<input/>');
		if (item.type) checkboxTag.attr('type',item.type);
		checkboxTag.attr('style','right:3%');
	    if (item.name) checkboxTag.attr('name',item.name);
		if(item.id) checkboxTag.attr('id',item.id);
		if(item.style) checkboxTag.attr('style',item.style);
		if(item.className) checkboxTag.attr('class',item.className);
		if(item.checked) checkboxTag.attr('checked',item.checked);
		pTag.append(checkboxTag);
		});
		var spanTag = $j('<span/>'); // creating a span tag inside the ptag
		spanTag.html(button.title);
		if(button.spanId)spanTag.attr('id',button.spanId);		//Add data inside the span Tag
		var brTag = $j('<br/>'); // Create a br for new Line
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.required) {
			spanTag = $j('<span class="req_astrisk"> *</span>');
			pTag.append(spanTag);
		}
		pTag.append(brTag); // Add the br line tag inside the P tag
		
		var inputTag = $j('<input/>');
		inputTag.attr('type','text');
		if (button.name) inputTag.attr('name',button.name);
		if(button.id) inputTag.attr('id',button.id);
		if(button.className) inputTag.attr('class',button.className);
		if(button.style) inputTag.attr('style',button.style);
		if(button.readonly) inputTag.attr('readonly',button.readonly);
		pTag.append(inputTag);
		return pTag;
	}
	
}