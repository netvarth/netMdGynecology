
$j(document).ready(function(){
	var schedule;
	var StrtTime;
	var appointmentid=getAppointmentId('.fc-agenda-slots');
	var appdate=getAppoinmentDate();
	viewAppointment(appdate,appointmentid);
	
$j('#vwEditAppointmentForm #appbtnEdit').die('click').live("click",function() {	
	fillpatientAsAutoComplete("#vwEditAppointmentForm #appPatient");
	$j('#vwEditAppointmentForm #appointmentlabel').hide();
	$j('#vwEditAppointmentForm #appbtnEdit').hide();
	$j('#vwEditAppointmentForm #appointmentselect').show();
	$j('#vwEditAppointmentForm #appbtnDone').show();
});	

$j('#vwEditAppointmentForm #appbtnDone').die('click').live("click",function() {	
	var appoinmentViewResponse=submitViewAppointmentInfo();
	if(appoinmentViewResponse.success==true){
		var appid=appoinmentViewResponse.id;
		var time=appoinmentViewResponse.startTime;
		var dateformat=ampmConversion(time);	
		$j('.fc-agenda-slots tr th').each(function() {
			var timeslot=$j(this).text();
			if(timeslot==dateformat){
				var patName=$j('#vwEditAppointmentForm #appPatient').val();
				var patientName=patName.split("[")[0];
				$j(this).nextAll('td').children('div').text(patientName);
				$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
                $j(this).nextAll('td:first').attr('value',appid);
				$j('tr td[selected]').removeAttr('style');
				$j('tr td').removeAttr('selected');
			}		
						
		});
	}	
	else 
		updateTipsNew(getErrorName(response.error),$j('#vwEditAppointmentForm #errorDivVwAppmtData'),$j('#vwEditAppointmentForm #errorDivVwAppmtData'));
	
	$j('#vwEditAppointmentForm #appointmentselect').hide();
	$j('#vwEditAppointmentForm #appointmentlabel').show();
	$j('#vwEditAppointmentForm #appbtnDone').hide();
	$j('#vwEditAppointmentForm #appbtnEdit').show(); 
	viewAppointment(appdate,appointmentid);
	});	
	
});		
/*---------functions------------------------------------*/	
function getAppointmentId(pgTableName){
	var appointmentId="";
	var selappointment = $j(pgTableName + ' tbody tr td[selected]');
	if(selappointment.length==0)
		updateTips("Select atleast one appointment",$j('#errorDivHeader'));
	else if(selappointment.length>1)
		updateTips("Select only one appointment",$j('#errorDivHeader'));
	else
	appointmentId=selappointment.val();
	return appointmentId;
}	
function viewAppointment(day,appointmentid){
	var responseapp=getRequestData('/netmd/ws/ui/appointment/view/' + GB_DOCID + ',' +day);	
	$j(responseapp.appointment).each(function(index,data) {
		var appId=data.id;
		starttme=data.startTime;
		StrtTime=ampmConversion(starttme);
		schedule=data.scheduleId;	
		if(appointmentid==appId){
			if(data.patientName)
				$j("#vwEditAppointmentForm #appointmentPatient label").text(data.patientName+" "+data.patientLastName);
			else
				$j("#vwEditAppointmentForm #appointmentPatient label").text("Nil");
		}
	});
}		
function submitViewAppointmentInfo(){
	var resultJson = createSubmitJson();
	var response = postdataToServer(constant_appointment_update_Url, resultJson );
	return response;
}

function createSubmitJson(){
	var Apptbl='.fc-agenda-slots';
		var appointmentid=getAppointmentId('.fc-agenda-slots');
		
		var patient=$j('#vwEditAppointmentForm #appPatient').val()
		var newPatientName=patient.split("[")[0];
		var patID=getPatientIdUsingName(newPatientName);
		var selecteddate=$j('.fc-header-title h2').text();
		var datesplit=selecteddate.split(',');
		var date=datesplit[1];
			date=$j.trim(date);
		var appointmentDetails = '{"id":'+appointmentid+',';
			appointmentDetails += '"patientId":'+patID+',';
			appointmentDetails += '"doctorId":'+ GB_DOCID +',';
			appointmentDetails +='"scheduleId":' +schedule+',';
			appointmentDetails +='"startTime":"' +StrtTime+ '",';
			appointmentDetails +='"startDate":"'+date+'",';
			appointmentDetails +='"patientName":"' +$j('#vwEditAppointmentForm #appPatient').val() + '"';
			appointmentDetails += '}';
		return appointmentDetails;

	}
