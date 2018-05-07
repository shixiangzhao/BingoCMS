package com.shixzh.bcms.framework;

public class TestSwitch {

	public static void main(String[] args) {
		//getXXX进行二次逻辑的，一定要判空
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

		Integer integer = null;
		System.out.println(integer.toString());

	}

}
