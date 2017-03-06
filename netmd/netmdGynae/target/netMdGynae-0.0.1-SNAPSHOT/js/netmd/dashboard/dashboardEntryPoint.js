$j('#pageTitle').html("Dashboard");

$j(document).ready(function() {
	var tableObj = '#dashboard';
	showDashboard(tableObj);	
});
function showDashboard(tableObj) {
	var dashboardTable=setDashBoardTableStructure();
	$j('#tabs-1').html(dashboardTable.html());
	setCustomDataTableNPagin(tableObj);
	//setCustomDataTable(tableObj);
	//filldashboardTable (tableObj,dashboardResult);
}
function setDashBoardTableStructure() {
	//create the table structure for dashboard Table
	var tblData = getRequestData('/netmd/json/list/dashboardTable.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
}

/* function filldashboardTable(tableObj,dashboardResult) {
	alert(tableObj);
	alert(JSON.stringify(dashboardResult));
	this.setTableValues = function(tableObj, dashboardResult) {
		alert("in");
		$j(tableObj).dataTable().fnClearTable();
		 if(dashboardResult.dashboard) {
			if(dashboardResult.dashboard.length>0) {			
				$j(dashboardResult.dashboard).each(function(index, dashboard) {
				var id=dashboard.id;
				var category=dashboard.category;
				var count=dashboard.count;
					var rowData=$j(tableObj).dataTable().fnAddData([id,category,count]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","dashboardIdCol Ustyle");
				});
			}		
		} 
	}
}
 */