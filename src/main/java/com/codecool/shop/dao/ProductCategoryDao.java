package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category) throws SQLException;
    ProductCategory find(String name) throws SQLException;
    void remove(String name) throws SQLException;

    List<ProductCategory> getAll() throws SQLException;

}
