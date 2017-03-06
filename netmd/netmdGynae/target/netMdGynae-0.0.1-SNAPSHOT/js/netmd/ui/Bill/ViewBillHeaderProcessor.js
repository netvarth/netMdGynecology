function ViewBillHeaderProcessor(viewBillUI) {
	this.viewBillUI=viewBillUI;
	self=this.getViewBillUI();
}

ViewBillHeaderProcessor.prototype.getViewBillUI=function() {
	return this.viewBillUI;
}

ViewBillHeaderProcessor.prototype.writable = function() {
	$j(self.patientEditButton).hide();
	$j(self.patientHeaderNote).show();
	$j(self.ptbBack).hide();
	$j(self.ptbUp).hide();
	$j(self.ptbDown).hide();
	$j(self.patientHeader+ " input[type=text]").removeAttr('readonly');
	$j(self.patientHeader + " input[type=text]").removeClass('newBox');
	$j(self.patientCancelButton).show();
	$j(self.patientUpdateButton).show();
	$j(self.patientHeader + " input[type=text]").removeAttr('disabled');
}

ViewBillHeaderProcessor.prototype.readable = function() {
	$j(self.ptbBack).show();
	$j(self.ptbUp).show();
	$j(self.ptbDown).show();
	$j(self.patientHeaderNote).hide();
	$j(self.patientHeader + " input[type=text]").attr('readonly',true);
	$j(self.patientHeader + " input[type=text]").addClass('newBox');
	$j(self.patientCancelButton).hide();
	$j(self.patientUpdateButton).hide();
	$j(self.patientEditButton).show();
	$j(self.patientHeader + " input[type=text]").attr('disabled',true);
}

ViewBillHeaderProcessor.prototype.setPatientList = function() {
	var billService=self.getBillService();
    self.patientDetails=billService.getPatientDetails();
    methodInvoker.fillPatientDetailsToControl(self.patientName,self.patientDetails);
}


ViewBillHeaderProcessor.prototype.fillOtherPatientDetails= function(patientDetail) {
	self.patientId=patientDetail.id;
	$j(self.email).text(patientDetail.email);
	$j(self.age).text(patientDetail.age);
	$j(self.phone).text(patientDetail.phone);
	$j(self.mobile).text(patientDetail.mobile);
	
}
ViewBillHeaderProcessor.prototype.getPatientHeaderDetails= function() {
	var billPatientHeaderDTO = new BillPatientHeaderDTO();
	var referral=$j(self.referral).val();
	var patientType=$j(self.patientType).text();
	var note=$j(self.patientNote).val();
	billPatientHeaderDTO.setPatientId(self.patientId);
	billPatientHeaderDTO.setUid(self.billUid);
	billPatientHeaderDTO.setReferralName(referral);
	billPatientHeaderDTO.setOrigin(patientType);
	//billPatientHeaderDTO.setNote(note);
	return billPatientHeaderDTO;
}
ViewBillHeaderProcessor.prototype.validateHeaderDetails=function(){
	var bValid=true;
	var patientName=$j(self.patientName).val();
	 var patientStatus= methodInvoker.checkValidPatient(patientName,self.patientDetails);
		if(patientStatus==false){
			commonMethodInvoker.createError($j(self.patientName), constants.ENTERVALIDPATIENT);
			bValid=false;
		}
		return bValid;

}
ViewBillHeaderProcessor.prototype.bindEvents = function() {
	var self_this=this;
	$j(self.patientEditButton).die('click').live('click',function(){
		commonMethodInvoker.removeErrors();
		self_this.writable(); 
		self_this.setPatientList();
		
	});

	$j(self.patientUpdateButton).die('click').live('click',function(){
		self.errorHeader.hide();
		commonMethodInvoker.removeErrors();
		var patientHeaderDetails = self_this.getPatientHeaderDetails();
		var billService =self.getBillService();
		if(self_this.validateHeaderDetails()){
			var billResponse = billService.updateBillHeader(patientHeaderDetails);
			if(billResponse.success==true) {
				showTip(constants.PATIENTUPDATESUCCESS);
				self_this.readable();
				var response=self.viewBill(billResponse.id);
				self.setPatientDetails(response);
				
			} else {
				commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(billResponse.error));
			}
	}
	
			
});

 $j(self.patientCancelButton).die('click').live('click',function(){
	self.errorHeader.hide();
	commonMethodInvoker.removeErrors();
	var response=self.viewBill(self.billUid);
	self.setPatientDetails(response);
	self_this.readable();
	});		
	

	$j(self.patientName).autocomplete({
   		 select: function(e, ui){
	 var newPatientName;
	 var originaldata;
       $j(self.patientName).val(ui.item.value);
        commonMethodInvoker.removeErrors();
        var patientName=$j(self.patientName).val();
		if(patientName.indexOf('[')==-1){
			newPatientName=patientName;
			originaldata=patientName;	
		}
		else{
			newPatientName=patientName.split("[")[0];
			newPatientName=$j.trim(newPatientName);
			var data=patientName.split("[")[1];
			originaldata=data.split("]")[0];
		}
	
		
		var patientDetail=methodInvoker.getPatientDetails(originaldata,newPatientName,self.patientDetails);
	   	self_this.fillOtherPatientDetails(patientDetail);
	    this.value =newPatientName;
   		 return false;
    }
	});
}