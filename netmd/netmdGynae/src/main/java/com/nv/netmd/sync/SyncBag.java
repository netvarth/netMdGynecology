 /**
* SyncBag.java
* @author Nithesh Mohanan
*
* Version 1.0 Apr 23, 2014
*
* Copyright (c) 2014 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.sync;

/**
 * @author Nithesh Mohanan
 *
 */


public class SyncBag<T,K> {
	


private Class<T> inputClass;
private Class<K> outputClass;
private Object input;
private String syncUrl="";


public String getSyncUrl(){
	return this.syncUrl;
}







/**
 * @return the inputClass
 */
public Class<T> getInputClass() {
	return inputClass;
}

/**
 * @return the outputClass
 */
public Class<K> getOutputClass() {
	return outputClass;
}


/**
 * @param syncUrl the syncUrl to set
 */
public void setSyncUrl(String syncUrl) {
	this.syncUrl = syncUrl;
}

/**
 * @return the input
 */
public Object getInput() {
	return input;
}

/**
 * @param input the input to set
 */
public void setInput(Object input) {
	this.input = input;
}

/**
 * @return the inputList
 */

public SyncBag(Class<T> inputClass, Class<K> outputClass) {
	this.inputClass = inputClass;
	this.outputClass = outputClass;
}

}
