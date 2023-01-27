package com.codecool.shop.service;

import com.codecool.shop.dao.LoginDao;

public class LoginService {

    LoginDao loginDao;

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public String getUserName(String email, String password) {
        System.out.println(loginDao.getUserName(email, password) +"!!!!");
        return loginDao.getUserName(email, password);
    }

    public int getUserId(String email, String password) {
        return loginDao.getUserId(email, password);
    }

    public int getIsAdmin(String email, String password) {
        return loginDao.getIsAdmin(email, password);
    }

    public boolean verifyPassword(String password) {
        return loginDao.getPassword(password) != null;
    }
}
