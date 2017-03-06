/**
 * ItemDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 16-Aug-2013
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
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.TaxTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.ItemDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class ItemDaoImpl extends GenericDaoHibernateImpl implements ItemDao {


	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(ItemDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ItemDao#create(com.nv.netmd.rs.dto.ItemDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(ItemDTO item)  throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String itemName = item.getName().trim().replaceAll(" +", " ");
		ItemTbl itm=(ItemTbl)getItemByName(itemName);
		if(itm!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ItemWithNameExist);
			se.addParam(new Parameter(Constants.NAME,item.getName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		ItemTbl itemTbl=new  ItemTbl();
		itemTbl.setName(item.getName());
		itemTbl.setPrice(item.getPrice());
		itemTbl.setQuantity(item.getQuantity());
		itemTbl.setDescription(item.getDescription());
		if(item.getTaxId()!=0){
			TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,item.getTaxId());
			if(taxTbl==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoTaxFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(item.getTaxId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			itemTbl.setTaxTbl(taxTbl);
		}
		StatusEnum status=StatusEnum.Active;
		itemTbl.setStatus(status);
		save(itemTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(itemTbl.getId());

		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ItemDao#delete(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class, id);
		if(itemTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoItemFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//itemTbl.setStatus(status);
		//update(itemTbl);
		delete(itemTbl);
		response.setError(null);;
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ItemDao#update(com.nv.netmd.rs.dto.ItemDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(ItemDTO item)   throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class, item.getId());
		if(itemTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoItemFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(item.getId())));
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
		String itemName = item.getName().trim().replaceAll(" +", " ");
		ItemTbl itm=(ItemTbl)getItemByName(itemName);
		if(itm!=null){
			if(itm.getId()!=itemTbl.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.ItemWithNameExist);
				se.addParam(new Parameter(Constants.NAME,item.getName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(item.getTaxId()!=0){
			TaxTbl taxTbl=(TaxTbl)getById(TaxTbl.class,item.getTaxId());
			if(taxTbl==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoTaxFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(item.getTaxId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			itemTbl.setTaxTbl(taxTbl);
		}
		itemTbl.setName(item.getName());
		itemTbl.setPrice(item.getPrice());
		itemTbl.setQuantity(item.getQuantity());
		itemTbl.setDescription(item.getDescription());
		StatusEnum status=StatusEnum.Active;
		itemTbl.setStatus(status);
		update(itemTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(itemTbl.getId());
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ItemDao#view(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public ItemDTO view(int id)  throws PersistenceException {
		ItemDTO response=new ItemDTO();
		ItemTbl itemTbl=(ItemTbl)getById(ItemTbl.class, id);
		if(itemTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoItemFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(itemTbl.getId());
		response.setName(itemTbl.getName());
		response.setDescription(itemTbl.getDescription());
		response.setPrice(itemTbl.getPrice());
		response.setQuantity(itemTbl.getQuantity());
		if(itemTbl.getTaxTbl()!=null){
			response.setTaxId(itemTbl.getTaxTbl().getId());
			response.setTaxName(itemTbl.getTaxTbl().getName());

		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/**
	 * get item by name
	 * @param name
	 * @return ItemTbl
	 * @throws PersistenceException 
	 */
	public ItemTbl getItemByName(String name) throws PersistenceException {
		String itemName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_ITEM_BY_NAME);
		query.setParameter("param1",itemName);		
		return executeUniqueQuery(ItemTbl.class, query);
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ItemDao#getItems()
	 */
	@Override
	@Transactional(readOnly=false)
	public ItemListResponseDTO getItems() throws PersistenceException {
		ItemListResponseDTO response=new ItemListResponseDTO();
		List<ItemDTO> itemList=new ArrayList<ItemDTO>();
		List<ItemTbl> itemTblList=(ArrayList<ItemTbl>)getItemList();
		for (ItemTbl itemTbl : itemTblList) {
			float amount = 0;
			float stdRate=0;
			float taxVal=0;
			String taxType=null;
			ItemDTO item=new ItemDTO();
			item.setId(itemTbl.getId());
			item.setName(itemTbl.getName());
			item.setDescription(itemTbl.getDescription());
			item.setQuantity(itemTbl.getQuantity());
			item.setPrice(itemTbl.getPrice());
			if(itemTbl.getTaxTbl()!=null){
				item.setTaxId(itemTbl.getTaxTbl().getId());
				item.setTaxName(itemTbl.getTaxTbl().getName());				
				item.setTaxValue(itemTbl.getTaxTbl().getTaxVal());				
				taxVal=itemTbl.getTaxTbl().getTaxVal();
				
				if(itemTbl.getTaxTbl().getCalculationType()!=null){
					
					item.setTaxType(itemTbl.getTaxTbl().getCalculationType().getDisplayName());
					taxType=itemTbl.getTaxTbl().getCalculationType().getDisplayName();
					if(taxType==CalculationTypeEnum.Fixed.getDisplayName())
						amount=taxVal;
					else
						amount=itemTbl.getPrice()*taxVal/100;
				}
			}
			
			stdRate=itemTbl.getPrice()+amount;
			item.setStdRate(stdRate);			
			itemList.add(item);

		}
		response.setItemList(itemList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private List<ItemTbl> getItemList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_ITEM);
		return executeQuery(ItemTbl.class, query);
	}

}
