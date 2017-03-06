/**
 * BlockDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 26, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.impl;

import java.lang.reflect.Array;
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
import com.nv.netmd.pl.entity.BlockTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.BlockDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class BlockDaoImpl extends GenericDaoHibernateImpl implements BlockDao{
	
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(BlockDaoImpl.class);
	/**
	 * create block
	 * @param block
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(BlockDTO block)  throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		BlockTbl blockTbl=new BlockTbl();
		BlockTbl blo=(BlockTbl)getBlockByName(block.getName());
		if(blo!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.BlockWithNameExist);
			se.addParam(new Parameter(Constants.NAME,block.getName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		blockTbl.setName(block.getName());
		blockTbl.setDescription(block.getDescription());
		StatusEnum status=StatusEnum.Active;
		blockTbl.setStatus(status);
		save(blockTbl);
		response.setError(null);
		response.setId(blockTbl.getId());
		response.setSuccess(true);
		return response;
	}

	/** 
	 * Delete or deactivate block
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id)  throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		BlockTbl blockTbl=(BlockTbl)getById(BlockTbl.class,id);
		if(blockTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBlockFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//blockTbl.setStatus(status);
		//update(blockTbl);
		delete(blockTbl);
		response.setId(blockTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/** 
	 * update block
	 * @param BlockDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(BlockDTO block)   throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		BlockTbl blockTbl=(BlockTbl)getById(BlockTbl.class, block.getId());
		if(blockTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBlockFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(block.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String blockName = block.getName().trim().replaceAll(" +", " ");
		
		BlockTbl bloc=(BlockTbl)getBlockByName(blockName);
		if(bloc!=null){
			if(bloc.getId()!=blockTbl.getId()){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.BlockWithNameExist);
			se.addParam(new Parameter(Constants.NAME,block.getName()));
			se.setDisplayErrMsg(true);
			throw se;
			}
		}
		blockTbl.setName(block.getName());
		blockTbl.setDescription(block.getDescription());
		StatusEnum status=StatusEnum.Active;
		blockTbl.setStatus(status);
		update(blockTbl);
		response.setId(blockTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
		
	}

	/** 
	 * View block
	 * @param id
	 * @return BlockDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public BlockDTO view(int id)  throws PersistenceException {
		BlockDTO response=new BlockDTO();
		BlockTbl blockTbl=(BlockTbl)getById(BlockTbl.class,id);
		if(blockTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBlockFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(blockTbl.getId());
		response.setName(blockTbl.getName());
		response.setDescription(blockTbl.getDescription());
		response.setSuccess(true);
		response.setError(null);
		return response;
	}
	/**
	 * get block by name
	 * @param name
	 * @return BlockTbl
	 * @throws PersistenceException 
	 */
	public BlockTbl getBlockByName(String name) throws PersistenceException {
		String blocName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_BLOCK_BY_NAME);
		query.setParameter("param1",blocName);		
		return executeUniqueQuery(BlockTbl.class, query);
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.BlockDao#getBlockByName()
	 */
	@Override
	@Transactional(readOnly=false)
	public BlockListResponseDTO getBlocks() throws PersistenceException {
		BlockListResponseDTO blockList=new BlockListResponseDTO();
		List<BlockDTO>blockDTOList=new ArrayList<BlockDTO>();
		List<BlockTbl> blockTblList=(ArrayList<BlockTbl>)getBlockList();
		for (BlockTbl blockTbl : blockTblList) {
			BlockDTO block=new BlockDTO();
			block.setId(blockTbl.getId());
			block.setName(blockTbl.getName());
			blockDTOList.add(block);
		}
		blockList.setBlock(blockDTOList);
		blockList.setError(null);
		blockList.setSuccess(true);
		return blockList;
	}
	private List<BlockTbl> getBlockList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_BLOCKS);
		return executeQuery(BlockTbl.class, query);
	}
}
