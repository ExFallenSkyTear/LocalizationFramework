package com.java.framework.localization;

import java.util.ArrayList;

public class LocalizationManager {
    private final ArrayList<LocalizationEntry> localizationList = new ArrayList<>();

    public String getLocalization(String key) {
        return this.localizationList.get(this.getEntryIndex(key)).getValue();
    }

    private int getEntryIndex(String key) {
        for (int i = 0; i < localizationList.size(); i++) {
            if (localizationList.get(i).getName() == key) return i;
        }
        return -1;
    }
}