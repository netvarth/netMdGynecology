function ViewSettingUI(settingUIStartup) {
	this.viewSettingPage = "#viewSetting";
	this.errorHeader= $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewSetting #id label";
	this.name = "#viewSetting #name";
	this.tableName="#viewSetting #listTable";
	this.updateButton = '#viewSetting #btnSettingSave';
	this.editButton = '#viewSetting #btnSettingEdit';
	this.cancelButton = '#viewSetting #btnSettingCancel'; 
	this.ptbBack = "#settingGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#settingGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#settingGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = "#viewSetting :input[type=text]";
	this.workBench="#viewSetting #workBench";
	this.lblname="#viewSetting #lblname";
	this.btnAdd="#viewSetting #btnAdd";
	this.autofield="#viewSetting #autofield";
	this.deleteRow=".delete";
	this.settingListArray=[];
	this.settingUIStartup=settingUIStartup;
	this.viewSettingPTB = new ViewSettingPTB(this);
}
ViewSettingUI.prototype.getSettingList = function() {
	return this.settingList;
}
ViewSettingUI.prototype.setSettingList = function(settingList) {
	 this.settingList=settingList;
}
ViewSettingUI.prototype.getSettingUIStartup = function() {
	return this.settingUIStartup;
}
ViewSettingUI.prototype.getViewSettingPTB = function() {
	return this.viewSettingPTB;
}
ViewSettingUI.prototype.getSettingService = function() {
	var settingUIStartup = this.getSettingUIStartup();
	return settingUIStartup.getSettingService();
}
ViewSettingUI.prototype.setPageTitle = function(value) {
	this.pageTitle.empty().html(value);
}
ViewSettingUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
ViewSettingUI.prototype.init = function(settingId) {
	var self = this;
	var ViewSettingPTB = self.getViewSettingPTB();
	ViewSettingPTB.init(self);
	pageHandler.create(constants.VIEWSETTINGPAGEURL);
	//pageHandler.buttons(constants.PERMISSIONURL+constants.SETTINGVIEWINFOBUTTONS, '#' + constants.SETTINGBUTTONSCONTAINER);
	self.bindEvents();
	commonMethodInvoker.setViewTable(self.tableName);
	self.viewSetting(settingId);
	//pageHandler.setActivePage(self);
}
ViewSettingUI.prototype.bindEvents = function() {
	self = this;	
	var parent=this;
	$j(self.inputFields).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.editButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		$j(self.autofield).unbind('keypress').bind("keypress", function (e) {
			commonMethodInvoker.removeErrors();
			if(e.keyCode == 13) {
				var name=$j(self.autofield).val();
				if(name==""){
					commonMethodInvoker.createError($j(self.autofield), constants.LISTREQUIRED);
					return false;
				}
			$j(self.btnAdd).trigger('click');	
			}	
		});
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var settingDetail=self.getSettingList();
		self.setSetting(settingDetail);
		self.readable();
		self.settingListArray=[];
		
	});
	$j(self.btnAdd).die('click').click(function(){
		self=parent;
		commonMethodInvoker.removeErrors();
		var listValue=$j(self.autofield).val();	
		self.onAddEvent();
		$j(self.autofield).val("");	
		self.getSettingsRequest(listValue,"Add");	
	
	});
	$j(self.deleteRow).die('click').live('click',function(){
		self=parent;
	   	var currow=$j(this).closest('tr');
	    var name = currow.children("td:eq(0)").text();
		$j(self.tableName).dataTable().fnDeleteRow(currow[0]);
		self.getSettingsRequest(name,"Delete");
		   
	});
	$j(self.updateButton).die('click').live('click',function(){
		self=parent;
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var setting = self.getSetting();
		//alert(JSON.stringify(setting));
		if(self.settingListArray.length>0) {
			var settingValidator = new SettingValidator();
			var error = settingValidator.validate(setting);
		    if(error.errorStatus==false) {
				var settingService = self.getSettingService();
				var settingResponse = settingService.updateSetting(setting);
				//alert(JSON.stringify(settingResponse));
				if(!settingResponse.errorMessage){
					self.settingListArray=[];
					showTip(constants.SETTINGUPDATESUCCESS);
					var setting = self.getSetting();
					self.viewSetting(setting.id);
					self.readable();
				} else 
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData,settingResponse.errorMessage);	
		  	} else 
			 	self.createError(error);
		} else 
			self.readable();	 
	});
} 
ViewSettingUI.prototype.viewSetting = function(settingId) {
	self=this;
	var header = constants.SETTINGVIEWINFO;
	var settingService = self.getSettingService();
	var settingResponse = settingService.viewSetting(settingId);
	if(!settingResponse.errorMessage) {
		$j(settingResponse.setting).each(function(index,settingDetail){
			var setting = new SettingDTO();
			setting.setName(settingDetail.name);
			setting.setId(settingDetail.uid);
			self.setSettingList(settingDetail);
			self.setSetting(settingDetail); 
		});
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData,settingResponse.errorMessage);
	}
	self.setPageTitle(header);
}

