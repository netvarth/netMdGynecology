var doctorExpFilteredData=[];
var doctorQualiFilteredData=[];
var doctorAchmntFilteredData=[];
var doctorExprFilteredData=[];
var doctorMemFilteredData=[];
var doctorInfo="";
$j(document).ready(function(){
//back button click action
$j("#referralGeneralPTBContainer #btn_back_ptb_id").die('click').live('click',function() {	
	$j.cachedScript(constant_ReferralEntry_Url).done(function(script, textStatus) {
	})
	
});

//up button click action
$j("#referralGeneralPTBContainer #btn_up_ptb_id").die('click').live('click',function() {
	removeErrors();
	var curdoctorId = getDoctorId();
	var doctorIdforView = getpreviousDoctorId(curdoctorId,pgDoctorList);
	viewdoctorInfomation(doctorIdforView,'#referrals'); 
	doctorInfo = getDoctorData(doctorIdforView);
	$j('#pageTitle').html("View Doctor"+'  '+'( '+doctorInfo.firstName.toUpperCase()+' )');
});

//down button click action
$j("#referralGeneralPTBContainer #btn_down_ptb_id").die('click').live('click',function() {
	removeErrors();
	var curdoctorId = getDoctorId();
	var doctorIdforView = getnextDoctorId(curdoctorId,pgDoctorList);
	viewdoctorInfomation(doctorIdforView,'#referrals');	
	doctorInfo = getDoctorData(doctorIdforView);
	$j('#pageTitle').html("View Doctor"+'  '+'( '+doctorInfo.firstName.toUpperCase()+' )');
	
}); 

$j('#doctorViewBtnEditOne').die('click').live('click',function(){
   var doctorId = getDoctorId();
    doctorInfo = getDoctorData(doctorId);
	$j('#pageTitle').html("Edit Doctor Details"+'  '+'( '+doctorInfo.firstName.toUpperCase()+' )');
	
	$j("#doctorViewForm #dateofbirth").datepicker({
	changeMonth: true,
	changeYear: true,
	yearRange: '-100:+0'
	});
	
	removeErrors();
	$j('#referralGeneralPTBContainer #btn_up_ptb_id,#referralGeneralPTBContainer #btn_down_ptb_id,#referralGeneralPTBContainer #btn_back_ptb_id').hide();
	$j('#doctorViewForm input[type=text] ,#doctorViewForm textarea').removeClass('newBox'); 
	$j('#doctorViewForm input[type=text],#doctorViewForm textarea').removeAttr('readonly');
	clearNilFields('doctorViewForm');
	$j('#doctorViewForm #doctorViewBtnEditOne').hide();
	$j('#doctorViewForm #doctorViewBtnSave').show();
	$j('#doctorViewForm #doctorViewBtnCancel').show();
//Edit Gender Section
	$j('#doctorViewForm #viewGender').hide();
	$j('#doctorViewForm #lblDoctorEditGender').show();
	if(doctorInfo.gender=="Male")
	$j("#doctorViewForm #lblViewDoctorGenderMale").attr('checked','true');
	else
	$j("#doctorViewForm #lblViewDoctorGenderFemale").attr('checked','true');
	('#doctorViewForm #lblDoctorEditGender').hide();
});

$j('#doctorViewBtnCancel').die('click').live('click',function(){
	removeErrors();
	$j("#doctorViewForm #dateofbirth").datepicker( 'disable' ); 
	$j('#btn_up_ptb_id,#btn_down_ptb_id,#btn_back_ptb_id').show();
	viewdoctorInfo(doctorInfo);
	restoredoctorInfo();
	clearNilFields('doctorViewForm');
	$j('#doctorViewForm #doctorViewBtnSave').hide();
	$j('#doctorViewForm #doctorViewBtnCancel').hide();
	$j('#doctorViewForm #doctorViewBtnEditOne').show();
	var doctorId = getDoctorId();
    doctorInfo = getDoctorData(doctorId);
	$j('#pageTitle').html("View Doctor"+'  '+'( '+doctorInfo.firstName.toUpperCase()+' )');
	
	return false;
});

$j('#doctorViewBtnSave').die('click').live('click',function(){
	    removeErrors();
		if(validateViewDoctor()) {
		var response = submitdoctorInfo();
		if(response.success==true){
			doctorId = getDoctorId();
			doctorInfo = getDoctorData(doctorId);
			viewdoctorInfo(doctorInfo);
			restoredoctorInfo();
			clearNilFields('doctorViewForm');
			fillDoctorListForAdmin("#docSelect");
			showTip("Personal Information updated successfully");
		} 
		else{
		 if(response.error.errCode=="078")
				showTip("This is not a Primary device.");
			}  
     }
	 $j("#doctorViewForm #dateofbirth").datepicker( 'disable' ); 
	 var doctorId = getDoctorId();
    doctorInfo = getDoctorData(doctorId);
	$j('#pageTitle').html("View Doctor"+'  '+'( '+doctorInfo.firstName.toUpperCase()+' )');
	
});

// Operations for experience table in Doctor View
$j('#doctorViewForm #doctorExpBtnEdit').die('click').live('click',function(){ 
		removeErrors();
		$j('#doctorViewForm #workBenchOne').show();
		$j('#doctorViewForm #doctorExpBtnEdit').hide();
		$j('#doctorViewForm #doctorExpBtnSave').show();
		$j('#doctorViewForm #doctorExpBtnCancel').show();
		$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 4, true );
		$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 5, true );
	});
	
	
