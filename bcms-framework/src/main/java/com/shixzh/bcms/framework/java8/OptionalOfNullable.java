package com.shixzh.bcms.framework.java8;

import com.shixzh.bcms.po.UserPO;

import java.util.Optional;

public class OptionalOfNullable {

    public UserPO getUserNormal(UserPO user) {
        if (user != null && "zhangsan".equals(user.getUserName())) {
            return user;
        } else {
            UserPO userPO = new UserPO();
            userPO.setUserName("zhangwu");
            return userPO;
        }
    }

    public UserPO getUser(UserPO user) {
        return Optional.ofNullable(user)
                .filter(u -> "zhangsan".equals(u.getUserName()))
                .orElseGet(() -> {
                    UserPO userPO = new UserPO();
                    userPO.setUserName("zhangwu");
                    return userPO;
                });
    }

    public UserPO getUserWrong(UserPO user) {
        if (user != null) {
            String name = user.getUserName();
            if ("zhangsan".equals(name)){
                return user;
            }
            return null;
        } else {
            UserPO userPO = new UserPO();
            userPO.setUserName("zhangsan");
            return userPO;
        }
    }

}
