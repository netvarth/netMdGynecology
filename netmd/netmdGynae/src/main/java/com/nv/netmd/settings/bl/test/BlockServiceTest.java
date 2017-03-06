/**
 * BlockServiceTest.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 26, 2013
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
import com.nv.netmd.rs.dto.BlockDTO;
import com.nv.netmd.rs.dto.BlockListResponseDTO;
import com.nv.netmd.rs.dto.ExpressionDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.settings.bl.service.BlockService;



/**
 *
 *
 * @author Sreeram T G
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class BlockServiceTest {
	@Autowired
	private ApplicationContext applicationContext;
	@Test
	public void createBlock() throws ServiceException{
		BlockService blockService=(BlockService)applicationContext.getBean("block.Service");
		BlockDTO block=new BlockDTO();
		block.setName("Neuro surgen");
		block.setDescription("neuro for all");
		blockService.create(block);
	}
	@Test
	public void createBlockWithSameName(){
		BlockService blockService=(BlockService)applicationContext.getBean("block.Service");
		BlockDTO block=new BlockDTO();
		block.setName("Neuro surgen");
		block.setDescription("neuro for all");
		try {
			blockService.create(block);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void updateBlock(){
		BlockService blockService=(BlockService)applicationContext.getBean("block.Service");
		BlockDTO block=new BlockDTO();
		block.setId(1);
		block.setName("Neuro surgen");
		block.setDescription("neuro for all");
		try {
			blockService.update(block);
		}

		catch (ServiceException e) {
			System.out.println(e.isDisplayErrMsg());
			System.out.println(e.getError());
			System.out.println(e.getParamList());
		}

	}
	@Test
	public void view() throws ServiceException{
		BlockService blockService=(BlockService)applicationContext.getBean("block.Service");

		blockService.view(1);

	}
	@Test
	public void delete() throws ServiceException{
		BlockService blockService=(BlockService)applicationContext.getBean("block.Service");

		blockService.delete(1);
	}
		@Test
		public void filter() throws ServiceException{
			BlockService blockService=(BlockService)applicationContext.getBean("block.Service");
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
			BlockListResponseDTO response = blockService.list(filter);
			System.out.println(response.getBlock().size());
			for (BlockDTO bl : response.getBlock()) {
				System.out.println(bl.getName());

			

		}

	}
}
