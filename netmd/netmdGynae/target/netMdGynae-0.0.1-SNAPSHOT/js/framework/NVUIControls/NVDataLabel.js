function datalabel(button) {
	this.result = function () {
		//var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		//if(button.className)  pTag.attr('class',button.className);
		var spanTag = $j('<span/>'); // creating a span tag inside the ptag
		//pTag.html(button.title); //Add data inside the span Tag
		if(button.name) spanTag.attr('name',button.name);
		if(button.id) spanTag.attr('id',button.id);
		if(button.className) spanTag.attr('class',button.className);
		if(button.style) spanTag.attr('style',button.style);
		//spanTag.html(); //Add data inside the span Tag
		//pTag.append(spanTag); //Add the span tag inside the P tag	
		//return pTag;	
		return spanTag;
	};
}