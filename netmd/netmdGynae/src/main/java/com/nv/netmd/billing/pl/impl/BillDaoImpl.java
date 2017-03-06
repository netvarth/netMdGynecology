/**
 * BillDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.pl.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.billing.pl.dao.BillDao;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.BedTbl;
import com.nv.netmd.pl.entity.BillBedTbl;
import com.nv.netmd.pl.entity.BillDiscountTbl;
import com.nv.netmd.pl.entity.BillItemTbl;
import com.nv.netmd.pl.entity.BillPaymentTbl;
import com.nv.netmd.pl.entity.BillStatusEnum;
import com.nv.netmd.pl.entity.BillSupportTbl;
import com.nv.netmd.pl.entity.BillTbl;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.DiscountTbl;
import com.nv.netmd.pl.entity.DiscountTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.pl.entity.NetmdTbl;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.PayStatusEnum;
import com.nv.netmd.pl.entity.SequenceTbl;
import com.nv.netmd.pl.entity.SupportTbl;
import com.nv.netmd.pl.entity.SyncTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.BillBedDTO;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillHeaderDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.BillItemListDTO;
import com.nv.netmd.rs.dto.BillListResponseDTO;
import com.nv.netmd.rs.dto.BillPaymentDTO;
import com.nv.netmd.rs.dto.BillResponseDTO;
import com.nv.netmd.rs.dto.BillStatusDTO;
import com.nv.netmd.rs.dto.BillSummaryDTO;
import com.nv.netmd.rs.dto.BillSupportDTO;
import com.nv.netmd.rs.dto.BillSyncResponseDTO;
import com.nv.netmd.rs.dto.BillViewResponseDTO;
import com.nv.netmd.rs.dto.NetMdBranchDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.PatientDetail;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;



/**
 *
 *
 * @author Sreeram T G
 */
public class BillDaoImpl extends GenericDaoHibernateImpl implements BillDao {

	@PersistenceContext()
	private EntityManager em;
	/**
	 * create bill
	 * @param bill
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(BillDTO bill) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();

		PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class,bill.getPatientId());
		if(patientTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.PatientNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		BillTbl billTable=(BillTbl)getBillByPatientId(bill.getPatientId());
		if(billTable!=null){
			 PersistenceException se=new  PersistenceException(ErrorCodeEnum.PatientBillAlreadyExists);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		BillTbl billTbl=new BillTbl();
		billTbl.setPatientTbl(patientTbl);
		
		int seqId=getNextSequenceId(bill.getOrigin());
		
		if(bill.getOrigin().equals(OriginEnum.InPatient.getDisplayName()))
		{
		    billTbl.setUid(Constants.IN+Integer.toString(seqId));
		}
		if(bill.getOrigin().equals(OriginEnum.OutPatient.getDisplayName()))
		{
			billTbl.setUid(Constants.OUT+Integer.toString(seqId));
		}

		billTbl.setCurrency(bill.getCurrency());
		billTbl.setReferralName(bill.getReferralName());
		billTbl.setCreatedOn(new Date());
		billTbl.setNote(bill.getNotes());
		billTbl.setBillAmount(bill.getBillAmount());
		OriginEnum originEnum=OriginEnum.getEnum(bill.getOrigin());
		billTbl.setOrigin(originEnum);
		billTbl.setGrandTotal(bill.getGrandTotal());
		
		/*if(bill.getOrigin().equals(OriginEnum.OutPatient.getDisplayName()))
			billTbl.setBillStatus(BillStatusEnum.Closed.getDisplayName());
		else
			billTbl.setBillStatus(BillStatusEnum.Open.getDisplayName());*/
	
		if(bill.getOrigin().equals(OriginEnum.OutPatient.getDisplayName())){
			if(bill.getAmountPaid()>bill.getBillAmount()){
				billTbl.setPaidAmount(bill.getBillAmount());
				billTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
				billTbl.setBillStatus(BillStatusEnum.Closed);
			}
			else
				billTbl.setPaidAmount(bill.getAmountPaid());
			
			if(bill.getAmountPaid()==bill.getBillAmount()){
				billTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
				billTbl.setBillStatus(BillStatusEnum.Closed);
			}
		}
		else{
			billTbl.setPaidAmount(bill.getAmountPaid());					
			if(bill.getAmountPaid()==bill.getBillAmount() || bill.getAmountPaid()>bill.getBillAmount())
				billTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
				billTbl.setBillStatus(BillStatusEnum.Open);
			  }
		if(bill.getAmountPaid()==0){
			billTbl.setPaymentStatus(PayStatusEnum.NotPaid);	
			billTbl.setBillStatus(BillStatusEnum.Open);
		}
		if(bill.getAmountPaid()!=0 && bill.getAmountPaid()<bill.getBillAmount()){
			billTbl.setPaymentStatus(PayStatusEnum.PartiallyPaid);	
			billTbl.setBillStatus(BillStatusEnum.Open);
		}
		billTbl.setBillAmount(bill.getBillAmount());

		if(!bill.getItem().isEmpty()){
			//bill items
			Set<BillItemTbl> billItemList=new HashSet<BillItemTbl> (setBillItemList(bill.getItem(),billTbl));
			billTbl.setBillItemTbls(new ArrayList<BillItemTbl>(billItemList));
		}
		if(!bill.getSupport().isEmpty()){
			//bill support
			Set<BillSupportTbl> billSupportList=new HashSet<BillSupportTbl>(setBillSupportList(bill.getSupport(),billTbl));
			billTbl.setBillSupportTbls(new ArrayList<BillSupportTbl>(billSupportList));
		}
		if(!bill.getBed().isEmpty()){
			//bill bed
			Set<BillBedTbl> billBedTbls=new HashSet<BillBedTbl>(setBillBedList(bill.getBed(),billTbl));
			billTbl.setBillBedTbls(new ArrayList<BillBedTbl>(billBedTbls));
		}
		if(!bill.getDiscount().isEmpty()){
			//bill discount
			Set<BillDiscountTbl> billDiscountTbls=new HashSet<BillDiscountTbl>(setBillDiscountList(bill.getDiscount(),billTbl));
			billTbl.setBillDiscountTbls(new ArrayList<BillDiscountTbl>(billDiscountTbls));
		}
		Date  currentDateTime= new Date();
		billTbl.setCreatedDateTime(currentDateTime);
		billTbl.setUpdatedDateTime(currentDateTime);
		billTbl.setGlobalId(0);
		save(billTbl);
		BillPaymentTbl billPayemntTbl=new BillPaymentTbl(); //bill payment table		
		billPayemntTbl.setBillNo(billTbl.getUid());
		billPayemntTbl.setPaymentDate(new Date());
		if(bill.getOrigin().equals(OriginEnum.OutPatient.getDisplayName())){
			if(bill.getAmountPaid()>bill.getBillAmount())
				billPayemntTbl.setAmountPaid(bill.getBillAmount());
			else
		        billPayemntTbl.setAmountPaid(bill.getAmountPaid());
		}
		else
			billPayemntTbl.setAmountPaid(bill.getAmountPaid());
		billPayemntTbl.setBillTbl(billTbl);
	
