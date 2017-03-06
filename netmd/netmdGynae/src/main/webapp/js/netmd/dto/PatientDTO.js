function PatientDTO() {

}

function PatientDTO(id,firstName,lastName,phone,mobile,dob,gender,email,age,education,address,bloodGroup,rh,ailment,chronicDisease,diagnosis,familyHistory,allergies,medicalHistory,caseResponseList) {
	this.id=id;
	this.firstName=firstName;
	this.lastName=lastName;
	this.phone=phone;
	this.mobile=mobile;
	this.dob=dob;
	this.gender=gender;
	this.email=email;
	this.age=age;
	this.education=education;
	this.address=address;
	this.bloodGroup=bloodGroup;
	this.rh=rh;
	this.ailment=ailment;
	this.chronicDisease=chronicDisease;
	this.diagnosis=diagnosis;
	this.familyHistory=familyHistory;
	this.allergies=allergies;
	this.medicalHistory=medicalHistory;
	this.caseResponseList=caseResponseList;
}

PatientDTO.prototype.setId = function(id) {
	this.id=id;
}
PatientDTO.prototype.getId = function() {
	return this.id;
}
PatientDTO.prototype.setFirstName = function(firstName) {
	this.firstName=firstName;
}
PatientDTO.prototype.getFirstName = function() {
	return this.firstName;
}
PatientDTO.prototype.setLastName = function(lastName) {
	this.lastName=lastName;
}
PatientDTO.prototype.getLastName = function() {
	return this.lastName;
}
PatientDTO.prototype.setPhone = function(phone) {
	 this.phone=phone;
}
PatientDTO.prototype.getPhone = function() {
	return this.phone;
}

PatientDTO.prototype.setMobile = function(mobile) {
	 this.mobile=mobile;
}
PatientDTO.prototype.getMobile = function() {
	return this.mobile;
}

PatientDTO.prototype.setDob = function(dob) {
	 this.dob=dob;
}
PatientDTO.prototype.getDob  = function() {
	return this.dob;
}

PatientDTO.prototype.setGender = function(gender) {
	 this.gender=gender;
}
PatientDTO.prototype.getGender = function() {
	return this.gender;
}

PatientDTO.prototype.setEmail = function(email) {
	 this.email=email;
}
PatientDTO.prototype.getEmail  = function() {
	return this.email;
}
PatientDTO.prototype.setAge = function(age) {
	 this.age=age;
}
PatientDTO.prototype.getAge  = function() {
	return this.age;
}
PatientDTO.prototype.setEducation = function(education) {
	 this.education=education;
}
PatientDTO.prototype.getEducation  = function() {
	return this.education;
}
PatientDTO.prototype.setAddress= function(address) {
	 this.address=address;
}
PatientDTO.prototype.getAddress  = function() {
	return this.address;
}
PatientDTO.prototype.setBloodGroup= function(bloodGroup) {
	 this.bloodGroup=bloodGroup;
}
PatientDTO.prototype.getBloodGroup  = function() {
	return this.bloodGroup;
}
PatientDTO.prototype.setRh= function(rh) {
	 this.rh=rh;
}
PatientDTO.prototype.getRh  = function() {
	return this.rh;
}
PatientDTO.prototype.setAilment= function(ailment) {
	 this.ailment=ailment;
}
PatientDTO.prototype.getAilment  = function() {
	return this.ailment;
}
PatientDTO.prototype.setChronicDisease= function(chronicDisease) {
	 this.chronicDisease=chronicDisease;
}
PatientDTO.prototype.getChronicDisease  = function() {
	return this.chronicDisease;
}
PatientDTO.prototype.setDiagnosis= function(diagnosis) {
	 this.diagnosis=diagnosis;
}
PatientDTO.prototype.getDiagnosis  = function() {
	return this.diagnosis;
}
PatientDTO.prototype.setFamilyHistory= function(familyHistory) {
	 this.familyHistory=familyHistory;
}
PatientDTO.prototype.getFamilyHistory  = function() {
	return this.familyHistory;
}

PatientDTO.prototype.setAllergies= function(allergies) {
	 this.allergies=allergies;
}
PatientDTO.prototype.getAllergies  = function() {
	return this.allergies;
}

PatientDTO.prototype.setMedicalHistory= function(medicalHistory) {
	 this.medicalHistory=medicalHistory;
}
PatientDTO.prototype.getMedicalHistory  = function() {
	return this.medicalHistory;
}
PatientDTO.prototype.setCaseResponseList= function(caseResponseList) {
	 this.caseResponseList=caseResponseList;
}
PatientDTO.prototype.getCaseResponseList  = function() {
	return this.caseResponseList;
}
