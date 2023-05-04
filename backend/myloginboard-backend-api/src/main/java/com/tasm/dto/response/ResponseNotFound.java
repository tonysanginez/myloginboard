package com.tasm.dto.response;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;

public class ResponseNotFound implements Serializable {

	private static final long serialVersionUID = 1L;

	private int code;
	private boolean success;
	private String message;
	private Object data;
	
	public static String messageWebsiteNotFound = "Not found website";
	
	public ResponseNotFound(String message, Object data) {
		super();
		this.code = Status.NOT_FOUND.getStatusCode();
		this.success = true;
		this.message = message;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