$j('#doctorViewForm #doctorExpBtnSave').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchOne').hide();
		$j('#doctorViewForm #doctorExpBtnEdit').show();
		$j('#doctorViewForm #doctorExpBtnSave').hide();
		$j('#doctorViewForm #doctorExpBtnCancel').hide();
	    $j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 4, false );
		$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 5, false );
		var response = submitExpInfo();
		if(response.success==true){
		doctorExpFilteredData=[];
		showTip("Doctor Experience Updated Successfully");
         }
		 else
		 updateTipsNew(getErrorName(response.error),$j('#doctorViewForm #errorDivViewdoctorData'),$j('#doctorViewForm #errorDivHeader'));
	});
	
$j('#doctorViewForm #doctorExpBtnCancel').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchOne').hide();
		$j('#doctorViewForm #doctorExpBtnEdit').show();
		$j('#doctorViewForm #doctorExpBtnSave').hide();
		$j('#doctorViewForm #doctorExpBtnCancel').hide();
		$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 4, false );
		$j('#doctorViewForm #doctorExpTable').dataTable().fnSetColumnVis( 5, false );
	});	

$j('#doctorViewForm #docExpBtn').die('click').live('click',function(){
	    removeErrors();
		if(validateViewDoctorExp()) {
		var expId='0';
		var desig =$j('#doctorViewForm #designations').val();
		var worksat =$j('#doctorViewForm #worksAt').val();
		var fromdate =$j('#doctorViewForm #fromDate').val();
		var todate =$j('#doctorViewForm #toDate').val();
		var mainString=desig +'_' + worksat +'_' + fromdate +'_' + todate;
        var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';	 
		var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	    var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
		$j('#doctorViewForm #doctorExpTable').dataTable().fnAddData([desig,worksat,fromdate,todate,myData,EditBT]);		
	    $j("#doctorViewForm #workBenchOne input[type=text], textarea").val("");
		$j("#doctorViewForm #designations").focus();
		doctorExpFilteredData=resultJsonForExperience(expId,"Add",doctorInfo,doctorExpFilteredData,mainString); 
	}
	
	//else
		//createError(constants_ExpWorkbench_errorMsg,$j('#doctorViewForm #designations'));
		
	});

	
$j('#doctorViewForm #doctorExpTable .delete').die('click').live('click',function(){
	removeErrors();
	var expId=$j(this).closest('tr').attr('id');
	var action="Delete";
	doctorExpFilteredData=resultJsonForExperience(expId,action,doctorInfo,doctorExpFilteredData); 
	});

$j('#doctorViewForm #doctorExpTable #edit').die('click').live('click',function(){
		removeErrors();
		var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',true);
		 if($j(this).hasClass('datepicker')){
			$j(this).datepicker(); 
		  } 
   });
});

$j('#doctorViewForm #doctorExpTable #save').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',false);
   });
   var expId=$j(this).closest('tr').attr('id');
    var desig=row.closest('tr').children('td:nth-child(1)').text();
	var wrk_at=row.closest('tr').children('td:nth-child(2)').text();
	var fromDate=row.closest('tr').children('td:nth-child(3)').text();
	var toDate=row.closest('tr').children('td:nth-child(4)').text();
	var action="Update";
	var mainString=desig +'_' + wrk_at +'_' + fromDate +'_' + toDate;
	doctorExpFilteredData=resultJsonForExperience(expId,action,doctorInfo,doctorExpFilteredData,mainString); 
}); 
   



$j("#doctorViewForm #doctorExpTable .img-swap").die('click').live('click', function() {
	var source = $j(this);
	if(source.attr('name')=="edit") {
		this.src = this.src.replace("_on","_off");
		$j(this).attr("name","save");
		$j(this).attr("title","save");
		$j(this).attr("id","save");
	}
	else {
		this.src = this.src.replace("_off","_on");
		$j(this).attr("name","edit");
		$j(this).attr("title","edit");
		$j(this).attr("id","edit");
	}	
	return false;
});

