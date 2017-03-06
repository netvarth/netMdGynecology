//alert("inside fnctns");
function viewReferralList(referralListJson,tableObj) {
	var referalTable=setReferralTableStructure();
	$j('#tabs-1').html(referalTable.html());
	setCustomDataTable(tableObj);
	loadReferralPageToolBar();
	loadReferralFilterToolBar();
	pgDoctorList=fillDoctorTable(referralListJson,tableObj);
	//alert(JSON.stringify(pgDoctorList));
	return pgDoctorList;
}


function loadReferralPageToolBar() {
	//Creating Page Tool Bar
	var ptbdata =getRequestData('/netmd/json/toolbars/doctorPageToolBar.json');
	var ptbContainer = $j('<div id="referralPTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	//alert(JSON.stringify(ptb));
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function setReferralTableStructure() {
	//create the table structure for doctor Table
	var tblData = getRequestData('/netmd/json/list/referralTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);
		});
	return 	boxDiv;
}

function fillDoctorTable(referralListJson, tableObj) {
	$j(tableObj).dataTable().fnClearTable();
	referralResult = postdataToServer("/netmd/ws/ui/doctor/list",referralListJson);
	//alert(JSON.stringify(referralResult));
		if(referralResult.referral.length>0) {			
			$j(referralResult.referral).each(function (refIndex,referralObj) {
				 var fullName= referralObj.firstName+" "+referralObj.lastName
				var rowData=$j(tableObj).dataTable().fnAddData([referralObj.id,fullName,referralObj.specialization,referralObj.mobile,referralObj.email]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',referralObj.id);	
				$j(row).children("td:nth-child(1)").attr("class","referralIdCol Ustyle");
			});	
		}		
	
	
return referralResult;
}

 // Filter functions

function loadReferralFilterToolBar(){
	var ftbdata =getRequestData('/netmd/json/toolbars/doctorFilterToolBar.json');
	var ftb = new FilterToolBar(ftbdata);
	var refFilterCont=$j('<div id="ref-filter-cont">');
	var refFilterTB = $j('<div id="referral-filter-toolbar" class="box-content"/>');
	refFilterTB.append(ftb.result); // Add the filter tool bar to Div
	refFilterCont.append(refFilterTB);
	var refFilterWB = $j('<div id="referral-filter-wb" style="display:none;padding:0 0 0 0px"/>');
	var refFilterWBInner = $j('<div id="referral-filter-wb-inner" style="float:left; width:40%"/>');
	refFilterWB.append(refFilterWBInner);
	var refFilterSubBtn = $j('<input type="button" value="Go", id="ref_btnfltrSubmit">');
	refFilterWB.append(refFilterSubBtn);
	refFilterCont.append(refFilterWB);
	$j('#filterToolBar-Container').html(refFilterCont);
	$j('#filter').show();
	$j('#filterWorkBench').hide();	
} 

 
function setReportFilterValues(referalName) {
	//alert(JSON.stringify(referralResult));
	if(referalName=='firstName'){
		autoCompleteArray=[];
		$j(referralResult.referral).each(function(index,doctor){
			autoCompleteArray.push(''+doctor.firstName+'');	
		});
		makeautoComplete("txt"+referalName,autoCompleteArray);
	}
	if(referalName=='email'){
		autoCompleteArray=[];
		$j(referralResult.referral).each(function(index,doctor){
			autoCompleteArray.push(''+doctor.email+'');	
		});
		makeautoComplete("txt"+referalName,autoCompleteArray);
	}
	if(referalName=='mobile'){
		autoCompleteArray=[];
		$j(referralResult.referral).each(function(index,doctor){
			autoCompleteArray.push(''+doctor.mobile+'');	
		});
		makeautoComplete("txt"+referalName,autoCompleteArray);
	}
	if(referalName=='status'){
		autoCompleteArray=[];
		autoCompleteArray.push("Active");
		autoCompleteArray.push("Inactive");
		makeautoComplete("txt"+referalName,autoCompleteArray);
	}	
	if(referalName=='specialization'){
		autoCompleteArray=[];
		$j(referralResult.referral).each(function(index,doctor){
			autoCompleteArray.push(''+doctor.specialization+'');	
		});
		makeautoComplete("txt"+referalName,autoCompleteArray);
	}	
	if(referalName=='address'){
		autoCompleteArray=[];
		$j(referralResult.referral).each(function(index,doctor){
			autoCompleteArray.push(''+doctor.address+'');	
		});
		makeautoComplete("txt"+referalName,autoCompleteArray);
	}	
}

function getFilterlistUrl(filterExp,startindex,interval){
	var listJson='{"exp":[' + filterExp + '], "from":' + startindex + ',"count":'+interval + ',"asc":false}';
	return listJson;
} 

function  getExpression(){
	var listJsonForDoctor='{"name":"status","value":"active","operator":"eq"}';
	return listJsonForDoctor;
}