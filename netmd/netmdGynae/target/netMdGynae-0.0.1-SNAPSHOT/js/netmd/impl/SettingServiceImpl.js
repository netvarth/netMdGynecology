function SettingServiceImpl () {
	this.setTableValues = function(tableObj, settingResult) {   
	//alert(JSON.stringify(settingResult));
		$j(tableObj).dataTable().fnClearTable();
		if(settingResult.setting.length>0) {			
			$j(settingResult.setting).each(function(index, setting) {
				var id=setting.uid;
				var name=setting.groupName;
				var value="";
				$j(setting.setting).each(function(index,subsetting){
					value=value+subsetting.value+'<br/>';
				});
				var rowData=$j(tableObj).dataTable().fnAddData([id,name,value]);
				var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
				$j(row).attr('id',id);
				$j(row).children("td:nth-child(1)").attr("class","settingIdCol Ustyle");	
			});
		}							
	}
}

SettingServiceImpl.prototype.createSetting=function(settingObj) {
	ajaxProcessor.setUrl(constants.CREATESETTINGURL);
	return ajaxProcessor.post(settingObj);
}
SettingServiceImpl.prototype.updateSetting=function(settingObj) {
	ajaxProcessor.setUrl(constants.UPDATESETTINGURL);
	return ajaxProcessor.post(settingObj);
}
SettingServiceImpl.prototype.deleteSetting=function(settingObj) {
	ajaxProcessor.setUrl(constants.DELETESETTINGURL + settingObj);
	return ajaxProcessor.get();
}
SettingServiceImpl.prototype.viewSetting=function(settingObj) {
	ajaxProcessor.setUrl(constants.VIEWSETTINGURL + settingObj);
	return ajaxProcessor.get();
} 
