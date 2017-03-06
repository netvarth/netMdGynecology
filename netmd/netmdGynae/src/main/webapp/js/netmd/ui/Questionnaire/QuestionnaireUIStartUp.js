
function QuestionnaireStartUp() {
	this.pgTableName = "#questionnaire";
	this.pgTableContainer="#questionnaireListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#questionnairePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#questionnairePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#questionnairePTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#questionnairePTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.questionnaireIdCol';
	this.exp = new ExpressionListDTO();
	this.questionnaireService = new QuestionnaireServiceImpl();
	this.listUrl = constants.QUESTIONNAIRELISTURL;
	this.questionnaireTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.questionnaireService,this.exp);
	this.ftbToolBar = '#questionnaire-filter-toolbar';
	this.filter = '#filter';
	this.patientName;
	this.viewQuestionnaireUI = new ViewQuestionnaireUI(this);
}

 QuestionnaireStartUp.prototype.setQuestionnaireTableNavigator = function(questionnaireTableNavigator) {
	this.questionnaireTableNavigator = questionnaireTableNavigator;
}
QuestionnaireStartUp.prototype.getQuestionnaireService = function() {
	return this.questionnaireService;
}
QuestionnaireStartUp.prototype.getQuestionnaireTableNavigator = function() {
	return this.questionnaireTableNavigator;
}
QuestionnaireStartUp.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
QuestionnaireStartUp.prototype.getViewQuestionnaireUI = function() {
	return this.viewQuestionnaireUI;
} 

QuestionnaireStartUp.prototype.init = function() {
	var self = this;
	var questionnaireTableNavigator = self.getQuestionnaireTableNavigator();
	self.setPageTitle(constants.QUESTIONNAIRETITLE);
	var expList = new ExpressionListDTO();
	var exp= new ExpressionDTO("status","active","eq");
	var exp1= new ExpressionDTO("deptQuestId",2,"eq");
	expList.add(exp);
	expList.add(exp1);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.QUESTIONNAIRE,constants.QUESTIONNAIREPAGETOOLBAR); //Create the Page tool Bar for Area/* 
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.QUESTIONNAIRETABLELIST);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	questionnaireTableNavigator.setExp(expList);
	questionnaireTableNavigator.list();
	self.bindEvents();
	
}

 QuestionnaireStartUp.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEQUESTIONNAIREUI,constants.QUESTIONNAIREMODEL);		
		openModalBox(obj,constants.QUESTIONNAIREMODEL); 
		var newQuestionnaireUI = new NewQuestionnaireUI(self);
		newQuestionnaireUI.init();
		newQuestionnaireUI.bindEvents();
	});
	
	 self.ptbView.die('click').live('click',function() {
		removeErrors();
		var questionnaireId=self.getSelectedQuestionnaireId(self.pgTableName);
		$j('#' + constants.QUESTIONNAIRE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(questionnaireId!="") {
			var viewQuestionnaireUI = self.getViewQuestionnaireUI();
			viewQuestionnaireUI.init(questionnaireId);
		}	
	});
	
	 self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var questionnaireId=self.getSelectedQuestionnaireId(self.pgTableName);
		if(questionnaireId!="") {
			var questionnaireService = self.getQuestionnaireService();
			var questionnaireResponse = questionnaireService.viewQuestionnaire(questionnaireId);
			var confirmStatus = confirm(constants.QUESTIONNAIREDELETECONFIRM + questionnaireResponse.questionnaireId);
			if(confirmStatus==true) {				
				var response = questionnaireService.deleteQuestionnaire(questionnaireId);
				if(response.success==true) {
					showTip(constants.QUESTIONNAIREDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var questionnaireTableNavigator = self.getQuestionnaireTableNavigator();
				questionnaireTableNavigator.list();
				
			}
		}	
	});  
	
	 self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
}
QuestionnaireStartUp.prototype.getSelectedQuestionnaireId = function () {
	var questionnaireId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selQuestionnaire = $j(this.pgTableName + ' tbody tr[selected]');
		if(selQuestionnaire.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEQUESTIONNAIRE);
		} else if(selQuestionnaire.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEQUESTIONNAIREONLY);
		else
			questionnaireId=selQuestionnaire.attr('id');
	}
	return questionnaireId;
}
QuestionnaireStartUp.prototype.bindEvents = function() {
	self = this;
	$j(self.pgTableName + ' tbody tr').die('click').live('click',function(){		
		if($j(this).attr('selected')) {
			$j(this).removeAttr('selected');
			$j(this).removeAttr('style');
		} else {	
			$j(this).attr('selected','selected');
			$j(this).attr('style',constants.SELECTEDROWCOLOR);
		}	
		removeErrors();
	});	
	
	$j(self.pgTableRowClass).die('click').live('click',function(){
	   var questionnaireId= $j(this).parent().attr('id');
	   $j('#' + constants.QUESTIONNAIRE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(questionnaireId!="") {
			$j('#questionnaire-filter-wb').hide();
			var viewQuestionnaireUI = self.getViewQuestionnaireUI();
			viewQuestionnaireUI.init(questionnaireId);
		}	
	});
}  