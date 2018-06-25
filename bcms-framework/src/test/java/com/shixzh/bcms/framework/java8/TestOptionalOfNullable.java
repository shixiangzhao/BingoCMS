package com.shixzh.bcms.framework.java8;

import com.shixzh.bcms.po.UserPO;
import org.junit.Assert;
import org.junit.Test;

public class TestOptionalOfNullable {

    @Test
    public void testGetUserIsNull() {
        OptionalOfNullable optionalOfNullable = new OptionalOfNullable();
        UserPO userPO = optionalOfNullable.getUser(null);
        System.out.println(userPO);
        Assert.assertEquals("User isn't Zhang San.","zhangsan",userPO.getUserName());
    }

    @Test
    public void testGetUserZhangsan() {
        OptionalOfNullable optionalOfNullable = new OptionalOfNullable();
        UserPO paramUserPO = new UserPO();
        paramUserPO.setUserName("zhangsan");
        UserPO userPO = optionalOfNullable.getUser(paramUserPO);
        System.out.println(userPO);
        Assert.assertEquals("User isn't Zhang San.","zhangsan",userPO.getUserName());
    }

    @Test
    public void testGetUserZhangsi() {
        OptionalOfNullable optionalOfNullable = new OptionalOfNullable();
        UserPO paramUserPO = new UserPO();
        paramUserPO.setUserName("zhangsi");
        UserPO userPO = optionalOfNullable.getUser(paramUserPO);
        System.out.println(userPO);
        Assert.assertEquals("User isn't Zhang San.","zhangwu",userPO.getUserName());
    }

    @Test
    public void testGetUserNormalZhangsi() {
        OptionalOfNullable optionalOfNullable = new OptionalOfNullable();
        UserPO paramUserPO = new UserPO();
        paramUserPO.setUserName("zhangsi");
        UserPO userPO = optionalOfNullable.getUserNormal(paramUserPO);
        System.out.println(userPO);
        Assert.assertEquals("User isn't Zhang San.","zhangwu",userPO.getUserName());
    }

}
