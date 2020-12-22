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
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class EditUserRole
 */
@WebServlet("/edituser")
public class EditUserRoleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = Logger.getLogger(EditUserRoleController.class);  
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserRoleController() {
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
		String netId = request.getParameter("netId").trim();
		String role = request.getParameter("role").trim();
		String tower = request.getParameter("towerid").trim();
		
		HttpSession session = request.getSession(false);
		
		ServletContext context= getServletContext();
		Properties properties = PropertiesReader.getApplicationProperty(request);
	    ApplicationDao appdao =new ApplicationDao(properties);
	   
	    int isSave =-1;
	    String message = "";
	    
	    User user=null;
	    if(session!=null)
	    	user = (User) session.getAttribute("user");
	    if(user != null) {	
			    try {
			    	
			    	User userObj = new User();			    	
			    	userObj.setNetId(netId);
			    	userObj.setUserRole(Integer.parseInt(role));
			    	userObj.setUserTower(Integer.parseInt(tower));
			    	
			    	isSave= appdao.updateUser(userObj);			    	
			   
				} catch (Exception e) {
					LOGGER.error("Exception in Add User : " + e.getMessage());
				}
			RequestDispatcher view = context.getRequestDispatcher("/admin");
			
	    	if(isSave==1)
	    		message = "User edited successfully.";
	    	else
	    		message = "Error : Please try again.";
	    	
	    	request.setAttribute("message",message);
			view.forward(request, response);
		    
		}
		else {
			RequestDispatcher view = context.getRequestDispatcher("/login.jsp");
			view.include(request, response);
		}	
	}
}