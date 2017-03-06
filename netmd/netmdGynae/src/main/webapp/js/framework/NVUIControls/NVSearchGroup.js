function searchGroup() {
	this.result = function () {
		var divTag = $j('<div/>'); // Create a  divtag [outer-1]
		var selectTag = $j('<select name="filterOperator" id="filterOperator" style="width:45%" class="small"/>'); // creating a select tag inside the tag
		var spanTag = $j('<span>&nbsp;&nbsp;</span>');
		var inputTag = $j('<input name="filterValue" id="filterValue" style="width:49%" class="large"/>');
		divTag.append(selectTag);
		divTag.append(spanTag);
		divTag.append(inputTag);	
		return divTag.html();
	}	
}