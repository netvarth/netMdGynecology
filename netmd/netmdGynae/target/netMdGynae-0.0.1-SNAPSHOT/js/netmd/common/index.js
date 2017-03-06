var GB_DOCID;
var usertype;
$j = jQuery.noConflict(); 
$j.ajaxSetup({async:false});
$j.cachedScript = function(url, options) {
  // allow user to set any option except for dataType, cache, and url
  options = $j.extend(options || {}, {
    dataType: "script",
    cache: true,
    url: url
  });
  // Use $j.ajax() since it is more flexible than $j.getScript
  // Return the jqXHR object so we can chain callbacks
  return $j.ajax(options);
};
var classLoader = new ClassLoader();
$j.cachedScript("/netmd/js/framework/login.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/constants.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/netmd/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/netmd/js/framework/general.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/common.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/ribbonEventHandler.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/framework/invokeUIControls.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/framework/createModal.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/validations.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/leftPaneEventhandler.js").done(function(script, textStatus) {
})
var errorData=getErrorData();
$j(document).ready(function(){
	createLeftpaneToolBar();
	$j('#tabs-1').removeClass('ui-widget-content');
	
	// Showing full username with specialization in dropdown for admin
	var user=getRequestData('/netmd/ws/ui/auth/getUser');
	if(user.name=="" ||user.name==null){
		location.reload();
		return false;
	}
	var userName=user.name;
	if (userName.indexOf('@') != -1) {
	  userName= userName.split("@")[0];
	 }
	$j('#userName').html(userName);
	   usertype=user.userType;
	
	//Checking for Admin or Doctor
	if(usertype=="admin"||usertype=="owner"){
		createGlobalToolBarFirstLogin();
		$j('#headleft').show();
		$j('#leftPaneMyProfile').hide();
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		fillDoctorAsAutoComplete("docSelect");	
		$j('#leftPaneSchedules,#leftPaneAppointments').hide();
		$j("#leftPaneDashBoard").trigger('click');
		
	}
	else{
		createGlobalToolBarForDoctor();
		$j('#headleft').hide();
		$j('#leftPaneReferrals').hide();
		$j('#ribbonNewDoctor').hide();
		$j('#leftPaneMyProfile').show();
		GB_DOCID = user.doctorId;
		$j("#leftPaneDashBoard").trigger('click');
	}
	$j.cachedScript("/netmd/js/classInitializer.js").done(function(script, textStatus) {
	}).fail(function(xhr,status,exception) {
		alert(xhr.status + exception);
	}) 	
	
		
	$j('#docGoBtn').die('click').live("click",function() {
		var doctorname=$j('#docSelect').val();
		if(doctorname==""){
			$j('#leftPaneSchedules,#leftPaneAppointments').hide();
			$j("#leftPaneDashBoard").trigger('click');
			createGlobalToolBarFirstLogin();
			alert("Please Select Atleast One Doctor");
			createError("Please Select Atleast One Doctor",$j('#newDoctorForm #email'));
			return false;
		}
		else{
			var GB_DOCID =getDoctorIdUsingName(doctorname);
			if(GB_DOCID=="false"){
				$j('#leftPaneSchedules,#leftPaneAppointments').hide();
				$j("#leftPaneDashBoard").trigger('click');
				createGlobalToolBarFirstLogin();
				alert("Please Select A valid Doctor");
				return false;
			}
			else{
				$j('#leftPaneSchedules,#leftPaneAppointments').show();
				createGlobalToolBar();
				$j("#leftPaneDashBoard").trigger('click');
			}
		}
		
	 });	
	 
	$j('#btnMyProfile').die('click').live("click",function() {	
			var user=getRequestData('/netmd/ws/ui/auth/getUser');
			$j.cachedScript(constants_ReferralProfile).done(function(script, textStatus) {
				})
			var obj=$j(this);
			createModal('/netmd/json/view/doctorProfile.json','docProfModal');		
			openModalBox(obj,'docProfModal')
			viewDoctor(user);
	});

	/* $j('#btnContactUs').die('click').live("click",function() {	
			var obj=$j(this);
			createModal('/netmd/json/view/contactUs.json','contactUs');		
			openModalBox(obj,'contactUs')
	}); */
		
});	



