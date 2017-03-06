/**
 * LikeFilter.java
 *
 * Sep 21, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.filters;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.QueryBuilder;



public class LikeFilter implements Filter{
	private String name;
	private String value;

	public LikeFilter(){

	}
	public LikeFilter(String name, String value) {
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
		Criteria criteria = new Criteria();
		if(value==null){
			return criteria;	
		}
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			Expression<String> pathObj = queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName());
			predicate = queryBuilder.getCriteriaBuilder().like(
					queryBuilder.getCriteriaBuilder().upper(pathObj),"%"+value.trim().toUpperCase()+"%"
					);
		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			Expression<String> pathObj = queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName());

			predicate = queryBuilder.getCriteriaBuilder().like(
					queryBuilder.getCriteriaBuilder().upper(pathObj), "%"+value.trim().toUpperCase()+"%"
					);
		}else{
			Expression<String> pathObj = queryBuilder.getRoot().get(property.getFieldName());

			predicate = queryBuilder.getCriteriaBuilder().like(
					queryBuilder.getCriteriaBuilder().upper(pathObj), "%"+value.trim().toUpperCase()+"%"
					);
		}
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
