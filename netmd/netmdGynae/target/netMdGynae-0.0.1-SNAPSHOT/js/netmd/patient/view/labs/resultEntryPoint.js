
$j.cachedScript(constants_viewTestNames).done(function(script, textStatus) {
})
$j(document).ready(function(){
	var resultTable=setResultTableStructure();
	$j('#labsInfoTab').html(resultTable.html());
	//loadResultPageToolBar();
	setCustomDataTable('#labTable');
	
	var exp=getExpression(); 
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var resultListJson = getFilterlistUrl(exp,(curPage-1),interval);
	var pgTableName = "#labTable"; // Table showing referral list
	pgTableContainer = "#labResultTableCont"; // Parent container of the referral list table
	
	pgResultList=fillResultTable(resultListJson,pgTableName);
	
	if(pgResultList.count)
		maxRecords = pgResultList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	
	
$j('.resultIdCol').die('click').live('click',function(){
		    var resId= $j(this).parent().attr('id');
		   if(resId!="") {
				var obj=$j(this);
				removeErrors();
				createModal('/netmd/json/report/testNames.json','testNamesModal',resId);		
				openModalBox(obj,'testNamesModal')			
			}	 
		
		}); 

 $j('#resultGeneralPTBContainer #btn_view_ptb_id').die('click').live("click",function() {	
		   var resId=getSelectedResultId('#labTable');
		   if(resId!="") {
				var obj=$j(this);
				removeErrors();
				createModal('/netmd/json/report/testNames.json','testNamesModal',resId);		
				openModalBox(obj,'testNamesModal')	
			}		
		}); 	
		
		
	$j('#resultGeneralPTBContainer #btn_back_ptb_id').die('click').live("click",function() {
		 $j.cachedScript(constants_viewResultEntryPoint).done(function(script, textStatus) { 
		})
		var patientId=getPatientId();
		var patientInfo = getPatientData(patientId);
		$j('#pageTitle').html(constant_PatientView_Msg+'  '+'( '+patientInfo.firstName.toUpperCase()+' )');
});   		
	
$j(pgTableContainer +' #next').die('click').click(function() {	
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			resultListJson=getFilterlistUrl(exp,startValue,interval);
			pgResultList=fillResultTable(resultListJson,pgTableName);			
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			resultListJson=getFilterlistUrl(exp,startValue,interval);
			pgResultList=fillResultTable(resultListJson,pgTableName);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			resultListJson= getFilterlistUrl(exp,startValue,interval);
			pgResultList=fillResultTable(resultListJson,pgTableName);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			resultListJson=getFilterlistUrl(exp,startValue,interval);
			pgResultList=fillResultTable(resultListJson,pgTableName);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});
	
	 
	/* 	//To select row from the table
		$j("#labTable" + ' tbody tr').die('click').live('click',function(){		
			if($j(this).attr('selected')) {
				$j(this).removeAttr('selected');
				$j(this).removeAttr('style');
			} else {	
				$j(this).attr('selected','selected');
				$j(this).attr('style','background:#DCDCDC;');
			}			
		});	 */
	
})

function getSelectedResultId(pgTableName) {
		var resId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selRes = $j(pgTableName + ' tbody tr[selected]');
			if(selRes.length==0){		
				updateTips("Select atleast one result",$j('#errorDivData'),$j('#errorDivHeader'));
			} else if(selRes.length>1) 
				updateTips("Select only one result",$j('#errorDivData'),$j('#errorDivHeader'));
			else
				resId=selRes.attr('id');
		}	
		return resId;
	}

function setResultTableStructure() {
     //alert(" set refferal tbl");
	var tblData = getRequestData('/netmd/json/list/labResultTable.json');
	var boxDiv = $j('<div/>');
		$j(tblData.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);
		});
	return 	boxDiv;
}

function loadResultPageToolBar() {
var ptbContainer = $j('<div id="resultGeneralPTBContainer" />');
	var ptbdata =getRequestData('/netmd/json/toolbars/patientResultToolBar.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#patientResultToolbarDiv').html(ptbContainer);
}

	
function fillResultTable(resultListJson,tableObj){
//alert(resultListJson);
	$j(tableObj).dataTable().fnClearTable();
	reportResult = postdataToServer("/netmd/ws/ui/result/list",resultListJson);
	//alert(JSON.stringify(reportResult));
		if(reportResult.resultList.length>0) {			
			$j(reportResult.resultList).each(function (refIndex,referralObj) {
				var rowData=$j(tableObj).dataTable().fnAddData([referralObj.orderUid,referralObj.orderDate,referralObj.labName,referralObj.labBranchName]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',referralObj.id);	
				$j(row).children("td:nth-child(1)").attr("class","resultIdCol Ustyle");
			});	
		}		
	
	
return reportResult;
}

function getFilterlistUrl(filterExp,startindex,interval){
	var listJson='{"exp":[' + filterExp + '], "from":' + startindex + ',"count":'+interval + ',"asc":false}';
	return listJson;
}

function getExpression(){
	var patientId=getPatientId();
	//alert(patientId);
	var listJsonForResult='{"name":"doctorId","value":"'+GB_DOCID+'","operator":"eq"}'+','+
	'{"name":"patientId","value":"'+patientId+'","operator":"eq"}';
	return listJsonForResult;
}
