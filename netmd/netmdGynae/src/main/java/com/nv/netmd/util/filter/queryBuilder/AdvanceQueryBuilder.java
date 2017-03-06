 /**
* AdvanceQueryBuilder.java
* @author Nithesh Mohanan
*
* Version 1.0 Oct 23, 2013
*
* Copyright (c) 2013 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
/**
 * 
 */
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.pl.entity.AdvanceTbl;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.dao.FilterDao;

/**
 * @author Nithesh
 *
 */
public class AdvanceQueryBuilder extends RootQueryBuilder<AdvanceTbl> {
	public AdvanceQueryBuilder() {
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override
	public void init(FilterDao dao) {
		super.init(dao, AdvanceTbl.class);
	}

	@Override
	public void addFilter(Filter filter) {
		String referenceName = AdvancePropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = AdvancePropertyEnum.valueOf(filter.getName())
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
				AdvancePropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		} 

	}
	

}
