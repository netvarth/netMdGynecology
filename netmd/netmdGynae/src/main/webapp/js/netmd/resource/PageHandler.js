function PageHandler() {
	this.getHomePageUrl=function() {
		return "/netmd/ws/ui/html/startUp";
	}
	
	this.create = function(url) {
		ajaxProcessor.setUrl(url);
		var pageContent = ajaxProcessor.get();
		var page = new form(pageContent);
		$j('#tabs-1').html(page.result);
	}
	
	this.buttons = function(url, parent) {
		ajaxProcessor.setUrl(url);
		var response = ajaxProcessor.post();
		var buttonsDiv = new buttonsContainer(response);
		$j(parent).html(buttonsDiv.result);
	}
	this.getHomePage = function() {
		return this.homePage;
	}
	this.setHomePage = function(homePage) {
		this.homePage = homePage;
	}
}