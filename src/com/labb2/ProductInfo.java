package com.labb2;

import java.util.Objects;

public class ProductInfo {
    Product product;
    int quantity;
    float price;
    Discount d;


    public ProductInfo(Product product, int quantity, float price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductInfo(Product product, int quantity, float price, Discount d) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.d = d;
    }

    //<editor-fold desc="Getter/Setter">
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    //</editor-fold>

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductInfo that = (ProductInfo) o;
        return quantity == that.quantity &&
                Float.compare(that.price, price) == 0 &&
                Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, price);
    }

    @Override
    public String toString() {
        return "{" + product +
                ", Quantity :" +"'"+ quantity +"'"+
                ", price :" +"'"+ price +"'"+
                '}';
    }
}
