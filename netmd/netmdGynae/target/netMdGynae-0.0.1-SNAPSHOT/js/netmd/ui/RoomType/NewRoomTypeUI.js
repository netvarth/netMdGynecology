function NewRoomTypeUI(roomTypeUIStartup) {
	this.createButton = $j("#newRoomType #btnRoomTypeSave");
	this.cancelButton = $j('#newRoomType #btnRoomTypeCancel');
	this.newRoomTypeUIPage = "#newRoomType";
	this.roomTypeModal = '#roomTypeModal';
	this.errorHeader = $j('#roomTypeModal #errorDivHeader');
	this.errorData = $j('#roomTypeModal #errorDivNewRoomTypeData');
	this.type="#newRoomType #type";
	this.rent="#newRoomType #rent";
	this.noOfBeds="#newRoomType #nmbrOfBed";
	this.count="#newRoomType #count";
	this.inputFields = ":input";
	this.roomTypeUIStartup = roomTypeUIStartup;
}

NewRoomTypeUI.prototype.getRoomTypeUIStartup = function() {
	return this.roomTypeUIStartup;
}

NewRoomTypeUI.prototype.getRoomTypeUITableNavigator = function() {
	var roomTypeUIStartup = this.getRoomTypeUIStartup();
	return roomTypeUIStartup.getRoomTypeTableNavigator();
}

NewRoomTypeUI.prototype.getRoomTypeService = function() {
	var roomTypeUIStartup = this.getRoomTypeUIStartup();
	return roomTypeUIStartup.getRoomTypeService();
}

NewRoomTypeUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewRoomTypeUI.prototype.getRoomType = function() {
	var self=this;
	var type = $j(self.type).val();	
	var rent=parseFloat($j(self.rent).val());
	var count=parseInt($j(self.count).val());
	var noOfBeds=parseInt($j(self.noOfBeds).val());
	var roomType = new RoomTypeDTO();
	roomType.setType(type);
	roomType.setRent(rent);
	roomType.setNoOfBeds(noOfBeds);
	roomType.setCount(count);
	return roomType;
}

NewRoomTypeUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newRoomTypeUIPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewRoomTypeUI.prototype.clearFields = function() {
	$j(this.newRoomTypeUIPage + " input[type=text]").val("");
}

NewRoomTypeUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newRoomTypeUIPage + " " + self.inputFields);
	
	$j(self.newRoomTypeUIPage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});

	$j(self.roomTypeModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.roomTypeModal + self.newRoomTypeUIPage + " input[type=text]").val("");	
		$j(self.roomTypeModal).trigger('reveal:close');
		$j(self.roomTypeModal).remove();
		self=self.getRoomTypeUIStartup();
	});
	 self.createButton.die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var roomType = self.getRoomType();
	var roomTypeValidator = new RoomTypeValidator();
	var error  = roomTypeValidator.validate(roomType);
	if(error.errorStatus==false) {
		var roomTypeService = self.getRoomTypeService();
		var roomTypeResponse = roomTypeService.createRoomType(roomType);
		if(roomTypeResponse.success==true) {
			showTip(constants.ROOMTYPECREATESUCCESS);//For showing the global Tip
			self.clearFields();
			var roomTypeTableNavigator = self.getRoomTypeUITableNavigator();
			roomTypeTableNavigator.list();
		} else {
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(roomTypeResponse.error));
	
		}
	} else {
		self.createError(error);
	}	
});
	  self.cancelButton.die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	$j(self.roomTypeModal + self.newRoomTypePage + " input[type=text]").val("");	
	$j(self.roomTypeModal).trigger('reveal:close');
	$j(self.roomTypeModal).remove();
	self=self.getRoomTypeUIStartup();
	});	

	


	

	
}