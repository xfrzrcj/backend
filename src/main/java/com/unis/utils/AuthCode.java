package com.unis.utils;

public enum AuthCode {
	AUTH_ACCOUNT_LIST(1),
	AUTH_USER_INFO(2),
	AUTH_DAY_ACTIVITY_LIST(3),
	AUTH_GAME_LIST(4),
	AUTH_WITHDRAW_LIST(5),
	AUTH_TRANSACTION_OUT_LIST(6),
	AUTH_TRANSACTION_IN_LIST(7),
	AUTH_EDIT_APP(8),
	AUTH_APP_STATISTICS_LIST(9),
	AUTH_TOTAL_STATISTICS(10),
	AUTH_FREEZE_USER(100);
	private int code;
	AuthCode(int code) {
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
