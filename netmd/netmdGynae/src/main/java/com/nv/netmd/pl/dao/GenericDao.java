/**
 * 
 */
package com.nv.netmd.pl.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.rs.dto.Credentials;
import com.nv.netmd.rs.dto.HeaderDTO;


/**
 * @author Shaby
 *
 * Oct 23, 2007 11:02:32 AM
 */
public interface GenericDao extends Serializable{
	public  void save(final Object obj)throws PersistenceException;
	public  void update(final Object obj) throws PersistenceException;
	public  void  delete(Object obj)throws PersistenceException;
	public <T>  T getById(Class<T> className ,int id) throws PersistenceException;
	public <T>  T getByUid(Class<T> className ,int uid) throws PersistenceException;
	public BigInteger getNextSequence(String sequence) throws PersistenceException;
	public <T> List<T> loadAll(Class<T> className) throws PersistenceException;
	public Credentials getCredentials()throws PersistenceException;
	//public List<DoctorTbl> getDoctorByName(int id, String name);

	//public  <T> List<T> executeQuery(String queryString);

}
