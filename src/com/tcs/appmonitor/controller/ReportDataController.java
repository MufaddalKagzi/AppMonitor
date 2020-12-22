package com.tcs.appmonitor.controller;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.tcs.appmonitor.dao.ApplicationDao;
import com.tcs.appmonitor.entities.Master;
import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.PropertiesReader;
import com.tcs.appmonitor.util.Utility;

/**
 * Servlet implementation class ReportData
 */
@WebServlet("/reportdata")
public class ReportDataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(UserRoleController.class);  
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportDataController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			
		HttpSession session = request.getSession(false);
		
		ServletContext context= getServletContext();
		Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	    User user=null;
	    if(session!=null)
	    	user = (User) session.getAttribute("user");
	    if(user != null || request == null) {	  
		    	
	    	    String stower = request.getParameter("subtowerdd").trim();
				String server = request.getParameter("serverdd").trim();
				String startdate = request.getParameter("startdate").trim();		
				String enddate = request.getParameter("enddate").trim();	
	    	
			    try { 
			    	int appId = Integer.parseInt(server);
			    	int stowerId = Integer.parseInt(stower);
			    	String sDate = Utility.datetoString(startdate);
			    	String eDate = Utility.datetoString(enddate);
			    	String appName = appdao.getAppName(appId);
			    	
			    	long totalScanCount = Utility.getScanCount(sDate , eDate);
			    	
			    	String avlbleGraphData = appdao.getAvailabiltyGraphData(user.getUserTower() ,appId ,sDate,eDate ,totalScanCount);
			    	List<Master> hstryList = appdao.getHistoryList(user.getUserTower(), appId, sDate, eDate);
			    	String hstryGraph = ApplicationDao.HISTORY_GRAPH;
			    	
			    	request.setAttribute("tower",user.getUserTower());
			    	request.setAttribute("stower",stowerId);
			    	request.setAttribute("appName",appName);
			    	request.setAttribute("daterange",sDate + " - " + eDate);
			    	request.setAttribute("totalScanCount", totalScanCount);
			    	
			    	request.setAttribute("avlbleGraphData",avlbleGraphData);
			    	request.setAttribute("hstryList",hstryList);
			    	request.setAttribute("hstryGraph",hstryGraph);
			    	
			    	RequestDispatcher view = context.getRequestDispatcher("/reportdata.jsp");							
					view.forward(request, response);						
			    	
				} catch (Exception e) {
					LOGGER.error("Exception in ReportDataController  : " + e.getMessage());
				}				
		}
		else {
			RequestDispatcher view = context.getRequestDispatcher("/login.jsp");
			view.include(request, response);
		}
	    
	}

}