function resultJsonForExperience(expId,actionName,doctorInfo,doctorExpFilteredData,mainString){
	var status = checkExpExistsInJson(doctorInfo,expId);
    var row="#"+expId;
	var rowId=$j(row);
	var action=actionName;
	var Doc_id= getDoctorId();
	if(expId!='0')
		doctorExpFilteredData = filterExpression_expUid(doctorExpFilteredData,expId);
	if(actionName=='Delete'){
		var desig=rowId.closest('tr').children('td:nth-child(1)').text();
		var wrk_at=rowId.closest('tr').children('td:nth-child(2)').text();
		var fromDate=rowId.closest('tr').children('td:nth-child(3)').text();
		var toDate=rowId.closest('tr').children('td:nth-child(4)').text();
			if(status==true) 
				doctorExpFilteredData.push('{' + '"id"' + ':'+ expId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"designation":"'+ desig +'",' + '"workedAt"' + ':"' + wrk_at +'"' + ',' + '"fromDate"' + ':"' +fromDate+'"' + ','+'"toDate"' + ':"'+toDate+'"' + ','  + '"actionName"' + ':"'+'' + "Delete" + '"'+'}');
			var currentRow= rowId.closest('tr');
			$j('#doctorViewForm #doctorExpTable').dataTable().fnDeleteRow(currentRow[0]);
	}else {
			var desig=mainString.split('_')[0];
			var wrk_at=mainString.split('_')[1];
			var fromDate=mainString.split('_')[2];
			var toDate=mainString.split('_')[3];
		if(status==true){
		if(expId=='0')
			doctorExpFilteredData.push('{' + '"id"' + ':'+ expId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"designation":"'+ desig +'",' + '"workedAt"' + ':"' + wrk_at +'"' + ',' + '"fromDate"' + ':"' +fromDate+'"' + ','+'"toDate"' + ':"'+toDate+'"' + ','  + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
		else
			doctorExpFilteredData.push('{' + '"id"' + ':'+ expId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"designation":"'+ desig +'",' + '"workedAt"' + ':"' + wrk_at +'"' + ',' + '"fromDate"' + ':"' +fromDate+'"' + ','+'"toDate"' + ':"'+toDate+'"' + ','  + '"actionName"' + ':"'+'' + "Update" + '"'+'}');
		
	}
	else
	doctorExpFilteredData.push('{' + '"id"' + ':'+ expId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"designation":"'+ desig +'",' + '"workedAt"' + ':"' + wrk_at +'"' + ',' + '"fromDate"' + ':"' +fromDate+'"' + ','+'"toDate"' + ':"'+toDate+'"' + ','  + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
	
	
}
return doctorExpFilteredData;
}

function checkExpExistsInJson(resultJson,expId) {
	var status = false;
	$j(resultJson.doctorExperience).each(function (index,exp) {
		if(exp.id == expId){
			status=true;
			return false;
		}	  
	});
	return status;
}

function filterExpression_expUid(expr,id) {
	var result=[];
	var tempexp = '[' + expr + ']';
	tempexp = $j.parseJSON(tempexp);
	$j(tempexp).each(function(index,data){
	if(id!='0'){
		if(data.id==id)
			tempexp.splice(index,1);
		else
			result.push(JSON.stringify(data));
		}
		else
			result.push(JSON.stringify(data));
	});
	return result;
}

// Operations for Qualification table in Doctor View

$j('#doctorViewForm #doctorQualiBtnEdit').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchTwo').show();
		$j('#doctorViewForm #doctorQualiBtnEdit').hide();
		$j('#doctorViewForm #doctorQualiBtnSave').show();
		$j('#doctorViewForm #doctorQualiBtnCancel').show();
		$j('#doctorViewForm #doctorViewForm #QualiDelete').show();
		$j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 3, true );
        $j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 4, true );
	});
	
	
$j('#doctorViewForm #doctorQualiBtnSave').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchTwo').hide();
		$j('#doctorViewForm #doctorQualiBtnEdit').show();
		$j('#doctorViewForm #doctorQualiBtnSave').hide();
		$j('#doctorViewForm #doctorQualiBtnCancel').hide();
		$j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 3, false );
		 $j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 4, false );
		var response = submitQualificationInfo();
		if(response.success==true) {
		var doctorQualiFilteredData=[];
		fillDoctorListForAdmin("#docSelect");
		showTip("Doctor Qualification Updated Successfully");
	    	}
			else
			updateTipsNew(getErrorName(response.error),$j('#doctorViewForm #errorDivViewdoctorData'),$j('#doctorViewForm #errorDivHeader'));

	});
	
$j('#doctorViewForm #doctorQualiBtnCancel').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchTwo').hide();
		$j('#doctorViewForm #doctorQualiBtnEdit').show();
		$j('#doctorViewForm #doctorQualiBtnSave').hide();
		$j('#doctorViewForm #doctorQualiBtnCancel').hide();
		$j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 3, false );
		$j('#doctorViewForm #doctorQualiTable').dataTable().fnSetColumnVis( 4, false );
	});		

	
	
$j('#doctorViewForm #docQualiBtn').die('click').live('click',function(){
        removeErrors();
		if(validateViewDoctorQual()) {
	  	var qualiId='0';
		var Degree =$j('#doctorViewForm #degrees').val();
		var PassoutDate =$j('#doctorViewForm #passoutDates').val();
		var Institution =$j('#doctorViewForm #institutions').val();
		var mainString_One = Degree +'_' + PassoutDate +'_' + Institution;	
        var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	    var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	    var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
	   $j('#doctorViewForm #doctorQualiTable').dataTable().fnAddData([Degree,PassoutDate,Institution,myData,EditBT]);  
	    $j("#doctorViewForm #workBenchTwo input[type=text], textarea").val("");
		$j("#doctorViewForm #degrees").focus();
		doctorQualiFilteredData=resultJsonForQualification(qualiId,"Add",doctorInfo,doctorQualiFilteredData,mainString_One); 	
		}
	//else
	//createError(constants_ExpWorkbench_errorMsg,$j('#doctorViewForm #degrees'));
	});	
	
		
