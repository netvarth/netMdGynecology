/**
 * ItemServiceTest.java
 * @author Sreeram T G 
 *
 * Version 1.0 16-Aug-2013
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
import com.nv.netmd.rs.dto.ItemDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.ItemListResponseDTO;
import com.nv.netmd.settings.bl.service.ItemService;

/**
 *
 *
 * @author Sreeram T G
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class ItemServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void createItem() throws ServiceException{
		ItemService itemService=(ItemService)applicationContext.getBean("item.Service");
		ItemDTO item=new ItemDTO();
		item.setName("Neuro surgen");
		item.setDescription("neuro for all");
		itemService.create(item);
	}
	@Test
	public void createItemWithSameName(){
		ItemService itemService=(ItemService)applicationContext.getBean("item.Service");
		ItemDTO item=new ItemDTO();
		item.setName("Neuro surgen");
		item.setDescription("neuro for all");
		try {
			itemService.create(item);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateItem(){
		ItemService itemService=(ItemService)applicationContext.getBean("item.Service");
		ItemDTO item=new ItemDTO();
		item.setId(1);
		item.setName("leo");
		item.setDescription("neuro for all");
		try {
			itemService.update(item);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void view() throws ServiceException{
		ItemService itemService=(ItemService)applicationContext.getBean("item.Service");

		itemService.view(1);

	}
	@Test
	public void delete() throws ServiceException{
		ItemService itemService=(ItemService)applicationContext.getBean("item.Service");

		itemService.delete(1);
	}
	@Test
	public void filter() throws ServiceException{
		ItemService itemService=(ItemService)applicationContext.getBean("item.Service");
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
		ItemListResponseDTO response = itemService.list(filter);
		System.out.println(response.getItemList().size());
		for (ItemDTO it : response.getItemList()) {
			System.out.println(it.getName());

		}
	}

}
