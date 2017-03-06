/*JS contains the general methods for weblims application*/

function NetMDMethodInvoker() {
	this.getEnumList=function() {
		return this.enumList;
	}
	this.setEnumList=function() {
		ajaxProcessor.setUrl(constants.ENUMLISTURL);
		this.enumList =ajaxProcessor.get();
	}
	var enumList = this.getEnumList();
	this.fillMedicalTypesToControl=function(controlName) {
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Type</option>');
		$j(controlName).append('<option value="Prescription"> Prescription</option>');
		$j(controlName).append('<option value="Email">Email</option>');
		$j(controlName).append('<option value="Phone">Phone</option>');
		$j(controlName).append('<option value="DoctorRounds">Doctor Rounds</option>');
		$j(controlName).append('<option value="Other">Other</option>');
		$j(controlName).append('<option value="PersonalVisit">Personal Visit</option>');	
	}
	this.fillDoctorAsAutoComplete=function(controlObj) {
	var exp=getExpressionForDoctor();
	var maxRecords=0; 
	var maxPages = 0; 
	var interval = 10;
	var curPage = 1;
	autoCompleteArray=[];
	var referralListJson = getFilterlistUrl(exp,(curPage-1),interval);
	listDoctor= postdataToServer("/netmd/ws/ui/doctor/list",referralListJson);
	 $j(listDoctor.referral).each(function (doctorIndex, refferalObj) {
			 if(refferalObj.lastName==null)
				autoCompleteArray.push(''+refferalObj.firstName+' '+refferalObj.qualification+'');	
			else if(refferalObj.qualification=="")
				autoCompleteArray.push(''+refferalObj.firstName+' '+refferalObj.lastName+'');	
			else
				autoCompleteArray.push(''+refferalObj.firstName+' '+refferalObj.lastName+' '+refferalObj.qualification+'');
		});	
			if(listDoctor.referral=="")	
				$j('#leftPaneSchedules,#leftPaneAppointments').hide();
			 else
			    $j('#leftPaneSchedules,#leftPaneAppointments').show();
			  makeautoComplete(controlObj,autoCompleteArray);
	}
	this.fillRoomTypeToControl=function(controlName,data) {
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Type</option>');
		$j(data.roomType).each(function(index,roomType){ 
			$j(controlName).append('<option value='+roomType.id+'>'+roomType.type+'</option>');
		});
		
	}

	this.fillBedTypeToControl=function(controlName,data) {
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Type</option>');
		$j(data.bedTypeList).each(function(index,bedType){ 
			$j(controlName).append('<option value='+bedType.id+'>'+bedType.type+'</option>');
		});
		
	}
	this.fillTaxTypeToControl=function(controlName,data) {	
		$j(controlName).empty();
		$j(controlName).append('<option value="0">Select tax Type</option>');
		$j(data.taxlist).each(function(index,tax){ 
			$j(controlName).append('<option value='+tax.id+'>'+tax.name+'</option>');
		});
		
	}
	this.fillParentHeadToControl=function(controlName,data) {	
		$j(controlName).empty();
		$j(controlName).append('<option value="select"></option>');
		 $j(data.headList).each(function(index,parentHead){ 
			$j(controlName).append('<option value='+parentHead.id+'>'+parentHead.headName+'</option>');
		}); 
		
	}
	this.fillHeadTypeToControl=function(controlName,data) {	
		$j(controlName).empty();
		$j(controlName).append('<option value="Select Head">Select Head</option><option value="Head">Head</option><option value="SubHead">Sub Head</option>');		
	}
	this.fillMedReportType=function(controlObj) {
		$j(controlObj).empty();
		$j(controlObj).append('<option value="Prescription">Prescription</option><option value="Doctorrounds">Doctor rounds</option><option value="Email">Email</option><option value="Phone">Phone</option><option value="Other">Other</option>');
	} 
	this.fillDiscountToControl=function(controlName) {	
		var self=this;
		var discountList=self.getDiscountList();
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Discount</option>');
		$j(discountList.discount).each(function(index,discount){ 
			$j(controlName).append('<option value='+discount.id+'>'+discount.name+'</option>');
		});
		
	}
    this.fillDepartmentToControl=function(controlName,data){
	var self=this;
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Department</option>');
		$j(data.department).each(function(index,department){ 
			$j(controlName).append('<option value='+department.id+'>'+department.departmentName+'</option>');
		});
	}
    this.fillRoomNumbersToControl=function(controlName,data) {
		autoCompleteArray=[];
		$j(data.room).each(function(index,room){ 
			autoCompleteArray.push(room.roomNumber);	
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
		
	}
	this.fillBlockToControl=function(controlName,data) {
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Type</option>');
		$j(data.block).each(function(index,block){ 
			$j(controlName).append('<option value='+block.id+'>'+block.name+'</option>');
		});
	}
	this.fillDepartmentToControl=function(controlName,data) {
		$j(controlName).empty();
		$j(controlName).append('<option value="select">Select Type</option>');
		$j(data.department).each(function(index,department){ 
			$j(controlName).append('<option value='+department.id+'>'+department.departmentName+'</option>');
		});
	}
	this.fillPatientDetailsToControl=function(controlName,data) {
		autoCompleteArray=[];
		$j(data.patient).each(function(index,patient){ 
			if(patient.email!="")
				autoCompleteArray.push(patient.firstName+" "+patient.lastName+" "+'['+patient.email+']');
			else if(patient.mobile!="")
				autoCompleteArray.push(patient.firstName+" "+patient.lastName+" "+'['+patient.mobile+']');
			else if(patient.phone!="")
				autoCompleteArray.push(patient.firstName+" "+patient.lastName+" "+'['+patient.phone+']');	
			else 
				autoCompleteArray.push(patient.firstName+" "+patient.lastName);	
           	
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.getPatientDetails=function(email,data){
		var patientDetails="";
		$j(data.patient).each(function(index,patient){ 
			if(patient.email==email){
			 patientDetails=patient;
			return false;
		}
		});
		return patientDetails
	}
	this.getPatientDetails=function(data,newPatientName,patientDetail){
		var patientDetails="";
		$j(patientDetail.patient).each(function(index,patient){
		
			if(patient.email==data && patient.firstName+" "+patient.lastName==newPatientName){
			 patientDetails=patient;
			return false;
		}
		else if(patient.mobile == data && patient.firstName+" "+patient.lastName == newPatientName){
			 patientDetails=patient;
			return false;
		}
		 else if(patient.phone == data && patient.firstName+" "+patient.lastName == newPatientName){
			 patientDetails=patient;
			return false;
		}
		else if(patient.firstName+" "+patient.lastName == newPatientName){
			 patientDetails=patient;
			return false;
		} 
		});
		return patientDetails;
	}
	
	this.getPatientIdUsingName=function(controlObj) {
		ajaxProcessor.setUrl(constants.GETPATIENTDETAILS);
		var listPatient = ajaxProcessor.get();
		 $j(listPatient.patient).each(function(index,patient){ 
		var fullName=''+patient.firstName+' '+patient.lastName+'';
			if(controlObj==fullName)
				patientId=patient.id;
		});
		return patientId; 
}

	this.checkValidPatient=function(patientName,data){
		var status=false;
		$j(data.patient).each(function(index,patient){ 
			if(patient.firstName.trim().toUpperCase()==patientName.trim().toUpperCase()){
				status=true;
				return false;
			}
		});
		return status;
	}
	
	 this.checkValidRoomNumber=function(roomNo,data) {
	 	var roomNumberId=0;
		$j(data.room).each(function(index,room){ 
			if(room.roomNumber==roomNo){
			    roomNumberId= room.id;
			    return false;
			}

		});
		return roomNumberId;		
	}
	this.fillListToControl=function(controlName) {	
		var self=this;	
		autoCompleteArray=[];
		var items=self.getItemList();
		var services=self.getServiceList();
		var beds=self.getBedList();
		$j(controlName).empty();
		$j(items.itemList).each(function(index,item){ 
			autoCompleteArray.push(item.name);
		});
		$j(services.supportList).each(function(index,support){ 
			autoCompleteArray.push(support.name);
		});
		$j(beds.bedList).each(function(index,bed){ 
			autoCompleteArray.push(bed.bedNumber);
		});
		commonMethodInvoker.makeautoComplete(controlName, autoCompleteArray);
	}
	this.getItemList=function() {
		ajaxProcessor.setUrl(constants.GETITEMLIST);
	   return ajaxProcessor.get();
	}
	this.getDiscountList=function() {
		ajaxProcessor.setUrl(constants.GETDISCOUNTLIST);
	   return ajaxProcessor.get();
	}
	this.getServiceList=function() {
		ajaxProcessor.setUrl(constants.GETSERVICELIST);
	   return ajaxProcessor.get();
	}

	this.getBedList=function() {
		ajaxProcessor.setUrl(constants.GETBEDLIST);
	   return ajaxProcessor.get();
	}
	this.getDiscountInfo=function(discountId) {
		ajaxProcessor.setUrl(constants.GETDISCOUNTINFO+discountId);
	   return ajaxProcessor.get();
	}
	/* sample to get the id of items or services*/
	this.checkItemServiceExists = function(itemService) {
		var self=this;
		var itemServiceId="";
		var items=self.getItemList();
		var services=self.getServiceList();
		var beds=self.getBedList();
		$j(items.itemList).each(function(index,item){ 
			if(itemService.trim().toUpperCase()==item.name.toUpperCase() ) {
				itemServiceId="item_"+item.id;
				return false;
			}	
		});
		if(itemServiceId=="") {
			$j(services.supportList).each(function(index,support){  
				if(itemService.trim().toUpperCase()==support.name.toUpperCase() ) {
					itemServiceId="service_"+support.id;
					return false;
				}	
			});
		}
		if(itemServiceId=="") {
			$j(beds.bedList).each(function(index,bed){  
				if(itemService.trim().toUpperCase()==bed.bedNumber.toUpperCase() ) {
					itemServiceId="bed_"+bed.id;
					return false;
				}	
			});
		}
		return itemServiceId;
	}
	this.getItemServiceDetail = function(itemOrServiceId) {
		var self=this;
		var itemServiceDetail="";
		itemServiceId=itemOrServiceId.split("_")[1];
		var isitem=itemOrServiceId.split("_")[0];
		if(isitem=="item"){
			var items=self.getItemList();
			$j(items.itemList).each(function(index,item){ 
				if(itemServiceId==item.id ) {
					itemServiceDetail=item;
					return false;
				}	
			});
	}
	else if(isitem=="service"){
		     var services=self.getServiceList();
			$j(services.supportList).each(function(index,support){  
				if(itemServiceId==support.id) {
					itemServiceDetail=support;
					return false;
				}	
			});
		
	}
	else if(isitem=="bed"){
		var beds=self.getBedList();
		$j(beds.bedList).each(function(index,bed){  
				if(itemServiceId==bed.id) {
					itemServiceDetail=bed;
					return false;
				}	
			});

	}
		return itemServiceDetail;
	}
	this.generateReportTabs = function(response){
		if(!response.errorMessage) 
			$j('#reportTabsDiv').append((new tabs(response).result));
		else
			$j('#reportTabsDiv').append("Reports Not Available");
		$j("#tabs-nohdr").tabs();
	}
	
	this.addBabyRow = function(count,mode){
		var self=this;
		mainhead = $j('<div class="one" id="babyDiv"></div>');
		
		var agesubhead = $j('<div class="oneSixth bold" number="'+count+'">Baby  '+(count+1)+'<div>');
		mainhead.append(agesubhead);
		var agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec = $j('<select id="babyGender_'+count+'" name="babyGender" class="select ans"><option value="select">&nbsp;</option><option value="male">Male</option><option value="female">Female</option><option value="ambiguous">Ambiguous</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec = $j('<input type="text" style="width:95%"  class="ans babyWeight" id="babyWeight_'+count+'" name="babyWeight">');
		if(mode=="view")
		   $j(inputSec).attr('readonly','readonly');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec = $j('<input type="text" style="width:95%"  class="ans DeliveryTime" id="DeliveryTime_'+count+'" name="DeliveryTime">');
		if(mode=="view")
		   $j(inputSec).attr('readonly','readonly');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec =$j('<select id="babyApgaronemin_'+count+'"  name="babyApgaronemin" class="select apgar ans "/>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec=$j('<select  id="babyApgarfivemin_'+count+'" name="babyApgarfivemin" class="select apgar ans"/>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec=$j('<select  id="babyPresentation_'+count+'" name="babyPresentation" class="select  ans"><option value="select">&nbsp;</option><option value="Cephalic">Cephalic</option><option value="Breech">Breech</option><option value="Shoulder">Shoulder</option><option value="Face">Face</option><option value="Transverse">Transverse</option><option value="Others">Others</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="oneSixth bold"></div>');
		var inputSec=$j('<select  id="babyDeliveryType_'+count+'" name="babyDeliveryType" class="select  ans"><option value="select">&nbsp;</option><option value="Normal">Normal</option><option value="Forcepsdelivery">Forceps</option><option value="Vacuumextraction">Vacuum</option><option value="Breech">Breech</option><option value="Caesarean">Caesarean</option><option value="Sequential">Sequential</option><option value="VBAC">VBAC</option><option value="Others">Others</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		  agesubhead.empty().html(inputSec); 
		mainhead.append(agesubhead);
		
		if(count==0)
			var agesubhead = $j('<div class="add-delete" style="width:16%;float:left;>'+constants.ADDIMAGETAG +'</div>');
		else	
			var agesubhead = $j('<div class="add-delete" style="width:16%;float:left;>'+constants.DELETEBABYTAG +'</div>');
		mainhead.append(agesubhead);
		return mainhead;
	}
	
	this.addFetalRow = function(count,mode){
		var self=this;
		mainhead = $j('<div class="one" id="foetalDiv"></div>');
		var agesubhead = $j('<div class="onefourth bold" number="'+count+'">Baby  '+(count+1)+'<div>');
		mainhead.append(agesubhead);
		
		var agesubhead = $j('<div class="onefourth bold"></div>');
		var inputSec = $j('<select id="fetalCongAnomali_'+count+'" name="fetalCongAnomali" class="select ans"><option value="select">&nbsp;</option><option value="Yes">Yes</option><option value="No">No</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		
		var agesubhead = $j('<div class="onefourth bold"></div>');
		var inputSec = $j('<select id="CongAnomaliReason_'+count+'" name="CongAnomaliReason" class="select ans"><option value="select">&nbsp;</option><option value="diaphragmaticHernia">Diaph.Hernia</option><option value="neuralTubeDefect">N.Tube Defect</option><option value="CHD">CHD</option><option value="CLEFTLIP">Cleft Lip</option><option value="CLEFTPALATE">Cleft Palate</option><option value="CDH">CDH</option><option value="OMPHALOCELE">Omphalocele</option><option value="AMBIGGENITALIA">AmbigGenitalia</option><option value="BOWELATRESIA">Bowel Atresia</option><option value="ERBS">ERBS</option><option value="RENAL">RENAL</option><option value="TRISOMY">TRISOMY</option><option value="Others">Others</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="onefourth bold"></div>');
		var inputSec =$j('<select id="fetalNICU_'+count+'" name="fetalNICU"class="select ans"><option value="select">&nbsp;</option><option value="Yes">Yes</option><option value="No">No</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="onefourth bold"></div>');
		var inputSec =$j('<select id="NICUReason_'+count+'" name="NICUReason" class="select ans"><option value="select">&nbsp;</option><option value="PRETERM">PRETERM</option><option value="ASPHYXIA">Asphyxia</option><option value="JAUNDICE">Jauntice</option><option value="HYPOGLYCAEMIA">Hypoglycaemia</option><option value="TTN">TTN</option><option value="GRUNTING">Grunting</option><option value="IUGR">IUGR</option><option value="SEPSIS">SEPSIS</option><option value="RDS">RDS</option><option value="MAS">MAS</option><option value="POORFEEDING">Poor Feeding</option><option value="ERBS">ERBS</option><option value="Anomalies">Anomalies</option><option value="Others">Others</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="onefourth bold"></div>');
		var inputSec =$j('<select id="fetalstillbirth_'+count+'" name="fetalstillbirth" class="select ans"><option value="select">&nbsp;</option><option value="Yes">Yes</option><option value="No">No</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		agesubhead = $j('<div class="onefourth bold"></div>');
		var inputSec =$j('<select id="fetalNeonatalDeath_'+count+'" name="fetalNeonatalDeath" class="select ans"><option value="select">&nbsp;</option><option value="Yes">Yes</option><option value="No">No</option><option value="Not Entered">Not Entered</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		agesubhead.empty().html(inputSec);
		mainhead.append(agesubhead);
		
		if(count==0)
			var agesubhead = $j('<div class="add-delete" style="width:10%;float:left;>'+constants.ADDFETAL +'</div>');
		else	
			var agesubhead = $j('<div class="add-delete" style="width:10%;float:left;>'+constants.DELETEFETAL +'</div>');
		mainhead.append(agesubhead);
		return mainhead;
	}
	
	this.addDeathDetailsRow = function(count,mode){
		var self=this;
		mainhead = $j('<div id="deathDetailsDiv" number="'+count+'" class="A"></div>');
		var subhead = $j('<div class="B bold"  >Death Details  '+count+'<div>');
		mainhead.append(subhead);
		
		var subhead = $j('<div class="B bold"></div>');
		var inputSec = $j('<input type="text" class="ans" id="death'+count+'name" name="death'+count+'name">');
		if(mode=="view")
		   $j(inputSec).attr('readonly','readonly');
		subhead.empty().html(inputSec);
		mainhead.append(subhead);
		
		var subhead = $j('<div class="B bold"></div>');
		var inputSec = $j('<select  id="death'+count+'cause" name="death'+count+'cause" class="select ans"><option value="select">&nbsp;</option><option value="Haemorrhage">Haemorrhage</option><option value="Hypertension">Hypertension</option><option value="Afembolism">Af Embolism</option><option value="Sepsis">Sepsis</option><option value="HeartDisease">Heart Disease</option><option value="Others">Others</option></select>');
		if(mode=="view")
		   $j(inputSec).attr('disabled','disabled');
		subhead.empty().html(inputSec);
		mainhead.append(subhead);
		
		var subhead = $j('<div class="B bold"></div>');
		var inputSec = $j('<textarea  class="ans" id="death'+count+'address" name="death'+count+'address"></textarea>');
		if(mode=="view")
		   $j(inputSec).attr('readonly','readonly');
		subhead.empty().html(inputSec);
		mainhead.append(subhead);
			
		if(count==1)
			var subhead = $j('<div style="width:20%;float:left;>'+constants.ADDDEATH +'</div>');
		else	
			var subhead = $j('<div style="width:20%;float:left;>'+constants.DELETEDEATH +'</div>');
		mainhead.append(subhead);
		return mainhead;
	}
	
	this.fillvaluesToQuestionNaire=function(controlName){
		var minOffset = 0, maxOffset = 9; 
		$j(controlName).empty();
		$j(controlName).append('<option value="select">&nbsp;</option>');
		for (var i = minOffset; i <= maxOffset; i++) {
			$j('<option>', {value: i, text: i}).appendTo(controlName);
		}
		$j(controlName).append('<option value="Not Entered">Not Entered</option>');
	}
	this.getFilterParameterType = function(button){
		var type=null;
		if(button.displayName=="uid") 
			type="text";
		if(button.displayName=="patientName")
			type="text";
		if(button.displayName=="referralName")
			type="text";
		if(button.displayName=="paymentStatus")
			type="text";
		if(button.displayName=="billStatus")
			type="text";	
		if(button.displayName=="origin")
			type="text";	
		if(button.displayName=="orderDate")
			type="date";
		if(button.displayName=="firstName")
			type="text";
		if(button.displayName=="mobile")
			type="text";
		if(button.displayName=="phone")
			type="text";
		if(button.displayName=="email")
			type="text";
		if(button.displayName=="status")
			type="text";
		if(button.displayName=="address")
			type="text";
		if(button.displayName=="age")
			type="text";
		if(button.displayName=="dob")
			type="text";
		if(button.displayName=="bloodGroup")
			type="text";
		if(button.displayName=="name")
			type="text";
		if(button.displayName=="price")
			type="text";
		if(button.displayName=="taxVal")
			type="text";	
		if(button.displayName=="calculationType")
			type="text";
		if(button.displayName=="discountType")
			type="text";
		if(button.displayName=="createdTime")
			type="date";
		if(button.displayName=="antenatalCreatedDate")
			type="date";
		if(button.displayName=="syncStatus")
			type="text";
		if(button.displayName=="patientId")
			type="number";
		if(button.displayName=="caseNumber")
			type="number";
		if(button.displayName=="department")
			type="text";
		if(button.displayName=="deliveryMonth")
			type="date";
		return type;			
	}
	
	this.getFilterParameterTypeNew = function(displayName){
		var type=null;
		if(displayName=="height")
			  type="number";
		if(displayName=="age")
			  type="number";
		if(displayName=="bookedUnbooked")
			  type="text";	
		 if(displayName=="weight")
			  type="number";	
		if(displayName=="bodymassIndex")
			  type="number";	 
		if(displayName=="bloodgroup")
			  type="text";	 
		if(displayName=="parity")
			  type="number";	 
		if(displayName=="previousCS")
			  type="number";	 
		if(displayName=="TypeOfDelivery")
			  type="text";	 
		if(displayName=="multiplePregnancy")
			  type="text";	 
		if(displayName=="help")
			  type="text";	
		if(displayName=="eclampsia")
			  type="text";	 
		if(displayName=="DeliveryType")
			  type="text";	  
		 if(displayName=="deliveryName")
			  type="text";	
		if(displayName=="episiotomy")
			  type="text";	 
		if(displayName=="perinealTear")
			  type="text";	 
		if(displayName=="induction")
			  type="text";	 
		if(displayName=="extraOxytoxinUsed")
			  type="text";	 
		if(displayName=="oxytoxinBolus")
			  type="text";	 
		if(displayName=="otherOxytoxinUsed")
			  type="text";	
		if(displayName=="thirdStageDuration")
			  type="number";	
		if(displayName=="bloodLoss")
			  type="number";	
		if(displayName=="bloodProduct")
			  type="text";	
		if(displayName=="placentalWght")
			  type="number";
		if(displayName=="amtFluids")
			  type="number";
		if(displayName=="isfourthStageMon")
			  type="text";
		if(displayName=="maternalDeath")
			  type="text";
		if(displayName=="AntiBioticsUsed")
			  type="text";
		if(displayName=="muscleRelaxants")
			  type="text";
		if(displayName=="robsonclass")
			  type="text";	 
		if(displayName=="doctorIncharge")
			  type="text";	 
		if(displayName=="conductedBy")
			  type="text";	 
		if(displayName=="babyPresentation_0"||displayName=="babyPresentation_1")
			  type="text";	
		if(displayName=="babyWeight_0"||displayName=="babyWeight_1")
			  type="number";
		if(displayName=="fetalstillbirth_0"||displayName=="fetalstillbirth_1")
			  type="number";
		if(displayName=="fetalNICU_0"||displayName=="fetalNICU_1")
			  type="number";
	   return type;				  
	}
	
	this.toTitleCase=function(str) {
		 return str.replace(/\w\S*/g, function(txt){return txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase();});
	}
	
}