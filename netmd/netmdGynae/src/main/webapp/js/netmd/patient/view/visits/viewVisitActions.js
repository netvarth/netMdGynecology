$j.cachedScript(constants_viewVisitFunction).done(function(script, textStatus) {
})
$j(document).ready(function(){
$j('#visitViewForm #btnVisitEdit').die('click').live('click',function(){
//alert("Record Edit");
	//$j('#pageTitle').html("Edit Visit Details");
	var patientId=getPatientId();
	var patientInfo = getPatientData(patientId);
	$j('#pageTitle').html("Edit Visit Details"+'  '+'( '+patientInfo.firstName+' )');
	removeErrors();
	var medReportId = getMedRecordId();
	//alert(medReportId);
	ReportInfo = getVisitData(medReportId);
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').hide();
	$j('#visitViewForm input[type=text] ,#visitViewForm textarea').removeClass('newBox'); 
	$j('#visitViewForm input[type=text],#visitViewForm textarea').removeAttr('readonly');
	clearNilFields('visitViewForm');
	$j('#visitViewForm #btnVisitEdit').hide();
	$j('#visitViewForm #btnVisitSave').show();
	$j('#visitViewForm #btnVisitCancel').show();
	
});	 

$j('#visitViewForm #btnVisitSave').die('click').live('click',function(){
	removeErrors();
		var response = submitVisitInfoUrl();
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		if(response.success==true){
			var medReportId = getMedRecordId();
			viewVisitInfo(medReportId);
			restoreVisitInfo();
		} 
		else
			 updateTipsNew(getErrorName(response.error),$j('#visitViewForm #errorDivVwVisitData'),$j('#visitViewForm #errorDivHeader'));
		//$j('#pageTitle').html("View Visit");
		$j('#pageTitle').html("View Visit"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
		showTip("Visit updated successfully");
});

$j('#visitViewForm #btnVisitCancel').die('click').live('click',function(){
    //alert("Cancel Clicked");
	removeErrors();
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
	var medReportId = getMedRecordId();
	var patientId=getPatientId();
	var patientInfo = getPatientData(patientId);
	restoreVisitInfo(medReportId);
	$j('#visitViewForm #btnVisitSave').hide();
	$j('#visitViewForm #btnVisitCancel').hide();
	$j('#visitViewForm #btnVisitEdit').show();
	$j('#pageTitle').html("View Visit"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	//$j('#pageTitle').html("View Visit");
	return false;	

});	

$j("#patientVisitPTBContainer #btn_back_ptb_id").die('click').live('click',function() {
	$j.cachedScript(constants_newVisitEntry).done(function(script, textStatus) {
	})
	//$j('#pageTitle').html("View Patient");
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html(constant_PatientView_Msg+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});

$j("#patientVisitPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
		removeErrors();
		var patientId=getPatientId();
		var visitId = getMedRecordId();
		var visitIdforView = getpreviousVisitId(visitId,pgVisitList);
		viewVisitInfo(visitIdforView,'#visitTable');
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});
$j("#patientVisitPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
		removeErrors();
		var patientId=getPatientId();
		var visitId = getMedRecordId();
		var visitIdforView = getnextVisitId(visitId,pgVisitList);
		viewVisitInfo(visitIdforView,'#visitTable');
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});

});