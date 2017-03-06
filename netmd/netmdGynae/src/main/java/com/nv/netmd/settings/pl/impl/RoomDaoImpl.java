/**
 * RoomDaoImpl.java
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
import com.nv.netmd.pl.entity.BlockTbl;
import com.nv.netmd.pl.entity.DepartmentTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.RoomTbl;
import com.nv.netmd.pl.entity.RoomTypeTbl;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomListResponseDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;
import com.nv.netmd.rs.dto.RoomTypeListResponseDTO;
import com.nv.netmd.settings.pl.dao.RoomDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class RoomDaoImpl extends GenericDaoHibernateImpl implements RoomDao{
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(RoomDaoImpl.class);
	/** 
	 * create room
	 * @param RoomDTO
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO create(RoomDTO room)  throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String roomNum=room.getRoomNumber().trim().replaceAll(" +", " ");
		RoomTbl rooms=(RoomTbl)getByRoomNumber(roomNum);
		if(rooms!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.RoomWithNameExist);
			se.addParam(new Parameter(Constants.NAME,room.getRoomNumber()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		RoomTbl roomTbl=new RoomTbl();
		roomTbl.setRoomNumber(room.getRoomNumber());
		roomTbl.setPrefix(room.getPrefix());

		roomTbl.setDescription(room.getDescription());
		RoomTypeTbl roomTypeTbl=(RoomTypeTbl)getById(RoomTypeTbl.class,room.getRoomTypeId());
		if(roomTypeTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomTypeFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getRoomTypeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		roomTbl.setRoomTypeTbl(roomTypeTbl);
		DepartmentTbl departmentTbl=(DepartmentTbl)getById(DepartmentTbl.class,room.getDepartmentId());
		if(departmentTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDepartmentFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getDepartmentId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		roomTbl.setDepartmentTbl(departmentTbl);
		BlockTbl blockTbl=(BlockTbl)getById(BlockTbl.class,room.getBlockId());
		if(blockTbl==null){
			//error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBlockFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getBlockId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		roomTbl.setBlockTbl(blockTbl);
		StatusEnum status=StatusEnum.Active;
		roomTbl.setStatus(status);
		save(roomTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(roomTbl.getId());
		return response;

	}

	@Override
	@Transactional(readOnly=false)
	public RoomListResponseDTO getRooms() throws PersistenceException {
		RoomListResponseDTO response=new RoomListResponseDTO();
		List<RoomDTO>roomDTOList=new ArrayList<RoomDTO>();
		List<RoomTbl>roomTblList=(ArrayList<RoomTbl>)getRoomList();
		for (RoomTbl roomTbl : roomTblList) {
			RoomDTO roomDTO=new RoomDTO();
			roomDTO.setId(roomTbl.getId());
			roomDTO.setRoomNumber(roomTbl.getRoomNumber());
			roomDTOList.add(roomDTO);
		}
		response.setError(null);
		response.setRoom(roomDTOList);
		response.setSuccess(true);
		return response;
	}

	/**
	 * @return
	 * @throws PersistenceException 
	 */
	private List<RoomTbl> getRoomList() throws PersistenceException {
		javax.persistence.Query query = em.createQuery(Query.GET_ROOM);
		return executeQuery(RoomTbl.class, query);
	
	}

	/** 
	 * Delete Room
	 * @param id
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		RoomTbl roomTbl=(RoomTbl)getById(RoomTbl.class,id);
		if(roomTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//roomTbl.setStatus(status);
		//update(roomTbl);
		delete(roomTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}




	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.RoomDao#update(com.nv.netmd.rs.dto.RoomDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(RoomDTO room)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();	
		RoomTbl roomTbl=(RoomTbl)getById(RoomTbl.class,room.getId());
		if(roomTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String roomNum=room.getRoomNumber().trim().replaceAll(" +", " ");
		RoomTbl rooms=(RoomTbl)getByRoomNumber(roomNum);
		if(rooms!=null){
			if(roomTbl.getId()!=rooms.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.RoomWithNameExist);
				se.addParam(new Parameter(Constants.NAME,room.getRoomNumber()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}

		roomTbl.setRoomNumber(room.getRoomNumber());
		roomTbl.setPrefix(room.getPrefix());

		roomTbl.setDescription(room.getDescription());
		RoomTypeTbl roomTypeTbl=(RoomTypeTbl)getById(RoomTypeTbl.class,room.getRoomTypeId());
		if(roomTypeTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomTypeFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getRoomTypeId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		roomTbl.setRoomTypeTbl(roomTypeTbl);
		DepartmentTbl departmentTbl=(DepartmentTbl)getById(DepartmentTbl.class,room.getDepartmentId());
		if(departmentTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDepartmentFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getDepartmentId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		roomTbl.setDepartmentTbl(departmentTbl);
		BlockTbl blockTbl=(BlockTbl)getById(BlockTbl.class,room.getBlockId());
		if(blockTbl==null){
			//error
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoBlockFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(room.getBlockId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		roomTbl.setBlockTbl(blockTbl);
		StatusEnum status=StatusEnum.Active;
		roomTbl.setStatus(status);
		update(roomTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(roomTbl.getId());
		return response;
	}



	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.RoomDao#view(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public RoomDTO view(int id)  throws PersistenceException{
		RoomDTO response=new RoomDTO();
		RoomTbl roomTbl=(RoomTbl)getById(RoomTbl.class,id);
		if(roomTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(roomTbl.getId());
		response.setDescription(roomTbl.getDescription());
		response.setRoomNumber(roomTbl.getRoomNumber());
		response.setPrefix(roomTbl.getPrefix());
		if(roomTbl.getRoomTypeTbl()!=null){
			response.setRoomTypeId(roomTbl.getRoomTypeTbl().getId());
			response.setRoomTypeName(roomTbl.getRoomTypeTbl().getType());
		}
		if(roomTbl.getDepartmentTbl()!=null){
			response.setDepartmentId(roomTbl.getDepartmentTbl().getId());
			response.setDepartmentName(roomTbl.getDepartmentTbl().getName());
		}
		if(roomTbl.getBlockTbl()!=null){
			response.setBlockId(roomTbl.getBlockTbl().getId());
			response.setBlockName(roomTbl.getBlockTbl().getName());
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}


	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.RoomDao#createRoomType(com.nv.netmd.rs.dto.RoomTypeDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO createRoomType(RoomTypeDTO roomType) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		String roomTypeName = roomType.getType().trim().replaceAll(" +", " ");
		RoomTypeTbl roomTypeTbl=(RoomTypeTbl)getByType(roomTypeName);
		if(roomTypeTbl!=null){
			PersistenceException se=new PersistenceException(ErrorCodeEnum.RoomTypeWithTypeExist);
			se.addParam(new Parameter(Constants.TYPE,roomType.getType()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		RoomTypeTbl roomTypeTbls=new RoomTypeTbl();
		roomTypeTbls.setType(roomType.getType());
		roomTypeTbls.setCount(roomType.getCount());
		roomTypeTbls.setNoOfBed(roomType.getNoOfBeds());
		roomTypeTbls.setRent(roomType.getRent());
		StatusEnum status=StatusEnum.Active;
		roomTypeTbls.setStatus(status);
		save(roomTypeTbls);
		response.setId(roomTypeTbls.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.RoomDao#updateRoomType(com.nv.netmd.rs.dto.RoomTypeDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO updateRoomType(RoomTypeDTO roomType) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		RoomTypeTbl roomTypeTbls=(RoomTypeTbl)getById(RoomTypeTbl.class,roomType.getId());
		if(roomTypeTbls==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomTypeFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(roomType.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String roomTypeName = roomType.getType().trim().replaceAll(" +", " ");
		RoomTypeTbl roomTypeTbl=(RoomTypeTbl)getByType(roomTypeName);
		if(roomTypeTbl!=null){
			if(roomTypeTbls.getId()!=roomTypeTbl.getId()){
				PersistenceException se=new PersistenceException(ErrorCodeEnum.RoomTypeWithTypeExist);
				se.addParam(new Parameter(Constants.TYPE,roomType.getType()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		roomTypeTbls.setType(roomType.getType());
		roomTypeTbls.setCount(roomType.getCount());
		roomTypeTbls.setNoOfBed(roomType.getNoOfBeds());
		roomTypeTbls.setRent(roomType.getRent());
		StatusEnum status=StatusEnum.Active;
		roomTypeTbls.setStatus(status);
		update(roomTypeTbls);
		response.setId(roomTypeTbls.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;

	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.RoomDao#viewRoomType(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public RoomTypeDTO viewRoomType(int id)  throws PersistenceException{
		RoomTypeDTO response=new RoomTypeDTO();
		RoomTypeTbl roomTypeTbls=(RoomTypeTbl)getById(RoomTypeTbl.class,id);
		if(roomTypeTbls==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomTypeFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setId(roomTypeTbls.getId());
		response.setCount(roomTypeTbls.getCount());
		response.setRent(roomTypeTbls.getRent());
		response.setNoOfBeds(roomTypeTbls.getNoOfBed());
		response.setType(roomTypeTbls.getType());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.RoomDao#deleteRoomType(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO deleteRoomType(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		RoomTypeTbl roomTypeTbls=(RoomTypeTbl)getById(RoomTypeTbl.class,id);
		if(roomTypeTbls==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoRoomTypeFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//roomTypeTbls.setStatus(status);
		//update(roomTypeTbls);
		delete(roomTypeTbls);
		response.setId(roomTypeTbls.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	
	private RoomTypeTbl getByType(String type) throws PersistenceException{
		String typeName= type.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_ROOMTYPE_BY_NAME);
		query.setParameter("param1",typeName);		
		return executeUniqueQuery(RoomTypeTbl.class, query);
	}

	public RoomTbl getByRoomNumber(String name) throws PersistenceException {
		String roomName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_ROOM_BY_NAME);
		query.setParameter("param1",roomName);		
		return executeUniqueQuery(RoomTbl.class, query);
	}
	@Override
	@Transactional(readOnly=false)
	public RoomTypeListResponseDTO getRoomTypes() throws PersistenceException {
		RoomTypeListResponseDTO response=new RoomTypeListResponseDTO();
		List<RoomTypeDTO>roomTypeDTOList=new ArrayList<RoomTypeDTO>();
		List<RoomTypeTbl>roomTypeTblList=(ArrayList<RoomTypeTbl>)getRoomTypeList();
		for (RoomTypeTbl roomTypeTbl : roomTypeTblList) {
			RoomTypeDTO roomTypeDTO=new RoomTypeDTO();
			roomTypeDTO.setId(roomTypeTbl.getId());
			roomTypeDTO.setType(roomTypeTbl.getType());
			roomTypeDTOList.add(roomTypeDTO);
		}
		response.setError(null);
		response.setRoomType(roomTypeDTOList);
		response.setSuccess(true);
		return response;
	}
	private List<RoomTypeTbl> getRoomTypeList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_ROOMTYPE);
		return executeQuery(RoomTypeTbl.class, query);
	}
	
}
