/**
 * FilterFactory.java
 *
 * Aug 24, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.core;

import java.util.HashMap;
import java.util.Map;

import com.nv.netmd.rs.dto.ExpressionDTO;




public class FilterFactory {
	private  Map<String,String> filterMap = new HashMap<String, String>() ;

	public Filter getFilter(ExpressionDTO exp){
		Filter filter = null;
		if(exp==null){
			return filter;
		}
		try{
			String filterName = filterMap.get(exp.getOperator());
			Class classObj= Class.forName(filterName);
			
			Object myFilter = classObj.newInstance();
			Class<? extends Object> myFilterClass = myFilter.getClass();
			if (CastingFilter.class.isAssignableFrom(myFilterClass)){
				CastingFilter typeFilter = (CastingFilter) myFilter;
				typeFilter.setCalculationType(exp.getCalculationType());
			}
				
			filter =   (Filter) myFilter;
			
			filter.setName(exp.getName());
			if(exp.getValue()!=null && !exp.getValue().isEmpty()){
				String value = exp.getValue().trim();
				exp.setValue(value);
			}
			filter.setValue(exp.getValue());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return filter;
	}

	/**
	 * @return the filterMap
	 */
	public Map<String, String> getFilterMap() {
		return filterMap;
	}

	/**
	 * @param filterMap the filterMap to set
	 */
	public void setFilterMap(Map<String, String> filterMap) {
		this.filterMap = filterMap;
	}

}
