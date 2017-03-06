$j.cachedScript(constants_viewCaseFunction).done(function(script, textStatus) {
})
$j(document).ready(function(){

$j('#caseViewForm #btnCaseEdit').die('click').live('click',function(){
	var patientId=getPatientId();
	var patientInfo = getPatientData(patientId);
	//$j('#pageTitle').html("Edit Case Details");
	$j('#pageTitle').html("Edit Case Details"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	removeErrors();
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').hide();
	$j('#caseViewForm input[type=text] ,#caseViewForm textarea').removeClass('newBox'); 
	$j('#caseViewForm input[type=text],#caseViewForm textarea').removeAttr('readonly');
	clearNilFields('caseViewForm');
	$j('#caseViewForm #btnCaseEdit').hide();
	$j('#caseViewForm #btnCaseSave').show();
	$j('#caseViewForm #btnCaseCancel').show();
	$j('#caseViewForm #CaseStatusLabelHead').hide();
	$j('#caseViewForm #CaseStatusSelectHead').show();
	fillCaseStatusType("#caseViewForm #CaseStatusSelect");
	
});

$j('#caseViewForm #btnCaseSave').die('click').live('click',function(){
		removeErrors();
		var response = submitCaseInfoUrl();
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		if(response.success==true){
			caseId = getCaseId();
			viewCaseInfo(caseId);
			restoreCaseInfo(caseId);
			//$j('#pageTitle').html("View Case");
			showTip("Case updated successfully");
		} 
		else
		 updateTipsNew(getErrorName(response.error),$j('#caseViewForm #errorDivVwCaseData'),$j('#caseViewForm #errorDivHeader'));

		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName+' )');
	    $j('#caseViewForm #CaseStatusSelectHead').hide();
		$j('#caseViewForm #CaseStatusLabelHead').show();
		$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
});

$j('#caseViewForm #btnCaseCancel').die('click').live('click',function(){
	removeErrors();
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
	var patientId=getPatientId();
	var patientInfo = getPatientData(patientId);
	caseId = getCaseId();
	restoreCaseInfo(caseId);
	$j('#caseViewForm #btnCaseSave').hide();
	$j('#caseViewForm #btnCaseCancel').hide();
	$j('#caseViewForm #btnCaseEdit').show();
	//$j('#pageTitle').html("View Case");
	$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	$j('#caseViewForm #CaseStatusSelectHead').hide();
	$j('#caseViewForm #CaseStatusLabelHead').show();
	return false;	

});	
	//back button click action
	$j("#patientCasePTBContainer #btn_back_ptb_id").die('click').live('click',function() {
		 $j.cachedScript(constant_newCaseEntryPoint).done(function(script, textStatus) {
		})
		//$j('#pageTitle').html("View Patient");
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html(constant_PatientView_Msg+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});
	
	//back button click action
	$j("#patientCasePTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var patientId=getPatientId();
		var caseID = getCaseId();
		var caseIdforView = getpreviousCaseId(caseID,pgpatientList);
		viewCaseInfo(caseIdforView,'#casetbl');
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});
	$j("#patientCasePTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		 removeErrors();
		var patientId=getPatientId();
		var caseID = getCaseId();
		var caseIdforView = getnextCaseId(caseID,pgpatientList);
		viewCaseInfo(caseIdforView,'#casetbl');
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});

});	
