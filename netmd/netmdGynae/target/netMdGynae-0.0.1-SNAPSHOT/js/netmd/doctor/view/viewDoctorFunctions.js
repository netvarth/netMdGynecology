function viewDoctor(doctorid) {
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#pageToolBar-Container').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="referralGeneralPTBContainer" />');
	var ptbdata =getRequestData('/netmd/json/toolbars/viewGeneralToolbar.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
  
	var viewdata = getRequestData('/netmd/json/view/viewdoctordetails.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	
$j("#fromDate").datepicker({
	changeMonth: true,
	changeYear: true,
	yearRange: '-100:+0'
	});
	
$j("#toDate").datepicker({
	changeMonth: true,
	changeYear: true,
	yearRange: '-100:+0'
	});
	
		setCustomDataTable('#doctorViewForm #doctorExpTable');
		setCustomDataTable('#doctorViewForm #doctorQualiTable');
		setCustomDataTable('#doctorViewForm #doctorAchievementTable');
		setCustomDataTable('#doctorViewForm #doctorExpertiseTable');
		setCustomDataTable('#doctorViewForm #doctorMembershipTable');
		$j("#doctorExpTable_wrapper ,#doctorQualiTable_wrapper, #doctorExpertiseTable_wrapper ,#doctorAchievementTable_wrapper ,#doctorMembershipTable_wrapper").attr('style',"width:97%");
		
		doctorInfo = getDoctorData(doctorid);
		viewdoctorInfo(doctorInfo);
		clearNilFields('doctorViewForm');
			
	doctorId = getDoctorId();
	doctorInfo = getDoctorData(doctorId);
	$j('#pageTitle').html("View Doctor"+'  '+'( '+doctorInfo.firstName.toUpperCase()+' )');
	
}
function viewdoctorInfo(doctorInfo) {
    $j('#pageTitle').html("View Doctor");
	if(doctorInfo.id)
		$j("#doctorViewForm #doctorid label").text(doctorInfo.id);
	else
		$j("#doctorViewForm #doctorid label").text('Nil');
	if(doctorInfo.firstName)
		$j("#doctorViewForm #firstname").val(doctorInfo.firstName);
	else
		$j("#doctorViewForm #firstname").val('Nil');
	if(doctorInfo.lastName)
		$j("#doctorViewForm #lastname").val(doctorInfo.lastName);
	else
		$j("#doctorViewForm #lastname").val('Nil');
	if(doctorInfo.phone)
		$j("#doctorViewForm #landphone").val(doctorInfo.phone);
	else 
		$j("#doctorViewForm #landphone").val('Nil');
	if(doctorInfo.mobile)
		$j("#doctorViewForm #mobilephone").val(doctorInfo.mobile);
	else 
		$j("#doctorViewForm #mobilephone").val('Nil');
	if(doctorInfo.gender)
	    
		$j("#doctorViewForm #lblViewDoctorGender label").text(doctorInfo.gender);	
	else 
		$j("#doctorViewForm #lblViewDoctorGender label").text('Nil');
	if(doctorInfo.email)
		$j("#doctorViewForm #email label").text(doctorInfo.email);
	else 
		$j("#doctorViewForm #email label").text('Nil');
	if(doctorInfo.dateOfBirth)
		$j("#doctorViewForm #dateofbirth").val(doctorInfo.dateOfBirth);
	else 
		$j("#doctorViewForm #dateofbirth").val('Nil');
	if(doctorInfo.speciality)
		$j("#doctorViewForm #doctorspeciality").val(doctorInfo.speciality);
	else
		$j("#doctorViewForm #doctorspeciality").val('Nil');
	if(doctorInfo.worksat)
		$j("#doctorViewForm #workedat").val(doctorInfo.worksat);
	else
		$j("#doctorViewForm #workedat").val('Nil');
	if(doctorInfo.workhistory)
		$j("#doctorViewForm #workhistory").val(doctorInfo.workhistory);
	else 
		$j("#doctorViewForm #workhistory").val('Nil');
	if(doctorInfo.address)
		$j("#doctorViewForm #address").val(br2nl(doctorInfo.address));
	else 
		$j("#doctorViewForm #address").val('Nil');
	if(doctorInfo.specialization)
		$j("#doctorViewForm #specialization").val(doctorInfo.specialization);
	else 
		$j("#doctorViewForm #specialization").val('Nil');
	if(doctorInfo.consultationinterval)
		$j("#doctorViewForm #consultationinterval").val(doctorInfo.consultationinterval);
	else 
		$j("#doctorViewForm #consultationinterval").val('Nil');
		viewDoctorExperince(doctorInfo);
		viewDoctorQualification(doctorInfo);
		viewDoctorAchievement(doctorInfo);
		viewDoctorExpertise(doctorInfo);
		viewDoctorMemberships(doctorInfo);
}



function viewdoctorInfomation(doctorId) {
	doctorInfo = getDoctorData(doctorId);
	//alert(JSON.stringify(doctorInfo));
	if(doctorInfo.id)
		$j("#doctorViewForm #doctorid label").text(doctorInfo.id);
	else
		$j("#doctorViewForm #doctorid label").text('Nil');
	if(doctorInfo.firstName)
		$j("#doctorViewForm #firstname").val(doctorInfo.firstName);
	else
		$j("#doctorViewForm #firstname").val('Nil');
	if(doctorInfo.lastName)
		$j("#doctorViewForm #lastname").val(doctorInfo.lastName);
	else
		$j("#doctorViewForm #lastname").val('Nil');
	if(doctorInfo.phone)
		$j("#doctorViewForm #landphone").val(doctorInfo.phone);
	else 
		$j("#doctorViewForm #landphone").val('Nil');
	if(doctorInfo.mobile)
		$j("#doctorViewForm #mobilephone").val(doctorInfo.mobile);
	else 
		$j("#doctorViewForm #mobilephone").val('Nil');
	if(doctorInfo.gender)
	    
		$j("#doctorViewForm #lblViewDoctorGender label").text(doctorInfo.gender);	
	else 
		$j("#doctorViewForm #lblViewDoctorGender label").text('Nil');
	if(doctorInfo.email)
		$j("#doctorViewForm #email label").text(doctorInfo.email);
	else 
		$j("#doctorViewForm #email label").text('Nil');
	if(doctorInfo.dateOfBirth)
		$j("#doctorViewForm #dateofbirth").val(doctorInfo.dateOfBirth);
	else 
		$j("#doctorViewForm #dateofbirth").val('Nil');
	if(doctorInfo.speciality)
		$j("#doctorViewForm #doctorspeciality").val(doctorInfo.speciality);
	else
		$j("#doctorViewForm #doctorspeciality").val('Nil');
	if(doctorInfo.worksat)
		$j("#doctorViewForm #workedat").val(doctorInfo.worksat);
	else
		$j("#doctorViewForm #workedat").val('Nil');
	if(doctorInfo.workhistory)
		$j("#doctorViewForm #workhistory").val(doctorInfo.workhistory);
	else 
		$j("#doctorViewForm #workhistory").val('Nil');
	if(doctorInfo.address)
		$j("#doctorViewForm #address").val(br2nl(doctorInfo.address));
	else 
		$j("#doctorViewForm #address").val('Nil');
	if(doctorInfo.specialization)
		$j("#doctorViewForm #specialization").val(doctorInfo.specialization);
	else 
		$j("#doctorViewForm #specialization").val('Nil');
	if(doctorInfo.consultationinterval)
		$j("#doctorViewForm #consultationinterval").val(doctorInfo.consultationinterval);
	else 
		$j("#doctorViewForm #consultationinterval").val('Nil');
		viewDoctorExperince(doctorInfo);
		viewDoctorQualification(doctorInfo);
		viewDoctorAchievement(doctorInfo);
		viewDoctorExpertise(doctorInfo);
		viewDoctorMemberships(doctorInfo);
}


function viewDoctorExperince(doctorInfo) {
$j('#doctorViewForm #doctorExpTable').dataTable().fnClearTable();
if(doctorInfo.doctorExperience.length>0) {	
$j(doctorInfo.doctorExperience).each(function (expIndex,expObj) {
                var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
				var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	            var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
				var rowData=$j('#doctorViewForm #doctorExpTable').dataTable().fnAddData([expObj.designation,expObj.workedAt,expObj.fromDate,expObj.toDate,myData,EditBT]);
				var row=$j('#doctorViewForm #doctorExpTable').dataTable().fnSettings().aoData[rowData].nTr;
				$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 4, false );
				$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 5, false );
				$j(row).attr('id',expObj.id);
				$j(row).children('td:nth-child(2)').attr('class','datepicker');
				$j(row).children('td:nth-child(3)').attr('class','datepicker');
				
  });	
  }
}	

