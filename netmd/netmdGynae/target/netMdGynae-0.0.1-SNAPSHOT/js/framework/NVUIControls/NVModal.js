function Modal(modaldata,model) {
	this.result = function() {
		var mainDiv = $j('<div/>');
		var modalTitleDiv = $j('<div class="reveal-modal-head"/>'); // Create the  header div for the Modal(title)
		modalTitleDiv.html('<h1>' + modaldata.title + '</h1>'); // give value to the title
		mainDiv.append(modalTitleDiv); // Add the div inside the form Container
		var modalContentDiv = $j('<div class="reveal-modal-content"/>'); // Create the  Content div for the Modal
		$j(modaldata.data).each(function(index,myform) { //iterate for each form
			var contentForm = new form(myform,model);
			modalContentDiv.append(contentForm.result);
		});
		mainDiv.append(modalContentDiv);
		var CloseModalTag = $j('<a class="close-reveal-modal">&#215;</a>');
		mainDiv.append(CloseModalTag); // Appending the a tag for closing the modal box
		return mainDiv;
	};
}

