
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

