$j('#pageTitle').html("Settings");
var ptbContainer = $j('<div id="settingPTBContainer"/>');
$j('#pageToolBar-Container').html(ptbContainer);
response = getRequestData('/netmd/json/toolbars/adminToolBar.json');
var adminTB = new AdminToolBar(response);
$j('#tabs-1').html(adminTB.result);			
$j.cachedScript("/netmd/js/netmd/admin/adminOperations.js").done(function(script, textStatus) {
})
