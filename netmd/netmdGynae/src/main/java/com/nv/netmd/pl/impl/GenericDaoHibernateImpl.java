/**
 * 
 */
package com.nv.netmd.pl.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.dao.GenericDao;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.NetmdPassphraseTbl;
import com.nv.netmd.rs.dto.Credentials;
import com.nv.netmd.rs.dto.HeaderDTO;

/**
 * @author sreeram
 * 
 * 
 */
public class GenericDaoHibernateImpl implements GenericDao {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(GenericDaoHibernateImpl.class);

	@PersistenceContext()
	private EntityManager em;

	public GenericDaoHibernateImpl() {

	}

	@Transactional(readOnly = false)
	public void update(final Object obj) throws PersistenceException {
		try {
			em.merge(obj);
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public void delete(Object obj) throws PersistenceException {
		try {
			em.remove(obj);
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public void save(Object obj) throws PersistenceException {

		try {
			em.persist(obj);
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> T getById(Class<T> a, int id) throws PersistenceException {
		try {
			T obj = em.find(a, id);
			return obj;
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}

	}

	@Transactional(readOnly = false)
	public <T> List<T> loadAll(Class<T> className) throws PersistenceException {
		List<T> response = null;
		try {
			Query query = em.createQuery("from " + className.getName());
			response = query.getResultList();
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	@Transactional(readOnly = false)
	public <T> T getByUID(Class<T> className, int uid) throws PersistenceException {
		try {
			Query query = em.createQuery("from " + className.getName()
					+ " as obj where obj.uid=:uid");
			query.setParameter("uid", uid);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	@Transactional(readOnly = false)
	public <T> T getByUid(Class<T> className, int uid) throws PersistenceException {
		try {
			Query query = em.createQuery("from " + className.getName()
					+ " as obj where obj.uid=:uid");
			query.setParameter("uid", uid);
			return (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nv.weblims.pl.dao.GenericDao#getSequence(java.lang.String)
	 */
	@Override
	public BigInteger getNextSequence(String sequence) throws PersistenceException {
		try {
			Query query = em.createNativeQuery("select nextval('" + sequence
					+ "')");
			return (BigInteger) query.getSingleResult();
		} catch (RuntimeException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
	}
	
	@Override
	@Transactional(readOnly=false)
	public Credentials getCredentials() throws PersistenceException {
		Credentials response=new Credentials();
		NetmdPassphraseTbl passphrase=getPassPhrase();
		if(passphrase!=null){
			response.setMacId(passphrase.getMacId());
			if(passphrase.getNetmdBranchTbl()!=null)
				response.setBranchId(passphrase.getNetmdBranchTbl().getGlobalId());
			if(passphrase.getNetmdTbl()!=null)
				response.setHeadOfficeId(passphrase.getNetmdTbl().getGlodalId());
			response.setPassPhrase(passphrase.getPassPhrase().trim());
		}
		return response;
	}

	public NetmdPassphraseTbl getPassPhrase() throws PersistenceException {
		// TODO Auto-generated method stub
		javax.persistence.Query query = em
				.createQuery(com.nv.netmd.pl.query.Query.GET_PASSPHRASE);
		return executeUniqueQuery(NetmdPassphraseTbl.class, query);
	}
	@Transactional(readOnly = false)
	public <T> List<T> executeQuery(Class<T> className, Query query) throws PersistenceException {
		List<T> response = null;
		try {
			response = query.getResultList();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}

	@Transactional(readOnly = false)
	public <T> T executeUniqueQuery(Class<T> className, Query query) throws PersistenceException {
		T response = null;
		try {
			response = (T) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (ClassCastException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.UnknownEnum);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RuntimeException e) {
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.DatabaseError);
			se.setDisplayErrMsg(true);
			throw se;
		}
		return response;
	}
}