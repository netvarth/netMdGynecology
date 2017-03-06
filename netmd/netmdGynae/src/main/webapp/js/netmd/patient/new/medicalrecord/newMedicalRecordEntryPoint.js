
$j(document).ready(function(){
$j.cachedScript(constants_newMedicalRecordAction).done(function(script, textStatus) {
})
	var mrTable=setmedicalRecordTableStructure();
	$j('#medicalRecordInfoTab').html(mrTable.html());
	getMedReportToolbar();
	
	setCustomDataTable('#medRecordTbl');
	
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var tableObjOne='#medRecordTbl';
	var pgTableContainer='#medicalReportTableCont';
	var expr=getExpression();
	var medReportListJson = getFilterlistUrl(expr,(curPage-1),interval);
	pgMRecordList=fillMedReportTable(medReportListJson,tableObjOne);
	if(pgMRecordList.count)
		maxRecords = pgMRecordList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	// Datatable operations next,previous,first,last MRecord Tbl
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			medReportListJson=getFilterlistUrl(expr,startValue,interval);
			pgMRecordList=fillMedReportTable(medReportListJson,tableObjOne);			
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			medReportListJson=getFilterlistUrl(expr,startValue,interval);
			pgMRecordList=fillMedReportTable(medReportListJson,tableObjOne);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			medReportListJson= getFilterlistUrl(expr,startValue,interval);
			pgMRecordList=fillMedReportTable(medReportListJson,tableObjOne);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			medReportListJson=getFilterlistUrl(expr,startValue,interval);
			pgMRecordList=fillMedReportTable(medReportListJson,tableObjOne);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	
	
	
$j('#patientMedReportPTBContainer #btn_new_ptb_id').die('click').live("click",function() {
		var obj=$j(this);
	    removeErrors();	
		createModal(constant_newMedicalReport_Json,'medReportModal');		
		openModalBox(obj,'medReportModal')
		$j.cachedScript(constants_newMedicalRecordAction).done(function(script,textStatus) {
		})
	});
	
$j('#patientMedReportPTBContainer #btn_view_ptb_id').die('click').live("click",function() {
        var pgTableName = "#medRecordTbl";
		var medReportId= getSelectedRecordId(pgTableName);
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		if(medReportId!="") {
		   $j.cachedScript(constant_viewMedicalReport_Action).done(function(script, textStatus) {
		   setmedReportJson();
		   getmedReportViewToolbar();
	       viewMedReportInfo(medReportId);
			})
		}
		$j('#pageTitle').html("View Medical Record"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});	
	
$j('.mRecordIdCol').die('click').live('click',function(){
	   var medReportId= $j(this).parent().attr('id');
	   var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		 setmedReportJson();
		 getmedReportViewToolbar();
	    viewMedReportInfo(medReportId);
		$j('#pageTitle').html("View Medical Record"+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
	});
	
$j("#medRecordTbl" + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	}); 
});

function getSelectedRecordId(pgTableName) {
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
	


 
 
 function setmedicalRecordTableStructure() {
	var tblData = getRequestData('/netmd/json/list/medicalReportTable.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
 }
 
 function setmedReportJson() {
 $j('#pageTitle').html("View Medical Report");
 var viewdata = getRequestData('/netmd/json/view/viewMedicalRecord.json');
	var contentForm = new form(viewdata);
	$j('#medicalRecordInfoTab').html(contentForm.result);	
 }
 
 
function getMedReportToolbar() {
	var medReportContainer = $j('<div id="patientMedReportPTBContainer" />');
	var meddata =getRequestData('/netmd/json/toolbars/patientmedReportToolBar.json');
	var pctb = new PageToolBar(meddata);
	$j(medReportContainer).html(pctb.result);
	$j('#medReportToolbarDiv').html(medReportContainer);
}

function getmedReportViewToolbar() {
    //alert("Med Record Toolbar");
	var medReportContainer = $j('<div id="patientMedReportPTBContainer" />');
	var meddata =getRequestData('/netmd/json/toolbars/viewGeneralToolbar.json');
	var pctb = new PageToolBar(meddata);
	$j(medReportContainer).html(pctb.result);
	$j('#medicalRecordViewForm #patientMedRecordToolbarDiv').html(medReportContainer);
}