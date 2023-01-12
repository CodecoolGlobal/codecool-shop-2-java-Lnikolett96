package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier) throws SQLException;
    Supplier find(String name) throws SQLException;
    void remove(String name) throws SQLException;

    List<Supplier> getAll() throws SQLException;
}
