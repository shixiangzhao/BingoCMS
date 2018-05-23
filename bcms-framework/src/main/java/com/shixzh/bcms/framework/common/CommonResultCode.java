package com.shixzh.bcms.framework.common;

public enum CommonResultCode {
	SUCCESS(2000, "success"), 
	SERVER_ERROR(5000, "服务器内部错误"), 
	PARAM_ERROR(5001, "参数错误"), 
	CACHE_ERROR(5002, "缓存错误"), 
	SMS_ERROR(5003, "验证码错误"),
	TOKEN_ERROR(5004, "无效token"),
	FILE_TYPE_ERROR(5005, "文件类型错误"),
	SMS_SEND_ERROR(5006, "短信发送失败"),
	FILE_DOWNLOAD_ERROR(5007, "文件未找到"),
	PRIVILEGE_ERROR(5008, "没有该权限");

	private Integer code;
	private String msg;

	private CommonResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
