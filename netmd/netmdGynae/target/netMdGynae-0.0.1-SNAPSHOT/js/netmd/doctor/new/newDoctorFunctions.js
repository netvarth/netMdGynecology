function submitReferralInfo(){
	var loginData = getLoginDetail();
	var loginDataOne='"login":'+loginData+"}";
	var finalDoctorDetails = createSubmitJson();
	var resultJson = finalDoctorDetails+loginDataOne;
	var response = postdataToServer(constant_newReferral_Create_Url, resultJson );	
	return response;	
}

function createSubmitJson(){
	var name=$j('#newDoctorForm #firstname').val();
	name= name.replace(/\b[a-z]/g, function(letter) {
		return letter.toUpperCase();
	});
    DoctorAddress = nl2br($j('#newDoctorForm #address').val());
	var doctorDetails = '{"firstName":"'+ name +'",';
		doctorDetails += '"lastName":"'+ $j('#newDoctorForm #lastname').val() +'",';
		doctorDetails +='"dateOfBirth":"' + $j('#newDoctorForm #dob').val() +'",';
		doctorDetails +='"gender":"' + $j("#newDoctorForm input[name='gender']:checked").val() + '",';
		doctorDetails +='"workHistory":"' +""  + '",';
		doctorDetails +='"mobile":"' + $j('#newDoctorForm #mobile').val() + '",';
		doctorDetails +='"phone":"' + $j('#newDoctorForm #phone').val() + '",';
		doctorDetails +='"email":"' + $j('#newDoctorForm #email').val() + '",';
		doctorDetails +='"address":"' + DoctorAddress + '",';
		doctorDetails +='"workingPlaces":"' + "" + '",';
		doctorDetails +='"designation":"' + ""+'",';
		doctorDetails +='"specialization":"' +$j('#newDoctorForm #specialization').val()  +'",';
		doctorDetails +='"consultationInterval":"' +"" +'",';
	return doctorDetails;

}

function getLoginDetail(){
	var check=$j('#check').attr("checked");
	var check_one="";
	if(check=="checked")
		check_one = "admin";
	else
		check_one ="doctor";
	var getLoginDetailData = '{"password":"'+""+'",';
	getLoginDetailData = '{"password":"'+""+'",';
	getLoginDetailData += '"userName":"'+ $j('#username').val()+'",';
	getLoginDetailData +='"userType":"' +check_one +'"';
	getLoginDetailData += '}';
	return getLoginDetailData;  
}

function resetNewReferralForm() {
	removeErrors();
	$j("#newDoctorForm input[type=text], textarea").val("");
    $j("#newDoctorForm  input[type=text],#newDoctorForm input[type=password]").val("");
    $j('#check').removeAttr('checked');
	$j("#newDoctorName").focus();
}
		