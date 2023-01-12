package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.connection.SQLDataConnection;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC implements SupplierDao {

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

    public void remove(int id) throws SQLException {
        Connection sqlConnection = dataSource.getConnection();

        String query = "DELETE FROM suppliers WHERE id=?;";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setInt(1, id);

        statement.executeUpdate();
    }

    public List<Supplier> getAll() throws SQLException {
        List<Product> result = new ArrayList<>();
        Connection sqlConnection = dataSource.getConnection();

        String query = "SELECT id, name, description FROM suppliers";

        PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

        ResultSet queryResult = sqlStatement.executeQuery();

        return queryResultToList(queryResult);
    }

    private List<Supplier> queryResultToList(ResultSet queryResult) throws SQLException {
        List<Supplier> result = new ArrayList<>();

        while (queryResult.next()) {
            Supplier supplier = buildSupplier(queryResult);
            result.add(supplier);
        }

        return result;
    }


    private Supplier buildSupplier(ResultSet queryResult) throws SQLException {
        return new Supplier(queryResult.getString("name"), queryResult.getString("description"));
    }
}
