package com.shixzh.bcms.framework.shiro;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class ShiroLogin {

	public void login() {

		String username = "name";
		String password = "password";
		// 1. Acquire submitted principals and credentials:
		AuthenticationToken token = new UsernamePasswordToken(username, password);
		// 2. Get the current Subject:
		Subject currentUser = SecurityUtils.getSubject();
		// 3. Login:
		try {
			currentUser.login(token);
		} catch (IncorrectCredentialsException ice) {

		} catch (LockedAccountException lae) {

		} catch (AuthenticationException ae) {

		}

		// Listing 7. Role Check
		if (currentUser.hasRole("administrator")) {
			// show the ‘Create User’ button
		} else {
			// grey-out the button?
		}

		// Listing 8. Permission Check

		if (currentUser.isPermitted("user:create")) {
			// show the ‘Create User’ button
		} else {
			// grey-out the button?
		}

		// Listing 9. Instance-Level Permission Check

		if (currentUser.isPermitted("user:delete:jsmith")) {
			// delete the ‘jsmith’ user
		} else {
			// don’t delete ‘jsmith’
		}

		// Listing 10. Subject’s Session

		Session session = currentUser.getSession();
		// Session session2 = currentUser.getSession(true);

		// Listing 11. Session methods
		session.getAttribute("key");
		Date start = session.getStartTimestamp();
		Date timestamp = session.getLastAccessTime();
		session.setTimeout(2000);
	}

}
