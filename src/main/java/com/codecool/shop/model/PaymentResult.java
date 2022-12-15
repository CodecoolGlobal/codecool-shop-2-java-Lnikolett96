package com.codecool.shop.model;

import java.math.BigDecimal;

public class PaymentResult {
    private String name;
    private int zipCode;
    private String city;
    private String address;
    private String phone;
    private String payment_method;
    private String credit_card_number;
    private String email;
    private BigDecimal totalPrice;
    private boolean success;


    public PaymentResult(String name, int zipCode, String city, String address, String phone, String payment_method, String credit_card_number, String email, BigDecimal totalPrice, boolean success) {
        this.name = name;
        this.zipCode = zipCode;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.payment_method = payment_method;
        this.credit_card_number = credit_card_number;
        this.email = email;
        this.totalPrice = totalPrice;
        this.success = success;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public String getCredit_card_number() {
        return credit_card_number;
    }

    public String getEmail() {
        return email;
    }

    public boolean isSuccess() {
        return success;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public String getName() {
        return name;
    }
}
