package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private ProductCategoryDaoJDBC instance = null;
    private DataSource dataSource;

    private ProductCategoryDaoJDBC() {
        dataSource = new SQLDataConnection().connect();
    }

    public ProductCategoryDaoJDBC getInstance() {
        if (instance==null) instance = new ProductCategoryDaoJDBC();
        return instance;
    }

    public void add(ProductCategory productCategory) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "INSERT INTO product_categories (name, department, description) VALUES (?, ?, ?);";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, productCategory.getName());
        statement.setString(2, productCategory.getDepartment());
        statement.setString(3, productCategory.getDescription());

        statement.executeUpdate();
    }

    




}