		save(billPayemntTbl);
		response.setUid(billTbl.getUid());
		response.setId(billTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * @param billDiscount
	 * @param billTbl
	 * @return List<BillDiscountTbl>
	 */
	private List<BillDiscountTbl>setBillDiscountList(List<BillDiscountDTO> billDiscount,BillTbl billTbl) throws PersistenceException{
		List<BillDiscountTbl> discountList=new ArrayList<BillDiscountTbl>();
		for (BillDiscountDTO billDiscountDTO : billDiscount) {
			BillDiscountTbl billDiscountTbl=new BillDiscountTbl();
			DiscountTbl discountTbl=(DiscountTbl)getById(DiscountTbl.class, billDiscountDTO.getDiscountId());
			if(discountTbl==null){
				//error
				PersistenceException se=new PersistenceException(ErrorCodeEnum.NoDiscountFound);
				se.setDisplayErrMsg(true);
				throw se;

			}
			billDiscountTbl.setDiscountTbl(discountTbl);
			billDiscountTbl.setDiscountValue(billDiscountDTO.getDiscountValue());
			CalculationTypeEnum calEnum=CalculationTypeEnum.getEnum(billDiscountDTO.getCalculationType());
			billDiscountTbl.setCalculationType(calEnum);
			billDiscountTbl.setDiscountAmount(billDiscountDTO.getDiscountAmount());
			billDiscountTbl.setBillTbl(billTbl);
			discountList.add(billDiscountTbl);
		}

		return discountList;
	}
	/*
	 * set values for bill bed tables
	 */
	private List<BillBedTbl>setBillBedList(List<BillBedDTO> billBed,BillTbl billTbl) throws PersistenceException{
		List<BillBedTbl>bedList=new ArrayList<BillBedTbl>();
		for (BillBedDTO billBedDTO : billBed) {
			BillBedTbl billBedTbl=new BillBedTbl();
			BedTbl bedTbl=(BedTbl)getById(BedTbl.class,billBedDTO.getBedId());
			if(bedTbl==null){
				PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBedFound);
				se.setDisplayErrMsg(true);
				throw se;
			}
			billBedTbl.setBedTbl(bedTbl);
			if(bedTbl.getBedTypeTbl()!=null){
				billBedTbl.setStandardRate(bedTbl.getBedTypeTbl().getRent());
				if(bedTbl.getBedTypeTbl().getTaxTbl()!=null){
					billBedTbl.setTaxRate(bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal());
					CalculationTypeEnum cal=CalculationTypeEnum.getEnum(bedTbl.getBedTypeTbl().getTaxTbl().getCalculationType().getDisplayName());
					billBedTbl.setTaxCalculationType(cal);
				}
			} 
			billBedTbl.setQuantity(billBedDTO.getQuantity());
			billBedTbl.setBillTbl(billTbl);
			bedList.add(billBedTbl);
		}
		return bedList;
	}
	/*
	 * set values for bill support table
	 */
	private List<BillSupportTbl>setBillSupportList(List<BillSupportDTO> billSupport,BillTbl billTbl) throws PersistenceException{
		List<BillSupportTbl> supportList=new ArrayList<BillSupportTbl>();
		for (BillSupportDTO billSupportDTO : billSupport) {
			BillSupportTbl billSupportTbl=new BillSupportTbl();
			SupportTbl supportTbl=(SupportTbl)getById(SupportTbl.class,billSupportDTO.getSupportId());
			if(supportTbl==null){
				PersistenceException se=new PersistenceException(ErrorCodeEnum.NoServiceFound);
				se.setDisplayErrMsg(true);
				throw se;
			}
			billSupportTbl.setSupportTbl(supportTbl);
			billSupportTbl.setStandardValue(supportTbl.getPrice());
			if(supportTbl.getTaxTbl()!=null){
				billSupportTbl.setTaxRate(supportTbl.getTaxTbl().getTaxVal());
				billSupportTbl.setTaxCalculationType(supportTbl.getTaxTbl().getCalculationType());
			}
			billSupportTbl.setQuantity(billSupportDTO.getQuantity());
			billSupportTbl.setBillTbl(billTbl);
			billSupportTbl.setSupportDate(new Date());
			supportList.add(billSupportTbl);
		}
		return supportList;
	}

