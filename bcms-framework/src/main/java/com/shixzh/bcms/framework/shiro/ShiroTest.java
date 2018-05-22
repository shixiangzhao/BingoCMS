package com.shixzh.bcms.framework.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.SecurityUtils;

public class ShiroTest {
	Subject currentUser = SecurityUtils.getSubject();

}
