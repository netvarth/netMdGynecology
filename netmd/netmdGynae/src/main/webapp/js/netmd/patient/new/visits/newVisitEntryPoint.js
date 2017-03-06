
$j(document).ready(function(){
$j.cachedScript(constants_newVisitAction).done(function(script, textStatus) {
})
   

	var visitTable=setVisitTableStructure();
	$j('#visitsInfoTab').html(visitTable.html());
	getVisitListToolbar();
	setCustomDataTable('#visitTable');
	
	var express=getExpressionOne();
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page 
	var tableObjTwo='#visitTable';
	var pgTableContainerOne='#visitTableTableCont';
	
	var visitListJson = getFilterlistUrl(express,(curPage-1),interval);
	 pgVisitList=fillVisitTable(visitListJson,tableObjTwo);	
	 if(pgVisitList.count)
		maxRecords = pgVisitList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainerOne);
	
	// Datatable operations next,previous,first,last Visit Tbl
	$j(pgTableContainerOne +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			visitListJson=getFilterlistUrl(express,startValue,interval);
			pgMRecordList=fillVisitTable(visitListJson,tableObjTwo);			
			setPaginationFields(curPage, maxPages,pgTableContainerOne);
		}		
	});
	$j(pgTableContainerOne +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			visitListJson=getFilterlistUrl(express,startValue,interval);
			pgMRecordList=fillVisitTable(visitListJson,tableObjTwo);				
			setPaginationFields(curPage, maxPages,pgTableContainerOne);				
		}
	});
	$j(pgTableContainerOne +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			visitListJson= getFilterlistUrl(express,startValue,interval);
			pgMRecordList=fillVisitTable(visitListJson,tableObjTwo);	
			setPaginationFields(curPage, maxPages, pgTableContainerOne);
		}	
	});
	$j(pgTableContainerOne +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			visitListJson=getFilterlistUrl(express,startValue,interval);
			pgMRecordList=fillVisitTable(visitListJson,tableObjTwo);			
			setPaginationFields(curPage, maxPages, pgTableContainerOne);
		}
	});



// medical Rport Container
$j('#patientVisitPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
		var obj=$j(this);
	    removeErrors();	
		createModal(constant_newVisit_Json,'visitsModal');		
		openModalBox(obj,'visitsModal')
		$j.cachedScript(constants_newVisitEntry).done(function(script,textStatus) {
		})
	});
	
	$j('#patientVisitPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
        var pgTableName = "#visitTable";
		var medReportId= getSelectedVisitId(pgTableName);
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		if(medReportId!="") {
			//$j('#referral-filter-wb').hide();
		   $j.cachedScript(constants_viewVisitEntry).done(function(script, textStatus) {
		   setVisitJson();
		   getVisitViewToolbar();
	       viewVisitInfo(medReportId);
			})
		}
		$j('#pageTitle').html("View Visit"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});
	
	
$j('.visitCol').die('click').live('click',function(){
	   var medReportId= $j(this).parent().attr('id');
	   var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		 setVisitJson();
		 getVisitViewToolbar();
	    viewVisitInfo(medReportId);
		$j('#pageTitle').html("View Visit"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});
	
$j("#visitTable" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});
	
});


function setVisitTableStructure() {
	var tblData = getRequestData('/netmd/json/list/visitTable.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
}


function getSelectedVisitId(pgTableName) {
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
	
// functions for  Med Report view
 function setVisitJson() {
 $j('#pageTitle').html("View Visit");
 var viewdata = getRequestData('/netmd/json/view/viewVisit.json');
	var contentForm = new form(viewdata);
	$j('#visitsInfoTab').html(contentForm.result);	
 }	
	
function getVisitListToolbar() {
    //alert("Med Record Toolbar");
	var visitContainer = $j('<div id="patientVisitPTBContainer" />');
	var meddata =getRequestData('/netmd/json/toolbars/patientCaseToolBar.json');
	var pctb = new PageToolBar(meddata);
	$j(visitContainer).html(pctb.result);
	$j('#patientVisitToolbarDiv').html(visitContainer);
}

function getVisitViewToolbar() {
    //alert("visit view Toolbar");
	var visitContainer = $j('<div id="patientVisitPTBContainer" />');
	var meddata =getRequestData('/netmd/json/toolbars/viewGeneralToolbar.json');
	var pctb = new PageToolBar(meddata);
	$j(visitContainer).html(pctb.result);
	$j('#patientViewVisitToolbarDiv').html(visitContainer);
}

