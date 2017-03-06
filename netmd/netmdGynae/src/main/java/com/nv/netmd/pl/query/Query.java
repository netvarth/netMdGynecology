/**
 * Query.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 11, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.pl.query;

/**
 * 
 */
public class Query {
	
	/** DoctorPracticeExperienceTbl **/
	public static final String GET_EXPERIENCE_BY_DOCTORID = "from DoctorPracticeExperienceTbl as experience where experience.doctorTbl.id= :param1";
	public static final String GET_EXPERIENCE = "from DoctorPracticeExperienceTbl as experience where experience.id=:param1 and experience.doctorTbl.id=:param2";

	/** DoctorEducationalQualificationTbl **/
	public static final String GET_QUALIFICATION = "from DoctorEducationalQualificationTbl as qualification where qualification.id=:param1 and qualification.doctorTbl.id=:param2 ";
	public static final String GET_QUALIFICATION_BY_DOCTORID = "from DoctorEducationalQualificationTbl as qualification where qualification.doctorTbl.id=:param1 ";

	/** PatientMedicalHistoryTbl **/
	public static final String GET_MEDICALHISTORY = "from PatientMedicalHistoryTbl as history where history.patientTbl.id=:param1";
	public static final String GET_PATIENT_MEDICAL_HISTORY = "from PatientMedicalHistoryTbl as history where history.id=:param1 and history.patientTbl.id=:param2";

	/** DoctorScheduleTbl **/
	public static final String GET_SCHEDULE_BY_DATE = "from DoctorScheduleTbl as schedule where schedule.date=:param1 and schedule.status='Active'";
	public static final String GET_SCHEDULE = "from DoctorScheduleTbl as schedule where schedule.doctorTbl.id=:param1 and schedule.date=:param2 and schedule.status='Active'";
	public static final String GET_SCHEDULE_BY_SERIES_ID = "from DoctorScheduleTbl as schedule where schedule.seriesTbl.id=:param1";
	public static final String GET_NEW_SCHEDULE = "from DoctorScheduleTbl as schedule where schedule.globalId=:param1 and schedule.status='Active'";
	public static final String GET_UPDATED_SCHEDULE = "from DoctorScheduleTbl as schedule where schedule.createdTime!=schedule.updatedTime and schedule.updatedTime>:param1 and schedule.status!='Inactive' and schedule.globalId!='0'";
	public static final String GET_DELETED_SCHEDULE = "from DoctorScheduleTbl as schedule where schedule.updatedTime>:param1 and schedule.status=:param2 and schedule.globalId!='0'";
	public static final String GET_SCHEDULE_BY_GLOBALID = "from DoctorScheduleTbl as schedule where schedule.globalId=:param1";
	public static final String GET_SCHEDULE_BY_DOCTOR="from DoctorScheduleTbl as schedule where schedule.doctorTbl.id=:param1 and schedule.status='Active'";

	/** PatientAppointmentTbl **/
	public static final String GET_APPOINTMENT = "from PatientAppointmentTbl as appointment where appointment.doctorTbl.id=:param1 and appointment.date=:param2 and appointment.startingTime=:param3 and appointment.status='Active'";
	public static final String GET_APPOINTMENT_BY_SCHEDULE = "from PatientAppointmentTbl as appointment where appointment.doctorScheduleTbl.id=:param1";
	public static final String GET_APPOINTMENT_LIST = "from PatientAppointmentTbl as appointment where appointment.doctorTbl.id=:param1 and appointment.date=:param2 and appointment.status='Active'";
	public static final String GET_NEW_APPOINTMENT = "from PatientAppointmentTbl as appointment where appointment.globalId=:param1 and appointment.status='Active'";
	public static final String GET_UPDATED_APPOINTMENT = "from PatientAppointmentTbl as appointment where appointment.createdTime!=appointment.updatedTime and appointment.updatedTime>:param1 and appointment.status!='Inactive' and appointment.globalId!='0'";
	public static final String GET_DELETED_APPOINTMENT = "from PatientAppointmentTbl as appointment where appointment.updatedTime>:param1 and appointment.status=:param2 and appointment.globalId!='0'";
	public static final String GET_APPOINTMENT_BY_GLOBALID = "from PatientAppointmentTbl as appointment where appointment.globalId=:param1 and appointment.status='Active'";
	public static final String GET_APPOINTMENT_BY_PATIENT = "from PatientAppointmentTbl as appointment where appointment.patientTbl.id=:param1 and appointment.status='Active'";
	

