package com.shixzh.bcms.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.shixzh.bcms.dao.UserDao;
import com.shixzh.bcms.framework.util.DecriptUtil;
import com.shixzh.bcms.po.UserPO;

public class MyShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserDao userDao;

	/*
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		/*
		String userName = (String) principals.fromRealm(getName()).iterator().next(); 
		UserPO userPO = userDao.selectByMobile(userName);
		if (userPO != null) {  
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();  
            for (UserGroupPO group : userPO.getGroupList()) {
                info.addStringPermissions(group.getPermissionList());  
            }  
            return info;  
        } else {  
        	throw new AuthenticationException(); 
        }  
		*/

		Set<String> roleNames = new HashSet<String>();
		Set<String> permissions = new HashSet<String>();
		roleNames.add("admin");// 添加角色
		permissions.add("system:index");// 添加权限
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
		info.setStringPermissions(permissions);
		return info;
	}

	/*
	 * 登录验证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserPO userPO = userDao.selectByMobile(token.getUsername());
		if (userPO != null) {
			return new SimpleAuthenticationInfo(userPO.getUserMobile(), DecriptUtil.MD5(userPO.getUserPassword()),
					getName());
		} else {
			throw new AuthenticationException();
		}
	}

}
