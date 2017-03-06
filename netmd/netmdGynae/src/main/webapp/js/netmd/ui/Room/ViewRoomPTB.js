function ViewRoomPTB(viewRoomUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewRoomUI = viewRoomUI;
	
	this.getViewRoomUI = function() {
		return this.viewRoomUI;
	}
		
	this.getRoomUIStartup = function() {
		var viewRoomUI = this.getViewRoomUI();
		return viewRoomUI.getRoomUIStartup();
	}
	
	this.getRoomTableNavigator = function() {
		var roomUIStartup = this.getRoomUIStartup();
		return roomUIStartup.getRoomTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewRoomUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var roomUIStartup = self.getRoomUIStartup();
			roomUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var roomTableNavigator = self.getRoomTableNavigator();
			var room = source.getRoom();
			var prevId = self.getPrevId(room.getId(),roomTableNavigator.getPgDataList());
			viewUI.viewRoom(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var roomTableNavigator = self.getRoomTableNavigator();
			var room = source.getRoom();
			var prevId = self.getNextId(room.getId(),roomTableNavigator.getPgDataList());
			viewUI.viewRoom(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.ROOMGENERAL, constants.ROOMGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}