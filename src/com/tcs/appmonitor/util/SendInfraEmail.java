package com.tcs.appmonitor.util;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class SendInfraEmail {
	static final Logger LOGGER = Logger.getLogger(SendEmail.class);
	static SimpleDateFormat sdfwithTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	
	public static void sendEmail(Properties prop ,  Map<String , Double> infraMap , int tower , Map<Integer , String> entityMap) {
		
		String mailServer = prop.getProperty("mail.server");
		String fromAddress = prop.getProperty("email.fromaddress");
		String emailFlag = prop.getProperty("send.email");
		
		String toaddress = "";
		String subject = "";
		
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", mailServer);
		StringBuffer sb = new StringBuffer();
		
		if(tower == Constants.SUB_TOWER_TEAMCENTER  ||  tower == Constants.SUB_TOWER_CLEAR_CASE ) {
		  toaddress  = Constants.SUB_TOWER_TEAMCENTER == tower ?  prop.getProperty("teamcenter.groupemail") : prop.getProperty("clearcase.groupemail");
		  subject = entityMap.get(tower) + " : Infra Monitoring Status ";	  
	    }else if(tower == Constants.SUB_TOWER_POLARION ) { 
	    	toaddress  = prop.getProperty("polarion.groupemail");
			subject =  "Polarion/Plastic Scm/CM Synergy/JIRA/Doors : Infra Monitoring Status ";	
	    }else if(tower == Constants.SUB_TOWER_GES ) { 
	    	toaddress  = prop.getProperty("ges.groupemail");
			subject = entityMap.get(tower) + " : Infra Monitoring Status ";	
	    }
	    	
	    
	      sb.append( "Team, <br>" ) ;
		  sb.append( "Below list of Servers(s) need attention from your Tower :  <br><br>" ) ;
		  for(String serverName : infraMap.keySet()) {
						  
			  sb.append("Server Name : " + serverName );
			  sb.append("<br>");
			  sb.append("CPU Usage  : " + infraMap.get(serverName));			
			  sb.append("<br>");
			  sb.append("-------------------------------------------------------------------------");
			  sb.append("<br>");
		  
		  }		 
		
		sb.append("<br><br>");	
			  sb.append("*************************************************************************");	
		sb.append("<br><br>");	
		sb.append("DO NOT REPLY") ;
		Session session = Session.getDefaultInstance(props);
		
		try {
			
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(fromAddress));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toaddress));
			if(tower == Constants.SUB_TOWER_POLARION )	{
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(prop.getProperty("plastic.groupemail")) );
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(prop.getProperty("doors.groupemail")) );
				msg.addRecipient(Message.RecipientType.CC, new InternetAddress(prop.getProperty("synergy.groupemail")) );
			}
			msg.setSubject(subject);
			msg.setContent(sb.toString(), "text/html");
			//LOGGER.warn("Mail Content : " + sb.toString());
			if(emailFlag.equalsIgnoreCase("Y")) {
				Transport.send(msg);	  
				LOGGER.warn( "Infra Email Sent Successfully : " + subject  + " - " + toaddress);
			}else {
				LOGGER.error( "Email not Sent  : " + subject  + " - " + toaddress);
			}

      } catch (Exception e) {
    	  LOGGER.error( "Email Exception  : " + e.getMessage()); 	  
      }
		
	}

}
