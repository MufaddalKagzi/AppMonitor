package com.tcs.appmonitor.controller;

import java.io.IOException;
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
import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.Constants;
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class AccessController
 */
@WebServlet("/access")
public class AccessController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(AccessController.class);     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);	
		ServletContext context= getServletContext();
		Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	    User user=null;
	    if(session!=null)
	    	user = (User) session.getAttribute("user");
	    if(user != null) {
	     try { 	
	    	User userObj =  appdao.getUserDetail(user.getNetId());
			if(userObj==null) { 
				
				RequestDispatcher view = context.getRequestDispatcher("/accessrequest.jsp");
				view.include(request, response);
				
			}else {
            	RequestDispatcher view = context.getRequestDispatcher("/home.jsp");
            	if(userObj.getUserRole()== Constants.GUEST_ROLE)
            		request.setAttribute("message"," Your request is pending with supervisor for approval. ");
            	else if (userObj.getUserRole()==Constants.USER_ROLE || userObj.getUserRole()==Constants.ADMIN_ROLE)
            		request.setAttribute("message"," You already have access. ");
				view.include(request, response);
             }
	     } catch (Exception e) {
				LOGGER.error("Exception in AccessController  : " + e.getMessage());
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
