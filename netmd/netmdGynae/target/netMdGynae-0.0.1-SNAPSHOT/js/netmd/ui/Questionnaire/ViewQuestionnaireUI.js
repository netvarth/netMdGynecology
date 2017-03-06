function ViewQuestionnaireUI(questionnaireUIStartup) {
 	this.viewQuestionnairePage = "#viewQuestionnaire";
	this.errorDivHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivViewQuestionnaireData');
	this.viewQuestionnaireInnerDiv= "#viewQuestionnaire #viewQuestionnaireInnerDiv";
	this.viewQuestionnaireDiv = "#viewQuestionnaire #viewQuestionnaireDiv";
	this.updateButton = '#viewQuestionnaire #btnViewQuestionnaireSave';
	this.editButton = '#viewQuestionnaire #btnViewQuestionnaireEdit';
	this.cancelButton = '#viewQuestionnaire #btnViewQuestionnaireCancel';
	this.questionnaireId;	
	this.questnrId;
	this.ptbBack = "#questionnaireGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#questionnaireGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#questionnaireGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.answerList;
	this.questionnaireUIStartup=questionnaireUIStartup;
	this.viewQuestionnairePTB = new ViewQuestionnairePTB(this); 
}
 ViewQuestionnaireUI.prototype.getQuestionnaireUIStartup = function() {
	return this.questionnaireUIStartup;
}

ViewQuestionnaireUI.prototype.getViewQuestionnairePTB = function() {
	return this.viewQuestionnairePTB;
}

ViewQuestionnaireUI.prototype.getQuestionnaireTableNavigator = function() {
	var questionnaireUIStartup = this.getQuestionnaireUIStartup();
	return questionnaireUIStartup.getQuestionnaireTableNavigator();
}

ViewQuestionnaireUI.prototype.getQuestionnaireService = function() {
	var questionnaireUIStartup = this.getQuestionnaireUIStartup();
	return questionnaireUIStartup.getQuestionnaireService();
}
//Set the page title of the questionnaire ui page
ViewQuestionnaireUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewQuestionnaireUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(this.questionnaireName);
}


ViewQuestionnaireUI.prototype.init = function(questionnaireId) {
	var self = this;
	var viewQuestionnairePTB = self.getViewQuestionnairePTB();
	viewQuestionnairePTB.init(self);
	pageHandler.create(constants.VIEWQUESTIONNAIRE);
	self.bindEvents(questionnaireId);
	self.ViewQuestionnaire(questionnaireId);
}

 ViewQuestionnaireUI.prototype.ViewQuestionnaire = function(questionnaireId) {
	self=this;
	var questionnaire = constants.VIEWQUESTIONNAIRETITLE;
	var questionnaireService=self.getQuestionnaireService();
	var jsonResponse = questionnaireService.getViewQuestionnaire();	
	var newQuestionnaireProcess = new newQuestionnaireProcessor();
	var QnViewResponse = newQuestionnaireProcess.createQuestionnaire(jsonResponse);	
	$j(self.viewQuestionnaireInnerDiv).append(QnViewResponse);
	var questionnaireResponse = questionnaireService.viewQuestionnaire(questionnaireId);
	self.questnrId=questionnaireResponse.questionnaireId; 
	if(questionnaireResponse.success!=true) {
		var ntmdQstnAnswerDTO = new NetmdQuestionAnswerDTO();		
		self.setQuestionnaire(jsonResponse,questionnaireResponse);
	} else {
		commonMethodInvoker.createServerError(self.errorDivHeader,self.errorData, commonMethodInvoker.getErrorName(questionnaireResponse.error));
	}	 
	self.setPageTitle(questionnaire);	
}

ViewQuestionnaireUI.prototype.setQuestionnaire = function(jsonResponse,qstnObj) {
	var self=this;
	var deathcount=0;
	$j(self.viewQuestionnaireDiv).show();
	var questionnaireService=self.getQuestionnaireService();
	var newGyneQuestionaire = questionnaireService.getViewQuestionnaire();	
	var newGyneModal=new newQuestionnaireProcessor();
	var data=newGyneModal.createQuestionnaire(newGyneQuestionaire,"view",qstnObj);
	$j(self.viewQuestionnaireInnerDiv).html("");
	$j(self.viewQuestionnaireInnerDiv).append(data); 
	newGyneModal.bindEvents(); 
	self.answerList=qstnObj.answerDTO;
	  $j(qstnObj.answerDTO).each(function(index,answer){
	      if(answer.questionKey=="death"+deathcount+"name"){
		    deathcount++;
		    methodInvoker.fillvaluesToQuestionNaire('#death'+deathcount+'cause');
		  }
		  if(answer.answer=="Unknown")
			$j('#'+answer.questionKey).val("");
		else
			$j('#'+answer.questionKey).val(answer.answer);
		
		$j(self.viewQuestionnairePage + " input[type=text],textarea").attr('readonly',true);
		$j(self.viewQuestionnairePage + " input[type=text],textarea").addClass('newBox');
		$j(self.viewQuestionnairePage + " input[type=text],textarea").attr('disabled',true);
	  });
	 $j('.addDeath').hide();
	 $j('.deleteDeath').hide();
	 $j('.deathone').show();
}

