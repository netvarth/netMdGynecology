function ViewQuestionnairePTB(viewQuestionnaireUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewQuestionnaireUI = viewQuestionnaireUI;
	
	this.getViewQuestionnaireUI = function() {
		return this.viewQuestionnaireUI;
	}
		
	this.getQuestionnaireUIStartup = function() {
		var viewQuestionnaireUI = this.getViewQuestionnaireUI();
		return viewQuestionnaireUI.getQuestionnaireUIStartup();
	}
	
	this.getQuestionnaireTableNavigator = function() {
		var questionnaireUIStartup = this.getQuestionnaireUIStartup();
		return questionnaireUIStartup.getQuestionnaireTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewQuestionnaireUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var questionnaireUIStartup = self.getQuestionnaireUIStartup();
			questionnaireUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var questionnaireTableNavigator = self.getQuestionnaireTableNavigator();
			var questionnaire = source.getQuestionnaire();
			var prevId = self.getPrevId(questionnaire.getId(),questionnaireTableNavigator.getPgDataList());
			viewUI.ViewQuestionnaire(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var questionnaireTableNavigator = self.getQuestionnaireTableNavigator();
			var questionnaire = source.getQuestionnaire();
			var prevId = self.getNextId(questionnaire.getId(),questionnaireTableNavigator.getPgDataList());
			viewUI.ViewQuestionnaire(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.QUESTIONNAIREGENERAL, constants.QUESTIONNAIREGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}