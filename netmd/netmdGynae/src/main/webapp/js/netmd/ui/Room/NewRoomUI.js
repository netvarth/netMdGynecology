function NewRoomUI(roomUIStartup) {
	this.createButton = $j("#newRoom #btnRoomSave");
	this.cancelButton = $j('#newRoom #btnRoomCancel');
	this.newRoomUIPage = "#newRoom";
	this.roomModal = '#roomModal';
	this.errorHeader = $j('#roomModal #errorDivHeader');
	this.errorData = $j('#roomModal #errorDivNewRoomData');
	this.roomNo="#newRoom #roomNo";
	this.roomtype="#newRoom #type";
	this.department="#newRoom #department";
	this.block="#newRoom #block";
	this.description="#newRoom #description";
	this.inputFields = ":input";
	this.roomUIStartup = roomUIStartup;
}

NewRoomUI.prototype.getRoomUIStartup = function() {
	return this.roomUIStartup;
}
NewRoomUI.prototype.init = function() {
	var self=this;
	var roomService = self.getRoomService();
	var roomTypes = roomService.getRoomTypes();
	var blockList=roomService.getBlocks();
	var departmentList=roomService.getDepartments();
	methodInvoker.fillRoomTypeToControl(self.roomtype,roomTypes);
	methodInvoker.fillBlockToControl(self.block,blockList);
	methodInvoker.fillDepartmentToControl(self.department,departmentList);
	
}
NewRoomUI.prototype.getRoomUITableNavigator = function() {
	var roomUIStartup = this.getRoomUIStartup();
	return roomUIStartup.getRoomTableNavigator();
}

NewRoomUI.prototype.getRoomService = function() {
	var roomUIStartup = this.getRoomUIStartup();
	return roomUIStartup.getRoomService();
}

NewRoomUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewRoomUI.prototype.getRoom = function() {
	var self=this;
	var roomNo=$j(self.roomNo).val();
	var roomtype = parseInt($j(self.roomtype).val());
	var	departmentId=parseInt($j(self.department).val());
	var blockId=parseInt($j(self.block).val());
	var description=$j(self.description).val();
	var room = new RoomDTO();
	room.setRoomNumber(roomNo);
	room.setRoomTypeId(roomtype);
	room.setBlockId(blockId);
	room.setDepartmentId(departmentId);
	room.setDescription(description);
	return room;
}

NewRoomUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newRoomUIPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}

NewRoomUI.prototype.clearFields = function() {
	var self=this;
	$j(self.newRoomUIPage + " input[type=text],textarea").val("");
	$j(self.department+" option[value='select']").attr('selected','selected');
	$j(self.block+" option[value='select']").attr('selected','selected');
	$j(self.roomtype+" option[value='select']").attr('selected','selected');
}

NewRoomUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newRoomUIPage + " " + self.inputFields);
	
	$j(self.newRoomUIPage+" input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.roomModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.roomModal + self.newRoomUIPage + " input[type=text]").val("");	
		$j(self.roomModal).trigger('reveal:close');
		$j(self.roomModal).remove();
		self=self.getRoomUIStartup();
	});
	self.createButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var room = self.getRoom();
		var roomValidator = new RoomValidator();
		var error  = roomValidator.validate(room);
		if(error.errorStatus==false) {
			var roomService = self.getRoomService();
			var roomResponse = roomService.createRoom(room);
			if(roomResponse.success==true) {
				showTip(constants.ROOMCREATESUCCESS);//For showing the global Tip
				self.clearFields();
				var roomTableNavigator = self.getRoomUITableNavigator();
				roomTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(roomResponse.error));
		
			}
		} else {
			self.createError(error);
		}		
	});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.roomModal + self.newRoomPage + " input[type=text]").val("");	
		$j(self.roomModal).trigger('reveal:close');
		$j(self.roomModal).remove();
		self=self.getRoomUIStartup();
	});	

	


	

	
}