function RoomTypeUIStartup() {
	this.pgTableName = "#roomType";
	this.pgTableContainer="#roomTypeListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#roomTypePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#roomTypePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#roomTypePTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#roomTypePTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.roomTypeIdCol';
	this.exp = new ExpressionListDTO();
	this.roomTypeService = new RoomTypeServiceImpl();
	this.listUrl = constants.ROOMTYPELISTURL;
	this.roomTypeTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.roomTypeService,this.exp);
	this.ftbToolBar = '#roomType-filter-toolbar';
	this.filter = '#filter';
	this.viewRoomTypeUI = new ViewRoomTypeUI(this);
}

RoomTypeUIStartup.prototype.setRoomTypeTableNavigator = function(roomTypeTableNavigator) {
	this.roomTypeTableNavigator = roomTypeTableNavigator;
}
RoomTypeUIStartup.prototype.getRoomTypeService = function() {
	return this.roomTypeService;
}
RoomTypeUIStartup.prototype.getRoomTypeTableNavigator = function() {
	return this.roomTypeTableNavigator;
}
RoomTypeUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
RoomTypeUIStartup.prototype.getViewRoomTypeUI = function() {
	return this.viewRoomTypeUI;
} 

RoomTypeUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var roomTypeTableNavigator = self.getRoomTypeTableNavigator();
	self.setPageTitle(constants.ROOMTYPETITLE);
	var exp = new ExpressionListDTO();
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.ROOMTYPE,constants.ROOMTYPEPAGETOOLBAR); //Create the Page tool Bar for roomtype
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.ROOMTYPELISTTABLEURL);//Create Table for Listing roomtype
	dataTableProcessor.setCustomTable(self.pgTableName);
	roomTypeTableNavigator.setExp(expList);
	roomTypeTableNavigator.list();
	self.bindEvents();
	
}

 RoomTypeUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEROOMTYPEUI,constants.ROOMTYPEMODAL);		
		openModalBox(obj,constants.ROOMTYPEMODAL);
		var newRoomTypeUI = new NewRoomTypeUI(self);
		newRoomTypeUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var roomTypeId=self.getSelectedRoomTypeId(self.pgTableName);
		$j('#' + constants.ROOMTYPE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(roomTypeId!="") {
			var viewRoomTypeUI = self.getViewRoomTypeUI();
			viewRoomTypeUI.init(roomTypeId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var roomTypeId=self.getSelectedRoomTypeId(self.pgTableName);
		if(roomTypeId!="") {
			var roomTypeService = self.getRoomTypeService();
			var roomTypeResponse = roomTypeService.viewRoomType(roomTypeId);
			var confirmStatus = confirm(constants.ROOMTYPEDELETECONFIRM + roomTypeResponse.type);
			if(confirmStatus==true) {				
				var response = roomTypeService.deleteRoomType(roomTypeId);
				if(response.success==true) {
					showTip(constants.ROOMTYPEDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(roomtypeResponse.error));
				}
				var roomtypeTableNavigator = self.getRoomTypeTableNavigator();
				roomtypeTableNavigator.list();
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

RoomTypeUIStartup.prototype.getSelectedRoomTypeId = function () {
	var roomTypeId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selRoomType = $j(this.pgTableName + ' tbody tr[selected]');
		if(selRoomType.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEROOMTYPE);
		} else if(selRoomType.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEROOMTYPEONLY);
		else
			roomTypeId=selRoomType.attr('id');
	}
	return roomTypeId;
}
 RoomTypeUIStartup.prototype.bindEvents = function() {
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
	   var roomtypeId= $j(this).parent().attr('id');
	   $j('#' + constants.ROOMTYPE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(roomtypeId!="") {
			$j('#roomtype-filter-wb').hide();
			var viewRoomTypeUI = self.getViewRoomTypeUI();
			viewRoomTypeUI.init(roomtypeId);
		}	
	});
} 