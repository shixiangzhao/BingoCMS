package com.shixzh.bcms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shixzh.bcms.service.SystemService;

@Service("systemService")
public class SystemServiceImpl implements SystemService {

	private static Logger logger = LoggerFactory.getLogger(SystemServiceImpl.class);

	@Override
	public void toLogin() {
		logger.info("SystemServiceImpl -> toLogin");
	}

}
