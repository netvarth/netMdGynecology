
function validateViewDoctor(){
//alert("Validate");
/*validation using regular expression*/
	var name = $j("#doctorViewForm #firstname");
    var phone = $j("#doctorViewForm #landphone");
	var mobile=$j("#doctorViewForm #mobilephone");
	var specialization=$j("#doctorViewForm #specialization");
	var dob =$j("#doctorViewForm #dateofbirth");
		
   	var bValid=true,NameValid=true,phoneValid=true,Mobvalid=true,degValid=true,dobValid=true,NregValid=true,PhregValid=true;
  	NameValid = checkNull( name,constants_nameRequired);
	//degValid = checkNull( specialization,constants_designationRequired);
	//dobValid= checkNull( dob,constants_dobRequired);
	//phoneValid= checkNull( phone,constants_numberRequired);
	//mobValid= checkNull( mobile,constants_numberRequired);
	
	if((isEmpty(phone)))
	PhregValid= checkRegexp(phone,/^0?[1-9]{1}[0-9]{9}$/,constants_numberInvalidMessage,$j('#errorDivNewOrderData'));
	PhregValid=PhregValid&&NameValid;
	 
	if((isEmpty(dob)))
	dobValid= checkRegexp(dob,/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,constants_dobInvalidMessage,$j('#errorDivNewOrderData'));
	dobValid=dobValid&&PhregValid;
		
	 if((isEmpty(mobile)))
	Mobvalid= checkRegexp(mobile,/^[0123456789]\d{9}$/,constants_numberInvalidMessage,$j('#errorDivNewOrderData'));
	Mobvalid=Mobvalid&&PhregValid;
		bValid=bValid && Mobvalid; 

	return bValid;
}

function validateViewDoctorExp(){
	var frmDte=$j("#doctorViewForm #workBenchOne  #fromDate");
	var toDte=$j("#doctorViewForm #workBenchOne  #toDate");
	var desig=$j("#doctorViewForm #workBenchOne  #designations");
	var startDate = new Date($j('#doctorViewForm #workBenchOne #fromDate').val());
	var endDate = new Date($j('#doctorViewForm #workBenchOne #toDate').val());
	
	var bValid=true,frmDteValid=true,toDteValid=true,PhregValid=true;
	
	desigValid=checkNull( desig,constants_desigRequired);	
	frmDteValid=checkNull( frmDte,constants_fromdateRequired);
	toDteValid=checkNull( toDte,constants_toDateRequired);
	

	if((isEmpty(frmDte)))
	frmDteValid= checkRegexp(frmDte,/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,constants_ExpWorkbench_InvFDateMsg,$j('#errorDivNewOrderData'));
	frmDteValid=frmDteValid&&PhregValid;
	
	if((isEmpty(toDte)))
	toDteValid= checkRegexp(toDte,/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,constants_ExpWorkbench_InvTDateMsg,$j('#errorDivNewOrderData'));
	toDteValid=toDteValid&&PhregValid;
		bValid=bValid && toDteValid;
	
	

	if (startDate>endDate || startDate==endDate){
	createError("Please select valid dates",$j('#doctorViewForm #workBenchOne #toDate'));
		}
		
		
	return bValid;

	
}

function validateViewDoctorQual(){
	var Degree=$j("#doctorViewForm #workBenchTwo  #degrees");
	var passDte=$j("#doctorViewForm #workBenchTwo  #passoutDates");
	var Institution=$j("#doctorViewForm #workBenchTwo  #institutions");
	var bValid=true,insValid=true,passDteValid=true,degreeValid=true;
	
	degreeValid=checkNull( Degree,constants_degreeRequired);	
	passDteValid=checkNull( passDte,constants_passdateRequired);
	insValid=checkNull( Institution,constants_instRequired);
	
	if((isEmpty(passDte)))
	passDteValid= checkRegexp(passDte,/\d+/,constants_ExpWorkbench_InvpassDateMsg,$j('#errorDivNewOrderData'));
	passDteValid=passDteValid&&insValid;
		bValid=bValid && passDteValid;
	return bValid;
}

