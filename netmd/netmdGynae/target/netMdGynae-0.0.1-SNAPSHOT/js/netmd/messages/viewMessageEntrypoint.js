
function viewMessageInfo(messageId){
	var messageInfo =getMessageData(messageId);
	//alert(JSON.stringify(messageInfo));
	if(messageInfo.id)
		$j("#messageViewForm #messageID label").text(messageInfo.id);
	else
		$j("#messageViewForm #messageID label").text("Nil");
	if(messageInfo.doctorName)
		$j("#messageViewForm #doctorname").val(messageInfo.doctorName);
	else
		$j("#messageViewForm #doctorname").val("Nil");
	if(messageInfo.date)
		$j("#messageViewForm #dateandtime").val(messageInfo.date);
	else
		$j("#messageViewForm #dateandtime").val("Nil");
	if(messageInfo.message)
		$j("#messageViewForm #viewMessage").val(messageInfo.message);
	else
		$j("#messageViewForm #viewMessage").val("Nil");		
}

function getMessageData(messageId){
var response= getRequestData('/netmd/ws/ui/message/viewMessage/'+ messageId);
return response;
}


