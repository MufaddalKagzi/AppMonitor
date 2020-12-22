package com.tcs.appmonitor.entities;

import java.io.Serializable;

public class Entity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int entityId;
	private String entityType;
	private String entityName;
	private String isActive;
	private int entityValue;
	private int parentEntity;
	
	public int getEntityId() {
		return entityId;
	}
	public void setEntityId(int entityId) {
		this.entityId = entityId;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public int getEntityValue() {
		return entityValue;
	}
	public void setEntityValue(int entityValue) {
		this.entityValue = entityValue;
	}
	public int getParentEntity() {
		return parentEntity;
	}
	public void setParentEntity(int parentEntity) {
		this.parentEntity = parentEntity;
	}
	
	
}
