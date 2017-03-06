function fillCaseStatusType(controlObj) {
		$j(controlObj).empty();
		$j(controlObj).append('<option value="Open">OPEN</option><option value="On Hold">ON HOLD</option><option value="Closed">CLOSED</option>');
	 }
	 
	 
function viewCaseInfo(caseId) {
var caseInfo = getCaseData(caseId);
//alert(JSON.stringify(caseInfo));
    if(caseInfo.id)
		$j("#caseViewForm #viewCaseId label").text(caseInfo.id);
	else
		$j("#caseViewForm #viewCaseId label").text('Nil');
	if(caseInfo.date)
		$j("#caseViewForm #viewCaseDate label").text(caseInfo.date);
	else
		$j("#caseViewForm #viewCaseDate label").text('Nil');
	if(caseInfo.caseStatus)
		$j("#caseViewForm #CaseStatusLabel label").text(caseInfo.caseStatus);
	else
		$j("#caseViewForm #CaseStatusLabel label").text('Nil');
	if(caseInfo.caseName)
		$j("#caseViewForm #viewCaseName label").text(caseInfo.caseName);
	else
		$j("#caseViewForm #viewCaseName label").text('Nil');
	if(caseInfo.shortDesc)
		$j("#caseViewForm #viewshortDescription").val(br2nl(caseInfo.shortDesc));
	else
		$j("#caseViewForm #viewshortDescription").val('Nil');	
	if(caseInfo.longDesc)
		$j("#caseViewForm #viewlongDescription").val(br2nl(caseInfo.longDesc));
	else
		$j("#caseViewForm #viewlongDescription").val('Nil');	
}

function submitCaseInfoUrl(){
	var resultJson = createSubmitJsonForCase();
	//alert(resultJson);
	var response = postdataToServer("/netmd/ws/ui/patient/updateCase", resultJson );	
	//alert(JSON.stringify(response));
	return response;
}

function createSubmitJsonForCase(){
var patID = getPatientId();
	var caseDetails = '{"caseName":"'+$j('#caseViewForm #viewCaseName label').text()+'",';
	    caseDetails += '"id":'+$j('#caseViewForm #viewCaseId label').text()  +',';
		caseDetails += '"patientId":'+patID +',';
		caseDetails +='"shortDesc":"'+nl2br($j('#caseViewForm #viewshortDescription').val())  +'",';
		caseDetails +='"caseStatus":"'+$j('#caseViewForm #CaseStatusSelect').val() +'",';
		caseDetails +='"date":"'+$j('#caseViewForm #viewCaseDate label').text()+'",';
		caseDetails +='"longDesc":"'+nl2br($j('#caseViewForm #viewlongDescription').val())  +'"}';
		return caseDetails;
}

function getCaseData(caseId) {
	var response=getRequestData(constant_newCase_View_Url+caseId);
	return response;
}

function restoreCaseInfo(caseId){
//alert("Nithesh");
	$j('#caseViewForm input[type=text],#caseViewForm textarea').addClass('newBox');
	$j('#caseViewForm input[type=text],#caseViewForm textarea').attr('readonly','readonly');
	$j('#btnCaseSave').hide();
	$j('#btnCaseCancel').hide();
	$j('#btnCaseEdit').show();
	$j('#patientCasePTBContainer #btn_up_ptb_id,#patientCasePTBContainer #btn_down_ptb_id,#patientCasePTBContainer #btn_back_ptb_id').show();	
}

function getCaseIdMed() {
	return $j("#caseViewForm #viewCaseIdMed label").text();
}


function getCaseId() {
	return $j("#caseViewForm #viewCaseId label").text();
}

