package com.tcs.appmonitor.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.Constants;

/**
 * Servlet implementation class ReportController
 */
@WebServlet("/report")
public class ReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportController() {
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
			    User user=null;
			    if(session!=null)
			    	user = (User) session.getAttribute("user");
				if(user != null) {
					
					Integer userRole = user.getUserRole();
					if(userRole==Constants.ADMIN_ROLE || userRole==Constants.USER_ROLE) { 
						
						RequestDispatcher view = context.getRequestDispatcher("/report.jsp");					
						
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
