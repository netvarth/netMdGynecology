function ViewRoomTypePTB(viewRoomTypeUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewRoomTypeUI = viewRoomTypeUI;
	
	this.getViewRoomTypeUI = function() {
		return this.viewRoomTypeUI;
	}
		
	this.getRoomTypeUIStartup = function() {
		var viewRoomTypeUI = this.getViewRoomTypeUI();
		return viewRoomTypeUI.getRoomTypeUIStartup();
	}
	
	this.getRoomTypeTableNavigator = function() {
		var roomTypeUIStartup = this.getRoomTypeUIStartup();
		return roomTypeUIStartup.getRoomTypeTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewRoomTypeUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var roomTypeUIStartup = self.getRoomTypeUIStartup();
			roomTypeUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var roomTypeTableNavigator = self.getRoomTypeTableNavigator();
			var roomType = source.getRoomType();
			var prevId = self.getPrevId(roomType.getId(),roomTypeTableNavigator.getPgDataList());
			viewUI.viewRoomType(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var roomTypeTableNavigator = self.getRoomTypeTableNavigator();
			var roomType = source.getRoomType();
			var prevId = self.getNextId(roomType.getId(),roomTypeTableNavigator.getPgDataList());
			viewUI.viewRoomType(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.ROOMTYPEGENERAL, constants.ROOMTYPEGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}