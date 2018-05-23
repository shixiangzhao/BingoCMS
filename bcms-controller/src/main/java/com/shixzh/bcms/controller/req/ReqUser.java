package com.shixzh.bcms.controller.req;

public class ReqUser {

	private Long userId;
	private String userName;
	private String userMobile;
	private String userPassword;

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

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "ReqUser [userId=" + userId + ", userName=" + userName + ", userMobile=" + userMobile + ", userPassword="
				+ userPassword + "]";
	}

}
