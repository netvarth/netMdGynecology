$j(document).ready(function(){
	var currentDate=getCurrentDate();
	var scheduleDetails=getScheduleDetails(currentDate);

	$j(scheduleDetails.schedule).each(function(index,interval) {
		if(interval.startDate)
			$j("#editScheduleForm #Startdate1").val(interval.startDate);
		else
			$j("#editScheduleForm #Startdate1").val("Nil");
		if(interval.startTime)
			$j("#editScheduleForm #from1").val(interval.startTime);
		else
			$j("#editScheduleForm #from1").val("Nil");
		if(interval.endTime)
			$j("#editScheduleForm #to1").val(interval.endTime);
		else
			$j("#editScheduleForm #to1").val("Nil");
		if(interval.status)
			$j("#editScheduleForm #workinghours label").text(interval.status);
		else
			$j("#editScheduleForm #workinghours label").text("Nil");	
		if(interval.repeat)
			$j("#editScheduleForm #repeat label").text(interval.repeat);
		else
			$j("#editScheduleForm #repeat label").text("Nil");	
	}); 
		
});		