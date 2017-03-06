function viewPatientList(patientListJson,tableObj) {
	var patientTable=setPatientTableStructure();
	$j('#tabs-1').html(patientTable.html());
	setCustomDataTable(tableObj);
    loadPatientPageToolBar();
	loadPatientFilterToolBar();
	pgPatientList=fillPatientTable(patientListJson,tableObj);
	return pgPatientList;

}

//create the table structure for patient Table
    function setPatientTableStructure() {
	var tblData = getRequestData('/netmd/json/list/patientTable.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
}

//Creating Page Tool Bar
     function loadPatientPageToolBar() {
	var ptbdata =getRequestData(constant_PatientToolBar);
	var ptbContainer = $j('<div id="patientPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function loadPatientFilterToolBar(){
	var ftbdata =getRequestData('/netmd/json/toolbars/patientFilterToolBar.json');
	var ftb = new FilterToolBar(ftbdata);
	var refFilterCont=$j('<div id="pat-filter-cont">');
	var refFilterTB = $j('<div id="patient-filter-toolbar" class="box-content"/>');
	refFilterTB.append(ftb.result); // Add the filter tool bar to Div
	refFilterCont.append(refFilterTB);
	var refFilterWB = $j('<div id="patient-filter-wb" style="display:none;padding:0 0 0 0px"/>');
	var refFilterWBInner = $j('<div id="patient-filter-wb-inner" style="float:left; width:40%"/>');
	refFilterWB.append(refFilterWBInner);
	var refFilterSubBtn = $j('<input type="button" value="Go", id="ref_btnfltrSubmit">');
	refFilterWB.append(refFilterSubBtn);
	refFilterCont.append(refFilterWB);
	$j('#filterToolBar-Container').html(refFilterCont);
	$j('#filter').show();
	$j('#filterWorkBench').hide();	
}

function setReportFilterValues(patientName) {
	if(patientName=='firstName'){
		autoCompleteArray=[];
		$j(patientResult.patient).each(function(index,patient){
			autoCompleteArray.push(''+patient.firstName+'');	
		});
		makeautoComplete("txt"+patientName,autoCompleteArray);
	}
}

function fillPatientTable (patientListJson, tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	patientResult= postdataToServer("/netmd/ws/ui/patient/list",patientListJson);
	//alert(JSON.stringify(patientResult));
		if(patientResult.patient.length>0) {	
			$j(patientResult.patient).each(function (patientIndex, patientObj) {
				var rowData=$j(tableObj).dataTable().fnAddData([patientObj.id,patientObj.firstName,patientObj.mobile,patientObj.email]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',patientObj.id);	
				$j(row).children("td:nth-child(1)").attr("class","patientIdCol Ustyle");
			});	
		//}		
  }
 return  patientResult;
 }

function getFilterlistUrl(filterExp,startindex,interval){
	var listJson='{"exp":[' + filterExp + '], "from":' + startindex + ',"count":'+interval + ',"asc":false}';
	return listJson;
}

function  getExpression(){
	var listJsonForDoctor='{"name":"status","value":"active","operator":"eq"}';
	return listJsonForDoctor;
}




