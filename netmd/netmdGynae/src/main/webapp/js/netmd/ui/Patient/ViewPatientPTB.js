function ViewPatientPTB(viewPatientUI) {

	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewPatientUI = viewPatientUI;
	
	this.getViewPatientUI = function() {
		return this.viewPatientUI;
	}
		
	this.getPatientUIStartup = function() {
		var viewPatientUI = this.getViewPatientUI();
		return viewPatientUI.getPatientUIStartup();
	}
	
	this.getPatientTableNavigator = function() {
		var patientUIStartup = this.getPatientUIStartup();
		return patientUIStartup.getPatientTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewPatientUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var patientUIStartup = self.getPatientUIStartup();
			patientUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var patientTableNavigator = self.getPatientTableNavigator();
			var patient = source.getPatient();
			var prevId = self.getPrevId(patient.getId(),patientTableNavigator.getPgDataList());
			viewUI.ViewPatient(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var patientTableNavigator = self.getPatientTableNavigator();
			var patient = source.getPatient();
			var prevId = self.getNextId(patient.getId(),patientTableNavigator.getPgDataList());
			viewUI.ViewPatient(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.PATIENTGENERAL, constants.PATIENTGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}