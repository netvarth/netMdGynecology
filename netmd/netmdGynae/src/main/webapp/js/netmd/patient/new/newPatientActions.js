$j.cachedScript(constant_newPatientFunctions_Url).done(function(script, textStatus) {
})

$j(document).ready(function(){
	$j('#newPatientForm #btnNewPatientCreate').die('click').live("click",function() {	
	//alert("hii");
	removeErrors();
	 if(validateNewPatient()) {
		 var response = submitPatientInfo();
		 //alert(JSON.stringify(response));
		 if(response.success==true){
		 //resetnewPatientForm();
		  $j('#newPatientForm').trigger('reveal:close');
		 $j.cachedScript(constant_PatientEntryPoint_Url).done(function(script, textStatus) {
		})
		showTip("Patient created successfully");	
			} 
			 else 
			 updateTipsNew(getErrorName(response.error),$j('#newPatientForm #errorDivNewPatient'),$j('#newPatientForm #errorDivHeader'));
		}
	 });

$j('#newPatientForm #btnNewPatientCancel').die('click').click(function(){
		removeErrors();
		 $j("#newPatientForm input[type=text]").val("");
		 $j("#newPatientForm input[type=password]").val("");	
         $j("#newPatientForm input[type=text],textarea").val("");		 
		 //$j("#check").val("");
		//$j('#check').removeAttr('checked');
		 //$j('#newPatientForm').trigger('reveal:close');	
	});	
});	