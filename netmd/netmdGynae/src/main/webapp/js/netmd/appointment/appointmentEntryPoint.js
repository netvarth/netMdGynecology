
$j(document).ready(function () {
     $j('#pageToolBar-Container').html("");
	 var doctorName=$j('#docSelect').val();
	 if(usertype!="doctor")
		$j('#pageTitle').html("Appointments For - "+doctorName);
	 else
       $j('#pageTitle').html("Appointments For");	 
     appointmentPageToolBar();
     createCalendar('#tabs-appointment');    
});
    var appoinmentDate=getAppoinmentDate();    
    var scheduleDetailsForAppoinment=getScheduleDetailsForAppoinment(appoinmentDate);
    fillScheduleDetailsToAppointment(scheduleDetailsForAppoinment);
    fillAppointments(appoinmentDate);
/*--------------not checked-----------------------*/
$j('#tabs-appointment .fc-agenda-slots tbody tr td').die('click').live("click", function () {
   var slotdata = $j(this).text().length;
    var slotdataText = $j(this).text();
    if (slotdataText == "Take Your Appointment") {
        var timeslot = $j(this).closest('tr').children('th').text();
        var id = $j(this).children('div').attr('name');
		var obj = $j(this);
        createModal(constants_newappointmentJson, 'scheduleModal');
        openModalBox(obj,'scheduleModal')
        $j.cachedScript(constant_AppointmentFunctions_Url).done(function (script, textStatus) {
			setTimeslot(timeslot);
            setScheduleid(id);
        })
    } else {
            if ($j(this).attr('selected')) {
                $j(this).removeAttr('selected');
                $j(this).removeAttr('style');
            } else {
                $j(this).attr('selected', 'selected');
                $j(this).attr('style', 'background:#DCDCDC;');
            }
    }

 });
 
$j('#appointmentPTBContainer #btn_view_ptb_id').die('click').live("click", function () {
    var obj = $j(this);
    var appmtId = getAppointmentId();
    if (appmtId != "") {
        createModal(constants_vweditAppointmentJson, 'appointmentViewModal');
        openModalBox(obj, 'appointmentViewModal')
        $j.cachedScript(constants_vieweditAppointment).done(function (script, textStatus) {
		})
	}	
       
 });
$j('#appointmentPTBContainer #btn_delete_ptb_id').die('click').live("click", function () {
    var delAppid = getAppointmentId();
    var delResponse = getRequestData('/netmd/ws/ui/appointment/delete/' + delAppid);
    if (delResponse.success == true) {
        var id = delResponse.id;
        $j('.fc-agenda-slots tbody tr td').each(function () {
            var value = $j(this).attr('value');
            if (id == value) {
                $j(this).children('div').text("Take Your Appointment");
                $j(this).children('div').attr('style', 'font-size:150%;margin: 10px 0px 0px 300px;');
                $j(this).removeAttr('selected');
                $j(this).removeAttr('style');
            }
        });
    }
});

$j('#tabs-appointment .fc-header-right .fc-button-today').die('click').live("click", function () {
    $j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
    $j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
    $j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
    $j('.fc-agenda-slots tbody tr').show();
	var appoinmentDate=getAppoinmentDate();	
	var scheduleDetailsForAppoinment=getScheduleDetailsForAppoinment(appoinmentDate);
	fillScheduleDetailsToAppointment(scheduleDetailsForAppoinment);
	fillAppointments(appoinmentDate);
});
$j('#tabs-appointment .fc-header-right .fc-button-prev').die('click').live("click", function () {;
    $j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
    $j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
    $j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
    $j('.fc-agenda-slots tbody tr').show();
	var appoinmentDate=getAppoinmentDate();	
	var scheduleDetailsForAppoinment=getScheduleDetailsForAppoinment(appoinmentDate);
	fillScheduleDetailsToAppointment(scheduleDetailsForAppoinment);
	fillAppointments(appoinmentDate);
});
$j('#tabs-appointment .fc-header-right .fc-button-next').die('click').live("click", function () {
    $j('.fc-agenda-slots tr th').nextAll('td').children('div').text("");
    $j('.fc-agenda-slots tr th').nextAll('td').children('div').removeAttr('style');
    $j('.fc-agenda-slots tr th').parents('.fc-agenda-slots tr').removeClass('appointment');
    $j('.fc-agenda-slots tbody tr').show();
	var appoinmentDate=getAppoinmentDate();	
	var scheduleDetailsForAppoinment=getScheduleDetailsForAppoinment(appoinmentDate);
	fillScheduleDetailsToAppointment(scheduleDetailsForAppoinment);
	fillAppointments(appoinmentDate);
 });
 /*-----------------------------------------------------------------------*/

