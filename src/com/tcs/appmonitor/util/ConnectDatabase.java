package com.tcs.appmonitor.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.tcs.appmonitor.dao.ApplicationDao;

public class ConnectDatabase {
	 
	static final Logger LOGGER = Logger.getLogger(ApplicationDao.class);
		
	public static Connection connectSqlite(String databasePath) {  
		
        try {
        	Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:"+databasePath); 
            LOGGER.info(" Database Connected Successfully : ");
            return conn;
        } catch (ClassNotFoundException ex) {
        	LOGGER.error(" SQLite Database Connection Failed : "  + ex.getMessage());
        	ex.printStackTrace();
        	return null;
        } catch (SQLException ex) {
        	LOGGER.error(" SQLite Database Connection Failed : "  + ex.getMessage());
        	ex.printStackTrace();
        	return null;
        }          
    }	
}
