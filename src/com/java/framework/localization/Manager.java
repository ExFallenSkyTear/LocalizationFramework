package com.java.framework.localization;

import java.util.ArrayList;

public class Manager {
    private final ArrayList<Category> localizationCategories = new ArrayList<>();

    private String languageTag = "default";

    public Category createCategory(String categoryName) throws Exception {
        if (!this.categoryExist(categoryName)) {
            Category newCategory = new Category(categoryName);
            localizationCategories.add(newCategory);
            return newCategory;
        } else throw new IllegalArgumentException();
    }

    public Category getCategory(String categoryName) {
        return this.localizationCategories.get(this.getCategoryIndex(categoryName));
    }

    public boolean categoryExist(String categoryName) {
        return getCategoryIndex(categoryName) >= 0;
    }

    private int getCategoryIndex(String categoryName) {
        for (int i = 0; i < localizationCategories.size(); i++) {
            if (localizationCategories.get(i).getName() == categoryName) return i;
        }
        return -1;
    }

    private void setLanguageTag(String languageTag) {
        this.languageTag = languageTag;
    }

    public String getLanguageTag() {
        return this.languageTag;
    }
}