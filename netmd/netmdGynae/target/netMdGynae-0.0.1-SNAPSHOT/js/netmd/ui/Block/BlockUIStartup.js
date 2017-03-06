function BlockUIStartup() {
	this.pgTableName = "#block";
	this.pgTableContainer="#blockListTableCont"; 
	this.pageTitle = $j('#pageTitle');
	this.ptbCreate=$j('#blockPTBContainer #btn_new_ptb_id');
	this.ptbView=$j('#blockPTBContainer #btn_view_ptb_id');
	this.ptbDelete=$j('#blockPTBContainer #btn_delete_ptb_id');
	this.ptbHome=$j('#blockPTBContainer #btn_home_ptb_id');
	this.errorData = $j('#errorDivData');
	this.errorHeader = $j('#errorDivHeader');
	this.pgTableRowClass = '.blockIdCol';
	this.exp = new ExpressionListDTO();
	this.blockService = new BlockServiceImpl();
	this.listUrl = constants.BLOCKLISTURL;
	this.blockTableNavigator = new DataTableNavigator(this.pgTableName,this.listUrl,this.pgTableContainer,this.blockService,this.exp);
	this.ftbToolBar = '#block-filter-toolbar';
	this.filter = '#filter';
	this.viewBlockUI = new ViewBlockUI(this);
}

BlockUIStartup.prototype.setBlockTableNavigator = function(blockTableNavigator) {
	this.blockTableNavigator = blockTableNavigator;
}
BlockUIStartup.prototype.getBlockService = function() {
	return this.blockService;
}
BlockUIStartup.prototype.getBlockTableNavigator = function() {
	return this.blockTableNavigator;
}
BlockUIStartup.prototype.setPageTitle = function(value) {
	this.pageTitle.html(value);
}
BlockUIStartup.prototype.getViewBlockUI = function() {
	return this.viewBlockUI;
} 

BlockUIStartup.prototype.init = function() {
	var self = this;
	var expList=new ExpressionListDTO();
	var blockTableNavigator = self.getBlockTableNavigator();
	self.setPageTitle(constants.BLOCKTITLE);
	var exp = new ExpressionDTO("status","active","eq");
	expList.add(exp);
	var ptbProcessor = new PageToolBarProcessor();
	ptbProcessor.getPageToolBar(constants.BLOCK,constants.BLOCKPAGETOOLBAR); //Create the Page tool Bar for Area
	self.bindToolBarEvents();
	dataTableProcessor.create(self.pgTableName,constants.BLOCKLISTTABLEURL);//Create Table for Listing Area
	dataTableProcessor.setCustomTable(self.pgTableName);
	blockTableNavigator.setExp(expList);
	blockTableNavigator.list();
	self.bindEvents();
	
}

 BlockUIStartup.prototype.bindToolBarEvents = function() {
	var self=this;
	self.ptbCreate.die('click').live('click',function() {
		var obj=$j(this);
		removeErrors();
		createModal(constants.CREATEBLOCKUI,constants.BLOCKMODAL);		
		openModalBox(obj,constants.BLOCKMODAL);
		var newBlockUI = new NewBlockUI(self);
		newBlockUI.bindEvents();
	});
	
	self.ptbView.die('click').live('click',function() {
		removeErrors();
		var blockId=self.getSelectedBlockId(self.pgTableName);
		$j('#' + constants.BLOCK + '-filter-cont').hide();
		$j(self.filter).hide();
		if(blockId!="") {
			var viewBlockUI = self.getViewBlockUI();
			viewBlockUI.init(blockId);
		}	
	});
	
	self.ptbDelete.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();;
		var blockId=self.getSelectedBlockId(self.pgTableName);
		if(blockId!="") {
			var blockService = self.getBlockService();
			var blockResponse = blockService.viewBlock(blockId);
			var confirmStatus = confirm(constants.BLOCKDELETECONFIRM + blockResponse.name);
			if(confirmStatus==true) {				
				var response = blockService.deleteBlock(blockId);
				if(response.success==true) {
					showTip(constants.BLOCKDELETESUCCESS);//For showing the global Tip
				} else {
					commonMethodInvoker.createServerError(self.errorHeader,self.errorData, commonMethodInvoker.getErrorName(areaResponse.error));
				}
				var blockTableNavigator = self.getBlockTableNavigator();
				blockTableNavigator.list();
				
			}
		}	
	});
	self.ptbHome.die('click').live('click',function() {
		commonMethodInvoker.removeErrors();
		$j.cachedScript("/netmd/js/netmd/admin/listAdminOptions.js").done(function(script, textStatus) {
		})
	}); 
	}

BlockUIStartup.prototype.getSelectedBlockId = function () {
	var blockId="";
	if($j(this.pgTableName).dataTable().fnGetData().length>0) {
		var selBlock = $j(this.pgTableName + ' tbody tr[selected]');
		if(selBlock.length==0){
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBLOCK);
		} else if(selBlock.length>1) 
			commonMethodInvoker.createServerError(self.errorHeader,self.errorData, constants.SELECTONEBLOCKONLY);
		else
			blockId=selBlock.attr('id');
	}
	return blockId;
}
BlockUIStartup.prototype.bindEvents = function() {
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
	   var blockId= $j(this).parent().attr('id');
	   $j('#' + constants.BLOCK + '-filter-cont').hide();
	   $j(self.filter).hide();
		if(blockId!="") {
			$j('#block-filter-wb').hide();
			var viewBlockUI = self.getViewBlockUI();
			viewBlockUI.init(blockId);
		}	
	});
} 