function ViewBedUI(bedUIStartup) {
	this.viewBedPage = "#viewBed";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewBed #id label";
	this.bedNumber = "#viewBed #bedNo";
	this.roomNumber="#viewBed #roomNo";
	this.description="#viewBed #description";
	this.viewBedType="#viewBed #viewBedType";
	this.editBedType="#viewBed #editBedType";
	this.bedTypelabel="#viewBed #lblbedType label";
	this.bedType="#viewBed #bedType";
	this.updateButton = '#viewBed #btnBedSave';
	this.editButton = '#viewBed #btnBedEdit';
	this.cancelButton = '#viewBed #btnBedCancel'; 
	this.ptbBack = "#bedGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#bedGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#bedGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.bedUIStartup=bedUIStartup;
	this.roomResponse;
	this.bedTypeId;
	this.roomId;
	this.viewBedPTB = new ViewBedPTB(this);
}
ViewBedUI.prototype.getBedUIStartup = function() {
	return this.bedUIStartup;
}

ViewBedUI.prototype.getViewBedPTB = function() {
	return this.viewBedPTB;
}

ViewBedUI.prototype.getBedTableNavigator = function() {
	var bedUIStartup = this.getBedUIStartup();
	return bedUIStartup.getBedTableNavigator();
}

ViewBedUI.prototype.getBedService = function() {
	var bedUIStartup = this.getBedUIStartup();
	return bedUIStartup.getBedService();
}
//Set the page title of the area ui page
ViewBedUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewBedUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}

ViewBedUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewBedPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
ViewBedUI.prototype.init = function(bedId) {
	var self = this;
	var viewBedPTB = self.getViewBedPTB();
	viewBedPTB.init(self);
	pageHandler.create(constants.VIEWBEDPAGE);
	this.bindEvents();
	this.viewBed(bedId);
}

ViewBedUI.prototype.viewBed = function(bedId) {
	self=this;
	var header = constants.VIEWBEDTITLE;
	var bedService = self.getBedService();
	var bedResponse = bedService.viewBed(bedId);
	if(bedResponse.success==true) {
		var bed = new BedDTO();
		bed.setId(bedResponse.id);
		bed.setBedNumber(bedResponse.bedNumber);
		bed.setBedType(bedResponse.bedType);
		self.bedTypeId=bedResponse.bedTypeId;
		bed.setRoomId(bedResponse.roomId);
		bed.setRoomName(bedResponse.roomName);
		bed.setDescription(bedResponse.description);
		self.setBed(bed);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
	}
	self.setPageTitle(header);
}

ViewBedUI.prototype.setBed = function(bed) {
	$j(self.id).text(bed.id);
	$j(self.bedTypelabel).text(bed.bedType);
	$j(self.bedNumber).val(bed.bedNumber);
	$j(self.roomNumber).val(bed.roomName);
	$j(self.description).val(bed.description);
}

ViewBedUI.prototype.getBed = function() {
var self=this;
	var id = parseInt($j(self.id).text());
	var roomNumber = $j(self.roomNumber).val();
	var description = $j(self.description).val();
	var bedTypeId = parseInt($j(self.bedType+" option:selected").val());
	var bedNumber=$j(self.bedNumber).val();
	var bed = new BedDTO();
	bed.setId(id);
	bed.setBedTypeId(bedTypeId);
	bed.setRoomName(roomNumber);
	bed.setRoomId(self.roomId);
	bed.setBedNumber(bedNumber);
	bed.setDescription(description);
	return bed;
}

ViewBedUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewBedPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
ViewBedUI.prototype.checkRoomNumber = function(roomNumber) {
	var self=this;
	var status=false;
  	var roomStatus=methodInvoker.checkValidRoomNumber(roomNumber,self.roomResponse);
    if(roomStatus!=0)
       self.roomId=roomStatus ; 
    else {
    	commonMethodInvoker.createError($j(self.viewBedPage + self.roomNumber),constants.ENTERVALIDROOMNUMBER);
     	 status=true;
    }  
    return status; 	
}


ViewBedUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewBedPage + " " + self.inputFields);
	$j(self.viewBedType).hide();
	$j(self.editBedType).show();
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewBedPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewBedPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewBedPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewBedUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewBedType).show();
	$j(self.editBedType).hide();
	$j(self.viewBedPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewBedPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewBedPage + " input[type=text]").attr('disabled',true);
}

ViewBedUI.prototype.getPrevId = function(curId,bedResult) {
	var prevId;
	$j(bedResult.bedList).each(function (index, rowBedType) {
		if(curId==rowBedType.id)	{
			var arrayLength=(bedResult.bedList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=bedResult.bedList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewBedUI.prototype.getNextId = function(curId,bedResult) {
	var nextId;
	$j(bedResult.bedList).each(function (index, rowBed) {
		if(curId==rowBed.id)	{
			var arrayLength=(bedResult.bedList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=bedResult.bedList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewBedUI.prototype.bindEvents = function() {
	self = this;
	$j(self.viewBedPage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var bedService =self.getBedService();
		var bedResponse = bedService.getBedTypes();
		self.roomResponse=bedService.getRooms();
		methodInvoker.fillBedTypeToControl(self.bedType,bedResponse);
		methodInvoker.fillRoomNumbersToControl(self.roomNo,self.roomResponse);
		$j(self.bedType+" option[value="+self.bedTypeId+"]").attr('selected','selected');
		self.writable(); 
		
	});
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var roomNumber=$j(self.roomNumber).val();
		var status=self.checkRoomNumber(roomNumber);
		if(status!=true){
			var bed = self.getBed();
			var bedValidator = new BedValidator();
			var error  = bedValidator.validate(bed);
			if(error.errorStatus==false) {
				var bedService =self.getBedService();
				var bedResponse = bedService.updateBed(bed);
				if(bedResponse.success==true) {
					showTip(constants.BEDUPDATESUCCESS);//For showing the global Tip
					var bed = self.getBed();
					self.viewBed(bed.id);
					self.readable();
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
			} else {
				self.createError(error);
			}
		}		
});

 $j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var bed = self.getBed();
	self.viewBed(bed.id);
	self.readable();
	});		
	
}