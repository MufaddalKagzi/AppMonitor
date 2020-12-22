package com.tcs.appmonitor.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.tcs.appmonitor.entities.Master;

public class SendEmail {
	static final Logger LOGGER = Logger.getLogger(SendEmail.class);
	static SimpleDateFormat sdfwithTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public static void sendHtmlEmail(Properties prop , List<Master> mailList,int tower,Map<Integer , String> entityMap) {
		
		String mailServer = prop.getProperty("mail.server");
		String fromAddress = prop.getProperty("email.fromaddress");
		String emailFlag = prop.getProperty("send.email");
		
		String toaddress = "";
		String subject = "";
		
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", mailServer);
		StringBuffer sb = new StringBuffer();
		
		if(tower == Constants.SUB_TOWER_GES ) {
		  toaddress  = prop.getProperty("ges.groupemail");
		  subject = entityMap.get(tower) + " : App Monitor Status ";	  
		  
		  sb.append( "Team, <br>" ) ;
		  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br>" ) ;
		  for(Master master : mailList) {
			 
			  sb.append("Application Name : " + master.getAppName());
			  sb.append("<br>");
			  sb.append("Application URL  : " + master.getAppUrl());
			  sb.append("<br>");
			  //sb.append("Last Scan 		  :	" + sdfwithTime.format(master.getScanDate()) +" (EST) ");
			  sb.append("<br>");
			  sb.append("-------------------------------------------------------------------------");
			  sb.append("<br>");
		  
		  }		 
		}else if (tower == Constants.SUB_TOWER_CLEAR_CASE) {
			toaddress  = prop.getProperty("clearcase.groupemail");
			subject = entityMap.get(tower) + " : App Monitor Status ";
			  
			  sb.append( "Team, <br>" ) ;
			  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br><br><br>" ) ;
			  for(Master master : mailList) {
				 
				  sb.append("Server Name  : " + master.getServerName());
				  sb.append("<br>");
				  //sb.append("Last Scan    : " + sdfwithTime.format(master.getScanDate())+" (EST) ");
				  sb.append("<br>");
				  sb.append("--------------------------------------------------------------------");
				  sb.append("<br>");
				  
			  }	
			
		}else if (tower == Constants.SUB_TOWER_TEAMCENTER) {
			
			toaddress  = prop.getProperty("teamcenter.groupemail");
			subject = entityMap.get(tower) + " : App Monitor Status ";
			  
			  sb.append( "Team, <br>" ) ;
			  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br><br><br>" ) ;
			  for(Master master : mailList) {
				  
				  sb.append("Site Name 	  : " + master.getSiteName() );
				  sb.append("<br>");
				  sb.append("Server Name  : " + master.getServerName());
				  sb.append("<br>");
				  //sb.append("Last Scan    : " + sdfwithTime.format(master.getScanDate()) +" (EST) ");
				  sb.append("<br>");
				  sb.append("---------------------------------------------------------------------");  
				  sb.append("<br>");
			  }	
			
		}else if (tower == Constants.SUB_TOWER_POLARION) {
			
			toaddress  = prop.getProperty("polarion.groupemail");
			subject = entityMap.get(tower) + " : App Monitor Status ";
			  
			  sb.append( "Team, <br>" ) ;
			  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br><br><br>" ) ;
			  for(Master master : mailList) {
			
				  sb.append("Site Name    : " + master.getSiteName() );
				  sb.append("<br>");
				  //sb.append("Last Scan    : " + sdfwithTime.format(master.getScanDate())+" (EST) ");
				  sb.append("<br>");
				  sb.append("--------------------------------------------------------------------");
				  sb.append("<br>");    
			  }	
			
		}else if (tower == Constants.SUB_TOWER_PLASTIC) {
			toaddress  = prop.getProperty("plastic.groupemail");
			subject = entityMap.get(tower) + " : App Monitor Status ";
			  
			  sb.append( "Team, <br>" ) ;
			  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br><br><br>" ) ;
			  for(Master master : mailList) {
				
				  sb.append("Server Name  : " + master.getServerName() );
				  sb.append("<br>");
				 // sb.append("Last Scan    : " + sdfwithTime.format(master.getScanDate())+" (EST) ");
				  sb.append("<br>");
				  sb.append("--------------------------------------------------------------------");
				  sb.append("<br>");    
			  }	
			
		}else if (tower == Constants.SUB_TOWER_DOORS) {
			
			toaddress  = prop.getProperty("doors.groupemail");
			subject = entityMap.get(tower) + " : App Monitor Status ";
			  
			  sb.append( "Team, <br>" ) ;
			  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br><br><br>" ) ;
			  for(Master master : mailList) {
				
				  sb.append("Server Name  	 : " + master.getServerName() );
				  sb.append("<br>");
				  sb.append("Application URL : " + master.getAppUrl() );
				  sb.append("<br>");
				  //sb.append("Last Scan    	 : " + sdfwithTime.format(master.getScanDate())+" (EST) ");
				  sb.append("<br>");
				  sb.append("-----------------------------------------------------------------------");
				  sb.append("<br>");    
			  }	
			
		}else if (tower == Constants.SUB_TOWER_CM_SYNERGY) {
			
			toaddress  = prop.getProperty("synergy.groupemail");
			subject = entityMap.get(tower) + " : App Monitor Status ";
			  
			  sb.append( "Team, <br>" ) ;
			  sb.append( "Below list of Application(s) is Down from your Tower :  <br><br><br><br>" ) ;
			  for(Master master : mailList) {
			
				  sb.append("Server Name  	 : " + master.getServerName() );
				  sb.append("<br>");
				  sb.append("Application URL : " + master.getAppUrl() );
				  sb.append("<br>");
				//  sb.append("Last Scan       : " + sdfwithTime.format(master.getScanDate())+" (EST) ");
				  sb.append("<br>");
				  sb.append("-----------------------------------------------------------------------");
				  sb.append("<br>");    
			  }	
		}
		sb.append("<br><br>");	
		sb.append("**************************************************************************************");	
		sb.append("<br><br>");	
		sb.append("DO NOT REPLY") ;
		Session session = Session.getDefaultInstance(props);
		
		try {
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromAddress));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));
			msg.setSubject(subject);
			msg.setContent(sb.toString(), "text/html");
			//System.out.println("Mail Content : " + sb.toString());
			if(emailFlag.equalsIgnoreCase("Y")) {
				Transport.send(msg);	  
				LOGGER.warn( "Email Sent Successfully : " + subject  + " - " + toaddress);
			}else {
				LOGGER.error( "Email not Sent  : " + subject  + " - " + toaddress);
			}

      } catch (Exception e) {
    	  LOGGER.error( "Email Exception  : " + e.getMessage()); 	  
      }
		
	}

}
