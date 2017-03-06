

function fillMedReportType(controlObj) {
	$j(controlObj).empty();
	$j(controlObj).append('<option value="Prescription">Prescription</option><option value="Doctor rounds">Doctor rounds</option><option value="Email">Email</option><option value="Phone">Phone</option><option value="Other">Other</option>');
} 
function fillMedReportCaseName(controlObj) {
	var patientId = getPatientId();
	listPatient= getRequestData(constant_newCase_list_Url+patientId);	
	$j(listPatient.caseList).each(function (patientIndex, patientObj) {
		if(patientObj.caseStatus=="Open")
			$j(controlObj).append('<option value="'+patientObj.id+'">'+patientObj.caseName+'</option>');
	});	
 }
 
function submitMedReportInfo(){
	var medReportDetails = createSubmitMedReportJson();
	var response = postdataToServer(constant_newMedReport_Create_Url,medReportDetails);	
	return response;	
}

function createSubmitMedReportJson(){
	var patientId = getPatientId();
	if(usertype!="doctor"){
		var doctorname=$j('#docSelect').val();
	   GB_DOCID =getDoctorIdUsingName(doctorname);
	}
	if(GB_DOCID=="false"&& usertype!="doctor")
		updateTipsNew("Please select the doctor from Global select box in home page",$j('#newMedicalRecordForm #errorDivNewMRecord'),$j('#newMedicalRecordForm #errorDivHeader'));
	var caseId = $j('#caseName').val();
	var patientDetails = '{'+'"doctorId"'+':'+GB_DOCID+',';
	patientDetails += '"patientId":'+patientId +',';
	patientDetails += '"caseId":'+caseId +',';
	patientDetails +='"type":"'+$j('#newMedicalRecordForm #mrType').val()  +'",';
	patientDetails +='"date":"'+""+'",';
	patientDetails +='"medicalRecord":"'+nl2br($j('#newMedicalRecordForm #medicalRecord').val())  +'"}';
	return patientDetails;
}

function fillMedReportTable(medReportListJson,tableObjOne) {
	var patientId = getPatientId();
	$j(tableObjOne).dataTable().fnClearTable();
	listmedReport= postdataToServer(constant_MedReport_list_URL,medReportListJson);
	if(listmedReport.medicalList.length>0) {	
		$j(listmedReport.medicalList).each(function (patientIndex, mRecordObj) {
			var rowData=$j(tableObjOne).dataTable().fnAddData([mRecordObj.id,mRecordObj.date,mRecordObj.type,mRecordObj.medicalRecord.slice(0,25)]);
			var row=$j(tableObjOne).dataTable().fnSettings().aoData[rowData].nTr;
			$j(row).attr('id',mRecordObj.id);	
			$j(row).children("td:nth-child(1)").attr("class","mRecordIdCol Ustyle");
		});					
	}
	return  listmedReport;
 }


 
function getExpression(){
var patientId=getPatientId() ;
	var listJsonForDoctor='{"name":"doctorId","value":"'+GB_DOCID+'","operator":"eq"}'+','+
	'{"name":"patientId","value":"'+patientId+'","operator":"eq"}'+','+
	'{"name":"type","value":"Personal visit","operator":"neq" }';
	return listJsonForDoctor;
}

function resetnewMedReportForm() {
	removeErrors();
	$j("#newMedicalRecordForm input[type=text], textarea").val("");
    $j("#newMedicalRecordForm  input[type=text],#newMedicalRecordForm input[type=password]").val("");
	$j("#doctorName").focus();
}	 
