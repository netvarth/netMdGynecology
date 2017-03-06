/**
 * IsNotNullFilter.java
 *
 * Sep 21, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.filters;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;



public class IsNotNullFilter implements Filter{
	private String name;
	private String value;

	public IsNotNullFilter(){

	}
	public IsNotNullFilter(String name, String value) {
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
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			Path<Object> pathObj = queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName());
			predicate = pathObj.isNotNull();
		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			Path<Object> pathObj = queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName());
			predicate = pathObj.isNotNull();
		}else{
			predicate = queryBuilder.getRoot().get(property.getFieldName()).isNotNull();
		}

		Criteria criteria = new Criteria();
		criteria.setPredicate(predicate);
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
