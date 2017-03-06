/**
* MessageList.java
* 
* @Author Sreeram
*
* Version 1.0 May 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.util.filter.list;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.transaction.annotation.Transactional;

import com.nv.netmd.business.bl.service.FilterService;
import com.nv.netmd.common.Constants;
import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;
import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.MessageDTO;
import com.nv.netmd.rs.dto.MessageListResponseDTO;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.FilterFactory;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;
import com.nv.netmd.util.filter.core.QueryBuilderFactory;
import com.nv.netmd.util.filter.queryBuilder.MessagePropertyEnum;
import com.nv.netmd.util.filter.validation.FilterValidator;

/**
 * 
 */
public class MessageList extends FilterValidator implements FilterService{
	private QueryBuilderFactory queryBuilderFactory;
	private FilterFactory filterFactory;
	/**
	 * @return the queryBuilderFactory
	 */
	public QueryBuilderFactory getQueryBuilderFactory() {
		return queryBuilderFactory;
	}
	/**
	 * @param queryBuilderFactory the queryBuilderFactory to set
	 */
	public void setQueryBuilderFactory(QueryBuilderFactory queryBuilderFactory) {
		this.queryBuilderFactory = queryBuilderFactory;
	}
	/**
	 * @return the filterFactory
	 */
	public FilterFactory getFilterFactory() {
		return filterFactory;
	}
	/**
	 * @param filterFactory the filterFactory to set
	 */
	public void setFilterFactory(FilterFactory filterFactory) {
		this.filterFactory = filterFactory;
	}
	/**
	 * get list of messageTbl
	 */
	@Override
	@Transactional(readOnly = false)
	public MessageListResponseDTO list(FilterDTO filterDTO) throws ServiceException {
		System.out.println(filterDTO.getCount());
		MessageListResponseDTO messageList = new MessageListResponseDTO();

		// validate filterDTO to identify invalid expressions and if there is
		// any,
		// return result with appropriate error code
//		ErrorDTO error =validate(filterDTO);
//		if (error != null) {
//			messageList.setError(error);
//			messageList.setSuccess(false);
//			return messageList;
//		}

		// get queryBuilder for message from builder factory
		QueryBuilder queryBuilder = queryBuilderFactory
				.getQueryBuilder(Constants.MESSAGE);
		if (queryBuilder == null) {
			return messageList;
		}
		for (ExpressionDTO exp : filterDTO.getExp()) {

			// get filter from filter factory by setting expression name and
			// value to filter
			Filter filter = filterFactory.getFilter(exp);
			queryBuilder.addFilter(filter);
		}
		// build query
		TypedQuery<MessageTbl> q = queryBuilder.buildQuery(filterDTO.isAsc(),
				filterDTO.getFrom(), filterDTO.getCount());
		Long count;
		try {
			count = queryBuilder.getCount();
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		// execute query
		List<MessageTbl> messages;
		try {
			messages = queryBuilder.executeQuery(q);
		} catch (PersistenceException e) {
			throw new ServiceException(ErrorCodeEnum.DatabaseError,e);	
		}
		messageList = getMessageList(messages);
		messageList.setCount(count);
		messageList.setSuccess(true);

		return messageList;
	}
	private MessageListResponseDTO getMessageList(List<MessageTbl> messages) {
		MessageListResponseDTO response = new MessageListResponseDTO();
		int doctorId=0;
		String message=null;
		int id=0;
		String date=null;
		String doctorName=null;
		boolean viewed=false;
		
		
		if (messages == null) {
			return response;
		}
		List<MessageDTO> messageList = new ArrayList<MessageDTO>();
		for (MessageTbl messageTbl : messages) {			
			if (messageTbl.getViewed()!= null) {
				viewed=messageTbl.getViewed();
			}
			id=messageTbl.getId();	
			
			
			DateFormat df = new SimpleDateFormat(Constants.DATE_FORMAT_WITH_TIME);
			if (messageTbl.getCreatedTime() != null) {
				
				date= df.format(messageTbl.getCreatedTime());

			}
			if (messageTbl.getDoctorTbl() != null) {
				doctorId=messageTbl.getDoctorTbl().getId();
				doctorName=messageTbl.getDoctorTbl().getFirstName();
			}
			messageList.add(new MessageDTO(id,messageTbl.getMessage(),doctorId,doctorName,date,viewed));
		}
		response.setMessageDTO(messageList);
		return response;
	}
	/**
	 * validate filter DTO
	 */
	@Override
	public ErrorDTO validate(FilterDTO filterDTO) {
		ErrorDTO error = new ErrorDTO();
		for (ExpressionDTO exp : filterDTO.getExp()) {
			Property property = null;
			try {
				property = MessagePropertyEnum.valueOf(exp.getName());
			} catch (IllegalArgumentException e) {
				error = getInvalidExpNameError(exp);
				return error;
			}
			error = validateExp(exp, property);
			if (error != null) {
				return error;
			}
		}
		return null;
	}
}
