$j(document).ready(function(){
    $j("#ribbonNewDoctor").die('click').live("click",function() {
		removeErrors();
		$j("#leftPaneReferrals").trigger('click');
		$j("#btn_new_ptb_id").trigger('click');
	});
	
	 $j("#ribbonNewPatient").die('click').live("click",function() {
		removeErrors();
		$j("#leftPanePatients").trigger('click');
		$j("#btn_new_ptb_id").trigger('click');
	});
	
	$j("#ribbonNewSchdule").die('click').live("click",function() {
		removeErrors();
		$j("#leftPaneSchedules").trigger('click');
		//$j("#btn_new_ptb_id").trigger('click');
	});
	
	$j("#ribbonNewAppointment").die('click').live("click",function() {
		removeErrors();
		$j("#leftPaneAppointments").trigger('click');
		//$j("#btn_new_ptb_id").trigger('click');
	});
	$j("#ribbonNewBill").die('click').live("click",function() {
		$j('#pageToolBar-Container').show();
		var obj=$j(this);
		removeErrors();
		var billUI = new BillUIStartup();			
		billUI.init();
		billUI.createBillModal(obj);
		
	});
	
	
	$j("#ribbonNewMessage").die('click').live("click",function() {
		removeErrors();
		$j("#leftPaneMessages").trigger('click');
		//$j("#btn_new_ptb_id").trigger('click');
	});
	
});	
	
	