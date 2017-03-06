/**
 * BillServiceTest.java
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

import com.nv.netmd.billing.bl.impl.BillManager;
import com.nv.netmd.billing.bl.service.BillService;
import com.nv.netmd.billing.bl.validator.BillValidator;
import com.nv.netmd.billing.pl.dao.BillDao;
import com.nv.netmd.billing.pl.mockImpl.BillMockDaoImpl;
import com.nv.netmd.discount.bl.impl.DiscountServiceImpl;
import com.nv.netmd.discount.bl.service.DiscountService;
import com.nv.netmd.discount.bl.validator.DiscountValidator;
import com.nv.netmd.discount.pl.dao.DiscountDao;
import com.nv.netmd.discount.pl.mockImpl.DiscountMockDaoImpl;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.pl.entity.CalculationTypeEnum;
import com.nv.netmd.pl.entity.DiscountTypeEnum;
import com.nv.netmd.pl.entity.OriginEnum;
import com.nv.netmd.pl.entity.PayStatusEnum;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDTO;
import com.nv.netmd.rs.dto.BillItemDTO;
import com.nv.netmd.settings.bl.impl.TaxServiceImpl;
import com.nv.netmd.settings.bl.service.TaxService;
import com.nv.netmd.settings.bl.validator.TaxValidator;
import com.nv.netmd.settings.pl.dao.TaxDao;

/**
 *
 *
 * @author Sreeram T G
 */
public class BillServiceTest {
@Test
public void createBill() throws ServiceException{
	BillManager billService=new BillManager();
	BillValidator billValidator=new BillValidator();
	BillDao billDao=new BillMockDaoImpl();
	TaxServiceImpl taxService=new  TaxServiceImpl();
	TaxValidator taxValidator=new TaxValidator();
	
	taxService.setTaxValidator(taxValidator);
	
	DiscountServiceImpl discService=new DiscountServiceImpl();
	DiscountDao discountDao=new DiscountMockDaoImpl();
	DiscountValidator discountValidator=new DiscountValidator();
	discService.setDiscountDao(discountDao);
	discService.setDiscountValidator(discountValidator);
	BillDTO billDto=new BillDTO();
	billDto.setPatientId(6);
	billDto.setOrigin(OriginEnum.OutPatient.getDisplayName());
	billDto.setPaymentStatus(PayStatusEnum.PartiallyPaid.getDisplayName());
	billDto.setNotes("sam");
	billDto.setAmountPaid(200);
	billDto.setGrandTotal(300);
	List<BillItemDTO>billItemDTO=new ArrayList<BillItemDTO>();
	BillItemDTO billItem=new BillItemDTO();
	billItem.setItemId(12);
	billItem.setQuantity(2);
	
	billItemDTO.add(billItem);
	
	billDto.setItem(billItemDTO);
	List<BillDiscountDTO> billDiscount=new ArrayList<BillDiscountDTO>();
	BillDiscountDTO billDisc=new BillDiscountDTO();
	billDisc.setCalculationType(CalculationTypeEnum.Percentage.getDisplayName());
	billDisc.setId(1);
	billDisc.setDiscountType(DiscountTypeEnum.Predefined.getDisplayName());
	billDiscount.add(billDisc);
	billDto.setDiscount(billDiscount);
//	List<BillSupportDTO> billSupportDTO=new ArrayList<BillSupportDTO>();
//	BillSupportDTO billSupport=new BillSupportDTO();
//	billSupport.setSupportId(2);
//	billSupport.setQuantity(3);
//	billSupportDTO.add(billSupport);
//	billDto.setSupport(billSupportDTO);
	billService.setBillDao(billDao);
	billService.setBillValidator(billValidator);
	billService.setTaxService(taxService);
	billService.setDiscountService(discService);
	billService.create(billDto);
}

}
