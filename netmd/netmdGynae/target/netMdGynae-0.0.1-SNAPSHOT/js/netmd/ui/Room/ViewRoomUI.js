function ViewRoomUI(roomUIStartup) {
	this.viewRoomPage = "#viewRoom";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.id = "#viewRoom #id label";
	this.roomNo="#viewRoom  #roomNo";
	this.lblviewDepartment="#viewRoom #lblViewDepartment label";
	this.lblviewBlock="#viewRoom #lblViewBlock label";
	this.lblviewRoomType="#viewRoom #lblViewRoomType label";
	this.viewDepartment="#viewRoom #viewDepartment";
	this.editDepartment="#viewRoom #editDepartment";
	this.department="#viewRoom #department";
	this.block="#viewRoom #block";
	this.roomType="#viewRoom #roomType";
	this.blockId="";
	this.departmentId="";
	this.roomTypeId="";
	this.description="#viewRoom #description";
	this.viewBlock="#viewRoom #viewBlock";
	this.editBlock="#viewRoom #editBlock";
	this.editRoomType="#viewRoom #editRoomType";
	this.viewRoomType="#viewRoom #viewRoomType";
	this.updateButton = '#viewRoom #btnRoomSave';
	this.editButton = '#viewRoom #btnRoomEdit';
	this.cancelButton = '#viewRoom #btnRoomCancel'; 
	this.ptbBack = "#roomGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#roomGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#roomGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.roomUIStartup=roomUIStartup;
	this.viewRoomPTB = new ViewRoomPTB(this);
}
ViewRoomUI.prototype.getRoomUIStartup = function() {
	return this.roomUIStartup;
}

ViewRoomUI.prototype.getViewRoomPTB = function() {
	return this.viewRoomPTB;
}

ViewRoomUI.prototype.getRoomTableNavigator = function() {
	var roomUIStartup = this.getRoomUIStartup();
	return roomUIStartup.getRoomTableNavigator();
}

ViewRoomUI.prototype.getRoomService = function() {
	var roomUIStartup = this.getRoomUIStartup();
	return roomUIStartup.getRoomService();
}
//Set the page title of the area ui page
ViewRoomUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewRoomUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.name);
}


ViewRoomUI.prototype.init = function(roomId) {
	var self = this;
	var viewRoomPTB = self.getViewRoomPTB();
	viewRoomPTB.init(self);
	pageHandler.create(constants.VIEWROOMPAGEURL);
	this.bindEvents();
	this.viewRoom(roomId);
}

ViewRoomUI.prototype.viewRoom = function(roomId) {
	self=this;
	var header = constants.ROOMVIEWINFO;
	var roomService = self.getRoomService();
	var roomResponse = roomService.viewRoom(roomId);
	if(roomResponse.success==true) {
		var room = new RoomDTO();
		room.setRoomNumber(roomResponse.roomNumber);
		room.setRoomTypeName(roomResponse.roomTypeName);
		room.setRoomTypeId(roomResponse.roomTypeId)
		room.setBlockName(roomResponse.blockName);
		room.setBlockId(roomResponse.blockId)
		room.setDepartmentName(roomResponse.departmentName);
		room.setDepartmentId(roomResponse.departmentId);
		room.setId(roomResponse.id);
		room.setDescription(roomResponse.description);
		self.setRoom(room);
	} else {

		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
	}
	self.setPageTitle(header);
}

ViewRoomUI.prototype.setRoom = function(room) {
	$j(self.id).text(room.id);
	$j(self.roomNo).val(room.roomNumber);
	$j(self.roomType).val(room.roomTypeName);
	$j(self.lblviewDepartment).text(room.departmentName);
	$j(self.lblviewBlock).text(room.blockName);
	$j(self.lblviewRoomType).text(room.roomTypeName);
	$j(self.description).text(room.description);
	self.roomTypeId=room.roomTypeId;
	self.blockId=room.blockId;
	self.departmentId=room.departmentId;
}

