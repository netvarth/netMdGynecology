/**
 * TaxDaoImpl.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
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
import org.springframework.transaction.annotation.Transactional;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.BedTbl;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.pl.entity.SupportTbl;
import com.nv.netmd.pl.entity.TaxTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.TaxDTO;
import com.nv.netmd.rs.dto.TaxListResponseDTO;
import com.nv.netmd.rs.dto.TaxViewResponseDTO;
import com.nv.netmd.settings.pl.dao.TaxDao;


/**
 *
 *
 * @author Leonora Louis
 */
public  class TaxDaoImpl extends GenericDaoHibernateImpl implements TaxDao {
	@PersistenceContext()
	private EntityManager em;
	/** 
	 *create tax
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(TaxDTO taxdto) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		TaxTbl taxtbl=(TaxTbl)getTaxByName(taxdto.getName());
		if(taxtbl!=null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.InValidName);
			se.setDisplayErrMsg(false);
			throw se;
		}
		TaxTbl taxtable=new TaxTbl();
		taxtable.setName(taxdto.getName());
		CalculationTypeEnum cal=CalculationTypeEnum.getEnum(taxdto.getCalculationType());
		taxtable.setCalculationType(cal);
		taxtable.setDescription(taxdto.getDescription());
		taxtable.setTaxVal(taxdto.getTaxVal());
		taxtable.setDescription(taxdto.getDescription());
		save(taxtable);
		response.setSuccess(true);
		response.setId(taxtable.getId());
		response.setError(null);
		return response;
	}



	/**
	 * get tax by name
	 * @param name
	 * @return TaxTbl
	 * @throws PersistenceException 
	 */

	private TaxTbl getTaxByName(String name) throws PersistenceException {
		String taxName=name.toUpperCase();
		javax.persistence.Query query=em.createQuery(Query.GET_TAX_BY_NAME);
		query.setParameter("param1", taxName);
		return executeUniqueQuery(TaxTbl.class,query);

	}


	/**
	 * view tax
	 * @param id
	 * @return TaxViewResponseDTO
	 */

