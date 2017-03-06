function ViewCaseUI(caseUIStartup) {
	this.viewCasePage = "#viewCase";
	this.errorHeader = $j('#errorDivHeader');
	this.errorData = $j('#errorDivData');
	this.patientName='#viewCase #patientName label';
	this.patientId;
	this.caseName="#viewCase #case";
	this.patientType="#viewCase #patientType";
	this.admittedDate='#viewCase #date';
	this.date='#viewCase #date';
	this.height="#viewCase #height";
	this.weight='#viewCase #weight';
	this.bmi='#viewCase #bmi label';
	this.hb='#viewCase #hb';
	this.question="#viewCase #question";
	this.questionnaire="#viewCase #questionnaire";
	this.generalDetails="#viewCase #generalDetails";
	this.caseId;
	this.answerSetId;
	this.departmentlbl='#viewCase #department label';
	this.department="#viewCase #department";
	this.departmentId;
	this.viewDepartment="#viewCase #viewDepartment";
	this.editDepartment="#viewCase #editDepartment";
	this.description="#viewCase #description";
	this.patientTypelbl="#viewCase #patientTypelbl label";
	this.updateButton = '#viewCase #btnCaseSave';
	this.editButton = '#viewCase #btnCaseEdit';
	this.cancelButton = '#viewCase #btnCaseCancel'; 
	this.ptbBack = "#caseGeneralPTBContainer #btn_back_ptb_id";
	this.ptbUp = "#caseGeneralPTBContainer #btn_up_ptb_id";
	this.ptbDown = "#caseGeneralPTBContainer #btn_down_ptb_id";
	this.pageTitle = $j('#pageTitle');
	this.inputFields = ":input";
	this.caseUIStartup=caseUIStartup;
	this.departmentName;
	this.answerList;
	this.viewCasePTB = new ViewCasePTB(this);
	this.gyneQuestionnaire = new GynQuestionnaire(this);
}
ViewCaseUI.prototype.getCaseUIStartup = function() {
	return this.caseUIStartup;
}

ViewCaseUI.prototype.getGyneQuestionnaire = function() {
	return this.gyneQuestionnaire;
} 

ViewCaseUI.prototype.getViewCasePTB = function() {
	return this.viewCasePTB;
}

ViewCaseUI.prototype.getCaseTableNavigator = function() {
	var caseUIStartup = this.getCaseUIStartup();
	return caseUIStartup.getCaseTableNavigator();
}

ViewCaseUI.prototype.getCaseService = function() {
	var caseUIStartup = this.getCaseUIStartup();
	return caseUIStartup.getCaseService();
}

ViewCaseUI.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}

ViewCaseUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.viewCasePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
ViewCaseUI.prototype.init = function(patientId,patientName,caseId) {
	var self = this;
	self.patientId=patientId;
	self.caseId=caseId;
	var viewCasePTB = self.getViewCasePTB();
	viewCasePTB.init(self);
	pageHandler.create(constants.VIEWCASE);
	self.bindEvents();
	$j(self.patientName).text(patientName);
	self.ViewCase(caseId);
}
ViewCaseUI.prototype.autosaveRow = function(index,key,answer,ansSetId,mArray,mode) {
 var self=this;
        var param=self.getAutoCase(index,key,answer,self.answerSetId,mArray,mode);
		//alert(JSON.stringify(param));
        var postResponse;
 		jQuery.ajax({
			type: "POST",
			url: constants.CASEAUTOSAVEBYROWURL,
			data: JSON.stringify(param),
			contentType: "application/json;charset=utf-8",
			dataType: "json",
			async: false,
			success:function(response) {
				if(response==null){
					return false;
				}	
				else{
					postResponse = response;
				}	
			},
			error: function(xhr, ajaxOptions, thrownError) {
				alert(thrownError);
				postResponse = false;	
				 
			}
		});	 
		return postResponse;	
}
ViewCaseUI.prototype.ViewCase = function(caseId) {
	var self=this;
	var header = constants.VIEWCASETITLE;
	var caseService = self.getCaseService();
	var response = caseService.viewCase(caseId);
	self.answerSetId=response.answerSetId;
	//alert(JSON.stringify(response));
	if(response.success==false) {
		self.setCase(response);
	} else {
		commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
	}
	self.setPageTitle(header);
}