/*--------------functions----------------------------------*/
function appointmentPageToolBar() {
    var ptbdata = getRequestData('/netmd/json/toolbars/appointmentPageToolBar.json');
    var ptbContainer = $('<div id="appointmentPTBContainer"/>');
    var ptb = new PageToolBar(ptbdata);
    $j(ptbContainer).append(ptb.result);
    $j('#pageToolBar-Container').html(ptbContainer);
}
function fillScheduleDetailsToAppointment(scheduleDetails){
    $j(scheduleDetails.schedule).each(function (index, interval) {
        var timevw = interval.startTime;
        var endtime = interval.endTime;
        var status = interval.status;
        var nameid = interval.id;
        var srisid = interval.seriesId;
        var conversion = ampmConversion(timevw);
        var conversion1 = ampmConversion(endtime);
        var end = "00:30";
        $j('.fc-agenda-slots tr th').each(function () {
            var timeslot = $j(this).text();
            var frmat = conversion.slice(6, 8);
            if (timeslot == conversion && conversion != conversion1) {
                if (status == "Working Hours") {
                    $j(this).nextAll('td').children('div').text("Take Your Appointment");
                    $j(this).nextAll('td:first').children('div').attr('style', 'font-size:150%;margin: 10px 0px 0px 300px;');
                    $j(this).nextAll('td:first').children('div').attr('name', nameid);
                    $j(this).parents('.fc-agenda-slots tr').addClass('appointment');
                    conversion = addTime(conversion, end, frmat);
                }
            }
        });

    });
}
function fillAppointments(date){
    var appointmentDetails= getRequestData('/netmd/ws/ui/appointment/view/' + GB_DOCID + ',' + date);
    $j(appointmentDetails.appointment).each(function (indx, data) {
        var viewdates = data.startTime;
        var patientname = data.patientName;
        var patientLastname = data.patientLastName;
        var appointId = data.id;
        var vwDateformat = ampmConversion(viewdates);
        $j('.fc-agenda-slots tr th').each(function () {
            var timeslot = $j(this).text();
            if (timeslot == vwDateformat) {
                $j(this).nextAll('td').children('div').text(patientname + " " + patientLastname);
                $j(this).nextAll('td:first').children('div').attr('style', 'font-size:150%;margin: 10px 0px 0px 300px;');
                $j(this).nextAll('td:first').attr('value', appointId);
            }

        });
    });
	
}
function getAppoinmentDate(){
	var selecteddate = $j('.fc-header-title h2').text();
    var datesplit = selecteddate.split(',');
    var date = datesplit[1];
    var day = $j.trim(date);
	return day;
}
function getScheduleDetailsForAppoinment(day){
	var scheduleDetails = getRequestData('/netmd/ws/ui/schedule/dayView/'+day+','+GB_DOCID);
	return scheduleDetails;
}
function dayViewClickApp() {
    var selecteddate = $j('.fc-header-title h2').text();
    var datesplit = selecteddate.split(',');
    var date = datesplit[1];
    date = $j.trim(date);

    var response = getRequestData('/netmd/ws/ui/schedule/dayView/' + date + ',' + GB_DOCID);
    var responseapp = getRequestData('/netmd/ws/ui/appointment/view/' + GB_DOCID + ',' + date);

    $j(response.schedule).each(function (index, interval) {

        var timevw = interval.startTime;
        var endtime = interval.endTime;
        var status = interval.status;
        var nameid = interval.id;
        var srisid = interval.seriesId;
        var conversion = ampmConversion(timevw);
        var conversion1 = ampmConversion(endtime);
        var end = "00:30";
        $j('.fc-agenda-slots tr th').each(function () {
            var timeslot = $j(this).text();
            var frmat = conversion.slice(6, 8);
            if (timeslot == conversion && conversion != conversion1) {
                if (status == "Working Hours") {
                    $j(this).nextAll('td').children('div').text("Take Your Appointment");
                    $j(this).nextAll('td:first').children('div').attr('style', 'font-size:150%;margin: 10px 0px 0px 300px;');
                    $j(this).nextAll('td:first').children('div').attr('name', nameid);
                    $j(this).parents('.fc-agenda-slots tr').addClass('appointment');
                    conversion = addTime(conversion, end, frmat);
                }
            }
        });


    });
    $j(responseapp.appointment).each(function (indx, data) {
        var viewdates = data.startTime;
        var patientname = data.patientName;
        var patientLastname = data.patientLastName;
        var appointId = data.id;
        var vwDateformat = ampmConversion(viewdates);
        $j('.fc-agenda-slots tr th').each(function () {
            var timeslot = $j(this).text();
            
            if (timeslot == vwDateformat) {
                $j(this).nextAll('td').children('div').text(patientname + " " + patientLastname);
                $j(this).nextAll('td:first').children('div').attr('style', 'font-size:150%;margin: 10px 0px 0px 300px;');
                $j(this).nextAll('td:first').attr('value', appointId);
            }

        });
    });

}

function getAppointmentId() {
    var appoinId = "";
    var selectedOrders = $j('.fc-agenda-slots tbody tr td[selected]');
    if (selectedOrders.length == 0) {
        updateTips("Select atleast one appointment", $j('#errorDivHeader'));
    } else if (selectedOrders.length > 1) {
        updateTips("Select only one appointment", $j('#errorDivHeader'));
    } else {
        appoinId = selectedOrders.attr('value');
    }
    return appoinId;
}