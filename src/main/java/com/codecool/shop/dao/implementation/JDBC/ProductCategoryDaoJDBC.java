package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.model.ProductCategory;

import javax.sql.DataSource;
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

    public void add(ProductCategory productCategory){
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "INSERT INTO product_categories (name, department, description) VALUES (?, ?, ?);";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, productCategory.getName());
            statement.setString(2, productCategory.getDepartment());
            statement.setString(3, productCategory.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }


    public ProductCategory find(String name){
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT id, name, department, description FROM product_categories\n" +
                    "WHERE name=?;";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
            sqlStatement.setString(1, name);

            ResultSet queryResult = sqlStatement.executeQuery();
            queryResult.next();

            return buildProductCategory(queryResult);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    ;

    public void remove(String name) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "DELETE FROM product_categories WHERE name=?;";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public List<ProductCategory> getAll() {
        try {
            List<ProductCategory> result = new ArrayList<>();
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT id, name, department, description FROM product_categories";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

            ResultSet queryResult = sqlStatement.executeQuery();

            return queryResultToList(queryResult);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private List<ProductCategory> queryResultToList(ResultSet queryResult) {

        try {
            List<ProductCategory> result = new ArrayList<>();

            while (queryResult.next()) {
                ProductCategory productCategory = buildProductCategory(queryResult);
                result.add(productCategory);
            }

            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private ProductCategory buildProductCategory(ResultSet queryResult) {
        try {
            String name = queryResult.getString("name");
            String department = queryResult.getString("department");
            String description = queryResult.getString("description");

            return new ProductCategory(name, department, description);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
