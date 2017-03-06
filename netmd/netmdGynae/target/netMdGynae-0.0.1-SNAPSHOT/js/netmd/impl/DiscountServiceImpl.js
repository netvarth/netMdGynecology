function DiscountServiceImpl () {
	var self=this;
	
	this.setTableValues = function(tableObj, discountResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(discountResult.discount) {
			if(discountResult.discount.length>0) {			
				$j(discountResult.discount).each(function(index, discount) {
					var id=discount.id;
					var name=discount.name;
					var value=self.getDiscountValue(discount);
					var rowData=$j(tableObj).dataTable().fnAddData([id,name,value]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","discountIdCol Ustyle");
				});
			}		
		} 
	}
	
}

DiscountServiceImpl.prototype.createDiscount=function (discountObj) {
	ajaxProcessor.setUrl(constants.DISCOUNTCREATEURL);
	return ajaxProcessor.post(discountObj);
}
DiscountServiceImpl.prototype.updateDiscount=function(discountObj) {
	ajaxProcessor.setUrl(constants.DISCOUNTUPDATEURL);
	return ajaxProcessor.post(discountObj);
}
DiscountServiceImpl.prototype.deleteDiscount=function(discountId) {
	ajaxProcessor.setUrl(constants.DISCOUNTDELETEURL + discountId);
	return ajaxProcessor.get();
}
DiscountServiceImpl.prototype.viewDiscount=function(discountId) {
	ajaxProcessor.setUrl(constants.DISCOUNTVIEWURL + discountId);
	return ajaxProcessor.get();
}
DiscountServiceImpl.prototype.getDiscounts=function(discountId) {
	ajaxProcessor.setUrl(constants.GETDISCOUNTLIST);
	return ajaxProcessor.get();
}

DiscountServiceImpl.prototype.getDiscountValue=function(discount){
	var discountValue=discount.discValue;
	var disctype=discount.discType
	var calctype=discount.calculationType;
	if(calctype=="Percentage" && disctype=="Predefined")
		displayType= discountValue + " %";
	else if(calctype=="Fixed" && disctype=="Predefined")
		displayType=constants.RUPEE + discountValue.toFixed(2);
	else if(calctype=="Percentage" && disctype=="OnDemand")
		displayType=disctype + "- %";
	else
		displayType=disctype + '-'+ constants.RUPEE;
	return displayType;
}
