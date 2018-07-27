package com.shixzh.bcms.framework.json;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.shixzh.bcms.po.UserPO;
import org.junit.Test;

public class TestIsProperty {

    @Test
    public void testIsProperty() {
        UserPO userPO = new UserPO();
        userPO.setIsPaidMember(true);
        String str = JSON.toJSONString(userPO);
        System.out.println(str);
        //结果：{"paidMember":true}，由此可见，JSON.toJSONString()方法是对象转为json后的最终形式。
    }
}
