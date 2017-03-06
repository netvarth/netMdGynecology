function ViewRoomTypeUI(roomTypeUIStartup) {
	this.viewRoomTypePage = "#viewRoomType";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewRoomType #id label";
	this.type = "#viewRoomType #type";
	this.noOfBeds="#viewRoomType #noOfBeds";
	this.count="#viewRoomType #count";
	this.rent="#viewRoomType #rent";
	this.updateButton = '#viewRoomType #btnRoomTypeSave';
	this.editButton = '#viewRoomType #btnRoomTypeEdit';
	this.cancelButton = '#viewRoomType #btnRoomTypeCancel'; 
	this.ptbBack = "#roomTypeGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#roomTypeGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#roomTypeGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.roomTypeUIStartup=roomTypeUIStartup;
	this.viewRoomTypePTB = new ViewRoomTypePTB(this);
}
ViewRoomTypeUI.prototype.getRoomTypeUIStartup = function() {
	return this.roomTypeUIStartup;
}

ViewRoomTypeUI.prototype.getViewRoomTypePTB = function() {
	return this.viewRoomTypePTB;
}

ViewRoomTypeUI.prototype.getRoomTypeTableNavigator = function() {
	var roomTypeUIStartup = this.getRoomTypeUIStartup();
	return roomTypeUIStartup.getRoomTypeTableNavigator();
}

ViewRoomTypeUI.prototype.getRoomTypeService = function() {
	var roomTypeUIStartup = this.getRoomTypeUIStartup();
	return roomTypeUIStartup.getRoomTypeService();
}
//Set the page title of the area ui page
ViewRoomTypeUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewRoomTypeUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewRoomTypeUI.prototype.init = function(roomTypeId) {
	var self = this;
	var viewRoomTypePTB = self.getViewRoomTypePTB();
	viewRoomTypePTB.init(self);
	pageHandler.create(constants.VIEWROOMTYPEPAGEURL);
	this.bindEvents();
	this.viewRoomType(roomTypeId);
}

ViewRoomTypeUI.prototype.viewRoomType = function(roomTypeId) {
	self=this;
	var header = constants.VIEWROOMTYPETITLE;
	var roomTypeService = self.getRoomTypeService();
	var roomTypeResponse = roomTypeService.viewRoomType(roomTypeId);
	if(roomTypeResponse.success==true) {
		var roomType = new RoomTypeDTO();
		roomType.setType(roomTypeResponse.type);
		roomType.setCount(roomTypeResponse.count);
		roomType.setRent(roomTypeResponse.rent.toFixed(2));
		roomType.setNoOfBeds(roomTypeResponse.noOfBeds);
		roomType.setId(roomTypeResponse.id);
		self.setRoomType(roomType);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
	}
	self.setPageTitle(header);
}

ViewRoomTypeUI.prototype.setRoomType = function(roomType) {
	$j(self.id).text(roomType.id);
	$j(self.type).val(roomType.type);
	$j(self.count).val(roomType.count);
	$j(self.noOfBeds).val(roomType.noOfBeds);
	$j(self.rent).val(roomType.rent);
}

ViewRoomTypeUI.prototype.getRoomType = function() {
var self=this;
	var id = parseInt($j(self.id).text());
	var type = $j(self.type).val();
	var count = $j(self.count).val();	
	var rent=$j(self.rent).val();
	var noOfBeds=$j(self.noOfBeds).val();
	var roomType = new RoomTypeDTO();
	roomType.setId(id);
	roomType.setType(type);
	roomType.setCount(count);
	roomType.setRent(rent);
	roomType.setNoOfBeds(noOfBeds);
	return roomType;
}

ViewRoomTypeUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewRoomTypePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}



ViewRoomTypeUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewRoomTypePage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewRoomTypePage + " input[type=text]").removeAttr('readonly');
	$j(self.viewRoomTypePage + " input[type=text]").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewRoomTypePage + " input[type=text]").removeAttr('disabled');
}

ViewRoomTypeUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.viewRoomTypePage + " input[type=text]").attr('readonly',true);
	$j(self.viewRoomTypePage + " input[type=text]").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewRoomTypePage + " input[type=text]").attr('disabled',true);
}

ViewRoomTypeUI.prototype.getPrevId = function(curId,roomTypeResult) {
	var prevId;
	$j(roomTypeResult.roomType).each(function (index, rowRoomType) {
		if(curId==rowRoomType.id)	{
			var arrayLength=(roomTypeResult.roomType).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=roomTypeResult.roomType[index-1].id;
		}
	});
	return prevId;	
}
	
ViewRoomTypeUI.prototype.getNextId = function(curId,roomTypeResult) {
	var nextId;
	$j(roomTypeResult.roomType).each(function (index, rowRoomType) {
		if(curId==rowRoomType.id)	{
			var arrayLength=(roomTypeResult.roomType).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=roomTypeResult.roomType[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewRoomTypeUI.prototype.bindEvents = function() {
	self = this;
	$j(self.viewRoomTypePage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		
	});
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var roomType = self.getRoomType();
		var roomTypeValidator = new RoomTypeValidator();
		var error  = roomTypeValidator.validate(roomType);
		if(error.errorStatus==false) {
			var roomTypeService =self.getRoomTypeService();
			var roomTypeResponse = roomTypeService.updateRoomType(roomType);
			if(roomTypeResponse.success==true) {
				showTip(constants.ROOMTYPEUPDATESUCCESS);//For showing the global Tip
				var roomType = self.getRoomType();
				self.viewRoomType(roomType.id);
				self.readable();
			} else 
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
		
		} else 
				self.createError(error);
			
	});

   $j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var roomType = self.getRoomType();
		self.viewRoomType(roomType.id);
		self.readable();
	});		
	
}