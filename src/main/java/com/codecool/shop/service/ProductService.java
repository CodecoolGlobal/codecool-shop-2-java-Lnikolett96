package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService{
    private ProductDao productDao;
    private ProductCategoryDao productCategoryDao;


    public ProductService(ProductDao productDao, ProductCategoryDao productCategoryDao) {
        this.productDao = productDao;
        this.productCategoryDao = productCategoryDao;
    }

    public ProductCategory getProductCategory(String name) throws SQLException {
        return productCategoryDao.find(name);
    }

    public List<Product> getProductsForCategory(String name) throws SQLException {
        var category = productCategoryDao.find(name);
        return productDao.getBy(category);
    }
}
