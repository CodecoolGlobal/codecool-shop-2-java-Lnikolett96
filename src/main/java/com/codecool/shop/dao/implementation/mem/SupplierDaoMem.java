package com.codecool.shop.dao.implementation.mem;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> data = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(data.size() + 1);
        data.add(supplier);
    }

    @Override
    public Supplier find(String name) {
        return data.stream().filter(t -> t.getName().equals(name)).findFirst().orElse(null);
    }

    @Override
    public void remove(String name) {
        data.remove(find(name));
    }

    @Override
    public List<Supplier> getAll() {
        return data;
    }
}
