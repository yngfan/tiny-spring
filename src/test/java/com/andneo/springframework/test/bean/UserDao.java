package com.andneo.springframework.test.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: tiny-spring
 * @description:
 * @author: fanfan.yang
 * @create: 2021-09-24 16:50
 **/
public class UserDao {

    private static Map<String, String> hashMap = new HashMap<String, String>();

    static {
        hashMap.put("001", "我是001");
        hashMap.put("002", "我是002");
        hashMap.put("003", "我是003");
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }


}
