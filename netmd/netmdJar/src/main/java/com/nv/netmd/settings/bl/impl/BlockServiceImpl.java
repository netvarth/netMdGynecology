/**
 * BlockServiceImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 26, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.impl;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.bl.service.BlockService;
import com.nv.netmd.settings.bl.validator.BlockValidator;
import com.nv.netmd.settings.pl.dao.BlockDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class BlockServiceImpl implements BlockService{
	private BlockDao blockDao;
	private BlockValidator blockValidator;
	private FilterService blockFilterService;
	/** 
	 * create block
	 * @param block
	 * @return  ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO create(BlockDTO block) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=blockValidator.validateCreateBlock(block);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=blockDao.create(block);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 *update block
	 *@param block
	 *@return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO update(BlockDTO block) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		ErrorDTO error=blockValidator.validateUpdateBlock(block);
		if (error != null) {
			response.setError(error);
			response.setSuccess(false);
			return response;
		}
		try {
			response=blockDao.update(block);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/**view block
	 * @param id
	 * @return BlockDTO
	 * @throws ServiceException 
	 *  
	 * 
	 */
	@Override
	public BlockDTO view(int id) throws ServiceException {
		BlockDTO response=new BlockDTO();
		try {
			response=blockDao.view(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	/** 
	 * delete block
	 * @param id
	 * @return ResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public ResponseDTO delete(int id) throws ServiceException {
		ResponseDTO response=new ResponseDTO();
		try {
			response=blockDao.delete(id);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}
	/** 
	 * list of blocks
	 * @param filterDTO
	 * @return BlockListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BlockListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		//System.out.println(filterDTO.getCount());
		BlockListResponseDTO blockList = new BlockListResponseDTO();
		ErrorDTO error=blockFilterService.validate(filterDTO);
		if (error != null) {
			blockList.setError(error);
			blockList.setSuccess(false);
			return blockList;
		}
		blockList =blockFilterService.list(filterDTO);
		return blockList;
	}
	/**
	 * @return the blockDao
	 */
	public BlockDao getBlockDao() {
		return blockDao;
	}

	/**
	 * @param blockDao the blockDao to set
	 */
	public void setBlockDao(BlockDao blockDao) {
		this.blockDao = blockDao;
	}

	/**
	 * @return the blockValidator
	 */
	public BlockValidator getBlockValidator() {
		return blockValidator;
	}

	/**
	 * @param blockValidator the blockValidator to set
	 */
	public void setBlockValidator(BlockValidator blockValidator) {
		this.blockValidator = blockValidator;
	}

	/**
	 * @return the blockFilterService
	 */
	public FilterService getBlockFilterService() {
		return blockFilterService;
	}

	/**
	 * @param blockFilterService the blockFilterService to set
	 */
	public void setBlockFilterService(FilterService blockFilterService) {
		this.blockFilterService = blockFilterService;
	}

	/** 
	 * get all blocks
	 * @return BlockListResponseDTO
	 * @throws ServiceException 
	 */
	@Override
	public BlockListResponseDTO getBlocks() throws ServiceException {
		BlockListResponseDTO response;
		try {
			response = blockDao.getBlocks();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		return response;
	}

	

}
