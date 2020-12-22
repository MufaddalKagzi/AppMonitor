package com.tcs.appmonitor.controller;

import java.io.IOException;
import java.util.Hashtable;
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
import com.tcs.appmonitor.util.AuthenticateUser;
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class UserRoleController
 */
@WebServlet("/adduser")
public class UserRoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(UserRoleController.class);  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRoleController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String page = request.getParameter("page").trim(); 
		String tower = request.getParameter("towerid").trim();
		String netId = request.getParameter("netId").trim();		
		
		
		HttpSession session = request.getSession(false);
		
		ServletContext context= getServletContext();
		Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	    int isSave = -1;    
	    User user=null;
	    if(session!=null)
	    	user = (User) session.getAttribute("user");
	    if(user != null) {	    
			    try { 	
			    	if(page!=null && page.equalsIgnoreCase("access")) {
			    		user.setUserTower(Integer.parseInt(tower));
			    		isSave = appdao.saveUser(user);	
			    		if(isSave==1)
			    			request.setAttribute("message","Request submitted for approval.");
			    		else
			    			request.setAttribute("message","Error : Try again.");
			    		RequestDispatcher view = context.getRequestDispatcher("/home.jsp");							
						view.forward(request, response);
			    	}else {
			    		String role = request.getParameter("role").trim();
			    		Hashtable htable=null;
						try {
							htable = AuthenticateUser.getAnyUserAttr( netId , 0, properties.getProperty("ldapurl"));
						}catch(Exception ex) {
							LOGGER.error("Exception in UserRoleController  : " + ex.getMessage());
						}
						if(htable==null) {							
							request.setAttribute("message","Invalid NetId.");							
						}else {
							User userObj = new User((String)htable.get("sAMAccountName"),(String)htable.get("displayName"),(String)htable.get("mail"),Integer.parseInt(role),Integer.parseInt(tower));
							isSave = appdao.saveUser(userObj);
							if(isSave==1)
				    			request.setAttribute("message","User added successfully.");
				    		else
				    			request.setAttribute("message","Error : Try again.");
						}
						RequestDispatcher view = context.getRequestDispatcher("/admin");							
						view.forward(request, response);						
			    	}
				} catch (Exception e) {
					LOGGER.error("Exception in UserRoleController  : " + e.getMessage());
				}				
		}
		else {
			RequestDispatcher view = context.getRequestDispatcher("/login.jsp");
			view.include(request, response);
		}					
		
	}

}
