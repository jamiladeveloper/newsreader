package com.jamila.rssserver.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("configurations")
public class Configuration {
	
	@Id
	private String id;
	
	private String refreshInterval;
	private String timestamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRefreshInterval() {
		return refreshInterval;
	}
	public void setRefreshInterval(String refreshInterval) {
		this.refreshInterval = refreshInterval;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
