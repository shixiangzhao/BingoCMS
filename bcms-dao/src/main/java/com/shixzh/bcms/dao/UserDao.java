package com.shixzh.bcms.dao;

import com.shixzh.bcms.po.UserPO;

public interface UserDao {

	UserPO selectByPrimaryKey(Long userId);

}
