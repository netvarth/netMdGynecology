
function combinedControl(buttonGroup) {
	this.result = function() {

		var pTag = $j('<p/>'); // Create a  ptag [outer-1]
		if(buttonGroup.title) {
			var spanTag = $j('<span/>'); // creating a span tag inside the ptag
			spanTag.html(buttonGroup.title); //Add data inside the span Tag
			var brTag = $j('<br/>'); // Create a br for new Line
			if(buttonGroup.idforInner) {
				var innerSpanTag = $j('<span/>'); // creating a span tag inside the ptag
				innerSpanTag.attr('id',buttonGroup.idforInner);
				spanTag.append(innerSpanTag);
			}
			pTag.append(spanTag); //Add the span tag inside the P tag
			pTag.append(brTag); // Add the br line tag inside the P tag
		}
		
		/*var inputTag = $j('<input/>');
		inputTag.attr('type',button.type);
		inputTag.attr('name',button.name);
		inputTag.attr('id',button.name);
		pTag.append(inputTag);*/
		
		$j(buttonGroup.items).each(function(index,button) {
			if(button.type == "radio") {// The part which return the radio button
				$j(buttonGroup.value).each(function(index,item) {
					var inputTag = $j('<input/>');
					inputTag.attr('type',item.type);
					if(item.name) inputTag.attr('name',item.name);
					if(item.id) inputTag.attr('id',item.name + index);
					if(item.checked) inputTag.attr('checked',item.checked);
					if(item.className) inputTag.attr('class',item.className);
					if(item.style) inputTag.attr('style',item.style);
					if(item.tabIndex) inputTag.attr('tabIndex',item.tabIndex);
					pTag.append(inputTag);
				});
			}
			if(button.type=="text") {
				var inputTag = $j('<input/>');
				inputTag.attr('type',button.type);
				inputTag.attr('name',button.name);
				inputTag.attr('id',button.name);
				if(button.className) inputTag.attr('class',button.className);
				if(button.style) inputTag.attr('style',button.style);
				if(button.placeHolder) inputTag.attr('placeHolder',button.placeHolder);
				if(button.tabIndex) inputTag.attr('tabIndex',button.tabIndex);
				pTag.append(inputTag);
							
			}	
			if(button.type=="datalabel") {
				var labelTag = $j('<label/>');
				if(button.name) labelTag.attr('name',button.name);
				if(button.id) labelTag.attr('id',button.name);
				if(button.className) labelTag.attr('class',button.className);
				if(button.style) labelTag.attr('style',button.style);
				pTag.append(labelTag);			
			}
			if(button.type=="newlabel") {
				var labelTag = $j('<label/>');
				if(button.name) labelTag.attr('name',button.name);
				if(button.id) labelTag.attr('id',button.name);
				if(button.className) labelTag.attr('class',button.className);
				if(button.style) labelTag.attr('style',button.style);
				if(button.title) labelTag.html(button.title);
				pTag.append(labelTag);			
			}
			if(button.type=="textarea") {
				var textareaTag = $j('<textarea/>');
				if(button.name) textareaTag.attr('name',button.name);
				if(button.id) textareaTag.attr('id',button.id);
				if(button.className) textareaTag.attr('class',button.className);
				if(button.tabIndex) textareaTag.attr('tabIndex',button.tabIndex);
				pTag.append(textareaTag);			
			}
			if(button.type=="button") {
				$j(button.content.buttons).each(function(index,btn) {
					var btnTag = $j('<input/>'); 					
					btnTag.attr('value',btn.title);
					btnTag.attr('type',btn.type);
					if(btn.className)  btnTag.attr('class',btn.className);
					if(btn.name) btnTag.attr('name',btn.name);
					if(btn.id) btnTag.attr('id',btn.name);
					if(btn.className) btnTag.attr('class',btn.className);
					if(btn.style) btnTag.attr('style',btn.style);	
					if(btn.tabIndex) btnTag.attr('tabIndex',btn.tabIndex);
					pTag.append(btnTag);
				});
			}
			if(button.type=="select") {
				var  selectTag= $j('<select/>');
				if(button.name) selectTag.attr('name',button.name);
				if(button.id) selectTag.attr('id',button.id);
				if(button.className) selectTag.attr('class',button.className);
				if(button.style) selectTag.attr('style',button.style);
				if(button.tabIndex) selectTag.attr('tabIndex',button.tabIndex);
				if(button.value){
					$j(button.value).each(function(index,item) {
						var optTag = $j("<option/>");
						if(item.id)
							optTag.attr("value",item.id);
						else
                           	optTag.attr("value",item.title);					
						optTag.html(item.title);
						selectTag.append(optTag);
					});		
			  }
			 pTag.append(selectTag);				 
				
			}
			
		});
		return pTag;
	};
}