$j('#doctorViewForm #doctorQualiTable .delete').die('click').live('click',function(){
	removeErrors();
	var qualiId=$j(this).closest('tr').attr('id');
	var action="Delete";
	doctorQualiFilteredData=resultJsonForQualification(qualiId,action,doctorInfo,doctorQualiFilteredData); 
});

$j('#doctorViewForm #doctorQualiTable #edit').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',true);
   });
});

$j('#doctorViewForm #doctorQualiTable #save').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',false);
   });
	var qualiId=$j(this).closest('tr').attr('id');
    var Degree=row.closest('tr').children('td:nth-child(1)').text();
	var PassoutDate=row.closest('tr').children('td:nth-child(2)').text();
	var Institution=row.closest('tr').children('td:nth-child(3)').text();
	var action="Update";
	var mainString_One = Degree +'_' + PassoutDate +'_' + Institution;	
	doctorQualiFilteredData=resultJsonForQualification(qualiId,action,doctorInfo,doctorQualiFilteredData,mainString_One); 
}); 
   



$j("#doctorViewForm #doctorQualiTable .img-swap").die('click').live('click', function() {
	var source = $j(this);
	if(source.attr('name')=="edit") {
		this.src = this.src.replace("_on","_off");
		$j(this).attr("name","save");
		$j(this).attr("title","save");
		$j(this).attr("id","save");
	}
	else {
		this.src = this.src.replace("_off","_on");
		$j(this).attr("name","edit");
		$j(this).attr("title","edit");
		$j(this).attr("id","edit");
	}	
	return false;
});

function resultJsonForQualification(qualiId,actionName,doctorInfo,doctorQualiFilteredData,mainString_One){
	var status = checkQualExistsInJson(doctorInfo,qualiId);
    var row="#"+qualiId;
	var rowId=$j(row);
	var action=actionName;
	var Doc_id= getDoctorId();
	if(qualiId!='0')
		doctorQualiFilteredData = filterExpression_qualUid(doctorQualiFilteredData,qualiId);
	if(actionName=='Delete'){
			var degree=rowId.closest('tr').children('td:nth-child(1)').text();
			var passOutDate=rowId.closest('tr').children('td:nth-child(2)').text();
			var institute=rowId.closest('tr').children('td:nth-child(3)').text();
			doctorQualiFilteredData.push('{' + '"id"' + ':'+ qualiId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"educationalDegree":"'+ degree +'",' + '"passedOutDate"' + ':"' + passOutDate +'"' + ',' + '"institution"' + ':"' +institute+'"' + ',' + '"actionName"' + ':"'+'' + "Delete" + '"'+'}');
			var currentRow= rowId.closest('tr');
			$j('#doctorViewForm #doctorQualiTable').dataTable().fnDeleteRow(currentRow[0]);
	}else {
			var degree=mainString_One.split('_')[0];
			var passOutDate=mainString_One.split('_')[1];
			var institute=mainString_One.split('_')[2];
		if(status==true){
		if(qualiId=='0')
			doctorQualiFilteredData.push('{' + '"id"' + ':'+ qualiId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"educationalDegree":"'+ degree +'",' + '"passedOutDate"' + ':"' + passOutDate +'"' + ',' + '"institution"' + ':"' +institute+'"' + ',' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
		else
			doctorQualiFilteredData.push('{' + '"id"' + ':'+ qualiId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"educationalDegree":"'+ degree +'",' + '"passedOutDate"' + ':"' + passOutDate +'"' + ',' + '"institution"' + ':"' +institute+'"' + ',' + '"actionName"' + ':"'+'' + "Update" + '"'+'}');
		
	}
	else
	doctorQualiFilteredData.push('{' + '"id"' + ':'+ qualiId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"educationalDegree":"'+ degree +'",' + '"passedOutDate"' + ':"' + passOutDate +'"' + ',' + '"institution"' + ':"' +institute+'"' + ',' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
	
	
}
return doctorQualiFilteredData;
}

function checkQualExistsInJson(resultJson,qualiId) {
	var status = false;
	$j(resultJson.doctorQualifications).each(function (index,qal) {
		if(qal.id == qualiId){
			status=true;
			return false;
		}	  
	});
	return status;
}

function filterExpression_qualUid(qual,id) {
	var result=[];
	var tempqual = '[' + qual + ']';
	tempqual = $j.parseJSON(tempqual);
	$j(tempqual).each(function(index,data){
	if(id!='0'){
		if(data.id==id)
			tempqual.splice(index,1);
		else
			result.push(JSON.stringify(data));
	    }
		else
			result.push(JSON.stringify(data));
	});
	return result;
}

// Operations for Achievement Table
$j('#doctorViewForm #doctorAchievementsBtnEdit').die('click').live('click',function(){ 
		removeErrors();
		$j('#doctorViewForm #workBenchAchievement').show();
		$j('#doctorViewForm #achievementArea input[type=text] ,#doctorViewForm #achievementArea textarea').removeClass('newBox'); 
	    $j('#doctorViewForm #achievementArea  input[type=text],#doctorViewForm #achievementArea textarea').removeAttr('readonly');
		$j('#doctorViewForm #doctorAchievementsBtnEdit').hide();
		$j('#doctorViewForm #doctorAchievementsBtnSave').show();
		$j('#doctorViewForm #doctorAchievementsBtnCancel').show();
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 1, true );
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 2, true );
		

	});
	
	
