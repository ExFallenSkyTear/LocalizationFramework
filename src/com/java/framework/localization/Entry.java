package com.java.framework.localization;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
        return this.value != "" && this.value != null?  this.value : this.defaultValue;
    }

    public void addToXml(Document sourceDocument, Element sourceElement) {
        Element localNode = sourceDocument.createElement(this.getName());
        sourceElement.appendChild(localNode);
        localNode.setAttribute("value", this.getValue());
    }
}