package com.nv.netmd.common;
import java.util.Date;

/**
 * DateFormat.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Apr 23, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */

/**
 * 
 */
public interface DateFormat {
	public String format(Date pattern);
	public Date parse(String pattern);
}