$j('#doctorViewForm #doctorAchievementsBtnSave').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchAchievement').hide();
		$j('#doctorViewForm #doctorAchievementsBtnEdit').show();
		$j('#doctorViewForm #doctorAchievementsBtnSave').hide();
		$j('#doctorViewForm #doctorAchievementsBtnCancel').hide();
	    $j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 2, false );
		var response = submitAchievementInfo();
		if(response.success==true){
		var doctorAchmntFilteredData=[];
		showTip("Doctor Achievement Updated Successfully");
          }
		  else
		  updateTipsNew(getErrorName(response.error),$j('#doctorViewForm #errorDivViewdoctorData'),$j('#doctorViewForm #errorDivHeader'));
	});
	
$j('#doctorViewForm #doctorAchievementsBtnCancel').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchAchievement').hide();
		$j('#doctorViewForm #doctorAchievementsBtnEdit').show();
		$j('#doctorViewForm #doctorAchievementsBtnSave').hide();
		$j('#doctorViewForm #doctorAchievementsBtnCancel').hide();
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 2, false );
	});	

	
$j('#doctorViewForm #doctorAchievementsBtnAdd').die('click').live('click',function(){
		removeErrors();
		var achmnt =nl2br($j('#doctorViewForm #achievements').val());
		var achId='0';
        var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
		var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
		var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
		if(achmnt!=""){
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnAddData([achmnt,myData,EditBT]);  
	    $j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnSetColumnVis( 2, false );
		$j("#doctorViewForm #achievements input[type=text], textarea").val("");
		doctorAchmntFilteredData=resultJsonForAchievement(achId,"Add",doctorInfo,doctorAchmntFilteredData,achmnt); 
	}
	else
		createError(constants_ExpWorkbench_Msg,$j('#doctorViewForm #achievements'));
	});	
	
		
$j('#doctorViewForm #doctorAchievementTable .delete').die('click').live('click',function(){
		removeErrors();
		var achId=$j(this).closest('tr').attr('id');
		var action="Delete";
		doctorAchmntFilteredData=resultJsonForAchievement(achId,action,doctorInfo,doctorAchmntFilteredData); 
	});

$j('#doctorViewForm #doctorAchievementTable #edit').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',true);
   });
});

$j('#doctorViewForm #doctorAchievementTable #save').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',false);
   });
  var achId=$j(this).closest('tr').attr('id');
  var achmnt=row.closest('tr').children('td:nth-child(1)').text();
   action="Update";
	doctorAchmntFilteredData=resultJsonForAchievement(achId,action,doctorInfo,doctorAchmntFilteredData,achmnt); 
}); 
  
$j("#doctorViewForm #doctorAchievementTable .img-swap").die('click').live('click', function() {
	var source = $j(this);
	if(source.attr('name')=="edit") {
		this.src = this.src.replace("_on","_off");
		$j(this).attr("name","save");
		$j(this).attr("title","save");
		$j(this).attr("id","save");
	}
	else {
		this.src = this.src.replace("_off","_on");
		$j(this).attr("name","edit");
		$j(this).attr("title","edit");
		$j(this).attr("id","edit");
	}	
	return false;
});
	
	
function resultJsonForAchievement(achId,actionName,doctorInfo,doctorAchmntFilteredData,achmnt){
	var status = checkAchExistsInJson(doctorInfo,achId);
    var row="#"+achId;
	var rowId=$j(row);
	var action=actionName;
	var Doc_id= getDoctorId();
	doctorAchmntFilteredData = filterExpression_achUid(doctorAchmntFilteredData,achId);
	if(actionName=='Delete'){
	  var achmnt=rowId.closest('tr').children('td:nth-child(1)').text();
			doctorAchmntFilteredData.push('{' + '"id"' + ':'+ achId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"achievement":"'+ achmnt +'",' + '"actionName"' + ':"'+'' + "Delete" + '"'+'}');
		var currentRow= rowId.closest('tr');
		$j('#doctorViewForm #doctorAchievementTable').dataTable().fnDeleteRow(currentRow[0]);
	}else {
		if(status==true){
		if(achId=='0')
			doctorAchmntFilteredData.push('{' + '"id"' + ':'+ achId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"achievement":"'+ achmnt +'",' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
	  else 
			doctorAchmntFilteredData.push('{' + '"id"' + ':'+ achId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"achievement":"'+ achmnt +'",' + '"actionName"' + ':"'+'' + "Update" + '"'+'}');
	 }
	  else  
			doctorAchmntFilteredData.push('{' + '"id"' + ':'+ achId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"achievement":"'+ achmnt +'",' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
}	  
	//alert(doctorAchmntFilteredData);
	return doctorAchmntFilteredData;
}

function checkAchExistsInJson(resultJson,achId) {
	var status = false;
	$j(resultJson.achievement).each(function (index,ach) {
		if(ach.id == achId){
			status=true;
			return false;
		}	  
	});
	return status;
}

function filterExpression_achUid(ach,id) {
	var result=[];
	var tempach = '[' + ach + ']';
	tempach = $j.parseJSON(tempach);
	$j(tempach).each(function(index,data){
	if(id!='0'){
		if(data.id==id)
			tempach.splice(index,1);
		else
			result.push(JSON.stringify(data));
	}
	else
			result.push(JSON.stringify(data));
	});
	return result;
}

	

	
// Operations for Expertise Table
$j('#doctorViewForm #doctorExpertiseBtnEdit').die('click').live('click',function(){ 
		removeErrors();
		$j('#doctorViewForm #workBenchExpertise').show();
		$j('#doctorViewForm #ExpertiseArea input[type=text] ,#doctorViewForm #ExpertiseArea textarea').removeClass('newBox'); 
	    $j('#doctorViewForm #ExpertiseArea input[type=text],#doctorViewForm #ExpertiseArea textarea').removeAttr('readonly');
		$j('#doctorViewForm #doctorExpertiseBtnEdit').hide();
		$j('#doctorViewForm #doctorExpertiseBtnSave').show();
		$j('#doctorViewForm #doctorExpertiseBtnCancel').show();
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 1, true );
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 2, true );
		

	});
	
	
