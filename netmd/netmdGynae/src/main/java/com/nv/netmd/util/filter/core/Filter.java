/**
 * Filter.java
 *
 * Aug 17, 2012
 *
 * @author Asha Chandran 
 */
package com.nv.netmd.util.filter.core;

public interface Filter {
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property);
	public Criteria getPredicate(QueryBuilder queryBuilder,Property property, String calculationType);
	public void setName(String name);
	public void setValue(String value);
	public String getName();
	
}
