package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
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


    private ProductDaoJDBC(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ProductDaoJDBC getInstance(DataSource dataSource){
        if (instance == null) instance = new ProductDaoJDBC(dataSource);
        return instance;
    }

    @Override
    public void add(Product product) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "INSERT INTO products (name, price, category_id, supplier_id, currency, image_file_name) VALUES (?, ?, ?, ?, ?, ?);";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, product.getName();
        statement.setString(2, product.getPrice().toString());
        statement.setInt(3, product.getProductCategory().getId());
        statement.setInt(4, product.getSupplier().getId());
        statement.setString(5, product.getDefaultCurrency().toString());
        statement.setString(6, product.getImageFileName());

        statement.executeUpdate();
    }

    @Override
    public Product find(int id) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                "    JOIN product_categories pc on pc.id = p.category_id\n" +
                "    JOIN suppliers s on p.supplier_id = s.id                                                                                                                                                        \n" +
                "    WHERE p.id=1;";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        ResultSet queryResult = sqlStatement.executeQuery();
        queryResult.next();

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

    @Override
    public void remove(int id) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "DELETE FROM products WHERE id=?;";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setInt(1, id);

        statement.executeUpdate();
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> result = new ArrayList<>();
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                "    JOIN product_categories pc on pc.id = p.category_id\n" +
                "    JOIN suppliers s on p.supplier_id = s.id\n";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

        ResultSet queryResult = sqlStatement.executeQuery();

        return queryResultToList(queryResult);
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws SQLException {
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

    @Override
    public List<Product> getBy(ProductCategory productCategory) throws SQLException {
        List<Product> result = new ArrayList<>();

        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT p.id, p.name, p.price, p.category_id, p.supplier_id, p.currency, p.description, p.image_file_name, pc.id, pc.name, pc.department, pc.description, s.name, s.description FROM products p\n" +
                "    JOIN product_categories pc on pc.id = p.category_id\n" +
                "    JOIN suppliers s on p.supplier_id = s.id\n" +
                "    WHERE pc.name ILIKE ?;";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
        sqlStatement.setString(1, productCategory.getName());

        ResultSet queryResult = sqlStatement.executeQuery();

        return queryResultToList(queryResult);;ű
    }

    private static List<Product> queryResultToList (ResultSet queryResult) throws SQLException {
        List<Product> result = new ArrayList<>();

        while (queryResult.next()) {
            String name = queryResult.getString("p.name");
            BigDecimal defaultPrice = BigDecimal.valueOf(queryResult.getDouble("price"));
            String currencyString = queryResult.getString("currency");
            String description = queryResult.getString("p.description");
            String imageFileName = queryResult.getString("image_file_name");

            String categoryName = queryResult.getString("pc.name");
            String categoryDescription = queryResult.getString("pc.description");
            String categoryDepartment = queryResult.getString("department");
            ProductCategory productCategoryTemp = new ProductCategory(categoryName, categoryDepartment, categoryDescription);

            String supplierName = queryResult.getString("s.name");
            String supplierDescription = queryResult.getString("s.description");
            Supplier supplierTemp = new Supplier(supplierName, supplierDescription);
            Product product = new Product(name, defaultPrice, currencyString, description, productCategoryTemp, supplierTemp, imageFileName);

            result.add(product);
        }
        return result;
    }
}
