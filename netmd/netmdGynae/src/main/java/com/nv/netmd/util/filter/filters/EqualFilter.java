/**
 * NameFilter.java
 *
 * Aug 17, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.filters;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.nv.netmd.util.filter.core.CastingFilter;
import com.nv.netmd.util.filter.core.Criteria;
import com.nv.netmd.util.filter.core.Filter;
import com.nv.netmd.util.filter.core.Property;
import com.nv.netmd.util.filter.core.PropertyUtil;
import com.nv.netmd.util.filter.core.QueryBuilder;




public class EqualFilter implements Filter,CastingFilter{
	private String name;
	private String value;
	private String calculationType;

	public EqualFilter(){

	}
	public EqualFilter(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Get predicate
	 */

	@Override
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property) {

		Class<?> type = PropertyUtil.getFieldType(property.getFieldName(), property.getEntityName());
		Object propertyValue = PropertyUtil.getPropertyValue(type,value);
		com.nv.netmd.util.filter.core.Expression expression = new com.nv.netmd.util.filter.core.Expression();
		expression.setName(name);
		expression.setValue(propertyValue);
		Criteria criteria = new Criteria();
		Predicate predicate = null;
		Calendar dateCalendar = Calendar.getInstance();
		if(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName())!=null){
			if(type.getSimpleName().equals("Date")){
				dateCalendar.setTime((Date) propertyValue);
				Path<Date> date = queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName());
				predicate = queryBuilder.getCriteriaBuilder().and(
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("year", Integer.class, date), dateCalendar.get(Calendar.YEAR)),
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("month", Integer.class, date), dateCalendar.get(Calendar.MONTH) + 1),
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("day", Integer.class, date), dateCalendar.get(Calendar.DATE)));

			}else if(type.getSimpleName().equals("String")){
				javax.persistence.criteria.Expression<String> literal = queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName());
				javax.persistence.criteria.Expression<String> arg = queryBuilder.getCriteriaBuilder().upper(queryBuilder.getCriteriaBuilder().literal((String) value.trim()));
				predicate = queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().upper(literal),arg);	
			}else{
				predicate = queryBuilder.getCriteriaBuilder().
						equal(queryBuilder.getPathReferenceMap().get(property.getPathReferenceName()).get(property.getFieldName()),
								PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
				criteria.setExpression(expression);
			}
		}else if(queryBuilder.getReferenceMap().get(property.getReferenceName())!=null){
			if(type.getSimpleName().equals("Date")){
				dateCalendar.setTime((Date) propertyValue);
				Path<Date> date = queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName());
				predicate = queryBuilder.getCriteriaBuilder().and(
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("year", Integer.class, date), dateCalendar.get(Calendar.YEAR)),
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("month", Integer.class, date), dateCalendar.get(Calendar.MONTH) + 1),
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("day", Integer.class, date), dateCalendar.get(Calendar.DATE)));

			}else if(type.getSimpleName().equals("String")){
				javax.persistence.criteria.Expression<String> literal = queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName());
				javax.persistence.criteria.Expression<String> arg = queryBuilder.getCriteriaBuilder().upper(queryBuilder.getCriteriaBuilder().literal((String) value.trim()));
				predicate = queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().upper(literal),arg);	
			}else{
				predicate = queryBuilder.getCriteriaBuilder().
						equal(queryBuilder.getReferenceMap().get(property.getReferenceName()).get(property.getFieldName()),
								PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
				criteria.setExpression(expression);
			}
		}else{
			if(type.getSimpleName().equals("Date")){
				dateCalendar.setTime((Date) propertyValue);
				Path<Date> date =  queryBuilder.getRoot().get(property.getFieldName());
				
				predicate = queryBuilder.getCriteriaBuilder().and(
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("year", Integer.class, date), dateCalendar.get(Calendar.YEAR)),
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("month", Integer.class, date), dateCalendar.get(Calendar.MONTH) + 1),
						queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().function("day", Integer.class, date), dateCalendar.get(Calendar.DATE)));

			}else if(type.getSimpleName().equals("String")){
				javax.persistence.criteria.Expression<String> literal = queryBuilder.getRoot().get(property.getFieldName());
				javax.persistence.criteria.Expression<String> arg = queryBuilder.getCriteriaBuilder().upper(queryBuilder.getCriteriaBuilder().literal((String) value.trim()));
				predicate = queryBuilder.getCriteriaBuilder().equal(queryBuilder.getCriteriaBuilder().upper(literal),arg);	
			}else{
				predicate = queryBuilder.getCriteriaBuilder().equal(queryBuilder.getRoot().get(property.getFieldName()),
						PropertyUtil.getParameterExpForProperty(queryBuilder.getCriteriaBuilder(), name, type));
			criteria.setExpression(expression);

			}
		}
		criteria.setPredicate(predicate);
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
			predicate = queryBuilder.getCriteriaBuilder().equal(pathInt,Integer.parseInt(value));
			return predicate;
		}
		
		if(calculationType.equals("Float")){
			
			 Expression<Double> pathFloat = path.as(Double.class);
			predicate = queryBuilder.getCriteriaBuilder().equal(pathFloat,Double.parseDouble(value));
			return predicate;
		} 
		if(calculationType.equals("String")){
			
			 Expression<String> pathString = path.as(String.class);
			predicate = queryBuilder.getCriteriaBuilder().equal(pathString,value);
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
	@Override
	public String getCalculationType() {
		
		return this.calculationType;
	}
	@Override
	public void setCalculationType(String calculationType) {
		this.calculationType = calculationType;
		
	}
}
