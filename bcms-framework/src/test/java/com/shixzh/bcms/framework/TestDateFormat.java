package com.shixzh.bcms.framework;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDateFormat {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		System.out.println(sdf.format(new Date()));
	}

}
