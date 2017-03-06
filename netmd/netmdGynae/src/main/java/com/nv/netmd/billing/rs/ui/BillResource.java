/**
 * BillResource.java
 * @author Sreeram T G 
 *
 * Version 1.0 23-Aug-2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.billing.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nv.netmd.billing.bl.service.BillService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.BillDTO;
import com.nv.netmd.rs.dto.BillDiscountDetailsDTO;
import com.nv.netmd.rs.dto.BillHeaderDTO;
import com.nv.netmd.rs.dto.BillItemListDTO;
import com.nv.netmd.rs.dto.BillListResponseDTO;
import com.nv.netmd.rs.dto.BillPaymentDTO;
import com.nv.netmd.rs.dto.BillResponseDTO;
import com.nv.netmd.rs.dto.BillStatusDTO;
import com.nv.netmd.rs.dto.BillViewResponseDTO;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.NetMdBranchDTO;
import com.nv.netmd.rs.dto.NetMdDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.PatientListResponseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

/**
 *
 *
 * @author Sreeram T G
 */
@Controller
@RequestMapping("ui/bill/")
public class BillResource {
	private BillService billService;
	
	/**
	 * create bill
	 * @param bill
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody BillDTO bill){
		ResponseDTO response = new ResponseDTO();
		try {
			response = billService.create(bill);			

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
	
	/**
	 * get logo

	 * @return netmdBranchDTo
	 */
	@RequestMapping(value = "getlogo", method = RequestMethod.GET)
	@ResponseBody
	public NetMdDTO getlogo(){
		NetMdDTO response = new NetMdDTO();
		try {
			response = billService.getlogo();			

		} catch (ServiceException e) {

			List<Parameter> parameters = e.getParamList();
			ErrorDTO error = new ErrorDTO();
			error.setErrCode(e.getError().getErrCode());
			error.setParams(parameters);
			error.setDisplayErrMsg(e.isDisplayErrMsg());

		}
		return response;

	}
	
	/**
	 * 
	 * update billHeader
	 * @param billHeader
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateBillHeader", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateBillHeader(@RequestBody BillHeaderDTO billHeader){
		ResponseDTO response = new ResponseDTO();
		try {
			response = billService.updateBillHeader(billHeader);			

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
	
	/**
	 * 
	 * update billStatus
	 * @param billStatus
	 * @return ResponseDTO
	 */
	@RequestMapping(value = "updateBillStatus", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateBillStatus(@RequestBody BillStatusDTO billStatus){
		ResponseDTO response = new ResponseDTO();
		try {
			response = billService.updateBillStatus(billStatus);			

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
	/**
	 * update bill items
	 * @param billItems
	 * @return BillResponseDTO
	 */
	@RequestMapping(value = "updateBillItems", method = RequestMethod.POST)
	@ResponseBody
	public BillResponseDTO updateBillItems(@RequestBody BillItemListDTO billItems){
		BillResponseDTO response = new BillResponseDTO();
		try {
			response = billService.updateBillItems(billItems);			

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
	
	/**
	 * update bill payment
	 * @param billPayment
	 * @return BillResponseDTO
	 */
	@RequestMapping(value = "updateBillPayment", method = RequestMethod.POST)
	@ResponseBody
	public BillResponseDTO updateBillPayment(@RequestBody BillPaymentDTO billPayment){
		BillResponseDTO response = new BillResponseDTO();
		try {
			response = billService.updateBillPayment(billPayment);			

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
	/**
	 * list bill
	 * @param filterDTO
	 * @return BillListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public BillListResponseDTO list(@RequestBody FilterDTO filterDTO){
		BillListResponseDTO response = new BillListResponseDTO();
		try {
			response = billService.list(filterDTO);			

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
	
	/**
	 * view bill
	 * @param uid
	 * @return BillViewResponseDTO
	 */
	@RequestMapping(value = "view/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public BillViewResponseDTO view(@PathVariable String uid){
		BillViewResponseDTO response = new BillViewResponseDTO();
		try {
			response = billService.view(uid);			

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
	
	@RequestMapping(value = "discharge/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public BillResponseDTO discharge(@PathVariable String uid){
		BillResponseDTO response = new BillResponseDTO();
		try {
			response = billService.discharge(uid);			

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
	
	/**
	 * update bill discount
	 * @param billDiscountDetails
	 * @return ResponseDTO
	 */ 
	@RequestMapping(value = "updateBillDiscount", method = RequestMethod.POST)
	@ResponseBody
	public BillResponseDTO updateBillDiscount (@RequestBody BillDiscountDetailsDTO billDiscountDetails){
		BillResponseDTO response = new BillResponseDTO();
		try {
			response = billService.updateBillDiscount(billDiscountDetails);			

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
	
	/**
	 * bill list
	 * 
	 * 
	 * @return PatientListResponseDTO
	 */
	@RequestMapping(value = "getBills", method = RequestMethod.GET)
	@ResponseBody
	public BillListResponseDTO getPatients() {
		BillListResponseDTO response = new BillListResponseDTO();
		try {
			response = billService.getBills();
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
	
	/**
	 * @return the billService
	 */
	public BillService getBillService() {
		return billService;
	}
	/**
	 * @param billService the billService to set
	 */
	public void setBillService(BillService billService) {
		this.billService = billService;
	}
}
