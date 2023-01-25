package com.codecool.shop.service;

import com.codecool.shop.dao.LoginDao;

public class LoginService {

    LoginDao loginDao;

    public LoginService(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    public String getUserName(String email, String password) {
       return loginDao.getUserName(email, password);
    }
}
