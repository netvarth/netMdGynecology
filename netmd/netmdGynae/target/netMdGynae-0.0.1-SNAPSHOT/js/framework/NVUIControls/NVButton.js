function button(btn) {
	this.result = function() {
		var btnTag = $j('<input/>'); 
		btnTag.attr('value',btn.title);
		btnTag.attr('type',btn.type);
		if(btn.className)  btnTag.attr('class',btn.className);
		if(btn.name) {
			btnTag.attr('name',btn.name);
			btnTag.attr('id',btn.name);
		}	
		if(btn.className) btnTag.attr('class',btn.className);
		if(btn.style) btnTag.attr('style',btn.style);
		if(btn.tabIndex) btnTag.attr('tabIndex',btn.tabIndex);

		return btnTag;
	};
	
}