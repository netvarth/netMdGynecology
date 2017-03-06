$j(document).ready(function(){
		$j(".headright #dropdownOne #dd").die('click').live("click",function() {
			$j(this).toggleClass('active');
	
	     });
		 $j("#btnLogout").die('click').live("click",function() {
				var response = getRequestData('/netmd/ws/ui/auth/logout');
					window.location.href=serverPath + "/netmd/ws/ui/html/startUp";
					location.reload();
	     });
});