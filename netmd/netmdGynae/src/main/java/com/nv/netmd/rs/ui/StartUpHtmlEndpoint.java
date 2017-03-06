/**
 * StartUpEndPoint.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Dec 3, 2012
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.rs.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 */
@Controller
@RequestMapping("/")
public class StartUpHtmlEndpoint {
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String check() {
		return "index";
	}

}
