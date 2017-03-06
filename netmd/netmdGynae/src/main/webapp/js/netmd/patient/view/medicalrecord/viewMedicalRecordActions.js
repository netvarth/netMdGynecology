$j.cachedScript(constant_viewMedicalReport_Function).done(function(script, textStatus) {
})
$j(document).ready(function(){
$j('#medicalRecordViewForm #btnRecordEdit').die('click').live('click',function(){
//alert("Record Edit");
	var patientId=getPatientId();
	var patientInfo = getPatientData(patientId);
	$j('#pageTitle').html("Edit Medical Record Details"+'  '+'( '+patientInfo.firstName+' )');
	//$j('#pageTitle').html("Edit Medical Record Details");
	removeErrors();
	var medReportId = getMedRecordId();
	//alert(medReportId);
	ReportInfo = getMedReportData(medReportId);
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').hide();
	$j('#medicalRecordViewForm input[type=text] ,#medicalRecordViewForm textarea').removeClass('newBox'); 
	$j('#medicalRecordViewForm input[type=text],#medicalRecordViewForm textarea').removeAttr('readonly');
	clearNilFields('medicalRecordViewForm');
	$j('#btnRecordEdit').hide();
	$j('#btnRecordSave').show();
	$j('#btnRecordCancel').show();
	
});	 

$j('#medicalRecordViewForm #btnRecordSave').die('click').live('click',function(){
//alert("Save clicked");
	removeErrors();
		var response = submitMedicalReportInfoUrl();
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		if(response.success==true){
			var medReportId = getMedRecordIdForEdit();
			viewMedReportInfo(medReportId);
			restoreMedicalRecordInfo();
			$j('#pageTitle').html("View Medical Record");
			showTip("Medical Record updated successfully");
		} 
		else
		 updateTipsNew(getErrorName(response.error),$j('#medicalRecordViewForm #errorDivVwMedRecordData'),$j('#medicalRecordViewForm #errorDivHeader'));

		$j('#pageTitle').html("View Medical Record"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
		$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
});

$j('#medicalRecordViewForm #btnRecordCancel').die('click').live('click',function(){
    //alert("Cancel Clicked");
	removeErrors();
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
	var patientId=getPatientId();
	var patientInfo = getPatientData(patientId);
	var medReportId = getMedRecordIdForEdit();
	restoreMedicalRecordInfo();
	$j('#btnRecordSave').hide();
	$j('#btnRecordCancel').hide();
	$j('#btnRecordEdit').show();
	$j('#pageTitle').html("View Medical Record"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	//$j('#pageTitle').html("View Medical Record");
	return false;	

});	

$j("#patientMedReportPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
	$j.cachedScript(constant_newMedicalReport_Entry).done(function(script, textStatus) {
	})
	//$j('#pageTitle').html("View Patient");
	var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html(constant_PatientView_Msg+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});

$j("#patientMedReportPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
	removeErrors();
		var patientId=getPatientId();
		var medID = getMedRecordIdForEdit();
		var medIdforView = getpreviousMedId(medID,pgMRecordList);
		viewMedReportInfo(medIdforView,'#medRecordTbl');
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});
$j("#patientMedReportPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
	 removeErrors();
		var patientId=getPatientId();
		var medID = getMedRecordIdForEdit();
		var medIdforView = getnextMedId(medID,pgMRecordList);
		viewMedReportInfo(medIdforView,'#medRecordTbl');
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});

});	