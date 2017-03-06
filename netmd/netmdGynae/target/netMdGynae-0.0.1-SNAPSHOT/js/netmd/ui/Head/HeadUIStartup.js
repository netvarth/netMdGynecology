function HeadUIStartup() {
	this.pgTableName = "#head";
	this.pgTableContainer="#headListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#headPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#headPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#headPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#headPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.headIdCol';
	this.exp = new ExpressionListDTO();
	this.headService = new HeadServiceImpl();
	this.listUrl = constants.HEADLISTURL;
	this.headTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.headService,this.exp);
	this.ftbToolBar = '#head-filter-toolbar';
	this.filter = '#filter';
	this.viewHeadUI = new ViewHeadUI(this);
}

HeadUIStartup.prototype.setHeadTableNavigator = function(headTableNavigator) {
	this.headTableNavigator = headTableNavigator;
}
 HeadUIStartup.prototype.getHeadService = function() {
	return this.headService;
}  
HeadUIStartup.prototype.getHeadTableNavigator = function() {
	return this.headTableNavigator;
}
HeadUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
 HeadUIStartup.prototype.getViewHeadUI = function() {
	return this.viewHeadUI;
}  

HeadUIStartup.prototype.init = function() {
	var self = this;
	var headTableNavigator = self.getHeadTableNavigator();
	self.setPageTitle(constants.HEADTITLE);
	var expList = new ExpressionListDTO();
	var exp= new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.HEAD,constants.HEADPAGETOOLBAR); //Create the Page tool Bar for head
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.HEADTABLELIST);//Create Table for Listing head
	dataTableProcessor.setCustomTable(self.pgTableName);
	headTableNavigator.setExp(expList);
	headTableNavigator.list();
	self.bindEvents();
	
}

 HeadUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEHEADUI,constants.HEADMODEL);		
		openModalBox(obj,constants.HEADMODEL);
		var newHeadUI = new NewHeadUI(self);
		newHeadUI.init();
		newHeadUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var headId=self.getSelectedHeadId(self.pgTableName);
		$j('#' + constants.HEAD + '-filter-cont').hide();
		$j(self.filter).hide();
		if(headId!="") {
			var viewHeadUI = self.getViewHeadUI();
			viewHeadUI.init(headId);
		}	
	});
	
	 self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var headId=self.getSelectedHeadId(self.pgTableName);
		if(headId!="") {
			var headService = self.getHeadService();
			var headResponse = headService.viewHead(headId);
			var confirmStatus = confirm(constants.HEADDELETECONFIRM + headResponse.headName);
			if(confirmStatus==true) {				
				var response = headService.deleteHead(headId);
				if(response.success==true) {
					showTip(constants.HEADDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var headTableNavigator = self.getHeadTableNavigator();
				headTableNavigator.list();
				
			}
		}	
	}); 
	 self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

HeadUIStartup.prototype.getSelectedHeadId = function () {
	var headId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selHead = $j(this.pgTableName + ' tbody tr[selected]');
		if(selHead.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTHEAD);
		} else if(selHead.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTHEADONLY);
		else
			headId=selHead.attr('id');
	}
	return headId;
}
HeadUIStartup.prototype.bindEvents = function() {
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
	   var headId= $j(this).parent().attr('id');
	   $j('#' + constants.HEAD + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(headId!="") {
			$j('#head-filter-wb').hide();
			var viewHeadUI = self.getViewHeadUI();
			viewHeadUI.init(headId);
		}	
	});
} 