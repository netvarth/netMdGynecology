 /**
* HeadDaoImpl.java
* @author Nithesh Mohanan
*
* Version 1.0 Nov 25, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.settings.pl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.common.Constants;
import com.nv.netmd.common.DateFormat;
import com.nv.netmd.common.SimpleDateFormat;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.pl.entity.BillItemTbl;
import com.nv.netmd.pl.entity.BillStatusEnum;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.ExpensePaymentTbl;
import com.nv.netmd.pl.entity.ExpenseTbl;
import com.nv.netmd.pl.entity.HeadTbl;
import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.pl.entity.PayStatusEnum;
import com.nv.netmd.pl.entity.StatusEnum;
import com.nv.netmd.pl.entity.TaxTbl;
import com.nv.netmd.pl.impl.GenericDaoHibernateImpl;
import com.nv.netmd.pl.query.Query;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.ExpenseDTO;
import com.nv.netmd.rs.dto.ExpenseListResponseDTO;
import com.nv.netmd.rs.dto.ExpensePaymentDTO;
import com.nv.netmd.rs.dto.ExpenseViewDTO;
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.settings.pl.dao.ExpenseDao;




/**
 * @author Nithesh Mohanan
 *
 */
public class ExpenseDaoImpl extends GenericDaoHibernateImpl implements ExpenseDao{
	
	@PersistenceContext()
	private EntityManager em;
	private static final Log log = LogFactory.getLog(ExpenseDaoImpl.class);
	
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO create(ExpenseDTO expense)  throws PersistenceException{
		float balance;
		ResponseDTO response=new ResponseDTO();
		String expenseName = expense.getExpenseName().trim().replaceAll(" +", " ");
		ExpenseTbl exp=(ExpenseTbl)getExpenseByName(expenseName);
		if(exp!=null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.ExpenseWithNameExists);
			se.addParam(new Parameter(Constants.NAME,exp.getExpenseName()));
			se.setDisplayErrMsg(true);
			throw se;
		}
		ExpenseTbl expTbl=new  ExpenseTbl();
		expTbl.setExpenseName(expense.getExpenseName());
		expTbl.setHeadName(expense.getHeadName());
		expTbl.setTotalAmount(expense.getTotalAmount());
		expTbl.setPaidAmount(expense.getPaidAmount());
		expTbl.setCreatedOn(new Date());
		balance = expTbl.getTotalAmount()- expTbl.getPaidAmount();
		expTbl.setBalance(balance);
		expTbl.setDescription(expense.getDescription());
		
		if(expTbl.getPaidAmount()!=0 && expTbl.getPaidAmount()<expense.getTotalAmount())
			expTbl.setPaymentStatus(PayStatusEnum.PartiallyPaid);
		if(expTbl.getPaidAmount()==expense.getTotalAmount() || expTbl.getPaidAmount()>expense.getPaidAmount())
			expTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
		if(expTbl.getPaidAmount()==0 && expense.getTotalAmount()>0)
			expTbl.setPaymentStatus(PayStatusEnum.NotPaid);
		
			
			if(expense.getHeadId()!=0){
				HeadTbl headTbl=(HeadTbl)getById(HeadTbl.class, expense.getHeadId());
					if(headTbl==null){
						PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoHeadFound);
						se.addParam(new Parameter(Constants.ID,Integer.toString(expense.getHeadId())));
						se.setDisplayErrMsg(true);
						log.error(se);
						throw se;
					}
			
