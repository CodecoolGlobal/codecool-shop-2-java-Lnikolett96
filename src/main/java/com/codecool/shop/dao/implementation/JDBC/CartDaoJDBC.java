package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartDaoJDBC {

    private static CartDaoJDBC instance = null;
    DataSource dataSource;

    public CartDaoJDBC() {
    }

    public static CartDaoJDBC getInstance() {
        if(instance == null) instance = new CartDaoJDBC();
        return instance;
    };

    public void add(User user, Product product) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "INSERT INTO carts (user_id, product_id) VALUES (?, ?);";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setInt(2, product.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void remove(User user, Product product) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "DELETE FROM carts WHERE user_id = ? AND product_id = ?;";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setInt(1, user.getId());
            statement.setInt(2, product.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Product> getAll(User user) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT c.id, SUM(c.quantity), p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name FROM carts as c Join products p on p.id = c.product_id WHERE c.id=? GROUP by c.product_id, c.id, p.name";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

            ResultSet queryResult = sqlStatement.executeQuery();

            return queryResultToList(queryResult);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private List<Product> queryResultToList(ResultSet queryResult) {

        try {
            List<Product> result = new ArrayList<>();

            while (queryResult.next()) {
                Product product = buildProduct(queryResult);
                result.add(product);
            }

            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private Product buildProduct(ResultSet queryResult) {
        int categoryIdIndex = 9;
        int categoryNameIndex = 10;
        int categoryDepartmentIndex = 11;
        int categoryDescriptionIndex = 12;
        int supplierNameIndex = 13;
        int supplierDescriptionIndex = 14;


        try {
            String name = queryResult.getString("name");
            BigDecimal defaultPrice = BigDecimal.valueOf(queryResult.getDouble("price"));
            String currencyString = queryResult.getString("currency");
            String description = queryResult.getString("description");
            String imageFileName = queryResult.getString("image_file_name");

            int categoryId = Integer.parseInt(queryResult.getString(categoryIdIndex));

            String categoryName = queryResult.getString(categoryNameIndex);
            String categoryDescription = queryResult.getString(categoryDescriptionIndex);
            String categoryDepartment = queryResult.getString(categoryDepartmentIndex);
            ProductCategory productCategory = new ProductCategory(categoryId,categoryName, categoryDepartment, categoryDescription);

            String supplierName = queryResult.getString(supplierNameIndex);
            String supplierDescription = queryResult.getString(supplierDescriptionIndex);
            Supplier supplier = new Supplier(supplierName, supplierDescription);

            return new Product(name, defaultPrice, currencyString, description, productCategory, supplier, imageFileName);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
