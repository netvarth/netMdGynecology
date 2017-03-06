 /**
* SyncListner.java
* @author Nithesh Mohanan
*
* Version 1.0 Apr 23, 2014
*
* Copyright (c) 2014 Netvarth Technologies, Inc.
* All rights reserved.
*
*/
package com.nv.netmd.sync;



import com.nv.netmd.exception.PersistenceException;
import com.nv.netmd.exception.RemoteCallException;
import com.nv.netmd.rs.dto.CommonSyncResponseDTO;

/**
 * @author Nithesh Mohanan
 *
 */


public interface SyncListner   {
	
 	public <T,K> SyncBag<T,K> getSyncData() throws RemoteCallException, PersistenceException;

	void callback(CommonSyncResponseDTO response) throws RemoteCallException, PersistenceException;

}
