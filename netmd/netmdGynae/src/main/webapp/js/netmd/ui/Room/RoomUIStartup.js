function RoomUIStartup() {
	this.pgTableName = "#room";
	this.pgTableContainer="#roomListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#roomPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#roomPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#roomPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#roomPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.roomIdCol';
	this.exp = new ExpressionListDTO();
	this.roomService = new RoomServiceImpl();
	this.listUrl = constants.ROOMLISTURL;
	this.roomTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.roomService,this.exp);
	this.ftbToolBar = '#room-filter-toolbar';
	this.filter = '#filter';
	this.viewRoomUI = new ViewRoomUI(this);
}

RoomUIStartup.prototype.setRoomTableNavigator = function(roomTableNavigator) {
	this.roomTableNavigator = roomTableNavigator;
}
RoomUIStartup.prototype.getRoomService = function() {
	return this.roomService;
}
RoomUIStartup.prototype.getRoomTableNavigator = function() {
	return this.roomTableNavigator;
}
RoomUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
RoomUIStartup.prototype.getViewRoomUI = function() {
	return this.viewRoomUI;
} 

RoomUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var roomTableNavigator = self.getRoomTableNavigator();
	self.setPageTitle(constants.ROOMTITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.ROOM,constants.ROOMPAGETOOLBAR); //Create the Page tool Bar for roomtype
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.ROOMLISTTABLEURL);//Create Table for Listing roomtype
	dataTableProcessor.setCustomTable(self.pgTableName);
	roomTableNavigator.setExp(expList);
	roomTableNavigator.list();
	self.bindEvents();
	
}

 RoomUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEROOMUI,constants.ROOMMODAL);		
		openModalBox(obj,constants.ROOMMODAL);
		var newRoomUI = new NewRoomUI(self);
		newRoomUI.init();
		newRoomUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var roomId=self.getSelectedRoomId(self.pgTableName);
		$j('#' + constants.ROOM + '-filter-cont').hide();
		$j(self.filter).hide();
		if(roomId!="") {
			var viewRoomUI = self.getViewRoomUI();
			viewRoomUI.init(roomId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var roomId=self.getSelectedRoomId(self.pgTableName);
		if(roomId!="") {
			var roomService = self.getRoomService();
			var roomResponse = roomService.viewRoom(roomId);
			var confirmStatus = confirm(constants.ROOMDELETECONFIRM + roomResponse.roomNumber);
			if(confirmStatus==true) {				
				var roomResponse = roomService.deleteRoom(roomId);
				if(roomResponse.success==true) {
					showTip(constants.ROOMDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(roomResponse.error));
				}
				var roomTableNavigator = self.getRoomTableNavigator();
				roomTableNavigator.list();
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

RoomUIStartup.prototype.getSelectedRoomId = function () {
	var roomId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selRoom = $j(this.pgTableName + ' tbody tr[selected]');
		if(selRoom.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEROOM);
		} else if(selRoom.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEROOMONLY);
		else
			roomId=selRoom.attr('id');
	}
	return roomId;
}
 RoomUIStartup.prototype.bindEvents = function() {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(this.pgTableRowClass).die('click').live('click',function(){
	   var roomId= $j(this).parent().attr('id');
	   $j('#' + constants.ROOM + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(roomId!="") {
			$j('#room-filter-wb').hide();
			var viewRoomUI = self.getViewRoomUI();
			viewRoomUI.init(roomId);
		}	
	});
} 