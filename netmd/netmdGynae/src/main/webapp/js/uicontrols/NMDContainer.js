function Container(contentgroup) {

	this.result =	function(){ 
		var content = jQuery('<div>');
		if(contentgroup.className) content.attr('class',contentgroup.className);
		if(contentgroup.id) content.attr('id',contentgroup.id);
		if(contentgroup.style) content.attr('style',contentgroup.style);
		jQuery(contentgroup.elements).each(function(index, buttonelement) {
			// The part which return the radio button
			if(buttonelement.type == "radio") 
				content.append((new radio(buttonelement).result));	
			if(buttonelement.type=="table")
				content.append((new table(buttonelement.content).result));
			if(buttonelement.type=="text") 					
				content.append((new text(buttonelement).result));
            if(buttonelement.type=="textarea") 					
				content.append((new textarea(buttonelement).result));				
			if(buttonelement.type=="label") 
				content.append((new label(buttonelement).result));
			// The part which return the radio button
			if(buttonelement.type=="button") {
				jQuery(buttonelement.content.buttons).each(function(index,btn) {											
					content.append((new button(btn).result));
				});
			}
			if(buttonelement.data){
				jQuery(buttonelement.data).each(function (index, group) {										
					var contain = new Container(group);						
					content.append(contain.result);	
				});
			}																			
		});
		jQuery(contentgroup.data).each(function (index, group) {	
			var contain = new Container(group);
			content.append(contain.result);										
		});
		return content;
	};
}

