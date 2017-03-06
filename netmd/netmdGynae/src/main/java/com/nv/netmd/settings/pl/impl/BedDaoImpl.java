/**
 * BedDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.AvailabilityStatusEnum;
import com.nv.netmd.pl.entity.BedTbl;
import com.nv.netmd.pl.entity.BedTypeTbl;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.RoomTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.TaxTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.BedDTO;
import com.nv.netmd.rs.dto.BedListResponseDTO;
import com.nv.netmd.rs.dto.BedTypeDTO;
import com.nv.netmd.rs.dto.BedTypeListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.BedDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class BedDaoImpl extends GenericDaoHibernateImpl implements BedDao{
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(BedDaoImpl.class);
	/**
	 * create Bed type
	 * @param bedtype
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO createBedType(BedTypeDTO bedtype) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();

		String bedTypeName = bedtype.getType().trim().replaceAll(" +", " ");
		BedTypeTbl bedType=(BedTypeTbl)getBedTypeByName(bedTypeName);
		if(bedType!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.BedWithTypeExist);
			se.addParam(new Parameter(Constants.TYPE,bedtype.getType()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		BedTypeTbl bedTypeTbl=new BedTypeTbl();
		bedTypeTbl.setType(bedtype.getType());
		bedTypeTbl.setRent(bedtype.getRent());
		bedTypeTbl.setCount(bedtype.getCount());
		if(bedtype.getTaxId()!=0){
			TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,bedtype.getTaxId());
			if(taxTbl==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoTaxFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(bedtype.getTaxId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			bedTypeTbl.setTaxTbl(taxTbl);
		}
		StatusEnum status=StatusEnum.Active;
		bedTypeTbl.setStatus(status);
		save(bedTypeTbl);
		response.setError(null);
		response.setId(bedTypeTbl.getId());
		response.setSuccess(true);
		return response;

	}
	/**
	 * @param type
	 * @return BedTypeTbl
	 * @throws PersistenceException 
	 */
	private BedTypeTbl getBedTypeByName(String type) throws PersistenceException {
		String bedType= type.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_BED_BY_TYPE);
		query.setParameter("param1",bedType);		
		return executeUniqueQuery(BedTypeTbl.class, query);
	}
	/**
	 * view bed type
	 * @param id
	 * @return BedTypeDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BedTypeDTO viewBedType(int id)  throws PersistenceException{
		BedTypeDTO response=new BedTypeDTO();
		BedTypeTbl bedTypeTbl=(BedTypeTbl)getById(BedTypeTbl.class,id);
		if(bedTypeTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedTypeFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		if(bedTypeTbl.getTaxTbl()!=null){
			response.setTaxId(bedTypeTbl.getTaxTbl().getId());
			response.setTaxName(bedTypeTbl.getTaxTbl().getName());

		}
		response.setId(bedTypeTbl.getId());
		response.setType(bedTypeTbl.getType());
		response.setRent(bedTypeTbl.getRent());
		response.setCount(bedTypeTbl.getCount());
		response.setSuccess(true);
		response.setError(null);
		return response;
	}
	/**
	 * Delete bed type
	 * @param id
	 * @return ResponseDTO
	 * 
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO deleteBedType(int id) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		BedTypeTbl bedTypeTbl=(BedTypeTbl)getById(BedTypeTbl.class,id);
		if(bedTypeTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedTypeFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//bedTypeTbl.setStatus(status);
		//update(bedTypeTbl);
		delete(bedTypeTbl);
		response.setId(bedTypeTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/**
	 * Update bed type
	 * @param bedtype
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateBedType(BedTypeDTO bedtype) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();

		BedTypeTbl bedTypeTbl=(BedTypeTbl)getById(BedTypeTbl.class, bedtype.getId());
		if(bedTypeTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedTypeFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(bedtype.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String bedTypeName = bedtype.getType().trim().replaceAll(" +", " ");
		BedTypeTbl bedType=(BedTypeTbl)getBedTypeByName(bedTypeName);
		if(bedType!=null){
			if(bedTypeTbl.getId()!=bedType.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.BedWithTypeExist);
				se.addParam(new Parameter(Constants.TYPE,bedtype.getType()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(bedtype.getTaxId()!=0){
			TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,bedtype.getTaxId());
			if(taxTbl==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoTaxFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(bedtype.getTaxId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			bedTypeTbl.setTaxTbl(taxTbl);
		}
		bedTypeTbl.setType(bedtype.getType());
		bedTypeTbl.setRent(bedtype.getRent());
		bedTypeTbl.setCount(bedtype.getCount());
		StatusEnum status=StatusEnum.Active;
		bedTypeTbl.setStatus(status);
		update(bedTypeTbl);
		response.setError(null);
		response.setId(bedTypeTbl.getId());
		response.setSuccess(true);
		return response;

	}
	/** 
	 * create bed
	 * @param bed
	 * @return RespnseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO createBed(BedDTO bed) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String bedNum = bed.getBedNumber().trim().replaceAll(" +", " ");
		BedTbl bedTbl=(BedTbl)getBedByNumber(bedNum);
		if(bedTbl!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.BedNumberAlreadyExist);
			se.addParam(new Parameter(Constants.NAME,bed.getBedNumber()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		BedTbl beds=new BedTbl();
		beds.setPrefix(bed.getPrefix());
		beds.setBedNumber(bed.getBedNumber());
		beds.setDescription(bed.getDescription());
		BedTypeTbl bedTypeTbl=(BedTypeTbl)getById(BedTypeTbl.class,bed.getBedTypeId());
		if(bedTypeTbl==null){
			//error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedTypeFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(bed.getBedTypeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		beds.setBedTypeTbl(bedTypeTbl);
		RoomTbl roomTbl=(RoomTbl)getById(RoomTbl.class, bed.getRoomId());
		if(roomTbl==null){
			//error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(bed.getRoomId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		beds.setRoomTbl(roomTbl);
		StatusEnum status=StatusEnum.Active;
		beds.setStatus(status);
		save(beds);
		response.setId(beds.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/** 
	 *delete bed
	 *@param id
	 *@return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO deleteBed(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		BedTbl beds=(BedTbl)getById(BedTbl.class,id);
		if(beds==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//beds.setStatus(StatusEnum.Inactive);
		//update(beds);
		delete(beds);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/** 
	 * update bed
	 * @param bed
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateBed(BedDTO bed)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();

		BedTbl bedTbl=(BedTbl)getById(BedTbl.class,bed.getId());
		if(bedTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(bed.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String bedNum = bed.getBedNumber().trim().replaceAll(" +", " ");
		BedTbl bedTblName=(BedTbl)getBedByNumber(bedNum);
		if(bedTblName!=null){
			if(bedTblName.getId()!=bedTbl.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.BedNumberAlreadyExist);
				se.addParam(new Parameter(Constants.NAME,bed.getBedNumber()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		bedTbl.setPrefix(bed.getPrefix());
		bedTbl.setBedNumber(bed.getBedNumber());
		bedTbl.setDescription(bed.getDescription());
		BedTypeTbl bedTypeTbl=(BedTypeTbl)getById(BedTypeTbl.class,bed.getBedTypeId());
		if(bedTypeTbl==null){
			//error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedTypeFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(bed.getBedTypeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		bedTbl.setBedTypeTbl(bedTypeTbl);
		RoomTbl roomTbl=(RoomTbl)getById(RoomTbl.class, bed.getRoomId());
		if(roomTbl==null){
			//error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(bed.getRoomId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		bedTbl.setRoomTbl(roomTbl);
		StatusEnum status=StatusEnum.Active;		
		bedTbl.setStatus(status);
		AvailabilityStatusEnum available=AvailabilityStatusEnum.AVAILABLE;
		bedTbl.setAvailability(available);
		update(bedTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/**
	 * view bed
	 * @param id
	 * @return BedDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BedDTO viewBed(int id) throws PersistenceException {
		BedDTO response=new BedDTO();
		BedTbl beds=(BedTbl)getById(BedTbl.class,id);
		if(beds==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBedFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(beds.getId());
		response.setDescription(beds.getDescription());
		response.setPrefix(beds.getPrefix());
		response.setBedNumber(beds.getBedNumber());
		if(beds.getBedTypeTbl()!=null){
			response.setBedTypeId(beds.getBedTypeTbl().getId());
			response.setBedType(beds.getBedTypeTbl().getType());
		}
		if(beds.getRoomTbl()!=null){
			response.setRoomId(beds.getRoomTbl().getId());
			response.setRoomName(beds.getRoomTbl().getRoomNumber());
		}
		if(beds.getAvailability()!=null){
			response.setAvailability(beds.getAvailability().getDisplayName());
		}
		if(beds.getStatus()!=null){
			response.setStatus(beds.getStatus().getDisplayName());
		}
		response.setSuccess(true);
		response.setError(null);
		return response;
	}
	/**
	 * @param type
	 * @return BedTbl
	 * @throws PersistenceException 
	 */
	private BedTbl getBedByNumber(String type) throws PersistenceException {
		String bed= type.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_BED_BY_NUMBER);
		query.setParameter("param1",bed);		
		return executeUniqueQuery(BedTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public BedTypeListResponseDTO getBedTypes() throws PersistenceException{
		BedTypeListResponseDTO response=new BedTypeListResponseDTO();
		List<BedTypeDTO> bedTypeDTOList=new ArrayList<BedTypeDTO>();
		List<BedTypeTbl>bedTypeTblList=(ArrayList<BedTypeTbl>)getBedTypeList();
		for (BedTypeTbl bedTypeTbl : bedTypeTblList) {
			BedTypeDTO bedTypeDTO=new BedTypeDTO();
			bedTypeDTO.setId(bedTypeTbl.getId());
			bedTypeDTO.setType(bedTypeTbl.getType());
			bedTypeDTOList.add(bedTypeDTO);
		}
		response.setBedTypeList(bedTypeDTOList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	private List<BedTypeTbl> getBedTypeList() throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_BEDTYPE);
		return executeQuery(BedTypeTbl.class, query);
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.BedDao#getBeds()
	 */
	@Override
	@Transactional(readOnly=false)
	public BedListResponseDTO getBeds() throws PersistenceException {
		BedListResponseDTO response=new BedListResponseDTO();
		List<BedDTO> bedDTOList=new ArrayList<BedDTO>();
		List<BedTbl> bedTblList=(ArrayList<BedTbl>)getBedList();
		for (BedTbl bedTbl : bedTblList) {
			float amount = 0;
			float stdRate=0;
			float taxVal=0;
			String taxType=null;
			BedDTO bedDto=new BedDTO();
			bedDto.setId(bedTbl.getId());
			bedDto.setBedNumber(bedTbl.getBedNumber());
			if(bedTbl.getStatus()!=null)
				bedDto.setStatus(bedTbl.getStatus().getDisplayName());
			if(bedTbl.getAvailability()!=null)
			bedDto.setAvailability(bedTbl.getAvailability().getDisplayName());
			if(bedTbl.getBedTypeTbl()!=null){
				bedDto.setBedTypeId(bedTbl.getBedTypeTbl().getId());
				bedDto.setBedType(bedTbl.getBedTypeTbl().getType());;
				bedDto.setPrice(bedTbl.getBedTypeTbl().getRent());
				if(bedTbl.getBedTypeTbl().getTaxTbl()!=null)
					taxVal=bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal();
				if(bedTbl.getBedTypeTbl().getTaxTbl()!=null){
					bedDto.setTaxValue(bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal());
					taxType=bedTbl.getBedTypeTbl().getTaxTbl().getCalculationType().getDisplayName();
					if(taxType==CalculationTypeEnum.Fixed.getDisplayName())
						amount=taxVal;
					else
						amount=bedTbl.getBedTypeTbl().getRent()*taxVal/100;
				}
				
			}
			
			stdRate=bedTbl.getBedTypeTbl().getRent()+amount;
			bedDto.setStdRate(stdRate);
			bedDTOList.add(bedDto);
			
		}
		response.setBedList(bedDTOList);
		response.setError(null);
		response.setSuccess(true);
		
		return response;
	}
	private List<BedTbl> getBedList() throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_BEDS);
		return executeQuery(BedTbl.class, query);
	}
}
