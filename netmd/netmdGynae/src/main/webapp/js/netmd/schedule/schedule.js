$j(document).ready(function(){
	var doctorName=$j('#docSelect').val();
	if(usertype!="doctor")
		$j('#pageTitle').html(constant_shedule_Msg+"- "+doctorName);
	else
		$j('#pageTitle').html(constant_shedule_Msg);
	createCalendar('#tabs-schedule');
	schedulePageToolBar();
});
var selectedRowSeriesId;
var selectedRowId;
var currentDate=getCurrentDate();
var scheduleDetails=getScheduleDetails(currentDate);
fillScheduleDetailsToCalender(scheduleDetails,currentDate);

$j('#tabs-schedule .fc-agenda-slots tbody tr td').die('click').live("click",function() {
    selectedRowSeriesId=$j(this).children('div').attr('value');
	selectedRowId=$j(this).children('div').attr('name');
	var slotdata=$j(this).text().length;
	slotdata=$j.trim(slotdata);
	if((slotdata=='1')||(slotdata=='0')){
		var timeslot=$j(this).closest('tr').children('th').text();
		var endtime=$j(this).closest('tr').nextAll('tr:first').children('th').text();
		var obj=$j(this);		
		createModal(constants_newScheduleJson,'scheduleModal');		
		openModalBox(obj,'scheduleModal')
		$j("#newScheduleForm #from").val(timeslot);
		$j("#newScheduleForm #to").val(endtime)
		$j.cachedScript(constants_newschedule).done(function(script, textStatus) {
		})
	}
	else{
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} 
		else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}	
	}
});

$j('#tabs-schedule .fc-border-separate tr td').die('click').live("click",function() {
	var date=$j(this).children('div').children('div').text();
	var newdate=combiningDate(date);
	var obj=$j(this);	
	createModal(constants_newScheduleJson,'scheduleModal');		
	openModalBox(obj,'scheduleModal')
	$j.cachedScript(constants_newmonthschedule).done(function(script, textStatus) {		
		var date=$j.trim(newdate);
		$j("#newScheduleForm #Startdate").val(date);	
	})	
}); 


	$j('#tabs-schedule .fc-header-right .fc-button-today').die('click').live("click",function() {
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
		var currentDate=getCurrentDate();
		var scheduleDetails=getScheduleDetails(currentDate);
		fillScheduleDetailsToCalender(scheduleDetails,currentDate); 
	});
	$j('#tabs-schedule .fc-header-right .fc-button-prev').die('click').live("click",function() {
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
		var currentDate=getCurrentDate();
		var scheduleDetails=getScheduleDetails(currentDate);
		fillScheduleDetailsToCalender(scheduleDetails,currentDate);
	});
	$j('#tabs-schedule .fc-header-right .fc-button-next').die('click').live("click",function() {
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
		$j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
		var currentDate=getCurrentDate();
		var scheduleDetails=getScheduleDetails(currentDate);
		fillScheduleDetailsToCalender(scheduleDetails,currentDate);
	});

$j('#schedulePTBContainer #btn_view_ptb_id').die('click').live("click",function() {
    var obj=$j(this);	
	var schedlId=getSelectedScheduleId();	
	if(schedlId!=""){			
		createModal(constants_vweditScheduleJson,'scheduleViewModal');		
		openModalBox(obj,'scheduleViewModal')
		$j.cachedScript(constants_vieweditschedule).done(function(script, textStatus) {
		})
	}
});

$j('#schedulePTBContainer #btn_delete_ptb_id').die('click').live("click",function() {
	var obj=$j(this);	
	var schedlId=getSelectedScheduleId();	
	if(schedlId!=""){		
		createModal(constants_deleteScheduleJson,'deleteViewModal');		
		openModalBox(obj,'deleteViewModal')
	}
});	 

