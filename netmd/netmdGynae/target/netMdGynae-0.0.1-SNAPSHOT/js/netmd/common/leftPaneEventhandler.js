	// Left pane tab click events
	
	$j("#leftPaneReferrals").die('click').live("click",function() {	
			//alert("Doctor Clicked");
			$j('#tabs-schedule').hide();
			$j('#tabs-appointment').hide();
			$j('#filterToolBar-Container').hide();
			$j('#pageToolBar-Container').show();
			$j('#tabs-1').show();
			$j.cachedScript(constant_ReferralEntry_Url).done(function(script, textStatus) {
			}) 
	});
	$j("#leftPaneBills").die('click').live('click',function(){
			$j('#tabs-schedule').hide();
			$j('#tabs-appointment').hide();
			$j('#pageToolBar-Container').show();
			$j('#tabs-1').show();
			removeErrors();
			var billUI = new BillUIStartup();			
			billUI.init(); 
	});
	$j('#leftPaneDashBoard').die('click').live("click",function() {	
	      $j.cachedScript(constant_dashboardEntry_Url).done(function(script, textStatus) {
		  })
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#pageToolBar-Container').hide();
		$j('#filterToolBar-Container').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#tabs-1').show();
	});
	
	$j('#leftPaneMyProfile').die('click').live("click",function() {
		var user=getRequestData('/netmd/ws/ui/auth/getUser');
		GB_DOCID = user.doctorId;
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
		$j('#tabs-1').show();
		if(GB_DOCID!="") {
			$j('#referral-filter-wb').hide();
			$j.cachedScript(constants_ViewReferralEntryPoint).done(function(script, textStatus) {
				viewDoctor(GB_DOCID);
				$j('#pageToolBar-Container').hide();
			})
		}	
		
	});
		
	
	
	$j('#leftPaneResults').die('click').live("click",function() {
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
		$j('#tabs-1').show();
		$j('#pageToolBar-Container').show();
			$j.cachedScript("/netmd/js/netmd/result/newResultFunctions.js").done(function(script, textStatus) {
			})
	});
	
	$j('#leftPaneSchedules').die('click').live("click",function() {	
	if(usertype=="admin"||usertype=="owner"){
			var doctorname=$j('#docSelect').val();
			GB_DOCID =getDoctorIdUsingName(doctorname);
		}
		else{
			var user=getRequestData('/netmd/ws/ui/auth/getUser');
			GB_DOCID = user.doctorId;
			}
		$j('#tabs-appointment').empty();
		$j('#tabs-appointment').hide();
		$j('#tabs-1').hide();
		$j('#filter').hide();
		$j('#filterToolBar-Container').hide();
		$j('#filterWorkBench').hide();
		$j('#tabs-schedule').show();
		$j('#pageToolBar-Container').show();	
			$j.cachedScript("/netmd/js/netmd/schedule/schedule.js").done(function(script, textStatus) {
			})
	});
	
	$j('#leftPaneAppointments').die('click').live("click",function() {
		if(usertype=="admin"||usertype=="owner"){
			var doctorname=$j('#docSelect').val();
			GB_DOCID =getDoctorIdUsingName(doctorname);
		}
		else{
			var user=getRequestData('/netmd/ws/ui/auth/getUser');
			GB_DOCID = user.doctorId;
			}
		$j('#tabs-schedule').empty();
		$j('#tabs-schedule').hide();
		$j('#tabs-1').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
		$j('#tabs-appointment').show();
		$j('#pageToolBar-Container').show();

			$j.cachedScript("/netmd/js/netmd/appointment/appointmentEntryPoint.js").done(function(script, textStatus) {
			})
	});
	
	$j('#leftPaneMessages').die('click').live("click",function() {	
	$j('#tabs-schedule').hide();
	$j('#tabs-appointment').hide();
	$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
	$j('#tabs-1').show();
	$j('#pageToolBar-Container').show();
		$j.cachedScript("/netmd/js/netmd/messages/messagesEntryPoint.js").done(function(script, textStatus) {
		})
	});
	
	$j("#leftPaneSettings").die('click').live("click",function() {
		removeErrors();
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
		$j('#tabs-1').show();
		$j('#pageToolBar-Container').show();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	});
	$j("#leftPaneAccounts").die('click').live("click",function() {
		removeErrors();
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
		$j('#tabs-1').show();
		$j('#pageToolBar-Container').show();
		var ExpenseUI = new ExpenseUIStartup();			
		ExpenseUI.init(); 
		}) 
	$j("#leftPaneReports").die('click').live("click",function() {
		removeErrors();
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#filter').hide();
		$j('#filterWorkBench').hide();
		$j('#filterToolBar-Container').hide();
		$j('#tabs-1').show();
		$j('#pageToolBar-Container').hide();
		var ReportUI=new ReportUIStartup();
		ReportUI.init();
	});
	
	/*modifiying patient ui*/
	
	$j('#leftPanePatients').die('click').live("click",function() {	
	
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#tabs-1').show();
		$j('#pageToolBar-Container').show();
		var patientUI = new PatientUIStartup();			
		patientUI.init(); 
	});
	
	$j('#leftPaneCaseList').die('click').live("click",function() {	
		$j('#tabs-schedule').hide();
		$j('#tabs-appointment').hide();
		$j('#tabs-1').show();
		$j('#pageToolBar-Container').show();
		var caseUI = new CaseListUIStartup();			
		caseUI.init(); 
	});