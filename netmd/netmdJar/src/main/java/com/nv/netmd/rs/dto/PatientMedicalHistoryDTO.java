package com.nv.netmd.rs.dto;

public class PatientMedicalHistoryDTO {
	private int id;
	private int patientId;
	private String medicalIssue;
	private int diagonisedAge;
	private String tenure;
	private String treatment;
	private String surgery;
	private String medication;
	private String isCured;
	private String actionName;

	/**
	 * @param id
	 * @param patientId
	 * @param medicalIssue
	 * @param diagonisedAge
	 * @param tenure
	 * @param treatment
	 * @param surgery
	 * @param medication
	 * @param isCured
	 * @param actionName
	 */
	public PatientMedicalHistoryDTO(int id, int patientId,
			String medicalIssue, int diagonisedAge, String tenure,
			String treatment, String surgery, String medication,
			String isCured, String actionName) {
		super();
		this.id = id;
		this.patientId = patientId;
		this.medicalIssue = medicalIssue;
		this.diagonisedAge = diagonisedAge;
		this.tenure = tenure;
		this.treatment = treatment;
		this.surgery = surgery;
		this.medication = medication;
		this.isCured = isCured;
		this.actionName = actionName;
	}

	public int getId() {
		return id;
	}

	public PatientMedicalHistoryDTO() {
		super();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getMedicalIssue() {
		return medicalIssue;
	}

	public void setMedicalIssue(String medicalIssue) {
		this.medicalIssue = medicalIssue;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public String getSurgery() {
		return surgery;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public String getMedication() {
		return medication;
	}

	public void setMedication(String medication) {
		this.medication = medication;
	}

	public String getIsCured() {
		return isCured;
	}

	public void setIsCured(String isCured) {
		this.isCured = isCured;
	}

	/**
	 * @return the diagonisedAge
	 */
	public int getDiagonisedAge() {
		return diagonisedAge;
	}

	/**
	 * @param diagonisedAge
	 *            the diagonisedAge to set
	 */
	public void setDiagonisedAge(int diagonisedAge) {
		this.diagonisedAge = diagonisedAge;
	}

	/**
	 * @return the actionName
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * @param actionName
	 *            the actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
}
