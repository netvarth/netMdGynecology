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



import java.util.Map;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.print.attribute.standard.Fidelity;

import com.nv.netmd.pl.entity.CaseAnswerSetTbl;
import com.nv.netmd.pl.entity.CaseTbl;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.dao.FilterDao;
import com.nv.netmd.util.filter.filters.IsNullFilter;

/**
 * 
 */
public class AntenatalQueryBuilder extends RootQueryBuilder<CaseAnswerSetTbl> {
	public AntenatalQueryBuilder() {
	}

	/**
	 * Initialize criteria query, root and criteriaBuilder
	 */
	@Override
	public void init(FilterDao dao) {
		super.init(dao, CaseAnswerSetTbl.class);
			
	}

	/**
	 * Add filter to query builder then generate predicates, which are needed to
	 * build query for specified filter
	 * 
	 * @param filter
	 */
	@Override
	public void addFilter(Filter filter) {
		String referenceName = AntenatalPropertyEnum.valueOf(filter.getName())
				.getReferenceName();
		String pathReferenceName = AntenatalPropertyEnum.valueOf(filter.getName())
				.getPathReferenceName();
		String fieldName = AntenatalPropertyEnum.valueOf(filter.getName())
				.getFieldName();


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
				AntenatalPropertyEnum.valueOf(filter.getName()));
		if (criteria.getPredicate() != null) {
			System.out.println("CRITERIA  "+criteria.getPredicate());
			predicates.add(criteria.getPredicate());
            			
		}

		if (criteria.getExpression() != null) {
			System.out.println("EXP "+criteria.getExpression());
			expressions.add(criteria.getExpression());
			
		}
		
//		Criteria criteria1 = new IsNullFilter().getPredicate(this,
//				AntenatalPropertyEnum.valueOf("caseId"));
//		
//		Predicate predicate = criteria1.getPredicate();
//		Expression<Boolean> expression = predicate.as(Boolean.class);
//		
//		this.getCriteriaBuilder().and(predicate ,expression);
//			
//		}
//	
//		 //Path<Object> pathObj = referenceMap.get(referenceName).get(fieldName);
//		 //predicates.add(pathObj.isNull());
		
	}

}
