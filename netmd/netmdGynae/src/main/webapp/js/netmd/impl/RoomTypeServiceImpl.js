function RoomTypeServiceImpl () {
	
	this.setTableValues = function(tableObj, roomTypeResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(roomTypeResult.roomType) {
			if(roomTypeResult.roomType.length>0) {			
				$j(roomTypeResult.roomType).each(function(index, roomType) {
					var id=roomType.id;
					var type=roomType.type;
					var rent=roomType.rent.toFixed(2);
					var rowData=$j(tableObj).dataTable().fnAddData([id,type,rent]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(3)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","roomTypeIdCol Ustyle");
				});
			}		
		} 
	}
	
}

RoomTypeServiceImpl.prototype.createRoomType=function (roomTypeObj) {
	ajaxProcessor.setUrl(constants.CREATEROOMTYPEURL);
	return ajaxProcessor.post(roomTypeObj);
}
RoomTypeServiceImpl.prototype.updateRoomType=function(roomTypeObj) {
	ajaxProcessor.setUrl(constants.UPDATEROOMTYPEURL);
	return ajaxProcessor.post(roomTypeObj);
}
RoomTypeServiceImpl.prototype.deleteRoomType=function(roomTypeId) {
	ajaxProcessor.setUrl(constants.DELETEROOMTYPEURL + roomTypeId);
	return ajaxProcessor.get();
}
RoomTypeServiceImpl.prototype.viewRoomType=function(roomTypeId) {
	ajaxProcessor.setUrl(constants.VIEWROOMTYPEURL + roomTypeId);
	return ajaxProcessor.get();
}