/**
 * NetMdServerListener.java
 * 
 * @Author Sreeram
 *
 * Version 1.0 Mar 2, 2013
 *
 * Copyright (c) 2011-2012 Netvarth Technologies Pvt. Ltd.
 * All rights reserved
 *
 */
package com.nv.netmd.common;

import java.util.Timer;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import org.springframework.context.ApplicationContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 
 */

public class NetMdServerListener implements ServletContextListener {
	private ServletContext context = null;
	private ApplicationContext applicationContext = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.ServletContextListener#contextInitialized(javax.servlet
	 * .ServletContextEvent)
	 */

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//long delay = 10 * 1000; // 10 sec.
		long delay = 120 * 1000; //2 mins
		System.out.println("starting");
		applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(event.getServletContext());
		/*
		 * Schedules the specified task for repeated fixed-delay execution,
		 * beginning after the specified delay. Subsequent executions take place
		 * at approximately regular intervals separated by the specified period
		 */
	
		Timer timer = new Timer();
		NetMdTaskScheduler taskScheduler = (NetMdTaskScheduler) applicationContext.getBean("task.Scheduler");
		timer.schedule(taskScheduler, 0, delay); // starts a timer
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.
	 * ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {

		System.out.println("destroyed");

	}

}
