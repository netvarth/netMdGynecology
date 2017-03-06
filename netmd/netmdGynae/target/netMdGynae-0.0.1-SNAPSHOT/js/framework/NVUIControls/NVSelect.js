
function select(button) {
	this.result = function() {
		var resTag=$j('<div/>');
		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		if(button.title) {
			var spanTag = $j('<span/>'); // creating a span tag inside the ptag
			spanTag.html(button.title); //Add data inside the span Tag
			var brTag = $j('<br/>'); // Create a br for new Line			
			pTag.append(spanTag); //Add the span tag inside the P tag
			if(button.required) {
				spanTag = $j('<span class="req_astrisk"> *</span>');
				pTag.append(spanTag);
			}
			pTag.append(brTag); // Add the br line tag inside the P tag
		}
		var  selectTag= $j('<select/>');
		if(button.name) selectTag.attr('name',button.name);
		if(button.id) selectTag.attr('id',button.id);
		if(button.className) selectTag.attr('class',button.className);
		if(button.style) selectTag.attr('style',button.style);	
		if(button.tabIndex) selectTag.attr('tabIndex',button.tabIndex);
		
		$j(button.value).each(function(index,item) {
			var optionTag = $j('<option />');
			optionTag.html(item);
			selectTag.append(optionTag);
		});
		pTag.append(selectTag);
		return pTag;
	};
}