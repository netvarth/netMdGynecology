function GyneModalProcessor() {

}

GyneModalProcessor.prototype.create = function(response,mode,caseResult) {
	var babycount=0;
	var self=this;
	var parent=self;
	var parentDiv =$j('<div>');
	var innerDiv=$j('<div id="GyneModalProcessor" />');	
	var table=$j('<table />');
	table.attr('class','stdtable');
	$j(response.data).each(function(index,element) {
		var thead = $j('<thead >');
		thead.attr('id','thead_'+index);
		var headRow = $j('<tr style="background:DDD9D9; " width="100%"/>');
		headRow.attr('class','accordion');
		var headCol = $j('<th style="text-align:left;border-radius:4px;line-height:1.4;" colspan="4" />');
		var headCheck = $j('<img class=expand_collapse id='+element.id+' name="expandimg" align="center" src="/netmd/images/expandimg.png">&nbsp&nbsp<label ><font color="#fff">'+element.section+'</font></label>');
		headCol.append(headCheck);
		headRow.html(headCol);
		thead.html(headRow);
		table.append(thead);
		var tbody=$j('<tbody class="'+element.className+'" id="'+element.id+'"/>');
		if(element.id=="baby"){
			var babySection=parent.createHeaderSection("baby","Gender","Weight(gms)","Delivery Time","Apgar(1min)","Apgar(5min)","Presentation","Del.Type",mode,caseResult);
			tbody.append(babySection);
		}
		else if(element.id=="foetus"){
			var fetalsection=parent.createHeaderSection("foetus","Cong-Anomali","Cong-Anomali Reason","NICU ","NICU-Reason","Still birth","Neonatal Death","",mode,caseResult);	 
			tbody.append(fetalsection);
		}
		else{
			$j(element.values).each(function(index,data){
			 	if(data.type=="multipleSelectBox"){
				var multiDropdown=parent.createMultiSelect(data,mode);
				tbody.append(multiDropdown);
				}
				else{ 
				var divObj=parent.createQuestionnaire(data,index,mode);				
				tbody.append(divObj);
				}
			});	
        }		
		table.append(tbody);			   
	});       
	innerDiv.append(table);
	parentDiv.append(innerDiv);
	return parentDiv;
}
GyneModalProcessor.prototype.createQuestionnaire = function(data,index,mode) {
var self=this;
	if(index%2==0){
		var maindiv=$j('<div class="five-sixth"');
		var className="column-left";
	}
    else
	    var className="column-right";
	var div=$j('<div class="gyne-content"> </div>');
	var pTag=$j('<p></p>');
	if(data.style)
		pTag.attr('style',data.style);
	if (data.className)
		pTag.addClass(data.className);
	var spanQuest=$j('<span class="questStyle">'+data.question+' &nbsp;</span>');		
	pTag.append(spanQuest);
	if(data.required){
	  spanTag = $j('<span class="req_astrisk">*</span>');
	  pTag.append(spanTag);
	  }
	var breakTag=$j('</br>');
	pTag.append(breakTag);
	$j(data.answer).each(function(index,ans){
		var ansTag;
		if(ans.type=="String"||ans.type=="Integer")
			ansTag=$j('<input type="text" id="'+data.id+'" name="'+data.id+'"></input> ');
        if(ans.type=="textarea")
            ansTag=$j('<textarea  id="'+data.id+'" name="'+data.id+'"></textarea> ');   							
		else if(ans.type=="select" ){
			ansTag= $j('<select id="'+data.id+'" name="'+data.id+'" ></select>');
			if(data.className)
				ansTag.addClass(data.className);
			var optionTag=$j('<option value="select">&nbsp;</option>');
			if(data.multiselect)
				ansTag.attr("multiple","multiple");
			ansTag.append(optionTag);
			$j(ans.attribute).each(function(index,attr){
				optionTag = $j('<option  value="'+attr.option+'"/>');
				optionTag.html(attr.option);
				ansTag.append(optionTag);
			}); 
		}	
		if(ans.readonly)
		  ansTag.attr("readonly",ans.readonly);
		if(ans.className)
			ansTag.addClass(ans.className);
        if(ans.style)
			ansTag.attr('style',ans.style);
		pTag.append(ansTag);
		if(ans.correlationRule!=null){
			var divTag=$j('<div></div>');
			var divObj;
			$j(ans.correlationRule).each (function(index,rule){
				$j(rule.data).each(function(index,data){
				 if(data.type=="multipleSelectBox"){
					divObj=self.createMultiSelect(data,mode);
					divObj.addClass('marginstyle');
					divTag.append(divObj);	
				}
				else{
					divObj =self.createQuestionnaire(data);	
					divObj.addClass('marginstyle');
					divTag.append(divObj);	
					}					
				});
				
			});
			pTag.append(divTag);
		}	
	}); 
	div.append(pTag);
	if(index%2==0)
	maindiv.append(div);
	return div;
}

