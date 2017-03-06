function ViewDepartmentPTB(viewDepartmentUI) {
	this.backbutton = '#btn_back_ptb_id';
	this.upbutton = '#btn_up_ptb_id';
	this.downbutton = '#btn_down_ptb_id';
	this.viewDepartmentUI = viewDepartmentUI;
	
	this.getViewDepartmentUI = function() {
		return this.viewDepartmentUI;
	}
		
	this.getDepartmentUIStartup = function() {
		var viewDepartmentUI = this.getViewDepartmentUI();
		return viewDepartmentUI.getDepartmentUIStartup();
	}
	
	this.getDepartmentTableNavigator = function() {
		var departmentUIStartup = this.getDepartmentUIStartup();
		return departmentUIStartup.getDepartmentTableNavigator();
	}
	
	
	this.bindEvents = function(ptbParent,source) {
		self=this;
		var viewUI = self.getViewDepartmentUI();
		$j(ptbParent +" " + self.backbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var departmentUIStartup = self.getDepartmentUIStartup();
			departmentUIStartup.init();
		});
		$j(ptbParent +" " + self.upbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var departmentTableNavigator = self.getDepartmentTableNavigator();
			var department = source.getDepartment();
			var prevId = self.getPrevId(department.getId(),departmentTableNavigator.getPgDataList());
			viewUI.viewDepartment(prevId);
		});
		$j(ptbParent +" " + self.downbutton).die('click').live('click',function(){
			commonMethodInvoker.removeErrors();
			var departmentTableNavigator = self.getDepartmentTableNavigator();
			var department = source.getDepartment();
			var prevId = self.getNextId(department.getId(),departmentTableNavigator.getPgDataList());
			viewUI.viewDepartment(prevId);
		});
	}
	
	this.init = function (source) {
		var ptbProcessor = new PageToolBarProcessor();
		ptbParent = ptbProcessor.create(constants.DEPARTMENTGENERAL, constants.DEPARTMENTGENERALPTBURL);
		this.bindEvents(ptbParent,source);
	} 
}