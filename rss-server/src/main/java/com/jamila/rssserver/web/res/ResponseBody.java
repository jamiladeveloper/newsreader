package com.jamila.rssserver.web.res;

public class ResponseBody<T> {
	String code;
	String message;
	T body;
	
	public ResponseBody(String code, String message, T body) {
		this.code = code;
		this.message = message;
		this.body = body;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
	
	
}
