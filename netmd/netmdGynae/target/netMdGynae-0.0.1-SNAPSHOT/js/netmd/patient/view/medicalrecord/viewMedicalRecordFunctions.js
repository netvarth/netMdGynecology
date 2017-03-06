// functions for  Med Report view
  
function viewMedReportInfo(medReportId) {
	var ReportInfo = getMedReportData(medReportId);
	//alert(JSON.stringify(ReportInfo));
    if(ReportInfo.id)
		$j("#medicalRecordViewForm #viewMedRecordId label").text(ReportInfo.id);
	else
		$j("#medicalRecordViewForm #viewMedRecordId label").text('Nil');
	if(ReportInfo.date)
		$j("#medicalRecordViewForm #viewRecordDate label").text(ReportInfo.date);
	else
		$j("#medicalRecordViewForm #viewRecordDate label").text('Nil');
	if(ReportInfo.type)
		$j("#medicalRecordViewForm #recordType label").text(ReportInfo.type);
	else
		$j("#medicalRecordViewForm #recordType label").text('Nil');
	if(ReportInfo.doctorName)
		$j("#medicalRecordViewForm #viewDoctorName label").text(ReportInfo.doctorName);
	else
		$j("#medicalRecordViewForm #viewDoctorName label").text('Nil');
	if(ReportInfo.caseId)
		$j("#medicalRecordViewForm #viewCaseIdMed label").text(ReportInfo.caseId);
	else
		$j("#medicalRecordViewForm #viewCaseIdMed label").text('Nil');	
	if(ReportInfo.medicalRecord)
		$j("#medicalRecordViewForm #viewMedicalRecord").val(br2nl(ReportInfo.medicalRecord));
	else
		$j("#medicalRecordViewForm #viewMedicalRecord").val('Nil');	
}

function getMedReportData(medReportId) {
	var response=getRequestData(constant_newMedReport_view_Url+medReportId);
	return response;
}

// Medical Report Edit Functions
function submitMedicalReportInfoUrl(){
    //alert("create med Report info");
	var resultJson = createSubmitJsonForMedicalReport();
	//alert(resultJson);
	var response = postdataToServer("/netmd/ws/ui/patient/updateMedicalRecord", resultJson );	
	//alert(JSON.stringify(response));
	return response;
	
	
}


function createSubmitJsonForMedicalReport(){
//alert("Create submit json m report");
        // GB_DOCID declared in index.js as Global Variable
		var patientId = getPatientId();
	    var patientDetails = '{'+'"id"'+':'+$j('#medicalRecordViewForm #viewMedRecordId label').text()+',';
		patientDetails += '"doctorId":'+GB_DOCID+',';
		patientDetails += '"patientId":'+patientId+',';
		patientDetails += '"caseId":'+$j('#medicalRecordViewForm #viewCaseIdMed label').text()+',';
		patientDetails +='"doctorName":"'+$j('#medicalRecordViewForm #viewDoctorName label').text()+'",';
		patientDetails +='"type":"'+$j('#medicalRecordViewForm #recordType label').text()+'",';
		patientDetails +='"date":"'+$j('#medicalRecordViewForm #viewRecordDate label').text()+'",';
		patientDetails +='"medicalRecord":"'+nl2br($j('#medicalRecordViewForm #viewMedicalRecord').val())+'"}';
		return patientDetails;
}

function restoreMedicalRecordInfo(){
	$j('#medicalRecordViewForm input[type=text],#medicalRecordViewForm textarea').addClass('newBox');
	$j('#medicalRecordViewForm input[type=text],#medicalRecordViewForm textarea').attr('readonly','readonly');
	$j('#btnRecordSave').hide();
	$j('#btnRecordCancel').hide();
	$j('#btnRecordEdit').show();
	$j('#patientMedReportPTBContainer #btn_up_ptb_id,#patientMedReportPTBContainer #btn_down_ptb_id,#patientMedReportPTBContainer #btn_back_ptb_id').show();
    $j('#pageToolBar-Container #btn_up_ptb_id,#pageToolBar-Container #btn_down_ptb_id,#pageToolBar-Container #btn_back_ptb_id').show();	
}

function getpreviousMedId(medID, pgMRecordList) {
	var patId;
	$j(pgMRecordList.medicalList).each(function (index, rowpatient) {
		if(medID==rowpatient.id)	{
			var arrayLength=(pgMRecordList.medicalList).length;
			var comp=arrayLength-1;
			if(index==0)
				patId = medID;
			else
				patId=pgMRecordList.medicalList[index-1].id;
		}
	});
	return patId;	
}
/* function to get the  next patientId from patients table*/
function getnextMedId(medID, pgMRecordList) {
	var patId;
	$j(pgMRecordList.medicalList).each(function (index, rowpatient) {
		if(medID==rowpatient.id)	{
			var arrayLength=(pgMRecordList.medicalList).length;
			var comp=arrayLength-1;
			if(index==comp){
				patId = medID;
			}else{
				patId=pgMRecordList.medicalList[index+1].id;
			}	
		}
	});	
	return patId;	
}


function getPatientId() {
	return $j("#patientViewForm #patientid label").text();
}

function getCaseId() {
	return $j("#caseViewForm #viewCaseId label").text();
}

function getMedRecordIdForEdit() {
	return $j("#medicalRecordViewForm #viewMedRecordId label").text();
}