ViewRoomUI.prototype.getRoom = function() {
var self=this;
	var id = parseInt($j(self.id).text());
	var roomNumber = $j(self.roomNo).val();
	var departmentName = $j(self.department+" option:selected").text();
	var blockName= $j(self.block+" option:selected").text();	
	var roomType= $j(self.roomType+" option:selected").text();	
	var departmentId = $j(self.department+" option:selected").val();
	var blockId= $j(self.block+" option:selected").val();	
	var roomTypeId= $j(self.roomType+" option:selected").val();
	var description=$j(self.description).val();
	var room = new RoomDTO();
	room.setId(id);
	room.setRoomNumber(roomNumber);
	room.setRoomTypeName(roomType);
	room.setDepartmentName(departmentName);
	room.setBlockName(blockName);
	room.setRoomTypeId(roomTypeId);
	room.setDepartmentId(departmentId);
	room.setBlockId(blockId);
	room.setDescription(description);
	return room;
}

ViewRoomUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewRoomPage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}


ViewRoomUI.prototype.writable = function() {
	self=this;
	self.removecolors(self.viewRoomPage + " " + self.inputFields);
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.viewDepartment+" ,"+self.viewBlock+" ,"+self.viewRoomType).hide();
	$j(self.editDepartment+" ,"+self.editBlock+" ,"+self.editRoomType).show();
	$j(self.viewRoomPage + " input[type=text],textarea").removeAttr('readonly');
	$j(self.viewRoomPage + " input[type=text],textarea").removeClass('newBox');
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewRoomPage + " input[type=text],textarea").removeAttr('disabled');
}

ViewRoomUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
    $j(self.editDepartment+" ,"+self.editBlock+" ,"+self.editRoomType).hide();
    $j(self.viewDepartment+" ,"+self.viewBlock+" ,"+self.viewRoomType).show();
	$j(self.viewRoomPage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewRoomPage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewRoomPage + " input[type=text],textarea").attr('disabled',true);
}

ViewRoomUI.prototype.getPrevId = function(curId,roomResult) {
	var prevId;
	$j(roomResult.room).each(function (index, rowRoomType) {
		if(curId==rowRoomType.id)	{
			var arrayLength=(roomResult.room).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=roomResult.room[index-1].id;
		}
	});
	return prevId;	
}
	
ViewRoomUI.prototype.getNextId = function(curId,roomResult) {
	var nextId;
	$j(roomResult.room).each(function (index, rowRoom) {
		if(curId==rowRoom.id)	{
			var arrayLength=(roomResult.room).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=roomResult.room[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewRoomUI.prototype.bindEvents = function() {
	var self = this;
	$j(self.viewRoomPage).bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});

	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.viewRoomType+" ,"+self.viewBlock+" ,"+self.viewDepartment).hide();
		$j(self.editRoomType +" ,"+self.editBlock+" ,"+self.editDepartment).show();
		var roomService = self.getRoomService();
		var roomTypes = roomService.getRoomTypes();
		var blockList=roomService.getBlocks();
		var departmentList=roomService.getDepartments();
		methodInvoker.fillRoomTypeToControl(self.roomType,roomTypes);
		methodInvoker.fillBlockToControl(self.block,blockList);
		methodInvoker.fillDepartmentToControl(self.department,departmentList);
		$j(self.roomType+" option[value="+self.roomTypeId+"]").attr('selected','selected');
		$j(self.block+" option[value="+self.blockId+"]").attr('selected','selected');
		$j(self.department+" option[value="+self.departmentId+"]").attr('selected','selected');
		self.writable(); 
		
	});
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var room = self.getRoom();
		var roomValidator = new RoomValidator();
		var error  = roomValidator.validate(room);
		if(error.errorStatus==false) {
			var roomService =self.getRoomService();
			var roomResponse = roomService.updateRoom(room);
			if(roomResponse.success==true) {
				showTip(constants.ROOMUPDATESUCCESS);//For showing the global Tip
				var room = self.getRoom();
				self.viewRoom(room.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(roomResponse.error));
			}
		} else {
			self.createError(error);
		}		
});

 $j(self.cancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var room = self.getRoom();
	self.viewRoom(room.id);
	$j(self.editRoomType +" ,"+self.editBlock+" ,"+self.editDepartment).hide();
	$j(self.viewRoomType+" ,"+self.viewBlock+" ,"+self.viewDepartment).show();
	self.readable();
	});		
	
}