$j('#pageTitle').html(constant_PatientList_Msg);
$j.cachedScript(constant_PatientFunctions_Url).done(function(script, textStatus) {
})

$j(document).ready(function(){
	var exp=getExpression();
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var patientListJson = getFilterlistUrl(exp,(curPage-1),interval);
	var pgTableName = "#patients"; // Table showing patient list
	pgTableContainer = "#patientTableCont"; // Parent container of the patient list table
	pgPatientList =viewPatientList(patientListJson,pgTableName);
	
	if(pgPatientList.count)
		maxRecords = pgPatientList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	$j('.patientIdCol').die('click').live('click',function(){
	    var patientId= $j(this).parent().attr('id');
		if(patientId!="") {
			$j.cachedScript(constants_ViewPatientEntryPoint).done(function(script, textStatus) {
				viewPatient(patientId);
				var patientInfo = getPatientData(patientId);
				$j('#pageTitle').html(constant_PatientView_Msg+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
			})
		}	
	});

	$j(pgTableName + ' tbody tr').die('click').live('click',function(){	
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	

	$j('#patientPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
		var patientId = getSelectedPatientId(pgTableName);
		if(patientId!="") {
		//$j('#patient-filter-wb').hide();
			$j.cachedScript(constants_ViewPatientEntryPoint).done(function(script, textStatus) {
				viewPatient(patientId);
			})
			}
			var patientInfo = getPatientData(patientId);
			$j('#pageTitle').html(constant_PatientView_Msg+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});

	$j('#patientPTBContainer #btn_delete_ptb_id').die('click').live("click",function() {	
		removeErrors();
		var patientId = getSelectedPatientId(pgTableName);
		//alert(patientId);
		if(patientId!="") {
			var response = getRequestData("/netmd/ws/ui/patient/delete/"+ patientId);
			//alert(JSON.stringify(response));
			if(response.success==true){
				$j(pgTableName).dataTable().fnDeleteRow($j('#'+patientId).closest('tr')[0]);	
				showTip("Patient deleted successfully");				
			}	
			else
				updateTipsNew(getErrorName(response.error),$j('#errorDivData'),$j('#errorDivHeader'));
		}
	});	
	
	$j('#patientPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
		var obj=$j(this);
	    //removeErrors();	
		createModal(constants_newPatientJson,'patientModal');		
		openModalBox(obj,'patientModal')
		$j.cachedScript(constants_newPatientEntryPoint).done(function(script,textStatus) {
		})
	});
	
	//function
	function getSelectedPatientId(pgTableName) {
		var patientId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selPatients = $j(pgTableName + ' tbody tr[selected]');
			if(selPatients.length==0){		
				updateTips("Select atleast one patient",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selPatients.length>1) 
				updateTips("Select only one patient",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				patientId=selPatients.attr('id');
		}	
		return patientId;
	}

// Filter tool Bar Operation
	
	$j('#patient-filter-toolbar  a:not(:selected)').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		$j('#lst'+curObjName).show();
		$j('#txt'+curObjName).show();
		$j('#txt'+curObjName).focus();
		setReportFilterValues(curObjName);
	});
	
	$j('#patient-filter-toolbar a[selected]').die('click').live("click",function(){
		var curObjName = $j(this).attr('name');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		$j('#txt'+curObjName).hide();
		$j('#lst'+curObjName).hide();
		$j('#patient-filter-toolbar #btnGo').trigger('click');
	});
	
	$j('#patient-filter-toolbar #btnGo').die('click').click(function() {
		removeErrors();
		expFilter=[];
		$j('#patient-filter-toolbar a[selected]').each(function(){
			var selName=$j(this).attr('name');
			var selTextValue=$j("#txt"+ selName).val();
			var selOperator = $j("#lst"+ selName).val();
			expFilter.push('{"name":"' + selName + '","value":"' + selTextValue + '","operator":"' + selOperator  + '"}');
		});	
		startValue=0;
		curPage =1;
		referralListJson=getFilterlistUrl(expFilter,startValue,interval);
		pgReferralList=fillPatientTable(referralListJson,pgTableName);	
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
		var filterArray = $j('#patient-filter-toolbar.button_filter');
		$j(filterArray).each(function(){
			var element = $j(this);
			element.removeClass('button_filter');
			element.removeAttr('selected');
			$j('#patient-filter-wb').hide();
		});
	});


$j(pgTableContainer +' #next').die('click').click(function() {	
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			patientListJson=getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillPatientTable(patientListJson,pgTableName);			
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			patientListJson=getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillPatientTable(patientListJson,pgTableName);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			patientListJson= getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillPatientTable(patientListJson,pgTableName);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			patientListJson=getFilterlistUrl(exp,startValue,interval);
			pgReferralList=fillPatientTable(patientListJson,pgTableName);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	

});	