$j('#doctorViewForm #doctorExpertiseBtnSave').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchExpertise').hide();
		$j('#doctorViewForm #doctorExpertiseBtnEdit').show();
		$j('#doctorViewForm #doctorExpertiseBtnSave').hide();
		$j('#doctorViewForm #doctorExpertiseBtnCancel').hide();
	    $j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 2, false );
		var response = submitExpertiseInfo();
		if(response.success==true){
		var doctorExprFilteredData=[];
		showTip("Doctor Expertise Updated Successfully");
           }
		   else
		   updateTipsNew(getErrorName(response.error),$j('#doctorViewForm #errorDivViewdoctorData'),$j('#doctorViewForm #errorDivHeader'));
	});
	
$j('#doctorViewForm #doctorExpertiseBtnCancel').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchExpertise').hide();
		$j('#doctorViewForm #doctorExpertiseBtnEdit').show();
		$j('#doctorViewForm #doctorExpertiseBtnSave').hide();
		$j('#doctorViewForm #doctorExpertiseBtnCancel').hide();
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 2, false );
	});	

	
$j('#doctorViewForm #doctorExpertiseBtnAdd').die('click').live('click',function(){
		removeErrors();
		var expertise =nl2br($j('#doctorViewForm #Expertise').val());
		var exprId='0';
        var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	   var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
	   var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
		if(expertise!=""){
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnAddData([expertise,myData,EditBT]);  
	    $j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnSetColumnVis( 2, false );
		$j("#doctorViewForm #Expertise input[type=text], textarea").val("");
		doctorExprFilteredData=resultJsonForExpertise(exprId,"Add",doctorInfo,doctorExprFilteredData,expertise); 
	}
	else
		createError(constants_ExpWorkbench_Msg,$j('#doctorViewForm #Expertise'));
	});	
	
$j('#doctorViewForm #doctorExpertiseTable .delete').die('click').live('click',function(){
	removeErrors();
	var exprId=$j(this).closest('tr').attr('id');
	var action="Delete";
	doctorExprFilteredData=resultJsonForExpertise(exprId,action,doctorInfo,doctorExprFilteredData); 
	});	

$j('#doctorViewForm #doctorExpertiseTable #edit').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',true);
   });
});

$j('#doctorViewForm #doctorExpertiseTable #save').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',false);
   });
  var achId=$j(this).closest('tr').attr('id');
  var expertise=row.closest('tr').children('td:nth-child(1)').text();
   action="Update";
	doctorExprFilteredData=resultJsonForExpertise(achId,action,doctorInfo,doctorExprFilteredData,expertise); 
}); 
  
$j("#doctorViewForm #doctorExpertiseTable .img-swap").die('click').live('click', function() {
	var source = $j(this);
	if(source.attr('name')=="edit") {
		this.src = this.src.replace("_on","_off");
		$j(this).attr("name","save");
		$j(this).attr("title","save");
		$j(this).attr("id","save");
	}
	else {
		this.src = this.src.replace("_off","_on");
		$j(this).attr("name","edit");
		$j(this).attr("title","edit");
		$j(this).attr("id","edit");
	}	
	return false;
});	
	
function resultJsonForExpertise(exprId,actionName,doctorInfo,doctorExprFilteredData,expertise){
	var status = checkExpertiseExistsInJson(doctorInfo,exprId);
    var row="#"+exprId;
	var rowId=$j(row);
	var action=actionName;
	var Doc_id= getDoctorId();
	doctorExprFilteredData = filterExpression_ExpUid(doctorExprFilteredData,exprId);
	if(actionName=='Delete'){
	var expertise=rowId.closest('tr').children('td:nth-child(1)').text();
		//if(status==true) 
			doctorExprFilteredData.push('{' + '"id"' + ':'+ exprId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"expertise":"'+ expertise +'",' + '"actionName"' + ':"'+'' + "Delete" + '"'+'}');
		var currentRow= rowId.closest('tr');
		$j('#doctorViewForm #doctorExpertiseTable').dataTable().fnDeleteRow(currentRow[0]);
	}else {
		if(status==true){
		if(exprId=='0')
			doctorExprFilteredData.push('{' + '"id"' + ':'+ exprId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"expertise":"'+ expertise +'",' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');	
		else 
		doctorExprFilteredData.push('{' + '"id"' + ':'+ exprId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"expertise":"'+ expertise +'",' + '"actionName"' + ':"'+'' + "Update" + '"'+'}');
	}
	else
	doctorExprFilteredData.push('{' + '"id"' + ':'+ exprId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"expertise":"'+ expertise +'",' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');
	}
	return doctorExprFilteredData;
}

