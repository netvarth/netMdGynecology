$j('#pageTitle').html("Messages");
$j.cachedScript(constant_messageFunctions).done(function(script, textStatus) {
})
$j(document).ready(function(){
	var exp=[]; 
	var maxRecords=0; // Total number of records
	var maxPages = 0; // Total number of pages
	var interval = 10;// Interval between pages
	var curPage = 1;//Current selected page
	var expr=getExpression();	
	var messageListJson = getFilterlistUrl(expr,(curPage-1),interval);
	var pgTableName = "#messages"; // Table showing patient list
	pgTableContainer = "#messageTableCont"; // Parent container of the patient list table
	pgMessageList=viewMessageList(messageListJson,pgTableName);
	
	if(pgMessageList.count)
		maxRecords = pgMessageList.count;	
	if(maxRecords%interval!=0)
		maxPages = parseInt(maxRecords/interval) + 1;
	else
		maxPages = parseInt(maxRecords/interval);	
	setPaginationFields(curPage, maxPages, pgTableContainer);
	
	// Datatable operations next,previous,first,last
	$j(pgTableContainer +' #next').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage+=1;
			var startValue = interval*(curPage-1);
			messageListJson=getFilterlistUrl(exp,startValue,interval);
			pgMsgList=fillMessageTable(messageListJson,pgTableName);			
			setPaginationFields(curPage, maxPages,pgTableContainer);
		}		
	});
	$j(pgTableContainer +' #previous').die('click').click(function() {
		if(curPage!=1) {
			curPage-=1;
			var startValue = interval*(curPage-1);
			messageListJson=getFilterlistUrl(exp,startValue,interval);
			pgMsgList=fillMessageTable(messageListJson,pgTableName);				
			setPaginationFields(curPage, maxPages,pgTableContainer);				
		}
	});
	$j(pgTableContainer +' #first').die('click').click(function() {
		if(curPage!=1) {
			curPage=1;
			startValue = 0;
			messageListJson= getFilterlistUrl(exp,startValue,interval);
			pgMsgList=fillMessageTable(messageListJson,pgTableName);	
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}	
	});
	$j(pgTableContainer +' #last').die('click').click(function() {
		if(curPage!=maxPages && curPage<maxPages) {
			curPage =maxPages;
			startValue = (curPage-1)*interval;
			messageListJson=getFilterlistUrl(exp,startValue,interval);
			pgMsgList=fillMessageTable(messageListJson,pgTableName);			
			setPaginationFields(curPage, maxPages, pgTableContainer);
		}
	});	
	
	// view message by clicking ID
	$j('.messageIdCol').die('click').live('click',function(){
	    var messageId= $j(this).parent().attr('id');
		var obj=$j(this);		
		if(messageId!="") {
		    createModal(constants_viewMessageJson,'messageViewModal');		
			openModalBox(obj,'messageViewModal')
			$j.cachedScript(constants_ViewMessageEntryPoint).done(function(script, textStatus) {
				viewMessageInfo(messageId);
			})
		}	
	});
	
// view message by toolbar	
$j('#messagePTBContainer #btn_view_ptb_id').die('click').live("click",function() {
	        var obj=$j(this);
            var messageId = getSelectedMessageId(pgTableName);	
		if(messageId!="") {			
			createModal(constants_viewMessageJson,'messageViewModal');		
			openModalBox(obj,'messageViewModal')
			$j.cachedScript(constants_ViewMessageEntryPoint).done(function(script, textStatus) {
			viewMessageInfo(messageId);
			})
	}
	});	
	
	
	
	// Selected row gets different color
	$j(pgTableName + ' tbody tr').die('click').live('click',function(){	
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style','background:#DCDCDC;');
		}			
	});	
	
	
	//function for selecting a particular row ID
	function getSelectedMessageId(pgTableName) {
		var messageId="";
		if($j(pgTableName).dataTable().fnGetData().length>0) {
			var selMessage = $j(pgTableName + ' tbody tr[selected]');
			if(selMessage.length==0){
				updateTips("Select atleast one message",$j('#errorDivHeader'));
			} else if(selMessage.length>1) 
				updateTips("Select only one message",$j('#errorDivHeader'));
			else
				messageId=selMessage.attr('id');
		}	
		return messageId;
	}
	
	
	function getFilterlistUrl(expression,startindex,interval){
	var listJson='{"exp":[' + expression + '], "from":' + startindex + ',"count":'+interval + ',"asc":false}';
	return listJson;
}
function getExpression(){
	//alert(GB_DOCID);
	var listJsonForDoctor='{"name":"'+"doctorId"+'","value":"'+GB_DOCID+'","operator":"'+"eq"+'"}';
	return listJsonForDoctor;
}

	
});