ViewQuestionnaireUI.prototype.getQuestionnaire = function(questionnaireId) {
	var self=this;
	var array=[];
	var netmdQuestionAnswerDTO=new NetmdQuestionAnswerDTO();
	$j(self.viewQuestionnaireInnerDiv ).find("input,textarea,select").map(function(index, elm) {
	if ($j(elm).hasClass('ans')) 			  
			array.push( {questionKey: elm.id, answer: $j(elm).val()});
	});
	netmdQuestionAnswerDTO.setAnswerDTO(array);
	netmdQuestionAnswerDTO.setQuestionnaireId(questionnaireId);
	return netmdQuestionAnswerDTO;
}


ViewQuestionnaireUI.prototype.writable = function() {
	self=this;
	var editQuestionnaire = constants.EDITQUESTIONNAIRETITLE;
	self.setPageTitle(editQuestionnaire);
	$j(self.editButton).hide();
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	 $j('.addDeath').show();
	 $j('.deleteDeath').show();
	/* var newGyneQuestionnaire=self.newQuestionnaireProcessor();
	newGyneQuestionnaire.bindEvents(); */
	$j(self.viewQuestionnaireInnerDiv).find("input[type=text],textarea,select").map(function(index,elm) {
	     if ( typeof $j(elm).attr('disabled')  ) {
			   $j(elm).removeAttr('disabled');
			}	
		if( $j(elm).hasClass('view')) 
			$j(elm).attr("style","display:none");
		else if( $j(elm).hasClass('edit'))				
			$j(elm).attr("style","display:block");
		});	
		
		$j(self.viewQuestionnaireInnerDiv).find("select").map(function(index,elm) {
			$j(self.answerList).each(function(index,answer){
				if(elm.id==answer.questionKey)
					$j('#'+answer.questionKey+" option[value='"+answer.answer+"']").attr("selected","selected");
			}); 
		});
			
		$j(self.viewQuestionnairePage + " input[type=text],textarea").removeAttr('readonly');
		$j(self.viewQuestionnairePage + " input[type=text],textarea").removeClass('newBox');
		$j(self.viewQuestionnairePage +"#viewQuestionnaire #deathDetail").removeClass('one');
		$j(self.viewQuestionnairePage +"#viewQuestionnaire #deathDetail").addClass('viewOne');
		$j(self.viewQuestionnairePage + " input[type=text],textarea").removeAttr('disabled');
}

ViewQuestionnaireUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j('.addDeath').hide();
	$j('.deleteDeath').hide();
		$j(self.viewQuestionnaireInnerDiv ).find("input,textarea,select").map(function(index, elm) {
		if( $j(elm).hasClass('edit')) 
			$j(this).attr("style","display:none");
		else if( $j(elm).hasClass('view'))				
			$j(elm).attr("style","display:block");
		});			
		$j(self.viewQuestionnaireInnerDiv ).find("input,textarea,").map(function(index,elm) {
			if( $j(elm).hasClass('common')) 
				$j(this).addClass("newBox")	
		});
	$j(self.viewQuestionnairePage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewQuestionnairePage + " input[type=text],textarea").addClass('newBox');
	$j(self.viewQuestionnairePage + " input[type=text],textarea").attr('disabled',true);
} 

 ViewQuestionnaireUI.prototype.getPrevId = function(curId,questionnaireResult) {
	var prevId;
	$j(questionnaireResult.questionnaireList).each(function (index, rowQuestionnaire) {
		if(curId==rowQuestionnaire.id)	{
			var arrayLength=(questionnaireResult.questionnaireList).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=questionnaireResult.questionnaireList[index-1].id;
		}
	});
	return prevId;	
}
	
ViewQuestionnaireUI.prototype.getNextId = function(curId,questionnaireResult) {
	var nextId;
	$j(questionnaireResult.questionnaireList).each(function (index, rowQuestionnaire) {
		if(curId==rowQuestionnaire.id)	{
			var arrayLength=(questionnaireResult.questionnaireList).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=questionnaireResult.questionnaireList[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewQuestionnaireUI.prototype.bindEvents = function(questionnaireId) {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorDivHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self.errorDivHeader.hide();
		commonMethodInvoker.removeErrors();
		self.ViewQuestionnaire(questionnaireId);
		self.readable();
	});
	
	 $j(self.updateButton).die('click').live('click',function(){
		self.errorDivHeader.hide();
		commonMethodInvoker.removeErrors();
		var qstnObj = self.getQuestionnaire(questionnaireId);
		var questionnaireValidator = new QuestionnaireValidator();
		var error  = questionnaireValidator.validate(qstnObj); 
		if(error.errorStatus==false) {
			var questionnaireService=self.getQuestionnaireService();
			var questnrResponse = questionnaireService.updateQuestionnaire(qstnObj);	
			if(questnrResponse.success==true) {
				showTip(constants.QUESTIONNAIREUPDATESUCCESS);//For showing the global Tip
				self.ViewQuestionnaire(questnrResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(questnrResponse.error));
			}
		 } else {
			self.createError(error); 
		}		
	}); 
	
	}
  

	
