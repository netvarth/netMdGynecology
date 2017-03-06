function RoomServiceImpl () {
	
	this.setTableValues = function(tableObj, roomResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(roomResult.room) {
			if(roomResult.room.length>0) {			
				$j(roomResult.room).each(function(index, room) {
					var id=room.id;
					var roomNo=room.roomNumber;
					var roomtype=room.roomTypeName;
					var rowData=$j(tableObj).dataTable().fnAddData([id,roomNo,roomtype]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","roomIdCol Ustyle");
				});
			}		
		} 
	}
	
}

RoomServiceImpl.prototype.createRoom=function (roomObj) {
	ajaxProcessor.setUrl(constants.CREATEROOMURL);
	return ajaxProcessor.post(roomObj);
}
RoomServiceImpl.prototype.updateRoom=function(roomObj) {
	ajaxProcessor.setUrl(constants.UPDATEROOMURL);
	return ajaxProcessor.post(roomObj);
}
RoomServiceImpl.prototype.deleteRoom=function(roomId) {
	ajaxProcessor.setUrl(constants.DELETEROOMURL + roomId);
	return ajaxProcessor.get();
}
RoomServiceImpl.prototype.viewRoom=function(roomId) {
	ajaxProcessor.setUrl(constants.VIEWROOMURL + roomId);
	return ajaxProcessor.get();
}
RoomServiceImpl.prototype.getRoomTypes=function() {
	ajaxProcessor.setUrl(constants.GETROOMTYPES);
	return ajaxProcessor.get();
}
RoomServiceImpl.prototype.getBlocks=function() {
	ajaxProcessor.setUrl(constants.GETBLOCKLIST);
	return ajaxProcessor.get();
}
RoomServiceImpl.prototype.getDepartments=function() {
	ajaxProcessor.setUrl(constants.GETDEPARTMENTLIST);
	return ajaxProcessor.get();
}