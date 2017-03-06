function ViewMedicalRecordPTB(viewMedicalRecordUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewMedicalRecordUI = viewMedicalRecordUI;
	
	this.getViewMedicalRecordUI = function() {
		return this.viewMedicalRecordUI;
	}
		
	this.getMedicalRecordUIStartup = function() {
		var viewMedicalRecordUI = this.getViewMedicalRecordUI();
		return viewMedicalRecordUI.getMedicalRecordUIStartup();
	}
	
	this.getMedicalRecordTableNavigator = function() {
		var medicalRecordUIStartup = this.getMedicalRecordUIStartup();
		return medicalRecordUIStartup.getMedicalRecordTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewMedicalRecordUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var medicalRecordUIStartup = self.getMedicalRecordUIStartup();
			medicalRecordUIStartup.init(viewUI.caseId,viewUI.patientId);
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var medicalRecordTableNavigator = self.getMedicalRecordTableNavigator();
			var medicalRecordObj = source.getMedicalRecord();
			var prevId = self.getPrevId(medicalRecordObj.getId(),medicalRecordTableNavigator.getPgDataList());
			viewUI.ViewMedicalRecord(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var medicalRecordTableNavigator = self.getMedicalRecordTableNavigator();
			var medicalRecordObj = source.getMedicalRecord();
			var prevId = self.getNextId(medicalRecordObj.getId(),medicalRecordTableNavigator.getPgDataList());
			viewUI.ViewMedicalRecord(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.MEDICALRECORDGENERAL, constants.MEDICALRECORDGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}