ViewCaseUI.prototype.setCase = function(caseObj) {
	var babycount=1;
	var self=this;
	self.caseId=caseObj.id;
	$j(self.caseName).val(caseObj.caseName);
	
	var date_old=caseObj.admittedDate;
	if(date_old!=null){
		d = date_old.substr(0, 10).split("-");
		new_date = d[2] + "-" + d[1] + "-" + d[0];
		$j(self.date).val(new_date);
	}
	$j(self.height).val(caseObj.height);
	$j(self.weight).val(caseObj.weight);
	$j(self.bmi).text(caseObj.bmi);
	$j(self.hb).val(caseObj.hbCount);
	$j(self.departmentlbl).text(caseObj.departmentName);
	$j(self.description).val(caseObj.description);
	$j(self.patientTypelbl).text(caseObj.patientType);
	self.departmentName=caseObj.departmentName;
	self.departmentId=caseObj.departmentId;
	if(caseObj.departmentName=="Obstetrics"){
	$j(self.question).show();
	var caseService=self.getCaseService();
	var gyneQuestionaire= caseService.getViewGyneQuestionnaire();
	var gyneModal=new GyneModalProcessor();
	var data=gyneModal.create(gyneQuestionaire,"view",caseObj);
	$j(self.questionnaire).empty().html("");
	$j(self.questionnaire).append(data); 
	self.toggle("General");
	var gyneQuestionnaire=self.getGyneQuestionnaire(self);
	gyneQuestionnaire.questionnaireActions(self.caseId);
	self.answerList=caseObj.questionAnswerDTO.answerDTO;
	  $j(caseObj.questionAnswerDTO.answerDTO).each(function(index,answer){
		 if(answer.questionKey=="babyGender_"+babycount){
		   babycount++;
		    methodInvoker.fillvaluesToQuestionNaire('#babyApgaronemin_'+babycount);
			methodInvoker.fillvaluesToQuestionNaire('#babyApgarfivemin_'+babycount);
			babycount=babycount;
		  }
		  if(answer.questionKey=="deliveryName" && answer.answer=="Caesarean")
		     $j("#questionnaire #csType").parent('span').show();
		 if(answer.answer=="Unknown"||answer.answer=="Deleted")
			$j('#'+answer.questionKey).val("Not Entered");
		else
		 $j('#'+answer.questionKey).val(answer.answer);
	  });
	  $j('.addbaby,.addfetal').hide();
	 $j('.deletebaby,.deletefetal').hide();
	 $j("#questionnaire #dateofAdmissionHospital,#questionnaire #lmp,#questionnaire #confinementDate,#questionnaire #dateTimeDelivery").datepicker().datepicker('disable');
}else {
     $j(self.generalDetails).show();
}
}

ViewCaseUI.prototype.getCase = function() {
	var self=this;
	var array=[];
	var caseDTO= new CaseDTO();
	var patientType=$j('input:radio[name=patientType]:checked').val();
    var caseName=$j(self.caseName).val();
		if(patientType=="InPatient"){
			var admitDate=$j(self.date).val();
			caseDTO.setAdmittedDate(admitDate);
		}	
		var questionAnswerDTO=new QuestionAnswerDTO();
		if(self.departmentName=="Obstetrics"){
			$j(self.questionnaire ).find("input[type=text],textarea").map(function(index, elm) {
				if ($j(elm).hasClass('ans'))
				array.push( {questionKey: elm.name,questionIndex: elm.id, answer: $j(elm).val()});
			});
			
			$j(self.questionnaire ).find("select").map(function(index, elm) {
				if ($j(elm).hasClass('ans'))
				array.push( {questionKey: elm.name,questionIndex: elm.id, answer: self.getSelectedAnswers(elm)});
			});
			questionAnswerDTO.setAnswerDTO(array);
		}
		else{
			var height=$j(self.height).val();
			var weight=$j(self.weight).val();
			var bmi=$j(self.bmi).text();
			var hb=$j(self.hb).val();
			var description=$j(self.description).val();
			caseDTO.setHeight(parseFloat(height));
			caseDTO.setWeight(parseFloat(weight));
			caseDTO.setBmi(parseFloat(bmi));
			caseDTO.setHbCount(parseFloat(hb));
			caseDTO.setDescription(description);
	}	

	caseDTO.setId(self.caseId);
	caseDTO.setPatientId(self.patientId);
	caseDTO.setPatientType(patientType);
	caseDTO.setDepartmentId(self.departmentId); 
	caseDTO.setDepartmentName(self.departmentName);
	caseDTO.setCaseName(caseName);
	caseDTO.setQuestionAnswerDTO(questionAnswerDTO);
	return caseDTO;
}

