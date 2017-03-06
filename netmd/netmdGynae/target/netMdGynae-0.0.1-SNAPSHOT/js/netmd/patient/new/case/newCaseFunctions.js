function submitCaseInfo(){
	var caseDetails = createSubmitJson();
	var response = postdataToServer(constant_newCase_Create_Url,caseDetails);	
	return response;	
}
function createSubmitJson(){
    var patientId = getPatientId();
	var patientDetails = '{"caseName":"'+$j('#CaseName').val()  +'",';
	patientDetails += '"patientId":'+patientId +',';
	patientDetails +='"shortDesc":"'+nl2br($j('#shortDescription').val())  +'",';
	patientDetails +='"caseStatus":"'+"Open"+'",';
	patientDetails +='"date":"'+""+'",';
	patientDetails +='"longDesc":"'+nl2br($j('#longDescription').val())  +'"}';
	return patientDetails;
}



function validateNewCase(){
	var casename = $j("#newCaseForm #CaseName");
	var shortdesc = $j("#newCaseForm #shortDescription");
    var longdesc = $j("#newCaseForm #longDescription");
	
   	var bValid=true,casenameValid=true,shortdescValid=true,longdescValid=true;
	
  	casenameValid = checkNull( casename,constants_casenameRequired);
	//shortdescValid= checkNull( shortdesc,constants_shortdescRequired);
	//longdescValid= checkNull( longdesc,constants_longdescRequired);
	return bValid;
}

 
 
 

 