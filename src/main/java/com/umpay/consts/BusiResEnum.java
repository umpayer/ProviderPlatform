package com.umpay.consts;

public enum BusiResEnum {
	SUCCESS("0000", "处理成功"),
	ERROR("9999","当前环节出现异常");
	
	
	private String retCode;
	private String retMsg;

	BusiResEnum(String retCode, String retMsg) {
		this.retCode = retCode;
		this.retMsg = retMsg;
	}

	public String getRetCode() {
		return retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}
}
