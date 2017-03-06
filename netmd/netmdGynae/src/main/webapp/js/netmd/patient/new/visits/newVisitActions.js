
$j.cachedScript(constants_newVisitFunction).done(function(script, textStatus) {
})
$j(document).ready(function(){

$j('#newVisitForm #mrType').val("Personal visit");

fillVisitCaseName("#newVisitForm #caseName");

//Create Medical report
$j('#newVisitForm #btnVisitCreate').die('click').live('click',function(){
	removeErrors();
	if(validateNewVisit()){
	var exp=getExpressionOne(); 
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var tableObjTwo='#visitTable';
	var visitListJson = getFilterlistUrl(exp,(curPage-1),interval);
    var response = submitVisitInfo();
	//alert(JSON.stringify(response));
	if(response.success==true){
	$j('#newVisitForm').trigger('reveal:close');
	//resetVisitForm();
	showTip("Visit created successfully");	
    fillVisitTable(visitListJson,tableObjTwo);	
		} 
		else
			updateTipsNew(getErrorName(response.error),$j('#newVisitForm #errorDivNewVisit'),$j('#newVisitForm #errorDivHeader'));
	} 
	})

$j('#newVisitForm #btnVisitClear').die('click').click(function(){
		 removeErrors();
		 $j("#newVisitForm #medicalRecord input[type=text]").val("");
		 $j("#newVisitForm #medicalRecord input[type=password]").val("");	
         $j("#newVisitForm #medicalRecord input[type=text],textarea").val("");		 
		 //$j('#newVisitForm').trigger('reveal:close');	
	});	
	
	
});

function fillVisitCaseName(controlObj) {
   //alert("in fill casename select");
	var patientId = getPatientId();
	listPatient= getRequestData(constant_newCase_list_Url+patientId);	
	//alert(JSON.stringify(listPatient.caseList));
			$j(listPatient.caseList).each(function (patientIndex, patientObj) {	
			if(patientObj.caseStatus=="Open")
	             $j(controlObj).append('<option value="'+patientObj.id+'">'+patientObj.caseName+'</option>');
			});	
			
 }

function validateNewVisit(){
/*validation using regular expression*/
	var casename = $j("#newVisitForm #caseName");
	var mrectype = $j("#newVisitForm #mrType");
    var mrecord = $j("#newVisitForm #medicalRecord");
	
   	var bValid=true,casenameValid=true,mrectypeValid=true,mrecordValid=true;
  	casenameValid = checkNull( casename,constants_casenameRequired);
	mrectypeValid= checkNull( mrectype,constants_mrtypeRequired);
	mrecordValid= checkNull( mrecord,constants_mrecordRequired);
	return bValid;
}	 