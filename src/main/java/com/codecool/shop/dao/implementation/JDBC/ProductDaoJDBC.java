package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.connection.SQLDataConnection;
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

public class ProductDaoJDBC implements ProductDao {

    private static ProductDaoJDBC instance = null;
    private DataSource dataSource;


    private ProductDaoJDBC() {
        dataSource = new SQLDataConnection().connect();
    }

    public static ProductDaoJDBC getInstance(){
        if (instance == null) instance = new ProductDaoJDBC();
        return instance;
    }

    @Override
    public void add(Product product) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "INSERT INTO products (name, price, category_id, supplier_id, currency, image_file_name) VALUES (?, ?, ?, ?, ?, ?);";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, product.getName());
            statement.setString(2, product.getPrice().toString());
            statement.setInt(3, product.getProductCategory().getId());
            statement.setInt(4, product.getSupplier().getId());
            statement.setString(5, product.getDefaultCurrency().toString());
            statement.setString(6, product.getImageFileName());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Product find(int id) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                    "    JOIN product_categories pc on pc.id = p.category_id\n" +
                    "    JOIN suppliers s on p.supplier_id = s.id                                                                                                                                                        \n" +
                    "    WHERE p.id=?;";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
            sqlStatement.setInt(1, id);

            ResultSet queryResult = sqlStatement.executeQuery();
            queryResult.next();

            return buildProduct(queryResult);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "DELETE FROM products WHERE id=?;";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Product> getAll() {
        try {
            List<Product> result = new ArrayList<>();
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                    "    JOIN product_categories pc on pc.id = p.category_id\n" +
                    "    JOIN suppliers s on p.supplier_id = s.id\n";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

            ResultSet queryResult = sqlStatement.executeQuery();

            return queryResultToList(queryResult);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        try {
            List<Product> result = new ArrayList<>();

            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                    "    JOIN product_categories pc on pc.id = p.category_id\n" +
                    "    JOIN suppliers s on p.supplier_id = s.id\n" +
                    "    WHERE s.name ILIKE ?;";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
            sqlStatement.setString(1, supplier.getName());

            ResultSet queryResult = sqlStatement.executeQuery();

            return queryResultToList(queryResult);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        try {
            List<Product> result = new ArrayList<>();

            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                    "    JOIN product_categories pc on pc.id = p.category_id\n" +
                    "    JOIN suppliers s on p.supplier_id = s.id\n" +
                    "    WHERE pc.name ILIKE ?;";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
            sqlStatement.setString(1, productCategory.getName());

            ResultSet queryResult = sqlStatement.executeQuery();

            return queryResultToList(queryResult);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static List<Product> queryResultToList (ResultSet queryResult) {
        try {
            List<Product> result = new ArrayList<>();

            while (queryResult.next()) {
                Product product = buildProduct(queryResult);
                result.add(product);
            }
            return result;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static Product buildProduct(ResultSet queryResult) {
        try {
            String name = queryResult.getString("p.name");
            BigDecimal defaultPrice = BigDecimal.valueOf(queryResult.getDouble("price"));
            String currencyString = queryResult.getString("currency");
            String description = queryResult.getString("p.description");
            String imageFileName = queryResult.getString("image_file_name");

            String categoryName = queryResult.getString("pc.name");
            String categoryDescription = queryResult.getString("pc.description");
            String categoryDepartment = queryResult.getString("department");
            ProductCategory productCategory = new ProductCategory(categoryName, categoryDepartment, categoryDescription);

            String supplierName = queryResult.getString("s.name");
            String supplierDescription = queryResult.getString("s.description");
            Supplier supplier = new Supplier(supplierName, supplierDescription);

            return new Product(name, defaultPrice, currencyString, description, productCategory, supplier, imageFileName);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
