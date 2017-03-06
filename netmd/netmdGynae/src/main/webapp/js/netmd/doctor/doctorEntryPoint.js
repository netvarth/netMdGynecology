
 $j('#pageTitle').html(constant_ReferralsList_Msg);
$j.cachedScript(constant_ReferralFunctions_Url).done(function(script, textStatus) {
})
$j.cachedScript(constants_ViewReferralEntryPoint).done(function(script, textStatus) {
})

$j(document).ready(function() {
	var exp=getExpression();
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var referralListJson = getFilterlistUrl(exp,(curPage-1),interval);
	var pgTableName = "#referrals"; // Table showing referral list
	pgTableContainer = "#referralTableCont"; // Parent container of the referral list table
	pgReferralList=viewReferralList(referralListJson,pgTableName);
	if(pgReferralList.count)
		maxRecords = pgReferralList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	$j('.referralIdCol').die('click').live('click',function(){
	   var doctorId= $j(this).parent().attr('id');
	   if(doctorId!="") {
			$j('#referral-filter-wb').hide();
			$j.cachedScript(constants_ViewReferralEntryPoint).done(function(script, textStatus) {
				viewDoctor(doctorId);
			})
		}	
	});
	
	//To select row from the table
	$j("#referrals" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	

	$j('#referralPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
		var doctorId= getSelectedReferralId(pgTableName);
		if(doctorId!="") {
			$j('#referral-filter-wb').hide();
			$j.cachedScript(constants_ViewReferralEntryPoint).done(function(script, textStatus) {
				viewDoctor(doctorId);
			})
		}
	});

	$j('#referralPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {	
		removeErrors();
		var doctorId = getSelectedReferralId(pgTableName);
		if(doctorId!="") {
			var response = getRequestData('/netmd/ws/ui/doctor/delete/' + doctorId);
			if(response.success==true){
				 $j.cachedScript(constant_ReferralEntry_Url).done(function(script, textStatus) {
			    })
				showTip("Doctor deleted successfully");
				fillDoctorListForAdmin('#docSelect');
				defaultData=getDefaultData();
			}	
			else
				updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
	
	$j('#referralPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
		var obj=$j(this);
        //removeErrors();		
		createModal(constants_newDoctorJson,'referralModal');		
		openModalBox(obj,'referralModal')
		$j.cachedScript(constants_newReferralEntryPoint).done(function(script, textStatus) {
		})
	});
	
	//function to get the selected id from table
	function getSelectedReferralId(pgTableName) {
		var doctorId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selFacilities = $j(pgTableName + ' tbody tr[selected]');
			if(selFacilities.length==0){		
				updateTips("Select atleast one doctor",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selFacilities.length>1) 
				updateTips("Select only one doctor",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				doctorId=selFacilities.attr('id');
		}		
		return doctorId;
	}
	
 // Filter tool Bar Operations

$j('#referral-filter-toolbar  a:not(:selected)').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		setReportFilterValues(curObjName);
	});
	
	$j('#referral-filter-toolbar a[selected]').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txt'+curObjName).hide();
		$j('#lst'+curObjName).hide();
		$j('#referral-filter-toolbar #btnGo').trigger('click');
	});
	
	$j('#referral-filter-toolbar #btnGo').die('click').click(function() {
		removeErrors();
		expFilter=[];
		$j('#referral-filter-toolbar a[selected]').each(function(){
			var selName=$j(this).attr('name');
			var selTextValue=$j("#txt"+ selName).val();
			var selOperator = $j("#lst"+ selName).val();
			expFilter.push('{"name":"' + selName + '","value":"' + selTextValue + '","operator":"' + selOperator  + '"}');
		});	
		startValue=0;
		curPage =1;
		referralListJson=getFilterlistUrl(expFilter,startValue,interval);
		pgReferralList=fillDoctorTable(referralListJson,pgTableName);	
		if(pgReferralList.count)
			maxRecords = pgReferralList.count;	
		if(maxRecords%interval!=0)
			maxPages = parseInt(maxRecords/interval) + 1;
		else
			maxPages = parseInt(maxRecords/interval);	
		setPaginationFields(curPage, maxPages, pgTableContainer);
	});
	
	$j('#filter').die('click').live('click',function(){
		removeErrors();
		var filterArray = $j('#referral-filter-toolbar .button_filter');
		$j(filterArray).each(function(){
			var element = $j(this);
			element.removeClass('button_filter');
			element.removeAttr('selected');
			$j('#referral-filter-wb').hide();
		});
	});   

	
$j(pgTableContainer +' #next').die('click').click(function() {	
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			referralListJson=getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillDoctorTable(referralListJson,pgTableName);			
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			referralListJson=getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillDoctorTable(referralListJson,pgTableName);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			referralListJson= getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillDoctorTable(referralListJson,pgTableName);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			referralListJson=getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillDoctorTable(referralListJson,pgTableName);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});  

});	
