package com.codecool.shop.dao.implementation.JDBC;

public class CartDaoJDBC {

    private static CartDaoJDBC instance = null;

    public CartDaoJDBC() {
    }

    public CartDaoJDBC getInstance() {
        if(instance == null) instance = new CartDaoJDBC();
        return instance;
    };
}
