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

    public void add(Supplier supplier) {
        try {
            Connection sqlConnection = dataSource.getConnection();

        String query = "INSERT INTO suppliers (name, description) VALUES (?, ?);";

        PreparedStatement statement = sqlConnection.prepareStatement(query);
        statement.setString(1, supplier.getName());
        statement.setString(2, supplier.getDescription());

        statement.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        }
    }

    public Supplier find(String name){
        try{
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT id, name, description FROM suppliers\n" +
                    "WHERE name=?;";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);
            sqlStatement.setString(1, name);

            ResultSet queryResult = sqlStatement.executeQuery();
            queryResult.next();

            return buildSupplier(queryResult);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
       return null;
    }

    public void remove(String name) {
        try{
            Connection sqlConnection = dataSource.getConnection();

            String query = "DELETE FROM suppliers WHERE name=?;";

            PreparedStatement statement = sqlConnection.prepareStatement(query);
            statement.setString(1, name);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Supplier> getAll() {
        try {
            Connection sqlConnection = dataSource.getConnection();

            String query = "SELECT id, name, description FROM suppliers";

            PreparedStatement sqlStatement = sqlConnection.prepareStatement(query);

            ResultSet queryResult = sqlStatement.executeQuery();

            return queryResultToList(queryResult);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private List<Supplier> queryResultToList(ResultSet queryResult) {
        try {
            List<Supplier> result = new ArrayList<>();

            while (queryResult.next()) {
                Supplier supplier = buildSupplier(queryResult);
                result.add(supplier);
            }

            return result;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    private Supplier buildSupplier(ResultSet queryResult) {
        try {
            return new Supplier(queryResult.getString("name"), queryResult.getString("description"));
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
