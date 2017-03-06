function NewResultSettingUI(settingUIStartup) {
	this.setupModal = "#setUpModal";
	this.headerTab = this.setupModal + " #headerTab";
	this.contentTab = this.setupModal + " #contentTab";
	this.footerTab = this.setupModal + " #footerTab";
	this.layoutTab = this.setupModal + " #layoutTab";
	this.tabs = this.setupModal + " #layout_tabs";
	this.btnSave = $j(this.setupModal + " #btnSave");
	this.chkVisible = this.setupModal + " #content_visible";
	this.settingUIStartup = settingUIStartup;
}
NewResultSettingUI.prototype.getSettingUIStartup = function() {
	return this.settingUIStartup;
}
NewResultSettingUI.prototype.getSettingService = function() {
	var settingUIStartup = this.getSettingUIStartup();
	return settingUIStartup.getSettingService();
}
NewResultSettingUI.prototype.init = function() {
	var self=this;
	var parent=$j("<div/>");
	parent.append(self.generateHeader());
	$j(self.headerTab).html(parent.html());
	/*Content Section Begins*/
	var parent=$j("<div/>");
	parent.append(self.generateContentPage());
	$j(self.contentTab).html(parent.html());
	parent=$j("<div/>");
	parent.append(self.generateFooter());
	$j(self.footerTab).html(parent.html());
	parent=$j("<div/>");
	parent.append(self.generateLayout());
	$j(self.layoutTab).html(parent.html());
	return parent;
}
NewResultSettingUI.prototype.initValues = function() {
	var self=this;
	methodInvoker.fillPageSettingsToSelect(self.setupModal + " .select_fields");
	var settings = methodInvoker.getPageSettings();
	$j(settings).each(function(index, setting){
		if(setting.key=='content'){
			if($j("#" + setting.key + " #content_visible")){
				if(setting.visible!=false)
					$j("#" + setting.key .settingsParam+ " #content_visible").attr('checked',true);
			}	
			if($j("#" + setting.key + " #content_width")){
				if(setting.width!="")
					$j("#" + setting.key + " #content_width").val(setting.width);
			}	
			if($j("#" + setting.key + " #content_height")){
				if(setting.height!="")
					$j("#" + setting.key + " #content_height").val(setting.height);
			}	
			if($j("#" + setting.key + " #marginTop")){
				if(setting.marginTop!="")
					$j("#" + setting.key + " #marginTop").val(setting.marginTop);
			}	
			if($j("#" + setting.key + " #fontSize")){
				if(setting.fontSize!='' && setting.fontSize!=null)
					$j("#" + setting.key + " #content_fontSize").val(setting.fontSize);
			}
		} else {
			if($j("#" + setting.key + " #width")){
				if(setting.width!='' && setting.width!=null)
					$j("#" + setting.key + " #width").val(setting.width);
			}
			if($j("#" + setting.key + " #height")){
				if(setting.height!='' && setting.height!=null)
					$j("#" + setting.key + " #height").val(setting.height);
			}
			if($j("#" + setting.key + " #marginTop")){
				if(setting.marginTop!='' && setting.marginTop!=null)
					$j("#" + setting.key + " #marginTop").val(setting.marginTop);
			}
			if($j("#" + setting.key + " #marginLeft")){
				if(setting.marginLeft!='' && setting.marginLeft!=null)
					$j("#" + setting.key + " #marginLeft").val(setting.marginLeft);
			}
			if($j("#" + setting.key + " #fontSize")){
				if(setting.fontSize!='' && setting.fontSize!=null)
					$j("#" + setting.key + " #fontSize").val(setting.fontSize);
			}
			if($j("#" + setting.key + " #visible")){
				if(setting.visible!=false)
					$j("#" + setting.key + " #visible").attr('checked',true);
			}
			if($j("#" + setting.key + " #bold")){
				if(setting.bold!=false)
					$j("#" + setting.key + " #bold").attr('checked',true);
			}
			if($j("#" + setting.key + " #italics")){
				if(setting.italics!=false)
					$j("#" + setting.key + " #italics").attr('checked',true);
			}
			if($j("#" + setting.key + " #underline")){
				if(setting.underline!=false)
					$j("#" + setting.key + " #underline").attr('checked',true);
			}
			if($j("#" + setting.key + " #border")){
				if(setting.border!=false)
					$j("#" + setting.key + " #border").attr('checked',true);
			}
			if($j("#" + setting.key + " #label")){
				if(setting.label!='' && setting.label!=null){
					if($j("#" + setting.key + " #label").hasClass('select_fields'))
						$j("#" + setting.key + " #label option[value=" + setting.label + "]").attr('selected','selected');
					else
						$j("#" + setting.key + " #label").val(setting.label);
				}
			}
		}
	});
}
NewResultSettingUI.prototype.getSection=function(positionValue){
	var rightSection = $j("<div class='styles " + positionValue +  "_styles' />");
	var content = $j("<div />");
		spanTag = $j("<span><input type='checkbox' id='bold'> B </input></span>");
	content.append(spanTag);
		spanTag = $j("<span><input type='checkbox' id='italics'> I </input></span>");
	content.append(spanTag);
		spanTag = $j("<span><input type='checkbox' id='underline'> U </input></span>");
	content.append(spanTag);
		spanTag = $j("<span><input type='checkbox' id='visible'> Visible </input></span>");
	content.append(spanTag);
	rightSection.append(content);
	content = $j("<div class='sub'/>");
		spanTag = $j("<span>Width<input type='text' class='tiny-small' id='width'/></span>");
	content.append(spanTag);
		spanTag = $j("<span>Height<input type='text' class='tiny-small' id='height'/></span>");
	content.append(spanTag);
	rightSection.append(content);
	content = $j("<div class='sub'/>");
		spanTag = $j("<span>Font-Size<input type='text' class='tiny-small' id='fontSize'/></span>");
	content.append(spanTag);
	rightSection.append(content);
	return rightSection;
}
NewResultSettingUI.prototype.bindEvents=function(){
	var self=this; 
	self.init();
	$j(self.tabs).tabs();
	self.btnSave.die('click').live('click',function() {
		var contents = [];
		contents.push(self.getHeadernfo());//header
		contents.push(self.getContentInfo());
		contents.push(self.getFooterInfo());
		contents.push(self.getLayoutInfo());
		$j("#content .keys").each(function() {
			var key=$j(this).attr('id');
			contents.push(self.getContentInfoUsingKey(key));
		});
		var settingService = self.getSettingService();
		var response = settingService.updatePageSettings(contents);
		if(!response.errorMessage){
			methodInvoker.setPageSettings(response);
		}
		self.cancel();
	});
}
NewResultSettingUI.prototype.cancel = function() {
	var self=this;	
	$j(self.setupModal).trigger('reveal:close');
	$j(self.setupModal).remove();
	self=pageHandler.getActivePage();
}
NewResultSettingUI.prototype.getContentInfoUsingKey=function(key){
	var self=this;
	var contentInfo = new PageContentInfoDTO();
	contentInfo.setKey(key);
	if($j("#" + key + " #bold").attr('checked'))
		contentInfo.setBold(true);
	else
		contentInfo.setBold(false);
	if($j("#" + key + " #italics").attr('checked'))
		contentInfo.setItalics(true);
	else
		contentInfo.setItalics(false);
	if($j("#" + key + " #underline").attr('checked'))
		contentInfo.setUnderline(true);
	else
		contentInfo.setUnderline(false);
	if($j("#" + key + " #visible").attr('checked'))
		contentInfo.setVisible(true);
	else
		contentInfo.setVisible(false);
	
	contentInfo.setLabel($j("#" + key + " #label").val().trim());
	contentInfo.setHeight($j("#" + key + " #height").val().trim());
	contentInfo.setWidth($j("#" + key + " #width").val().trim());
	contentInfo.setFontSize($j("#" + key + " #fontSize").val().trim());
	return contentInfo;
}
NewResultSettingUI.prototype.getHeadernfo=function(){
	var self=this;
	var contentInfo = new PageContentInfoDTO();
	contentInfo.setKey('header');
	contentInfo.setWidth($j("#header #width").val());
	contentInfo.setHeight($j("#header #height").val());
	contentInfo.setMarginTop($j("#header #marginTop").val());
	contentInfo.setMarginLeft($j("#header #marginLeft").val());
	return contentInfo;
}
NewResultSettingUI.prototype.getContentInfo=function(){
	var self=this;
	var contentInfo = new PageContentInfoDTO();
	contentInfo.setKey('content');
	contentInfo.setWidth($j("#content #content_width").val());
	contentInfo.setHeight($j("#content #content_height").val());
	contentInfo.setMarginTop($j("#content #marginTop").val());
	contentInfo.setFontSize($j("#content #content_fontSize").val());
	if($j("#content #content_visible").attr('checked'))
		contentInfo.setVisible(true);
	else
		contentInfo.setVisible(false);
	return contentInfo;
}
NewResultSettingUI.prototype.getFooterInfo=function(){
	var self=this;
	var contentInfo = new PageContentInfoDTO();
	contentInfo.setKey('footer');
	contentInfo.setWidth($j("#footer #width").val());
	contentInfo.setHeight($j("#footer #height").val());
	return contentInfo;
}
NewResultSettingUI.prototype.getLayoutInfo=function(){
	var self=this;
	var contentInfo = new PageContentInfoDTO();
	contentInfo.setKey('general_layout');
	if($j("#general_layout #border").attr('checked'))
		contentInfo.setBorder(true);
	else
		contentInfo.setBorder(false);
	return contentInfo;
}
NewResultSettingUI.prototype.getResultSetupPage = function() {
	var parent = $j("<div />");
	var root = $j('<div id="layout_tabs"/>');
	root.append($j('<ul><li><a href="#headerTab">Header</a></li><li><a href="#contentTab">Content</a></li><li><a href="#footerTab">Footer</a></li><li><a href="#layoutTab">Layout</a></li></ul>'));
	root.append($j('<div id="headerTab"/>'));
	root.append($j('<div id="contentTab"/>'));
	root.append($j('<div id="footerTab"/>'));
	root.append($j('<div id="layoutTab"/>'));
	parent.append(root);
	var buttonSection = $j("<div id='buttontab' >");
	var btn = $j("<input type='button' class='stdbtn btn_black' id='btnSave' value='Submit'/>");
	buttonSection.append(btn);
	parent.append(buttonSection);
	return parent;
}