ViewCaseUI.prototype.getAutoCase = function(index,key,answer,ansSetId,multipleArray,mode) {
	var self=this;
	var departmentId=$j(self.department+" option:selected").val();
	var autoSaveDTO= new AutoSaveDTO();
		autoSaveDTO.setCaseId(self.caseId);
		autoSaveDTO.setAnswerSetId(ansSetId);
		autoSaveDTO.setDepartmentId(departmentId);
		if(mode=="array"){
			autoSaveDTO.setAnswerDTO(multipleArray);
		}
		else{
			var array=[];
		    array.push( {questionKey: key,questionIndex: index, answer: answer});
			autoSaveDTO.setAnswerDTO(array);
			}
		return autoSaveDTO;
} 

ViewCaseUI.prototype.getSelectedAnswers =function(elm){
	var items="";
	$j(elm).children(':selected').each(function(){
	if(items!="")
		items+=",";
	items+=$j(this).val();
	}); 
	return items;
}


ViewCaseUI.prototype.writable = function() {
	self=this;
	var empty ="Not Entered";
	$j(self.editButton).hide();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	var gyneQuestionnaire=self.getGyneQuestionnaire();
	gyneQuestionnaire.questionnaireActions(self.caseId);
	
	$j(self.questionnaire ).find("input[type=text],textarea,select").map(function(index,elm) {
	        if ( typeof $j(elm).attr('disabled')  ) {
			   $j(elm).removeAttr('disabled');
			}	
		if( $j(elm).hasClass('view')) 
			$j(elm).attr("style","display:none");
		else if( $j(elm).hasClass('edit'))				
			$j(elm).attr("style","display:block");
			});	
	
	$j(self.questionnaire ).find("select").map(function(index,elm) {
		$j(self.answerList).each(function(index,answer){
			if(elm.id==answer.questionKey){
				if(answer.answer=="Unknown"){
					$j('#'+answer.questionKey+" option[value='"+empty+"']").attr("selected","selected");
				}
				else{
					var ansArray = answer.answer.trim();
					if(ansArray.indexOf(',') === -1)
						$j('#'+answer.questionKey+" option[value='"+ansArray+"']").attr("selected","selected");
					else{
						var str_AnsArray=ansArray.split(',');
						$j(str_AnsArray).each(function(index2, curAnswer){
							$j('#'+answer.questionKey+" option[value='"+curAnswer+"']").attr("selected","selected");
						});
					}
				}
			}
		}); 
	});
			
	 $j(self.viewCasePage + " input[type=text],textarea").removeAttr('readonly');
	 $j(self.viewCasePage + " input[type=text],textarea").removeClass('newBox');
     $j(self.admittedDate).datepicker({ dateFormat: 'dd-mm-yy' }).val();
	 $j('.addbaby,.addfetal').show();
	 $j('.deletebaby,.deletefetal').show();
	$j(self.cancelButton).show();
	$j(self.updateButton).show();
	$j(self.viewCasePage +"#questionnaire #babyDiv").removeClass('one');
	$j(self.viewCasePage +"#questionnaire #babyDiv").addClass('viewOne');
   	$j("#questionnaire #dateofAdmissionHospital,#questionnaire #lmp,#questionnaire #confinementDate,#questionnaire #dateTimeDelivery").datepicker().datepicker('enable');
	$j(self.viewCasePage + " input[type=text],textarea").removeAttr('disabled');
	$j('#questionnaire #bodymassIndex').attr("readonly","readonly");
	$j('#questionnaire #bodymassIndex').addClass('newBox');
}

