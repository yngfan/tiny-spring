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

//    static {
//        hashMap.put("001", "我是001");
//        hashMap.put("002", "我是002");
//        hashMap.put("003", "我是003");
//    }

    public void initDataMethod(){
        System.out.println("执行：init-method");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyDataMethod(){
        System.out.println("执行：destroy-method");
        hashMap.clear();
    }

    public String queryUserName(String uId) {
        return hashMap.get(uId);
    }


}
