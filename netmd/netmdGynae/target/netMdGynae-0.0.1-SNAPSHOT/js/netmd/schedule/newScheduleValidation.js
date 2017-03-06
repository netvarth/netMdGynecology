
function validateNewSchedule(){
	var strtdte = $j("#newScheduleForm #Startdate");
	var frmTime= $j("#newScheduleForm #from");
    var toTime = $j("#newScheduleForm #to");
	var enddate=$j("#newScheduleForm #datepickerOne");
	var frmTime1= $j("#newScheduleForm #from").val();
	var toTime1 = $j("#newScheduleForm #to").val();
	
   	var bValid=true,strtdteValid=true,frmTimeValid=true,toTimeValid=true,enddateValid=true;
  	strtdteValid = checkNull( strtdte,constants_StartDateRequired);
	frmTimeValid = checkNull( frmTime,constants_FrmtimeRequired);
	toTimeValid = checkNull( toTime,constants_totimeRequired);

	if((isEmpty(strtdte)))
		strtdteValid= checkRegexp(strtdte,/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,constants_startdateInvalidMessage,$j('#errorDivNewOrderData'));
	strtdteValid=strtdteValid&&frmTimeValid;
	
	if((isEmpty(enddate)))
		enddateValid= checkRegexp(enddate,/^[0-9]{4}-[0-9]{2}-[0-9]{2}$/,constants_enddateInvalidMessage,$j('#errorDivNewOrderData'));
	enddateValid=enddateValid&&strtdteValid;
 
	// Time validation
	startTime = minFromMidnight(frmTime1);
	endTime = minFromMidnight(toTime1);
	if(startTime=="1470" && endTime=="780")
		bValid=true
	else if(startTime=="750" && endTime=="60")
		bValid=true
	else{
		if(startTime>endTime || startTime==endTime){
			createError("End time must be greater than start time",$j('#newScheduleForm #to'));
			bValid=false;
		}
		
     }
 	return bValid;
}
function minFromMidnight(tm){
	var amORpm= tm.substr(-2)
	var clk = tm.substr(0, 5);
	var m  = parseInt(clk.match(/\d+$/)[0], 10);
	var h  = parseInt(clk.match(/^\d+/)[0], 10);
	h += (amORpm.match(/pm/i))? 12: 0;
	return h*60+m;
}