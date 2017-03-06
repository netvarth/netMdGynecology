function viewPatient(patientId) {
	//$j('#pageTitle').html(constant_PatientView_Msg);
	$j('#filter').hide();
	$j('#tabs-1').html("");
	$j('#filterToolBar-Container').html("");
	$j('#pageToolBar-Container').html("");
	$j('#patientCaseToolbarDiv').html("");
	//Creating Page Tool Bar
	var ptbContainer = $j('<div id="patientGeneralPTBContainer" />');
	var ptbdata =getRequestData('/netmd/json/toolbars/viewGeneralToolbar.json');
	var ptb = new PageToolBar(ptbdata);
	$j(ptbContainer).html(ptb.result);
	$j('#pageToolBar-Container').html(ptbContainer);	
	
	// create view design
	var viewdata = getRequestData('/netmd/json/view/viewPatientDetailsDiv.json');
	var contentForm = new form(viewdata);
	$j('#tabs-1').html(contentForm.result);	
	
	var patientTabVar=getRequestData('/netmd/json/view/viewPatientDetailsTab.json'); 
	$j('#patientViewForm').append((new tabs(patientTabVar).result));
	
	var personalData = getRequestData('/netmd/json/view/viewPatientInfo.json');
	var pData = new Container(personalData);
	$j('#personalInfoTab').html(pData.result); 
	
	viewPatientInfo(patientId);
	$j("#tabs-nohdr").tabs();
	clearNilFields('patientPersonalDiv');

	$j.cachedScript("/netmd/js/netmd/patient/new/case/newCaseEntryPoint.js").done(function(script, textStatus) {
	})
	$j.cachedScript("/netmd/js/netmd/patient/new/medicalrecord/newMedicalRecordEntryPoint.js").done(function(script, textStatus) {
	})
	$j.cachedScript("/netmd/js/netmd/patient/new/visits/newVisitEntryPoint.js").done(function(script, textStatus) {
	})
	$j.cachedScript("/netmd/js/netmd/patient/view/case/viewCaseEntryPoint.js").done(function(script, textStatus) {
	})
	$j.cachedScript("/netmd/js/netmd/patient/view/visits/viewVisitEntryPoint.js").done(function(script, textStatus) {
	})
	$j.cachedScript("/netmd/js/netmd/patient/view/medicalrecord/viewMedicalRecordEntryPoint.js").done(function(script, textStatus) {
	})
	//$j.cachedScript("/netmd/js/netmd/patient/view/labs/viewLabEntryPoint.js").done(function(script, textStatus) {
	//})
	$j.cachedScript("/netmd/js/netmd/patient/view/labs/resultEntryPoint.js").done(function(script, textStatus) {
	})
	
}

