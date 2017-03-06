function CaseListUIStartup() {
    this.pgTableName = "#case";
	this.pgTableContainer="#caseListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#casePTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#casePTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#casePTBContainer #btn_delete_ptb_id');
	this.ptbCreateMedicalRecord=$j('#casePTBContainer #btn_createMedicalRecord_ptb_id');
	this.ptblistMedicalRecord=$j('#casePTBContainer #btn_listMedicalrecord_ptb_id');
	this.ptbPatientListbackbtn=$j('#casePTBContainer #btn_back_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.caseIdCol';
	this.exp = new ExpressionListDTO();
	this.caseService = new CaseServiceImpl();
	this.listUrl = constants.CASELISTURL;
	this.caseTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.caseService,this.exp);
	this.ftbToolBar = '#case-filter-toolbar';
	this.filter = '#filter';
	this.patientName;
	this.caseResponse;
	this.filterActionButton = '#btnGo';
	this.ViewCaseListUI = new ViewCaseListUI(this); 
}

CaseListUIStartup.prototype.setCaseTableNavigator = function(caseTableNavigator) {
	this.caseTableNavigator = caseTableNavigator;
}
CaseListUIStartup.prototype.getCaseService = function() {
	return this.caseService;
}
CaseListUIStartup.prototype.getCaseTableNavigator = function() {
	return this.caseTableNavigator;
}
CaseListUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
CaseListUIStartup.prototype.getViewCaseListUI = function() {
	return this.ViewCaseListUI;
} 


