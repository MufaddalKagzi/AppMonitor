package com.tcs.appmonitor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

public class Utility {

	static final Logger LOGGER = Logger.getLogger(Utility.class);
	
	static SimpleDateFormat sdfwithTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public static boolean DateCompare(String date) {
		boolean sts = false;
		try { 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 
			Date date1 = sdf.parse(date);
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			Date date2 = sdf.parse(modifiedDate);
			
			if(date1.compareTo(date2) == 0)
				sts =true;		
		} catch (ParseException e) {
			LOGGER.error("Parsing Date Error :" + e.getMessage());	
		}
		return sts;
	}
	
	public static Date stringToDate (String date) {
		Date parseDate = null;
		try { 
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			parseDate = sdf.parse(date);
			
		} catch (ParseException e) {
			LOGGER.error("Parsing Date Error :" + e.getMessage());	
		}
		return parseDate;
	}
	
	public static String datetoString (String inputdate) {
		 String outputDate = null;
		try { 
		    DateFormat inputFormat = new SimpleDateFormat("E MMM dd yyyy HH:mm:ss", Locale.ENGLISH);
		    Date date = inputFormat.parse(inputdate);
		    DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		    outputDate = outputFormat.format(date);
		} catch (ParseException e) {
			LOGGER.error("Parsing Date Error :" + e.getMessage());	
		}
		return outputDate;
	}
	public static boolean checkApplicationURL(String appURL , String appName , String authRequire, final String userName , final String password) {
		try {					
			final URL url = new URL(appURL);
			
			if(authRequire.equalsIgnoreCase("Y")) {
			Authenticator.setDefault(new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userName, password.toCharArray());
				}
			});
			}
			HttpURLConnection huc = (HttpURLConnection) url.openConnection();
			int responseCode = huc.getResponseCode();

			if (responseCode == 200) {
				LOGGER.info(" Successfully connected to URL " + appURL + " for " + appName );
				return true;
			} else {
				LOGGER.warn(" Could not connect URL " + appURL + " for " + appName + "[Error code : " + responseCode +"]" );
				return false;
			}			
		}catch(ConnectException ex) {
			LOGGER.warn( "Could not connect URL " +  appURL + " for " + appName + "[Exception : " + ex.getMessage() +"]" );
			return false;
		}
		catch(Exception ex) {
			LOGGER.warn( "Could not connect URL " +  appURL + " for " + appName + "[Exception : " + ex.getMessage() +"]" );
			return false;
		}			
	}
	
	public static Map<String, String> readTeamCenterLogs( String remotePath , String localPath ,String userName , String password , String fileName ) {
		
		//boolean status = connectRemoteMachine(remotePath,localPath,userName,password,fileName);
		boolean status = true;
		File file  = new File(localPath+fileName);
		if (file.lastModified() < Constants.BEFORE_TIME)
		{
			LOGGER.warn("Latest TeamCenter file is not available ,Last File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified() ));
			status = false;
		}		
		Map<String, String> tcMap = new HashMap<String, String>();
		if(status) {		
		try {
			
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				String arr[]=new String[2];
				int i=0;
				for (String retval: strLine.split("\\:")) {
					if(retval.equals("Info")) continue;
					for (String val: retval.split("\\-")) {
							arr[i] = val;
							i++;
					}
				}				
				
				 if( arr.length > 0 )
				 { 
					 
					 String sts = "";
					 if(arr[1].contains(Constants.TEAMCENTER_STS_TEXT))
						 sts = Constants.STATUS_UP;
					 else 
						 sts = Constants.STATUS_DOWN; 
					 
					 tcMap.put(arr[0].trim() , sts ); 
				 }
				 
			}
			
			br.close();
			LOGGER.info("TeamCenter Map loaded successfully ");
		} catch (FileNotFoundException ex) {
			LOGGER.warn("TeamCenter Log file not found " + ex.getMessage());	
		}catch (IOException ex) {
			LOGGER.warn(" IOException while reading TeamCenter Log file " + ex.getMessage());	
		}
		}
		
		return tcMap;
		
	}
	
	public static boolean  connectRemoteMachine (String remotePath , String localPath ,String userName , String password , String fileName) {
		SmbFile file = null;
		boolean status =false;
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(null, userName, password);
		try {
			file = new SmbFile(remotePath + fileName , auth);
			
			if (file.lastModified() < Constants.BEFORE_TIME) {
				copyFile(file ,new File(localPath + fileName));
				status =true;
			}else {
				LOGGER.warn("Latest TeamCenter log file is not available, Last Log File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified()));
			}
			
		} catch (MalformedURLException e) {
			LOGGER.warn( " MalformedURLException while connecting TC remote machine : " + e.getMessage() );
		}catch (Exception ex) {
			LOGGER.warn( " Exception while connecting TC remote machine : " + ex.getMessage() );
		}
		return status;
	}
	
	private static void copyFile(SmbFile srcfile, File destfile) throws IOException {
		SmbFileInputStream is = null;
        OutputStream os = null;
        try {
            is = new SmbFileInputStream(srcfile);
            os = new FileOutputStream(destfile);

            // buffer size 1K
            byte[] buf = new byte[1024];

            int bytesRead;
            while ((bytesRead = is.read(buf)) > 0) {
                os.write(buf, 0, bytesRead);
            }
            LOGGER.info( "  File Copied Successfully. " );
        } catch (Exception ex) {
			LOGGER.error( " Exception while Copying file : " + ex.getMessage() );
		}	finally {
            is.close();
            os.close();
        }
    }
	
	public static  String readClearCaseLogs(String localccPath, String serverName ) {
		File file = new File(localccPath+Constants.CLEARCASE_FILE_INITIALS+serverName+".txt");
		String sts=null;
		if (file.lastModified() < Constants.BEFORE_TIME)
		{
			LOGGER.warn("Latest ClearCase log file is not available ,Last Log File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified() ));
			return sts; 
		}
		try {
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			
			while ((strLine = br.readLine()) != null) {
				 if(strLine.contains(Constants.CLEARCASE_STS_TEXT))
						 sts = Constants.STATUS_UP;
					 else 
						 sts = Constants.STATUS_DOWN; 
			}			
			br.close();
			LOGGER.info("ClearCase file " +file.getName() + " read successfully ");
		} catch (FileNotFoundException ex) {
			LOGGER.warn("ClearCase Log file '" + file.getName()  +"' not found " + ex.getMessage());	
		}catch (IOException ex) {
			LOGGER.warn(" IOException while reading ClearCase Log file " + ex.getMessage());	
		}	
		return sts;
	}
	
	public static java.sql.Timestamp  getCurrentTimeStamp () {
		
		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());
	}
	
	public static void  connectUnixMachine (String hostName , String remotePath , String localPath ,String userName , String password ) {
		 JSch jsch = new JSch();
	     Session session = null;
	     SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");	 	
	     try {
			    session = jsch.getSession(userName, hostName,22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setConfig(
	            	    "PreferredAuthentications", 
	            	    "publickey,keyboard-interactive,password");
	            session.setPassword(password);
	            session.connect(); 
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel; 
	            
	            sftpChannel.cd(remotePath);
	            Vector filelist = sftpChannel.ls(remotePath);
	            
	            for(int i=0; i<filelist.size();i++){
	            	LsEntry entry = (LsEntry) filelist.get(i);
	            	 
	            	String fileName = entry.getFilename();
	            	if(fileName.contains(".log")) {
		            	sftpChannel.get(remotePath + fileName, localPath + fileName);
		            	SftpATTRS attrs = sftpChannel.lstat(remotePath + fileName);
		            	
		            	Date modDate = (Date) format.parse(attrs.getMtimeString());
		            	File downloadedFile = new File(localPath + fileName);
		                downloadedFile.setLastModified(modDate.getTime());
	            	}
	            }
	            
	            sftpChannel.exit();
	            session.disconnect();
	            
		} catch (JSchException e) {
			LOGGER.warn( " JSchException while connecting Polarion Unix machine : " + e.getMessage() );
		}catch (SftpException ex) {
			LOGGER.warn( " SftpException while connecting Polarion Unix machine : " + ex.getMessage() );
		}catch (ParseException ex) {
			LOGGER.warn( "ParseException while connecting Polarion Unix machine : " + ex.getMessage() );
		}	
	}
	
public static Map<String, String> readPolarionPlasticLogs(String hostName, String remotePath , String localPath ,String userName , String password  ) {
		
		connectUnixMachine(hostName, remotePath,localPath,userName,password);
		
		Map<String, String> polMap = new HashMap<String, String>();
	
		try {
			File folder = new File(localPath);
			File[] files = folder.listFiles();
			for(File file: files){
				String fileName = file.getName();
				String sts = "";
				if(fileName.startsWith(Constants.POLARION_FILE_INITIALS) && file.lastModified() > Constants.BEFORE_TIME) {
					
					String nodeName = fileName.substring(4, fileName.indexOf("."));
					
					FileInputStream fstream = new FileInputStream(file);
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
					String strLine;
				
					while ((strLine = br.readLine()) != null) {
					
						 if(strLine.contains(Constants.POLARION_STS_TEXT))
							 sts = Constants.STATUS_UP;
						 else 
							 sts = Constants.STATUS_DOWN; 
					}	
					polMap.put(nodeName, sts);
					br.close();	
					fstream.close();
				}else if (fileName.startsWith(Constants.PLASTIC_FILE_NAME) && file.lastModified() > Constants.BEFORE_TIME) {
					
					FileInputStream fstream = new FileInputStream(file);
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
					String strLine;
				
					while ((strLine = br.readLine()) != null) {
					
						 if(strLine.contains(Constants.PLASTIC_STS_TEXT))
							 sts = Constants.STATUS_UP;
						 else 
							 sts = Constants.STATUS_DOWN; 
					}	
					polMap.put("PlasticSCM", sts);
					br.close();	
					fstream.close();					
				}else if (fileName.startsWith(Constants.JIRA_FILE_NAME) && file.lastModified() > Constants.BEFORE_TIME) {
					
					FileInputStream fstream = new FileInputStream(file);
					BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
					String strLine;
				
					while ((strLine = br.readLine()) != null) {
					
						 if(strLine.contains(Constants.JIRA_STS_TEXT))
							 sts = Constants.STATUS_UP;
						 else 
							 sts = Constants.STATUS_DOWN; 
					}	
					polMap.put("JIRA", sts);
					br.close();	
					fstream.close();					
				}else {
					if(!fileName.startsWith("INFRA"))
					{
						sts = Constants.STATUS_DOWN; 
					    LOGGER.warn("Latest log file is not available ,Last Log File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified() ));
					}
				}
				
			}			
			
			LOGGER.info("Polarion Map loaded successfully ");
		} catch (FileNotFoundException ex) {
			LOGGER.warn("Polarion Log file not found " + ex.getMessage());	
		}catch (IOException ex) {
			LOGGER.warn(" IOException while reading Polarion Log file " + ex.getMessage());	
		}catch (IndexOutOfBoundsException ex) {
			LOGGER.warn(" IndexOutOfBoundsException while reading Polarion Log file " + ex.getMessage());	
		}
		
		return polMap;
		
	}


  
  public static Map<String ,List<String>> loadHtmlContent (String fileName ){
 	Map<String ,List<String>>  rowMap = null;
 	File file = new File(fileName);
 	if(file.lastModified() > Constants.BEFORE_TIME) {
		FileInputStream fis =null;
		rowMap = new HashMap<String, List<String>>();
		try {
			 fis = new FileInputStream(fileName);
			 Document doc = Jsoup.parse(fis, "", "");
		     Elements tableElements = doc.select("table");    		
		     Elements tableRowElements = tableElements.select(":not(thead) tr");
		     
		     for (int i = 0; i < tableRowElements.size(); i++) {
		    	
		        Element row = tableRowElements.get(i);           
		        Elements rowItems = row.select("td");
		        
		        List<String> colString = new ArrayList<String>();
		        
			        for (int j = 0; j < rowItems.size(); j++) {
			        	colString.add(rowItems.get(j).text());
			        }
			        colString.remove(2);
			       
			     
			     rowMap.put(colString.get(0) ,colString);
		     }
		     
		} catch (IOException ex) {
			LOGGER.warn(" IOException while loadHtmlContent " + ex.getMessage());	
		}	
		return rowMap;
 	}else {
 		LOGGER.warn("Latest file is not available ,Last File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified() ));
 		return rowMap;
 	}
  }
  
  
  public static Map<String ,List<String>> loadTextContent_forfutureRef (String fileName){
	  Map<String ,List<String>>  rowMap = null;  
	  File file = new File(fileName);
	 	if(file.lastModified() > Constants.BEFORE_TIME) {
		  FileInputStream fstream =null;
		  rowMap = new HashMap<String, List<String>>();
		  BufferedReader br=null;
			try {
				 fstream = new FileInputStream(fileName);
				 br = new BufferedReader(new InputStreamReader(fstream));
					String strLine;
					
					while ((strLine = br.readLine()) != null) {
						if(strLine.equals("")) continue;
						String[] record = strLine.split("#x#");
						List<String> colString = new ArrayList<String>();
						for(String str : record) {
							colString.add(str);
						}
						rowMap.put(colString.get(0) ,colString);
					}
			} catch (IOException ex) {
				LOGGER.warn(" IOException while loadTextContent " + ex.getMessage());	
			}finally {
				try{br.close();}catch(Exception e) {LOGGER.error(" IOException while closing BufferReader " + e.getMessage());}
			}
			return rowMap;  
	 	}else {
	 		LOGGER.warn("Latest file is not available ,Last File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified() ));
	 		return rowMap;
	 	}	
		
  }
  public static Map<String ,List<String>> loadTextContent (String fileName){
	  Map<String ,List<String>>  rowMap = null;  
	  File file = new File(fileName);
	 	if(file.lastModified() > Constants.BEFORE_TIME) {
		  FileInputStream fstream =null;
		  rowMap = new HashMap<String, List<String>>();
		  List<String> colString =null;
		  BufferedReader br=null;
			try {
				 fstream = new FileInputStream(fileName);
				 br = new BufferedReader(new InputStreamReader(fstream));
					String strLine;
					String key=null;
					
					while ((strLine = br.readLine()) != null) {
						
						if(strLine.equals("")) continue;
						if(strLine.startsWith("IP"))
						{
						   colString = new ArrayList<String>();
						   key = 	   strLine;							
						}
						
						if(rowMap.containsKey(key))
							rowMap.get(key).add(strLine);
						else
							rowMap.put(key, colString);						
						
					}
			} catch (IOException ex) {
				LOGGER.warn(" IOException while loadTextContent " + ex.getMessage());	
			}finally {
				try{br.close();}catch(Exception e) {LOGGER.error(" IOException while closing BufferReader " + e.getMessage());}
			}
			return rowMap;  
	 	}else {
	 		LOGGER.warn("Latest file is not available ,Last File is " + file.getName() + " with timestamp " + sdfwithTime.format(file.lastModified() ));
	 		return rowMap;
	 	}	
		
  }
  
  
  public static  long getScanCount(String startDate, String endDate) {
	  long scanCount =-1;
	  DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	  try {
		long scanMinutes = 24*60;  // As it not include last day , hence added no of minutes in a day.
		Date sDate = outputFormat.parse(startDate);
		Date eDate = outputFormat.parse(endDate);		
		long difference = eDate.getTime() - sDate.getTime();
		long minutes = (difference / (1000*60));
		scanMinutes = scanMinutes+minutes;
		scanCount = scanMinutes / Constants.SCHEDULAR_INTERVAL ;
	} catch (ParseException e) {
		LOGGER.warn(" ParseException while getScanCount " + e.getMessage());	
	}	  
	  return scanCount;
  }  
  
  public static Map<String , Double>   getCPUMap (Map<String ,List<String>> map ,int tower) {
	  Map<String , Double> servecpu = new HashMap<String , Double>();
	  try {
		  for (String serverName : map.keySet()) {
			 
			  String cpuVal="";
			   if(Constants.SUB_TOWER_CLEAR_CASE == tower || Constants.SUB_TOWER_TEAMCENTER == tower ) {
			     cpuVal = map.get(serverName).get(1).trim();			    
			   }else  {
				   String[] record = serverName.split("#x#");			   
				   cpuVal = record[1].trim() ;
				   serverName =  record[0].trim() ;
			   }
		
			   double cpu=-1;
			   if(cpuVal.contains("CPU") || cpuVal.contains("Used MEMORY")) {
		        try {
				   cpu = Double.parseDouble(cpuVal.substring(cpuVal.indexOf(":")+1, cpuVal.indexOf("%")));
		        }catch(NumberFormatException e) {
			    	LOGGER.warn(" Exception while geting  CPU Value of  from tower " + tower +""+ e.getMessage());	
			    }
			   }
			
			   if(cpu > Constants.CPU_UTILIZATION)
			      servecpu.put(serverName, cpu);
			   LOGGER.info("["+tower+"---" + serverName + "---" + cpuVal+"]");		   
		   }
	   }catch (Exception ex) {
			LOGGER.warn(" Exception while geting  CPU Value of  from tower " + tower +""+ ex.getMessage());	
	   }	
	  return servecpu;
  }  
}
