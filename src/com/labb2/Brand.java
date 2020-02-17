package com.labb2;

import java.util.Objects;

public class Brand {
    private String nameOfBrand;


    public Brand(String nameOfBrand) {
        this.nameOfBrand = nameOfBrand;
    }


    public String getNameOfBrand() {
        return nameOfBrand;
    }

    public void setNameOfBrand(String nameOfBrand) {
        this.nameOfBrand = nameOfBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(nameOfBrand, brand.nameOfBrand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfBrand);
    }

    @Override
    public String toString() { return " brand :" +"'"+ nameOfBrand +"'";}
}
