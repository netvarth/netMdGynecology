
function viewMessageList(messageListJson,pgTableName){
	var messageTable=setMessageTableStructure();
	$j('#tabs-1').html(messageTable.html());
	setCustomDataTable(pgTableName);
    loadMessagesPageToolBar();
	pgMessageList=fillMessageTable(messageListJson,pgTableName);
	return pgMessageList;
}

function setMessageTableStructure() {
	var tblData = getRequestData('/netmd/json/list/messagesTable.json');
	var boxDiv = $j('<div/>');
	$j(tblData.data).each(function(index,subgroup) {
		var cont = new Container(subgroup);
		boxDiv.append(cont.result);
	});
	return 	boxDiv;
}

//Creating Page Tool Bar
     function loadMessagesPageToolBar() {
	var ptbdata =getRequestData(constant_MessagesToolBar);
	var ptbContainer = $j('<div id="messagePTBContainer"/>');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).append(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);
}

function fillMessageTable(messageListJson,tableObj) {
	var pgTableName="#messages";
	$j(tableObj).dataTable().fnClearTable();
	messageResult= postdataToServer("/netmd/ws/ui/message/getMessage",messageListJson);
	if(messageResult.messageDTO.length>0) {	
				$j(messageResult.messageDTO).each(function (messageIndex, messageObj) {
				var rowData=$j(tableObj).dataTable().fnAddData([messageObj.id,messageObj.date,messageObj.message.slice(0,60)]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				if(messageObj.messageViewed==false)
					$j(row).attr('style','background:#A5F9EC;');	
				else
					$j(row).attr('style','background:#FFFFFF;');
					$j(row).attr('id',messageObj.id);	
					$j(row).children("td:nth-child(1)").attr("class","messageIdCol Ustyle");
				
				});		
  }
 return  messageResult;
 }
 
 
 