/**
 * DepartmentQueryBuilder.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.pl.entity.DepartmentTbl;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.dao.FilterDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class DepartmentQueryBuilder extends RootQueryBuilder<DepartmentTbl>{
	
	/**
	 * 
	 */
	public DepartmentQueryBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override
	public void init(FilterDao dao) {
		super.init(dao, DepartmentTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates, which are needed to
	 * build query for specified filter
	 * 
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = DepartmentPropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = DepartmentPropertyEnum.valueOf(filter.getName())
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
				DepartmentPropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		}

	}
}