function viewDoctorQualification(doctorInfo) {
$j('#doctorViewForm #doctorQualiTable').dataTable().fnClearTable();
if(doctorInfo.doctorQualifications.length>0) {	
$j(doctorInfo.doctorQualifications).each(function (qualIndex,qualiObj) {
				var my_Data='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
				var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	            var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
				var rowData=$j('#doctorViewForm #doctorQualiTable').dataTable().fnAddData([qualiObj.educationalDegree,qualiObj.passedOutDate,qualiObj.institution,my_Data,EditBT]);
				var row=$j('#doctorViewForm #doctorQualiTable').dataTable().fnSettings().aoData[rowData].nTr;
				$j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 3, false );
				$j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 4, false );
				$j(row).attr('id',qualiObj.id);	
  });	
  }
}	

function viewDoctorAchievement(doctorInfo) {
$j('#doctorViewForm #doctorAchievementTable').dataTable().fnClearTable();
if(doctorInfo.achievement.length>0) {	
$j(doctorInfo.achievement).each(function (achmntIndex,achmntObj) {
				var my_Data='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
				var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	            var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
				var rowData=$j('#doctorViewForm #doctorAchievementTable').dataTable().fnAddData([achmntObj.achievement,my_Data,EditBT]);
				var row=$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSettings().aoData[rowData].nTr;
				$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 1, false );
				$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 2, false );
				$j(row).attr('id',achmntObj.id);	
  });	
  }
}	


