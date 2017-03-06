function selectStatus() {
    	this.result = function () {
		var divTag = $j('<div/>'); // Create a  divtag [outer-1]
		var selectTag = $j('<select name="filterOperator" id="filterOperator" style="width:45%" class="small"/>'); // creating a select tag inside the tag
		var spanTag = $j('<span>&nbsp;&nbsp;</span>');
		var selectTag1 = $j('<select name="filterValue" id="filterValue" style="width:45%" class="small"/>'); 
		divTag.append(selectTag);
		divTag.append(spanTag);
		divTag.append(selectTag1);
		return divTag.html();
	}	
}