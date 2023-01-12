package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDaoJDBC {

    private static SupplierDaoJDBC instance = null;
    DataSource dataSource;

    private SupplierDaoJDBC() {
        dataSource = new SQLDataConnection().connect();
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) instance = new SupplierDaoJDBC();
        return instance;
    }

    public void add(Supplier supplier) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "INSERT INTO suppliers (name, description) VALUES (?, ?);";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getDescription());

        statement.executeUpdate();
    }

    public Supplier find(int id) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT id, name, description FROM suppliers\n" +
                "WHERE id=?;";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
        sqlStatement.setInt(1, id);

        ResultSet queryResult = sqlStatement.executeQuery();
        queryResult.next();

        return buildSupplier(queryResult);
    }

    

    private Supplier buildSupplier(ResultSet queryResult) throws SQLException {
        return new Supplier(queryResult.getString("name"), queryResult.getString("description"));
    }


}
