package com.shixzh.bcms.framework;

import com.shixzh.bcms.po.UserPO;

public class TestIsNull {

	public static void main(String[] args) {

		// 对象set null
		UserPO userPO = new UserPO();
		String userMobile = null;
		userPO.setUserMobile(userMobile);
		System.out.println("对象set null成功");

		// 测试自动解包
		Integer iKey = new Integer(2);
		System.out.println("Integer equal int: " + (iKey == 2));
		switch (iKey) {
		case 1:
			System.out.println("iKey1");
			break;
		case 2:
			System.out.println("iKey2");
			break;
		default:
			System.out.println("iKey-1");
			break;
		}

		// getXXX进行二次逻辑的，一定要判空
		Byte key = null;
		switch (key) {
		case 1:
			System.out.println("1");
			break;
		case 2:
			System.out.println("2");
			break;
		default:
			System.out.println("-1");
			break;
		}

		// toString 也要判空
		Integer integer = null;
		System.out.println(integer.toString());

		// StringBuilder不能append空
		String love = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(love).append("-").append(love);
		System.out.println(sBuilder);
	}

}
