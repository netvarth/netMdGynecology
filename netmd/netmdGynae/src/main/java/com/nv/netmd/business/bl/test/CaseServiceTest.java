package com.nv.netmd.business.bl.test;






import static org.junit.Assert.*;

import java.io.IOException;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nv.netmd.business.bl.service.PatientService;
import com.nv.netmd.exception.ServiceException;
import com.nv.netmd.rs.dto.CaseDTO;
import com.nv.netmd.rs.dto.ResponseDTO;

@RunWith(SpringJUnit4ClassRunner.class)

@ContextConfiguration(locations = { " file:resource/context.xml",
		"file:resource/testDataSource.xml","file:resource/netmd-context.xml" })

public class CaseServiceTest {
	
	 @Autowired
     private ApplicationContext applicationContext;

	 @Test
     public void createCaseTest() throws ServiceException{
		  
	     ObjectMapper objectMapper = (ObjectMapper) applicationContext.getBean("jacksonObjectMapper");  
	     Resource resource = applicationContext.getResource("file:E:/NVWorkspace/netMd1.0/systemTest/createGynaCase.txt"); 
	     try {
			
	    	 PatientService patientService = (PatientService)applicationContext.getBean("patient.service");
	    	 CaseDTO bundle =objectMapper.readValue(resource.getInputStream() , CaseDTO.class);
			 ResponseDTO response =(ResponseDTO) patientService.AutoSaveCase(bundle);
			  assertNotNull(response);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	     
    	
	 }
	 


	/**
	 * @return the applicationContext
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * @param applicationContext the applicationContext to set
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	
	

}
