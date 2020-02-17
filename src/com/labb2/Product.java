package com.labb2;

import java.util.Objects;

public class Product {
    private String productName;
    private String productNumber;
    private String color;
    private String info;
    private Brand brand;
    private Category category;



    public Product(String productName, String productNumber, String color, String info) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.color = color;
        this.info = info;
    }

    public Product(String productName, String productNumber, String color, String info, Brand brand, Category category) {
        this.productName = productName;
        this.productNumber = productNumber;
        this.color = color;
        this.info = info;
        this.brand = brand;
        this.category = category;
    }

    //<editor-fold desc="Getter/Setter">
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    //</editor-fold>


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName) &&
                Objects.equals(productNumber, product.productNumber) &&
                Objects.equals(color, product.color) &&
                Objects.equals(info, product.info) &&
                Objects.equals(brand, product.brand) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productName, productNumber, color, info, brand, category);
    }

    @Override
    public String toString() {
        return "productName :'" + productName + '\'' +
                ", productNumber :'" + productNumber + '\'' +
                ", color :'" + color + '\'' +
                ", info :'" + info + '\'' +
                "," + brand +
                "," + category;
    }
}
