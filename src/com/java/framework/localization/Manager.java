package com.java.framework.localization;

import java.util.ArrayList;

public class Manager {
    private final ArrayList<Category> localizationCategories = new ArrayList<>();

    public Category getCategory(String categoryName) {
        return this.localizationCategories.get(this.getCategoryIndex(categoryName));
    }

    private int getCategoryIndex(String categoryName) {
        for (int i = 0; i < localizationCategories.size(); i++) {
            if (localizationCategories.get(i).getName() == categoryName) return i;
        }
        return -1;
    }
}