function checkExpertiseExistsInJson(resultJson,exprId) {
	var status = false;
	$j(resultJson.expertise).each(function (index,exp) {
		if(exp.id == exprId){
			status=true;
			return false;
		}	  
	});
	return status;
}

function filterExpression_ExpUid(expr,id) {
	var result=[];
	var tempExp = '[' + expr + ']';
	tempExp = $j.parseJSON(tempExp);
	$j(tempExp).each(function(index,data){
		if(id!='0'){
		if(data.id==id)
			tempExp.splice(index,1);
		else
			result.push(JSON.stringify(data));
		}
			else
			result.push(JSON.stringify(data));
	});
	return result;
}
	
// Operations for Memberships Table
$j('#doctorViewForm #doctorMembershipBtnEdit').die('click').live('click',function(){ 
		removeErrors();
		$j('#doctorViewForm #workBenchMemberShip').show();
		$j('#doctorViewForm #MembershipArea input[type=text] ,#doctorViewForm #MembershipArea textarea').removeClass('newBox'); 
	    $j('#doctorViewForm #MembershipArea input[type=text],#doctorViewForm #MembershipArea textarea').removeAttr('readonly');
		$j('#doctorViewForm #doctorMembershipBtnEdit').hide();
		$j('#doctorViewForm #doctorMembershipBtnSave').show();
		$j('#doctorViewForm #doctorMembershipBtnCancel').show();
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 1, true );
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 2, true );
		

	});
	
	
$j('#doctorViewForm #doctorMembershipBtnSave').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchMemberShip').hide();
		$j('#doctorViewForm #doctorMembershipBtnEdit').show();
		$j('#doctorViewForm #doctorMembershipBtnSave').hide();
		$j('#doctorViewForm #doctorMembershipBtnCancel').hide();
	    $j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 2, false );
		var response = submitMembershipInfo();
		if(response.success==true){
		var doctorMemFilteredData=[];
		showTip("Doctor Membership Updated Successfully");
              }
			  else
			  updateTipsNew(getErrorName(response.error),$j('#doctorViewForm #errorDivViewdoctorData'),$j('#doctorViewForm #errorDivHeader'));
	});
	
$j('#doctorViewForm #doctorMembershipBtnCancel').die('click').live('click',function(){
		removeErrors();
		$j('#doctorViewForm #workBenchMemberShip').hide();
		$j('#doctorViewForm #doctorMembershipBtnEdit').show();
		$j('#doctorViewForm #doctorMembershipBtnSave').hide();
		$j('#doctorViewForm #doctorMembershipBtnCancel').hide();
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 2, false );
	});	

	
$j('#doctorViewForm #doctorMembershipBtnAdd').die('click').live('click',function(){
		removeErrors();
		var membership =nl2br($j('#doctorViewForm #Membership').val());
		var memId='0';
        var myData='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="delete" src="/netmd/images/remove-btn.png" rel="tooltip" title="delete"></a>';
	    var EditBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="edit" id="edit" src="/netmd/images/icon/dashboard/edit_on.png" rel="tooltip" title="edit"></a>';
		var SaveBT='<a href="#"><img width="20" height="20" style="margin:0 0 0 5px;"class="img-swap" name="save" id="save" src="/netmd/images/icon/dashboard/edit_off.png" rel="tooltip" title="save"></a>';
		if(membership!=""){
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnAddData([membership,myData,EditBT]);  
	    $j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 1, false );
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnSetColumnVis( 2, false );
		$j("#doctorViewForm #Membership input[type=text], textarea").val("");
		doctorMemFilteredData=resultJsonForMembership(memId,"Add",doctorInfo,doctorMemFilteredData,membership); 
	}
	else
		createError(constants_ExpWorkbench_Msg,$j('#doctorViewForm #Membership'));
	});	
	
	
$j('#doctorViewForm #doctorMembershipTable .delete').die('click').live('click',function(){
	removeErrors();
	var memId=$j(this).closest('tr').attr('id');
	var action="Delete";
	doctorMemFilteredData=resultJsonForMembership(memId,action,doctorInfo,doctorMemFilteredData); 
	});	

$j('#doctorViewForm #doctorMembershipTable #edit').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',true);
   });
});

