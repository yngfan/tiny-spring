package com.andneo.springframework.test.bean;

/**
 * @program: tiny-spring
 * @description: UserService
 * @author: fanfan.yang
 * @create: 2021-09-23 18:57
 **/
public class UserService {

    private String uid;

    private UserDao userDao;

//    public UserService(String name) {
//        this.name = name;
//    }

    public void queryUserInfo() {

        String s = this.userDao.queryUserName(uid);
        System.out.println("查询用户信息 -> " + s);
    }
}
