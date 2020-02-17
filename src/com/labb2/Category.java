package com.labb2;

import java.util.Objects;

public class Category {

    private String categories;

    public Category() {
    }

    public Category(String categories) {
        this.categories = categories;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categories, category.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories);
    }

    @Override
    public String toString() {
        return " category :'" + categories + '\'';
    }
}
