function submitPatientInfo(){
	var finalpatientDetails = createSubmitJson();
	//alert(finalpatientDetails);
	var response = postdataToServer(constant_newPatient_Create_Url,finalpatientDetails);	
	//alert(JSON.stringify(response));
	return response;
	
	
}

function createSubmitJson(){
    var age=$j('#newPatientForm #newPatientAge').val();
	if(age=="")
		age=0;
	
var name=$j('#newPatientForm #firstName').val();
name= name.replace(/\b[a-z]/g, function(letter) {
	return letter.toUpperCase();
})
	
     PatientAddress = nl2br($j('#Address').val());
	var patientDetails = '{"firstName":"'+ name +'",';
		patientDetails += '"lastName":"'+ $j('#newPatientForm #lastName').val() +'",';
		patientDetails +='"age":' + age +',' ;
		patientDetails +='"gender":"' + $j("#newPatientForm input[name='gender']:checked").val() + '",';
		patientDetails +='"mobile":"' + $j('#newPatientForm #mobile').val() + '",';
		patientDetails +='"phone":"' + $j('#newPatientForm #phone').val() + '",';
		patientDetails +='"email":"' + $j('#newPatientForm #email').val() + '",';
		patientDetails +='"ailment":"",';
		patientDetails +='"diagnosis":"",';
		//patientDetails +='"occupation":"",';
		patientDetails +='"address":"' + PatientAddress + '",';
		//patientDetails +='"medicalHistory":"' +$j('#medicalHistory').val()  +'",';
		patientDetails +='"medicalHistory":[]}';
		return patientDetails;

}

function resetnewPatientForm() {
	removeErrors();
	$j("#newPatientForm input[type=text], textarea").val("");
    $j("#newPatientForm  input[type=text],#newPatientForm input[type=password]").val("");
    $j('#check').removeAttr('checked');
	$j("#firstName").focus();
}