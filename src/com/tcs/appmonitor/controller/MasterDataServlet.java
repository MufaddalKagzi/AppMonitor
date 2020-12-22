package com.tcs.appmonitor.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.tcs.appmonitor.dao.ApplicationDao;
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class MasterDataServlet
 */
@WebServlet("/masterDataServlet")
public class MasterDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	static final Logger LOGGER = Logger.getLogger(MasterDataServlet.class);
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException
    	{
    	
    	Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	    
	    
    	 response.setContentType("text/html;charset=UTF-8");
    	 PrintWriter out = response.getWriter();
    	 String subTower = request.getParameter("subtower");
    	 
    	 JSONObject jo = new JSONObject();
    	 Map<Integer,String> masterApp =null;
    	 try {
    		 if(null!= subTower) {
    			 int subtowerId = Integer.parseInt(subTower.trim());
    			 masterApp = appdao.getApplications(subtowerId);   
    			 if (masterApp.size() < 0 ) {
	    			 jo.put("status", "ERROR");
	    		 }else {
	    			 jo.put("status", "OK");					
	    			 jo.put("AppNames", masterApp);
	    		 }
	    		 out.println(jo);
    		 }
    	 }catch(Exception ex) {
    		 LOGGER.error(" Exception in  Ajax Call for MasterDataServlet : " + ex.getMessage());
    	 }
    	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
