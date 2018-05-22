package com.shixzh.bcms.framework.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.Factory;

@SuppressWarnings("deprecation")
public class ShiroConfiguration {

	public void makeAccessible() {
		//1. Load the INI configuration
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/shiro.ini");
		//2. Create the SecurityManager
		SecurityManager securityManager = factory.getInstance();
		//3. Make it accessible
		SecurityUtils.setSecurityManager(securityManager);
	}
}
