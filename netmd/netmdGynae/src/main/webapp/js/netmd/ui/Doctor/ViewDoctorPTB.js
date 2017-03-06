function ViewDoctorPTB(viewDoctorUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewDoctorUI = viewDoctorUI;
	
	this.getViewDoctorUI = function() {
		return this.viewDoctorUI;
	}
		
	this.getDoctorUIStartup = function() {
		var viewDoctorUI = this.getViewDoctorUI();
		return viewDoctorUI.getDoctorUIStartup();
	}
	
	this.getDoctorTableNavigator = function() {
		var doctorUIStartup = this.getDoctorUIStartup();
		return doctorUIStartup.getDoctorTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewDoctorUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var doctorUIStartup = self.getDoctorUIStartup();
			doctorUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var doctorTableNavigator = self.getDoctorTableNavigator();
			var doctor = source.getDoctor();
			var prevId = self.getPrevId(doctor.getId(),doctorTableNavigator.getPgDataList());
			viewUI.ViewDoctor(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var doctorTableNavigator = self.getDoctorTableNavigator();
			var doctor = source.getDoctor();
			var prevId = self.getNextId(doctor.getId(),doctorTableNavigator.getPgDataList());
			viewUI.ViewDoctor(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.DOCTORGENERAL, constants.DOCTORGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}