package com.shixzh.bcms.service;

import com.shixzh.bcms.po.UserPO;

/**
 * 
 * @author shixzh
 *
 */
public interface UserService {

	UserPO getByUserId(Long userId);
}
