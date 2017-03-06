/**
 * DepartmentServiceTest.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 25, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.DepartmentDTO;
import com.nv.netmd.rs.dto.DepartmentListResponseDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.settings.bl.service.DepartmentService;

/**
 *
 *
 * @author Sreeram T G
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class DepartmentServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void createDepartment() throws ServiceException{
		DepartmentService departmentService=(DepartmentService)applicationContext.getBean("department.Service");
		DepartmentDTO department=new DepartmentDTO();
		department.setDepartmentName("Neuro surgen");
		department.setDescription("neuro for all");
		departmentService.create(department);
	}
	@Test
	public void createDepartmentWithSameName(){
		DepartmentService departmentService=(DepartmentService)applicationContext.getBean("department.Service");
		DepartmentDTO department=new DepartmentDTO();
		department.setDepartmentName("Neuro surgen");
		department.setDescription("neuro for all");
		try {
			departmentService.create(department);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateDepartment(){
		DepartmentService departmentService=(DepartmentService)applicationContext.getBean("department.Service");
		DepartmentDTO department=new DepartmentDTO();
		department.setId(1);
		department.setDepartmentName("leo");
		department.setDescription("neuro for all");
		try {
			departmentService.update(department);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void view() throws ServiceException{
		DepartmentService departmentService=(DepartmentService)applicationContext.getBean("department.Service");

		departmentService.view(1);

	}
	@Test
	public void delete() throws ServiceException{
		DepartmentService departmentService=(DepartmentService)applicationContext.getBean("department.Service");

		departmentService.delete(1);
	}
	@Test
	public void filter() throws ServiceException{
		DepartmentService departmentService=(DepartmentService)applicationContext.getBean("department.Service");
		FilterDTO filter = new FilterDTO();
		filter.setAsc(true);
		filter.setFrom(0);
		filter.setCount(5);
		//			ExpressionDTO exp = new ExpressionDTO();
		//			exp.setName("name");
		//			exp.setOperator("eq");
		//			exp.setValue("sree");	
		ExpressionDTO exp1 = new ExpressionDTO();
		exp1.setName("status");
		exp1.setOperator("eq");
		exp1.setValue("Active");	
		List<ExpressionDTO> exps = new ArrayList<ExpressionDTO>();
		//exps.add(exp);
		exps.add(exp1);
		filter.setExp(exps);
		DepartmentListResponseDTO response = departmentService.list(filter);
		System.out.println(response.getDepartment().size());
		for (DepartmentDTO dep : response.getDepartment()) {
			System.out.println(dep.getDepartmentName());

		}
	}
}
