package com.unis.utils;

public enum ERROR_CODE {
	LOGIN(0,"请登录"),
    SUCCESS(1,"Success"),
    MAX_OUT_VALUE_ERROR(1, "提现审核中"),
    NO_AUTH(2,"没有权限"),
    DB_ERROR(2002, "数据库操作失败"),
    ACCOUNT_ERROR(2003, "账户不存在"),
    AMOUNT_LOCK_ERROR(2004, "余额不足"),
    REQ_ERROR(2011, "请求错误,系统未接收到参数"),
    PARAM_ERROR(2012, "参数错误"),
    SYS_ERROR(2013,"系统错误"),
    TO_OUR_ERROR(2014, "充值失败"),
    TO_USER_ERROR(2015, "提现失败"),
    MIN_IN_VALUE_ERROR(2016, "充值额度不能小于"),
    MIN_OUT_VALUE_ERROR(2016, "提现额度不能小于"),
    TRANS_ERROR(2017, "交易不存在"),
    USED_EX_ERROR(2018, "已提现"),
    ACCOUNT_FREEZE(2019, "账户异常"),
    CONTENT_WITH_ADMIN(2020,"请联系管理员");
	private String msg;
	private int code;
	ERROR_CODE(int code,String message) {
		this.code = code;
		this.msg = message;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
}
