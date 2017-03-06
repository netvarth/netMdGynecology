/**
 * RoomResource.java
 * @author Sreeram T G 
 *
 * Version 1.0 Jul 29, 2013
 *
 * Copyright (c) 2013 Netvarth Technologies, Inc.
 * All rights reserved.
 *
 */
package com.nv.netmd.settings.rs.ui;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.ErrorDTO;
import com.nv.netmd.rs.dto.FilterDTO;
import com.nv.netmd.rs.dto.Parameter;
import com.nv.netmd.rs.dto.ResponseDTO;
import com.nv.netmd.rs.dto.RoomDTO;
import com.nv.netmd.rs.dto.RoomListResponseDTO;
import com.nv.netmd.rs.dto.RoomTypeDTO;
import com.nv.netmd.rs.dto.RoomTypeListResponseDTO;
import com.nv.netmd.settings.bl.service.RoomService;

/**
 *
 *
 * @author Sreeram T G
 */
@Controller
@RequestMapping("ui/room/")
public class RoomResource {
	private RoomService roomService;
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO create(@RequestBody RoomDTO room){
		ResponseDTO response = new ResponseDTO();
		try {
			response = roomService.create(room);			

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
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO update(@RequestBody RoomDTO room){
		ResponseDTO response = new ResponseDTO();
		try {
			response = roomService.update(room);			

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
	@RequestMapping(value = "view/{id}", method = RequestMethod.GET)
	@ResponseBody
	public RoomDTO viewRoom(@PathVariable int id) {
		RoomDTO response=new RoomDTO();
		try {
			response = roomService.view(id);
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
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteRoom(@PathVariable int id) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = roomService.delete(id);
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
	@RequestMapping(value = "createRoomType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO createRoomType(@RequestBody RoomTypeDTO roomType){
		ResponseDTO response = new ResponseDTO();
		try {
			response = roomService.createRoomType(roomType);			

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
	@RequestMapping(value = "updateRoomType", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO updateRoomType(@RequestBody RoomTypeDTO roomType){
		ResponseDTO response = new ResponseDTO();
		try {
			response = roomService.updateRoomType(roomType);			

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
	@RequestMapping(value = "viewRoomType/{id}", method = RequestMethod.GET)
	@ResponseBody
	public RoomTypeDTO viewRoomType(@PathVariable int id) {
		RoomTypeDTO response=new RoomTypeDTO();
		try {
			response = roomService.viewRoomType(id);
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
	@RequestMapping(value = "deleteRoomType/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseDTO deleteRoomType(@PathVariable int id) {
		ResponseDTO response=new ResponseDTO();
		try {
			response = roomService.deleteRoomType(id);
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
	 * room type list
	 * 
	 * @param filterDTO
	 * @return RoomTypeListResponseDTO
	 */
	@RequestMapping(value = "listRoomType", method = RequestMethod.POST)
	@ResponseBody
	public RoomTypeListResponseDTO getRoomTypeList(
			@RequestBody FilterDTO filterDTO) {
		RoomTypeListResponseDTO response = new RoomTypeListResponseDTO();
		try {
			response = roomService.listRoomType(filterDTO);
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
	
	
	
	@RequestMapping(value = "getRooms", method = RequestMethod.GET)
	@ResponseBody
	public RoomListResponseDTO getRooms() {
		RoomListResponseDTO response = new RoomListResponseDTO();
		try {
			response = roomService.getRooms();
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
	 * list of rooms
	 * @param filterDTO
	 * @return RoomListResponseDTO
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	@ResponseBody
	public RoomListResponseDTO getRoomList(@RequestBody FilterDTO filterDTO) {
		RoomListResponseDTO response = new RoomListResponseDTO();
		try {
			response = roomService.list(filterDTO);
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
	 * get room types
	 * @return RoomListResponseDTO
	 */
	@RequestMapping(value="getRoomTypes",method=RequestMethod.GET)
	@ResponseBody
	public RoomTypeListResponseDTO getRoomTypes() {
		RoomTypeListResponseDTO response = new RoomTypeListResponseDTO();
		try {
			response = roomService.getRoomTypes();
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
	 * @return the roomService
	 */
	public RoomService getRoomService() {
		return roomService;
	}
	/**
	 * @param roomService the roomService to set
	 */
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
}
