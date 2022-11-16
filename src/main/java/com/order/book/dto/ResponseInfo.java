package com.order.book.dto;

public class ResponseInfo {
	private String status;
	private String message;
	private Object payload;
	
	public ResponseInfo() {
		super();
	}

	public ResponseInfo(String status, String message, Object payload) {
		super();
		this.status = status;
		this.message = message;
		this.payload = payload;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
}
