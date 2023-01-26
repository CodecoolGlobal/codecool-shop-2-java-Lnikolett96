package com.codecool.shop.dao;


public interface LoginDao {

    public String getUserName(String email, String password);

    public int getUserId(String email, String password);

    public int getIsAdmin(String email, String password);

    public String verifyPassword(String password);
}
