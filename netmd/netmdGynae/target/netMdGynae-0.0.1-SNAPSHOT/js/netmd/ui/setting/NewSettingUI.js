function NewSettingUI(settingUIStartup) {
	this.settingModal = '#settingModal';
	this.newSettingPage = this.settingModal + " #newSetting";
	this.createButton = $j(this.newSettingPage + " #btnSettingSave");
	this.cancelButton = $j(this.newSettingPage + " #btnSettingCancel");
	this.errorHeader = $j(this.newSettingPage + " #errorDivHeader");
	this.errorData = $j(this.newSettingPage + " #errorDivNewSettingData");
	this.name=this.newSettingPage + " #name";
	this.autoField=this.newSettingPage + " #autofield";
	this.btnAdd=this.newSettingPage + " #btnAdd";
	this.inputFields = this.newSettingPage + " :input[type=text]";
	this.deleteRow=this.newSettingPage + " .delete";
	this.settingUIStartup = settingUIStartup;
	this.tableObj=this.newSettingPage + " #listTable";
	commonMethodInvoker.setViewTable(this.tableObj);
	this.settingCreationStatus= false;
}
NewSettingUI.prototype.getSettingCreationStatus = function() {
	return this.settingCreationStatus;
}
NewSettingUI.prototype.setSettingCreationStatus = function(status) {
	this.settingCreationStatus=status;
}
 NewSettingUI.prototype.getSettingUIStartup = function() {
	return this.settingUIStartup;
}

NewSettingUI.prototype.getSettingTableNavigator = function() {
	var settingUIStartup = this.getSettingUIStartup();
	return settingUIStartup.getSettingTableNavigator();
}
NewSettingUI.prototype.getSettingService = function() {
	var settingUIStartup = this.getSettingUIStartup();
	return settingUIStartup.getSettingService();
}
NewSettingUI.prototype.removecolors = function(cl) {
	var self=this;
	commonMethodInvoker.removeErrorColor(self.name);
}
NewSettingUI.prototype.getSetting = function() {
	var self=this;
	var name = $j(self.name).val();
	var settingDTO = new SettingDTO();
	settingDTO.setName(name);
	var settingList=[];
	if($j(self.tableObj).dataTable().fnGetData().length>0) {
	  var rows = $j( self.tableObj +' tr:gt(0)');
	  rows.each(function(index, row) { 
		var rowValue=$j(row).children('td:nth-child(1)').text();
			var settingListDTO = new SettingListDTO();
			settingListDTO.setItemId(index+1);
			settingListDTO.setValue(rowValue);
			settingList.push(settingListDTO);
          
	}); 
	}	
	settingDTO.setSettingList(settingList); 	
	return settingDTO; 
}

NewSettingUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(errormsg.errorField), errormsg.errorMessage);
	});
}

NewSettingUI.prototype.clearFields = function() {
	self = this;
	$j(self.newSettingPage + " input[type=text],textarea").val("");
	$j(self.tableObj).dataTable().fnClearTable(); 
	$j(self.name ).focus();
	
}
NewSettingUI.prototype.cancel = function() {
	var self=this;
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.newSettingPage + " input[type=text]").val("");	
	$j(self.settingModal).trigger('reveal:close');
	$j(self.settingModal).remove();
	if(self.getSettingCreationStatus() == true){
		if(pageHandler.getHomePage()==constants.SETTINGSPAGE) {
			var settingNavigator = self.getSettingTableNavigator();
			settingNavigator.list();
		}
	}
	self.setSettingCreationStatus(false);
	//self=pageHandler.getActivePage();
}
NewSettingUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.inputFields);
	$j(self.inputFields).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	
	$j(self.name).bind("keypress", function (e) {
		if (e.keyCode == 13)
			$j(self.autoField).focus();
	});

	$j(self.autoField).bind("keypress", function (e) {
		if (e.keyCode == 13){
			if(self.autoField.val()=="")
			$j(self.createButton).focus();
		}
	});
	
	
	$j(self.autoField).unbind('keypress').bind("keypress", function (e) {
		commonMethodInvoker.removeErrors();
		if (e.keyCode == 13) {
			var autofieldValue=$j(self.autoField).val();
			if(autofieldValue.trim()=="") 
				return false;		
			$j( self.btnAdd).trigger('click');
				
		}	
	});
		//Add button
	$j(self.btnAdd).die('click').click(function(){
		commonMethodInvoker.removeErrors();
		var listValue=$j(self.autoField).val();	
		self.onAddEvent();
		$j(self.autoField).val("");	
	
	});

	$j(self.settingModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.cancel();
	});
	
	$j(self.deleteRow).die('click').live('click',function(){
	   var currow=$j(this).closest('tr');
	   $j(self.tableObj).dataTable().fnDeleteRow(currow[0]);
	   
	});
	self.cancelButton.die('click').live('click',function(){
		self.cancel();
	});
	
	self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var setting = self.getSetting();
		var settingValidator = new SettingValidator();
		var error = settingValidator.validate(setting,self);
		 if(error.errorStatus==false) {
			var settingService = self.getSettingService();
			var settingResponse = settingService.createSetting(setting);
			//alert(JSON.stringify(settingResponse));
			if(settingResponse.success==true) {
				showTip(constants.SETTINGCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var settingTableNavigator = self.getSettingTableNavigator();
				settingTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData,settingResponse.errorMessage);
			}
		} else {
			self.createError(error);
		}		  
	});
	
}
NewSettingUI.prototype.onAddEvent = function() {
	var self=this;
	commonMethodInvoker.removeErrors();
		autoField = $j(self.autoField).val();
		var dupStatus = self.checkListDup(autoField);
		if(dupStatus==true){
		    commonMethodInvoker.createError($j(self.autoField),constants.LISTDUPLICATION);
			return false;
		}
			var rowData=$j(this.tableObj).dataTable().fnAddData([autoField,constants.DELETEIMAGETAG]);
			var row=$j(this.tableObj).dataTable().fnSettings().aoData[rowData].nTr;					

	return true;
}
NewSettingUI.prototype.checkListDup = function(autoField) {
	var status=false;
	if($j(self.tableObj).dataTable().fnGetData().length>0) {
		var table = $j(self.tableObj+" tr:gt(0)"); //this will not include the header row
		table.each(function() {
			var rowvalue=$j(this).children(' td:nth-child(1)').text();
			if(autoField==rowvalue) {
				status=true;
				return false;
			}
		});	
	}	
	return status;	
}