package com.example.security.dto;


public class Order {
    private String id;
    private String product;
    private String customer;
    private String price;

    public Order(String id, String product, String customer, String price) {
        this.id = id;
        this.product = product;
        this.customer = customer;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public String getCustomer() {
        return customer;
    }

    public String getPrice() {
        return price;
    }


}
