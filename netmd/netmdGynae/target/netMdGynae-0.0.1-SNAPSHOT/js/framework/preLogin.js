$j = jQuery.noConflict();
$j.ajaxSetup({async:false});
$j.cachedScript = function(url, options) {
  // allow user to set any option except for dataType, cache, and url
  options = $j.extend(options || {}, {
    dataType: "script",
    cache: true,
    url: url
  });
  // Use $j.ajax() since it is more flexible than $j.getScript
  // Return the jqXHR object so we can chain callbacks
  return $j.ajax(options);
};

$j.cachedScript("/netmd/js/framework/login.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/framework/logout.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/constants.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/netmd/js/framework/RequestHandler.js").done(function(script, textStatus) {
}) 
$j.cachedScript("/netmd/js/framework/general.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/common.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/netmd/common/ribbonEventHandler.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/framework/invokeUIControls.js").done(function(script, textStatus) {
})
$j.cachedScript("/netmd/js/framework/createModal.js").done(function(script, textStatus) {
})

$j(document).ready(function(){

	$j("#Passphrase").bind("keypress", function (e) {
		if (e.keyCode == 13) {
			if($j("#Passphrase").val()=="")
				return false;
			$j("#PassphraseBtnLogin").trigger('click');
		}		
	});
	
	$j('#PassphraseBtnLogin').die('click').live("click",function(){
		if(validate()) {
		var  phrase=$j('#Passphrase').val();		
		var loginData='{"passPhrase":"'+phrase+'"}';
		var response = postdataToServer("/netmd/ws/ui/sync/activateNetMd",loginData);
		location.reload();
			if(response.success==false){
	            errorTips("Activation Failed",'',$j('#loginError'));
		   }
		}	 
	});
		
	
	function  errorTips(t,obj,errorDiv) {
		errorDiv.attr('style','color:#ff0000');
		errorDiv.text(t);
		setTimeout(function() {
			if(obj!='') {
				obj.focus();
				obj.removeClass('error');
				obj.removeAttr('style');
			}
			errorDiv.text("");
		}, 5000 );
	}
	
	function validate() {
		var pass = $j('#Passphrase');
		var bValid=true;
		bValid = bValid && checkNull( pass, "Passphrase", $j('#loginError'));
		return bValid;
	}
	function checkNull(o,n, errorDiv){
		if(o.val().length=='0'){
			o.addClass("error");
			errorTips( "* " + n + " must not be null" ,o ,errorDiv);
			return false;	
		} 
		else 
			return true;
	}
	
	});	
