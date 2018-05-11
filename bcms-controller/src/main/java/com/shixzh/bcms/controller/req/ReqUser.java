package com.shixzh.bcms.controller.req;

public class ReqUser {

	private Long userId;
	private String userName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserPO [userId=" + userId + ", userName=" + userName + "]";
	}

}
