/**
 * NotEqualFilter.java
 *
 * Aug 17, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.filters;

import java.text.ParseException;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.nv.netmd.util.filter.core.CastingFilter;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.PropertyUtil;
import com.nv.netmd.util.filter.core.QueryBuilder;

import javax.persistence.criteria.Expression;



public class NotEqualFilter implements Filter,CastingFilter{
	private String name;
	private String value;
	private String calculationType;
	
	
	public NotEqualFilter(){
		
	}
	public NotEqualFilter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property) {
	
		String entityName = property.getEntityName();
		String fieldName = property.getFieldName();
		String referenceName = property.getReferenceName();
		String pathReferenceName = property.getPathReferenceName();
		Class<?> type = PropertyUtil.getFieldType(fieldName, entityName);
		Object propertyValue = PropertyUtil.getPropertyValue(type,value);
		com.nv.netmd.util.filter.core.Expression expression = new 	com.nv.netmd.util.filter.core.Expression();
		expression.setName(name);
		expression.setValue(propertyValue);
		Predicate predicate = null;
		if(queryBuilder.getPathReferenceMap().get(pathReferenceName)!=null){
			predicate = queryBuilder.getCriteriaBuilder().
					notEqual(queryBuilder.getPathReferenceMap().get(pathReferenceName).get(fieldName),
							PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
		}else if(queryBuilder.getReferenceMap().get(referenceName)!=null){
			predicate = queryBuilder.getCriteriaBuilder().
					notEqual(queryBuilder.getReferenceMap().get(referenceName).get(fieldName),
							PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
		}else{
			predicate = queryBuilder.getCriteriaBuilder().notEqual(queryBuilder.getRoot().get(fieldName),
					PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
		}

		Criteria criteria = new Criteria();
		criteria.setPredicate(predicate);
		criteria.setExpression(expression);
		return criteria;
	}

	
	
	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder, Property property,String calculationType) {
		Class<?> type = PropertyUtil.getFieldType(property.getFieldName(), property.getEntityName());
		
		Predicate predicate = null;
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			try {
				predicate = createPredicate(queryBuilder,
						queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName()),
						type, value,calculationType);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			try {
				predicate = createPredicate(queryBuilder,
						queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName()),
						type, value,calculationType);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				predicate = createPredicate(queryBuilder,queryBuilder.getRoot().get(property.getFieldName()), type, value,calculationType);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		Criteria criteria = new Criteria();
		criteria.setPredicate(predicate);
	
		return criteria;
	}
	
	private Predicate createPredicate(QueryBuilder queryBuilder,Path<?> path, Class<?> type, String value,String calculationType) throws ParseException {
		String value1="";
		Predicate predicate = null;		
		
		if(calculationType.equals("Integer")){
			 Expression<Integer> pathInt = path.as(Integer.class);
			predicate = queryBuilder.getCriteriaBuilder().notEqual(pathInt,Integer.parseInt(value));
			return predicate;
		}
		
		if(calculationType.equals("Float")){
			
			 Expression<Double> pathFloat = path.as(Double.class);
			predicate = queryBuilder.getCriteriaBuilder().notEqual(pathFloat,Double.parseDouble(value));
			return predicate;
		} 
		if(calculationType.equals("String")){
			
			 Expression<String> pathString = path.as(String.class);
			predicate = queryBuilder.getCriteriaBuilder().notEqual(pathString,value);
			return predicate;
		} 
			
		return predicate;
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
	
	/**
	 * @return the calculationType
	 */
	public String getCalculationType() {
		return calculationType;
	}
	/**
	 * @param calculationType the calculationType to set
	 */
	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
	}
}
