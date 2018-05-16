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
	private static final Long THREAD_SLEEP_MILLIS = 3000l;

	@Autowired
	private UserDao userDao;

	@Override
	public UserPO getByUserId(Long userId) {
		logger.info("UserServiceImpl -> getByUserId, userId={}", userId);
		return userDao.selectByPrimaryKey(userId);
	}

	@Override
	public UserPO getByUserIdTestDirtyRead(Long userId) {
		logger.info("UserServiceImpl -> getByUserIdTestDirtyRead, userId={}", userId);
		UserPO userPO = getByUserId(userId);
		logger.warn("Get detail for the user {}, userPO={}", userId, userPO);
		try {
			Thread.sleep(THREAD_SLEEP_MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UserPO userPO2 = getByUserId(userId);
		logger.warn("Get detail for the user {}, userPO2={}", userId, userPO2);
		return userPO2;
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

	@SuppressWarnings("finally")
	@Override
	public Integer updateUserTestDirtyRead(UserPO userPO) {
		logger.info("UserServiceImpl -> updateUserTestDirtyRead, userPO={}", userPO);
		try {
			updateUser(userPO);
			// sleep 10s
			Thread.sleep(THREAD_SLEEP_MILLIS);
		} catch (RuntimeException | InterruptedException e) {
			logger.error("There is a RuntimeException:", e);
		} finally {
			throw new RuntimeException();
		}
	}

	@Override
	public List<UserPO> listUser(UserPO userPO) {
		logger.info("UserServiceImpl -> listUser, userPO={}", userPO);
		return userDao.listUser(userPO);
	}

	@Override
	public List<UserPO> listUserTestPhantomRead(UserPO userPO) {
		logger.info("UserServiceImpl -> listUserTestPhantomRead, userPO={}", userPO);
		List<UserPO> lst = userDao.listUser(userPO);
		logger.info("UserServiceImpl -> listUserTestPhantomRead, List<UserPO> lst={}", lst);
		try {
			Thread.sleep(THREAD_SLEEP_MILLIS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<UserPO> lst2 = userDao.listUser(userPO);
		logger.info("UserServiceImpl -> listUserTestPhantomRead, List<UserPO> lst2={}", lst);
		return lst2;
	}

}
