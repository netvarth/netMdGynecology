function NewBedUI(bedUIStartup) {
	this.createButton = $j("#newBed #btnBedSave");
	this.cancelButton = $j('#newBed #btnBedCancel');
	this.newBedUIPage = "#newBed";
	this.bedModal = '#bedModal';
	this.errorHeader = $j('#bedModal #errorDivHeader');
	this.errorData = $j('#bedModal #errorDivNewBedData');
	this.bedNo="#newBed #bedNo";
	this.roomNo="#newBed #roomNo";
	this.type="#newBed #bedtype";
	this.description="#newBed #description";
	this.inputFields = ":input";
	this.bedUIStartup = bedUIStartup;
	this.roomResponse;
	this.roomId;
}

NewBedUI.prototype.getBedUIStartup = function() {
	return this.bedUIStartup;
}


NewBedUI.prototype.getBedUITableNavigator = function() {
	var bedUIStartup = this.getBedUIStartup();
	return bedUIStartup.getBedTableNavigator();
}

NewBedUI.prototype.init = function() {
	var self=this;
	var bedService = self.getBedService();
	var bedResponse = bedService.getBedTypes();
	self.roomResponse=bedService.getRooms();
	methodInvoker.fillBedTypeToControl(self.type,bedResponse);
	methodInvoker.fillRoomNumbersToControl(self.roomNo,self.roomResponse);
}

NewBedUI.prototype.getBedService = function() {
	var bedUIStartup = this.getBedUIStartup();
	return bedUIStartup.getBedService();
}

NewBedUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewBedUI.prototype.getBed = function() {
	var self=this;
	var bedType=$j(self.type+" option:selected").text();
	var bedTypeId=parseInt($j(self.type+" option:selected").val());
    var bedNumber=$j(self.bedNo).val();
    var roomNumber= $j(self.roomNo).val();
    var description=$j(self.description).val();
	var bed = new BedDTO();
	bed.setBedNumber(bedNumber);
	bed.setRoomId(self.roomId);
	bed.setBedTypeId(bedTypeId);
	bed.setBedType(bedType);
    bed.setDescription(description);
	return bed;
}
NewBedUI.prototype.checkRoomNumber = function(roomNumber) {
	var self=this;
	var status=false;
   var roomStatus=methodInvoker.checkValidRoomNumber(roomNumber,self.roomResponse);
    if(roomStatus!=0)
       self.roomId=roomStatus ; 
    else{
      commonMethodInvoker.createError($j(self.newBedUIPage + self.roomNo),constants.ENTERVALIDROOMNUMBER);
      status=true;
    }  
       return status; 	
}

NewBedUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newBedUIPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewBedUI.prototype.clearFields = function() {
	$j(this.newBedUIPage + " input[type=text],textarea").val("");
	$j(self.type+" option[value='select']").attr('selected','selected');
	$j(self.bedNo ).focus();
}

NewBedUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newBedUIPage + " " + self.inputFields);
	
	$j(self.newBedUIPage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.bedModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.bedModal + self.newBedUIPage + " input[type=text]").val("");	
		$j(self.bedModal).trigger('reveal:close');
		$j(self.bedModal).remove();
		self=self.getBedUIStartup();
	});

		$j(self.bedModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.bedModal + self.newBedUIPage + " input[type=text]").val("");	
		$j(self.bedModal).trigger('reveal:close');
		$j(self.bedModal).remove();
		self=self.getBedUIStartup();
	});
	 self.createButton.die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var roomNumber=$j(self.roomNo).val();
	var status=self.checkRoomNumber(roomNumber);
	if(status!=true){
		var bed = self.getBed();
		var bedValidator = new BedValidator();
		var error  = bedValidator.validate(bed);
		if(error.errorStatus==false) {
			var bedService = self.getBedService();
			var bedResponse = bedService.createBed(bed);
			if(bedResponse.success==true) {
				showTip(constants.BEDCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var bedTableNavigator = self.getBedUITableNavigator();
				bedTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData,commonMethodInvoker.getErrorName(bedResponse.error));
			}
		} else {
			self.createError(error);
		}
	}	
});
	  self.cancelButton.die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.bedModal + self.newBedPage + " input[type=text]").val("");	
	$j(self.bedModal).trigger('reveal:close');
	$j(self.bedModal).remove();
	self=self.getBedUIStartup();
	});	

	


	

	
}