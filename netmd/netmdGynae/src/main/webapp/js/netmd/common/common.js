function createGlobalToolBar () {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/netmd/json/toolbars/globalToolbar.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}

function createGlobalToolBarFirstLogin () {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/netmd/json/toolbars/globalToolbarFirstLogin.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
function createGlobalToolBarForDoctor () {
	//var response = getRequestData('/weblims/ws/ui/admin/pageControles/' + constants_globalToolBarFileName);
	var response= getRequestData("/netmd/json/toolbars/globalToolbarForDoc.json");
	var globalTB = new GlobalToolBar(response);
	$j('.global-bar-tab .controlB').html(globalTB.result);		
}
function createLeftpaneToolBar() {
	var response= getRequestData("/netmd/json/toolbars/leftPaneToolBar.json");
	var leftpaneTB = new leftpaneToolBar(response.buttons);
	$j('.leftmenu').html(leftpaneTB.result);		
}

function createCalendar(calenderObj){
	var newsheduleVar=$j('<div/>');
	newsheduleVar.attr('id','calendar');
	newsheduleVar.attr('class','fc');
	$j(calenderObj).html(newsheduleVar);
	$j.getScript("/netmd/js/plugins/calendar.js").done(function(script, textStatus) {
		$j.getScript("/netmd/js/plugins/fullcalendar.min.js").done(function(script, textStatus) {
		})
	})		 
}
function ampmConversion(time){
	var splitstrttme=time.slice(0,5);
	var splitstrttme1=time.slice(6,8);
	if((splitstrttme1)=="AM")
		splitstrttme1="am";	
	else
		splitstrttme1="pm";
	var strttime=splitstrttme+" "+splitstrttme1;
	return strttime;
}

 function addTime(t1,t2,format){
	var t1Hr = splitTimeStr(t1)[0];
	var t2Hr = splitTimeStr(t2)[0];
	var t1Min = splitTimeStr(t1)[1];
	var t2Min = splitTimeStr(t2)[1];
	var rHr = t1Hr + t2Hr;
	var rMin = t1Min + t2Min;
	var check=rHr+"."+rMin;		
	if (rMin >= 60){
		rMin = rMin - 60;
		rHr = rHr + 1;
	}
	if(check > 12.30)
		rHr = rHr - 12	
	var timefrmat=rHr + ":" + rMin;
	if(timefrmat == "12:0"){ 		
		if(format=="am")	  
			format="pm";   
		else
			 format="am";
	}		
	if (rMin < 10) rMin = "0" + rMin;
	if (rHr < 10) rHr = "0" + rHr;
	return "" + rHr + ":" + rMin+" "+format;
}

function splitTimeStr(t){
	var t = t.split(":");
	var timeslot=t[1].slice(0,2);
	t[0] = Number(t[0]);
	t[1] = Number(timeslot);
	return t;
}

function combiningDate(date){
		if(date<10){date="0"+date;}
		var selecteweekmonth=$j('.fc-header-title h2').text();
		var monthweek=selecteweekmonth.split(' ');
		var month=monthweek[0];
		var realmonth=convertToIntMonth(month);
		
		if(realmonth<10){
			realmonth="0"+realmonth;
		}
		
		var year=monthweek[1];
		var daymonthweek=year+"-"+realmonth+"-"+date;
		return daymonthweek
		}
		
function convertToIntMonth(monText) {
        var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
        var monInt;
        for (i = 0; i < 12; i++) {
            if (monText == monthNames[i])
            { 
monInt=i+1}
        }
        return monInt;
    }
	
function getErrorData(){
var errordata = getRequestData('/netmd/ws/ui/auth/getErrorCodes');
return errordata;
}
	
function getErrorName(error) {
	var errorcode = error.errCode;
	var errorName;
	$j(errorData.error).each(function(index,errordet){
	if(errorcode==errordet.errCode) {
	errorName=errordet.errMsg;
	$j(error.params).each(function(indexerror, err) {
	var toReplace='{' + err.parameterName + '}';
	var valuetoReplace = " " + err.value + " ";
	errorName=errorName.replace(toReplace,valuetoReplace);
		});
	return false;
}
  });
	return errorName;
}

function removeErrors() {
		$j('.errorStyle').remove();
		$j('.error').removeClass('error');
		$j('#errorDivHeader').hide();
	}
	
function clearNilFields(formid){
    var id='#'+formid;
	$j(id+' input[type=text],textarea ').each(function() {
		// Does value contain Nil?
		if ($j(this).val().indexOf("Nil") != -1)
			$j(this).val("");
		return;
    });
}

//get the default data
function getDefaultData() {
	var defData=getRequestData('/weblims/ws/ui/order/getDefaultData');
	return defData;
}

 function getFilterlistUrl(filterExp,startindex,interval){
	var listJson='{"exp":[' + filterExp + '], "from":' + startindex + ',"count":'+interval + ',"asc":false}';
	return listJson;
} 

function setPaginationFields(curPage, maxPages, obj) {
	$j(obj + ' #first').removeClass('paginate_button_disabled');
	$j(obj + ' #next').removeClass('paginate_button_disabled');
	$j(obj + ' #previous').removeClass('paginate_button_disabled');
	$j(obj + ' #last').removeClass('paginate_button_disabled');
	$j(obj + ' #first').addClass('paginate_button');
	$j(obj + ' #next').addClass('paginate_button');
	$j(obj + ' #previous').addClass('paginate_button');
	$j(obj + ' #last').addClass('paginate_button');
	$j(obj + ' .paginate_active').html(curPage);
	if(curPage==1 && maxPages<=1) {
		$j(obj + ' #first').removeClass('paginate_button');
		$j(obj + ' #next').removeClass('paginate_button');
		$j(obj + ' #previous').removeClass('paginate_button');
		$j(obj + ' #last').removeClass('paginate_button');
		$j(obj + ' #first').addClass('paginate_button_disabled');
		$j(obj + ' #next').addClass('paginate_button_disabled');
		$j(obj + ' #previous').addClass('paginate_button_disabled');
		$j(obj + ' #last').addClass('paginate_button_disabled');
	}else if(curPage==1) {
		$j(obj + ' #previous').removeClass('paginate_button');
		$j(obj + ' #previous').addClass('paginate_button_disabled');
		$j(obj + ' #first').removeClass('paginate_button');
		$j(obj + ' #first').addClass('paginate_button_disabled');
	}else if(curPage==maxPages) {
		$j(obj + ' #first').removeClass('paginate_button_disabled');
		$j(obj + ' #first').addClass('paginate_button');
		$j(obj + ' #previous').removeClass('paginate_button_disabled');
		$j(obj + ' #previous').addClass('paginate_button');
		
		$j(obj + ' #last').removeClass('paginate_button');
		$j(obj + ' #last').addClass('paginate_button_disabled');
		$j(obj + ' #next').removeClass('paginate_button');
		$j(obj + ' #next').addClass('paginate_button_disabled');
	} 
}	

function setCustomDataTableNPagin(tableObj) {
	$j(tableObj).dataTable( {
		"sPaginationType": " ",
		"bRetrieve":true,
		"bFilter":false,
		"bInfo":false,
		"bLengthChange":false,
		"bSort":false,
		"sDom": '<"top"Hi>'
	});
}


	
function getPatientData(patientId) {
	var response=getRequestData("/netmd/ws/ui/patient/view/" + patientId);
	return response;
}

function getPatientId() {
	return $j("#patientViewForm #patientid label").text();
}

 
function fillDoctorAsAutoComplete(controlObj) {
	var exp=getExpressionForDoctor();
	var maxRecords=0; 
	var maxPages = 0; 
	var interval = 10;
	var curPage = 1;
	autoCompleteArray=[];
	var referralListJson = getFilterlistUrl(exp,(curPage-1),interval);
	listDoctor= postdataToServer("/netmd/ws/ui/doctor/list",referralListJson);
	 $j(listDoctor.referral).each(function (doctorIndex, refferalObj) {
			 if(refferalObj.lastName==null)
				autoCompleteArray.push(''+refferalObj.firstName+' '+refferalObj.qualification+'');	
			else if(refferalObj.qualification=="")
				autoCompleteArray.push(''+refferalObj.firstName+' '+refferalObj.lastName+'');	
			else
				autoCompleteArray.push(''+refferalObj.firstName+' '+refferalObj.lastName+' '+refferalObj.qualification+'');
		});	
			/* if(listDoctor.count==0)	
				$j('#leftPaneSchedules,#leftPaneAppointments').hide();
			 else
			    $j('#leftPaneSchedules,#leftPaneAppointments').show();
			 */
		 makeautoComplete(controlObj,autoCompleteArray);
	}
 
function fillpatientAsAutoComplete(controlObj){
	var exp=getExpressionForPatient();
	autoCompleteArray=[];
	var interval = 10;
	var curPage = 1;
	var patientListJson = getFilterlistUrl(exp,(curPage-1),interval);
	listPatient= postdataToServer("/netmd/ws/ui/patient/list",patientListJson);	
	$j(listPatient.patient).each(function (doctorIndex, patientObj) {
		autoCompleteArray.push(''+patientObj.firstName+' '+patientObj.lastName+''+'['+ patientObj.email+']');	
	});	
	makeautoComplete(controlObj,autoCompleteArray);
}
 
function fillrepeatToControl(controlObj) {
	$j(controlObj).empty();
	$j(controlObj).append('<option value="None">None</option><option value="Daily">Daily</option><option value="Weekly">Weekly</option><option value="Monthily day of the month">Monthly day of the month</option><option value="Monthily day of the week">Monthly day of the week</option>');
}
function getWeekDays(){
	var weekarray=[];
	if($j("#newScheduleForm #repeat").val()=="Weekly"){
		$j('#repeatweek .week:checked').each(function() {
			var weedays=$j(this).attr('value');
			weekarray.push(weedays);
		});
	}
	else
		weekarray=[];	
		return weekarray;
}	

function getPatientIdUsingName(controlObj) {
	var exp=getExpressionForPatient();
	var interval = 10;
	var curPage = 1;
	var patientId;
	var patientListJson = getFilterlistUrl(exp,(curPage-1),interval);
	listPatient= postdataToServer("/netmd/ws/ui/patient/list",patientListJson);	
	$j(listPatient.patient).each(function(index,patient){ 
	var fullName=''+patient.firstName+' '+patient.lastName+'';
		if(controlObj==fullName)
			patientId=patient.id;
	});
	return patientId;
}

function getDoctorIdUsingName(name) {
	var exp=getExpressionForDoctor();
	var maxRecords=0; 
	var maxPages = 0; 
	var interval = 10;
	var curPage = 1;
	var fullName;
	autoCompleteArray=[];
	var referralListJson = getFilterlistUrl(exp,(curPage-1),interval);
	listDoctor= postdataToServer("/netmd/ws/ui/doctor/list",referralListJson);
	var doctorId="false";
	 $j(listDoctor.referral).each(function (doctorIndex, refferalObj) { 
		if(refferalObj.lastName==null){		
			var fullName=(''+refferalObj.firstName+' '+refferalObj.qualification+'');
			if(name==fullName)
			   doctorId=refferalObj.id;
         }		 
		else if(refferalObj.qualification==""){
			var fullName=(''+refferalObj.firstName+' '+refferalObj.lastName+'');
			if(name==fullName)
			   doctorId=refferalObj.id;
         }	
		else {
			var fullName=(''+refferalObj.firstName+' '+refferalObj.lastName+' '+refferalObj.qualification+'');
			if(name==fullName)
			   doctorId=refferalObj.id;
         }	

	});	
	return doctorId;
}

function makeautoComplete(id,sourceData){
	sourceData.sort();
	target = "#"+id;
	$j(target).autocomplete({
		source: function( request, response ) {
			var matches = $j.map( sourceData, function(tag) {
				if (tag.toUpperCase().indexOf(request.term.toUpperCase()) === 0 ) {
					return tag;
				}
			});
			response(matches);
		}			
	});		
	return;
}

function getDoctorData(doctorId) {
	var response=getRequestData("/netmd/ws/ui/doctor/view/" + doctorId);
	return response;
}
function getOwnerData(id){
  var response=getRequestData("/netmd/ws/ui/owner/view/" + id);
	return response;
}

function  getExpressionForDoctor(){
	var listJsonForDoctor='{"name":"status","value":"active","operator":"eq"}';
	return listJsonForDoctor;
}
function  getExpressionForPatient(){
	var listJsonForPatient='{"name":"status","value":"active","operator":"eq"}';
	return listJsonForPatient;
}