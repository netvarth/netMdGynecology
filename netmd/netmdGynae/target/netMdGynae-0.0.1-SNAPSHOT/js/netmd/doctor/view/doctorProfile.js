
function viewDoctor(user) {
	if(user.ownerId!=null){
		ownerInfo = getOwnerData(user.id);
		viewdoctorInfo(ownerInfo);
	}else {	
		doctorInfo = getDoctorData(user.doctorId);	
		viewdoctorInfo(doctorInfo);
	}
}

function viewdoctorInfo(Info) {
	if(Info.id)
		$j("#doctorProfViewForm #doctorid label").text(Info.id);
	else
		$j("#doctorProfViewForm #doctorid label").text('Nil');
	if(Info.firstName)
		$j("#doctorProfViewForm #firstname").val(Info.firstName);
	else
		$j("#doctorProfViewForm #firstname").val('Nil');
	if(Info.lastName)
		$j("#doctorProfViewForm #lastname").val(Info.lastName);
	else
		$j("#doctorProfViewForm #lastname").val('Nil');
	if(Info.phone)
		$j("#doctorProfViewForm #landphone").val(Info.phone);
	else 
		$j("#doctorProfViewForm #landphone").val('Nil');
	if(Info.mobile)
		$j("#doctorProfViewForm #mobilephone").val(Info.mobile);
	else 
		$j("#doctorProfViewForm #mobilephone").val('Nil');
	if(Info.gender)
	    
		$j("#doctorProfViewForm #lblViewDoctorGender label").text(Info.gender);	
	else 
		$j("#doctorProfViewForm #lblViewDoctorGender label").text('Nil');
	if(Info.email)
		$j("#doctorProfViewForm #email label").text(Info.email);
	else 
		$j("#doctorProfViewForm #email label").text('Nil');
	if(Info.dateOfBirth)
		$j("#doctorProfViewForm #dateofbirth").val(Info.dateOfBirth);
	else 
		$j("#doctorProfViewForm #dateofbirth").val('Nil');
	if(Info.speciality)
		$j("#doctorProfViewForm #doctorspeciality").val(Info.speciality);
	else
		$j("#doctorProfViewForm #doctorspeciality").val('Nil');
	if(Info.address)
		$j("#doctorProfViewForm #address").val(br2nl(Info.address));
	else 
		$j("#doctorProfViewForm #address").val('Nil');
	if(Info.specialization)
		$j("#doctorProfViewForm #specialization").val(Info.specialization);
	else 
		$j("#doctorProfViewForm #specialization").val('Nil');

		
}

function getDoctorData(doctorId) {
	var response=getRequestData("/netmd/ws/ui/doctor/view/" + doctorId);
	return response;
}
function getOwnerData(id){
  var response=getRequestData("/netmd/ws/ui/owner/view/" + id);
	return response;
}

