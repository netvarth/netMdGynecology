
		
		// ResultSetting validator JS 
		$j.getScript("/netmd/js/netmd/validation/SettingValidator.js").done(function(script, textStatus){
		}).fail(function (xhr, status, exception)
		{
			alert("SettingValidator" + xhr.status + exception);
		})
		// ResultSetting UI Pages 
		$j.getScript("/netmd/js/netmd/ui/setting/SettingUIStartup.js").done(function(script,textStatus){
		}).fail(function (xhr, status, exception)
		{
			alert("SettingUIStartup" + xhr.status + exception);
		})
		/$j.getScript("/netmd/js/netmd/ui/setting/NewSettingUI.js").done(function(script,textStatus){
		}).fail(function (xhr, status, exception)
		{
			alert("NewSettingUI" + xhr.status + exception);
		})
		$j.getScript("/netmd/js/netmd/ui/setting/ViewSettingUI.js").done(function(script,textStatus){
		}).fail(function (xhr, status, exception)
		{
			alert("ViewSettingUI" + xhr.status + exception);
		})
		$j.getScript("/netmd/js/netmd/ui/setting/ViewSettingPTB.js").done(function(script,textStatus){
		}).fail(function (xhr, status, exception)
		{
			alert("ViewSettingPTB" + xhr.status + exception);
		})
		$j.getScript("/netmd/js/netmd/ui/setting/NewResultSettingUI.js").done(function(script,textStatus){
		}) .fail(function (xhr, status, exception)
		{
			alert("NewResultSettingUI" + xhr.status + exception);
		})