function viewMedRecordInCase() {

	var mrTable=setmedicalRecordTableStructureInCase();
	$j('#viewMedicalRecordTblDiv').html(mrTable.html());
	setCustomDataTable('#medRecordTbl');
	
	var exprs=getExpressions();
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var tableObjOne='#medRecordTbl';
	var pgTableContainer='#medicalReportTableTableCont';
	var medReportListInCaseJson = getFilterlistUrl(exprs,(curPage-1),interval);
	//alert(medReportListInCaseJson);
	
    pgMRecordIncase=fillMedReportTableInCase(medReportListInCaseJson,tableObjOne);
    
	if(pgMRecordIncase.count)
		maxRecords = pgMRecordIncase.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	
	// Datatable operations next,previous,first,last MRecord Tbl
	$j(pgTableContainer +' #next').die('click').click(function() {
	//alert("Clicked");
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			medReportListInCaseJson=getFilterlistUrl(exprs,startValue,interval);
			pgMRecordList=fillMedReportTableInCase(medReportListInCaseJson,tableObjOne);			
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			medReportListInCaseJson=getFilterlistUrl(exprs,startValue,interval);
			pgMRecordList=fillMedReportTableInCase(medReportListInCaseJson,tableObjOne);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			medReportListInCaseJson= getFilterlistUrl(exprs,startValue,interval);
			pgMRecordList=fillMedReportTableInCase(medReportListInCaseJson,tableObjOne);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			medReportListInCaseJson=getFilterlistUrl(exprs,startValue,interval);
			pgMRecordList=fillMedReportTableInCase(medReportListInCaseJson,tableObjOne);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	
}

function resetnewCaseForm() {
	removeErrors();
	$j("#caseViewForm input[type=text], textarea").val("");
    $j("#caseViewForm  input[type=text],#caseViewForm input[type=password]").val("");
	$j("#CaseName").focus();
}

function setCaseJson() {
 $j('#pageTitle').html("View Case");
 var viewdata = getRequestData('/netmd/json/view/viewCase.json');
	var contentForm = new form(viewdata);
	$j('#casesInfoTab').html(contentForm.result);	
 }
 
function fillMedReportTableInCase(medReportListJson,tableObjOne) {
//alert("hiii"+medReportListJson);
	var patientId = getPatientId();
	var caseId = getCaseIdMed();
	$j(tableObjOne).dataTable().fnClearTable();
	//listmedReport= getRequestData(constants_viewMedRecordInCaseView_URL+patientId+','+caseId);
	listPatient= postdataToServer("/netmd/ws/ui/patient/listOfMedicalRecord/",medReportListJson);
	//alert(JSON.stringify(listPatient));
		if(listPatient.medicalList.length>0) {	
			$j(listPatient.medicalList).each(function (patientIndex, mRecordObj) {
				var rowData=$j(tableObjOne).dataTable().fnAddData([mRecordObj.id,mRecordObj.type,mRecordObj.medicalRecord]);
				var row=$j(tableObjOne).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',mRecordObj.id);	
				$j(row).children("td:nth-child(1)").attr("class","mRecordIdCol Vstyle");
			});	
				
  }
 return  listPatient;
 }
 
function getExpressions(){
var patientId=getPatientId();
var caseID=$j('#caseViewForm #viewCaseId label').text();
	var listJsonForDoctor='{"name":"doctorId","value":"'+GB_DOCID+'","operator":"eq"}'+','+
	'{"name":"patientId","value":"'+patientId+'","operator":"eq"}'+','+
	'{"name":"caseId","value":"'+caseID+'","operator":"eq"}'+','+
	'{"name":"type","value":"Personal visit","operator":"neq" }';
	return listJsonForDoctor;
} 

function setmedicalRecordTableStructureInCase() {
	var tblData = getRequestData('/netmd/json/list/medicalReportTableInCase.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
}

function getpreviousCaseId(caseID, pgpatientList) {
	var patId;
	$j(pgpatientList.caseList).each(function (index, rowpatient) {
		if(caseID==rowpatient.id)	{
			var arrayLength=(pgpatientList.caseList).length;
			var comp=arrayLength-1;
			if(index==0)
				patId = caseID;
			else
				patId=pgpatientList.caseList[index-1].id;
		}
	});
	return patId;	
}
/* function to get the  next patientId from patients table*/
function getnextCaseId(caseID, pgpatientList) {
	var patId;
	$j(pgpatientList.caseList).each(function (index, rowpatient) {
		if(caseID==rowpatient.id)	{
			var arrayLength=(pgpatientList.caseList).length;
			var comp=arrayLength-1;
			if(index==comp){
				patId = caseID;
			}else{
				patId=pgpatientList.caseList[index+1].id;
			}	
		}
	});	
	return patId;	
}