
function submitVisitInfo(){
	var visitDetails = createSubmitVisitJson();
	var response = postdataToServer(constant_newMedReport_Create_Url,visitDetails);	
	return response;	
}

function createSubmitVisitJson(){
	var patientId = getPatientId();
	if(usertype!="doctor"){
		var doctorname=$j('#docSelect').val();
		GB_DOCID =getDoctorIdUsingName(doctorname);
	}
	var caseId = $j('#newVisitForm  #caseName').val();
	if(GB_DOCID=="false"&&usertype!="doctor"){
		updateTipsNew("Please select the doctor from Global select box in home page",$j('#newVisitForm #errorDivNewVisit'),$j('#newVisitForm #errorDivHeader'));
	}
	var patientDetails = '{'+'"doctorId"'+':'+GB_DOCID+',';
	patientDetails += '"patientId":'+patientId +',';
	patientDetails += '"caseId":'+caseId +',';
	patientDetails +='"type":"'+$j('#newVisitForm  #mrType').val()  +'",';
	patientDetails +='"date":"'+""+'",';
	patientDetails +='"medicalRecord":"'+nl2br($j('#newVisitForm #medicalRecord').val())  +'"}';
	return patientDetails;
}

function fillVisitTable(visitListJson,tableObjTwo) {
	var patientId = getPatientId();
	$j(tableObjTwo).dataTable().fnClearTable();
	listVisit= getRequestData(constant_VisitList_Url+patientId);
	listVisit= postdataToServer(constant_MedReport_list_URL,visitListJson);
	if(listVisit.medicalList.length>0) {	
		$j(listVisit.medicalList).each(function (patientIndex,visitObj) {
			var rowData=$j(tableObjTwo).dataTable().fnAddData([visitObj.id,visitObj.date,visitObj.type,visitObj.medicalRecord.slice(0,35)]);
			var row=$j(tableObjTwo).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).attr('id',visitObj.id);	
			$j(row).children("td:nth-child(1)").attr("class","visitCol Ustyle");
		});	
	}
	return  listVisit;
 }

function resetVisitForm() {
	removeErrors();
	$j("#newVisitForm input[type=text], textarea").val("");
    $j("#newVisitForm  input[type=text],#newVisitForm input[type=password]").val("");
	$j("#doctorName").focus();
}

function getPatientId() {
	return $j("#patientViewForm #patientid label").text();
}

function getCaseId() {
	return $j("#caseViewForm #viewCaseId label").text();
}

function getMedRecordId() {
	return $j("#visitViewForm #viewVisitId label").text();
}

function getExpressionOne(){
var patientId=getPatientId();
	var listJsonForDoctor='{"name":"doctorId","value":"'+GB_DOCID+'","operator":"eq"}'+','+
	'{"name":"patientId","value":"'+patientId+'","operator":"eq"}'+','+
	'{"name":"type","value":"Personal visit","operator":"eq" }';
	return listJsonForDoctor;
}