CaseListUIStartup.prototype.init = function() {
  var self = this;
	var caseTableNavigator = self.getCaseTableNavigator();
	self.setPageTitle(constants.CASETITLE);	
	var expList = new ExpressionListDTO();
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.CASE,constants.CASELISTPAGETOOLBAR); //Create the Page tool Bar for CaseList/*
	var ftbProcessor = new FilterToolBarProcessor();
	ftbProcessor.create(constants.CASE,constants.CASEFILTERKEY,constants.ENUMLISTURL);
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.CASETABLELIST);//Create Table for Listing Case
	dataTableProcessor.setCustomTableForCase(self.pgTableName);
	caseTableNavigator.setExp(expList);
	//alert(JSON.stringify(expList));
	caseTableNavigator.list();
	self.bindEvents();	
	var caseService = self.getCaseService();
	self.caseInfo = caseService.getCaseList();
	$j("#btn_indexKey-conductedBy-answer-String-2_filter_id").hide();
	
}

 CaseListUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var caseId=self.getSelectedCaseId(self.pgTableName);
		
		var caseService = self.getCaseService();
		self.caseResponse = caseService.viewCase(caseId);
		$j('#' + constants.CASE + '-filter-cont').hide();
		$j(self.filter).hide();
		if(caseId!="") {
		self.patientId = self.caseResponse.patientId;
		self.patientName = self.caseResponse.patientFirstName;
			var ViewCaseListUI = self.getViewCaseListUI();
			ViewCaseListUI.init(self.patientId,self.patientName,caseId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var caseId=self.getSelectedCaseId(self.pgTableName);
		var caseService = self.getCaseService();
		self.caseResponse = caseService.viewCase(caseId);
		if(caseId!="") {
			var confirmStatus = confirm(constants.CASEDELETECONFIRM + self.caseResponse.caseName);
			if(confirmStatus==true) {				
				var response = caseService.deleteCase(caseId);
				if(response.success==true) {
					showTip(constants.CASEDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(response.error));
				}
				var caseTableNavigator = self.getCaseTableNavigator();
				caseTableNavigator.list();
				
			}
		}	
	});
	
	/*Filter Tool Bar Events starts here*/
	$j(self.ftbToolBar + " a:not(:selected)").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		var curObjId = $j(this).attr('id');
		$j(this).attr('selected','selected');
		$j(this).addClass('button_filter');
		if(curObjName.split("-").length>1){
			var selectName = curObjName.split("-")[0] + "-" + curObjName.split("-")[2]+"-" + curObjName.split("-")[4];
			var selectTag = $j("#"+selectName);
			var selectValue=$j("#lst" + selectName).val();
			selectTag=self.selectFilterFillParameters(selectTag,selectValue.split("-")[3]);
			selectTag.show();
			$j("#txt"+selectName).show();
		} else {
			$j('#lst'+curObjName).show();
			$j('#txt'+curObjName).show();
		}
		$j('#txt'+curObjName).focus();
		self.setReportFilterValues(curObjName,self.caseInfo);
		
	});
	
	$j(self.ftbToolBar + " a span").die('click').live('click',function() {
		var curObjName = $j(this).closest("a").attr('name');
		if(curObjName.split("-").length>1){
		var selectName = curObjName.split("-")[0] + "-" + curObjName.split("-")[2]+"-" + curObjName.split("-")[4];
			var selectTag = $j("#"+selectName);
			var selectValue=$j("#lst" + selectName).val();
			selectTag=self.selectFilterFillParameters(selectTag,selectValue.split("-")[3]);
			self.setReportFilterValuesForSelectFilter(selectValue.split("-")[1],$j("#txt"+selectName));
		}
		return false;
		
	});
	
	$j("#lstindexKey-answer-1").die('click').live('click',function() {
		var sel = $j("#lstindexKey-answer-1").val();
		var seleAns = sel.split("-")[3];
		$j("#lstindexKey-answer-2").val(sel);
		
		$j("#lstindexKey-answer-2").attr('disabled','true');
		
		if(seleAns=="Integer" || seleAns=="Float")
			$j("#btn_indexKey-conductedBy-answer-String-2_filter_id").show();
		else
			$j("#btn_indexKey-conductedBy-answer-String-2_filter_id").hide();
	
	});
	$j("#lstindexKey-answer-2").die('click').live('click',function() {
		var sel = $j("#lstindexKey-answer-2").val();
		$j("#lstindexKey-answer-1").val(sel);
		
	});
	
	$j(self.ftbToolBar + " a[selected]").die('click').live('click',function() {
		var curObjName = $j(this).attr('name');
		$j('#txt'+curObjName).datepicker('hide');
		$j(this).removeAttr('selected');
		$j(this).removeClass('button_filter');
		if(curObjName.split("-").length>1){
			var selectName =curObjName.split("-")[0] + "-" + curObjName.split("-")[2]+"-" + curObjName.split("-")[4];
			var selectTag = $j("#" + selectName);
			selectTag.hide();
			$j("#txt" + selectName).hide();
		} else{
			$j('#lst'+curObjName).hide();
			$j('#txt'+curObjName).hide();
		}
		$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	
	$j(self.ftbToolBar + ' ' + self.filterActionButton).die('click').click(function(){
		removeErrors();
		var expList=new ExpressionListDTO();
		$j(self.ftbToolBar + " a[selected]").each(function(){
			var curObjName=$j(this).attr('name');	
			var selTextValue=$j("#txt"+ curObjName).val();
			var selOperator = $j("#lst"+ curObjName).val();	
			if(curObjName=='deliveryMonth'){
				var expr=new ExpressionDTO("antenatalCreatedDate",selTextValue,"like");
				expList.add(expr);
			} else if((curObjName.split("-").length)>1){	
				selectedListValue = $j(this).children("span").children("select").val();
				var selNames = selectedListValue.split("-");
				var fieldName1= selNames[0];
				var fieldValue1= selNames[1];
				var fieldName2= selNames[2];
				var fieldName3= selNames[3];
				var fieldName4 = curObjName.split("-")[4];
				
				
				var fieldValue2= $j("#txt" + fieldName1 + "-"+fieldName2+"-"+fieldName4).val()
				if(fieldName3=="Integer")
				  fieldValue2= parseFloat(fieldValue2);
				if(fieldName3=="Float")
				  fieldValue2= parseFloat(fieldValue2);
			  
				var expr = new ExpressionDTO(fieldName1,fieldValue1,"eq");
				var operator = $j("#" + fieldName1 + "-"+fieldName2+"-"+fieldName4).val();
				
				if(!isNaN(fieldValue2)){
					var expr1 = new ExpressionDTO(fieldName2,fieldValue2,operator,fieldName3);
					expList.add(expr1);
				
				}
				if(fieldName3=="String"){
					var expr3 = new ExpressionDTO(fieldName2,fieldValue2,operator,fieldName3);
					expList.add(expr3);
				
				}
			expList.add(expr);
				
				}			
		else
		{
			var expr = new ExpressionDTO(curObjName,selTextValue,selOperator);
			expList.add(expr);
			}
		});
		var caseTableNavigator = self.getCaseTableNavigator();
		var exprStatus = new ExpressionDTO("status","active","eq");
		expList.add(exprStatus);
		var expr2 = new ExpressionDTO("answer","Unknown","neq");
		if(expList.expressionList.length>1)
			expList.add(expr2);
		caseTableNavigator.setExp(expList);
		caseTableNavigator.list();
		
	});
	
	$j(self.ftbToolBar + ' input[type="text"]').die('keypress').live("keypress",function(e){
			if(e.keyCode==13)
			$j(self.ftbToolBar + ' ' + self.filterActionButton).trigger('click');
	});
	/*Filter Tool Bar Events ends here*/
	
} 

CaseListUIStartup.prototype.setReportFilterValues=function(curObjName,CaseInfo) {
		var self=this;
		if(curObjName=='firstName'){
			autoCompleteArray=[];
			uniqueNames=[];
			 $j(CaseInfo.caseList).each(function(index,cases){  
				autoCompleteArray.push(''+cases.patientFirstName+'');	
			}); 
			
			$j.each(autoCompleteArray, function(i, el){
 				if($.inArray(el, uniqueNames) === -1) uniqueNames.push(el);
				});

			commonMethodInvoker.makeautoComplete("#txt"+curObjName, uniqueNames);
		} 
		if(curObjName=='createdTime'){
			$j("#txt"+curObjName).datepicker().datepicker("show");
						
		} 
		if(curObjName=='antenatalCreatedDate'){
			$j("#txt"+curObjName).datepicker({ dateFormat: 'dd-mm-yy' }).datepicker("show");
			} 
		if(curObjName=='deliveryMonth'){
		 var changingDate = false;
		$j("#txt"+curObjName).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: "mm-yy",

    onClose: function(dateText) {
	        if (dateText != "") {
		
            var selectedDate = $j.datepicker.parseDate("dd-mm-yy", "01-" + dateText);
						$j(this).datepicker("setDate", selectedDate);
						$j(this).datepicker("refresh");
        }
    },
	 beforeShow: function() {
        var dateText = $j(this).val();
        var isPopulated = dateText.length > 0;

        if (isPopulated) {
            var selectedDate = $j.datepicker.parseDate("dd-mm-yy", "01-" + dateText);
			$j(this).datepicker("option", "defaultDate", selectedDate);
			$j(this).datepicker("setDate", selectedDate);
            $j(this).datepicker("refresh");
        }
    } ,
			

    onChangeMonthYear: function(year, month) {
        if (changingDate) {
            return;
        } 

        changingDate = true;
        $j(this).datepicker("setDate", new Date(year, month - 1, 1));
        $j(this).datepicker("refresh");
        changingDate = false;
    } 
});
 $j("#txt"+curObjName).focus(function () {
    $j(".ui-datepicker-calendar").hide();
});

$j("#txt"+curObjName).blur(function () {
   $j(".ui-datepicker-calendar").hide();
	}); 
	   } 
		 
		if(curObjName=='patientId'){
		
		} 
		if(curObjName=='caseNumber'){
		} 
		if(curObjName=='department'){
			autoCompleteArray=[];
			autoCompleteArray.push("General");
			autoCompleteArray.push("Obstetrics");
			commonMethodInvoker.makeautoComplete("#txt"+curObjName, autoCompleteArray);
		} 
		if(curObjName=='status'){
			autoCompleteArray=[];
			autoCompleteArray.push("active");
			autoCompleteArray.push("inactive");
			commonMethodInvoker.makeautoComplete("#txt"+curObjName, autoCompleteArray);
		} 
		if(curObjName=='syncStatus'){
			autoCompleteArray=[];
			autoCompleteArray.push("NOCHANGE");
			autoCompleteArray.push("CHANGED");
			autoCompleteArray.push("INQUEUE");
			commonMethodInvoker.makeautoComplete("#txt"+curObjName, autoCompleteArray);
		} 
		
		

}
CaseListUIStartup.prototype.setReportFilterValuesForSelectFilter=function(curObjName,textBoxId) {
		autoCompleteArray=[];
		 if(curObjName=='bookedUnbooked'){
			autoCompleteArray.push("Booked");
			autoCompleteArray.push("Unbooked");
			autoCompleteArray.push("Referred");			
		} 	
		if(curObjName=='bloodgroup'){
			autoCompleteArray.push("A");
			autoCompleteArray.push("B");
			autoCompleteArray.push("AB");	
			autoCompleteArray.push("O");			
		} 	
		if(curObjName=='typeOfDelivery'){
			autoCompleteArray.push("Cs");
			autoCompleteArray.push("Vaginal");	
		} 	
		if(curObjName=='muscleRelaxants'){
			autoCompleteArray.push("Drotaverin");
			autoCompleteArray.push("Valethamate");	
			autoCompleteArray.push("Hyoscine");
			autoCompleteArray.push("Nil");	
			autoCompleteArray.push("Not Entered");	
		} 	
		
		if(curObjName=='extraOxytoxinUsed'){
			autoCompleteArray.push("IV");
			autoCompleteArray.push("IM");	
		} 	
		
		if(curObjName=='otherOxytoxinUsed'){
			autoCompleteArray.push("Methergin");
			autoCompleteArray.push("Methergin IV");	
			autoCompleteArray.push("Carboprost"); 
			autoCompleteArray.push("Misoprostol");	
			autoCompleteArray.push("Intra Uterine");
			autoCompleteArray.push("Pitocin");
			autoCompleteArray.push("Others");
			autoCompleteArray.push("Nil");	
			autoCompleteArray.push("Not Entered");	
		} 	
		if(curObjName=='multiplePregnancy'){
			autoCompleteArray.push("Twins");
			autoCompleteArray.push("Triplets");	
			autoCompleteArray.push("Others"); 
		} 
		if(curObjName=='episiotomy'){
			autoCompleteArray.push("Midline Episiotomy");
			autoCompleteArray.push("Medio-Lateral Episiotomy");	
			autoCompleteArray.push("Nil");	
			autoCompleteArray.push("Not Entered");	
		} 
		if(curObjName=='induction'){
			autoCompleteArray.push("Hypertensive disorders");
			autoCompleteArray.push("Intra-amniotic infection");	
			autoCompleteArray.push("PROM");	
			autoCompleteArray.push("PPROM");	
			autoCompleteArray.push("Elective induction");	
			autoCompleteArray.push("Postdated pregnancy");	
			autoCompleteArray.push("Others");
			autoCompleteArray.push("Not Entered");				
		} 
		
		if(curObjName=='deliveryType'){
			autoCompleteArray.push("Spontaneous");
			autoCompleteArray.push("Induced");	
			autoCompleteArray.push("Elective");	
			autoCompleteArray.push("Emergency");	
			autoCompleteArray.push("Not Entered");			
		} 
		
		if(curObjName=='deliveryName'){
			autoCompleteArray.push("Normal");
			autoCompleteArray.push("Forcepsdelivery");	
			autoCompleteArray.push("Vacuumextraction");	
			autoCompleteArray.push("Breech");
			autoCompleteArray.push("Caesarean");
			autoCompleteArray.push("Sequential");
			autoCompleteArray.push("VBAC");
			autoCompleteArray.push("Others");
			autoCompleteArray.push("Not Entered");			
		} 
		
		if(curObjName=='robsonclass'){
			autoCompleteArray.push("Group 1");
			autoCompleteArray.push("Group 2e ( elective )");	
			autoCompleteArray.push("Group 2i ( indicated )");	
			autoCompleteArray.push("Group 3");	
			autoCompleteArray.push("Group 4e ( elective )");	
			autoCompleteArray.push("Group 4i ( indicated )");	
			autoCompleteArray.push("Group 5");
			autoCompleteArray.push("Group 6");		
			autoCompleteArray.push("Group 7");		
			autoCompleteArray.push("Group 8");		
			autoCompleteArray.push("Group 9");		
			autoCompleteArray.push("Group 10");
			autoCompleteArray.push("Not Entered");				
		} 
		if(curObjName=='babyPresentation_0'||curObjName=='babyPresentation_1'||curObjName=='babyPresentation_2'){
			autoCompleteArray.push("Cephalic");
			autoCompleteArray.push("Breech");		
			autoCompleteArray.push("Shoulder");		
			autoCompleteArray.push("Face");		
			autoCompleteArray.push("Transverse");
			autoCompleteArray.push("Others");
			autoCompleteArray.push("Not Entered");				
		} 
		 if(curObjName=='doctorIncharge' ||curObjName=='conductedBy'){
			fillDoctorAsAutoComplete("txtindexKey-answer");	
		} 
		
		if(curObjName=='help' ||curObjName=='eclampsia'||curObjName=='perinealTear'||curObjName=='oxytoxinBolus'||
		curObjName=='bloodProduct' || curObjName=='isfourthStageMon'||curObjName=='maternalDeath'||curObjName=='AntiBioticsUsed'||
		curObjName=='fetalstillbirth_0'||curObjName=='fetalstillbirth_1'||curObjName=='fetalstillbirth_2'||curObjName=='fetalNICU_0'||	
		curObjName=='fetalNICU_1'||curObjName=='fetalNICU_2'){
			autoCompleteArray.push("Yes");
			autoCompleteArray.push("No");	
			autoCompleteArray.push("Not Entered"); 
		} 			
		
	commonMethodInvoker.makeautoComplete(textBoxId, autoCompleteArray);	
	textBoxId.val("");
}
 CaseListUIStartup.prototype.getSelectedCaseId = function () {
	var caseId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selCase = $j(this.pgTableName + ' tbody tr[selected]');
		if(selCase.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONECASE);
		} else if(selCase.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONECASEONLY);
		else
			caseId=selCase.attr('id');
	}
	return caseId;
} 
CaseListUIStartup.prototype.selectFilterFillParameters=function(selectTag, type){
	selectTag.empty();
	var option = $j('<option value="eq">eq</option>');
	selectTag.append(option);
	var option = $j('<option value="neq">neq</option>');
	selectTag.append(option);
	if(type=='Integer' || type=='Float'){
		var option = $j('<option value="gt">gt</option>');
		selectTag.append(option);
		var option = $j('<option value="lt">lt</option>');
		selectTag.append(option);
		var option = $j('<option value="ge">ge</option>');
		selectTag.append(option);
		var option = $j('<option value="le">le</option>');
		selectTag.append(option);
	}else if(type=='String'){
		var option = $j('<option value="like">like</option>');
		selectTag.append(option);
	}
	return selectTag;
}

 CaseListUIStartup.prototype.bindEvents = function() {
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
	   var caseId= $j(this).parent().attr('id');
	   $j('#' + constants.CASE + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(caseId!="") {
		    var caseService = self.getCaseService();
		    var caseResponse = caseService.viewCase(caseId);
			self.patientId = caseResponse.patientId;
			self.patientName = caseResponse.patientFirstName;
		
			$j('#case-filter-wb').hide();
			var ViewCaseListUI = self.getViewCaseListUI();
			ViewCaseListUI.init(self.patientId,self.patientName,caseId);
		}	
	});
}  