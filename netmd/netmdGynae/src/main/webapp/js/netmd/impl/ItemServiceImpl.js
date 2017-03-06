function ItemServiceImpl () {
	
	this.setTableValues = function(tableObj, itemResult) {	
		$j(tableObj).dataTable().fnClearTable();
		if(itemResult.itemList) {
			if(itemResult.itemList.length>0) {			
				$j(itemResult.itemList).each(function(index, itemList) {
					var id=itemList.id;
					var name=itemList.name;
					var price=itemList.price.toFixed(2);
					var rowData=$j(tableObj).dataTable().fnAddData([id,name,price]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(3)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","itemIdCol Ustyle");
				});
			}		
		} 
	}
	
}

ItemServiceImpl.prototype.createItem=function (itemObj) {
	ajaxProcessor.setUrl(constants.ITEMCREATEURL);
	return ajaxProcessor.post(itemObj);
}

ItemServiceImpl.prototype.updateItem=function(itemObj) {
	ajaxProcessor.setUrl(constants.ITEMUPDATEURL);
	return ajaxProcessor.post(itemObj);
}
ItemServiceImpl.prototype.deleteItem=function(itemId) {
	ajaxProcessor.setUrl(constants.ITEMDELETEURL + itemId);
	return ajaxProcessor.get();
}
ItemServiceImpl.prototype.viewItem=function(itemId) {
	ajaxProcessor.setUrl(constants.ITEMVIEWURL + itemId);
	return ajaxProcessor.get();
}
ItemServiceImpl.prototype.getTaxTypes=function() {
	ajaxProcessor.setUrl(constants.TAXES);
	return ajaxProcessor.get();
}
ItemServiceImpl.prototype.getItemList=function() {
	ajaxProcessor.setUrl(constants.GETITEMLIST);
	return ajaxProcessor.get();
}