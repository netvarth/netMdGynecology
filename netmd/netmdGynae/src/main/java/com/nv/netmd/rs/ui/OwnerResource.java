/**
 * OwnerResource.java
 * @author Leo
 *
 * Version 1.0 Oct 9, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.business.bl.service.OwnerService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.DoctorDetail;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.OwnerDetailDTO;
import com.nv.netmd.rs.dto.Parameter;

/**
 *
 *
 * @author Leonora Louis
 */
@Controller
@RequestMapping("ui/owner/")
public class OwnerResource {
private OwnerService ownerService;

@RequestMapping (value="view/{id}",method=RequestMethod.GET) 
@ResponseBody
public OwnerDetailDTO view(@PathVariable int id) {
	// DoctorService doctorService=new DoctorServiceImpl();
	OwnerDetailDTO response = new OwnerDetailDTO();
	try {
		response = ownerService.view(id);
	} catch (ServiceException e) {

		List<Parameter> parameters = e.getParamList();
		ErrorDTO error = new ErrorDTO();
		error.setErrCode(e.getError().getErrCode());
		error.setParams(parameters);
		error.setDisplayErrMsg(e.isDisplayErrMsg());
		response.setError(error);
		response.setSuccess(false);

	}
	return response;

}

public OwnerService getOwnerService() {
	return ownerService;
}

public void setOwnerService(OwnerService ownerService) {
	this.ownerService = ownerService;
}
}
