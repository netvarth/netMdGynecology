function GynQuestionnaire(caseUI) {
	this.caseUI=caseUI;
	this.flag=false;
	this.className;
	this.answerSetId=0;
	this.caseId=0;
	this.sectionId="general";
}
GynQuestionnaire.prototype.questionnaireActions= function(caseId) {
	var self=this;
	var fetalcount=1;
	var babycount=1;
	var babyFunction =function() {
		var array=[];
		array.length = 0;
		var mode="array";
		$j('#questionnaire #babyDiv').find("input[type=text],select").map(function(index, elm) {
		array.push( {questionKey: elm.name,questionIndex: elm.id, answer: $j(elm).val()});
		});
		var response=self.caseUI.autosaveRow("","","",self.answerSetId,array,mode);
		self.answerSetId=response.ansSetId; 
	}
	
	var foetalFunction =function() {
		var array=[];
		array.length = 0;
		var mode="array";
		$j('#questionnaire #foetalDiv').find("input[type=text],select").map(function(index, elm) {
		array.push( {questionKey: elm.name,questionIndex: elm.id, answer: $j(elm).val()});
		});
		var response=self.caseUI.autosaveRow("","","",self.answerSetId,array,mode);
		self.answerSetId=response.ansSetId; 
	}
	
	$j(function(){
		var pickerOpts = {
			changeMonth: true,
			changeYear: true
		};  
    $j("#questionnaire .dateTime").datetimepicker(pickerOpts);
  });
	$j("#questionnaire .date").datepicker({ dateFormat: 'dd-mm-yy' }).val();
	
	//$j("#questionnaire .time").timepicker();
	
	var height;
	var weight;
	$j("#questionnaire #height").focusout(function(){
		height=parseFloat($j(this).val())|| 0;
		if(height!=0)
			height=height/100; 		
		 weight=parseFloat($j("#weight").val())|| 0;
		if(height!=0&&weight!=0){
			var bmi=weight/(height*height)
			bmi=bmi/10000;
			var bmiValue=bmi.toFixed(2);
		}
		$j("#questionnaire #bodymassIndex").val(bmiValue); 
	});  
	$j("#questionnaire #weight").focusout(function(){
		height=parseFloat($j("#height").val())|| 0;
		if(height!=0)
			height=height/100; 
		weight=parseFloat($j("#weight").val())|| 0;
		if(height!=0&&weight!=0){
			var bmi=weight/(height*height)
			bmi=bmi/10000;
			var bmiValue=bmi.toFixed(2);
		}
		$j("#questionnaire #bodymassIndex").val(bmiValue);
	});  
	
	fillDoctorAsAutoComplete('doctorIncharge');
	fillDoctorAsAutoComplete('conductedBy');  
	
	$j("#questionnaire #isTribal").change(function(){
		var ansval;
		ansval=$j(this).val()
		if(ansval=="Yes")
			$j("#questionnaire #tribal").parent('p').attr("style","display:block");
		else{
			$j("#questionnaire #tribal option[value='select']").attr("selected","selected");
			$j("#questionnaire #tribal").parent('p').attr("style","display:none");
		}
	  }); 

	  
	  $j("#questionnaire #maternalDeath").change(function(){
		var ansval;
		ansval=$j(this).val()
		if(ansval=="Yes")
			$j("#questionnaire #maternalDeathType").parent('p').attr("style","display:block");
		else{
			$j("#questionnaire #maternalDeathType option[value='select']").attr("selected","selected");
			$j("#questionnaire #maternalDeathType").parent('p').attr("style","display:none");
		}
	  }); 
   $j("#questionnaire #severeHyper").change(function(){
	var ansval;
	ansval=$j(this).val()
    if(ansval=="Yes")
		$j("#questionnaire #antiSevereHyper").parent('p').attr("style","display:block");
	else{
		$j("#questionnaire #antiSevereHyper option[value='select']").attr("selected","selected");
		$j("#questionnaire #antiSevereHyper").parent('p').attr("style","display:none");
	}
  });
  $j("#questionnaire #heartDisease").change(function(){
	var ansval;
	ansval=$j(this).val()
    if(ansval=="Yes"){
		$j("#questionnaire #heartRelatedDisease").parent('p').attr("style","display:block");
		$j('.multiselect').multiselect();
	}
	else{
		$j("#questionnaire #heartRelatedDisease option[value='select']").attr("selected","selected");
		$j("#questionnaire #heartRelatedDisease").parent('p').attr("style","display:none");
	}
  
  });
 $j("#questionnaire #eclampsia").change(function(){
	var ansval;
	ansval=$j(this).val()
    if(ansval=="Yes")
	$j("#questionnaire #isMgso4").parent('p').attr("style","display:block");
	else{
	$j("#questionnaire #isMgso4 option[value='select']").attr("selected","selected");
	$j("#questionnaire #isMgso4").parent('p').attr("style","display:none");
  }
  
  });
    $j("#questionnaire #help").change(function(){
	var ansval;
	ansval=$j(this).val()
    if(ansval=="Yes")
		$j("#questionnaire #isReceivedBldPrdt").parent('p').attr("style","display:block");
	else{
		$j("#questionnaire #isReceivedBldPrdt option[value='select']").attr("selected","selected");
		$j("#questionnaire #isReceivedBldPrdt").parent('p').attr("style","display:none");
    } 
  });
    $j("#questionnaire #mildHyperTension").change(function(){
	var ansval;
	ansval=$j(this).val()
    if(ansval=="Yes")
		$j("#questionnaire #antiMildHyper").parent('p').attr("style","display:block");
		
	else{
		$j("#questionnaire #antiMildHyper option[value='select']").attr("selected","selected");
		$j("#questionnaire #antiMildHyper").parent('p').attr("style","display:none");
    }
  
  });
  $j("#questionnaire #age, #questionnaire #previousCS,#questionnaire #phoneNumber ,#questionnaire #bloodLoss ,#questionnaire #amtFluids").keyup(function(){
    var value = $j(this).val();
    value = value.replace(/[^0-9]+/g, '');
    $j(this).val(value);
 });
$j('#questionnaire #height,#questionnaire #weight,#questionnaire #indDelInterval ,#questionnaire #placentalWght,#questionnaire #thirdStageDuration').keyup(function(){
    var value = $j(this).val();
	value = value.replace(/[^\d.]/g, '');    
    $j(this).val(value);
}); 

$j('#questionnaire #height').keyup(function(){
  if ($j(this).val() >100){
	$j("#questionnaire #height").val("");
  }
});

$j('#questionnaire #weight').keyup(function(){
  if ($j(this).val() >200){
	 $j("#questionnaire #weight").val("");
  }
});

var minOffset = 0, maxOffset = 20; 
var select = $j("#questionnaire #lcb,.gravidity,.parity,.abortion,.livingChild");
$j(select).empty();
$j(select).append('<option value="select">&nbsp;</option>');
for (var i = minOffset; i <= maxOffset; i++) {
    $j('<option>', {value: i, text: i}).appendTo(select);
} 
$j(select).append('<option value="Not Entered">Not Entered</option>');


var minOffset = 0, maxOffset = 9; 
var select = $j(".apgar");
$j(select).empty();
$j(select).append('<option value="select">&nbsp;</option>');
for (var i = minOffset; i <= maxOffset; i++) {
    $j('<option>', {value: i, text: i}).appendTo(select);
} 
$j(select).append('<option value="Not Entered">Not Entered</option>');


var minOffset = 1, maxOffset = 12; 
var select = $j("#questionnaire #lcbMonths");
$j(select).empty();
$j(select).append('<option value="select">&nbsp;</option>');
for (var i = minOffset; i <= maxOffset; i++) {
    $j('<option>', {value: i, text: i}).appendTo(select);
}
$j(select).append('<option value="Not Entered">Not Entered</option>');


var minNum = 1, maxNum = 8; 
var select = $j("#questionnaire #numberOfbaby");
$j(select).empty();
$j(select).append('<option value="select">&nbsp;</option>');
for (var i = minNum; i <= maxNum; i++) {
    $j('<option>', {value: i, text: i}).appendTo(select);
} 
$j(select).append('<option value="Not Entered">Not Entered</option>');


var start=0,end=6;
var select = $j("#questionnaire #gestationDay");
$j(select).empty();
$j(select).append('<option value="select">&nbsp;</option>');
for (var i = start; i <= end; i++) {
    $j('<option>', {value: i, text: i}).appendTo(select);
} 
$j(select).append('<option value="Not Entered">Not Entered</option>');


var min = 0, max = 44; 
var select = $j("#questionnaire #gestationWeek");
$j(select).empty();
$j(select).append('<option value="select">&nbsp;</option>');
for (var i = min; i <= max; i++) {
    $j('<option>', {value: i, text: i}).appendTo(select);
} 
$j(select).append('<option value="Not Entered">Not Entered</option>');


$j('#questionnaire #deliveryName').change(function(){
	var deliveryName=$j("#questionnaire #deliveryName option:selected").text();
	if(deliveryName=="Caesarean")
		$j("#questionnaire #csType").parent('span').show();
	else
		$j("#questionnaire #csType").parent('span').hide();
});


$j('.addbaby').die('click').live('click',function(){
	babycount = self.getBabyCount();
	babycount++;
	if(babycount<='8'){
	var babysection=methodInvoker.addBabyRow(babycount);
	 $j('.babyone').append(babysection);
	 methodInvoker.fillvaluesToQuestionNaire('#babyApgaronemin_'+babycount);
	 methodInvoker.fillvaluesToQuestionNaire('#babyApgarfivemin_'+babycount);
	 }
	 babysection.bind('click',babyFunction);
	
	}); 	
	
	$j('.deleteBaby').die('click').live('click',function(){
		 if($j(this).parent().parent().nextAll('div.one:first').html()!=null){
	       alert(" sorry can't delete inner row");
		 }
		else {
			var array=[];
			 var questionAnswerDTO=new QuestionAnswerDTO();
			$j(this).parent().parent().children().each(function(){
			   var index=$j(this).children().attr('id');
				var answer=$j(this).children().val();
				var id=$j(this).children().attr('name');
				array.push( {questionKey: id,questionIndex: index, answer: answer});
				
			});
			questionAnswerDTO.setCaseId(caseId);
			questionAnswerDTO.setAnswerDTO(array);
			var caseImpl=new CaseServiceImpl();
			var response = caseImpl.deleteEachRow(questionAnswerDTO);
		$j(this).parent().parent().remove();
		 babycount--;
		 babycount=babycount;
	  }	 
	});
	
	
	$j('.addfetal').die('click').live('click',function(){
	    fetalcount = parseInt(self.getFetalCount());
		fetalcount++;
		if(fetalcount<='8'){
		var fetalsection=methodInvoker.addFetalRow(fetalcount);
		$j('.foetusone').append(fetalsection);
		}
		fetalsection.bind('click',foetalFunction);
	});
	
	$j('.deleteFetal').die('click').live('click',function(){
		if($j(this).parent().parent().nextAll('div.one:first').html()!=null){
	       alert(" sorry can't delete inner row");
		 }
		else {
		var array=[];
			 var questionAnswerDTO=new QuestionAnswerDTO();
			$j(this).parent().parent().children().each(function(){
			   var index=$j(this).children().attr('id');
				var answer=$j(this).children().val();
				var id=$j(this).children().attr('name');
				array.push( {questionKey: id,questionIndex: index, answer: answer});
				
			});
			questionAnswerDTO.setCaseId(caseId);
			questionAnswerDTO.setAnswerDTO(array);
			var caseImpl=new CaseServiceImpl();
			var response = caseImpl.deleteEachRow(questionAnswerDTO);
			$j(this).parent().parent().remove();
			fetalcount--;
			fetalcount=fetalcount;
		}	 
	});	
 	
GynQuestionnaire.prototype.getBabyCount= function() {
 var  babyNumber;
  babyNumber=$j('div .babyone').children('.one:last').children(':first').attr('number');
 return babyNumber;
}

GynQuestionnaire.prototype.getFetalCount= function() {
 var  fetalNumber;
 fetalNumber=$j('div.foetusone').children('.one:last').children(':first').attr('number');
 return fetalNumber;
}

$j('#questionnaire .gyne-content').find("input[type=text],textarea,select").on( "focusout",function() {
	var mode="";
	 var index=this.id;
	 var key=this.name;
	 var answer=this.value;
	var response=self.caseUI.autosaveRow(index,key,answer,self.answerSetId,"",mode);
	self.answerSetId=response.ansSetId; 
	});	
	
	$j('#questionnaire #babyDiv').on("focusout",babyFunction);
	
	$j('#questionnaire #foetalDiv').on("focusout",foetalFunction);
	
}
