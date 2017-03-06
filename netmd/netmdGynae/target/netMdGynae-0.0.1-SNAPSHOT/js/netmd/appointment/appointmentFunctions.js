$j(document).ready(function(){
	var timestart;
	var scheduleId;
	var patientDetails=getPatientDetailsForAppoinment();
	methodInvoker.fillPatientDetailsToControl("#newAppointmentForm #Patient",patientDetails);
});	
	$j('#newAppointmentForm #btnDone').die('click').live("click",function() {
		var patient=$j('#newAppointmentForm #Patient').val();
		var newPatientName=patient.split("[")[0];
		//$j('#newAppointmentForm #Patient').val(newPatientName);
		 var response = submitAppointmentDetails();
		if(response.success==true){	
			$j('.fc-agenda-slots tr th').each(function() {
				var timeslot=$j(this).text();
				var startTime=response.startTime;
				var startTimeConverted=ampmConversion(startTime);
				if(timeslot==startTimeConverted){
					$j(this).nextAll('td:first').children('div').text(response.patientFirstName+" "+response.patientLastName);
					$j(this).nextAll('td:first').attr('value', response.id);
					$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
				}
			});
			$j('#newAppointmentForm').trigger('reveal:close');
			showTip("Appointment created successfully");
		}
		else
			updateTipsNew(getErrorName(response.error),$j('#newAppointmentForm #errorDivNewAppmt'),$j('#newAppointmentForm #errorDivHeader'));
	});
	
function getPatientDetailsForAppoinment(){
	ajaxProcessor.setUrl(constants.GETPATIENTDETAILS);
	return ajaxProcessor.get();
}

function submitAppointmentDetails(){
	var resultJson = createJsonForAppoinment();
	var response = postdataToServer(constant_appointment_create_Url, resultJson );
	return response;
}
function createJsonForAppoinment(){	
	var patientName=$j('#newAppointmentForm #Patient').val();
	if(patientName.indexOf('[')==-1){
			newPatientName=patientName;
			originaldata=patientName;	
		}
		else{
			newPatientName=patientName.split("[")[0];
			newPatientName=$j.trim(newPatientName);
			var data=patientName.split("[")[1];
			originaldata=data.split("]")[0];
		}
	
	var patientDetails=getPatientDetailsForAppoinment();	
	var patientDetail=methodInvoker.getPatientDetails(originaldata,newPatientName,patientDetails);
	var patID=patientDetail.id;
	var selecteddate=$j('.fc-header-title h2').text();
	var datesplit=selecteddate.split(',');
	var date=datesplit[1];
	var startdate=$j.trim(date);
	var appointmentDetails = '{"patientId":'+patID+',';
	appointmentDetails += '"doctorId":'+ GB_DOCID +',';
	appointmentDetails +='"scheduleId":' + scheduleId+',';
	appointmentDetails +='"startTime":"' + timestart+ '",';
	appointmentDetails +='"startDate":"'+startdate+'",';
	appointmentDetails +='"patientName":"' +$j('#newAppointmentForm #Patient').val()+ '"';
	appointmentDetails += '}';
	return appointmentDetails;
}

function setScheduleid(id){
	scheduleId=id;
 }
function setTimeslot(time){
	timestart=time;
}

