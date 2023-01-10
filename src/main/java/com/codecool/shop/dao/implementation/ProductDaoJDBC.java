package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.model.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductDaoJDBC implements ProductDao {

    private static ProductDaoJDBC instance = null;
    private DataSource dataSource;


    private ProductDaoJDBC() {
        dataSource = new SQLDataConnection().connect("MonsterWebshop", "mimi", "vmimif");
    }

    public static ProductDaoJDBC getInstance(){
        if (instance == null) instance = new ProductDaoJDBC();
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


}
