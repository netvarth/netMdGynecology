function DoctorServiceImpl () {
	
	this.setTableValues = function(tableObj, doctorResult) {
		$j(tableObj).dataTable().fnClearTable();
		 if(doctorResult.referral) {
			if(doctorResult.referral.length>0) {			
				$j(doctorResult.referral).each(function(index, referral) {
					var mobile;
					var id=referral.id;
					var name=referral.firstName;
					name = name.toLowerCase().replace(/\b[a-z]/g, function(letter) {
					return letter.toUpperCase();
					});
					var specialization=referral.specialization;
					var email=referral.email;
					var mobile=referral.mobile;
					var rowData=$j(tableObj).dataTable().fnAddData([id,name,specialization,mobile,email]);
					var row=$j(tableObj).dataTable().fnSettings().aoData[rowData].nTr;
					$j(row).attr('id',id);
					$j(row).children("td:nth-child(1)").attr("class","doctorIdCol Ustyle");
				});
			}		
		} 
	}
	
}

DoctorServiceImpl.prototype.createDoctor=function (doctorObj) {
	ajaxProcessor.setUrl(constants.DOCTORCREATEURL);
	return ajaxProcessor.post(doctorObj);
}
DoctorServiceImpl.prototype.updateDoctor=function(doctorObj) {
	ajaxProcessor.setUrl(constants.DOCTORUPDATEURL);
	return ajaxProcessor.post(doctorObj);
}
DoctorServiceImpl.prototype.deleteDoctor=function(doctorId) {
	ajaxProcessor.setUrl(constants.DOCTORDELETEURL + doctorId);
	return ajaxProcessor.get();
}
DoctorServiceImpl.prototype.viewDoctor=function(doctorId) {
	ajaxProcessor.setUrl(constants.DOCTORVIEWURL + doctorId);
	return ajaxProcessor.get();
}