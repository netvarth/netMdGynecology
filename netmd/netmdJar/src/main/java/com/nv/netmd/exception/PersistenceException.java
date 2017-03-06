/**
 * ServiceException.java
 * 
 * @author Rakhi Vasudevan
 * 
 * Oct 29, 2012 
 * 
 */
package com.nv.netmd.exception;

import java.util.ArrayList;
import java.util.List;

import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.rs.dto.Parameter;

public class PersistenceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3858074760526705563L;
	private ErrorCodeEnum error;
	private List<Parameter> paramList = new ArrayList<Parameter>();
	private boolean displayErrMsg;

	/**
	 * @param error
	 * @param paramMap
	 */
	public PersistenceException(ErrorCodeEnum error) {
		super(error.getErrMsg());
		this.error = error;

	}

	/**
	 * @param error
	 * @param paramMap
	 */
	public PersistenceException(ErrorCodeEnum error, Throwable e) {
		super(error.getErrMsg(),e);
		this.error = error;

	}
	/**
	 * @return the error
	 */
	public ErrorCodeEnum getError() {
		return error;
	}

	public void addParam(Parameter e) {
		paramList.add(e);
	}

	public List<Parameter> getParamList() {
		return paramList;
	}

	/**
	 * @return the displayErrMsg
	 */
	public boolean isDisplayErrMsg() {
		return displayErrMsg;
	}

	/**
	 * @param displayErrMsg
	 *            the displayErrMsg to set
	 */
	public void setDisplayErrMsg(boolean displayErrMsg) {
		this.displayErrMsg = displayErrMsg;
	}

}
