function TaxServiceImpl () {
	
	this.setTableValues = function(tableObj, taxResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(taxResult.taxlist) {
			if(taxResult.taxlist.length>0) {			
				$j(taxResult.taxlist).each(function(index, tax) {
					var id=tax.id;
					var name=tax.name;
					var value=tax.taxVal;
					var rowData=$j(tableObj).dataTable().fnAddData([id,name,value]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","taxIdCol Ustyle");
				});
			}		
		} 
	}
	
}

TaxServiceImpl.prototype.createTax=function (taxObj) {
	ajaxProcessor.setUrl(constants.TAXCREATEURL);
	return ajaxProcessor.post(taxObj);
}
TaxServiceImpl.prototype.updateTax=function(taxObj) {
	ajaxProcessor.setUrl(constants.TAXUPDATEURL);
	return ajaxProcessor.post(taxObj);
}
TaxServiceImpl.prototype.deleteTax=function(taxId) {
	ajaxProcessor.setUrl(constants.TAXDELETEURL + taxId);
	return ajaxProcessor.get();
}
TaxServiceImpl.prototype.viewTax=function(taxId) {
	ajaxProcessor.setUrl(constants.TAXVIEWURL + taxId);
	return ajaxProcessor.get();
}
TaxServiceImpl.prototype.getTaxTypes=function() {
	ajaxProcessor.setUrl(constants.TAXES);
	return ajaxProcessor.get();
}