	/** LoginTbl */
	public static final String GET_LOGIN = "from LoginTbl as login where login.password = :param1 and login.userName=:param2";
	public static final String GET_USER_BY_PASSWORD = "from LoginTbl as login where login.password =:param1 and login.userName =:param2";
	public static final String GET_USER = "from LoginTbl as login where login.userName= :param1 and login.status='Active'";
	public static final String GET_ALL_USER_FROM_LOGIN = "from LoginTbl as login where login.userName= :param1";
	public static final String GET_LOGIN_TBL = "from LoginTbl";
	public static final String GET_DOCTOR_LOGIN = "from LoginTbl as login where login.id=:param1";
	/** DoctorTbl **/
	public static final String GET_DOCTOR_BY_LOGINID = "from DoctorTbl as doctor where doctor.loginTbl.id=:param1";
	public static final String GET_NEW_DOCTOR = "from DoctorTbl as doctor where doctor.globalId=:param1";
	public static final String GET_UPDATED_DOCTOR = "from DoctorTbl as doctor where doctor.createdTime!=doctor.updatedTime and doctor.updatedTime>:param1 and doctor.status!='Inactive' and doctor.globalId!='0'";
	public static final String GET_DELETED_DOCTOR = "from DoctorTbl as doctor where doctor.updatedTime>:param1 and doctor.status=:param2 and doctor.globalId!='0'";
	public static final String GET_DOCTOR_BY_GLOBALID = "from DoctorTbl as doctor where doctor.globalId=:param1";
	public static final String GET_DOCTOR_BY_EMAILID = "from DoctorTbl as doctor where doctor.email=:param1";
	
	/** CaseTbl **/
	public static final String GET_CASE_BY_PATIENTID = "from CaseTbl as ca where ca.patientTbl.id=:param1 order by date desc ";
	public static final String GET_CASE_BY_PATIENT = "from CaseTbl as ca where ca.patientTbl.id=:param1";
	
	/** MedicalRecordTbl **/
	public static final String GET_MEDICALRECORD_BY_PATIENTID = "from MedicalRecordTbl as record where record.patientTbl.id=:param1 order by createdTime desc";
	public static final String GET_PERSONALVISIT_BY_PATIENTID = "from MedicalRecordTbl as record where record.patientTbl.id=:param1 and TRIM(UPPER(record.type))=:param2 order by date desc";
	public static final String GET_MEDICALRECORD_BY_CASEID = "from MedicalRecordTbl as record where record.patientTbl.id=:param1 and record.caseTbl.id=:param2 order by date desc";
	public static final String GET_MEDRECORD_BY_CASEID = "from MedicalRecordTbl as record where record.caseTbl.id=:param1";
	public static final String GET_PATIENTS="from PatientTbl as patient where patient.status='Active'";
	public static final String GET_NEW_MEDICAL_RECORD = "from MedicalRecordTbl as record where record.globalId=:param1";
	public static final String GET_UPDATED_MEDICAL_RECORD = "from MedicalRecordTbl as record where record.createdTime!=record.updatedTime and record.updatedTime>:param1 and record.status!='Inactive' and record.globalId!='0'";
	public static final String GET_DELETED_MEDICAL_REOCRD = "from MedicalRecordTbl as record where  record.status=:param1 and record.updatedTime>:param2 and record.globalId!='0'";
	public static final String GET_MEDICAL_RECORD_BY_CASE = "from MedicalRecordTbl as record where record.caseTbl.id=:param1";
	/** ResultTbl **/
	public static final String GET_RESULT_BY_PATIENTID = "from ResultTbl as result where result.patientTbl.id=:param1";
	public static final String GET_RESULT_BY_GLOBALID = "from ResultTbl as result where result.globalId=:param1";
	
	/**DoctorExpertisetbl**/
	public static final String GET_EXPERTISE_BY_DOCTORID="from DoctorExpertiseTbl as expertise where expertise.doctorTbl.id=:param1 ";
	
	/**DoctorMembershiptbl**/
	public static final String GET_MEMBERSHIP_BY_DOCTORID="from DoctorMembershipTbl as membership where membership.doctorTbl.id=:param1";
	
	/**DoctorAchievementTbl**/
	public static final String GET_ACHIEVEMENT_BY_DOCTORID="from DoctorAchievementTbl as achievement where achievement.doctorTbl.id=:param1 ";
	
	/**SyncTbl**/
	public static final String GET_LAST_SYNC_TIME="from SyncTbl";
	
