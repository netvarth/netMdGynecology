function resultModal(modaldata,testName) {
	this.result = function() {
		var mainDiv = $j('<div/>');
		var modalTitleDiv = $j('<div class="reveal-modal-head"/>'); // Create the  header div for the Modal(title)
		var headtitle = '<h1>Result -' + testName + '</h1>';
		modalTitleDiv.html(headtitle); // give value to the title
		mainDiv.append(modalTitleDiv); // Add the div inside the form Container
		var modalContentDiv = $j('<div class="reveal-modal-content"/>'); // Create the  Content div for the Modal	
		$j.getScript("/weblims/js/NVUIControls/NVResultForm.js").done(function(script, textStatus) {
			var contentForm = new resultform(modaldata);
			modalContentDiv.append(contentForm.result);
		});
		mainDiv.append(modalContentDiv);
		var CloseModalTag = $j('<a class="close-reveal-modal">&#215;</a>');
		mainDiv.append(CloseModalTag); // Appending the a tag for closing the modal box
		return mainDiv;
	};
}

