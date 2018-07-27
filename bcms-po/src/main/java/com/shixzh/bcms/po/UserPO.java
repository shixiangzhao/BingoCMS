package com.shixzh.bcms.po;

import java.util.List;

public class UserPO {

	private Long userId;
	private String userName;
	private Integer userAge;
	private boolean isPaidMember;
	private String userMobile;
	private String userPassword;
	private List<UserGroupPO> groupList; // 权限组

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

	public List<UserGroupPO> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<UserGroupPO> groupList) {
		this.groupList = groupList;
	}

	public boolean getIsPaidMember() {
		return isPaidMember;
	}

	public void setIsPaidMember(boolean paidMember) {
		isPaidMember = paidMember;
	}

	@Override
	public String toString() {
		return "UserPO{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userAge=" + userAge +
				", isPaidMember=" + isPaidMember +
				", userMobile='" + userMobile + '\'' +
				", userPassword='" + userPassword + '\'' +
				", groupList=" + groupList +
				", userAgeStart=" + userAgeStart +
				", userAgeEnd=" + userAgeEnd +
				'}';
	}


}
