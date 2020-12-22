package com.tcs.appmonitor.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.appmonitor.dao.ApplicationDao;
import com.tcs.appmonitor.entities.Dashboard;
import com.tcs.appmonitor.entities.Master;
import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.Constants;
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		ServletContext context= getServletContext();
		Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	    User user=null;
	    if(session!=null)
	    	user = (User) session.getAttribute("user");
		if(user != null) {
			
			Integer userRole = user.getUserRole();
			if(userRole==Constants.ADMIN_ROLE || userRole==Constants.USER_ROLE) { 	
				
				String graphData = appdao.getSubtowerGraphData(user.getUserTower());
				String hstryGraphData = appdao.getHistoryGraphData(user.getUserTower());
				List<Master> masterList = appdao.loadMasterData(false ,user.getUserTower());
		        
				int upCount =0, downCount=0 , totalCount = 0;
				Date scanDate = null;
				for(Master mstrObj :masterList) {
					if(mstrObj.getStatus().equalsIgnoreCase(Constants.STATUS_DOWN))
						downCount++;
					else if(mstrObj.getStatus().equalsIgnoreCase(Constants.STATUS_UP))
					    upCount++;
					
					totalCount++;
					scanDate = mstrObj.getScanDate();
				}
				
				Dashboard dashboard = new Dashboard();
				dashboard.setScanDate(scanDate);
				dashboard.setScanCount(totalCount);
				dashboard.setScanDownCount(downCount);
				dashboard.setScanUpCount(upCount);
				
				Map<String , List<String>> timelineMap  =  appdao.loadTimeLineData();
				 
		        
				request.setAttribute("graphData",graphData);
				request.setAttribute("hstryGraphData",hstryGraphData);
				request.setAttribute("dashboard",dashboard);
				request.setAttribute("masterList",masterList);
				request.setAttribute("timelineMap",timelineMap);
								 
            	RequestDispatcher view = context.getRequestDispatcher("/dashboard.jsp");
				view.include(request, response);
             }else {
            	RequestDispatcher view = context.getRequestDispatcher("/home.jsp");
            	session.setAttribute("message"," Access Denied. ");
				view.include(request, response);
             }
			
		}
		else {
			RequestDispatcher view = context.getRequestDispatcher("/login.jsp");
			view.include(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
