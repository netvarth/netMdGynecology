$j(document).ready(function(){
	$j('#newDoctorForm #honorific').val("Dr.");

	$j('#newDoctorForm #firstname').bind("keypress",function(e){
		if(e.keyCode==13)
			$j("#newDoctorForm #dob").focus();
	});
	$j('#newDoctorForm #dob').bind("keypress",function(e){
		if(e.keyCode==13)
			$j("#newDoctorForm #phone").focus();
	});
	$j('#newDoctorForm #phone').bind("keypress",function(e){
		if(e.keyCode==13)
			$j("#newDoctorForm #mobile").focus();
	});
	$j('#newDoctorForm #mobile').bind("keypress",function(e){
		if(e.keyCode==13)
			$j("#newDoctorForm #designation").focus();
	});
	$j('#newDoctorForm #designation').bind("keypress",function(e){
		if(e.keyCode==13)
			$j("#newDoctorForm #email").focus();
	});
	$j('#newDoctorForm #email').bind("keypress",function(e){
	if(e.keyCode==13)
			$j("#newDoctorForm #password").focus();
	});
	$j("#newDoctorForm #password").bind("keypress", function (e) {
		if (e.keyCode == 13) 
			$j("#newDoctorForm #rePassword").focus();		
	});	
	$j("#newDoctorForm #rePassword").bind("keypress", function (e) {
		if (e.keyCode == 13) 
			$j('#newDoctorForm #btnNewDoctorCreate').trigger('click');		
	});
	$j("#newDoctorForm #btnNewDoctorCreate").bind("keypress", function (e) {
		if (e.keyCode == 13) 
			$j('#newDoctorForm #btnNewDoctorCancel').trigger('click');		
	});
    
	$j( "#dob" ).datepicker({
		changeMonth: true,
		changeYear: true,
		yearRange: '-100:+0'
	});
	
	//when focus is lost from email id it automatically fill the same value to the username
	$j('#newDoctorForm #email').die('focusout').live('focusout',function(){
		var Email=$j('#newDoctorForm #email').val();
		$j('#newDoctorForm #username').val(Email);
	});
	
	$j('#newDoctorForm #btnNewDoctorCreate').die('click').live("click",function() {	
		removeErrors();
		if(validateNewDoctor()) {
			var response = submitReferralInfo();
			//alert(JSON.stringify(response.error));
			if(response.success==true){
				$j('#newDoctorForm').trigger('reveal:close');
				$j.cachedScript(constant_ReferralEntry_Url).done(function(script, textStatus) {
				})
				fillDoctorAsAutoComplete("docSelect"); 
				showTip("Doctor created successfully.");  			
			} else 
				updateTipsNew(getErrorName(response.error),$j('#newDoctorForm #errorDivNewDoctor'),$j('#newDoctorForm #errorDivHeader'));
		}
	}); 
	
	$j('#newDoctorForm #btnNewDoctorCancel').die('click').click(function(){
		removeErrors();
        $j("#newDoctorForm input[type=text],textarea").val("");		 
		$j('#check').removeAttr('checked');
		$j('#newDoctorForm #honorific').val("Dr.");
		
	});	

	
});	


	