ViewSettingUI.prototype.setSetting = function(settingDetail) {
	var self=this;
	$j(self.id).text(settingDetail.id);
	$j(self.name).val(settingDetail.name);
    $j(self.tableName).dataTable().fnClearTable();
	$j(settingDetail.settingList).each(function(index,setting){
		var rowData=$j(self.tableName).dataTable().fnAddData([setting.value,constants.DELETEIMAGETAG]);
		var row=$j(self.tableName).dataTable().fnSettings().aoData[rowData].nTr;				
		$j(row).attr('id',setting.uid);
	});
	$j(self.tableName).dataTable().fnSetColumnVis(1, false);	
}

ViewSettingUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewSettingPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.workBench).show();
	$j(self.viewSettingPage + " input[type=text]").removeAttr('readonly');
	$j(self.viewSettingPage + " input[type=text]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.viewSettingPage ).removeAttr('readonly');
	$j(self.updateButton).show();
	$j(self.tableName).dataTable().fnSetColumnVis(1, true);
	$j(self.workBench).show();
	$j(self.viewSettingPage + " input[type=text]").attr('disabled',false);
}

ViewSettingUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewSettingPage + " input[type=text]").attr('readonly',true);
	$j(self.viewSettingPage + " input[type=text]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.lblname).show()
	$j(self.workBench).hide();
	$j(self.viewSettingPage + " input[type=text]").attr('disabled',true);
}

ViewSettingUI.prototype.getPrevId = function(curId,settingResult) {
	var prevId;
	$j(settingResult.setting).each(function (index, rowSetting) {
		if(curId==rowSetting.uid)	{
			var arrayLength=(settingResult.setting).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=settingResult.setting[index-1].uid;
		}
	});
	return prevId;	
}
	
ViewSettingUI.prototype.getNextId = function(curId,settingResult) {
	var nextId;
	$j(settingResult.setting).each(function (index, rowSetting) {
		if(curId==rowSetting.uid)	{
			var arrayLength=(settingResult.setting).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=settingResult.setting[index+1].uid;
			}	
		}
	});	
	return nextId;	
}

ViewSettingUI.prototype.onAddEvent = function() {
	var self=this;
	commonMethodInvoker.removeErrors();
	var value = $j(self.autofield).val();
	var dupStatus = self.checkListDup(value);
	if(dupStatus==true){
		commonMethodInvoker.createError($j(self.autofield),constants.LISTDUPLICATION);
		return false;
	}
    var rowData=$j(self.tableName).dataTable().fnAddData([value,constants.DELETEIMAGETAG]);
	var row=$j(self.tableName).dataTable().fnSettings().aoData[rowData].nTr;					
	return true;	
}

ViewSettingUI.prototype.checkListDup = function(value) {
	var status=false;
	if($j(self.tableName).dataTable().fnGetData().length>0) {
		var table = $j(self.tableName+" tr:gt(0)"); //this will not include the header row
		table.each(function() {
			var rowvalue=$j(this).children(' td:nth-child(1)').text();
			if(value==rowvalue) {
				status=true;
				return false;
			}
		});	
	}	
	return status;	
}
ViewSettingUI.prototype.getSettingsRequest = function(name,action) {
	var self=this;
	var status = self.checkIfExists(name);
	self.settingListArray=self.filterExpression_settingList(self.settingListArray,name);
	var settingListDTO = new SettingListDTO();
	settingListDTO.setValue(name);
	if(action=='Delete'){
		if(status==true) {	
			settingListDTO.setActionName(action);
            self.settingListArray.push(settingListDTO); 			
	     }
	} else {
	    if(status==false) {
			 settingListDTO.setActionName(action);
			 self.settingListArray.push(settingListDTO);
	    } else {
			 settingListDTO.setActionName("update");
			 self.settingListArray.push(settingListDTO);
		} 
	}
	return self.settingListArray; 
}
ViewSettingUI.prototype.checkIfExists=function (name) {
	var list=self.getSettingList();
	var status = false;
	$j(list.settingList).each(function(index,setting) {
		if(setting.value==name){
		    status=true;
			return false;
		}   
	});  
	return status; 
}
ViewSettingUI.prototype.filterExpression_settingList=function (exp,name) {
	var result=[];
	$j(exp).each(function(index,data){
		if(data.value==name)
			exp.splice(index,1);
		else
           result.push(data);		
	});
	return result;
		
}
ViewSettingUI.prototype.getSetting = function() {
	var self=this;
	var name = $j(self.name).val();	
	var id=$j(self.id).text();
	var settingDTO = new SettingDTO();
	settingDTO.setName(name);
	settingDTO.setId(id)
	settingDTO.setSettingList(self.settingListArray); 
	return settingDTO; 
}