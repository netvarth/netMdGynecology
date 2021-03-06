/**
* MessageQueryBuilder.java
* 
* @Author Sreeram
*
* Version 1.0 May 6, 2013
*
* Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
* All rights reserved
*
*/
package com.nv.netmd.util.filter.queryBuilder;


import com.nv.netmd.pl.entity.MessageTbl;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.dao.FilterDao;

/**
 * 
 */
public class MessageQueryBuilder extends RootQueryBuilder<MessageTbl> {

	/**
	 * 
	 */
	public MessageQueryBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override
	public void init(FilterDao dao) {
		super.init(dao, MessageTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates, which are needed to
	 * build query for specified filter
	 * 
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = MessagePropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = MessagePropertyEnum.valueOf(filter.getName())
				.getPathReferenceName();

		if (referenceName != null && !referenceName.equals("")) {
			if (referenceMap.get(referenceName) == null) {
				referenceMap.put(referenceName, getJoin(referenceName));
			}
		}
		if (pathReferenceName != null && !pathReferenceName.equals("")) {
			if (pathReferenceMap.get(pathReferenceName) == null) {
				pathReferenceMap.put(pathReferenceName,
						referenceMap.get(referenceName).get(pathReferenceName));
			}
		}
		Criteria criteria = filter.getPredicate(this,
				MessagePropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		}

	}

}
