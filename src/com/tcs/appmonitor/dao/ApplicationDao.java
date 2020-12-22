package com.tcs.appmonitor.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tcs.appmonitor.entities.Entity;
import com.tcs.appmonitor.entities.Master;
import com.tcs.appmonitor.entities.User;
import com.tcs.appmonitor.util.ConnectDatabase;
import com.tcs.appmonitor.util.Constants;
import com.tcs.appmonitor.util.SendEmail;
import com.tcs.appmonitor.util.SendInfraEmail;
import com.tcs.appmonitor.util.Utility;

public class ApplicationDao {
	
	static final Logger LOGGER = Logger.getLogger(ApplicationDao.class);
	Properties prop = new Properties();

	
	public ApplicationDao(Properties prop) {		
		this.prop =prop;				
	}
	
	
	public User getUserDetail(String userId) {
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		User usr = null;
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_role"));
			pstmt.setString(1, userId);
			rs    = pstmt.executeQuery();			
			while (rs.next()) {			
				usr = new User(rs.getString(1), rs.getString(2),rs.getString(3), rs.getInt(4) ,rs.getInt(5));							
			}
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getUserDetail : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getUserDetail : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return usr;
	}
	
	public int saveUser (User user) {
		int status =-1;
		Connection connection=null;
		PreparedStatement pstmt = null;
		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt = connection.prepareStatement(prop.getProperty("query.app_insert_user"));
			pstmt.setString(1, user.getNetId());
            pstmt.setString(2, user.getUserName());
            pstmt.setString(3, user.getEmailId());
            pstmt.setInt(4, user.getUserRole());
            pstmt.setInt(5, user.getUserTower());
            status =  pstmt.executeUpdate();
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for saveUser : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for saveUser : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public List<User> getUsersList(){
		List<User> userList = new ArrayList<User>();		
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs =null;		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			stmt  = connection.createStatement();
			rs    = stmt.executeQuery(prop.getProperty("query.app_role_list"));			
			while (rs.next()) {
				User user = new User();				
				user.setNetId(rs.getString(1));
				user.setUserName(rs.getString(2));
				user.setEmailId(rs.getString(3));
				user.setUserRole(rs.getInt(4));
				user.setUserTower(rs.getInt(5));
				userList.add(user);
			}
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getUsersList : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getUsersList : " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return userList;
	}
	
	
	public int updateUser (User user) {
		int status =-1;
		Connection connection=null;
		PreparedStatement pstmt = null;
		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt = connection.prepareStatement(prop.getProperty("query.app_update_user"));
			pstmt.setInt(1, user.getUserRole());
			pstmt.setInt(2, user.getUserTower());
			pstmt.setString(3, user.getNetId());
            status =  pstmt.executeUpdate();
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for updateUser : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {				
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for updateUser : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public   List<Entity> loadEntityList(){		
		 List<Entity> entityList =new ArrayList<Entity>();	
		
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs =null;		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			stmt  = connection.createStatement();
			rs    = stmt.executeQuery(prop.getProperty("query.app_entity"));			
			while (rs.next()) {
				
				Entity entityObj = new Entity();
				entityObj.setEntityId(rs.getInt(1));
				entityObj.setEntityType(rs.getString(2));
				entityObj.setEntityName(rs.getString(3));
				entityObj.setIsActive(rs.getString(4));
				entityObj.setEntityValue(rs.getInt(5));				
				entityObj.setParentEntity(rs.getInt(6));
			
				entityList.add(entityObj);												
			}
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for loadEntityMap : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for loadEntityMap : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return entityList;
	}
	
	public  Map<Integer , String> 	loadEntityMap(){		
		Map<Integer , String> entityMap = new HashMap<Integer , String>();
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs =null;		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			stmt  = connection.createStatement();
			rs    = stmt.executeQuery(prop.getProperty("query.app_entity"));			
			while (rs.next()) {				
				entityMap.put(rs.getInt(1),rs.getString(3));														
			}
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for loadEntityMap : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for loadEntityMap : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return entityMap;
	}
	
	public List<Master> loadMasterData (boolean flag, int towerId) {
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		String query ="";
		 if(flag)
			 query = "query.app_schedular_data";
		 else
	        query = "query.app_master_data";
		List<Master> mstrList = new ArrayList<Master>();
		try {
		
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt = connection.prepareStatement(prop.getProperty(query));
			pstmt.setInt(1, towerId);
			rs    = pstmt.executeQuery();
			while (rs.next()) {
				Master master = new Master();
				master.setId(rs.getInt(1));
				master.setTowerId(rs.getInt(2));
				master.setSubTowerId(rs.getInt(3));
				master.setAppName(rs.getString(4));
				master.setSiteName(rs.getString(5));
				master.setServerName(rs.getString(6));
				master.setServerLocation(rs.getString(7));
				master.setAppUrl(rs.getString(8));
				master.setAuthentication(rs.getString(9));
				master.setAbbreviaion(rs.getString(10));
				master.setIsActive(rs.getString(11));
				master.setCreatedBy(rs.getString(12));				
				master.setCreatedDate(Utility.stringToDate(rs.getString(13)));
				master.setModifiedBy(rs.getString(14));
				master.setModifiedDate(Utility.stringToDate(rs.getString(15)));	
				if(!flag) {
					master.setStatus(rs.getString(16));
					master.setScanDate(Utility.stringToDate(rs.getString(17)));
				}
				mstrList.add(master);				
			}
			
			
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for loadMasterData : " + ex.getMessage());
			 ex.printStackTrace();
		}catch(Exception ex) {
			 LOGGER.error("Exception for loadMasterData : " + ex.getMessage());
			 ex.printStackTrace();
		}
		finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for loadMasterData : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return mstrList;
	}
	
	
	public int deleteLastScan () {
		int status =-1;
		Connection connection=null;
		PreparedStatement pstmt = null;
		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt = connection.prepareStatement(prop.getProperty("query.app_delete_last_scan"));
			pstmt.execute();			
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for deleteLastScan : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {				
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for deleteLastScan : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public int saveLastScan ( Map<Integer,String> lstScanMap) {
		int status =-1;
		Connection connection=null;
		PreparedStatement pstmt = null;
		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt = connection.prepareStatement(prop.getProperty("query.app_last_scan"));
		 
			for (Map.Entry<Integer,String> entry : lstScanMap.entrySet()) {
				pstmt.setInt(1, entry.getKey());
				pstmt.setString(2, entry.getValue());				
				pstmt.addBatch();
			}
			int successCount[] =  pstmt.executeBatch();			
			
			LOGGER.info ("SQL successCount saveLastScan : " + successCount.length);
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for saveLastScan : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {				
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for saveLastScan : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	
	public int saveScanHistory ( Map<Integer,String> hstryScanMap) {
		int status =-1;
		Connection connection=null;
		PreparedStatement pstmt = null;
		int scanId = getId();
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt = connection.prepareStatement(prop.getProperty("query.app_down_hstry"));
		 
			for (Map.Entry<Integer,String> entry : hstryScanMap.entrySet()) {
				pstmt.setInt(1,scanId);
				pstmt.setInt(2, entry.getKey());
				pstmt.setString(3, entry.getValue());
				pstmt.addBatch();
			}
			int successCount[] =  pstmt.executeBatch();			
			
			LOGGER.info ("SQL successCount saveScanHistory : " + successCount.length);
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for saveScanHistory : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {				
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for saveScanHistory : " + e.getMessage());
				e.printStackTrace();
			}
		}
		return status;
	}
	
	public int getId() {
		Connection connection=null;
		Statement stmt = null;
		ResultSet rs =null;		
		
		int i=-1;
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			stmt  = connection.createStatement();
			rs    = stmt.executeQuery(prop.getProperty("query.app_maxid"));			
			while (rs.next()) {			
				i = rs.getInt(1);						
			}	
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getId : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getId : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return i;
	}

	public String getAppName(int appId) {
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		
		String appName =null;
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_name"));
			pstmt.setInt(1, appId);
			rs    = pstmt.executeQuery();			
					
				while (rs.next()) {	
					 int subtowerId = rs.getInt(2);
					  if(subtowerId == Constants.SUB_TOWER_GES ) 
						  	appName =	rs.getString(3); 
					  else  if(subtowerId == Constants.SUB_TOWER_TEAMCENTER || 	  subtowerId == Constants.SUB_TOWER_POLARION)
						    appName =  rs.getString(4); 
					  else if(subtowerId ==  Constants.SUB_TOWER_DOORS 	  || subtowerId == Constants.SUB_TOWER_PLASTIC || subtowerId == Constants.SUB_TOWER_CLEAR_CASE ||
							  subtowerId == Constants.SUB_TOWER_CM_SYNERGY ) 
						appName =  rs.getString(5);												
										
			}	
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getAppName : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getAppName : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return appName;
	}
	
	public String getSubtowerGraphData(int subtowerId){
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;	
		String graphdata ="";
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_subtower_graph"));
			pstmt.setInt(1, subtowerId);
			rs    = pstmt.executeQuery();
			
			
			StringBuffer str = new StringBuffer();
			str.append("[");
			while (rs.next()) {
				str.append (" {\"subTower\"  : " + "\"" + rs.getString(1) + "\", \"a\" : " +  "\"" + rs.getString(2)  + "\" , \"b\" : " + "\"" + rs.getString(3) + "\"  ,  \"c\" : " + "\""+rs.getString(4)  + "\"},"); 
			}
			graphdata = str.toString();
			if (graphdata.endsWith(",")) {
				graphdata = graphdata.substring(0, graphdata.length()-1);
			}
			graphdata = graphdata + "]";
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getUsersList : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getUsersList : " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return graphdata;
	}
	
	
	public String getAvailabiltyGraphData(int subtowerId , int appId, String startDate, String endDate , long totalScanCount){
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;	
		String graphdata ="";		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_dwn_cnt"));
			pstmt.setInt(1, subtowerId);
			pstmt.setInt(2, appId);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);
			rs    = pstmt.executeQuery();			
			
			StringBuffer str = new StringBuffer();
			while (rs.next()) {
				str.append (" [{ label  : 'Down',  value : " +  rs.getInt(1)  + " , color : '#f56954' } ,  { label  : 'Up',  value : " +  (totalScanCount - rs.getInt(1))  + " , color : '#00a65a' }]"); 
			}
			graphdata = str.toString();
			
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getUsersList : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getUsersList : " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return graphdata;
	}	
	
	
	public String getHistoryGraphData(int subtowerId){
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;	
		String graphdata ="";
		
		try {
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_hstry_graph"));
			pstmt.setInt(1, subtowerId);
			
			rs    = pstmt.executeQuery();			
			
			StringBuffer str = new StringBuffer();
			str.append("[");
			while (rs.next()) {
				str.append (" {scanDate: '<a href=\"javascript:onload("+rs.getString(3)+")\" class=\"text-aqua\"  class=\"text-aqua\" class=\"text-aqua\" class=\"text-aqua\" class=\"text-aqua\"  title = \"Click for more information...\">"+ rs.getString(4) +"</a>', a: " + rs.getInt(1)  + ", b: " + (rs.getInt(1)-rs.getInt(2)) + ", c: "+ rs.getInt(2) +"},");
			}
			graphdata = str.toString();
			if (graphdata.endsWith(",")) {
				graphdata = graphdata.substring(0, graphdata.length()-1);
			}
			graphdata = graphdata + "]";
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getUsersList : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getUsersList : " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return graphdata;
	}
	
	public static String HISTORY_GRAPH = null;
	
	public List<Master> getHistoryList(int towerId , int appId , String startDate, String endDate ){
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;			
		StringBuffer str = new StringBuffer();
		
		List<Master> masterLst = new ArrayList<Master>();
		try {
			
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_history_list"));
			pstmt.setInt(1, towerId);
			pstmt.setInt(2, appId);
			pstmt.setString(3, startDate);
			pstmt.setString(4, endDate);			
			rs    = pstmt.executeQuery();	
			str.append("[");
			while (rs.next()) {
				
				Master master = new Master();
				master.setTowerId(rs.getInt(1));
				master.setSubTowerId(rs.getInt(2));
				master.setAppName(rs.getString(3));
				master.setSiteName(rs.getString(4));
				master.setServerName(rs.getString(5));
				master.setStatus(rs.getString(6));
				master.setScanDate(Utility.stringToDate(rs.getString(7)));				 
				master.setId(rs.getInt(8));
				str.append ("{scanDate: '" + Utility.stringToDate(rs.getString(7)) + "', a : -10 },");
				  
				masterLst.add(master);
				}
			String graphdata = str.toString();
			
			  if (graphdata.endsWith(",")) { graphdata = graphdata.substring(0,
			  graphdata.length()-1); } graphdata = graphdata + "]";
			 
			
			HISTORY_GRAPH = graphdata;
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getUsersList : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getUsersList : " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return masterLst;
	}
	
	
	public List<Master> getScannedAppDetails (int scanId){
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;			
		
		List<Master> masterLst = new ArrayList<Master>();
		try {
			
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.app_scanned_detail"));
			pstmt.setInt(1, scanId);
			rs    = pstmt.executeQuery();	
			while (rs.next()) {
				
				Master master = new Master();
				master.setTowerId(rs.getInt(1));
				master.setSubTowerId(rs.getInt(2));
				master.setAppName(rs.getString(3));
				master.setSiteName(rs.getString(4));
				master.setServerName(rs.getString(5));
				master.setStatus(rs.getString(6));
				master.setScanDate(Utility.stringToDate(rs.getString(7)));
				
				masterLst.add(master);
			}
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getScannedAppDetails : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getScannedAppDetails : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return masterLst;
	}
	
	public Map<String , List<String>> loadTimeLineData() {
		Map<String , List<String>> timelineMap = new HashMap<String, List<String>>();
		try {
			FileInputStream fstream = new FileInputStream(prop.getProperty("logfile.path"));
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
		    while ((strLine = br.readLine()) != null) {
				String arr[]=new String[3];
				int i = 0 ;
			
				for (String retval: strLine.split("\\|")) {
					 arr[i] = retval;
					 i++;
				}				
				
				if( arr.length > 0  && arr[0].equalsIgnoreCase("WARN") && Utility.DateCompare(arr[1])) {
					
					 if(timelineMap.containsKey(arr[1])) {
						 timelineMap.get(arr[1]).add(arr[2]);
					 }else {
						 List<String> str = new ArrayList<String>();
						 str.add(arr[2]);
						 timelineMap.put(arr[1], str);
					 }
					 
				 } 
			}		
			br.close();
			fstream.close();
		} catch (FileNotFoundException ex) {
			LOGGER.error(" Log file not found : " + ex.getMessage());	
		}catch (IOException ex) {
			LOGGER.error(" IOException while reading Log file : " + ex.getMessage());	
		}		
		//Collections.sort(timelineMap);
		return timelineMap ;
		
	}
	
	
	
	
	
	public  Map<Integer,String> getApplications(int subtowerId){
		
		Map<Integer,String> appMap = new HashMap<Integer, String>();
		Connection connection=null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;		
		try {
			
			connection = ConnectDatabase.connectSqlite(prop.getProperty("databasePath"));
			pstmt  = connection.prepareStatement(prop.getProperty("query.apps_bytower"));
			pstmt.setInt(1, subtowerId);
			rs    = pstmt.executeQuery();	
									
			while (rs.next()) {					
				  if(subtowerId == Constants.SUB_TOWER_GES ) 
					  appMap.put(rs.getInt(1), rs.getString(2));
				else  if(subtowerId == Constants.SUB_TOWER_TEAMCENTER || 	  subtowerId == Constants.SUB_TOWER_POLARION)
					  appMap.put(rs.getInt(1), rs.getString(3));
				 else if(subtowerId ==  Constants.SUB_TOWER_DOORS 	  || subtowerId == Constants.SUB_TOWER_PLASTIC || subtowerId == Constants.SUB_TOWER_CLEAR_CASE ||
						  subtowerId == Constants.SUB_TOWER_CM_SYNERGY || subtowerId == Constants.SUB_TOWER_JIRA ) 
				 appMap.put(rs.getInt(1), rs.getString(4));									
			}
		}catch(SQLException ex) {
			 LOGGER.error("SQL Exception for getTowerMap : " + ex.getMessage());
			 ex.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				connection.close();
			}catch(Exception e) {
				LOGGER.error("SQL Exception  for getTowerMap : " + e.getMessage());
				e.printStackTrace();
			}
		}		
		return appMap;
	}	
	
	public void runSchedular() {
		
		/* Load Teamcenter logs from remote machine */
		Map<String, String> tcMap = Utility.readTeamCenterLogs(prop.getProperty("remote.tcpath"), prop.getProperty("local.tcpath"),
					 prop.getProperty("remote.username") ,  prop.getProperty("remote.password") , prop.getProperty("remote.tcfilename"));		
		
		/* Load Polarion logs from Unix Machine  */
		Map<String, String> polMap = Utility.readPolarionPlasticLogs(prop.getProperty("remote.polhostname"),prop.getProperty("remote.polpath"), prop.getProperty("local.polpath"),
				 prop.getProperty("remote.polusername") ,  prop.getProperty("remote.polpassword") );
		
		Map<Integer,String> lstScanMap = new HashMap<Integer, String>();
		Map<Integer,String> hstryScanMap = new HashMap<Integer, String>();
		
		try {			
			String status = null;
			List<Master> masterList = loadMasterData(true , Constants.TOWER_ENG);
			for(Master masterObj : masterList) {
				
				if (masterObj.getAuthentication().equalsIgnoreCase("Y") || masterObj.getAuthentication().equalsIgnoreCase("N")){ 
					boolean urlSts = Utility.checkApplicationURL(masterObj.getAppUrl(),masterObj.getAppName(), masterObj.getAuthentication() ,  
							                                        prop.getProperty("remote.username") ,  prop.getProperty("remote.password"));
					if(urlSts==true) {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_UP);
					}else {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_DOWN);
						hstryScanMap.put(masterObj.getId(), Constants.STATUS_DOWN);
					}
					
				}
				else if (masterObj.getSubTowerId() == Constants.SUB_TOWER_CLEAR_CASE ) {
					status = Utility.readClearCaseLogs(prop.getProperty("local.ccpath") , masterObj.getServerName().trim());
					if(status != Constants.NULL) {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_UP);
					}else {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_DOWN);
						hstryScanMap.put(masterObj.getId(), Constants.STATUS_DOWN);
					}
					
				}
				else if (masterObj.getSubTowerId() == Constants.SUB_TOWER_TEAMCENTER) {
					status = tcMap.get(masterObj.getAbbreviaion().trim());
					if(status != Constants.NULL){
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_UP);
					}else {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_DOWN);
						hstryScanMap.put(masterObj.getId(), Constants.STATUS_DOWN);
					}
				}else if (masterObj.getSubTowerId() == Constants.SUB_TOWER_POLARION || masterObj.getSubTowerId() == Constants.SUB_TOWER_PLASTIC || 
						  masterObj.getSubTowerId() == Constants.SUB_TOWER_JIRA) {
					status = polMap.get(masterObj.getSiteName().trim());
					if(status != Constants.NULL) {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_UP);
					}else {
						lstScanMap.put(masterObj.getId(),   Constants.STATUS_DOWN);
						hstryScanMap.put(masterObj.getId(), Constants.STATUS_DOWN);
					}
				}
			}
		}catch(Exception ex) 
		{
			LOGGER.error(" Exception in  runSchedular " + ex.getMessage());
		}
		
		
		/*  Save Scan Data  */
			deleteLastScan();
			saveLastScan (lstScanMap);	
			saveScanHistory (hstryScanMap);
		
		/* Send Email for Down Application Owner's */
		
		
		  List<Master> masterList = loadMasterData(false ,Constants.TOWER_ENG); 
		  Map<Integer , String> entityMap  = loadEntityMap();
		  if(masterList.size() > 0) {
			  Map<Integer , List<Master>> emailMap = new HashMap<Integer , List<Master>>();
				  for(Master masterObj : masterList) {
					 if(masterObj.getStatus().equals(Constants.STATUS_DOWN))
					 {
						  if(emailMap.containsKey(masterObj.getSubTowerId()))
							  emailMap.get(masterObj.getSubTowerId()).add(masterObj); 
						  else { 
							List<Master> downList = new ArrayList<Master>(); 
							downList.add(masterObj);
						  	emailMap.put(masterObj.getSubTowerId(), downList); 
						  	}			  
					 }	
				  }
				  for (int mapKey : emailMap.keySet()) { 
					    List<Master> mailList = emailMap.get(mapKey); 
					    SendEmail.sendHtmlEmail(prop,mailList,mapKey,entityMap); 
				   }		  
		  	}
		  
		  
		  /* Send Notification for Infra CPU Usage */
		  LOGGER.info(" INFRA_MAIL_INTERVAL [" + Constants.INFRA_MAIL_INTERVAL+"] / INFRA_INITIAL_COUNT [" + Constants.INFRA_INITIAL_COUNT+"]");
		  
		  if(Constants.INFRA_MAIL_INTERVAL == Constants.INFRA_INITIAL_COUNT) {
			  LOGGER.info("***** Infra Scan Started *****");
			  Constants.INFRA_INITIAL_COUNT = 0;
			  Map<String ,List<String>> teamCenterContent =  loadTeamCenterHTML();
			  if(teamCenterContent!=null) {
				  Map<String , Double> TC_CPUStatusMap = Utility.getCPUMap (teamCenterContent ,Constants.SUB_TOWER_TEAMCENTER);
				  if(TC_CPUStatusMap.size()>0)
			      SendInfraEmail.sendEmail(prop,TC_CPUStatusMap , Constants.SUB_TOWER_TEAMCENTER ,entityMap); 
			  }			  
			  Map<String ,List<String>> clearCaseContent = loadClearCaseHTML();
			  if(clearCaseContent!=null) {
				  Map<String , Double> CC_CPUStatusMap = Utility.getCPUMap (clearCaseContent ,Constants.SUB_TOWER_CLEAR_CASE);
				  if(CC_CPUStatusMap.size()>0)
				  SendInfraEmail.sendEmail(prop,CC_CPUStatusMap , Constants.SUB_TOWER_CLEAR_CASE ,entityMap);
			  }			  
			  Map<String ,List<String>> polPlasticJiraContent = loadPolarionPlasticJiraHTML();
			  if(polPlasticJiraContent!=null) {
				  Map<String , Double> POL_CPUStatusMap = Utility.getCPUMap (polPlasticJiraContent ,0);
				 // LOGGER.info("POL_CPUStatusMap " + POL_CPUStatusMap.size());
				  if(POL_CPUStatusMap.size()>0)
				  SendInfraEmail.sendEmail(prop,POL_CPUStatusMap,Constants.SUB_TOWER_POLARION,entityMap);
			  }			  
			  Map<String ,List<String>> gesContent = loadGesHTML();
			  if(gesContent!=null) {
				  Map<String , Double> GES_CPUStatusMap = Utility.getCPUMap (gesContent ,0);
				  //LOGGER.info("GES_CPUStatusMap " + GES_CPUStatusMap.size());
				  if(GES_CPUStatusMap.size()>0)
				  SendInfraEmail.sendEmail(prop,GES_CPUStatusMap , Constants.SUB_TOWER_GES ,entityMap);
			  }	
			  LOGGER.info("***** Infra Scan Completed *****" );
		  }				   
		  Constants.INFRA_INITIAL_COUNT++;
		  

	}
	
	
	public   Map<String ,List<String>> loadTeamCenterHTML (){
		String fileName = prop.getProperty("local.tcpath") +  prop.getProperty("html.filename");
		 Map<String ,List<String>> htmldata = Utility.loadHtmlContent(fileName );
		return htmldata;
	}
	
	public   Map<String ,List<String>> loadClearCaseHTML (){
		String fileName = prop.getProperty("local.ccpath") +  prop.getProperty("html.filename");
		Map<String ,List<String>> htmldata = Utility.loadHtmlContent(fileName );
		return htmldata;
	}
	
	public   Map<String ,List<String>> loadPolarionPlasticJiraHTML (){
		String fileName = prop.getProperty("local.polpath") +  prop.getProperty("pol.filename");
		Map<String ,List<String>> htmldata = Utility.loadTextContent(fileName);
		return htmldata;
	}
	
	public   Map<String ,List<String>> loadGesHTML (){
		String fileName = prop.getProperty("local.polpath") +  prop.getProperty("ges.filename");
		Map<String ,List<String>> htmldata = Utility.loadTextContent(fileName);
		return htmldata;
	}
}
