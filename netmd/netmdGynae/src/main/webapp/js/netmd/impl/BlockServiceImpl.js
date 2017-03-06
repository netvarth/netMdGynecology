function BlockServiceImpl () {
	
	this.setTableValues = function(tableObj, blockResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(blockResult.block) {
			if(blockResult.block.length>0) {			
				$j(blockResult.block).each(function(index, block) {
					if(block.status=="active"){
					var id=block.id;
					var rowData=$j(tableObj).dataTable().fnAddData([id,block.name]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","blockIdCol Ustyle");
				}	
				});
			}		
		} 
	}
	
}

BlockServiceImpl.prototype.createBlock=function (blockObj) {
	ajaxProcessor.setUrl(constants.CREATEBLOCKURL);
	return ajaxProcessor.post(blockObj);
}
BlockServiceImpl.prototype.updateBlock=function(blockObj) {
	ajaxProcessor.setUrl(constants.UPDATEBLOCKURL);
	return ajaxProcessor.post(blockObj);
}
BlockServiceImpl.prototype.deleteBlock=function(blockId) {
	ajaxProcessor.setUrl(constants.DELETEBLOCKURL + blockId);
	return ajaxProcessor.get();
}
BlockServiceImpl.prototype.viewBlock=function(blockId) {
	ajaxProcessor.setUrl(constants.VIEWBLOCKURL + blockId);
	return ajaxProcessor.get();
}