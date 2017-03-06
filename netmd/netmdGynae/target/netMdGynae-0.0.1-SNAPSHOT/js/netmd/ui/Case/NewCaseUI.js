function NewCaseUI(caseUIStartup) {
	this.createButton = $j("#newCase #btnCaseSave");
	this.cancelButton = $j('#newCase #btnCaseCancel');
	this.newCasePage = "#newCase";
	this.caseModal = '#caseModal';
	this.errorHeader = $j('#caseModal #errorDivHeader');
	this.errorData = $j('#caseModal #errorDivNewCase');
	this.patientName='#newCase #patientName';
	this.caseName="#newCase #case";
	this.patientType="#newCase #patientType";
	this.date='#newCase #date';
	this.admittedDate='#newCase #admittedDate';
	this.height="#newCase #height";
	this.weight='#newCase #weight';
	this.bmi='#newCase #bmi label';
	this.hb='#newCase #hb';
	this.department="#newCase #department";
	this.question="#newCase #question";
	this.general="#newCase #general";
	this.generalDetails="#newCase #generalDetails";
	this.questionnaire="#newCase #questionnaire";
	this.desc="#newCase #desc";
	this.patientCaseDiv='#newCase #patientCaseAddDiv';
	this.description="#newCase #description";
	this.outPatient="#newCase #patientType1";
	this.inPatient="#newCase #patientType0";
	this.inputFields = ":input";
	this.patientId;
	this.caseUIStartup = caseUIStartup;
	this.gyneQuestionnaire = new GynQuestionnaire(this);
	this.caseId=0;
	this.answerSetId=0;
	this.patientDetails="";
	this.newcaseAddress="#newCase #address";
	this.phoneNumber="#newCase #phoneNumber";
	this.Name="#newCase #name";
	this.Age="#newCase #age";
	this.Rh="#newCase #rh";
	this.BloodGroup="#newCase #bloodgroup";
	this.Education="#newCase #education";	
	this.dateOfAdmissn="#newCase #questionnaire #dateofAdmissionHospital";
}

NewCaseUI.prototype.getCaseUIStartup = function() {
	return this.caseUIStartup;
}

NewCaseUI.prototype.getCaseTableNavigator = function() {
	var caseUIStartup = this.getCaseUIStartup();
	return caseUIStartup.getCaseTableNavigator();
}
NewCaseUI.prototype.getGyneQuestionnaire = function() {
	return this.gyneQuestionnaire;
} 
NewCaseUI.prototype.getCaseService = function() {
	var caseUIStartup = this.getCaseUIStartup();
	return caseUIStartup.getCaseService();
}
NewCaseUI.prototype.createError = function(error) {
	var self=this;
	$j(error.errorMsgs).each(function(index, errormsg) {
		commonMethodInvoker.createError($j(self.newCasePage + " #" + errormsg.errorField), errormsg.errorMessage);
	});
}
NewCaseUI.prototype.removecolors = function(cl) {
	commonMethodInvoker.removeErrorColor(self.name);
}
NewCaseUI.prototype.init = function(patientId) {
	var self=this;
	var caseService=self.getCaseService();
	self.patientDetails=caseService.getPatientDetails();
	methodInvoker.fillPatientDetailsToControl(self.patientName,self.patientDetails);
	self.patientId=patientId;
	var response=caseService.getPatientDetail(self.patientId);
	var patName =response.firstName+" "+response.lastName;
		$j(self.patientName).val(patName);
		 $j(self.patientName).autocomplete({
		 select: function(e, ui){
		 var newPatientName;
		   $j(self.patientName).val(ui.item.value);
			commonMethodInvoker.removeErrors();
			var patientName=$j(self.patientName).val();
			if(patientName.indexOf('[')==-1){
				newPatientName=patientName;
			}
			else{
				newPatientName=patientName.split("[")[0];
				newPatientName=$j.trim(newPatientName);
			}	
			  this.value =newPatientName;
			  return false;
		}
	});	 
		 
	$j(self.date).datepicker({ dateFormat: 'dd-mm-yy' }).val();
	
	var departmentdetails=caseService.getDepartments();
	methodInvoker.fillDepartmentToControl(self.department,departmentdetails);
}

