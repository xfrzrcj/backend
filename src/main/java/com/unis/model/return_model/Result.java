package com.unis.model.return_model;

import com.unis.utils.ERROR_CODE;

public class Result {
	private Integer code;
	private String message;
	private Object data;
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
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
	
	public void setCode(ERROR_CODE code) {
		this.code = code.getCode();
		this.message = code.getMsg();
	}
}
