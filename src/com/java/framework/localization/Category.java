package com.java.framework.localization;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Category {
    private final ArrayList<Entry> categoryEntries = new ArrayList<>();

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Entry createEntry(String entryName, String defaultValue) throws Exception {
        if (!this.entryExist(entryName)) {
            Entry newEntry = new Entry(entryName, defaultValue);
            categoryEntries.add(newEntry);
            return newEntry;
        } else throw new IllegalArgumentException();
    }

    public Entry getEntry(String entryName) {
        return this.categoryEntries.get(this.getEntryIndex(entryName));
    }

    public boolean entryExist(String entryName) {
        return getEntryIndex(entryName) >= 0;
    }

    private int getEntryIndex(String entryName) {
        for (int i = 0; i < categoryEntries.size(); i++) {
            if (categoryEntries.get(i).getName() == entryName) return i;
        }
        return -1;
    }

    public void addToXml(Document sourceDocument, Element sourceElement){
        Element localNode = sourceDocument.createElement(this.name);
        sourceElement.appendChild(localNode);

        for (Entry categoryEntry : categoryEntries) {
            categoryEntry.addToXml(sourceDocument, localNode);
        }
    }
}