function viewDoctorExpertise(doctorInfo) {
$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnClearTable();
if(doctorInfo.expertise.length>0) {	
$j(doctorInfo.expertise).each(function (expertiseIndex,expertiseObj) {
				var my_Data='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
				var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	            var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
				var rowData=$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnAddData([expertiseObj.expertise,my_Data,EditBT]);
				var row=$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSettings().aoData[rowData].nTr;
				$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 1, false );
				$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 2, false );
				$j(row).attr('id',expertiseObj.id);	
  });	
  }
}	


function viewDoctorMemberships(doctorInfo){
$j('#doctorViewForm #doctorMembershipTable').dataTable().fnClearTable();
if(doctorInfo.membership.length>0) {	
$j(doctorInfo.membership).each(function (Memindex,MemObj) {
				var my_Data='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
				var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	            var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
				var rowData=$j('#doctorViewForm #doctorMembershipTable').dataTable().fnAddData([MemObj.membership,my_Data,EditBT]);
				var row=$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSettings().aoData[rowData].nTr;
				$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 1, false );
				$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 2, false );
				$j(row).attr('id',MemObj.id);	
  });	
  }
}	


function getDoctorData(doctorId) {
	var response=getRequestData("/netmd/ws/ui/doctor/view/" + doctorId);
	return response;
}


function submitdoctorInfo(){
//alert("in  submit doctor Info");
	var resultJson = createSubmitJson();
	//alert(resultJson);
	var response = postdataToServer("/netmd/ws/ui/doctor/updatePersonalInfo", resultJson );
	//alert(response);
	return response;
}

