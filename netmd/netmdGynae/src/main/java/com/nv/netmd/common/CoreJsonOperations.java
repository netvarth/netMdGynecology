package com.nv.netmd.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nv.netmd.exception.RemoteCallException;
import com.nv.netmd.pl.entity.ErrorCodeEnum;

public class CoreJsonOperations {
	
	private static final Logger log = Logger.getLogger(CoreJsonOperations.class);
	
	public static boolean isReachable(String url){
		
		Socket socket = null;
		boolean reachable = false;
		try {
		    socket = new Socket(url, 80);
		    reachable = true;
		} catch (UnknownHostException e) {
			log.error("EXception",e);
			System.out.println("WWW.NETMD.COM is not Available");
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("EXception",e);
			System.out.println("WWW.NETMD.COM is not Available");
			return reachable;
		} finally {            
		    if (socket != null) try { socket.close(); } catch(IOException e) {}
		}
				return reachable;		
	}
	
	
	public static void respondToWebService(Object obj, HttpServletResponse response){
		try {
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setObjectMapper(new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT,true));
			response.setContentType("application/json");
			HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
			converter.write(obj, new MediaType("application","json"), outputMessage);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("EXception",e);
		}
	}
	
	
	public synchronized static <T> T call(String url,Object obj,Class<T> responseClass) throws RemoteCallException{
		T returnObj = null;
		try {
			RestTemplate restOps = new RestTemplate();
			StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
			List<MediaType> mediaTypes = new ArrayList<MediaType>();
			mediaTypes.add(MediaType.parseMediaType("application/json"));
			stringConverter.setSupportedMediaTypes(mediaTypes);
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(stringConverter);
			restOps.setMessageConverters(list);
			ObjectMapper maper = new ObjectMapper();
			String jsonRequest = maper.writeValueAsString(obj);
			//System.out.println("JSON INPUT"+jsonRequest);
			String jsonResp = restOps.postForObject(url,jsonRequest,String.class);
			returnObj = maper.readValue(jsonResp, responseClass);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			log.error("EXception",e);
			RemoteCallException se =new RemoteCallException(ErrorCodeEnum.ConnectionFailed);
			se.setDisplayErrMsg(true);
			throw se;
		}
		catch (JsonMappingException e) {
			e.printStackTrace();
			log.error("EXception",e);
			RemoteCallException se =new RemoteCallException(ErrorCodeEnum.ConnectionFailed);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (IOException e) {
			e.printStackTrace();
			log.error("EXception",e);
			RemoteCallException se =new RemoteCallException(ErrorCodeEnum.ConnectionFailed);
			se.setDisplayErrMsg(true);
			throw se;
		} catch (RestClientException e){
		e.printStackTrace();
		log.error("EXception",e);
		RemoteCallException se =new RemoteCallException(ErrorCodeEnum.ConnectionFailed);
		se.setDisplayErrMsg(true);
		throw se;
	}
		return returnObj;
	}
	
	public synchronized static <T> T call(String url,Class<T> responseClass){
		T returnObj = null;
		try {
			RestTemplate restOps = new RestTemplate();
			StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
			List<MediaType> mediaTypes = new ArrayList<MediaType>();
			mediaTypes.add(MediaType.parseMediaType("application/json"));
			stringConverter.setSupportedMediaTypes(mediaTypes);
			List<HttpMessageConverter<?>> list = new ArrayList<HttpMessageConverter<?>>();
			list.add(stringConverter);
			restOps.setMessageConverters(list);
			ObjectMapper maper = new ObjectMapper();			
			String jsonResp = restOps.getForObject(url,String.class);
			returnObj = maper.readValue(jsonResp, responseClass);		
		} catch (Exception e) {
			e.printStackTrace();
			log.error("EXception",e);
		}
		return returnObj;
	}
	
	/*public static void sendDefaultErrorMessage(HttpServletResponse response){
		try {
			ErrorMsgResponse errorMsg = new ErrorMsgResponse();
			errorMsg.setErrorMsg("Error while parsing your request. Please contact Administrator");
			MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
			converter.setObjectMapper(new ObjectMapper().configure(SerializationConfig.Feature.INDENT_OUTPUT,true));
			response.setContentType("application/json");
			HttpOutputMessage outputMessage = new ServletServerHttpResponse(response);
			converter.write(errorMsg, new MediaType("application","json"), outputMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
		
}
