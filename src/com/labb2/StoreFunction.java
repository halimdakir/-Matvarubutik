package com.labb2;

import java.util.List;

public interface StoreFunction {

    void priceRange();
    void filterByCategory();
    void lagerSaldo();
    void updateProduct();
    void purchases();
    void updateStock(int quantity, List<ProductInfo> list);
    boolean ifQuantityAvailable(int quantity, List<ProductInfo> list);
    void saveChanges();

}