					expTbl.setHeadTbl(headTbl);
			}
		StatusEnum status=StatusEnum.Active;
		expTbl.setStatus(status);	
		save(expTbl);
		
		ExpensePaymentTbl ePaymentTbl=new ExpensePaymentTbl();
		ePaymentTbl.setPaymentDate(new Date());
		ePaymentTbl.setExpenseTbl(expTbl);
		if(expense.getPaidAmount()>expense.getTotalAmount())
			ePaymentTbl.setAmountPaid(expense.getTotalAmount());
		else
			ePaymentTbl.setAmountPaid(expense.getPaidAmount());
		
		save(ePaymentTbl);
		
		response.setError(null);
		response.setSuccess(true);
		response.setId(expTbl.getId());
		return response;
	}

	public ExpenseTbl getExpenseByName(String name) throws PersistenceException {
		String expenseName= name.toUpperCase();
		javax.persistence.Query query = em.createQuery(Query.GET_EXPENSE_BY_NAME);
		query.setParameter("param1",expenseName);		
		return executeUniqueQuery(ExpenseTbl.class, query);
	}
	
	@Override
	@Transactional(readOnly=false)
	public ExpenseListResponseDTO getExpenses() throws PersistenceException {
		ExpenseListResponseDTO response=new ExpenseListResponseDTO();
		List<ExpenseDTO> expenseList=new ArrayList<ExpenseDTO>();
		List<ExpenseTbl> ExpenseTblList=(ArrayList<ExpenseTbl>)getExpenseList();
		for (ExpenseTbl expTbl : ExpenseTblList) {
			ExpenseDTO expense = new ExpenseDTO();
			expense.setExpenseName(expTbl.getExpenseName());
			expense.setDescription(expTbl.getDescription());
			expense.setTotalAmount(expTbl.getTotalAmount());
			expense.setPaidAmount(expTbl.getPaidAmount());
			expense.setBalance(expTbl.getBalance());
			expenseList.add(expense);
		   }
		response.setExpenseList(expenseList);
		response.setError(null);
		response.setSuccess(true);
		return response;
	}
	private List<ExpenseTbl> getExpenseList() throws PersistenceException{
		javax.persistence.Query query = em.createQuery(Query.GET_EXPENSE);
		return executeQuery(ExpenseTbl.class, query);
	}
	
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ItemDao#view(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public ExpenseViewDTO view(int id) throws PersistenceException {
		ExpenseViewDTO response=new ExpenseViewDTO();
		ExpenseTbl expTbl=(ExpenseTbl)getById(ExpenseTbl.class, id);
		if(expTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoExpenseFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		List<ExpensePaymentDTO>expPaymentList=(ArrayList<ExpensePaymentDTO>)ExpensePaymentDTOList(expTbl.getId());
		response.setPaymentList(expPaymentList);
		
		response.setId(expTbl.getId());
		response.setExpenseName(expTbl.getExpenseName());
		response.setHeadName(expTbl.getHeadName());
		response.setDescription(expTbl.getDescription());
		response.setTotalAmount(expTbl.getTotalAmount());
		response.setPaidAmount(expTbl.getPaidAmount());
		response.setPaymentStatus(expTbl.getPaymentStatus());
		response.setBalance(expTbl.getBalance());
		if(expTbl.getHeadTbl()!=null){
			response.setHeadId(expTbl.getHeadTbl().getId());
		}
		response.setError(null);
		response.setSuccess(true);
		return response;
	}

	private ArrayList<ExpensePaymentDTO> ExpensePaymentDTOList(Integer id) throws PersistenceException {
		ArrayList<ExpensePaymentDTO> expensePaymentList=new ArrayList<ExpensePaymentDTO>();
		ArrayList<ExpensePaymentTbl>paymentList=(ArrayList<ExpensePaymentTbl>)getPaymentsByExpense(id);

		for (ExpensePaymentTbl expPaymentTbl : paymentList) {
			ExpensePaymentDTO expPayDto=new ExpensePaymentDTO();
			if(expPaymentTbl.getExpenseTbl()!=null){
				expPayDto.setId(expPaymentTbl.getId());
				expPayDto.setExpenseId(expPaymentTbl.getExpenseTbl().getId());
				DateFormat df=new SimpleDateFormat(Constants.DATE_FORMAT_WITHOUT_TIME);
				expPayDto.setPaymentDate(df.format(expPaymentTbl.getPaymentDate()));
				DateFormat df1=new SimpleDateFormat(Constants.TIMEWITHFORMAT);
				expPayDto.setPaymentTime(df1.format(expPaymentTbl.getPaymentDate()));
				expPayDto.setAmountPaid(expPaymentTbl.getAmountPaid());
			}	
			expensePaymentList.add(expPayDto);
		}
		return expensePaymentList;
	}

	private ArrayList<ExpensePaymentTbl> getPaymentsByExpense(Integer expenseId) throws PersistenceException {
		javax.persistence.Query query=em.createQuery(Query.GET_PAYMENT_BY_EXPENSEID);	
		query.setParameter("param1", expenseId);
		return (ArrayList<ExpensePaymentTbl>) executeQuery(ExpensePaymentTbl.class,query);
	}

	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ExpenseDao#update(com.nv.netmd.rs.dto.ExpenseDTO)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO update(ExpenseDTO expense) throws PersistenceException {
		float balAmount=0;
		float billAmount=0;
        float prevPaid=0;
        float balance=0;
		ResponseDTO response=new ResponseDTO();
		ExpenseTbl expTbl=(ExpenseTbl)getById(ExpenseTbl.class, expense.getId());
		ExpensePaymentTbl ePaymentTbl=new ExpensePaymentTbl();
		if(expTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoExpenseFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(expense.getId())));
			se.setDisplayErrMsg(true);
			log.error(se);
			throw se;
		}
		String expenseName = expense.getExpenseName().trim().replaceAll(" +", " ");
		ExpenseTbl exp=(ExpenseTbl)getExpenseByName(expenseName);
		if(exp!=null){
			if(exp.getId()!=expTbl.getId()){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.ExpenseWithNameExists);
				se.addParam(new Parameter(Constants.NAME,expense.getExpenseName()));
				se.setDisplayErrMsg(true);
				throw se;
			}
		}
		if(expense.getHeadId()!=0){
			HeadTbl headTbl=(HeadTbl)getById(HeadTbl.class,expense.getHeadId());
			if(headTbl==null){
				PersistenceException se = new PersistenceException(
						ErrorCodeEnum.NoHeadFound);
				se.addParam(new Parameter(Constants.ID,Integer.toString(expense.getHeadId())));
				se.setDisplayErrMsg(true);
				throw se;
			}
			expTbl.setHeadTbl(headTbl);
		}
		expTbl.setExpenseName(expense.getExpenseName());
		expTbl.setHeadName(expense.getHeadName());
		expTbl.setDescription(expense.getDescription());
		expTbl.setNote(expense.getNote());
		
		billAmount=expTbl.getTotalAmount();
		prevPaid=expTbl.getPaidAmount();
		balAmount=expense.getBalance();
		
			if(balAmount>=billAmount){
				expTbl.setPaidAmount(billAmount);
				expTbl.setPaymentStatus(PayStatusEnum.FullyPaid);	
				ePaymentTbl.setAmountPaid(billAmount);
			}else
				expTbl.setPaidAmount(prevPaid+balAmount);
				ePaymentTbl.setAmountPaid(balAmount);
			    balance=billAmount-expTbl.getPaidAmount();
				expTbl.setBalance(balance);
				
				ePaymentTbl.setExpenseTbl(expTbl);
				ePaymentTbl.setPaymentDate(new Date());
				save(ePaymentTbl);
		
			if(expTbl.getPaidAmount()!=0 && expTbl.getPaidAmount()<billAmount)
				expTbl.setPaymentStatus(PayStatusEnum.PartiallyPaid);
			if(expTbl.getPaidAmount()==billAmount || expTbl.getPaidAmount()>billAmount)
				expTbl.setPaymentStatus(PayStatusEnum.FullyPaid);
			if(expTbl.getPaidAmount()==0 && billAmount>0)
				expTbl.setPaymentStatus(PayStatusEnum.NotPaid);
		
		update(expTbl);
		response.setError(null);
		response.setSuccess(true);
		response.setId(expTbl.getId());
		return response;
	}
	
	/* (non-Javadoc)
	 * @see com.nv.netmd.bed.pl.dao.ExpenseDao#delete(int)
	 */
	@Override
	@Transactional(readOnly=false)
	public ResponseDTO delete(int id) throws PersistenceException {
		ResponseDTO response=new ResponseDTO();
		ExpenseTbl expenseTbl=(ExpenseTbl)getById(ExpenseTbl.class, id);
		if(expenseTbl==null){
			PersistenceException se = new PersistenceException(
					ErrorCodeEnum.NoExpenseFound);
			se.addParam(new Parameter(Constants.ID,Integer.toString(id)));
			se.setDisplayErrMsg(true);
			throw se;
		}
		//StatusEnum status=StatusEnum.Inactive;
		//expenseTbl.setStatus(status);
		//update(expenseTbl);
		delete(expenseTbl);
		response.setError(null);;
		response.setSuccess(true);
		return response;
	}
}
