/**
 * DepartmentDaoImpl.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
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
import com.nv.netmd.pl.entity.DepartmentTbl;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.DepartmentDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class DepartmentDaoImpl  extends GenericDaoHibernateImpl implements DepartmentDao {
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(DepartmentDaoImpl.class);

	/** create department
	 * @param department
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO create(DepartmentDTO department) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		DepartmentTbl departmentTbl=new DepartmentTbl();
		String departmentName = department.getDepartmentName().trim().replaceAll(" +", " ");
		DepartmentTbl dep=(DepartmentTbl)getDepartmentByName(departmentName);
		if(dep!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DepartmentWithNameExist);
			se.addParam(new Parameter(Constants.NAME,department.getDepartmentName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		departmentTbl.setName(department.getDepartmentName());
		departmentTbl.setDescription(department.getDescription());
		StatusEnum status=StatusEnum.Active;
		departmentTbl.setStatus(status);
		save(departmentTbl);
		response.setId(departmentTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/**
	 * get department by name
	 * @param name
	 * @return DepartmentTbl
	 * @throws PersistenceException 
	 */
	public DepartmentTbl getDepartmentByName(String name) throws PersistenceException {
		String depName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_DEPARTMENT_BY_NAME);
		query.setParameter("param1",depName);		
		return executeUniqueQuery(DepartmentTbl.class, query);
	}
	/** Update department
	 * @param department
	 * @return ResponseDTO
	 */
	@Override
	@Transactional(readOnly = false)
	public ResponseDTO update(DepartmentDTO department)  throws PersistenceException{
		ResponseDTO response=new ResponseDTO();
		DepartmentTbl departmentTbl=(DepartmentTbl)getById(DepartmentTbl.class, department.getId());
		if(departmentTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDepartmentFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(department.getId())));
			se.setDisplayErrMsg(true);
			throw se;
		}
		String departmentName = department.getDepartmentName().trim().replaceAll(" +", " ");
		DepartmentTbl dep=(DepartmentTbl)getDepartmentByName(departmentName);
		if(dep!=null){
			if(dep.getId()!=departmentTbl.getId()){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DepartmentWithNameExist);
			se.addParam(new Parameter(Constants.NAME,department.getDepartmentName()));
			se.setDisplayErrMsg(true);
			throw se;
			}
		}
		departmentTbl.setName(department.getDepartmentName());
		departmentTbl.setDescription(department.getDescription());
		StatusEnum status=StatusEnum.Active;
		departmentTbl.setStatus(status);
		update(departmentTbl);
		response.setId(departmentTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
		
	}
	/** view department
	 * @param id
	 */
	@Override
	@Transactional(readOnly = false)
	public DepartmentDTO view(int id)  throws PersistenceException{
		DepartmentDTO response=new DepartmentDTO();
		DepartmentTbl departmentTbl=(DepartmentTbl)getById(DepartmentTbl.class, id);
		if(departmentTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDepartmentFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		response.setDepartmentName(departmentTbl.getName());
		response.setDescription(departmentTbl.getDescription());
		response.setId(departmentTbl.getId());
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/** delete or deactivate department
	 * @param id
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		DepartmentTbl departmentTbl=(DepartmentTbl)getById(DepartmentTbl.class, id);
		if(departmentTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoDepartmentFound);
			se.addParam(new Parameter(Constants.ID, Integer
					.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//departmentTbl.setStatus(status);
		//update(departmentTbl);
		delete(departmentTbl);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.DepartmentDao#getDepartments()
	 */
	@Override
	@Transactional(readOnly=false)
	public DepartmentListResponseDTO getDepartments() throws PersistenceException {
		DepartmentListResponseDTO response=new DepartmentListResponseDTO();
		List<DepartmentDTO> departmentDTList=new ArrayList<DepartmentDTO>();
		List<DepartmentTbl> departmentList=(ArrayList<DepartmentTbl>)getDepartmentList();
		for (DepartmentTbl departmentTbl : departmentList) {
			DepartmentDTO department=new DepartmentDTO();
			department.setId(departmentTbl.getId());
			department.setDepartmentName(departmentTbl.getName());
			departmentDTList.add(department);
		}
		response.setDepartment(departmentDTList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private List<DepartmentTbl> getDepartmentList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_DEPARTMENTS);
		return executeQuery(DepartmentTbl.class, query);
	}
}