$j('#DeleteScheduleForm #btnok').die('click').live("click",function() {
		var currentDate=getCurrentDate();
		var scheduleDetails=getScheduleDetails(currentDate);
		
	if(scheduleDetails.success==true){
		$j(scheduleDetails.schedule).each(function(index,interval) {	 
			scheduleId=interval.id;
			seriesid=interval.seriesId;	
			 if($j("#DeleteScheduleForm input[name='deleteslot']:checked").val()=="AllSchedule"){
				if(selectedRowSeriesId==seriesid){
					var response=getRequestData('/netmd/ws/ui/schedule/delete/' + seriesid);
					if(response.success==true){
						$j('tr td div[value='+seriesid+']').text("");
						$j('tr td div[value='+seriesid+']').parent().removeAttr('style selected');
					}
				}
			}
			
			else if($j("#DeleteScheduleForm input[name='deleteslot']:checked").val()=="ThisSchedule"){
					if(selectedRowId==scheduleId){
						var response=getRequestData('/netmd/ws/ui/schedule/deleteInstance/' + scheduleId);
						if(response.success==true){
							var resid=response.id
							$j('tr td div[name='+resid+']').text("");
							$j('tr td div[value='+seriesid+']').parent().removeAttr('style selected');
						}
					}
			}	
			else{
				if(selectedRowId==scheduleId){
					var response=getRequestData('/netmd/ws/ui/schedule/deleteFollowing/' + scheduleId);
						if(response.success==true){
							var resid=response.id
							$j('tr td div[name='+resid+']').text("");
							$j('tr td div[value='+seriesid+']').parent().removeAttr('style selected');
						}
				}
			}
	   });
	} 
	 $j('#DeleteScheduleForm').trigger('reveal:close');
});
/*----------functions---------*/
function schedulePageToolBar() {
	var ptbdata =getRequestData('/netmd/json/toolbars/schedulePageToolBar.json');
	var ptbContainer = $('<div id="schedulePTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillScheduleDetailsToCalender(scheduleDetails,day){
	$j(scheduleDetails.schedule).each(function(index,interval) {
		var nameid=interval.id;
		var serisid=interval.seriesId;
		var slot=interval.status;
		var currentdate=interval.startDate;
		var timeslot1="00:30";
		var convertedStartTime=ampmConversion(interval.startTime);
		var convertedEndTime=ampmConversion(interval.endTime);
		$j('.fc-agenda-slots tr th').each(function() {
			var timeslot=$j(this).text();
			var frmat=convertedStartTime.slice(6,8);
			if(day==currentdate){
				if(timeslot==convertedStartTime && convertedStartTime!=convertedEndTime){
					$j(this).nextAll('td:first').children('div').text(slot);
					$j(this).nextAll('td:first').children('div').attr('style','font-size:150%;margin: 10px 0px 0px 300px;');
					$j(this).nextAll('td:first').children('div').attr('name',nameid);
				    $j(this).nextAll('td:first').children('div').attr('value',serisid);
					convertedStartTime=addTime(convertedStartTime,timeslot1,frmat);
				}
			}
		});			
	}); 
}

function getScheduleDetails(day){
	var scheduleDetails = getRequestData('/netmd/ws/ui/schedule/dayView/'+day+','+GB_DOCID);
	return scheduleDetails;
}
function getCurrentDate(){
	var selecteddate=$j('#tabs-schedule .fc-header-title h2').text();
	var datesplit=selecteddate.split(',');
	var date=datesplit[1];
	var day=date.trim();
	return day;
}
function getSelectedScheduleId() {
	var scheduleId="";
	var selectedSchedule = $j('.fc-agenda-slots tbody tr td[selected]');
	if(selectedSchedule.length==0)
		updateTips("Select atleast one schedule",$j('#errorDivHeader'));
	else if(selectedSchedule.length>1) 
		updateTips("Select only one schedule",$j('#errorDivHeader'));
	else 
		scheduleId =selectedSchedule.attr('value');
	return scheduleId;
} 

	