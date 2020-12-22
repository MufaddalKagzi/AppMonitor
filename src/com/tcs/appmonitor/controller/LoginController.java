package com.tcs.appmonitor.controller;

import java.io.IOException;
import java.util.Hashtable;
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
import com.tcs.appmonitor.entities.Entity;
import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.AuthenticateUser;
import com.tcs.appmonitor.util.Constants;
import com.tcs.appmonitor.util.PropertiesReader;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = request.getParameter("netId");
        String password = request.getParameter("password");
      
        ServletContext context= getServletContext();
        HttpSession session = request.getSession();
        Properties properties = PropertiesReader.getApplicationProperty(request);
        ApplicationDao appdao =new ApplicationDao(properties);
        Hashtable htable = AuthenticateUser.authenticateUser(properties.getProperty("ldapdomain") , userId, password, properties.getProperty("ldapurl"));
        
        if(htable!=null)
         {
        	User userObj =  appdao.getUserDetail(userId);
        	if(userObj ==null  ) {     		
        		userObj = new User((String)htable.get("sAMAccountName"),(String)htable.get("displayName"),(String)htable.get("mail"),Constants.GUEST_ROLE,0);
        	}
        	
        	List<Entity> entityList  = appdao.loadEntityList();
        	Map<Integer , String> entityMap  = appdao.loadEntityMap();
        	
        	session.setAttribute("user", userObj);        	
        	session.setAttribute("entityList", entityList);
        	session.setAttribute("entityMap", entityMap);
        	
        	RequestDispatcher view = context.getRequestDispatcher("/home.jsp");
			view.include(request, response);
            
         }else {
        	RequestDispatcher view = context.getRequestDispatcher("/login.jsp");
        	request.setAttribute("errormsg","Invalid NetId/Password.");
 		    view.include(request, response);
         }
        
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ServletContext context= getServletContext();
		session.invalidate();
		RequestDispatcher view = context.getRequestDispatcher("/login.jsp");
		view.include(request, response);
	}

}
