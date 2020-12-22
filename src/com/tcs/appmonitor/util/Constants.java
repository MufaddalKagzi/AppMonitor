package com.tcs.appmonitor.util;

public class Constants {

	
	public static int ADMIN_ROLE = 303;
	public static int USER_ROLE = 302;
	public static int GUEST_ROLE =301;
	
	
	public static int TOWER_ENG = 101;
	
	public static int SUB_TOWER_CLEAR_CASE  = 201;
	public static int SUB_TOWER_CM_SYNERGY  = 202;
	public static int SUB_TOWER_DOORS  = 203;
	public static int SUB_TOWER_GES  = 204;
	public static int SUB_TOWER_PLASTIC  = 205;
	public static int SUB_TOWER_POLARION  = 206;	
	public static int SUB_TOWER_TEAMCENTER  = 207;
	public static int SUB_TOWER_JIRA  = 208;	
	
	public static String STATUS_UP = "U";
	public static String STATUS_DOWN = "D";
	
	public static String TEAMCENTER_STS_TEXT = "successfully";
	public static String CLEARCASE_STS_TEXT = "GREEN";
	
	public static String POLARION_STS_TEXT = "running fine";
	public static String POLARION_FILE_INITIALS = "Pol";
	
	public static String PLASTIC_FILE_NAME = "AlertLogPlastic_TCS";	
	public static String PLASTIC_STS_TEXT = "UP";
	
	public static String JIRA_FILE_NAME = "Jira";	
	public static String JIRA_STS_TEXT = "Jira working";

	public static String CLEARCASE_FILE_INITIALS = "ClearCaseServerHealthCheckSummary_";
	
	public static String NULL = null;
	public static long BEFORE_TIME = System.currentTimeMillis() - 6 * 60 * 1000;
	
	public static long SCHEDULAR_INTERVAL = 5;  // Scan runs every 5 mins , it should be in multiple of 5
	
	public static long INFRA_MAIL_INTERVAL = (60 / SCHEDULAR_INTERVAL)*12 ; // Once in a Day.  ( 60 mins / 5 mins ) * hours in a day
	public static long CPU_UTILIZATION = 90;	
	public static long INFRA_INITIAL_COUNT = 1;
	
}
