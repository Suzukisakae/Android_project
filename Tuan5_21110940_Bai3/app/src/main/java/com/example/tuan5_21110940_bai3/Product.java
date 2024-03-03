/*
* Nguoi viet: Le Thanh Vinh
* MSSV: 21110940
* Bai 3: Bai tap xoa san pham
* */
package com.example.tuan5_21110940_bai3;

public class Product {
    String name;
    int price;
    int productID;

    public Product() {
    }

    public Product(String name, int price, int productID) {
        this.name = name;
        this.price = price;
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
