package com.codecool.shop.dao;

import java.sql.SQLException;

public interface LoginDao {

    public String getUserName(String email, String password);
}