$j('#doctorViewForm #doctorMembershipTable #save').die('click').live('click',function(){
	removeErrors();
	var row=$j(this).closest('tr');
	row.children("td").each(function() {
    $j(this).attr('contenteditable',false);
   });
  var memId=$j(this).closest('tr').attr('id');
  var membership=row.closest('tr').children('td:nth-child(1)').text();
   action="Update";
	doctorMemFilteredData=resultJsonForMembership(memId,action,doctorInfo,doctorMemFilteredData,membership); 
}); 
  
$j("#doctorViewForm #doctorMembershipTable .img-swap").die('click').live('click', function() {
	var source = $j(this);
	if(source.attr('name')=="edit") {
		this.src = this.src.replace("_on","_off");
		$j(this).attr("name","save");
		$j(this).attr("title","save");
		$j(this).attr("id","save");
	}
	else {
		this.src = this.src.replace("_off","_on");
		$j(this).attr("name","edit");
		$j(this).attr("title","edit");
		$j(this).attr("id","edit");
	}	
	return false;
});	
		
function resultJsonForMembership(memId,actionName,doctorInfo,doctorMemFilteredData,membership){
	var status = checkMemExistsInJson(doctorInfo,memId);
    var row="#"+memId;
	var rowId=$j(row);
	var action=actionName;
	var Doc_id= getDoctorId();
	doctorMemFilteredData = filterExpression_MemUid(doctorMemFilteredData,memId);
	if(actionName=='Delete'){
	var membership=rowId.closest('tr').children('td:nth-child(1)').text();
			doctorMemFilteredData.push('{' + '"id"' + ':'+ memId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"membership":"'+ membership +'",' + '"actionName"' + ':"'+'' + "Delete" + '"'+'}');
		var currentRow= rowId.closest('tr');
		$j('#doctorViewForm #doctorMembershipTable').dataTable().fnDeleteRow(currentRow[0]);
	}else {
		if(status==true){
		if(memId=='0')
			doctorMemFilteredData.push('{' + '"id"' + ':'+ memId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"membership":"'+ membership +'",' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');	
		else 
			doctorMemFilteredData.push('{' + '"id"' + ':'+ memId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"membership":"'+ membership +'",' + '"actionName"' + ':"'+'' + "Update" + '"'+'}');
	}
	else
	doctorMemFilteredData.push('{' + '"id"' + ':'+ memId +'' + ','+ '"doctorId"' + ':'+ Doc_id +'' + ',' + '"membership":"'+ membership +'",' + '"actionName"' + ':"'+'' + "Add" + '"'+'}');	
	 }
	return doctorMemFilteredData;
}

function checkMemExistsInJson(resultJson,memId) {
	var status = false;
	$j(resultJson.membership).each(function (index,exp) {
		if(exp.id == memId){
			status=true;
			return false;
		}	  
	});
	return status;
}

function filterExpression_MemUid(mem,id) {
	var result=[];
	var tempMem = '[' + mem + ']';
	tempMem = $j.parseJSON(tempMem);
	$j(tempMem).each(function(index,data){
	if(id!='0'){
	if(data.id==id)
			tempMem.splice(index,1);
		else
			result.push(JSON.stringify(data));
		}
		else
		result.push(JSON.stringify(data));
	});
	return result;
}
	
// functions for experience  
function submitExpInfo() {
	var resultJson = createSubmitJsonForExp();
	var response = postdataToServer("/netmd/ws/ui/doctor/doctorExperience", resultJson );	
	return response;
}

function createSubmitJsonForExp() {
	var submitdata;
    submitdata = '{'+ '"experience":['  + doctorExpFilteredData +']}';  
	return submitdata;
}

// functions for Qualification  
function submitQualificationInfo() {
	var resultJson = createSubmitJsonForQuali();
	var response = postdataToServer("/netmd/ws/ui/doctor/doctorQualification", resultJson );	
	return response;
}

function createSubmitJsonForQuali() {
	var submitdata;
    submitdata = '{'+ '"qualification":['  + doctorQualiFilteredData +']}';  
	return submitdata;
}

// functions for Achievements  
function submitAchievementInfo() {
	var resultJson = createSubmitJsonForAchvmt();
	var response = postdataToServer("/netmd/ws/ui/doctor/doctorAchievement", resultJson );	
	return response;
}

function createSubmitJsonForAchvmt() {
	var submitdata;
    submitdata = '{'+ '"achievement":['  + doctorAchmntFilteredData +']}';  
	return submitdata;
}


// functions for Expertise  
function submitExpertiseInfo() {
	var resultJson = createSubmitJsonForExpertise();
	var response = postdataToServer("/netmd/ws/ui/doctor/doctorExpertise", resultJson );	
	return response;
}

function createSubmitJsonForExpertise() {
	var submitdata;
    submitdata = '{'+ '"expertise":['  + doctorExprFilteredData+']}';  
	return submitdata;
}

// functions for Memberships 
function submitMembershipInfo() {
	var resultJson = createSubmitJsonForMembership();
	var response = postdataToServer("/netmd/ws/ui/doctor/doctorMemberShip", resultJson );	
	return response;
}

function createSubmitJsonForMembership() {
	var submitdata;
    submitdata = '{'+ '"membership":['  + doctorMemFilteredData +']}';  
	return submitdata;
}


function getDoctorId() {
	return $j("#doctorViewForm #doctorid label").text();
}

});	





