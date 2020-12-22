package com.tcs.appmonitor.entities;

import java.io.Serializable;
import java.util.Date;

public class Dashboard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private  Date scanDate;
	private  int  scanCount;
	private  int  scanDownCount;
	private  int  scanUpCount;
	
	public Date getScanDate() {
		return scanDate;
	}
	public void setScanDate(Date scanDate) {
		this.scanDate = scanDate;
	}
	public int getScanCount() {
		return scanCount;
	}
	public void setScanCount(int scanCount) {
		this.scanCount = scanCount;
	}
	public int getScanDownCount() {
		return scanDownCount;
	}
	public void setScanDownCount(int scanDownCount) {
		this.scanDownCount = scanDownCount;
	}
	public int getScanUpCount() {
		return scanUpCount;
	}
	public void setScanUpCount(int scanUpCount) {
		this.scanUpCount = scanUpCount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
	
	
	
	
	


}
