package com.tcs.appmonitor.controller;

import java.io.IOException;
import java.util.Date;
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

import com.tcs.appmonitor.dao.ApplicationDao;
import com.tcs.appmonitor.entities.Dashboard;
import com.tcs.appmonitor.entities.Master;
import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.Constants;
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class ApplicationDetails
 */
@WebServlet("/appdetails")
public class ApplicationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	    ServletContext context= getServletContext();
	    User user=null;
	    if(session!=null)
	    	user = (User) session.getAttribute("user");
		if(user != null) {
			
			Integer userRole = user.getUserRole();
			if(userRole==Constants.ADMIN_ROLE || userRole==Constants.USER_ROLE) { 	
					
				 List<Master> masterList = appdao.loadMasterData(false, user.getUserTower());
				 Date scanDate = null;
				for(Master mstrObj :masterList) {
						scanDate = mstrObj.getScanDate();
				}
				Dashboard dashboard = new Dashboard();
				dashboard.setScanDate(scanDate);
							 
		         session.setAttribute("masterList",masterList);
				 session.setAttribute("dashboard",dashboard);				 
				
            	RequestDispatcher view = context.getRequestDispatcher("/appdetails.jsp");
				view.include(request, response);
             }else {
            	RequestDispatcher view = context.getRequestDispatcher("/home.jsp");
            	session.setAttribute("message"," Access Denied. ");
            	view.include(request, response);
             }
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/login.jsp");
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
