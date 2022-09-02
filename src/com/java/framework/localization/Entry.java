package com.java.framework.localization;

public class Entry {
    private final String name;

    private final String defaultValue;

    private String value;

    Entry(String name, String defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return this.name;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }

    public String getValue() {
        return this.value;
    }
}
