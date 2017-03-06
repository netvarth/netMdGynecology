/**
 * CaseQueryBuilder.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Feb 25, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.util.filter.queryBuilder;


import com.nv.netmd.pl.entity.CaseTbl;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.CastingFilter;
import com.nv.netmd.util.filter.dao.FilterDao;

/**
 * 
 */
public class CaseQueryBuilder extends RootQueryBuilder<CaseTbl> {
	public CaseQueryBuilder() {
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override
	public void init(FilterDao dao) {
		super.init(dao, CaseTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates, which are needed to
	 * build query for specified filter
	 * 
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		Criteria criteria= null;
		String referenceName = CasePropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = CasePropertyEnum.valueOf(filter.getName())
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
		//System.out.println(filter.getClass().isAssignableFrom(CastingFilter.class));
		if (CastingFilter.class.isAssignableFrom(filter.getClass())){
			CastingFilter typedFilter = (CastingFilter)filter;
		if(typedFilter.getCalculationType()==null)
			criteria = filter.getPredicate(this,CasePropertyEnum.valueOf(filter.getName()));
		else
			criteria = filter.getPredicate(this,CasePropertyEnum.valueOf(filter.getName()),typedFilter.getCalculationType());
		}
		else {
			criteria = filter.getPredicate(this,CasePropertyEnum.valueOf(filter.getName()));

		}
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		}

	}

}
