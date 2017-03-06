function createReportFooter(group) {
	var footer = $('<div id="printReportFooter" style="width: 210mm; height: 35mm"/>');
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
		footer.append(sepDiv);
	});
	return footer;
}