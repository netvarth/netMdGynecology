
$j(document).ready(function(){
//back button click action
$j("#patientGeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
	$j.cachedScript(constant_PatientEntryPoint_Url).done(function(script, textStatus) {
		})
});

//up button click action
$j("#patientGeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
	removeErrors();
	var patientId = getPatientId();
	var patientIdforView = getpreviousPatientId(patientId,pgPatientList);
	viewPatientInfo(patientIdforView,'#patients');
	var patientInfo = getPatientData(patientIdforView);
	$j('#pageTitle').html("View Patient"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	
});

//down button click action
$j("#patientGeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
	removeErrors();
	var patientId = getPatientId();
	var patientIdforView = getnextPatientId(patientId,pgPatientList);
	viewPatientInfo(patientIdforView,'#patients');	
	var patientInfo = getPatientData(patientIdforView);
	$j('#pageTitle').html("View Patient"+'  '+'( '+patientInfo.firstName+' )');
	
}); 
	
// Patient Info Edit click actions
$j('#patientViewBtnEditOne').die('click').live('click',function(){
	removeErrors();
	var patientId = getPatientId();
	patientInfo = getPatientData(patientId);
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').hide();
	$j('#patientPersonalDiv input[type=text] ,#patientPersonalDiv textarea').removeClass('newBox'); 
	$j('#patientPersonalDiv input[type=text],#patientPersonalDiv textarea').removeAttr('readonly');
	clearNilFields('patientPersonalDiv');
	$j('#patientViewBtnEditOne').hide();
	$j('#patientViewBtnSave').show();
	$j('#patientViewBtnCancel').show();
	$j('#pageTitle').html("Edit Patient Details"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
//Edit Gender Section
	$j('#viewGender').hide();
	$j('#lblPatientEditGender').attr('style','display:""');
	if(patientInfo.gender=="Male")
	$j("#lblViewPatientGenderMale").attr('checked','true');
	else
	$j("#lblViewPatientGenderFemale").attr('checked','true');
});

$j('#patientViewBtnCancel').die('click').live('click',function(){
	removeErrors();
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
	patientId = getPatientId();
	viewPatientInfo(patientId);
	restorePatientInfo(patientId);
	clearNilFields('patientPersonalDiv');
	$j('#patientViewBtnSave').hide();
	$j('#patientViewBtnCancel').hide();
	$j('#patientViewBtnEditOne').show();
	$j('#pageTitle').html("View Patient"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	return false;
});

$j('#patientViewBtnSave').die('click').live('click',function(){
		removeErrors();
	if(validateViewPatient()) {
		var response = submitPatientInfo();
		if(response.success==true){
			patientId = getPatientId();
			viewPatientInfo(patientId);
			restorePatientInfo();
			clearNilFields('patientPersonalDiv');
			$j('#pageTitle').html("View Patient"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
			showTip("Patient updated successfully");
		} 
		else{
		 if(response.error.errCode=="078")
				showTip("This is not a Primary device.");
			}  
	}
});

});