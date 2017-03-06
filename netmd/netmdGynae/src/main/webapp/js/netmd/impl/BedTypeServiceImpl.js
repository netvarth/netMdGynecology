function BedTypeServiceImpl () {
	
	this.setTableValues = function(tableObj,bedTypeResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(bedTypeResult.bedTypeList) {
			if(bedTypeResult.bedTypeList.length>0) {			
				$j(bedTypeResult.bedTypeList).each(function(index, bedtype) {
					var id=bedtype.id;
					var rent=bedtype.rent.toFixed(2);
					var type=bedtype.type;
					var rowData=$j(tableObj).dataTable().fnAddData([id,type,rent]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(3)").addClass('column-2');
					$j(row).children("td:nth-child(1)").attr("class","bedTypeIdCol Ustyle");
				});
			}		
		} 
	}
	
}

BedTypeServiceImpl.prototype.createBedType=function (bedTypeObj) {
	ajaxProcessor.setUrl(constants.CREATEBEDTYPEURL);
	return ajaxProcessor.post(bedTypeObj);
}
BedTypeServiceImpl.prototype.updateBedType=function(bedTypeObj) {
	ajaxProcessor.setUrl(constants.UPDATEBEDTYPEURL);
	return ajaxProcessor.post(bedTypeObj);
}
BedTypeServiceImpl.prototype.deleteBedType=function(bedTypeId) {
	ajaxProcessor.setUrl(constants.DELETEBEDTYPEURL + bedTypeId);
	return ajaxProcessor.get();
}
BedTypeServiceImpl.prototype.viewBedType=function(bedTypeId) {
	ajaxProcessor.setUrl(constants.VIEWBEDTYPEURL + bedTypeId);
	return ajaxProcessor.get();
}
BedTypeServiceImpl.prototype.getTaxTypes=function(bedTypeId) {
	ajaxProcessor.setUrl(constants.TAXES);
	return ajaxProcessor.get();
}