function viewPatientInfo(patientId) {
	var patientInfo = getPatientData(patientId);
	//alert(JSON.stringify(patientInfo));
	if(patientInfo.id)
		$j("#patientViewForm #patientid label").text(patientInfo.id);
	else
		$j("#patientViewForm #patientid label").text("Nil");
	if(patientInfo.firstName)
		$j("#patientViewForm #viewPatientFirstName label").text(patientInfo.firstName);
	else
		$j("#patientViewForm #viewPatientFirstName label").text('Nil');
	if(patientInfo.lastName)
		$j("#patientViewForm #viewPatientLastName").val(patientInfo.lastName);
	else
		$j("#patientViewForm #viewPatientLastName").val('Nil');

	if(patientInfo.gender)
		$j("#patientViewForm #lblViewPatientGender label").text(patientInfo.gender);
	else
		$j("#patientViewForm #lblViewPatientGender label").text('Nil');
	if(patientInfo.age)
		$j("#patientViewForm #viewPatientAge").val(patientInfo.age);
	else 
		$j("#patientViewForm #viewPatientAge").val('Nil');
	if(patientInfo.phone)
		$j("#patientViewForm #viewPatientPhone").val(patientInfo.phone);
	else 
		$j("#patientViewForm #viewPatientPhone").val('Nil');
	if(patientInfo.mobile)
		$j("#patientViewForm #viewPatientMobile").val(patientInfo.mobile);
	else 
		$j("#patientViewForm #viewPatientMobile").val('Nil');
	if(patientInfo.address)
		$j("#patientViewForm #viewPatientAddress").val(br2nl(patientInfo.address));
	else 
		$("#patientViewForm #viewPatientAddress").val('Nil');
	if(patientInfo.email)
		$j("#patientViewForm #viewPatientEmail label").text(patientInfo.email);
	else 
		$j("#patientViewForm #viewPatientEmail label").text('Nil');
   	if(patientInfo.ailment)
		$j("#patientViewForm #viewPatientAilment").val(br2nl(patientInfo.ailment));
	else 
		$j("#patientViewForm #viewPatientAilment").val('Nil');
	if(patientInfo.diagnosis)
		$j("#patientViewForm #viewPatientDiagonosis").val(br2nl(patientInfo.diagnosis));
	else 
		$j("#patientViewForm #viewPatientDiagonosis").val('Nil'); 
	if(patientInfo.bloodGroup)
		$j("#patientViewForm #viewPatientBloodGroup").val(patientInfo.bloodGroup);
	else 
		$j("#patientViewForm #viewPatientBloodGroup").val('Nil'); 
	if(patientInfo.height)
		$j("#patientViewForm #viewPatientHeight").val(patientInfo.height);
	else 
		$j("#patientViewForm #viewPatientHeight").val('Nil'); 
	if(patientInfo.weight)
		$j("#patientViewForm #viewPatientWeight").val(patientInfo.weight);
	else 
		$j("#patientViewForm #viewPatientWeight").val('Nil'); 
	if(patientInfo.chronicDisease)
		$j("#patientViewForm #viewPatientChronicDiseases").val(br2nl(patientInfo.chronicDisease));
	else 
		$j("#patientViewForm #viewPatientChronicDiseases").val('Nil'); 
	if(patientInfo.allergies)
		$j("#patientViewForm #viewPatientAllergies").val(br2nl(patientInfo.allergies));
	else 
		$j("#patientViewForm #viewPatientAllergies").val('Nil'); 
	if(patientInfo.familyHistory)
		$j("#patientViewForm #viewPatientFamilyHistory").val(br2nl(patientInfo.familyHistory));
	else 
		$j("#patientViewForm #viewPatientFamilyHistory").val('Nil'); 
	if(patientInfo.emergencyNo)
		$j("#patientViewForm #viewPatientEmrNum").val(patientInfo.emergencyNo);
	else 
		$j("#patientViewForm #viewPatientEmrNum").val('Nil'); 
}
function submitPatientInfo(){
	var resultJson = createSubmitJsonForPatient();
	//alert("hiiii"+resultJson);
	var response = postdataToServer("/netmd/ws/ui/patient/update", resultJson );	
	//alert(JSON.stringify(response));
	return response;	
}
function createSubmitJsonForPatient(){
    var age=$j('#patientViewForm #viewPatientAge').val();
	if(age=="")
		age=0;
		
	var name=$j('#patientViewForm #viewPatientFirstName label').text();
name= name.replace(/\b[a-z]/g, function(letter) {
	return letter.toUpperCase();
})	
		
     PatientAddress = nl2br($j('#viewPatientAddress').val());
	var patientDetails = '{"firstName":"'+ name +'",';
		patientDetails += '"lastName":"'+ $j('#patientViewForm #viewPatientLastName').val() +'",';
		patientDetails += '"id":"'+ $j('#patientViewForm #patientid label').text() +'",';
		patientDetails +='"age":"' + age +'",' ;
		patientDetails +='"gender":"' + $j("#patientViewForm  input[name='lblViewPatientGenderOne']:checked").val() + '",';
		patientDetails +='"mobile":"' + $j('#patientViewForm #viewPatientMobile').val() + '",';
		patientDetails +='"phone":"' + $j('#patientViewForm #viewPatientPhone').val() + '",';
		patientDetails +='"email":"' + $j('#patientViewForm #viewPatientEmail label').text() + '",';
		patientDetails +='"bloodGroup":"' + $j('#patientViewForm #viewPatientBloodGroup').val() + '",';
		patientDetails +='"height":"' + $j('#patientViewForm #viewPatientHeight').val() + '",';
		patientDetails +='"weight":"' + $j('#patientViewForm #viewPatientWeight').val() + '",';
		patientDetails +='"chronicDisease":"' +nl2br( $j('#patientViewForm #viewPatientChronicDiseases').val()) + '",';
		patientDetails +='"allergies":"' + nl2br($j('#patientViewForm #viewPatientAllergies').val()) + '",';
		patientDetails +='"familyHistory":"' + nl2br($j('#patientViewForm #viewPatientFamilyHistory').val()) + '",';
		patientDetails +='"emergencyNo":"' + $j('#patientViewForm #viewPatientEmrNum').val() + '",';
		patientDetails +='"ailment":"'+ nl2br($j('#patientViewForm #viewPatientAilment').val()) + '",';
		patientDetails +='"diagnosis":"'+ nl2br($j('#patientViewForm #viewPatientDiagonosis').val()) + '",';
		//patientDetails +='"occupation":"",';
		patientDetails +='"address":"' + PatientAddress + '",';
		//patientDetails +='"medicalHistory":"' +$j('#medicalHistory').val()  +'",';
		patientDetails +='"medicalHistory":[]}';
		//alert(patientDetails);
		return patientDetails;

}
function getPatientData(patientId) {
	var response=getRequestData("/netmd/ws/ui/patient/view/" + patientId);
	return response;
}
/* function to get the  previous patientId from patients table */
function getpreviousPatientId(patientId, pgPatientList) {
	var patId;
	$j(pgPatientList.patient).each(function (index, rowpatient) {
		if(patientId==rowpatient.id)	{
			var arrayLength=(pgPatientList.patient).length;
			var comp=arrayLength-1;
			if(index==0)
				patId = patientId;
			else
				patId=pgPatientList.patient[index-1].id;
		}
	});
	return patId;	
}
/* function to get the  next patientId from patients table*/
function getnextPatientId(patientId, pgPatientList) {
	var patId;
	$j(pgPatientList.patient).each(function (index, rowpatient) {
		if(patientId==rowpatient.id)	{
			var arrayLength=(pgPatientList.patient).length;
			var comp=arrayLength-1;
			if(index==comp){
				patId = patientId;
			}else{
				patId=pgPatientList.patient[index+1].id;
			}	
		}
	});	
	return patId;	
}
function restorePatientInfo(){
    $j('#patientViewForm #viewGender').show();
	$j('#patientViewForm #lblPatientEditGender').attr('style','display:none');
	$j('#patientViewForm input[type=text],#patientViewForm textarea').addClass('newBox');
	$j('#patientViewForm input[type=text],#patientViewForm textarea').attr('readonly','readonly');
	$j('#patientViewBtnSave').hide();
	$j('#patientViewBtnCancel').hide();
	$j('#patientViewBtnEditOne').show();
	$j('#patientGeneralPTBContainer #btn_up_ptb_id,#patientGeneralPTBContainer #btn_down_ptb_id,#patientGeneralPTBContainer #btn_back_ptb_id').show();	
}
function getPatientId() {
	return $j("#patientViewForm #patientid label").text();
}

// Toggle effect of view patient Json
$j('#patientPersonalDiv .heading').die('click').live('click',function() {
$j(this).nextAll('div.details:first').toggle();
});