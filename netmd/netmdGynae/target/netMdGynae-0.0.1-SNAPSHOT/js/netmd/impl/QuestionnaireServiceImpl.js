function QuestionnaireServiceImpl () {
	this.setTableValues = function(tableObj, questionnaireResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(questionnaireResult) {			
				$j(questionnaireResult.questionnaireList).each(function(index, qustnObj) {
					var id=qustnObj.answerSetId;
					var createdTime=qustnObj.createdTime;
					var updatedTime=qustnObj.updatedTime;
					var rowData=$j(tableObj).dataTable().fnAddData([id,createdTime,updatedTime]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","questionnaireIdCol Ustyle");
				});
			}		
		}  
	}
	
QuestionnaireServiceImpl.prototype.getNewQuestionnaire=function() {
	ajaxProcessor.setUrl(constants.NEWGYNAQUESTIONNAIRE);
	return ajaxProcessor.get();
}
QuestionnaireServiceImpl.prototype.getViewQuestionnaire=function() {
	ajaxProcessor.setUrl(constants.VIEWGYNAQUESTIONNAIRE);
	return ajaxProcessor.get();
}

 QuestionnaireServiceImpl.prototype.createNewQuestionnaire=function (qustnObj) {
	ajaxProcessor.setUrl(constants.QUESTIONNAIRECREATEURL);
	return ajaxProcessor.post(qustnObj);
}
QuestionnaireServiceImpl.prototype.viewQuestionnaire=function(questionnaireId) {
	ajaxProcessor.setUrl(constants.QUESTIONNAIREVIEWURL + questionnaireId);
	return ajaxProcessor.get();
}

QuestionnaireServiceImpl.prototype.updateQuestionnaire=function(qustnObj) {
	ajaxProcessor.setUrl(constants.QUESTIONNAIREUPDATEURL);
	return ajaxProcessor.post(qustnObj);
}

QuestionnaireServiceImpl.prototype.deleteQuestionnaire=function(questionnaireId) {
	ajaxProcessor.setUrl(constants.QUESTIONNAIREDELETEURL + questionnaireId);
	return ajaxProcessor.get();
}
 