GyneModalProcessor.prototype.createHeaderSection = function(sectionName,headOne,headTwo,headThird,headFour,headFive,headSix,headSeven,mode,caseResult) {
 var babycount=0;
 var fetalcount=0;
	var self=this;
	var parent = $j('<div />');
	if(sectionName=="baby"){
		var mainhead = $j('<div class ="one" />');
		var agesubhead = $j('<div class="oneSixth bold"> &nbsp; </div>');
		mainhead.append(agesubhead);
		var agesubhead = $j('<div class="oneSixth bold"> '+headOne+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="oneSixth bold">'+headTwo+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="oneSixth bold">'+headThird+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="oneSixth bold">'+headFour+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="oneSixth bold">'+headFive+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="oneSixth bold">'+headSix+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="oneSixth bold">'+headSeven+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div style="width:16%;float:left;">&nbsp;</div>');
		mainhead.append(agesubhead);
		parent.append(mainhead);
	  $j(parent).addClass('babyone');
	 	if(mode=="view"){
			$j(caseResult.questionAnswerDTO.answerDTO).each(function(index,answer){
				if(answer.answer!="Deleted"){
					if(answer.questionKey=="babyGender_"+(babycount)){
						var babyDetails=methodInvoker.addBabyRow(babycount,"view");
						parent.append(babyDetails);
						babycount++;
					}	
				}
				
	        });
			if(babycount==0){
				var babyDetails=methodInvoker.addBabyRow(babycount,"view");
				parent.append(babyDetails);
			}
		} 
		else{
			var babyDetails=methodInvoker.addBabyRow(babycount);
			parent.append(babyDetails);
		}
	}else if(sectionName=="foetus"){
		var mainhead = $j('<div class ="one" />');
		var agesubhead = $j('<div class="onefourth bold"> &nbsp; </div>');
		mainhead.append(agesubhead);
		var agesubhead = $j('<div class="onefourth bold"> '+headOne+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="onefourth bold">'+headTwo+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="onefourth bold">'+headThird+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="onefourth bold">'+headFour+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="onefourth bold">'+headFive+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div class="onefourth bold">'+headSix+'</div>');
		mainhead.append(agesubhead);
		agesubhead = $j('<div style="width:16%;float:left;">&nbsp;</div>');
		mainhead.append(agesubhead);
		parent.append(mainhead);
	 $j(parent).addClass('foetusone');
		if(mode=="view"){
			$j(caseResult.questionAnswerDTO.answerDTO).each(function(index,answer){
				if(answer.answer!="Deleted"){
					if(answer.questionKey=="fetalCongAnomali_"+(fetalcount)){
						var fetalDetails=methodInvoker.addFetalRow(fetalcount,"view");
						parent.append(fetalDetails);
						fetalcount++;
					}
				}	
		   });
		    if(fetalcount==0){
				var fetalDetails=methodInvoker.addFetalRow(fetalcount,"view");
				parent.append(fetalDetails);
			}
		}
		else{
			var fetalDetails=methodInvoker.addFetalRow(fetalcount);
			parent.append(fetalDetails);
		}
	}	

	return parent; 
}
GyneModalProcessor.prototype.createMultiSelect = function(data,mode) {
	var mainDiv=$j('<div class="gyne-content"></div>'); 
	var pTag=$j('<p></p>');
	var questionSpan=$j('<span class="questStyle">'+data.question+'&nbsp;</span>');
	pTag.append(questionSpan);
	if(data.required){
	  spanTag = $j('<span class="req_astrisk">*</span>');
	  pTag.append(spanTag);
	  }
	var breakTag=$j('<br>');
	pTag.append(breakTag);
	var spanTag=$j('<span>&nbsp;'+data.firstSelectBoxText+"  "+'</span>');
	var selectTagOne=$j('<select id="'+data.firstSelect+'" name="'+data.firstSelect+'" class="ans"></select>');
	if(data.firstSelectAttributes){
		$j(data.firstSelectAttributes).each(function(index,item) {
			var optionTag = $j('<option value="'+item.option+'"/>');
			optionTag.html(item.option);
			selectTagOne.append(optionTag);
		});
	}
	spanTag.append(selectTagOne);
	var spanTag2=$j('<span>&nbsp;'+data.secondSelectBoxText+"  "+'</span>');
	var selectTagTwo=$j('<select  id="'+data.secondSelect+'" name="'+data.secondSelect+'" class="ans"></select>');
	if(data.secondSelectAttributes){
	$j(data.secondSelectAttributes).each(function(index,item) {
			var optionTag = $j('<option value="'+item.option+'"/>');
			optionTag.html(item.option);
			selectTagTwo.append(optionTag);
		});
	}
	if(data.style)
	 spanTag2.attr("style",data.style);
	if(data.multiselect)
		selectTagTwo.attr("multiple","multiple");
	spanTag2.append(selectTagTwo);
	
		
	if(mode=="view"){
		$j(selectTagOne).attr('disabled','disabled');
		$j(selectTagTwo).attr('disabled','disabled');
	}
	pTag.append(spanTag);
	pTag.append(spanTag2);
	mainDiv.append(pTag);
	return mainDiv;
 }

