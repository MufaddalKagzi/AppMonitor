package com.tcs.appmonitor.entities;

import java.io.Serializable;
import java.util.Date;

public class Master implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int towerId;
	private int subTowerId;
	private String appName;  // Application Name
	private String siteName;  // Site Name
	private String serverName;  // Server Name
	private String serverLocation;  // Server Locations
	private String appUrl;  // App URL
	private String authentication;  // Application Login authentication 
	private String abbreviaion;  // Abbreviations
	private String isActive;  // Status

	private Date createdDate; // scan date
	private String createdBy;
	private Date modifiedDate;
	private String modifiedBy;
	
	private String status;
	private Date scanDate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTowerId() {
		return towerId;
	}
	public void setTowerId(int towerId) {
		this.towerId = towerId;
	}
	public int getSubTowerId() {
		return subTowerId;
	}
	public void setSubTowerId(int subTowerId) {
		this.subTowerId = subTowerId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getServerLocation() {
		return serverLocation;
	}
	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}
	public String getAppUrl() {
		return appUrl;
	}
	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
	public String getAuthentication() {
		return authentication;
	}
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}
	public String getAbbreviaion() {
		return abbreviaion;
	}
	public void setAbbreviaion(String abbreviaion) {
		this.abbreviaion = abbreviaion;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}
	
		
	
	
}
