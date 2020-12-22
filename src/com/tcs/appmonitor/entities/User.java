package com.tcs.appmonitor.entities;

public class User {

	private String netId;
	private String userName;
	private String emailId;
	private int userRole;
	private int userTower;
	
	
	public User() {}
	public User(String netId,String userName,String emailId,Integer userRole,Integer userTower) {
	 this.netId = netId;
	 this.userName  = userName;
	 this.emailId = emailId;
	 this.userRole  = userRole;
	 this.userTower =userTower;
	}
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getUserRole() {
		return userRole;
	}
	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}
	public int getUserTower() {
		return userTower;
	}
	public void setUserTower(int userTower) {
		this.userTower = userTower;
	}
	
}
