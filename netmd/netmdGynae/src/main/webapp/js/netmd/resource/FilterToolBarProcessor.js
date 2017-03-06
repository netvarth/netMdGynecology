function FilterToolBarProcessor() {

	this.create = function(parent, category, url) {
		//Creating Fiter Tool Bar
		ajaxProcessor.setUrl(url);
		var response =ajaxProcessor.get();
		var ftbdata;
		$j(response.enumListDTO).each(function (index, enumList) {
			if(category==enumList.key) {
				ftbdata = enumList.enumValues;
				return false;
			}
		});
		var ftb = this.generate(ftbdata);
		var filterCont=$j('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $j('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(ftb); // Add the filter tool bar to Div
		filterCont.append(filterTB);
		var filterWB = $j('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $j('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		var filterSubBtn = $j('<input type="button" value="Go", id="' + parent + 'btnfltrSubmit"/>');
		filterWB.append(filterSubBtn);
		filterCont.append(filterWB);
		$j('#filterToolBar-Container').empty().html(filterCont);
		$j('#filter').show();
		$j('#filterWorkBench').hide();	
	}	
	this.generate = function(filterList) {
		var self = this;
		var toolBar=$j('<div/>');
		var specialCase=false;
		if(filterList!=null){	
			//Normal case
			$j(filterList).each(function(index,button) {
				if(button.parameterName=="")
					return;
				if(button.displayName.split("-").length==1){
					var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button.displayName +'" id="btn_'+ button.displayName +'_filter_id"><span>'+ button.parameterName +'</span></a>');
					toolBar.append(toolbutton);
					var type=methodInvoker.getFilterParameterType(button);
					if(type!=null){
						toolBar.append(self.setFilterOperators(button,type));}
					var inputTag=$j('<input type="text">');
					inputTag.attr('id','txt'+button.displayName);
					inputTag.addClass('genTextHeight');
					inputTag.attr('style','display:none');
					toolBar.append(inputTag);
				} else
					specialCase=true;
			});
			//Special Case
			if(specialCase==true){
				var btnName;
				selectFilter =$j("<select ></select>");
				$j(filterList).each(function(index,button) {
					if(button.parameterName=="")
						return;
					if(button.displayName.split("-").length>1){
						btnName= button.displayName;
						var selectContent = $j("<option value="+ btnName +">" + button.parameterName+"</option>");
						selectFilter.append(selectContent);
					}
				});
				
				var selectFilter1 = $j(selectFilter).clone();
				//var selectFilter2 = $j(selectFilter).clone();
				
				
				var button1 = btnName+"-1";
				var button2 = btnName+"-2";
				//var button3 = btnName+"-3";
				
				
				
				var selectFilterCont=$j("<div></div>");
				
				
				var selectName = btnName.split("-")[0]+"-"+btnName.split("-")[2]+"-1";
				selectFilter.attr('id',"lst"+selectName);
				
				selectFilterCont.html(selectFilter);
				
				var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button1 +'" id="btn_'+ button1 +'_filter_id"><span>'+ selectFilterCont.html() +'</span></a>');
				toolBar.append(toolbutton);
				
				var displayNames= btnName.split("-");
				var displayName=displayNames[1];
				
				var type=methodInvoker.getFilterParameterTypeNew(displayName);
				if(type!=null)
					toolBar.append(self.setFilterOperatorsNew(button1,type)); 
				var inputTag=$j('<input type="text">');
				inputTag.attr('id','txt'+selectName);
				inputTag.addClass('genTextHeight');
				inputTag.attr('style','display:none');
				toolBar.append(inputTag);
	
				var selectFilterCont=$j("<div></div>");
				var selectName = btnName.split("-")[0]+"-"+btnName.split("-")[2]+"-2";
				selectFilter1.attr('id',"lst"+selectName);
				selectFilterCont.html(selectFilter1);
			
				var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button2 +'" id="btn_'+ button2 +'_filter_id"><span>'+ selectFilterCont.html() +'</span></a>');
				toolBar.append(toolbutton);
				var type=methodInvoker.getFilterParameterTypeNew(displayName);
				if(type!=null)
					toolBar.append(self.setFilterOperatorsNew(button2,type)); 
				var inputTag=$j('<input type="text">');
				inputTag.attr('id','txt'+selectName);
				inputTag.addClass('genTextHeight');
			inputTag.attr('style','display:none');
				toolBar.append(inputTag);
				
				/* var selectFilterCont=$j("<div></div>");
				var selectName = btnName.split("-")[0]+"-"+btnName.split("-")[2]+"-3";
			    selectFilter2.attr('id',"lst"+selectName); */
				
			/* 	selectFilterCont.html(selectFilter2);
				var toolbutton= $j('<a href="#" class="anchorbutton remMarginRight genMarginLeft' + '" name="'+ button3 +'" id="btn_'+ button3 +'_filter_id"><span>'+ selectFilterCont.html() +'</span></a>');
				toolBar.append(toolbutton);
				var type=methodInvoker.getFilterParameterTypeNew(displayName);
				if(type!=null)
					toolBar.append(self.setFilterOperatorsNew(button3,type)); 
				var inputTag=$j('<input type="text">');
			inputTag.attr('id','txt'+selectName);
			inputTag.addClass('genTextHeight');
			inputTag.attr('style','display:none');
			toolBar.append(inputTag); */
				
				
				
			}
		}
		var inputTag=$j('<input type="button" value=" Go " class="genMarginLeft genTextHeight stdbtn btn_black">');
		inputTag.attr('id','btnGo');
		toolBar.append(inputTag);	
		return toolBar.html();
	}
	this.setFilterOperators=function(button,type) {
		var selectTag=$j('<select/>');
		selectTag.attr('id','lst'+ button.displayName);
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
	this.setFilterOperatorsNew=function(displayName,type) {
		var selectTag=$j('<select/>');
		selectTag.attr('id',displayName.split("-")[0]+"-"+displayName.split("-")[2]+"-"+displayName.split("-")[4]);
		selectTag.addClass('genTextHeight');
		selectTag.addClass('genTextwidth');
		selectTag.attr('style','display:none');
		return selectTag;
	}
	this.createFromHtml=function(parent, htmlData) {
		var filterCont=$j('<div id="' + parent + '-filter-cont"/>');
		var filterTB = $j('<div id="' + parent + '-filter-toolbar" class="box-content"/>');
		filterTB.append(htmlData); // Add the filter tool bar to Div
		filterCont.append(filterTB);
		var filterWB = $j('<div id="' + parent + '-filter-wb" style="display:none;padding:0 0 0 0px"/>');
		var filterWBInner = $j('<div id="' + parent + '-filter-wb-inner" style="float:left; width:40%"/>');
		filterWB.append(filterWBInner);
		filterCont.append(filterWB);
		$j('#filterToolBar-Container').empty().html(filterCont);
		$j('#filter').show();
		$j('#filterWorkBench').hide();	
	}
}