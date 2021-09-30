package com.andneo.springframework.test.bean;

import com.andneo.springframework.beans.factory.DisposableBean;
import com.andneo.springframework.beans.factory.InitializingBean;

/**
 * @program: tiny-spring
 * @description: UserService
 * @author: fanfan.yang
 * @create: 2021-09-23 18:57
 **/
public class UserService implements InitializingBean, DisposableBean {

    private String uid;

    private UserDao userDao;

    private String company;

    private String location;

    public String queryUserInfo() {

//        String s = this.userDao.queryUserName(uid);
//        System.out.println("查询用户信息 -> " + s);

        return userDao.queryUserName(uid) + "," + company + "," + location;
    }

    public String getUid() {
        return uid;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public String getCompany() {
        return company;
    }

    public String getLocation() {
        return location;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");

    }
}
