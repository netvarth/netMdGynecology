function reportModal(modaldata,title) {
	this.result = function() {
		var mainDiv = $j('<div/>');
		var modalTitleDiv = $j('<div class="reveal-modal-head"/>'); // Create the  header div for the Modal(title)
		var headtitle = '<h1>' + title + '</h1>';
		modalTitleDiv.html(headtitle); // give value to the title
		mainDiv.append(modalTitleDiv); // Add the div inside the form Container
		var modalContentDiv = $j('<div class="reveal-modal-content"/>'); // Create the  Content div for the Modal			
		var boxDiv = $j('<div/>');
		boxDiv.attr('class','box');
		var  boxcontentDiv = $j('<div/>');
		boxcontentDiv.attr('class','box-content');
		boxcontentDiv.attr('style','padding:5px 0% 5px 15%');
		boxcontentDiv.append(modaldata);
		boxDiv.append(boxcontentDiv);
		modalContentDiv.append(boxDiv);
		mainDiv.append(modalContentDiv);
		var CloseModalTag = $j('<a class="close-reveal-modal">&#215;</a>');
		mainDiv.append(CloseModalTag); // Appending the a tag for closing the modal box
		return mainDiv;
	};
}