function createSubmitJson(){
var doctorDetailss =getDoctorDetails();	
return doctorDetailss;
}


function getDoctorDetails() {

var name=$j('#doctorViewForm #firstname').val();
name= name.replace(/\b[a-z]/g, function(letter) {
	return letter.toUpperCase();
})	

//alert("4 getDoctorDetails");
    DoctorAddress = nl2br($j('#doctorViewForm #address').val());
	var doctorDetails = '{"firstName":"'+ name +'",';
		doctorDetails += '"lastName":"'+ $j('#doctorViewForm #lastname').val() +'",';
		doctorDetails += '"id":"'+ $j('#doctorViewForm #doctorid label').text() +'",';
		doctorDetails +='"dateOfBirth":"' + $j('#doctorViewForm #dateofbirth').val() +'",';
		doctorDetails +='"gender":"' + $j("#doctorViewForm input[name='lblViewDoctorGenderOne']:checked").val() + '",';
		doctorDetails +='"workHistory":"' +"Nil"  + '",';
		doctorDetails +='"mobile":"' + $j('#doctorViewForm #mobilephone').val() + '",';
		doctorDetails +='"phone":"' + $j('#doctorViewForm #landphone').val() + '",';
		doctorDetails +='"email":"' + $j('#doctorViewForm #email label').text() + '",';
		doctorDetails +='"address":"' + DoctorAddress + '",';
		doctorDetails +='"workingPlaces":"' + "Nil" + '",';
		doctorDetails +='"designation":"' + ""+'",';
		doctorDetails +='"specialization":"' +$j('#doctorViewForm #specialization').val()  +'",';
		doctorDetails +='"consultationInterval":"' +"Nil" +'"';
		doctorDetails +='}';
	return doctorDetails;
}

function asteriskShow(){
	$j("#viewDoctorForm .req_astrisk").each(function () {
		$j(this).show();
	});
}

// Toggle effect of view doctor Json
$j('#doctorViewForm .heading').die('click').live('click',function() {
$j(this).nextAll('div.details:first').toggle();
});

/* function to get the  previous curdoctorId from referrals table */
function getpreviousDoctorId(curdoctorId, pgDoctorList) {
//alert(JSON.stringify(pgDoctorList));
	var refId;
	$j(pgDoctorList.referral).each(function (index, rowReferral) {
		if(curdoctorId==rowReferral.id)	{
			var arrayLength=(pgDoctorList.referral).length;
			var comp=arrayLength-1;
			if(index==0)
				refId = curdoctorId;
			else
				refId=pgDoctorList.referral[index-1].id;
		}
	});
	return refId;	
}


/* function to get the  next curdoctorId from referrals table*/
function getnextDoctorId(curdoctorId, pgDoctorList) {
	var refId;
	$j(pgDoctorList.referral).each(function (index, rowReferral) {
		if(curdoctorId==rowReferral.id)	{
			var arrayLength=(pgDoctorList.referral).length;
			var comp=arrayLength-1;
			if(index==comp){
				refId = curdoctorId;
			}else{
				refId=pgDoctorList.referral[index+1].id;
			}	
		}
	});	
	return refId;	
}

function restoredoctorInfo(){
   $j('#doctorViewForm #viewGender').show();
	$j('#doctorViewForm #lblDoctorEditGender').hide();
	$j('#doctorViewForm #referralsdiv input[type=text],#doctorViewForm #referralsdiv textarea').addClass('newBox');
	$j('#doctorViewForm #referralsdiv input[type=text],#doctorViewForm #referralsdiv textarea').attr('readonly','readonly');
	$j('#doctorViewBtnSave').hide();
	$j('#doctorViewBtnCancel').hide();
	$j('#doctorViewBtnEditOne').show();
	$j('#referralGeneralPTBContainer #btn_up_ptb_id,#referralGeneralPTBContainer #btn_down_ptb_id,#referralGeneralPTBContainer #btn_back_ptb_id').show();	
}
function getDoctorId() {
	return $j("#doctorViewForm #doctorid label").text();
}