package com.jamila.rssserver.web.res;

public class ConfigurationDTO {
	public String id;
	public String refreshInterval;
	public String timestamp;
	public ConfigurationDTO(String id, String refreshInterval, String timestamp) {
		super();
		this.id = id;
		this.refreshInterval = refreshInterval;
		this.timestamp = timestamp;
	}
	
	
}
