
function newQuestionnaireProcessor() {
}


newQuestionnaireProcessor.prototype.createQuestionnaire = function(jsonResponse,mode,QuestionnaireResult) {
	var self=this;
	var mainDiv=$j('<div/>');
	$j(jsonResponse.data).each(function(index,element) {
		var parentDiv =$j('<div id="parentDiv" class="QuestionParent"></div>');
		if(element.id=="maternalDeathDetails"){
			var DeathData =self.createDeathDetailsSection(mode,QuestionnaireResult);
			parentDiv.append(DeathData);
			mainDiv.append(parentDiv);
		}
		else{
			var questionDiv= $j('<span class="Question" id="questionDiv">'+element.qstnNmbr+". "+element.question+'</span>');
			var ansDiv= $j('<span id="ansDiv" class="Answer"></span>');
			var ansTag;
			
			$j(element.answer).each(function(index,ans){
			if(ans.type=="String")
				ansTag=$j('<input type="text" id="'+element.id+'" ></input>');
			else if(ans.type=="select"){
				ansTag=$j('<select type="select" id="'+element.id+'" ></input>');
				var optionTag=$j('<option value="select">&nbsp;</option>');
				ansTag.append(optionTag);
				$j(ans.attribute).each(function(index,attr){
					optionTag = $j('<option  value="'+attr.option+'"/>');
					optionTag.html(attr.option);
					ansTag.append(optionTag);
				   }); 
				}
				if(ans.className)
					ansTag.addClass(ans.className);
				if(ans.readonly)
					ansTag.attr("readonly",ans.readonly);
				if(ans.style)
                     ansTag.attr('style',ans.style);	
			ansDiv.append(ansTag);
			parentDiv.append(questionDiv);
			parentDiv.append(ansDiv);
			mainDiv.append(parentDiv);
			});					
			
		}
	});
	return	mainDiv;
 }
	
newQuestionnaireProcessor.prototype.createDeathDetailsSection = function(mode,QuestionnaireResult) {
		var self=this;
		var deathCount=1;
		var parent = $j('<div id="deathDetail" style="display:none;"/>');
		 var mainhead = $j('<div class="C"/>');
		var subhead = $j('<div class="B bold"> &nbsp; </div>');
		mainhead.append(subhead);
		var subhead = $j('<div class="B bold"> '+"Name"+'</div>');
		mainhead.append(subhead);
		subhead = $j('<div class="B bold">'+"Cause Of Death"+'</div>');
		mainhead.append(subhead);
		subhead = $j('<div class="B bold">'+"Address"+'</div>');
		mainhead.append(subhead);
		subhead = $j('<div style="width:16%;float:left;">&nbsp;</div>');
		mainhead.append(subhead);
		parent.append(mainhead);
		$j(parent).addClass('deathone'); 
		
		if(mode=="view"){
			$j(QuestionnaireResult.answerDTO).each(function(index,answer){
				if(answer.questionKey=="death"+deathCount+"name"){
					var DeathDetails=methodInvoker.addDeathDetailsRow(deathCount,"view");
					parent.append(DeathDetails);
					deathCount++;
	            }	
				
	        });
			if(deathCount==1){
				var DeathDetails=methodInvoker.addDeathDetailsRow(deathCount,"view");
				parent.append(DeathDetails);
			}
		}
		else{
		var DeathDetails=methodInvoker.addDeathDetailsRow(deathCount);
		parent.append(DeathDetails);
		}
		return parent;
	}

newQuestionnaireProcessor.prototype.bindEvents = function() {
	 var self = this;
	$j('.addDeath').die('click').live('click',function(){
		var deathcount = self.getDeathCount();
		deathcount++;
		if(deathcount<='10'){
		var deathSection=methodInvoker.addDeathDetailsRow(deathcount);
		 $j('.deathone').append(deathSection);
		 }
		});	
	
	 $j('.deleteDeath').die('click').live('click',function(){
		 if($j(this).parent().parent().nextAll(':first').html()!=null){
	       alert(" sorry can't delete inner row");
		 }
		else {
		$j(this).parent().parent().remove();
		 deathcount--;
		 deathcount=deathcount;
	 }	 
	}); 
	
	$j("#AntenatalMaternalDeath").change(function(){
		var ansval;
		ansval=$j(this).val()
		if(ansval=="YES")
			$j("#deathDetail").show();
		else	
		    $j("#deathDetail").hide();
	  }); 

	
 }
	
newQuestionnaireProcessor.prototype.getDeathCount= function() {
 var  deathNumber;
  deathNumber=$j('div #deathDetail').children(':last').attr('number');
 return deathNumber;
}
