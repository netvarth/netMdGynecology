
function newlabel(button) {
	this.result = function () {

		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		var spanTag = $j('<span/>'); // creating a span tag inside the ptag
		var title = button.title;
		if(button.titleimage)
			title += '( <img src="' + button.titleimage + '"/>)'; 
		spanTag.html(title); //Add data inside the span Tag
		if(button.labelId)spanTag.attr('id',button.labelId);
		var labelTag = $j('<label/>');
		labelTag.attr('name','lblValue');		
		pTag.append(spanTag); //Add the span tag inside the P tag
		if(button.title){
			var brTag = $j('<br/>'); // Create a br for new Line
			pTag.append(brTag); // Add the br line tag inside the P tag
		}
		if(button.spanClass)spanTag.attr('class',button.spanClass);
		if (button.name) pTag.attr('name',button.name);
		if(button.image)spanTag.append('<img src ="' + button.image + '" /> ');
		if(button.id) pTag.attr('id',button.id);
		if(button.className) pTag.attr('class',button.className);
		if(button.style) pTag.attr('style',button.style);
		if(button.value) 
			labelTag.html(button.value);
		pTag.append(labelTag);
		
		return pTag;
	}
	
}