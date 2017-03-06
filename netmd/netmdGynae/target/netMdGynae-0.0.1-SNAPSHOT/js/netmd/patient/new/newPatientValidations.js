
function validateNewPatient(){
/*validation using regular expression*/
	var name = $j("#newPatientForm #firstName");
	var email = $j("#newPatientForm #email");
    var phone = $j("#newPatientForm #phone");
	var mobile=$j("#newPatientForm #mobile");
	var age =$j("#newPatientForm #newPatientAge");
	
   	var bValid=true,NameValid=true,EmailValid=true,phoneValid=true,ageValid=true,Mobvalid=true;
  	NameValid = checkNull( name,constants_nameRequired);
	ageValid= checkNull( age,constants_AgeRequired);
	EmailValid= checkNull( email,constants_emailRequired);
	//phoneValid= checkNull( phone,constants_phoneRequired);
	//Mobvalid= checkNull( mobile,constants_mobileRequired);
		
	if((isEmpty(phone)))
	phoneValid= checkRegexp(phone,/^0?[1-9]{1}[0-9]{9}$/,constants_phoneInvalidMessage,$j('#errorDivNewOrderData'));
	phoneValid=phoneValid&&NameValid;
	
	if((isEmpty(email)))
	EmailValid = checkRegexp( email, /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i, constants_emailInvalidMessage,$j('#errorDivNewOrderData') );
	EmailValid=EmailValid&&phoneValid;
	
	if((isEmpty(age)))
	phoneValid= checkRegexp(age,/\d+/,constants_AgeInvalidMessage,$j('#errorDivNewOrderData'));
	phoneValid=phoneValid&&NameValid;
	
	 if((isEmpty(mobile)))
	Mobvalid= checkRegexp(mobile,/^[0123456789]\d{9}$/,constants_mobileInvalidMessage,$j('#errorDivNewOrderData'));
	Mobvalid=Mobvalid&&EmailValid;
		bValid=bValid && Mobvalid; 

	return bValid;
}