	/**NetmdPassphraseTbl**/
	public static final String GET_PASSPHRASE="from NetmdPassphraseTbl";
	
	/**PatientTbl**/
	public static final String GET_NEW_PATIENT = "from PatientTbl as patient where patient.globalId=:param1";
	public static final String GET_UPDATED_PATIENT = "from PatientTbl as patient where patient.createdTime!=patient.updatedTime and patient.updatedTime>:param1 and patient.status!='Inactive' and patient.globalId!='0'";
	public static final String GET_DELETED_PATIENT = "from PatientTbl as patient where patient.updatedTime>:param1 and patient.status=:param2 and patient.globalId!='0'";
	public static final String GET_PATIENT_BY_GLOBALID = "from PatientTbl as patient where patient.globalId=:param1";
	public static final String GET_PATIENT_BY_EMAILID = "from PatientTbl as patient where TRIM(patient.email)=:param1";
	public static final String GET_PATIENT_BY_EMAIL_NAME = "from PatientTbl as patient where TRIM(patient.email)=:param1 and TRIM(UPPER(patient.firstName)) =:param2";
	
	
	/** CaseTbl*/
	public static final String GET_NEW_CASE = "from CaseTbl as casetbl where casetbl.globalId=:param1";
	public static final String GET_UPDATED_CASE = "from CaseTbl as casetbl where casetbl.createdTime!=casetbl.updatedTime and casetbl.updatedTime>:param1 and casetbl.status!='Inactive' and casetbl.globalId!='0'";
	public static final String GET_DELETED_CASE = "from CaseTbl as casetbl where  casetbl.status=:param1 and casetbl.globalId!='0'";
	/**MessageTbl*/
	public static final String GET_NEW_MESSAGE="from MessageTbl as msg where msg.viewed=FALSE ORDER BY msg.id ASC";
	public static final String GET_NEW_MESSAGE_OF_DOCTOR="from MessageTbl as msg where msg.viewed=FALSE and msg.doctorTbl.id=:param1 ORDER BY msg.id ASC";
	
	/**SeriesTbl**/
	public static final String GET_SERIES_BY_GLOBALID="from SeriesTbl as series where series.globalId=:param1";
	
	/**DepartmentTbl**/
	public static final String GET_DEPARTMENT_BY_NAME="from DepartmentTbl as department where TRIM(UPPER(department.name))=:param1";
	public static final String GET_DEPARTMENTS="from DepartmentTbl as department where department.status='Active'";
	
	/**BlockTbl**/
	public static final String GET_BLOCK_BY_NAME="from BlockTbl as block where TRIM(UPPER(block.name))=:param1";
	public static final String GET_BLOCKS="from BlockTbl as block where block.status='Active'";
	
	/**BedTypeTbl**/
	public static final String GET_BED_BY_TYPE = "from BedTypeTbl as bedType where TRIM(UPPER(bedType.type))=:param1";
	public static final String GET_BEDTYPE = "from BedTypeTbl as bedType where bedType.status='Active'";
	
	/**BedTbl**/
	public static final String GET_BED_BY_NUMBER = "from BedTbl as bed where TRIM(UPPER(bed.bedNumber))=:param1";
	public static final String GET_BEDS = "from BedTbl as bed where bed.status='Active'";
	/**RoomTypeTbl**/
	public static final String GET_ROOMTYPE_BY_NAME="from RoomTypeTbl as roomType where TRIM(UPPER(roomType.type))=:param1";
	public static final String GET_ROOMTYPE="from RoomTypeTbl as roomType where roomType.status='Active'";
	
	/**RoomTbl**/
	public static final String GET_ROOM_BY_NAME="from RoomTbl as room where TRIM(UPPER(room.roomNumber))=:param1";
	public static final String GET_ROOM = "from RoomTbl as room where room.status='Active'";
	/**TaxTbl**/
	public static final String GET_TAX_BY_NAME="from TaxTbl as tax where TRIM(UPPER(tax.name))=:param1";
	public static final String GET_TAX="from TaxTbl  ";
	
	/**ItemTbl**/
	public static final String GET_ITEM_BY_NAME="from ItemTbl as item where TRIM(UPPER(item.name))=:param1";
	public static final String GET_ITEM="from ItemTbl as item where item.status='Active'";
	
