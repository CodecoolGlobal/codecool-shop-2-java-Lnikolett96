package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category) throws SQLException;
    ProductCategory find(int id);
    void remove(int id);

    List<ProductCategory> getAll();

}
