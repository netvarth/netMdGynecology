function FilterToolBar (jsondata){
	var self=this;
	var toolBar=$j('<div></div>');
	if(jsondata!=null)
	{	
		/* Creation of ToolBar */
		$j(jsondata.buttons).each(function(index,button) {
			var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft ' + button.className + '" name="'+ button.name +'" id="btn_'+button.name+'_filter_id"><span>'+button.title+'</span></a>');
			toolBar.append(toolbutton);
			if(button.type){
				toolBar.append(self.setFilterOperators(button));
				}
			var inputTag=$j('<input type="text">');
			inputTag.attr('id','txt'+button.name);
			inputTag.addClass('genTextHeight');
			inputTag.attr('style','display:none');
			toolBar.append(inputTag);
		});
	}
	var inputTag=$j('<input type="button" value=" Go " class="genMarginLeft genTextHeight stdbtn btn_black">');
	inputTag.attr('id','btnGo');
	toolBar.append(inputTag);
	this.result = toolBar.html();
}

FilterToolBar.prototype.setFilterOperators=function(button) {
	var type=button.type;
	var selectTag=$j('<select/>');
	selectTag.attr('id','lst'+ button.name);
	selectTag.addClass('genTextHeight');
	selectTag.addClass('genTextwidth');
	selectTag.attr('style','display:none');
	var option = $j('<option value="eq">eq</option>');
	selectTag.append(option);
	var option = $j('<option value="neq">neq</option>');
	selectTag.append(option);
	if(type=='number'){
		var option = $j('<option value="gt">gt</option>');
		selectTag.append(option);
		var option = $j('<option value="lt">lt</option>');
		selectTag.append(option);
		var option = $j('<option value="ge">ge</option>');
		selectTag.append(option);
		var option = $j('<option value="le">le</option>');
		selectTag.append(option);
	}else if(type=='text'){
		var option = $j('<option value="like">like</option>');
		selectTag.append(option);
	}
	return selectTag;
}