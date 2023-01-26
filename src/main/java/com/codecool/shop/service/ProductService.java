package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService{
    private ProductDaoJDBC productDaoJDBC;
    private ProductCategoryDaoJDBC productCategoryDaoJDBC;


    public ProductService(ProductDaoJDBC productDaoJDBC, ProductCategoryDaoJDBC productCategoryDaoJDBC) {
        this.productDaoJDBC = productDaoJDBC;
        this.productCategoryDaoJDBC = productCategoryDaoJDBC;
    }

    public ProductCategory getProductCategory(int id) throws SQLException {
        return productCategoryDaoJDBC.find(id);
    }

    public List<Product> getProductsForCategory(int id) throws SQLException {
        var category = productCategoryDaoJDBC.find(id);
        return productDaoJDBC.getBy(category);
    }

    public List<Product> getAllProduct(){
        return productDaoJDBC.getAll();
    }
}
