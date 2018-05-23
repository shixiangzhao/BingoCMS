package com.shixzh.bcms.service;

import java.util.List;

import com.shixzh.bcms.po.UserPO;

/**
 * 
 * @author shixzh
 *
 */
public interface UserService {

	UserPO getByUserId(Long userId);

	Integer deleteUser(Long userId);

	Integer addUser(UserPO userPO);

	Integer updateUser(UserPO userPO);

	List<UserPO> listUser(UserPO userPO);

	UserPO getByUserIdTestDirtyRead(Long userId);

	Integer updateUserTestDirtyRead(UserPO userPO);

	List<UserPO> listUserTestPhantomRead(UserPO userPO);

	UserPO getUserByMobile(String userMobile);
}
