package com.tcs.appmonitor.util;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.tcs.appmonitor.dao.ApplicationDao;

public class ApplicationSchedular  implements Runnable  {

	static final Logger LOGGER = Logger.getLogger(ApplicationSchedular.class);
	
	private ServletContext context;
	private ApplicationDao appDao;
	public ApplicationSchedular(ServletContext context) {
		 
	        this.context = context;
	    }
	 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 try {
			 LOGGER.info(" **** Schedular Jobs Started **** " );		 
			 Properties prop = PropertiesReader.getApplicationProperty(context);
			 appDao = new ApplicationDao(prop);	
			 appDao.runSchedular();
			 LOGGER.info(" **** Schedular Jobs Completed **** " );
			 LOGGER.warn("Application's Scan completed Successfully.");	
		 }catch(Exception ex) {
			 LOGGER.error(" Error in Schedular : " + ex.getMessage() );
		 }
		 
	}
	
}
