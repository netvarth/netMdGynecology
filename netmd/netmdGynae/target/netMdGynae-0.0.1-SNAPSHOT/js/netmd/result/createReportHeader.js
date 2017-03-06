function createReportHeader(group) {
	var header = $('<div id="printReportHeader" style="width: 210mm; height: 50mm"/>');
	$.getScript("/weblims/js/NVUIControls/NVContainer.js").done(function(script, textStatus) {
		var sepDiv = $('<div/>');
		if (group.className) sepDiv.attr('class',group.className);
		if (group.id) sepDiv.attr('id',group.id);
		if (group.style) sepDiv.attr('style',group.style);
		var boxDiv = $('<div/>');
		$(group.data).each(function(index,subgroup) {
			var cont = new Container(subgroup);
			boxDiv.append(cont.result);
		});
		sepDiv.append(boxDiv);
		header.append(sepDiv);
	});
	return header;
}