NewCaseUI.prototype.autosaveRow = function(index,key,answer,ansSetId,mArray,mode) {
  var self=this;
  self.answerSetId=ansSetId;
        var param=self.getAutoCase(index,key,answer,ansSetId,mArray,mode);
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


NewCaseUI.prototype.getCaseCreate = function() {
	var self=this;
	var caseDTO= new CaseDTO();
		var patientType=$j('input:radio[name=patientType]:checked').val();
		var departmentName=$j(self.department+" option:selected").text();
		var departmentId=$j(self.department+" option:selected").val();
	     var caseName=$j(self.caseName).val();
		if(patientType=="InPatient"){
			var admitDate=$j(self.date).val();
			if(admitDate=="")
			 admitDate=null;
			caseDTO.setAdmittedDate(admitDate);
		}	
	if(departmentName=="General"){
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
		if(self.caseId!=0)
			caseDTO.setId(self.caseId);
			caseDTO.setPatientId(self.patientId);
			caseDTO.setPatientType(patientType);
			caseDTO.setDepartmentId(departmentId); 
			caseDTO.setDepartmentName(departmentName);
			caseDTO.setCaseName(caseName);
		return caseDTO;
} 
 NewCaseUI.prototype.getCase = function() {
	var self=this;
	var array=[];
	var caseDTO= new CaseDTO();
		var patientType=$j('input:radio[name=patientType]:checked').val();
	     var caseName=$j(self.caseName).val();
		if(patientType=="InPatient"){
			var admitDate=$j(self.date).val();
			if(admitDate=="")
			 admitDate=null;
			caseDTO.setAdmittedDate(admitDate);
		}	
		var departmentName=$j(self.department+" option:selected").text();
		var departmentId=$j(self.department+" option:selected").val();
		var questionAnswerDTO=new QuestionAnswerDTO();
		questionAnswerDTO.setDepartmentId(departmentId);
		questionAnswerDTO.setAnswerSetId(self.answerSetId);
		if(departmentName=="Obstetrics"){
			$j(self.questionnaire ).find("input[type=text],textarea").map(function(index, elm) {
				array.push( {questionKey: elm.name,questionIndex: elm.id, answer: $j(elm).val()});
			});
			
			$j(self.questionnaire ).find("select").map(function(index, elm) {
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
		
		if(self.caseId!=0)
			caseDTO.setId(self.caseId);
			caseDTO.setPatientId(self.patientId);
			caseDTO.setPatientType(patientType);
			caseDTO.setDepartmentId(departmentId); 
			caseDTO.setDepartmentName(departmentName);
			caseDTO.setCaseName(caseName);
			caseDTO.setQuestionAnswerDTO(questionAnswerDTO);
		return caseDTO;
} 
 
NewCaseUI.prototype.getAutoCase = function(index,key,answer,ansSetId,multipleArray,mode) {
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

NewCaseUI.prototype.getSelectedAnswers =function(elm){
	var items="";
	$j(elm).children(':selected').each(function(){
	if(items!="")
		items+=",";
	items+=$j(this).val();
	}); 
	return items;
}
NewCaseUI.prototype.clearFields = function() {
	self = this;
	$j(self.newCasePage + " input[type=text],textarea").val("");
	$j(self.name ).focus();
}


NewCaseUI.prototype.bindEvents = function() {
	self = this;
	self.removecolors(self.newCasePage + " " + self.inputFields);
	
	
	$j(self.patientCaseDiv).on( "focusout",function() {
	 	self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var caseService=self.getCaseService();
		var response=caseService.getPatientDetail(self.patientId);
		var caseObj = self.getCaseCreate();
		var caseValidator = new CaseValidator();
		var error  = caseValidator.validate(caseObj,response);
		if(error.errorStatus==false) {
			var caseService = self.getCaseService();	
			var caseResponse = caseService.createCase(caseObj);
			self.caseId=caseResponse.id;
		 }
		 else 
			self.createError(error);     
	});	
	
	
	
	$j(self.newCasePage+"input[type=text]").bind("keypress", function (e) {		
		if (e.keyCode == 13) {
			e.preventDefault();
			return false;
		}
	});

	$j(self.caseModal + ' .close-reveal-modal').die('click').live('click',function(){	
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.caseModal + self.newCasePage + " input[type=text]").val("");	
		$j(self.caseModal).trigger('reveal:close');
		$j(self.caseModal).remove();
		self=self.getCaseUIStartup();
	});
	$j(self.outPatient).click(function(){
		$j(self.admittedDate).hide();
	});
	$j(self.inPatient).click(function(){
	   $j(self.date).val("");
		$j(self.admittedDate).show();
	});
	$j(self.department).change(function(){	
		var department=$j(self.department +" option:selected").text();
		var caseService=self.getCaseService();
		var gyneQuestionaire= caseService.getGyneQuestionnaire();
		var response=caseService.getPatientDetail(self.patientId);
		if(department=="Obstetrics"){
			if(response.gender=="Female"){
			$j(self.questionnaire).html("");
			$j(self.general).hide();
			$j(self.desc).hide();
			$j(self.question).show();
			$j(self.caseName).val("Obstetrics");
			var gyneModal=new GyneModalProcessor();
			var data=gyneModal.create(gyneQuestionaire,"","",self);
			$j(self.questionnaire).append(data);
			self.toggle("General");
			var gyneQuestionnaire=self.getGyneQuestionnaire(self);
		 	gyneQuestionnaire.questionnaireActions();
			$j(self.newcaseAddress).val(response.address);
			if(response.phone!="")
				$j(self.phoneNumber).val(response.phone);
			else
				$j(self.phoneNumber).val(response.mobile);
			$j(self.Name).val(response.firstName+" "+response.lastName);
			if(response.age==0)
				$j(self.Age).val("");
			else
				$j(self.Age).val(response.age);
			$j(self.BloodGroup).val(response.bloodGroup);
			if(response.rh=="Positive")
				$j(self.Rh).val("+ve");
			else
				$j(self.Rh).val("-ve");
			$j(self.Education).val(response.education);
			
			//var AdmDate=$j(self.date).val();
			//$j(self.dateOfAdmissn).val(AdmDate);
			
			//$j(self.BloodGroup).attr("disabled","disabled"); 
			//$j(self.Rh).attr("disabled","disabled"); 
			//$j(self.Education).attr("disabled","disabled"); 
		  }else
			updateTipsNew((constants.THEPATIENTISMALE),$j('#newCase #errorDivNewCase'),$j('#newCase #errorDivHeader'));
		 }
		 else if(department=="General") {
				$j(self.general).show();
				$j(self.desc).show();
				$j(self.question).hide();
				$j(self.caseName).val("");
		  }
		  else{
		  $j(self.general).hide();
		 $j(self.question).hide();
		  }
		
	});
  
    self.createButton.die('click').live('click',function( ){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var caseService=self.getCaseService();
		var response=caseService.getPatientDetail(self.patientId);
		var caseObj = self.getCase();
		//alert(JSON.stringify(caseObj));
		var caseValidator = new CaseValidator();
		var error  = caseValidator.validate(caseObj,response);
		if(error.errorStatus==false) {
			var caseService = self.getCaseService();	
			var caseResponse = caseService.createAutoCase(caseObj);
			//alert(JSON.stringify(caseResponse));
			if(caseResponse.success==true) {
				showTip(constants.CASECREATESUCCESS);//For showing the global Tip
				self.clearFields();
				$j(self.caseModal + ' .close-reveal-modal').trigger('click');
				var caseTableNavigator = self.getCaseTableNavigator();
				caseTableNavigator.list();
			} else 
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(caseResponse.error));
			
		 } else 
			self.createError(error);  
			
});
	self.cancelButton.die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		$j(self.caseModal + self.newCasePage + " input[type=text]").val("");	
		$j(self.caseModal).trigger('reveal:close');
		$j(self.caseModal).remove();
		self=self.getCaseUIStartup();
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
		bmi=bmi/10000;
		var bmiValue=bmi.toFixed(2);
	}
	$j(self.bmi).text(bmiValue);
}); 
	
$j(self.height+","+self.weight+","+self.bmi+","+self.hb).keyup(function(){
    var value = $j(this).val();
	value = value.replace(/[^\d.]/g, '');    
    $j(this).val(value);
}); 
			
	$j(self.date).change(function(){
		var AdmDate=$j(self.date).val();
		$j(self.dateOfAdmissn).val(AdmDate+' 00:00 AM');
	});
}

$j(".accordion").die ('click').live('click', function() {
	currentSource=$j(this).children('th').children('img');
	var currentSection=currentSource[0].id;
	self.toggle(currentSection);
});

NewCaseUI.prototype.toggle = function(currentSection) {
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