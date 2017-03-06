
function validateNewDoctor(){
	var name = $j("#newDoctorForm #firstname");
	var email = $j("#newDoctorForm #email");
    var phone = $j("#newDoctorForm #phone");
	var mobile=$j("#newDoctorForm #mobile");
	var specialization=$j("#newDoctorForm #specialization");
	var dob =$j("#newDoctorForm #dob");
	
   	var bValid=true,PwdCompValid=true,NameValid=true,emailValid=true,phoneValid=true,mobValid=true,degValid=true,dobValid=true,NregValid=true,PhregValid=true,EmailValid=true,Mobvalid=true;
  	NameValid = checkNull( name,constants_nameRequired);
	degValid = checkNull( specialization,constants_designationRequired);
	dobValid= checkNull( dob,constants_dobRequired);
	EmailValid= checkNull( email,constants_emailRequired);
	
	if((isEmpty(phone)))
	PhregValid= checkRegexp(phone,/^0?[1-9]{1}[0-9]{9}$/,constants_numberInvalidMessage,$j('#errorDivNewOrderData'));
	PhregValid=PhregValid&&NameValid;
	
	if((isEmpty(dob)))
	dobValid= checkRegexp(dob,/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,constants_dobInvalidMessage,$j('#errorDivNewOrderData'));
	dobValid=dobValid&&PhregValid;
	
	if((isEmpty(email)))
	EmailValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_emailInvalidMessage,$j('#errorDivNewOrderData') );
	EmailValid=EmailValid&&PhregValid;
	
	if((isEmpty(mobile)))
	Mobvalid= checkRegexp(mobile,/^[0123456789]\d{9}$/,constants_numberInvalidMessage,$j('#errorDivNewOrderData'));
	Mobvalid=Mobvalid&&EmailValid;

	bValid=bValid && Mobvalid;

	return bValid;
}