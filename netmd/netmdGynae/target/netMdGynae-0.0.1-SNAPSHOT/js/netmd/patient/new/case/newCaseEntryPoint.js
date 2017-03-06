$j(document).ready(function(){
$j.cachedScript(constants_viewCaseAction).done(function(script, textStatus) {
})
	var caseTable=setCaseTableStructure();
	$j('#casesInfoTab').html(caseTable.html());
	getCaseListToolbar();

	setCustomDataTable('#casetbl');
	var exp=getExpressionTwo();
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var tableObj='#casetbl';
	var pgTableContainerTwo='#caseTableTableCont';
	var patientListJson = getFilterlistUrl(exp,(curPage-1),interval);
	pgpatientList=fillCaseTable(patientListJson,tableObj);
	
if(pgpatientList.count)
		maxRecords = pgpatientList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainerTwo);
	
	// Datatable operations next,previous,first,last Case Tbl
	$j(pgTableContainerTwo +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			patientListJson=getFilterlistUrl(exp,startValue,interval);
			pgMRecordList=fillCaseTable(patientListJson,tableObj);			
			setPaginationFields(curPage, maxPages,pgTableContainerTwo);
		}		
	});
	$j(pgTableContainerTwo +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			patientListJson=getFilterlistUrl(exp,startValue,interval);
			pgMRecordList=fillCaseTable(patientListJson,tableObj);				
			setPaginationFields(curPage, maxPages,pgTableContainerTwo);				
		}
	});
	$j(pgTableContainerTwo +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			patientListJson= getFilterlistUrl(exp,startValue,interval);
			pgMRecordList=fillCaseTable(patientListJson,tableObj);	
			setPaginationFields(curPage, maxPages, pgTableContainerTwo);
		}	
	});
	$j(pgTableContainerTwo +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			patientListJson=getFilterlistUrl(exp,startValue,interval);
			pgMRecordList=fillCaseTable(patientListJson,tableObj);			
			setPaginationFields(curPage, maxPages, pgTableContainerTwo);
		}
	});	

// Case container actions
$j('#patientCasePTBContainer #btn_new_ptb_id').die('click').live("click",function() {
		var obj=$j(this);
	    removeErrors();	
		createModal(constant_newCase_Json,'caseModal');		
		openModalBox(obj,'caseModal')
		$j.cachedScript(constants_newCaseAction).done(function(script,textStatus) {
		})
	});
	
$j('#patientCasePTBContainer #btn_view_ptb_id').die('click').live("click",function() {
        var pgTableName = "#casetbl";
		var caseId= getSelectedCaseId(pgTableName);
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		if(caseId!="") {
			setCaseJson();
			getCaseViewToolbar();
			viewCaseInfo(caseId);
			viewMedRecordInCase();
		}
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
		
	});
	


$j('.caseIdCol').die('click').live('click',function(){
	   var caseId= $j(this).parent().attr('id');
	   var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		setCaseJson();
		getCaseViewToolbar();
	    viewCaseInfo(caseId);
		viewMedRecordInCase();
		$j('#pageTitle').html("View Case"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});
	
$j("#casetbl" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});



});	
function getSelectedCaseId(pgTableName) {
		var caseId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selcase = $j(pgTableName + ' tbody tr[selected]');
			if(selcase.length==0){		
				updateTipsNew("Select atleast one user",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selcase.length>1) 
				updateTipsNew("Select only one user",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				caseId=selcase.attr('id');
		}	
		return caseId;
	}
function fillCaseTable(patientListJson,tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	listPatient= postdataToServer("/netmd/ws/ui/patient/listOfCase/",patientListJson);
	//alert(JSON.stringify(listPatient));
		if(listPatient.caseList.length>0) {	
			$j(listPatient.caseList).each(function (patientIndex, patientObj) {
				var rowData=$j(tableObj).dataTable().fnAddData([patientObj.id,patientObj.caseName,patientObj.date,patientObj.caseStatus,patientObj.shortDesc.slice(0,15)]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',patientObj.id);	
				$j(row).children("td:nth-child(1)").attr("class","caseIdCol Ustyle");
			});			
		}
	return  listPatient;
 }
 
 function setCaseTableStructure() {
	var tblData = getRequestData('/netmd/json/list/caseTable.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
} 
	
function getExpressionTwo(){
	var patientId=getPatientId();
	var listJsonForDoctor='{"name":"patientId","value":"'+patientId+'","operator":"eq"}';
	return listJsonForDoctor;
}
//Creating Case Tool Bar
function getCaseListToolbar() {
	var caseContainer = $j('<div id="patientCasePTBContainer" />');
	var casedata =getRequestData('/netmd/json/toolbars/patientCaseToolBar.json');
	var pctb = new PageToolBar(casedata);
	$j(caseContainer).html(pctb.result);
	$j('#patientCaseToolbarDiv').html(caseContainer);
}

function getCaseViewToolbar() {
    //alert("Case view Toolbar");
	var caseContainer = $j('<div id="patientCasePTBContainer" />');
	var casedata =getRequestData('/netmd/json/toolbars/viewGeneralToolbar.json');
	var pctb = new PageToolBar(casedata);
	$j(caseContainer).html(pctb.result);
	$j('#caseViewForm #patientCaseViewToolbarDiv').html(caseContainer);
}

