package com.shixzh.bcms.po;

public class UserPO {

	private Long userId;
	private String userName;
	private Integer userAge;
	private Integer userAgeStart;
	private Integer userAgeEnd;

	public UserPO() {
	}

	public UserPO(String userName) {
		this.userName = userName;
	}

	public UserPO(Long userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}

	public UserPO(String userName, Integer userAge) {
		this.userName = userName;
		this.userAge = userAge;
	}

	public UserPO(String userName, Integer userAgeStart, Integer userAgeEnd) {
		this.userName = userName;
		this.userAgeStart = userAgeStart;
		this.userAgeEnd = userAgeEnd;
	}

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

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Integer getUserAgeStart() {
		return userAgeStart;
	}

	public void setUserAgeStart(Integer userAgeStart) {
		this.userAgeStart = userAgeStart;
	}

	public Integer getUserAgeEnd() {
		return userAgeEnd;
	}

	public void setUserAgeEnd(Integer userAgeEnd) {
		this.userAgeEnd = userAgeEnd;
	}

	@Override
	public String toString() {
		return "UserPO [userId=" + userId + ", userName=" + userName + ", userAge=" + userAge + ", userAgeStart="
				+ userAgeStart + ", userAgeEnd=" + userAgeEnd + "]";
	}

}
