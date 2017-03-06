/**
 * IsNullFilter.java
 *
 * Sep 18, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.filters;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Expression;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;



public class IsNullFilter implements Filter{
	private String name;
	private String value;

	public IsNullFilter(){

	}
	public IsNullFilter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Get predicate
	 */
	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property) {

		Predicate predicate = null;
		Expression expression = new Expression();
		expression.setName(name);
		expression.setValue(value);
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			Path<Object> pathObj = queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName());
			predicate = pathObj.isNull();
		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			Path<Object> pathObj = queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName());
			predicate = pathObj.isNull();
		}else{
			predicate = queryBuilder.getRoot().get(property.getFieldName()).isNull();
		}

		Criteria criteria = new Criteria();
		javax.persistence.criteria.Expression<Boolean> a = predicate.as(Boolean.class);
		criteria.setPredicate(predicate);
		
		criteria.setExpression(expression);
		return criteria;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder, Property property,
			String calculationType) {
		// TODO Auto-generated method stub
		return null;
	}
}
