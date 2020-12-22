package com.tcs.appmonitor.entities;

import java.io.Serializable;

public class TimeLine  implements Serializable , Comparable<TimeLine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int lineNumber;
	private String logDate;
	private String logStr;
	
	
	public TimeLine(int lineNumber, String logDate, String logStr) {
        this.lineNumber = lineNumber;
        this.logDate = logDate;
        this.logStr = logStr;
       
    }
	public TimeLine() {}
	
	public String getLogDate() {
		return logDate;
	}
	public void setLogDate(String logDate) {
		this.logDate = logDate;
	}
	public String getLogStr() {
		return logStr;
	}
	public void setLogStr(String logStr) {
		this.logStr = logStr;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	 @Override
	    public int compareTo(TimeLine o) {
	        return o.lineNumber - this.lineNumber;
	    }
}
