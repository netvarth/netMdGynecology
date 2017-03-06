/**
 * TaxDao.java
 * @author Leo
 *
 * Version 1.0 Aug 8, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.pl.dao;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.TaxDTO;
import com.nv.netmd.rs.dto.TaxListResponseDTO;
import com.nv.netmd.rs.dto.TaxViewResponseDTO;

/**
 *
 *
 * @author Leonora Louis
 */
public interface TaxDao {
	public ResponseDTO create(TaxDTO taxdto) throws PersistenceException;
	public TaxViewResponseDTO view(int id) throws PersistenceException;
	public ResponseDTO update(TaxDTO taxdto) throws PersistenceException;
	public ResponseDTO delete(int id) throws PersistenceException;
	public TaxListResponseDTO getTaxes() throws PersistenceException;
	public float getItemTaxAmount(int itemId) throws PersistenceException;
	public float getBedTaxAmount(int bedId) throws PersistenceException;
	public float getSupportTaxAmount(int bedId) throws PersistenceException;
}
