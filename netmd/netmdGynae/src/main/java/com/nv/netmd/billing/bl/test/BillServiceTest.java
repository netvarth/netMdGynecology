/**
 * BillerviceTest.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.bl.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;











import com.nv.netmd.billing.bl.service.BillService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.ActionNameEnum;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.DiscountTypeEnum;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.pl.entity.PayStatusEnum;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.rs.dto.BillItemListDTO;
import com.nv.netmd.rs.dto.BillPaymentDTO;


/**
 *
 *
 * @author Sreeram T G
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml", "file:resource/netmd-context.xml" })
@Configurable
public class BillServiceTest {
	@Autowired 
	private ApplicationContext applicationContext;
	
	/**
	 * Create Bill
	 * @throws ServiceException 
	 */
	@Test
	public void createBill() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillDTO billDto=new BillDTO();
		billDto.setPatientId(6);
		billDto.setOrigin(OriginEnum.OutPatient.getDisplayName());
		billDto.setPaymentStatus(PayStatusEnum.PartiallyPaid.getDisplayName());
		billDto.setNotes("sam");
		billDto.setAmountPaid(200);
		//billDto.setGrandTotal(300);
		List<BillItemDTO>billItemDTO=new ArrayList<BillItemDTO>();
		BillItemDTO billItem=new BillItemDTO();
		billItem.setItemId(14);
		billItem.setQuantity(2);
		
		billItemDTO.add(billItem);
		
		billDto.setItem(billItemDTO);
		List<BillDiscountDTO> billDiscount=new ArrayList<BillDiscountDTO>();
		BillDiscountDTO billDisc=new BillDiscountDTO();
		//billDisc.setCalculationType(CalculationTypeEnum.Percentage.getDisplayName());
		billDisc.setDiscountId(1);
	//	billDisc.setDiscountType(DiscountTypeEnum.Predefined.getDisplayName());
		billDiscount.add(billDisc);
		billDto.setDiscount(billDiscount);
//		List<BillSupportDTO> billSupportDTO=new ArrayList<BillSupportDTO>();
//		BillSupportDTO billSupport=new BillSupportDTO();
//		billSupport.setSupportId(2);
//		billSupport.setQuantity(3);
//		billSupportDTO.add(billSupport);
//		billDto.setSupport(billSupportDTO);
		billService.create(billDto);
	}
	
	@Test
	public void view() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		billService.view("9");
	}
	
	@Test
	public void updateBillItemAdd() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillItemListDTO billItemListDTO=new BillItemListDTO();
		List<BillItemDTO> billItemList=new ArrayList<BillItemDTO>();
		BillItemDTO billItemDTO=new BillItemDTO();
		billItemDTO.setActionName(ActionNameEnum.ADD.getDisplayName());
		billItemDTO.setId(10);
		billItemDTO.setItemId(13);
		billItemDTO.setQuantity(2);		
		billItemListDTO.setBillUid("11");
		billItemListDTO.setNote("sample");
		billItemList.add(billItemDTO);
		billItemListDTO.setItem(billItemList);
		billService.updateBillItems(billItemListDTO);
	}
	@Test
	public void updateBillItemUpdate() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillItemListDTO billItemListDTO=new BillItemListDTO();
		List<BillItemDTO> billItemList=new ArrayList<BillItemDTO>();
		BillItemDTO billItemDTO=new BillItemDTO();
		billItemDTO.setActionName(ActionNameEnum.UPDATE.getDisplayName());
		billItemDTO.setId(10);
		billItemDTO.setItemId(13);
		billItemDTO.setQuantity(4);		
		billItemListDTO.setBillUid("11");
		billItemListDTO.setNote("sample");
		billItemList.add(billItemDTO);
		billItemListDTO.setItem(billItemList);
		billService.updateBillItems(billItemListDTO);
	}
	@Test
	public void updateBillItemDelete() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillItemListDTO billItemListDTO=new BillItemListDTO();
		List<BillItemDTO> billItemList=new ArrayList<BillItemDTO>();
		BillItemDTO billItemDTO=new BillItemDTO();
		billItemDTO.setActionName(ActionNameEnum.DELETE.getDisplayName());
		billItemDTO.setId(10);
		billItemDTO.setItemId(14);
	
		billItemListDTO.setBillUid("11");
		billItemListDTO.setNote("sample");
		billItemList.add(billItemDTO);
		billItemListDTO.setItem(billItemList);
		billService.updateBillItems(billItemListDTO);
	}
	
	@Test
	public void updateBillDiscAdd() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillDiscountDetailsDTO disc=new BillDiscountDetailsDTO();
		disc.setBillUid("9");
		List<BillDiscountDTO> billDiscList=new ArrayList<BillDiscountDTO>();
		BillDiscountDTO billDisc=new BillDiscountDTO();
		billDisc.setActionName(ActionNameEnum.ADD.getDisplayName());
		billDisc.setDiscountValue(10);
		billDisc.setDiscountId(2);
		billDisc.setCalculationType(CalculationTypeEnum.Fixed.getDisplayName());
		billDisc.setDiscountType(DiscountTypeEnum.OnDemand.getDisplayName());
		billDiscList.add(billDisc);
		disc.setBillDiscountList(billDiscList);
		billService.updateBillDiscount(disc);
	}
	
	@Test
	public void updateBillDiscUpdate() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillDiscountDetailsDTO disc=new BillDiscountDetailsDTO();
		disc.setBillUid("9");
		List<BillDiscountDTO> billDiscList=new ArrayList<BillDiscountDTO>();
		BillDiscountDTO billDisc=new BillDiscountDTO();
		billDisc.setActionName(ActionNameEnum.UPDATE.getDisplayName());
		billDisc.setDiscountValue(5);
		billDisc.setDiscountId(2);
		billDisc.setCalculationType(CalculationTypeEnum.Fixed.getDisplayName());
		billDisc.setDiscountType(DiscountTypeEnum.OnDemand.getDisplayName());
		billDisc.setId(5);
		billDiscList.add(billDisc);
		disc.setBillDiscountList(billDiscList);
		billService.updateBillDiscount(disc);
	}
	
	@Test
	public void updateBillDiscDel() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillDiscountDetailsDTO disc=new BillDiscountDetailsDTO();
		disc.setBillUid("9");
		List<BillDiscountDTO> billDiscList=new ArrayList<BillDiscountDTO>();
		BillDiscountDTO billDisc=new BillDiscountDTO();
		billDisc.setActionName(ActionNameEnum.DELETE.getDisplayName());
		billDisc.setDiscountValue(5);
		billDisc.setDiscountId(2);
		billDisc.setCalculationType(CalculationTypeEnum.Fixed.getDisplayName());
		billDisc.setDiscountType(DiscountTypeEnum.OnDemand.getDisplayName());
		billDisc.setId(5);
		billDiscList.add(billDisc);
		disc.setBillDiscountList(billDiscList);
		billService.updateBillDiscount(disc);
	}
	
	@Test
	public void updateBillPayment() throws ServiceException{
		BillService billService=(BillService)applicationContext.getBean("bill.Service");
		BillPaymentDTO payment=new BillPaymentDTO();
		payment.setBillUid("9");
		payment.setPaidAmount(5);
		payment.setNote("sampl");
		billService.updateBillPayment(payment);
	}
}
