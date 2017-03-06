function NewQuestionnaireUI(QuestonnaireStartUp) {
	this.createButton = $j("#newQuestionnaire #btnQuestionnaireSave");
	this.cancelButton = $j('#newQuestionnaire #btnQuestionnaireCancel');
	this.newQuestionnairePage = "#newQuestionnaire";
	this.questionnaireModal = '#questionnaireModal';
	this.errorHeader = $j('#questionnaireModal #errorDivHeader');
	this.errorData = $j('#questionnaireModal #errorDivnewQuestionnaireData');
	this.newQuestionnaireInnerDiv = $j('#questionnaireModal #newQuestionnaireInnerDiv');
	this.anteRegisered=$j('#questionnaireModal #anteRegisered');
	this.maternalDeath= $j('#questionnaireModal #maternalDeath');
	this.inputFields = ":input";
	this.response;
	this.newQuestionnaire="#newQuestionnaire #newQuestionnaireInnerDiv";
	this.QuestonnaireStartUp = QuestonnaireStartUp;

}
NewQuestionnaireUI.prototype.getQuestonnaireStartUp = function() {
	return this.QuestonnaireStartUp;
}
NewQuestionnaireUI.prototype.getQuestionnaireService = function() {
	var QuestonnaireStartUp = this.getQuestonnaireStartUp();
	return QuestonnaireStartUp.getQuestionnaireService();
}
 NewQuestionnaireUI.prototype.getQuestionnaireTableNavigator = function() {
	var QuestonnaireStartUp = this.getQuestonnaireStartUp();
	return QuestonnaireStartUp.getQuestionnaireTableNavigator();
} 
 NewQuestionnaireUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newQuestionnairePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
} 
 

NewQuestionnaireUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.headName);
}
NewQuestionnaireUI.prototype.getQuestionnaire = function() {
	var self=this;
	var array=[];
	var netmdQuestionAnswerDTO=new NetmdQuestionAnswerDTO();
	$j(self.newQuestionnaire ).find("input[type=text],textarea,select").map(function(index, elm) {
				array.push( {questionKey: elm.id, answer: $j(elm).val()});
			});
	netmdQuestionAnswerDTO.setAnswerDTO(array);
	return netmdQuestionAnswerDTO;
}

NewQuestionnaireUI.prototype.clearFields = function() {
	self = this;
	$j(self.newQuestionnairePage + " input[type=text],textarea").val("");
	$j(self.maternalDeath+" option[value='0']").attr('selected','selected');
	$j(self.anteRegisered ).focus();
}
NewQuestionnaireUI.prototype.init = function() {
    var self=this;
	var questionnaireService=self.getQuestionnaireService();
	var jsonResponse= questionnaireService.getNewQuestionnaire();	
	var newQuestionnaireProcess = new newQuestionnaireProcessor();
	var QnNewResponse=newQuestionnaireProcess.createQuestionnaire(jsonResponse);	
	$j(self.newQuestionnaireInnerDiv).append(QnNewResponse);
	newQuestionnaireProcess.bindEvents();
}

 NewQuestionnaireUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newQuestionnairePage + " " + self.inputFields);
	
	$j(self.newQuestionnairePage+ " input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});
	 $j(self.questionnaireModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.questionnaireModal + self.newQuestionnairePage + " input[type=text]").val("");	
		$j(self.questionnaireModal).trigger('reveal:close');
		$j(self.questionnaireModal).remove();
		self=self.getQuestonnaireStartUp();
	});

    self.createButton.die('click').live('click',function(){
		 self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var Qstnnaire = self.getQuestionnaire();
		var questionnaireValidator = new QuestionnaireValidator();
		var error  = questionnaireValidator.validate(Qstnnaire);
		if(error.errorStatus==false) {
			var questionnaireService=self.getQuestionnaireService();
			var QuestionnaireResponse = questionnaireService.createNewQuestionnaire(Qstnnaire);
			if(QuestionnaireResponse.success==true) {
				showTip(constants.QUESTIONNAIRECREATESUCCESS);//For showing the global Tip
				self.clearFields();
				$j(self.questionnaireModal).trigger('reveal:close');
				$j(self.questionnaireModal).remove();
				var questionnaireTableNavigator = self.getQuestionnaireTableNavigator();
				questionnaireTableNavigator.list();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(QuestionnaireResponse.error));
			}
		} else {
			self.createError(error);
		}	 
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.questionnaireModal + self.newQuestionnairePage + " input[type=text]").val("");	
		$j(self.questionnaireModal).trigger('reveal:close');
		$j(self.questionnaireModal).remove();
		self=self.getQuestonnaireStartUp();
	});	
	
} 