	@Override
	@Transactional(readOnly=false)
	public TaxViewResponseDTO view(int id) throws PersistenceException {
		TaxTbl taxtbl=(TaxTbl)getById(TaxTbl.class,id);		// TODO Auto-generated method stub
		if(taxtbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoTaxFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;

		}
		TaxViewResponseDTO response=new TaxViewResponseDTO();
		TaxDTO taxdto=new TaxDTO();
		taxdto.setId(taxtbl.getId());
		taxdto.setName(taxtbl.getName());
		taxdto.setDescription(taxtbl.getDescription());
		taxdto.setTaxVal(taxtbl.getTaxVal());
		if(taxtbl.getCalculationType()!=null)
			taxdto.setCalculationType(taxtbl.getCalculationType().getDisplayName());
		response.setTax(taxdto);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * delete tax
	 * @param id
	 * @return {@link ResponseDTO}
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		TaxTbl taxtbl=(TaxTbl)getById(TaxTbl.class,id);
		if(taxtbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoTaxFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;

		}
		delete(taxtbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/** 
	 *update taxes
	 *@param taxdto
	 *@return {@link ResponseDTO}
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(TaxDTO taxdto) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		TaxTbl taxtbl=(TaxTbl)getById(TaxTbl.class,taxdto.getId());
		if(taxtbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoTaxFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(taxdto.getId())));
			se.setDisplayErrMsg(true);
			throw se;

		}
		TaxTbl taxtable=(TaxTbl)getTaxByName(taxdto.getName());
		if(taxtable!=null){
			if(taxtbl.getId()!=taxtable.getId()){
				PersistenceException se=new PersistenceException(ErrorCodeEnum.InValidName);
				se.addParam(new Parameter(Constants.NAME,taxdto.getName()));
				se.setDisplayErrMsg(false);
				throw se;
			}
		}

		taxtbl.setName(taxdto.getName());
		taxtbl.setTaxVal(taxdto.getTaxVal());
		taxtbl.setDescription(taxdto.getDescription());
		CalculationTypeEnum cal=CalculationTypeEnum.getEnum(taxdto.getCalculationType());
		taxtbl.setCalculationType(cal);
		update(taxtbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(taxtbl.getId());
		// TODO Auto-generated method stub
		return response;
	}



	/** 
	 * get all the taxes
	 */
	@Override
	@Transactional(readOnly=false)
	public TaxListResponseDTO getTaxes() throws PersistenceException {
		TaxListResponseDTO response=new TaxListResponseDTO();
		List<TaxDTO> taxDTOList=new ArrayList<TaxDTO>();
		List<TaxTbl> taxTblList=(ArrayList<TaxTbl>)getTaxList();
		for (TaxTbl taxTbl : taxTblList) {
			TaxDTO taxDTO=new TaxDTO();
			taxDTO.setId(taxTbl.getId());
			taxDTO.setName(taxTbl.getName());
			taxDTO.setTaxVal(taxTbl.getTaxVal());
			taxDTO.setCalculationType(taxTbl.getCalculationType().toString());
			taxDTOList.add(taxDTO);
		}
		response.setTaxlist(taxDTOList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	private List<TaxTbl> getTaxList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_TAX);
		return executeQuery(TaxTbl.class, query);
	}
	/**
	 * get tax of an item
	 */
	@Override
	@Transactional(readOnly=false)
	public float getItemTaxAmount(int id) throws PersistenceException{
		float price=0;
		float tot=0;
		float taxval=0;
		ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class, id);
		if(itemTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoItemFound);
			se.setDisplayErrMsg(true);
			throw se;

		}
		price=itemTbl.getPrice();
		if(itemTbl.getTaxTbl()!=null){
			if(itemTbl.getTaxTbl().getTaxVal()!=0){
				taxval=itemTbl.getTaxTbl().getTaxVal();
				if(itemTbl.getTaxTbl().getCalculationType().equals(CalculationTypeEnum.Percentage)){
					tot=price*(taxval/100);
				}
				else
					tot=taxval;
			}
		}
		price+=tot;
		return price;
	}
	/**
	 * get tax of an bed
	 */
	@Override
	@Transactional(readOnly=false)
	public float getBedTaxAmount(int id) throws PersistenceException{
		float price=0;
		float tot=0;
		float taxVal=0;
		BedTbl bedTbl=(BedTbl)getById(BedTbl.class, id);
		if(bedTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoBedFound);
			se.setDisplayErrMsg(true);
			throw se;
		}

		if(bedTbl.getBedTypeTbl()!=null){			
			price=bedTbl.getBedTypeTbl().getRent();

			if(bedTbl.getBedTypeTbl().getTaxTbl()!=null){
				if(bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal()!=0){
					taxVal=bedTbl.getBedTypeTbl().getTaxTbl().getTaxVal();
					if(bedTbl.getBedTypeTbl().getTaxTbl().getCalculationType().equals(CalculationTypeEnum.Percentage)){
						tot=price*(taxVal/100);
					}
					else
						tot=taxVal;
				}
			}
			price+=tot;
		}
		return price;
	}
	/**
	 * get taxed amount of an support
	 */
	@Override
	@Transactional(readOnly=false)
	public float getSupportTaxAmount(int id) throws PersistenceException{
		float price=0;
		float taxVal=0;
		float tot=0;
		SupportTbl supportTbl=(SupportTbl)getById(SupportTbl.class, id);
		if(supportTbl==null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.NoServiceFound);
			se.setDisplayErrMsg(true);
			throw se;
		}
		price=supportTbl.getPrice();

		if(supportTbl.getTaxTbl()!=null){
			if(supportTbl.getTaxTbl().getTaxVal()!=0){
				taxVal=supportTbl.getTaxTbl().getTaxVal();
				if(supportTbl.getTaxTbl().getCalculationType().equals(CalculationTypeEnum.Percentage)){
					tot=price*(taxVal/100);
				}
				else
					tot=taxVal;
			}
		}
		price+=tot;
		return price;
	}


}
