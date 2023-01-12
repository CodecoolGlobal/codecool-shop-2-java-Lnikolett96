package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(String name);
    void remove(String name);

    List<Supplier> getAll() throws SQLException;
}
