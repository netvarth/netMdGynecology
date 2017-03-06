function viewVisitInfo(medReportId) {
var visitInfo = getVisitData(medReportId);
    if(visitInfo.id)
		$j("#visitViewForm #viewVisitId label").text(visitInfo.id);
	else
		$j("#visitViewForm #viewVisitId label").text('Nil');
	if(visitInfo.date)
		$j("#visitViewForm #viewVisitDate label").text(visitInfo.date);
	else
		$j("#visitViewForm #viewVisitDate label").text('Nil');
	if(visitInfo.type)
		$j("#visitViewForm #recordType label").text(visitInfo.type);
	else
		$j("#visitViewForm #recordTypelabel ").text('Nil');
	if(visitInfo.doctorName)
		$j("#visitViewForm #viewDoctorName label").text(visitInfo.doctorName);
	else
		$j("#visitViewForm #viewDoctorName label").text('Nil');
	if(visitInfo.caseId)
		$j("#visitViewForm #viewCaseIdVisit label").text(visitInfo.caseId);
	else
		$j("#visitViewForm #viewCaseIdVisit label").text('Nil');	
	if(visitInfo.medicalRecord)
		$j("#visitViewForm #viewVisitDescription").val(br2nl(visitInfo.medicalRecord));
	else
		$j("#visitViewForm #viewVisitDescription").val('Nil');	
}

function getVisitData(medReportId) {
	var response=getRequestData(constant_newMedReport_view_Url+medReportId);
	return response;
}

// Medical Report Edit Functions
function submitVisitInfoUrl(){
	var resultJson = createSubmitJsonForVisit();
	//alert(resultJson);
	var response = postdataToServer("/netmd/ws/ui/patient/updateMedicalRecord", resultJson );	
	//alert(JSON.stringify(response));
	return response;
	
	
}


function createSubmitJsonForVisit(){
        // GB_DOCID declared in index.js as Global Variable
		var patientId = getPatientId();
	    var patientDetails = '{'+'"id"'+':'+$j('#visitViewForm #viewVisitId label').text()+',';
		patientDetails += '"doctorId":'+GB_DOCID+',';
		patientDetails += '"patientId":'+patientId+',';
		patientDetails += '"caseId":'+$j('#visitViewForm #viewCaseIdVisit label').text()+',';
		patientDetails +='"doctorName":"'+$j('#visitViewForm #viewDoctorName label').text()+'",';
		patientDetails +='"type":"'+$j('#visitViewForm #recordType label').text()+'",';
		patientDetails +='"date":"'+$j('#visitViewForm #viewVisitDate label').text()+'",';
		patientDetails +='"medicalRecord":"'+nl2br($j('#visitViewForm #viewVisitDescription').val())+'"}';
		return patientDetails;
}

function restoreVisitInfo(){
	$j('#visitViewForm input[type=text],#visitViewForm textarea').addClass('newBox');
	$j('#visitViewForm input[type=text],#visitViewForm textarea').attr('readonly','readonly');
	$j('#btnVisitSave').hide();
	$j('#btnVisitCancel').hide();
	$j('#btnVisitEdit').show();
	$j('#patientVisitPTBContainer #btn_up_ptb_id,#patientVisitPTBContainer #btn_down_ptb_id,#patientVisitPTBContainer #btn_back_ptb_id').show();
    $j('#pageToolBar-Container #btn_up_ptb_id,#pageToolBar-Container #btn_down_ptb_id,#pageToolBar-Container #btn_back_ptb_id').show();	
	}
	
function getCaseId() {
	return $j("#caseViewForm #viewCaseId label").text();
}

function getMedRecordId() {
	return $j("#visitViewForm #viewVisitId label").text();
}	

function getPatientId() {
	return $j("#patientViewForm #patientid label").text();
}

function getpreviousVisitId(visitId, pgVisitList) {
	var patId;
	$j(pgVisitList.medicalList).each(function (index, rowpatient) {
		if(visitId==rowpatient.id)	{
			var arrayLength=(pgVisitList.medicalList).length;
			var comp=arrayLength-1;
			if(index==0)
				patId = visitId;
			else
				patId=pgVisitList.medicalList[index-1].id;
		}
	});
	return patId;	
}
/* function to get the  next patientId from patients table*/
function getnextVisitId(visitId, pgVisitList) {
	var patId;
	$j(pgVisitList.medicalList).each(function (index, rowpatient) {
		if(visitId==rowpatient.id)	{
			var arrayLength=(pgVisitList.medicalList).length;
			var comp=arrayLength-1;
			if(index==comp){
				patId = visitId;
			}else{
				patId=pgVisitList.medicalList[index+1].id;
			}	
		}
	});	
	return patId;	
}