	/*
	 * set values in bill item tables
	 */
	private List<BillItemTbl> setBillItemList(List<BillItemDTO> billItem,BillTbl billTbl) throws PersistenceException{
		List<BillItemTbl> list=new ArrayList<BillItemTbl>();
		for (BillItemDTO billItemDTO : billItem) {
			BillItemTbl billItemTbl=new BillItemTbl();
			ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class,billItemDTO.getItemId());
			if(itemTbl==null){
				PersistenceException se=new PersistenceException(ErrorCodeEnum.NoItemFound);
				se.setDisplayErrMsg(true);
				throw se;
			}
			billItemTbl.setItemTbl(itemTbl);
			billItemTbl.setStandardRate(itemTbl.getPrice());
			if(itemTbl.getTaxTbl()!=null){
				billItemTbl.setTaxRate(itemTbl.getTaxTbl().getTaxVal());
				billItemTbl.setTaxCalculationType(itemTbl.getTaxTbl().getCalculationType());

			}
			billItemTbl.setQuantity(billItemDTO.getQuantity());
			billItemTbl.setBillTbl(billTbl);
			billItemTbl.setItemDate(new Date());
			list.add(billItemTbl);
		}
		return list ;
	}
	/**
	 * get next sequence
	 * @param orgin
	 * @return int
	 */
	@Transactional(readOnly=false)
	public int getNextSequenceId(String orgin) throws PersistenceException{
		int seqId=0;
		SequenceTbl seqTbl = getSequence();
		if(seqTbl==null){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.InvalidSeq);			
			se.setDisplayErrMsg(true);			
			throw se;
		}
		if(orgin.equals(Constants.INPATIENT)){
			seqId=seqTbl.getInPatient()+1;
			seqTbl.setInPatient(seqId);
		}
		else{
			seqId=seqTbl.getOutPatient()+1;
			seqTbl.setOutPatient(seqId);
		}
		update(seqTbl);
		return seqId;
	}
	private SequenceTbl getSequence() throws PersistenceException {	
		javax.persistence.Query query=em.createQuery(Query.GET_SEQUENCE);		
		return executeUniqueQuery(SequenceTbl.class, query);			
	}
	/**
	 * view bill
	 * @param uid
	 * @return BillViewResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillViewResponseDTO view(String uid) throws PersistenceException{
		BillViewResponseDTO response=new BillViewResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(uid);
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,uid));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(billTbl.getPatientTbl()!=null){
			PatientDetail patient=new PatientDetail();
			patient.setId(billTbl.getPatientTbl().getId());
			patient.setFirstName(billTbl.getPatientTbl().getFirstName());
			patient.setLastName(billTbl.getPatientTbl().getLastName());
			patient.setEmail(billTbl.getPatientTbl().getEmail());
			patient.setAge(billTbl.getPatientTbl().getAge());
			patient.setMobile(billTbl.getPatientTbl().getMobile());
			patient.setPhone(billTbl.getPatientTbl().getPhone());
			if(billTbl.getPatientTbl().getGender()!=null)
				patient.setGender(billTbl.getPatientTbl().getGender().getDisplayName());
			response.setPatient(patient);
		}

		response.setId(billTbl.getId());
		response.setUid(billTbl.getUid());		
		response.setAmountPaid(billTbl.getPaidAmount());
		response.setReferralId(billTbl.getReferralId());
		response.setReferralName(billTbl.getReferralName());
		response.setBillAmount(billTbl.getBillAmount());
		response.setCurrency(billTbl.getCurrency());
		response.setBillStatus(billTbl.getBillStatus().getDisplayName());
		response.setGrandTotal(billTbl.getGrandTotal());
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		response.setBillDate(df.format(billTbl.getCreatedOn()));

		DateFormat df1=new SimpleDateFormat(Constants.TIMEWITHFORMAT);
		response.setBillTime(df1.format(billTbl.getCreatedOn()));

		float balanceAmount=billTbl.getGrandTotal()-billTbl.getPaidAmount();
		response.setBalanceAmount(balanceAmount);
		response.setBillDiscount(billTbl.getGrandTotal()-billTbl.getBillAmount());
		if(billTbl.getOrigin()!=null)
			response.setOrigin(billTbl.getOrigin().getDisplayName());
		if(billTbl.getPaymentStatus()!=null)
			response.setPaymentStatus(billTbl.getPaymentStatus().getDisplayName());
		response.setNotes(billTbl.getNote());
		List<BillItemDTO>itemList=(ArrayList<BillItemDTO>)getBillItemDTOList(billTbl.getId());
		response.setItem(itemList);
		List<BillPaymentDTO>paymentList=getBillPaymentDTOList(billTbl.getId());
		response.setPayment(paymentList);
		List<BillSupportDTO>supportList=(ArrayList<BillSupportDTO>)getBillSupportDTOList(billTbl.getId());
		response.setSupport(supportList);
		List<BillBedDTO>bedList=(ArrayList<BillBedDTO>)getBedDTOList(billTbl.getId());
		response.setBed(bedList);
		List<BillDiscountDTO>discontList=(ArrayList<BillDiscountDTO>)getBillDiscountList(billTbl.getId());
		response.setDiscount(discontList);
		response.setError(null);
		response.setSuccess(true);

		return response;
	}
	/**
	 * get bill
	 * @param uid
	 * @return BillViewResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillViewResponseDTO getBill(String uid)  throws PersistenceException{
		BillViewResponseDTO response=new BillViewResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(uid);
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,uid));
			se.setDisplayErrMsg(true);
			throw se;
		}

		response.setId(billTbl.getId());
		response.setUid(billTbl.getUid());		
		response.setAmountPaid(billTbl.getPaidAmount());
		response.setReferralId(billTbl.getReferralId());
		response.setBillAmount(billTbl.getBillAmount());
		response.setCurrency(billTbl.getCurrency());
		response.setGrandTotal(billTbl.getGrandTotal());
		if(billTbl.getOrigin()!=null)
			response.setOrigin(billTbl.getOrigin().getDisplayName());
		if(billTbl.getPaymentStatus()!=null)
			response.setPaymentStatus(billTbl.getPaymentStatus().getDisplayName());
		response.setNotes(billTbl.getNote());

		response.setError(null);
		response.setSuccess(true);

		return response;
	}


	private List<BillItemDTO> getBillItemDTOList(int id) throws PersistenceException{
		List<BillItemDTO>  billItemList=new ArrayList<BillItemDTO>();
		List<BillItemTbl>itemList=(ArrayList<BillItemTbl>)getItemsByBill(id);

		for (BillItemTbl billItemTbl : itemList) {
			float netRate=0;
			BillItemDTO billDto=new BillItemDTO();
			billDto.setBillId(id);
			if(billItemTbl.getItemTbl()!=null){
				billDto.setItemId(billItemTbl.getItemTbl().getId());
				billDto.setItemName(billItemTbl.getItemTbl().getName());
			}
			billDto.setStdRate(billItemTbl.getStandardRate());
			billDto.setQuantity(billItemTbl.getQuantity());				 
			billDto.setTaxValue(billItemTbl.getTaxRate());
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			if(billItemTbl.getItemDate()!=null)
			billDto.setItemDate(df.format(billItemTbl.getItemDate()));
			if(billItemTbl.getTaxCalculationType()!=null)
				billDto.setNetRate(calcNetRate(billItemTbl.getQuantity(),billItemTbl.getStandardRate(),billItemTbl.getTaxRate(),billItemTbl.getTaxCalculationType()));
			else{
				netRate=billItemTbl.getStandardRate()*billItemTbl.getQuantity();
				billDto.setNetRate(netRate);
			}
				
			billItemList.add(billDto);
		}



		return billItemList;

	}

	/**
	 * get discount lists by bill table id
	 * @param id
	 * @return List<BillDiscountDTO> 
	 * @throws PersistenceException 
	 */
	@Override
	@Transactional(readOnly=false)
	public List<BillDiscountDTO> getBillDiscountList(int id) throws PersistenceException{
		List<BillDiscountDTO>  billDiscontList=new ArrayList<BillDiscountDTO>();
		List<BillDiscountTbl>discountList=(ArrayList<BillDiscountTbl>)getDiscountsByBill(id);

		for (BillDiscountTbl billDiscountTbl : discountList) {
			BillDiscountDTO billDto=new BillDiscountDTO();

			billDto.setId(billDiscountTbl.getId());
			if(billDiscountTbl.getDiscountTbl()!=null){
				billDto.setDiscountId(billDiscountTbl.getDiscountTbl().getId());
				billDto.setName(billDiscountTbl.getDiscountTbl().getName());
			}
			if(billDiscountTbl.getCalculationType()!=null)
				billDto.setCalculationType(billDiscountTbl.getCalculationType().getDisplayName());
			billDto.setDiscountAmount(billDiscountTbl.getDiscountAmount());
			billDto.setDiscountValue(billDiscountTbl.getDiscountValue());
			billDiscontList.add(billDto);
		}

		return billDiscontList;

	}
	private List<BillSupportDTO> getBillSupportDTOList(int id) throws PersistenceException{
		List<BillSupportDTO>  billSupportList=new ArrayList<BillSupportDTO>();
		List<BillSupportTbl>supportList=(ArrayList<BillSupportTbl>)getSupportByBill(id);

		for (BillSupportTbl billSupportTbl : supportList) {
			float netRate=0;
			BillSupportDTO billDto=new BillSupportDTO();
			billDto.setBillId(id);
			if(billSupportTbl.getSupportTbl()!=null){
				billDto.setSupportId(billSupportTbl.getSupportTbl().getId());
				billDto.setSupportName(billSupportTbl.getSupportTbl().getName());
			}
			billDto.setStdRate(billSupportTbl.getStandardValue());
			billDto.setQuantity(billSupportTbl.getQuantity());				
			billDto.setTaxValue(billSupportTbl.getTaxRate());
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			if(billSupportTbl.getSupportDate()!=null)
			billDto.setSupportDate(df.format(billSupportTbl.getSupportDate()));
			if(billSupportTbl.getTaxCalculationType()!=null)
				billDto.setNetRate(calcNetRate(billSupportTbl.getQuantity(),billSupportTbl.getStandardValue(),billSupportTbl.getTaxRate(),billSupportTbl.getTaxCalculationType()));
			else{
				netRate=billSupportTbl.getStandardValue()*billSupportTbl.getQuantity();
				billDto.setNetRate(netRate);
			}
			billSupportList.add(billDto);
		}			


		return billSupportList;

	}
	private List<BillBedDTO> getBedDTOList(int id) throws PersistenceException{
		List<BillBedDTO>  billBedList=new ArrayList<BillBedDTO>();
		List<BillBedTbl>bedList=(ArrayList<BillBedTbl>)getBedByBillId(id);

		for (BillBedTbl billBedTbl : bedList) {
			float netRate=0;
			BillBedDTO billDto=new BillBedDTO();
			billDto.setBillId(id);
			billDto.setStdRate(billBedTbl.getStandardRate());
			if(billBedTbl.getBedTbl()!=null){
				billDto.setBedId(billBedTbl.getBedTbl().getId());
				billDto.setBedName(billBedTbl.getBedTbl().getBedNumber());
			}
			billDto.setQuantity(billBedTbl.getQuantity());
			billDto.setTaxValue(billBedTbl.getTaxRate());
			if(billBedTbl.getTaxCalculationType()!=null)
				billDto.setNetRate(calcNetRate(billBedTbl.getQuantity(),billBedTbl.getStandardRate(),billBedTbl.getTaxRate(),billBedTbl.getTaxCalculationType()));			
			else{
				netRate=billBedTbl.getStandardRate()*billBedTbl.getQuantity();
				billDto.setNetRate(netRate);
			}

			billBedList.add(billDto);
		}

		return billBedList;

	}
	private List<BillDiscountTbl>getDiscountsByBill(int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_DISCOUNT_BY_BILLID);	
		query.setParameter("param1", billId);
		return executeQuery(BillDiscountTbl.class,query);

	}

	private List<BillBedTbl>getBedByBillId(int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_BED_BY_BILLID);	
		query.setParameter("param1", billId);
		return executeQuery(BillBedTbl.class,query);

	}
	private List<BillSupportTbl>getSupportByBill(int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_SUPPORT_BY_BILLID);	
		query.setParameter("param1", billId);
		return executeQuery(BillSupportTbl.class,query);

	}
	private List<BillItemTbl>getItemsByBill(int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_ITEM_BY_BILLID);	
		query.setParameter("param1", billId);
		return executeQuery(BillItemTbl.class,query);

	}

	private BillTbl getBillByUid(String uid) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_BILL_BY_UID);	
		query.setParameter("param1", uid);
		return executeUniqueQuery(BillTbl.class, query);	
	}
	
	private BillTbl getBillByPatientId( int patientId) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_BILL_BY_PATIENT_ID);	
		query.setParameter("param1",patientId );

		// TODO Auto-generated method stub
		return executeUniqueQuery(BillTbl.class, query);
	}
	/**
	 * calculate net rate
	 * @param quantity
	 * @param stdRate
	 * @param taxVal
	 * @return 
	 */
	private float calcNetRate(int quantity,float stdRate,float taxVal,CalculationTypeEnum taxType) throws PersistenceException{
		if(quantity==0){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.QualitityNotNull);
			se.setDisplayErrMsg(true);
			throw se;
		}
		float taxAmount=0;
		float price=0;
		float netRate=0;
		if(taxType!=null){
			if(taxType==CalculationTypeEnum.Fixed)
				taxAmount=taxVal;
			else
				taxAmount=stdRate*taxVal/100;
			price=stdRate+taxAmount;
		}	
		netRate=price*quantity;
		return netRate;

	}
	/** 
	 *update bill header
	 *@param headerDto 
	 *@return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateBillHeader(BillHeaderDTO headerDto)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(headerDto.getUid());
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,headerDto.getUid()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		PatientTbl patientTbl=(PatientTbl)getById(PatientTbl.class, headerDto.getPatientId());
		if(patientTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.PatientNotFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(billTbl.getBillStatus().equals(BillStatusEnum.Cancelled)){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.CantUpdate);			
			se.setDisplayErrMsg(true);
			throw se;
		}		
		OriginEnum origin=OriginEnum.getEnum(headerDto.getOrigin());
		billTbl.setPatientTbl(patientTbl);
		billTbl.setReferralName(headerDto.getReferralName());
		billTbl.setOrigin(origin);
		billTbl.setNote(headerDto.getNote());
		Date updatedDateTime = new Date();
		billTbl.setUpdatedDateTime(updatedDateTime);
		update(billTbl);
		response.setId(billTbl.getId());
		response.setUid(billTbl.getUid());
		response.setError(null);
		response.setSuccess(true);

		return response;
	}
	
	/** 
	 *update bill billstatusDto
	 *@param billstatusDto 
	 *@return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateBillStatus(BillStatusDTO billstatusDto)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(billstatusDto.getUid());
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,billstatusDto.getUid()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		Date newDate=null;
		Date createdOn=null;
		try {
			newDate = df.parse(df.format(new Date()));
			createdOn=df.parse(df.format(billTbl.getCreatedOn()));
		} catch (ParseException e) {
			PersistenceException se =new PersistenceException(ErrorCodeEnum.InvalidDateFormat);
			se.setDisplayErrMsg(true);
			throw se;
		}
		
		
		if(!newDate.equals(createdOn)){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.NotTodaysOrder);
			se.setDisplayErrMsg(true);
			throw se;
		}
		BillStatusEnum billEnum= BillStatusEnum.getEnum(billstatusDto.getBillstatus());
		billTbl.setBillStatus(billEnum);
		billTbl.setNote(billstatusDto.getNote());
		//billTbl.setBillStatus(BillStatusEnum.Cancelled);
		Date updatedDateTime = new Date();
		billTbl.setUpdatedDateTime(updatedDateTime);
		update(billTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/** 
	 * update bill items 
	 * @param billItems
	 * @return BillResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillResponseDTO updateBillItems(BillItemListDTO billItems) throws PersistenceException {
		BillResponseDTO response=new BillResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(billItems.getBillUid());
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,billItems.getBillUid()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(billTbl.getBillStatus().equals(BillStatusEnum.Cancelled)){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.CantUpdate);			
			se.setDisplayErrMsg(true);
			throw se;
		}	
		billTbl.setGrandTotal(billItems.getGrandTotal());
	/*	if(billTbl.getPaidAmount()>billItems.getBillAmount()){
			//response.setAmountToBePaid(billTbl.getPaidAmount()-billItems.getBillAmount());
			//updatePaymentTbl(billTbl,billItems.getBillAmount());//update payment table
			//billTbl.setPaidAmount(billItems.getBillAmount());
			
		}*/
		
		if(billTbl.getPaidAmount()==0 && billItems.getBillAmount()>0)
			billTbl.setPaymentStatus(PayStatusEnum.NotPaid);					
		if(billTbl.getPaidAmount()!=0 && billTbl.getPaidAmount()<billItems.getBillAmount())
			billTbl.setPaymentStatus(PayStatusEnum.PartiallyPaid);						
		if(billTbl.getPaidAmount()==billItems.getBillAmount() || billTbl.getPaidAmount()>billItems.getBillAmount())
			billTbl.setPaymentStatus(PayStatusEnum.FullyPaid);	

		billTbl.setBillAmount(billItems.getBillAmount());

		List<BillItemTbl> billItemAddList=new ArrayList<BillItemTbl>();
		List<BillItemTbl> billItemUpdateList=new ArrayList<BillItemTbl>();
		List<BillItemTbl> billItemDelList=new ArrayList<BillItemTbl>();

		List<BillSupportTbl> billSupportAddList=new ArrayList<BillSupportTbl>();
		List<BillSupportTbl> billSupportUpdateList=new ArrayList<BillSupportTbl>();
		List<BillSupportTbl> billSupportDelList=new ArrayList<BillSupportTbl>();

		List<BillBedTbl> billBedAddList=new ArrayList<BillBedTbl>();
		List<BillBedTbl> billBedUpdateList=new ArrayList<BillBedTbl>();
		List<BillBedTbl> billBedDelList=new ArrayList<BillBedTbl>();

		if(billItems.getItem()!=null){

			for (BillItemDTO billItemDTO : billItems.getItem()) {
				if(billItemDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					BillItemTbl billItemTbl=new BillItemTbl();

					BillItemTbl billItem=(BillItemTbl)getItemByBillId(billItemDTO.getItemId(),billTbl.getId());
					if(billItem!=null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.BillItemAlreadyExist);						
						se.setDisplayErrMsg(true);
						throw se;
					}
					ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class, billItemDTO.getItemId());
					if(itemTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoItemFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billItemDTO.getItemId())));
						se.setDisplayErrMsg(true);
						throw se;
					}

					billItemTbl.setItemTbl(itemTbl);
					billItemTbl.setBillTbl(billTbl);
					billItemTbl.setItemDate(new Date());
					billItemTbl.setQuantity(billItemDTO.getQuantity());
					billItemTbl.setStandardRate(itemTbl.getPrice());
					if(itemTbl.getTaxTbl()!=null){
						billItemTbl.setTaxRate(itemTbl.getTaxTbl().getTaxVal());
						billItemTbl.setTaxCalculationType(itemTbl.getTaxTbl().getCalculationType());
					}
					billItemAddList.add(billItemTbl);
				}
				if(billItemDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					BillItemTbl billItemTbl=(BillItemTbl)getItemByBillId(billItemDTO.getItemId(),billTbl.getId());
					if(billItemTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillItemFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billItemDTO.getItemId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billItemTbl.setBillTbl(billTbl);
					ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class, billItemDTO.getItemId());
					if(itemTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoItemFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billItemDTO.getItemId())));
						se.setDisplayErrMsg(true);
						throw se;

					}
					billItemTbl.setQuantity(billItemDTO.getQuantity());
					billItemUpdateList.add(billItemTbl);
				}
				if(billItemDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					BillItemTbl billItemTbl=(BillItemTbl)getItemByBillId(billItemDTO.getItemId(),billTbl.getId());
					if(billItemTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillItemFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billItemDTO.getItemId())));
						se.setDisplayErrMsg(true);
						throw se;
					}

					billItemDelList.add(billItemTbl);
				}
			}

		}
		if(billItems.getSupport()!=null){
			for (BillSupportDTO billSupportDTO : billItems.getSupport()) {
				if(billSupportDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					BillSupportTbl billSupport=(BillSupportTbl)getSupportByBillId(billSupportDTO.getSupportId(),billTbl.getId());
					if(billSupport!=null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.BillSupportAlreadyExist);						
						se.setDisplayErrMsg(true);
						throw se;
					}
					BillSupportTbl billSupportTbl=new BillSupportTbl();
					billSupportTbl.setBillTbl(billTbl);
					SupportTbl supportTbl =(SupportTbl)getById(SupportTbl.class,billSupportDTO.getSupportId());
					if(supportTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoServiceFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billSupportDTO.getSupportId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billSupportTbl.setSupportTbl(supportTbl);
					billSupportTbl.setQuantity(billSupportDTO.getQuantity());
					billSupportTbl.setSupportDate(new Date());
					billSupportTbl.setStandardValue(supportTbl.getPrice());
					if(supportTbl.getTaxTbl()!=null){
						billSupportTbl.setTaxRate(supportTbl.getTaxTbl().getTaxVal());
						billSupportTbl.setTaxCalculationType(supportTbl.getTaxTbl().getCalculationType());
					}
					billSupportAddList.add(billSupportTbl);
				}
				if(billSupportDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					BillSupportTbl billSupportTbl=(BillSupportTbl)getSupportByBillId(billSupportDTO.getSupportId(),billTbl.getId());
					if(billSupportTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillSupportFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billSupportDTO.getSupportId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billSupportTbl.setBillTbl(billTbl);
					SupportTbl supportTbl =(SupportTbl)getById(SupportTbl.class,billSupportDTO.getSupportId());
					if(supportTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoServiceFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billSupportDTO.getSupportId())));
						se.setDisplayErrMsg(true);
						throw se;

					}
					billSupportTbl.setQuantity(billSupportDTO.getQuantity());
					billSupportUpdateList.add(billSupportTbl);
				}
				if(billSupportDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					BillSupportTbl billSupportTbl=(BillSupportTbl)getSupportByBillId(billSupportDTO.getSupportId(),billTbl.getId());
					if(billSupportTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillSupportFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billSupportDTO.getSupportId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billSupportDelList.add(billSupportTbl);
				}
			}
		}

		if(billItems.getBed()!=null){
			for (BillBedDTO billBedDTO : billItems.getBed()) {
				if(billBedDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					BillBedTbl billBed=(BillBedTbl)getBedByBillId(billBedDTO.getBedId(),billTbl.getId());
					if(billBed!=null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.BillBedAlreadyExist);						
						se.setDisplayErrMsg(true);
						throw se;
					}
					BillBedTbl billBedTbl=new BillBedTbl();
					billBedTbl.setBillTbl(billTbl);
					BedTbl bedTbl=(BedTbl)getById(BedTbl.class, billBedDTO.getBedId());
					if(bedTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBedFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billBedDTO.getBedId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billBedTbl.setBedTbl(bedTbl);
					billBedTbl.setQuantity(billBedDTO.getQuantity());
					if(bedTbl.getBedTypeTbl()!=null){
						billBedTbl.setStandardRate(bedTbl.getBedTypeTbl().getRent());
						if(bedTbl.getBedTypeTbl().getTaxTbl()!=null){
							billBedTbl.setTaxRate(bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal());
							billBedTbl.setTaxCalculationType(bedTbl.getBedTypeTbl().getTaxTbl().getCalculationType());
						}
					}
					billBedAddList.add(billBedTbl);
				}
				if(billBedDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					BillBedTbl billBedTbl=(BillBedTbl)getBedByBillId(billBedDTO.getBedId(),billTbl.getId());
					if(billBedTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillBedFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billBedDTO.getBedId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billBedTbl.setBillTbl(billTbl);
					BedTbl bedTbl=(BedTbl)getById(BedTbl.class, billBedDTO.getBedId());
					if(bedTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBedFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billBedDTO.getBedId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					//billBedTbl.setBedTbl(bedTbl);
					billBedTbl.setQuantity(billBedDTO.getQuantity());
					/*if(bedTbl.getBedTypeTbl()!=null){
						billBedTbl.setStandardRate(bedTbl.getBedTypeTbl().getRent());
						if(bedTbl.getBedTypeTbl().getTaxTbl()!=null){
							billBedTbl.setTaxRate(bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal());
							billBedTbl.setTaxCalculationType(bedTbl.getBedTypeTbl().getTaxTbl().getCalculationType());
						}
					}*/
					billBedUpdateList.add(billBedTbl);
				}
				if(billBedDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					BillBedTbl billBedTbl=(BillBedTbl)getBedByBillId(billBedDTO.getBedId(),billTbl.getId());
					if(billBedTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillBedFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(billBedDTO.getBedId())));
						se.setDisplayErrMsg(true);
						throw se;
					}
					billBedDelList.add(billBedTbl);
				}
			}
		}
		if(!billBedAddList.isEmpty()){
			for (BillBedTbl billBedTbl : billBedAddList) {
				save(billBedTbl);
			}
		}
		if(!billBedUpdateList.isEmpty()){
			for (BillBedTbl billBedTbl : billBedUpdateList) {
				update(billBedTbl);
			}
		}
		if(!billBedDelList.isEmpty()){
			for (BillBedTbl billBedTbl : billBedDelList) {
				delete(billBedTbl);
			}
		}
		if(!billItemAddList.isEmpty()){
			for (BillItemTbl billItemTbl : billItemAddList) {
				save(billItemTbl);
			}
		}
		if(!billItemUpdateList.isEmpty()){
			for (BillItemTbl billItemTbl : billItemUpdateList) {
				update(billItemTbl);
			}
		}
		if(!billItemDelList.isEmpty()){
			for (BillItemTbl billItemTbl : billItemDelList) {
				delete(billItemTbl);
			}
		}
		if(!billSupportAddList.isEmpty()){
			for(BillSupportTbl billSupportTbl:billSupportAddList){
				save(billSupportTbl);
			}
		}
		if(!billSupportUpdateList.isEmpty()){
			for(BillSupportTbl billSupportTbl:billSupportUpdateList){
				update(billSupportTbl);
			}
		}
		if(!billSupportDelList.isEmpty()){
			for(BillSupportTbl billSupportTbl:billSupportDelList){
				delete(billSupportTbl);
			}
		}
		
		Date updatedDateTime = new Date();
		billTbl.setUpdatedDateTime(updatedDateTime);
		update(billTbl);
		response.setId(billTbl.getId());
		response.setUid(billTbl.getUid());
		if(billTbl.getPatientTbl()!=null)
			response.setPatientName(billTbl.getPatientTbl().getFirstName());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/** 
	 *get a bill by id
	 *@param bedId
	 *@param billId
	 *@return BillBedDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillBedDTO getBillBedById(int bedId,int billId) throws PersistenceException {
		BillBedDTO response=new BillBedDTO();
		BillBedTbl billBedTbl=(BillBedTbl)getBedByBillId(bedId,billId);
		if(billBedTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBedFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setBedId(billBedTbl.getBedTbl().getId());
		response.setId(billBedTbl.getId());
		response.setStdRate(billBedTbl.getStandardRate());
		response.setQuantity(billBedTbl.getQuantity());
		response.setTaxValue(billBedTbl.getTaxRate());
		response.setTaxType(billBedTbl.getTaxCalculationType().getDisplayName());
		return response;
	}

	private BillBedTbl getBedByBillId(int bedId,int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_BED_BY_BILLID_BED_ID);			
		query.setParameter("param1", bedId);		
		query.setParameter("param2", billId);
		return executeUniqueQuery(BillBedTbl.class, query);	
	}
	private BillSupportTbl getSupportByBillId(int supportId,int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_SUPPORT_BY_BILLID_SUPPORT_ID);			

		query.setParameter("param1", supportId);
		query.setParameter("param2", billId);
		return executeUniqueQuery(BillSupportTbl.class, query);	
	}
	private BillItemTbl getItemByBillId(int itemId,int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_ITEM_BY_BILL_ITEM_ID);

		query.setParameter("param1", itemId);
		query.setParameter("param2", billId);
		return executeUniqueQuery(BillItemTbl.class, query);	
	}
	/** get bill item by id
	 * @param itemId
	 * @param billId
	 * @return BillItemDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillItemDTO getBillItemById(int itemId,int billId) throws PersistenceException {
		BillItemDTO response=new BillItemDTO();
		BillItemTbl billItemTbl=(BillItemTbl) getItemByBillId( itemId,billId);
		if(billItemTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoItemFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setItemId(billItemTbl.getItemTbl().getId());
		response.setId(billItemTbl.getId());
		response.setQuantity(billItemTbl.getQuantity());
		response.setStdRate(billItemTbl.getStandardRate());
		if(billItemTbl.getTaxCalculationType()!=null){
			response.setTaxValue(billItemTbl.getTaxRate());
			response.setTaxType(billItemTbl.getTaxCalculationType().getDisplayName());
		}	
		return response;
	}
	/** 
	 * get support by id
	 * @param supportId
	 * @param billId
	 * @return BillSupportDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillSupportDTO getBillSupportById(int supportId,int billId) throws PersistenceException {
		BillSupportDTO response=new BillSupportDTO();
		BillSupportTbl billSupportTbl=(BillSupportTbl)getSupportByBillId(supportId,billId);
		if(billSupportTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoServiceFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setSupportId(billSupportTbl.getSupportTbl().getId());
		response.setId(billSupportTbl.getId());
		response.setQuantity(billSupportTbl.getQuantity());
		response.setStdRate(billSupportTbl.getStandardValue());
		if(billSupportTbl.getTaxCalculationType()!=null){
			response.setTaxValue(billSupportTbl.getTaxRate());
			response.setTaxType(billSupportTbl.getTaxCalculationType().getDisplayName());
		}
		return response;
	}
	/** 
	 * update bill discount
	 *@param billDiscount
	 *@return BillResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillResponseDTO updateBillDiscount(BillDiscountDetailsDTO billDiscount) throws PersistenceException {

		BillResponseDTO response=new BillResponseDTO();
		float grdTotal=0;
		float totalDiscount=0;
		float billAmount=0;

		BillTbl billTbl=getBillByUid(billDiscount.getBillUid());
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,billDiscount.getBillUid()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(billTbl.getBillStatus().equals(BillStatusEnum.Cancelled)){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.CantUpdate);			
			se.setDisplayErrMsg(true);
			throw se;
		}	
		grdTotal=billTbl.getGrandTotal();
		List<BillDiscountTbl>billDiscountAddList=new ArrayList<BillDiscountTbl>();
		List<BillDiscountTbl>billDiscountUpList=new ArrayList<BillDiscountTbl>();
		List<BillDiscountTbl>billDiscountDelList=new ArrayList<BillDiscountTbl>();
		if(billDiscount.getBillDiscountList()!=null){
			for (BillDiscountDTO billDiscountDTO : billDiscount.getBillDiscountList()) {
				if(billDiscountDTO.getActionName().equals(ActionNameEnum.ADD.getDisplayName())){
					BillDiscountTbl billDis=(BillDiscountTbl)getBillDiscount(billDiscountDTO.getDiscountId(),billTbl.getId());
					if(billDis!=null){
						PersistenceException se=new PersistenceException(ErrorCodeEnum.BillDisccountAlreadyExist);
						se.setDisplayErrMsg(true);
						throw se;
					}
					BillDiscountTbl billDiscountTbl=new BillDiscountTbl();
					DiscountTbl discountTbl=(DiscountTbl)getById(DiscountTbl.class, billDiscountDTO.getDiscountId());
					if(discountTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoDiscountFound);
						se.setDisplayErrMsg(true);
						throw se;

					}
					billDiscountTbl.setDiscountTbl(discountTbl);
					billDiscountTbl.setDiscountValue(billDiscountDTO.getDiscountValue());				
					if(discountTbl.getCalculationType()!=null)
						billDiscountTbl.setCalculationType(discountTbl.getCalculationType());
					billDiscountTbl.setDiscountAmount(billDiscountDTO.getDiscountAmount());
					billDiscountTbl.setBillTbl(billTbl);
					billDiscountAddList.add(billDiscountTbl);

				}
				if(billDiscountDTO.getActionName().equals(ActionNameEnum.UPDATE.getDisplayName())){
					float discAmount=0;
					BillDiscountTbl billDiscountTbl=(BillDiscountTbl)getBillDiscount(billDiscountDTO.getDiscountId(),billTbl.getId());
					if(billDiscountTbl==null){
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillDiscountFound);
						se.setDisplayErrMsg(true);
						throw se;
					}
					DiscountTbl discountTbl=(DiscountTbl)getById(DiscountTbl.class, billDiscountDTO.getDiscountId());
					if(discountTbl==null){
						//error
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoDiscountFound);
						se.setDisplayErrMsg(true);
						throw se;

					}

					if(discountTbl.getDiscountType().equals(DiscountTypeEnum.OnDemand)){
						discAmount=calcDiscAmount(billTbl.getGrandTotal(), billDiscountDTO.getDiscountValue(), billDiscountTbl.getCalculationType().getDisplayName());
					}
					billDiscountTbl.setDiscountValue(billDiscountDTO.getDiscountValue());
					billDiscountTbl.setDiscountAmount(discAmount);
					billDiscountUpList.add(billDiscountTbl);
				}
				if(billDiscountDTO.getActionName().equals(ActionNameEnum.DELETE.getDisplayName())){
					BillDiscountTbl billDiscountTbl=(BillDiscountTbl)getBillDiscount(billDiscountDTO.getDiscountId(),billTbl.getId());
					if(billDiscountTbl==null){
						PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillDiscountFound);
						se.setDisplayErrMsg(true);
						throw se;
					}
					billDiscountDelList.add(billDiscountTbl);
				}

			}
		}

		if(billDiscountAddList!=null){
			for (BillDiscountTbl billDiscountTbl : billDiscountAddList) {
				save(billDiscountTbl);
			}
		}
		if(billDiscountUpList!=null){
			for (BillDiscountTbl billDiscountTbl : billDiscountUpList) {
				update(billDiscountTbl);
			}
		}
		if(billDiscountDelList!=null){
			for (BillDiscountTbl billDiscountTbl : billDiscountDelList) {
				delete(billDiscountTbl);
			}
		}
		//get bill discount table by bill id
		List<BillDiscountTbl>discountList=(ArrayList<BillDiscountTbl>)getDiscountsByBill(billTbl.getId());

		for (BillDiscountTbl billDiscountTbl : discountList) {

			totalDiscount+=billDiscountTbl.getDiscountAmount();			

		}
		if(grdTotal<totalDiscount){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.DiscountGreaterThanAmount);			
			se.setDisplayErrMsg(true);			
			throw se;
		}
		billAmount=grdTotal-totalDiscount;
	/*	if(billTbl.getPaidAmount()>billAmount){
			response.setAmountToBePaid(billTbl.getPaidAmount()-billAmount);
			billTbl.setPaidAmount(billAmount);
		}
		updatePaymentTbl(billTbl,billAmount);//update payment table
*/		//update pay status
		if(billTbl.getPaidAmount()!=0 && billTbl.getPaidAmount()<billAmount)
			billTbl.setPaymentStatus(PayStatusEnum.PartiallyPaid);
		if(billTbl.getPaidAmount()==billAmount || billTbl.getPaidAmount()>billAmount)
			billTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
		if(billTbl.getPaidAmount()==0 && billAmount>0)
			billTbl.setPaymentStatus(PayStatusEnum.NotPaid);

		billTbl.setBillAmount(billAmount);
		Date updatedDateTime = new Date();
		billTbl.setUpdatedDateTime(updatedDateTime);
		update(billTbl);
		response.setId(billTbl.getId());
		response.setUid(billTbl.getUid());
		if(billTbl.getPatientTbl()!=null)
			response.setPatientName(billTbl.getPatientTbl().getFirstName());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	private BillDiscountTbl getBillDiscount(int discountId,int billId) throws PersistenceException{
		javax.persistence.Query query=em.createQuery(Query.GET_BILL_DISCOUNT);

		query.setParameter("param1", discountId);
		query.setParameter("param2", billId);
		return executeUniqueQuery(BillDiscountTbl.class, query);	
	}

	/**
	 * update payment table
	 * @param orderTbl
	 * @param billAmount
	 * @throws PersistenceException 
	 */
	private void updatePaymentTbl(BillTbl billTbl, float billAmount) throws PersistenceException{

		if((billTbl.getPaidAmount()>billAmount)){
			BillPaymentTbl billPaymentTbl=new BillPaymentTbl();
			billPaymentTbl.setPaymentDate(new Date());
			billPaymentTbl.setBillNo(billTbl.getUid());
			billPaymentTbl.setBillTbl(billTbl);
			billPaymentTbl.setAmountPaid(-(billTbl.getPaidAmount()-billAmount));
			save(billPaymentTbl);
		}
	}
	private float calcDiscAmount(float amount,float discountValue,String calculationType ) throws PersistenceException{
		float netRate = 0;
		float discountAmount= 0;
		if(discountValue<=0){
			netRate = amount;
		}else if(calculationType.equals(CalculationTypeEnum.Fixed.getDisplayName())){
			netRate = amount-discountValue;
			discountAmount= discountValue;
		}else {
			netRate =  amount-(amount*discountValue/100);
			discountAmount =(amount*discountValue/100);
		}
		if(netRate<0){
			if(calculationType.equals(CalculationTypeEnum.Fixed.getDisplayName())){
				PersistenceException se =new PersistenceException(ErrorCodeEnum.DiscountGreaterThanAmount);			
				se.setDisplayErrMsg(true);			
				throw se;
			}
			else{
				PersistenceException se =new PersistenceException(ErrorCodeEnum.InvalidDiscountPercentage);			
				se.setDisplayErrMsg(true);			
				throw se;
			}

		}	
		return discountAmount;
	}
	/** 
	 *update bill payment
	 *@param payment
	 *@return  BillResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BillResponseDTO updateBillPayment(BillPaymentDTO payment)  throws PersistenceException{
		// TODO Auto-generated method stub
		BillResponseDTO response=new BillResponseDTO(); 

		float payAmount=0;
		float billAmount=0;
        float prevPaid=0;
		BillPaymentTbl billPaymentTbl=new BillPaymentTbl(); 
		BillTbl billTbl=getBillByUid(payment.getBillUid());
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,payment.getBillUid()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(billTbl.getBillStatus().equals(BillStatusEnum.Cancelled)){
			PersistenceException se =new PersistenceException(ErrorCodeEnum.CantUpdate);			
			se.setDisplayErrMsg(true);
			throw se;
		}	
		billAmount=billTbl.getBillAmount();
		prevPaid=billTbl.getPaidAmount();
		payAmount=billTbl.getPaidAmount()+payment.getPaidAmount();
		if(billTbl.getOrigin().getDisplayName().equals(OriginEnum.OutPatient.getDisplayName())&& payAmount>=billAmount){
			billTbl.setPaidAmount(billAmount);
			billTbl.setBillStatus(BillStatusEnum.Closed);
			float paidAmt=billAmount-prevPaid;
			billPaymentTbl.setAmountPaid(paidAmt);		
		}
		else{
			billTbl.setPaidAmount(payAmount);
			billPaymentTbl.setAmountPaid(payment.getPaidAmount());	
		}
	
		billPaymentTbl.setBillNo(billTbl.getUid());
		billPaymentTbl.setPaymentDate(new Date());
		billPaymentTbl.setBillTbl(billTbl);
		save(billPaymentTbl);

		if(billTbl.getPaidAmount()!=0 && billTbl.getPaidAmount()<billAmount)
			billTbl.setPaymentStatus(PayStatusEnum.PartiallyPaid);
		if(billTbl.getPaidAmount()==billAmount || billTbl.getPaidAmount()>billAmount)
			billTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
		if(billTbl.getPaidAmount()==0 && billAmount>0)
			billTbl.setPaymentStatus(PayStatusEnum.NotPaid);

		billTbl.setNote(payment.getNote());
		Date updatedDateTime = new Date();
		billTbl.setUpdatedDateTime(updatedDateTime);
		update(billTbl);
		response.setId(billTbl.getId());
		response.setUid(billTbl.getUid());
		if(billTbl.getPatientTbl()!=null)
			response.setPatientName(billTbl.getPatientTbl().getFirstName());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	
	private List<BillPaymentDTO> getBillPaymentDTOList(int id) throws PersistenceException {
		List<BillPaymentDTO>  billPaymentList=new ArrayList<BillPaymentDTO>();
		List<BillPaymentTbl>paymentList=(ArrayList<BillPaymentTbl>)getPaymentsByBill(id);

		for (BillPaymentTbl billPaymentTbl : paymentList) {
			BillPaymentDTO billDto=new BillPaymentDTO();
			DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
			billDto.setPaymentDate(df.format(billPaymentTbl.getPaymentDate()));
			billDto.setPaidAmount(billPaymentTbl.getAmountPaid());
			billDto.setNote(billPaymentTbl.getDescription());
			billDto.setBillUid(billPaymentTbl.getBillNo());
			billPaymentList.add(billDto);
		}
		return billPaymentList;
	}
	private List<BillPaymentTbl> getPaymentsByBill(int billId) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_PAYMENT_BY_BILLID);	
		query.setParameter("param1", billId);
		return executeQuery(BillPaymentTbl.class,query);
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#getNewBills()
	 */
	@Override
	@Transactional(readOnly=false)
	public List<BillSummaryDTO> getNewBills() throws PersistenceException {
		List<BillSummaryDTO> response=new ArrayList<BillSummaryDTO>();
		SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		List<BillTbl>billTblList=(ArrayList<BillTbl>)getnewilyCreatedBill();
		if(billTblList!=null){
			for (BillTbl billTbl : billTblList) {
				BillSummaryDTO billSummaryDTO=new BillSummaryDTO();
				billSummaryDTO.setUid(billTbl.getUid());
				billSummaryDTO.setAmountPaid(billTbl.getPaidAmount());
				billSummaryDTO.setBillAmount(billTbl.getBillAmount());
				//billSummaryDTO.setGlobalId(billTbl.getGlobalId());
				billSummaryDTO.setBillStatus(billTbl.getBillStatus().getDisplayName());
				if(billTbl.getCreatedOn()!=null)
					billSummaryDTO.setOrderDate(df.format(billTbl.getCreatedOn()));
				if(billTbl.getPatientTbl()!=null){
					billSummaryDTO.setPatientGlobalId(billTbl.getPatientTbl().getGlobalId());
					billSummaryDTO.setPatientName(billTbl.getPatientTbl().getFirstName());
				}
				if(billTbl.getPaymentStatus()!=null)
					billSummaryDTO.setPayStatus(billTbl.getPaymentStatus().getDisplayName());
				response.add(billSummaryDTO);
			}
		}
		return response;
	}

	public List<BillTbl>getnewilyCreatedBill() throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_NEW_BILLS);
		//query.setParameter("param1", 0);
		return executeQuery(BillTbl.class, query);
	}
	
	
	/**
	 * @return List<BillSummaryDTO>
	 * @throws PersistenceException 
	 */
	public List<BillSummaryDTO> getUpdatedBills() throws PersistenceException{
		SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
		List<BillSummaryDTO> response=new ArrayList<BillSummaryDTO>();
		SyncTbl syncTbl=getSyncTbl();
		if(syncTbl!=null){	
			List<BillTbl> billTblList=(ArrayList<BillTbl>)getUpdateList(syncTbl.getUploadedTime());
			for (BillTbl billTbl : billTblList) {
				BillSummaryDTO billSummaryDTO=new BillSummaryDTO();
				billSummaryDTO.setUid(billTbl.getUid());
				billSummaryDTO.setAmountPaid(billTbl.getPaidAmount());
				billSummaryDTO.setBillAmount(billTbl.getBillAmount());
				billSummaryDTO.setGlobalId(billTbl.getGlobalId());
				billSummaryDTO.setBillStatus(billTbl.getBillStatus().getDisplayName());
				if(billTbl.getCreatedOn()!=null)
					billSummaryDTO.setOrderDate(df.format(billTbl.getCreatedOn()));
				if(billTbl.getPatientTbl()!=null){
					billSummaryDTO.setPatientGlobalId(billTbl.getPatientTbl().getGlobalId());
					billSummaryDTO.setPatientName(billTbl.getPatientTbl().getFirstName());
				}
				if(billTbl.getPaymentStatus()!=null)
					billSummaryDTO.setPayStatus(billTbl.getPaymentStatus().getDisplayName());
				response.add(billSummaryDTO);
			}
		}
		return response;
	}
	public List<BillTbl> getUpdateList(Date lastUploadedTime ) throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_UPDATED_BILLS);
		query.setParameter("param1", lastUploadedTime);
		return executeQuery(BillTbl.class, query);
	}
	public SyncTbl getSyncTbl() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(Query.GET_LAST_SYNC_TIME);

		return executeUniqueQuery(SyncTbl.class, query);
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#addBillSyncResponse(com.nv.netmd.rs.dto.BillSyncResponseDTO)
	 */
	@Override
	@Transactional
	public void addBillSyncResponse(BillSyncResponseDTO billResponse)  throws PersistenceException{
		if(billResponse.getError()==null){
			BillTbl billTbl=(BillTbl)getByBillUid(billResponse.getUid());
			if (billTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.InvalidId);
				se.addParam(new Parameter(Constants.ID,billResponse.getUid()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			billTbl.setGlobalId(billResponse.getGlobalId());
			update(billTbl);
		}
		
	}
	private BillTbl getByBillUid(String uid) {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BILL_BY_UID);
		query.setParameter("param1",uid);
		return (BillTbl)query.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.billing.pl.dao.BillDao#updateBillSyncResponse(com.nv.netmd.rs.dto.BillSyncResponseDTO)
	 */
	@Override
	@Transactional
	public void updateBillSyncResponse(BillSyncResponseDTO billResponse) throws PersistenceException {
		DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT);
		// TODO Auto-generated method stub
		if(billResponse.getError()!=null){
			BillTbl billTbl=(BillTbl)getByBillUid(billResponse.getUid());
			if (billTbl == null) {
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.InvalidId);
				se.addParam(new Parameter(Constants.ID,billResponse.getUid()));
				se.setDisplayErrMsg(true);
				throw se;
			}
			if(billTbl.getBillStatus().equals(BillStatusEnum.Cancelled)){
				PersistenceException se =new PersistenceException(ErrorCodeEnum.CantUpdate);			
				se.setDisplayErrMsg(true);
				throw se;
			}	
			try {
				if(billResponse.getUpdateDateTime()!=null)
					billTbl.setUpdatedDateTime(df.parse(billResponse.getUpdateDateTime()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				//log.error("Error while updating the doctor",e);
				e.printStackTrace();
			}
			update(billTbl);
		}
		
	}
	@Override
	@Transactional(readOnly=false)
	public BillResponseDTO setBillStatus(String uid,float billAmount) throws PersistenceException{
		BillResponseDTO response= new BillResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(uid);
		updatePaymentTbl(billTbl,billAmount);//update payment table
		billTbl.setPaidAmount(billAmount);
		billTbl.setBillStatus(BillStatusEnum.Closed);
		update(billTbl);
		if(billTbl.getPatientTbl()!=null)
			response.setPatientName(billTbl.getPatientTbl().getFirstName());
		  response.setId(billTbl.getId());
		  response.setBillStatus(billTbl.getBillStatus().getDisplayName());
		  response.setOrigin(billTbl.getOrigin().getDisplayName());
		  return response;
	}

/*	@Override
	public BillResponseDTO discharge(String uid) {
		BillResponseDTO response=new BillResponseDTO();
		BillTbl billTbl=(BillTbl)getBillByUid(uid);
		if(billTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBillFound);
			se.addParam(new Parameter(Constants.ID,uid));
			se.setDisplayErrMsg(true);
			throw se;
		}
	
		return response;
	}*/
	public double getTotalAmountPaid(String uid){
		double sum=0;
		sum =getBillPaymentAmount(uid);
		return sum;
		
	}
   public float getBillAmount(String uid){
	   float grdtotal=0;
	   grdtotal=getGrandTotal(uid);
	   return grdtotal;
   }
	private double  getBillPaymentAmount(String uid) {
		double totalAmount=0;
		javax.persistence.Query query = em
				.createQuery(Query.GET_TOTAL_AMOUNT_PAID);
		query.setParameter("param1",uid);
		totalAmount= (Double) query.getSingleResult();
		return totalAmount;
	}
	private float  getGrandTotal(String uid) {
		float billAmount=0;
		javax.persistence.Query query = em
				.createQuery(Query.GET_GRAND_TOTAL);
		query.setParameter("param1",uid);
		billAmount= (Float) query.getSingleResult();
		return billAmount;
	}

	@Override
	public BillResponseDTO discharge(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly=false)
	public NetMdDTO getlogo()  throws PersistenceException{
		NetMdDTO netmd=new NetMdDTO();
		NetmdTbl netmdTbl=getnetmdLogo();
		if(netmdTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.InvalidId);
			//se.addParam(new Parameter(Constants.ID,billResponse.getUid()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		netmd.setLogo(netmdTbl.getLogo());
		return netmd;
	}

	private NetmdTbl getnetmdLogo() throws PersistenceException{
		javax.persistence.Query query = em
				.createQuery(Query.GET_LOGO);
		return executeUniqueQuery(NetmdTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public BillListResponseDTO getBills() throws PersistenceException {
		BillListResponseDTO response = new BillListResponseDTO();
		List<BillDTO> billDTOList=new ArrayList<BillDTO>();
		List<BillTbl> billLis=(ArrayList<BillTbl>)getBillList();
		String payStatus=null;
		String origin=null;
		int patId=0;
		String patientName=null;
		String date=null;
		if (billLis == null) {
			return response;
		}		
		for (BillTbl billTbl : billLis) {
			if(billTbl.getPatientTbl()!=null){
				patId=billTbl.getPatientTbl().getId();
				patientName=billTbl.getPatientTbl().getFirstName();
			}
			if(billTbl.getCreatedOn()!=null){
				SimpleDateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
				date=df.format(billTbl.getCreatedOn());
			}
			origin=	billTbl.getOrigin().getDisplayName();
			payStatus=	billTbl.getPaymentStatus().getDisplayName();
			billDTOList.add(new BillDTO(billTbl.getPatientTbl().getId(),billTbl.getPatientTbl().getFirstName()+" "+billTbl.getPatientTbl().getLastName(),origin,payStatus,billTbl.getId(),billTbl.getUid(),date,billTbl.getGrandTotal(),billTbl.getPaidAmount(),billTbl.getBillAmount(),billTbl.getBillStatus().getDisplayName()));
		}
		
		response.setBillList(billDTOList);
		return response;
		}
	
	public List<BillTbl> getBillList() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_BILLS);		
		return executeQuery(BillTbl.class, query);
	}
	
	
}
