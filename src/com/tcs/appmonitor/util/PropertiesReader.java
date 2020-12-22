package com.tcs.appmonitor.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public  class  PropertiesReader {
	
	static final Logger LOGGER = Logger.getLogger(PropertiesReader.class);
	
    public PropertiesReader() {

    }   
    public static String getContextParameter(HttpServletRequest request, String paramName) {
    	String propValue = request.getServletContext().getInitParameter(paramName);    	      
        return propValue;
    }    
   
    public static Properties getApplicationProperty(HttpServletRequest request) {
    	String propertiesFile = PropertiesReader.getContextParameter(request, "properties.file");
    	if(propertiesFile == null || propertiesFile.length() == 0) {
    		LOGGER.error("The definition for 'properties.file' was not found in web.xml.");
    		return null;
    	}
    	
    	InputStream input = request.getServletContext().getResourceAsStream("/WEB-INF/" + propertiesFile);
    	
    	if(input == null) {
    		LOGGER.error("InputStream is null while reading properties file for HttpServletRequest .");
    		return null;
    	}
    	
    	Properties properties = new Properties();
    	
    	try {
    		properties.load(input);
    		LOGGER.info("Properties file loaded Successfully for  HttpServletRequest.");
    	} catch(IOException ex) {
    		LOGGER.error("Properties file loading was failed  for  HttpServletRequest.");
    	}
    	return properties;
    	
    } 
    
    public static Properties getApplicationProperty(ServletContext servletContext) {
    	
    	InputStream input = servletContext.getResourceAsStream("/WEB-INF/app.properties");
    	
    	if(input == null) {
    		LOGGER.error("InputStream is null while reading properties file for ServletContext.");
    		return null;
    	}
    	
    	Properties properties = new Properties();
    	
    	try {
    		properties.load(input);
    		LOGGER.info("Properties file loaded Successfully for ServletContext..");
    	} catch(IOException ex) {
    		LOGGER.error("Properties file loading was failed  for  ServletContext.");
    		return null;
    	}
    	return properties;
    	
    } 
    
 
    
  
}