	/**ServiceTbl**/
	public static final String GET_SUPPORT_BY_NAME="from SupportTbl as support where TRIM(UPPER(support.name))=:param1 and support.status='active'";
	public static final String GET_SUPPORT="from SupportTbl as support where support.status='Active'";
	/**DiscountTbl**/
	public static final String GET_DISCOUNT_BY_NAME="from DiscountTbl as discount where TRIM(UPPER(discount.name))=:param1";
	public static final String GET_DISCOUNTS = "from DiscountTbl as discount where discount.status='Active'";
	
	/**SequenceTbl**/
	public static final String GET_SEQUENCE="select seq from SequenceTbl as seq";
	
	/**BillTbl**/
	public static final String GET_BILL_BY_UID=" from BillTbl as bill where bill.uid=:param1";
	public static final String GET_BILL_BY_PATIENT_ID = "from BillTbl as bill where bill.patientTbl.id=:param1 and bill.origin='InPatient'and bill.billStatus='Open' ";
	//public static final String GET_BILL_PAYMENT_LIST = "from BillPaymentTbl as bill where bill.billTbl.uid=param1";
	public static final String GET_TOTAL_AMOUNT_PAID = "select sum(payment.amountPaid) from BillPaymentTbl as  payment where payment.billTbl.uid=:param1 group by payment.billTbl.uid" ;
	public static final String GET_GRAND_TOTAL = "select bill.billAmount from BillTbl as bill where bill.uid=:param1";
	public static final String GET_NEW_BILLS="from BillTbl as bill where bill.globalId=0";
	public static final String GET_UPDATED_BILLS = "from BillTbl as bill where bill.createdDateTime!=bill.updatedDateTime and bill.updatedDateTime>:param1 and bill.globalId!='0'";
	//public static final String GET_UPDATED_BILLS = "from BillTbl as bill where bill.updatedTime>:param1 and bill.globalId!='0'";
	public static final String GET_BILLS = "from BillTbl as bill";
	
	/**BillItemTbl**/
	public static final String GET_ITEM_BY_BILLID="from BillItemTbl as bill where bill.billTbl.id=:param1";
	public static final String GET_ITEM_BY_BILL_ITEM_ID="from BillItemTbl as bill where bill.itemTbl.id=:param1 and bill.billTbl.id=:param2";
	
	/**BillSupportTbl**/
	public static final String GET_SUPPORT_BY_BILLID="from BillSupportTbl as bill where bill.billTbl.id=:param1";
	public static final String GET_SUPPORT_BY_BILLID_SUPPORT_ID="from BillSupportTbl as bill where bill.supportTbl.id=:param1 and bill.billTbl.id=:param2";
	
	/**BillBedTbl**/
	public static final String GET_BED_BY_BILLID="from BillBedTbl as bill where bill.billTbl.id=:param1";
	public static final String GET_BED_BY_BILLID_BED_ID="from BillBedTbl as bill where bill.bedTbl.id=:param1 and bill.billTbl.id=:param2";
	
	/**BillDiscountTbl**/
	public static final String GET_DISCOUNT_BY_BILLID="from BillDiscountTbl as bill where bill.billTbl.id=:param1";
	public static final String GET_BILL_DISCOUNT="from BillDiscountTbl as bill where bill.discountTbl.id=:param1 and bill.billTbl.id=:param2";
	public static final String GET_OWNER_BY_LOGINID = "from NetmdTbl as netmd where netmd.loginTbl.id=:param1";
	
	/** HeadTbl**/
	public static final String GET_HEAD_BY_NAME = "from HeadTbl as head where TRIM(UPPER(head.headName))=:param1";
	public static final String GET_HEAD ="from HeadTbl as head";
	