NewResultSettingUI.prototype.generateContentPage =function(){
	var self=this;
	var headerSection=$j("<div id='content' class='tabsection'/>");
	column=$j('<div class="left"/>');
	var column1 = $j("<div class='column_sub_1'>Width</div>");
	column.append(column1);

	var column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='content_width'/>"));
	column.append(column2);
	
	column1 = $j("<div class='column_sub_1' >Height</div>");
	column.append(column1);

	column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='content_height'/>"));
	column.append(column2);

	column1 = $j("<div class='column_sub_1'>Visible</div>");
	column.append(column1);

	column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type='checkbox' class='headerfields' id='content_visible'/>"));
	column.append(column2);
	headerSection.append(column);
	
	column=$j('<div class="left"/>');
	var column1 = $j("<div class='column_sub_1'>Margin-Top</div>");
	column.append(column1);
	var column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='marginTop'/>"));
	column.append(column2);

	column1 = $j("<div class='column_sub_1' >Font-Size (pixels)</div>");
	column.append(column1);
	column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='content_fontSize'/>"));
	column.append(column2);
	headerSection.append(column);

	column=$j('<div class="left"/>');
	column1 = self.generateColumn('column_1 keys','L1C', "Caption 1");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_2 keys caption_value','L1V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','M1C', "Caption 2");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_2 keys caption_value','M1V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','R1C', "Caption 3");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_2 keys caption_value','R1V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','L2C', "Caption 4");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','L2V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','M2C', "Caption 5");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','M2V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','R2C', "Caption 6");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','R2V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','L3C', "Caption 7");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','L3V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','M3C', "Caption 8");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','M3V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','R3C', "Caption 9");
	column.append(column1);
	headerSection.append(column); 
	column1 = self.generateColumnSelect('column_1 keys caption_value','R3V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','L4C', "Caption 10");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','L4V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','M4C', "Caption 11");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','M4V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','R4C', "Caption 12");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','R4V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','L5C', "Caption 13");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','L5V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','M5C', "Caption 14");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','M5V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','R5C', "Caption 15");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','R5V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','L6C', "Caption 16");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','L6V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','M6C', "Caption 17");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','M6V', "Value");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumn('column_1 keys','R6C', "Caption 18");
	column.append(column1);
	headerSection.append(column);
	column1 = self.generateColumnSelect('column_1 keys caption_value','R6V', "Value");
	column.append(column1);
	headerSection.append(column);
	return headerSection;


	//var firstColumn1=this.generateColumn('column_1', 'L1C');
	//parent.append(firstRow);
	return "parent";
}
NewResultSettingUI.prototype.generateColumn =function(className, groupName,placeHolder){
	var self=this;
	var parentColumn = $j('<div class="' + className + '" id=' + groupName + ' />');
	var row = $j('<div/>');
	var txtBox = $j('<input type=text id="label" class="text-style" placeHolder="' + placeHolder + '"/>');
	row.append(txtBox);
	parentColumn.append(row);
	parentColumn.append(self.getSection(groupName));
	return parentColumn;
}
NewResultSettingUI.prototype.generateColumnSelect =function(className, groupName,placeHolder){
	var self=this;
	var parentColumn = $j('<div class="' + className + '" id=' + groupName + ' />');
	var row = $j('<div/>');
	var lstBox = $j('<select id="label" class="text-style select_fields"/>');
	row.append(lstBox);
	parentColumn.append(row);
	parentColumn.append(self.getSection(groupName));
	return parentColumn;
}
NewResultSettingUI.prototype.generateHeader=function(){
	var self=this; 
	var headerSection=$j("<div id='header' class='tabsection'/>");
	var column=$j('<div class="left"/>');
	var column1 = $j("<div class='column_sub_1'>Width</div>");
	column.append(column1);

	var column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='width'/>"));
	column.append(column2);
	
	var column1 = $j("<div class='column_sub_1'>Height</div>");
	column.append(column1);

	column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='height'/>"));
	column.append(column2);

	column1 = $j("<div class='column_sub_1'>Margin-Top</div>");
	column.append(column1); 
	column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='marginTop'/>"));
	column.append(column2);
	headerSection.append(column);
	column=$j('<div class="left"/>');
	column1 = $j("<div class='column_sub_1'>Margin-Left</div>");
	column.append(column1); 
	column2 = $j("<div class='column_sub_2' />");
	column2.append($j("<input type=text class='headerfields' id='marginLeft'/>"));
	column.append(column2);

	headerSection.append(column);
	return headerSection;
}
NewResultSettingUI.prototype.generateLayout=function(){
	var self=this; 
	var headerSection=$j("<div id='layout' class='tabsection'/>");
	var column=$j('<div class="left"><b><u>General Layout</u></b></div>');
	headerSection.append(column);

	column=$j('<div class="left"  id="general_layout"/>');
	var column1 = $j("<div class='column_1'>Border</div>");
	column.append(column1);

	var column2 = $j("<div class='column_2' />");
	column2.append($j("<input type='checkbox' class='headerfields' id='border'/>"));
	column.append(column2);
	
	headerSection.append(column);
	return headerSection;
}
NewResultSettingUI.prototype.generateFooter=function(){
	var self=this; 
	var headerSection=$j("<div id='footer' class='tabsection'/>");
	var column=$j('<div class="left"/>');
	var column1 = $j("<div class='column_1'>Width</div>");
	column.append(column1);

	var column2 = $j("<div class='column_2' />");
	column2.append($j("<input type=text class='headerfields' id='width'/>"));
	column.append(column2);
	
	var column1 = $j("<div class='column_1'>Height</div>");
	column.append(column1);

	column2 = $j("<div class='column_2' />");
	column2.append($j("<input type=text class='headerfields' id='height'/>"));
	column.append(column2);
	headerSection.append(column);
	return headerSection;
}