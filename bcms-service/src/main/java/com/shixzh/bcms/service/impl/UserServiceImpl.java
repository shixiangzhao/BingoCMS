package com.shixzh.bcms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shixzh.bcms.dao.UserDao;
import com.shixzh.bcms.po.UserPO;
import com.shixzh.bcms.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserPO getByUserId(Long userId) {
		logger.info("UserServiceImpl -> getByUserId, userId={}", userId);
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public Integer deleteUser(Long userId) {
		logger.info("UserServiceImpl -> deleteUser, userId={}", userId);
		return userDao.deleteUser(userId);
	}

	@Override
	public Integer addUser(UserPO userPO) {
		logger.info("UserServiceImpl -> addUser, userPO={}", userPO);
		return userDao.addUser(userPO);
	}

	@Override
	public Integer updateUser(UserPO userPO) {
		logger.info("UserServiceImpl -> updateUser, userPO={}", userPO);
		return userDao.updateUser(userPO);
	}

	@Override
	public List<UserPO> listUser(UserPO userPO) {
		logger.info("UserServiceImpl -> listUser, userPO={}", userPO);
		return userDao.listUser(userPO);
	}

}
