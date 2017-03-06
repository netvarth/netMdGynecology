
$j(document).ready(function(){
	$j(".accordion").die ('click').live('click', function() {
		var source = $j(this).children('th').children('img');
		if(source.attr('name')=="expand") {
			source.attr('src','/netmd/images/collapse.jpg');
			source.attr('name','collapse');
			source.parents('thead').next('tbody').show();
		}
		else {
			 source.attr('src','/netmd/images/expand.jpg');
			 source.attr('name','expand');
			 source.parents('thead').next('tbody').hide();
			}  
	});	
});

function isIndexExists(source, index) {
	var foundStatus = false;
	for(ind = 0; ind<source.length;ind++){
		if(source[ind]==index) {
			foundStatus=true;
		}	
	}	
	return foundStatus;
}

function isEqual(value1,value2){
	var stat = false;
	if(value1==value2)
		stat = true;
	return stat;
}
