package com.shixzh.bcms.po;

public class UserPO {

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