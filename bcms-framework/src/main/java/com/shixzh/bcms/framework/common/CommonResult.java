package com.shixzh.bcms.framework.common;

import java.io.Serializable;

/**
 * Created by shixzh on 2018/5/23. API接口返回通用格式数据
 */
public class CommonResult<T> implements Serializable {

	private static final long serialVersionUID = 3414781618939246001L;

	private Integer code;
	private String msg;
	private T data;

	public CommonResult() {

	}

	public void setResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public void setResult(Integer code, String msg, T data) {
		this.code = code;
		this.data = data;
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

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
