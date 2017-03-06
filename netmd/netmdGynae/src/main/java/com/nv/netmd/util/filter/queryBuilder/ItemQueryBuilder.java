/**
 * ItemQueryBuilder.java
 * @author Sreeram T G 
 *
 * Version 1.0 16-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.util.filter.queryBuilder;

import com.nv.netmd.pl.entity.ItemTbl;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.dao.FilterDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class ItemQueryBuilder extends RootQueryBuilder<ItemTbl> {

	public ItemQueryBuilder() {
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override
	public void init(FilterDao dao) {
		super.init(dao, ItemTbl.class);
	}

	/**
	 * Add filter to query builder then generate predicates, which are needed to
	 * build query for specified filter
	 * 
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = ItemPropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = ItemPropertyEnum.valueOf(filter.getName())
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
				ItemPropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			predicates.add(criteria.getPredicate());
		}

		if (criteria.getExpression() != null) {
			expressions.add(criteria.getExpression());
		}

	}

}
