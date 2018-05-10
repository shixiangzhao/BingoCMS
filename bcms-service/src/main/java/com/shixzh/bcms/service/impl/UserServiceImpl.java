package com.shixzh.bcms.service.impl;

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

}