ViewCaseUI.prototype.readable = function() {
	self=this;
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
		$j(self.questionnaire ).find("input,textarea,select").map(function(index, elm) {
		if( $j(elm).hasClass('edit')) 
			$j(this).attr("style","display:none");
		else if( $j(elm).hasClass('view'))				
			$j(elm).attr("style","display:block");
		});			
		$j(self.questionnaire ).find("input,textarea,").map(function(index,elm) {
			if( $j(elm).hasClass('common')) 
				$j(this).addClass("newBox")	
		});
	$j(self.viewCasePage + " input[type=text],textarea").attr('readonly',true);
	$j(self.viewCasePage + " input[type=text],textarea").addClass('newBox');
	$j(self.cancelButton).hide();
	$j(self.updateButton).hide();
	$j(self.editButton).show();
	$j(self.viewType).show();
	$j(self.editType).hide();
	$j(self.viewCasePage +"#questionnaire #babyDiv").addClass('one');
	$j(self.viewCasePage +"#questionnaire #babyDiv").removeClass('viewOne'); 
	$j("#questionnaire #dateofAdmissionHospital,#questionnaire #lmp,#questionnaire #confinementDate,#questionnaire #dateTimeDelivery").removeClass('hasDatepicker');
	$j('.addbaby,.addfetal').hide();
	$j('.deletebaby,.deletefetal').hide();
	$j('#questionnaire .babyWeight').removeClass('newBox');
	$j('#questionnaire .DeliveryTime').removeClass('newBox');
	$j(self.viewCasePage + " input[type=text],textarea").attr('disabled',true);
}

ViewCaseUI.prototype.getPrevId = function(curId,caseResult) {
	var prevId;
	$j(caseResult.caselist).each(function (index, rowCase) {
		if(curId==rowCase.id)	{
			var arrayLength=(caseResult.caselist).length;
			var comp=arrayLength-1;
			if(index==0)
				prevId = curId;
			else
				prevId=caseResult.caselist[index-1].id;
		}
	});
	return prevId;	
}
	
ViewCaseUI.prototype.getNextId = function(curId,caseResult) {
	var nextId;
	$j(caseResult.caselist).each(function (index, rowCase) {
		if(curId==rowCase.id)	{
			var arrayLength=(caseResult.caselist).length;
			var comp=arrayLength-1;
			if(index==comp){
				nextId = curId;
			}else{
					nextId=caseResult.caselist[index+1].id;
			}	
		}
	});	
	return nextId;	
}

ViewCaseUI.prototype.bindEvents = function() {
	self = this;	
	$j(self.editButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.writable(); 
		
	});
	$j(self.cancelButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		self.ViewCase(self.caseId);
		self.readable();
	});
	
	$j(self.updateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var caseService=self.getCaseService();
		var response=caseService.getPatientDetail(self.patientId);
		var caseObj = self.getCase();
		//alert(JSON.stringify(caseObj));
		 var caseValidator = new CaseValidator();
		var error  = caseValidator.validate(caseObj,response); 
		if(error.errorStatus==false) {
			var caseResponse = caseService.updateCase(caseObj);
			//alert(JSON.stringify(caseResponse));
			if(caseResponse.success==true) {
				showTip(constants.CASEUPDATESUCCESS);//For showing the global Tip
				self.answerList=caseObj.questionAnswerDTO.answerDTO;
				self.ViewCase(caseResponse.id);
				self.readable();
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(caseResponse.error));
			}
		 } else {
			self.createError(error); 
		}		
	});

var height;
var weight;
$j(self.height).focusout(function(){
	height=parseFloat($j(this).val())|| 0;
	if(height!=0)
		height=height/100; 
});  
$j(self.weight).focusout(function(){
	weight=parseFloat($j("#weight").val())|| 0;
	if(height!=0&&weight!=0){
		var bmi=weight/(height*height)
		var bmiValue=bmi.toFixed(2);
	}
	$j(self.bmi).text(bmiValue);
}); 		
	
}
$j(".accordion").die ('click').live('click', function() {
	currentSource=$j(this).children('th').children('img');
	var currentSection=currentSource[0].id;
	self.toggle(currentSection);
});

ViewCaseUI.prototype.toggle = function(currentSection) {
$j(self.questionnaire).find(".accordion").each(function() {
	var source = $j(this).children('th').children('img');
	var section=source[0].id;
	if(section==currentSection){
		if(source.attr('name')=="expand") {
			source.attr('src','/netmd/images/collapse.jpg');
			source.attr('name','collapse');
			source.parents('thead').next('tbody').show();
		}
		else {
			 source.attr('src','/netmd/images/expand.jpg');
			 source.attr('name','expand');
			 source.parents('thead').next('tbody').hide();
			}	
	}else{
		 source.attr('src','/netmd/images/expand.jpg');
		 source.attr('name','expand');
		 source.parents('thead').next('tbody').hide();
		 }
	});
}