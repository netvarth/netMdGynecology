function checkbox(button) {
	this.result = function () {
		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		//if(!button.style) pTag.attr('style','margin:5px 0 0 0;');
		if(button.title) {
			var spanTag = $j('<span/>'); // creating a span tag inside the ptag
			spanTag.html(button.title); //Add data inside the span Tag
			pTag.append(spanTag);
			pTag.append($j('<br/>'));
		}
		$j(button.value).each(function(index,item) {
			var labelTag=$j('<label/>');
			labelTag.attr('for',item.title);
			labelTag.attr('style','margin:0 10px 0 3px;');
			labelTag.html(item.title);
			
			var inputTag = $j('<input/>');
			inputTag.attr('type',button.type);	
			if(item.checked) inputTag.attr('checked',item.checked);
			if(item.value)inputTag.attr('value',item.value);
			if(item.className)inputTag.attr('class',item.className);
			if(item.id) inputTag.attr('id',item.id);
			if(item.tabIndex) inputTag.attr('tabIndex',item.tabIndex);
			pTag.append(inputTag);
		
			pTag.append(labelTag);
		});
		return pTag;
	};
}