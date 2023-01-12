package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;

public interface ProductCategoryDao {

    void add(ProductCategory category);
    ProductCategory find(String name);
    void remove(String name);

    List<ProductCategory> getAll();

}
