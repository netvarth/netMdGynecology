$j.cachedScript(constants_newMedicalRecordFunction).done(function(script, textStatus) {
})
$j(document).ready(function(){

fillMedReportType("#newMedicalRecordForm #mrType");	 

fillMedReportCaseName("#newMedicalRecordForm #caseName");	 
	 
//Create Medical report
$j('#newMedicalRecordForm #btnMedReportCreate').die('click').live('click',function(){
removeErrors();
if(validateNewMrecord()){
	var exp=getExpression();
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var tableObjOne='#medRecordTbl';
	var medReportListJson = getFilterlistUrl(exp,(curPage-1),interval);
	var response = submitMedReportInfo();
		if(response.success==true){
		 $j('#newMedicalRecordForm').trigger('reveal:close');
		 //resetnewMedReportForm();
		 showTip("Medical Report created successfully");		 
	     fillMedReportTable(medReportListJson,tableObjOne);	
		} 
		else
			updateTipsNew(getErrorName(response.error),$j('#newMedicalRecordForm #errorDivNewMRecord'),$j('#newMedicalRecordForm #errorDivHeader'));
	}
	})	
	
$j('#newMedicalRecordForm #btnMedReportClear').die('click').click(function(){
		 removeErrors();
		 $j("#newMedicalRecordForm input[type=text]").val("");
		 $j("#newMedicalRecordForm input[type=password]").val("");	
         $j("#newMedicalRecordForm input[type=text],textarea").val("");		 
		 //$j('#newMedicalRecordForm').trigger('reveal:close');	
	});	
})		


function validateNewMrecord(){
/*validation using regular expression*/
	var casename = $j("#newMedicalRecordForm #caseName");
	var mrectype = $j("#newMedicalRecordForm #mrType");
    var mrecord = $j("#newMedicalRecordForm #medicalRecord");
	
   	var bValid=true,casenameValid=true,mrectypeValid=true,mrecordValid=true;
  	casenameValid = checkNull( casename,constants_casenameRequired);
	mrectypeValid= checkNull( mrectype,constants_mrtypeRequired);
	mrecordValid= checkNull( mrecord,constants_mrecordRequired);
	return bValid;
}	