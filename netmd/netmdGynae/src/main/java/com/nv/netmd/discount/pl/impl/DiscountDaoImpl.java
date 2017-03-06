/**  	 
 * DiscountDaoImpl.java	
 * @author Sreeram T G 
 *
 * Version 1.0 22-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.discount.pl.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.common.Constants;
import com.nv.netmd.discount.pl.dao.DiscountDao;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.DiscountTbl;
import com.nv.netmd.pl.entity.DiscountTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.PatientTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.DiscountDTO;
import com.nv.netmd.rs.dto.DiscountListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public class DiscountDaoImpl extends GenericDaoHibernateImpl implements DiscountDao{
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(DiscountDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.pl.dao.DiscountDao#create(com.nv.netmd.rs.dto.DiscountDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(DiscountDTO discount)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String discName = discount.getName().trim().replaceAll(" +", " ");
		DiscountTbl dis=(DiscountTbl)getDiscountByName(discName);
		if(dis!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DiscountWithNameExist);
			se.addParam(new Parameter(Constants.NAME,discount.getName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		DiscountTbl discountTbl=new DiscountTbl();
		discountTbl.setName(discount.getName());
		discountTbl.setDiscountValue(discount.getDiscValue());
		discountTbl.setDescription(discount.getDescription());
		CalculationTypeEnum calu=CalculationTypeEnum.getEnum(discount.getCalculationType());
		discountTbl.setCalculationType(calu);
		DiscountTypeEnum disc=DiscountTypeEnum.getEnum(discount.getDiscType());
		discountTbl.setDiscountType(disc);
		discountTbl.setStatus(StatusEnum.Active);
		save(discountTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(discountTbl.getId());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.pl.dao.DiscountDao#delete(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id)throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		DiscountTbl discountTbl=(DiscountTbl)getById(DiscountTbl.class,id);
		if(discountTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDiscountFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		discountTbl.setStatus(StatusEnum.Inactive);
		update(discountTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.pl.dao.DiscountDao#getDiscounts()
	 */
	@Override
	@Transactional(readOnly=false)
	public DiscountListResponseDTO getDiscounts() throws PersistenceException {
		DiscountListResponseDTO discountList=new DiscountListResponseDTO();
		List<DiscountDTO> discountListDTO = new ArrayList<DiscountDTO>();
		List<DiscountTbl>discountTblList=(ArrayList<DiscountTbl>)getDiscountList();
		for (DiscountTbl discountTbl : discountTblList) {
			DiscountDTO discountDTO=new DiscountDTO();
			discountDTO.setId(discountTbl.getId());
			discountDTO.setName(discountTbl.getName());
			discountDTO.setDescription(discountDTO.getDescription());
			discountDTO.setCalculationType(discountTbl.getCalculationType().getDisplayName());
			discountDTO.setDiscType(discountTbl.getDiscountType().getDisplayName());
			discountDTO.setDiscValue(discountDTO.getDiscValue());
			discountListDTO.add(discountDTO);
		}
		discountList.setDiscount(discountListDTO);
		discountList.setSuccess(true);
		discountList.setError(null);
		return discountList;
	}

	private List<DiscountTbl> getDiscountList() throws PersistenceException {
		javax.persistence.Query query = em
				.createQuery(Query.GET_DISCOUNTS);		
		return executeQuery(DiscountTbl.class, query);
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.pl.dao.DiscountDao#update(com.nv.netmd.rs.dto.DiscountDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(DiscountDTO discount) throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		DiscountTbl discountTbl=(DiscountTbl)getById(DiscountTbl.class,discount.getId());
		if(discountTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDiscountFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(discount.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}

		String discName = discount.getName().trim().replaceAll(" +", " ");		
		DiscountTbl dis=(DiscountTbl)getDiscountByName(discName);
		if(dis!=null){
			if(discountTbl.getId()!=dis.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.DiscountWithNameExist);
				se.addParam(new Parameter(Constants.NAME,discount.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}

		}		
		discountTbl.setName(discount.getName());
		discountTbl.setDiscountValue(discount.getDiscValue());
		discountTbl.setDescription(discount.getDescription());
		CalculationTypeEnum calu=CalculationTypeEnum.getEnum(discount.getCalculationType());
		discountTbl.setCalculationType(calu);
		DiscountTypeEnum disc=DiscountTypeEnum.getEnum(discount.getDiscType());
		discountTbl.setDiscountType(disc);
		discountTbl.setStatus(StatusEnum.Active);
		update(discountTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(discountTbl.getId());
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.discount.pl.dao.DiscountDao#view(int)
	 */
	@Override
	public DiscountDTO view(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public DiscountTbl getDiscountByName(String name) throws PersistenceException {
		String discountName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_DISCOUNT_BY_NAME);
		query.setParameter("param1",discountName);		
		return executeUniqueQuery(DiscountTbl.class, query);
	}

	@Override
	@Transactional(readOnly=false)
	public DiscountDTO getDiscountById(int id) throws PersistenceException {
		DiscountTbl discountTbl=(DiscountTbl)getById(DiscountTbl.class, id);
		if(discountTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDiscountFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		DiscountDTO discount=new DiscountDTO();
		discount.setId(discountTbl.getId());
		discount.setName(discountTbl.getName());
		discount.setDescription(discountTbl.getDescription());
		if(discountTbl.getCalculationType()!=null)
			discount.setCalculationType(discountTbl.getCalculationType().getDisplayName());
		if(discountTbl.getDiscountType()!=null)
			discount.setDiscType(discountTbl.getDiscountType().getDisplayName());
		discount.setDiscValue(discountTbl.getDiscountValue());

		return discount;
	}
}
