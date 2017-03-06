/**
 * DepartmentDao.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
public interface DepartmentDao {
	public ResponseDTO create(DepartmentDTO department) throws PersistenceException;
	public ResponseDTO update(DepartmentDTO department) throws PersistenceException;
	public DepartmentDTO view(int id) throws PersistenceException;
	public ResponseDTO delete(int id) throws PersistenceException;
	public DepartmentListResponseDTO getDepartments() throws PersistenceException;
}
