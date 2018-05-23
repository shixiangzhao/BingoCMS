package com.shixzh.bcms.dao;

import java.util.List;

import com.shixzh.bcms.po.UserPO;

public interface UserDao {

	UserPO selectByPrimaryKey(Long userId);

	List<UserPO> listUser(UserPO userPO);

	Integer addUser(UserPO userPO);

	Integer updateUser(UserPO userPO);

	Integer deleteUser(Long userId);

	UserPO selectByMobile(String userMobile);

}
