
function file(button) {
	this.result = function () {

		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		var spanTag = $j('<span/>'); // creating a span tag inside the ptag
		spanTag.html(button.title); //Add data inside the span Tag
		var brTag = $j('<br/>'); // Create a br for new Line
		pTag.append(spanTag); //Add the span tag inside the P tag
		pTag.append(brTag); // Add the br line tag inside the P tag
		
		var inputTag = $j('<input/>');
		inputTag.attr('type',button.type);
		inputTag.attr('name',button.name);
		inputTag.attr('id',button.name);
		if(button.className) inputTag.attr('class',button.className);
		if(button.style) inputTag.attr('style',button.style);
		pTag.append(inputTag);
		return pTag;
	}
}