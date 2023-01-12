package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.dao.implementation.mem.ProductCategoryDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao {
    private static ProductCategoryDaoJDBC instance = null;
    private DataSource dataSource;

    private ProductCategoryDaoJDBC() {
        dataSource = new SQLDataConnection().connect();
    }

    public static ProductCategoryDaoJDBC getInstance() {
        if (instance == null) instance = new ProductCategoryDaoJDBC();
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

    public ProductCategory find(String name) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT id, name, department, description FROM product_categories\n" +
                "WHERE name=?;";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
        sqlStatement.setString(1, name);

        ResultSet queryResult = sqlStatement.executeQuery();
        queryResult.next();

        return buildProductCategory(queryResult);
    }

    ;

    public void remove(String name) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "DELETE FROM product_categories WHERE id=?;";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, name);

        statement.executeUpdate();
    }

    public List<ProductCategory> getAll() throws SQLException {
        List<ProductCategory> result = new ArrayList<>();
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT id, name, department, description FROM product_categories";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

        ResultSet queryResult = sqlStatement.executeQuery();

        return queryResultToList(queryResult);
    }

    private List<ProductCategory> queryResultToList(ResultSet queryResult) throws SQLException {
        List<ProductCategory> result = new ArrayList<>();

        while (queryResult.next()) {
            ProductCategory productCategory = buildProductCategory(queryResult);
            result.add(productCategory);
        }

        return result;
    }


    private ProductCategory buildProductCategory(ResultSet queryResult) throws SQLException {
        String name = queryResult.getString("name");
        String department = queryResult.getString("department");
        String description = queryResult.getString("description");

        return new ProductCategory(name, department, description);
    }
}
