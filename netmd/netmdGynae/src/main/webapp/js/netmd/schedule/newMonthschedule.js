$j.cachedScript(constant_newScheduleValidations_Url).done(function(script, textStatus) {
})
$j(document).ready(function(){
   var day1=fillFormContents();
   fillOptions();
	$j('#newScheduleForm #btnDone').die('click').live("click",function() {
		removeErrors();
		if(validateNewSchedule()) {
			var response = submitscheduleInfo();
			 if(response.success==true){
				$j('#newScheduleForm').trigger('reveal:close');
				showTip("Schedule created successfully");
				var scheduleDetails= getRequestData('/netmd/ws/ui/schedule/dayView/'+day1+','+GB_DOCID );
                 fillScheduleDetailsToCalender(scheduleDetails,day1);
				$j("#newScheduleForm input[type=text], textarea").val("");
			}
			else
				updateTipsNew(getErrorName(response.error),$j('#newScheduleForm #errorDivNewSchedule'),$j('#newScheduleForm #errorDivHeader'));
		}
	});
	
	$j('#newScheduleForm #btnCancel').die('click').live("click",function() {
		removeErrors();
		$j('#newScheduleForm input[type=text]').val("");
		$j('#newScheduleForm #repeat option[value="None"]').attr("selected","selected");
		$j('#newScheduleForm .week').removeAttr('checked');
		$j("#newScheduleForm #repeatweek").hide();
		$j("#newScheduleForm #repeatend").hide();
	});
	
	
	
});
/*----------functions-----------------------------------------------------------------*/


function fillFormContents(){
	$j("#newScheduleForm #from").timepicker();
	$j("#newScheduleForm #to").timepicker();
	$j("#newScheduleForm #Startdate").datepicker();
	fillrepeatToControl("#newScheduleForm #repeat")
	var datechange=$j('.fc-header-title h2').text();
	var daysplit=datechange.split(",");
	var day=daysplit[1];
	day=$j.trim(day);
	$j("#newScheduleForm #Startdate").val(day);
	return day;
}

function fillOptions(){
$j("#newScheduleForm #endafter").die('click').live("click",function() {
		$j("#newScheduleForm #occuranceOne").val("");
		$j("#newScheduleForm #datepicker").hide();
		$j("#newScheduleForm #occurance").show();		
	});
	
$j("#newScheduleForm #enddate").die('click').live("click",function() {
    $j("#newScheduleForm #datepickerOne").val("");
	$j("#newScheduleForm #occurance").hide();
	$j("#newScheduleForm #datepicker").show();
	
	$j("#newScheduleForm #datepickerOne").datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: '1950:2100'
	});	
});
	
	$j("#newScheduleForm #repeat").change(function () {
		if($j("#newScheduleForm #repeat").val()=="None"){
			$j("#newScheduleForm #repeatweek").hide();
			$j("#newScheduleForm #repeatend").hide();
		}
		else if($j("#newScheduleForm #repeat").val()=="Weekly"){
		$j("#newScheduleForm #repeatweek").show();
		$j("#newScheduleForm #repeatend").show();
		
		}
		else{
		$j("#newScheduleForm #repeatend").show();
		$j("#newScheduleForm #repeatweek").hide();
		}
	});
	
	$j("#newScheduleForm #from").change(function () {
		var start=$j("#newScheduleForm #from").val();
		var end = "00:30";
		var format=start.slice(6,8);
		
    $j("#newScheduleForm #to").val(addTime(start,end,format));
	});
	
}
function submitscheduleInfo(){
	var resultJson = createSubmitJson();;
	var response = postdataToServer(constant_schedule_create_Url, resultJson );
	return response;
}
function createSubmitJson(){
	var week=getWeekDays();
	var occurence;
	if($j('#newScheduleForm #occuranceOne').val()!="")
		occurence=$j('#newScheduleForm #occuranceOne').val();
	else
		occurence=0;
	var scheduleDetails = '{"doctorId":'+GB_DOCID+',';
	scheduleDetails += '"name":"'+ "rim" +'",';
	scheduleDetails +='"startDate":"' + $j("#newScheduleForm #Startdate").val() +'",';
	scheduleDetails +='"startTime":"' + $j("#newScheduleForm #from").val() + '",';
	scheduleDetails +='"endTime":"' + $j("#newScheduleForm #to").val() + '",';
	scheduleDetails +='"endDate":"' + $j('#newScheduleForm #datepickerOne').val() + '",';
	scheduleDetails +='"repeat":"' + $j('#newScheduleForm #repeat').val() + '",';
	scheduleDetails +='"noOfOccurances":' + occurence + ',';
	scheduleDetails +='"weeklySunThruSatList":' +"["+ week+"]" + ',';
	scheduleDetails +='"status":"' + $j("#newScheduleForm input[name='slot']:checked").val()+ '"';
	scheduleDetails += '}';
	return scheduleDetails;

	}
	

