 /**
* AdvancePropertyEnum.java
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

import com.nv.netmd.business.bl.impl.EnumDisplay;
import com.nv.netmd.util.filter.core.Property;

/**
 * @author Remya
 *
 */
public enum AdvancePropertyEnum implements Property,EnumDisplay{
	status("status","status","com.nv.netmd.pl.entity.AdvanceTbl","",""),
	id("id","id","com.nv.netmd.pl.entity.AdvanceTbl","","");
	
	;
	String displayName;
	String fieldName;
	String entityName;
	String referenceName;
	String pathReferenceName; /**
	 * @param displayName
	 * @param fieldName
	 * @param entityName
	 * @param referenceName
	 * @param pathReferenceName
	 */
	private AdvancePropertyEnum(String displayName, String fieldName,
			String entityName, String referenceName, String pathReferenceName) {
		this.displayName = displayName;
		this.fieldName = fieldName;
		this.entityName = entityName;
		this.referenceName = referenceName;
		this.pathReferenceName = pathReferenceName;
	}

	{

}

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFieldName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferenceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPathReferenceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParameterName() {
		// TODO Auto-generated method stub
		return null;
	}
}
