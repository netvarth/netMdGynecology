$j.cachedScript(constants_newCaseFunction).done(function(script, textStatus) {
})
$j(document).ready(function(){
//Case Operations 
$j('#btnCaseCreate').die('click').live('click',function(){
	removeErrors();
	if(validateNewCase()){
		var response = submitCaseInfo();
		//alert(JSON.stringify(response));
			if(response.success==true){
				$j('#newCaseForm').trigger('reveal:close');
				$j.cachedScript(constant_newCaseEntryPoint).done(function(script, textStatus) {
				})
				showTip("case created successfully");
		}
		 else
		 updateTipsNew(getErrorName(response.error),$j('#newCaseForm #errorDivNewCase'),$j('#newCaseForm #errorDivHeader'));
}			
})	
	
$j('#newCaseForm #btnCaseClear').die('click').click(function(){
		 removeErrors();
		 $j("#newCaseForm input[type=text]").val("");
		 $j("#newCaseForm input[type=password]").val("");	
         $j("#newCaseForm input[type=text],textarea").val("");		 
	});	
});		





