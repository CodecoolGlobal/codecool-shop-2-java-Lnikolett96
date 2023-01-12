package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.sql.SQLException;

public interface registrationDao {
    void add(User user) throws SQLException;
    void addAdmin(User user) throws SQLException;
}