	/** ExpenseTbl**/
	public static final String GET_EXPENSE_BY_NAME = "from ExpenseTbl as expense where TRIM(UPPER(expense.expenseName))=:param1";
	public static final String GET_EXPENSE = "from ExpenseTbl as expense";
	public static final String GET_PAYMENT_BY_EXPENSEID="from ExpensePaymentTbl as payment where payment.expenseTbl.id=:param1";
	/**SettingTbl**/	
	public static final String GET_SETTING_BY_NAME = "from SettingTbl as stting where TRIM(UPPER(stting.groupName))=:param1 ";
	public static final String GET_SETTINGS_BY_NAME = "select setting,settingList from SettingTbl as setting left join fetch setting.settingListTbls as settingList where setting.groupName=:param1 ";
	public static final String GET_SETTING_LIST_BY_SETTING_ID = "from SettingListTbl as settingList where settingList.settingTbl.id=:param1";
	public static final String GET_SETTING_LIST = "from SettingListTbl as settingList where settingList.settingTbl.id =:param1 and settingList.value =:param2";
	public static final String GET_SETTING_BY_ID = "from SettingTbl as setting where setting.id = :param1";
	public static final String GET_ORDER_TYPE="select setting from SettingTbl as setting left join fetch setting.settingListTbls as settingList where setting.groupName=:param1";
	public static final String GET_PAGESETTING_BY_NAME = "from LayoutSettingsTbl as setting where setting.key=:param1";
	public static final String  GET_PAGESETTINGS = "from LayoutSettingsTbl";
	public static final String GET_REPORT_TITLE="select setting,settingList from SettingTbl as setting left join fetch setting.settingListTbls as settingList where setting.groupName='ReportTitle'";
	public static final String GET_MAXITEMID_SETTING = "select max(itemId) from SettingListTbl as settingListTbl where settingListTbl.settingTbl.id=:param1";	
	/**QuestionTbl**/
	//public static final String GET_BY_DEPT = "from QuestionTbl as qTbl where qTbl.departmentTbl.id=:param1 and qTbl.departmentQuestionnaireTbl.id=:param2";
	public static final String GET_BY_DEPT = "from QuestionTbl as qTbl where qTbl.departmentQuestionnaireTbl.id=:param2";
	public static final String GET_BY_CASE = "from AnswerTbl as answer where answer.caseTbl.id=:param1";
	public static final String GET_DELIVERY_DATE_FROM_ANSWER_TBL = "from AnswerTbl as answer where answer.caseAnswerSetTbl.id=:param1 and answer.questionTbl.id=:param2";
	public static final String GET_NETMD_QUESTION_TBL = "from QuestionTbl";
	public static final String GET_ANSLIST_BY_ANSSET_ID = "from AnswerTbl as answer where answer.caseAnswerSetTbl.id=:param1";
	//public static final String GET_NEW_QUESTIONNAIRE = "from NetmdAntenatalTbl as ntmdAnteTbl where ntmdAnteTbl.globalId=:param1";
	//public static final String GET_UPDATED_QUESTIONNAIRE = "from NetmdAntenatalTbl as ntmdAnteTbl where ntmdAnteTbl.createdTime!=ntmdAnteTbl.updatedTime and ntmdAnteTbl.updatedTime>:param1 and ntmdAnteTbl.globalId!='0'";
	public static final String GET_QUESTIONNAIRE_LIST = "from CaseAnswerSetTbl as caseAnsTbl where caseAnsTbl.syncStatus=:param1";
	public static final String GET_MANDATORY_ANSWER_LIST = "from AnswerTbl as answertbl where answertbl.questionTbl.questionKey=:param1 and answertbl.caseAnswerSetTbl.id=:param2";
	public static final String GET_MANDATORY_QUESTION_LIST = "from QuestionTbl as question where question.mandatory=:param1";
	public static final String GET_ANSWER_SET_ID = "from CaseAnswerSetTbl where caseTbl.id=:param1";
	public static final String GET_ANSWER_SET_WITH_ID = "from CaseAnswerSetTbl as caseAnsTbl where caseAnsTbl.id=:param1";
	//public static final String GET_ANSWER_BY_QSTN="from AnswerTbl as answer where answer.index=:param1 and answer.caseAnswerSetTbl.id=:param2";
	public static final String GET_ANSWER_BY_QSTNINDX_CASEID ="from AnswerTbl as answer where answer.indexKey=:param1 and answer.caseTbl.id=:param2";
	public static final String GET_ANSWER_BY_QUESTION_INDEX_ANSWERSETID = "from AnswerTbl as answer where answer.indexKey=:param1 and answer.caseAnswerSetTbl.id=:param2";
	public static final  String GET_ANSWER_SET_WITH_INQUEUE_STATUS="from CaseAnswerSetTbl as caseAnsTbl where caseAnsTbl.syncStatus=:param1";
	/**BillPaymentTbl**/
	public static final String GET_PAYMENT_BY_BILLID="from BillPaymentTbl as payment where payment.billTbl.id=:param1";
	
    /**logo**/
	public static final String GET_LOGO = "from NetmdTbl as netmd";
	public static final String GET_NETMD_BY_GLOBALID ="from NetmdTbl as netmd where netmd.globalId=:param1";
	public static final String GET_OBSTETRICS_RECORD = "from DepartmentTbl as dept where dept.name=:param1" ;
	
	
	
	
	
	
	
	
	
}
