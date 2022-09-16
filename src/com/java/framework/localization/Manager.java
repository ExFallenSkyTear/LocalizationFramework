package com.java.framework.localization;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Manager {
    private final ArrayList<Category> localizationCategories = new ArrayList<>();

    private String languageTag = "default";

    private final String ROOT_ELEMENT_NAME = "Localization";

    private final String FILE_NAME = "DefaultLocalization.xml";

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

    public String quickRead(String categoryName, String entryName) {
        return this.getCategory(categoryName).getEntry(entryName).getValue();
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

    public void exportDefaults(String fileDirectory) throws Exception {
        String fullFilePath = fileDirectory + FILE_NAME;
        this.writeXmlDocument(fullFilePath, this.getXmlDocument());
    }

    private Document getXmlDocument() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document xmlDocument = docBuilder.newDocument();

        this.addToXml(xmlDocument);

        return xmlDocument;
    }

    public void addToXml(Document sourceDocument){
        Element localNode = sourceDocument.createElement(ROOT_ELEMENT_NAME);
        sourceDocument.appendChild(localNode);

        for (Category localizationCategory : localizationCategories) {
            localizationCategory.addToXml(sourceDocument, localNode);
        }
    }

    private void writeXmlDocument(String fullFilePath, Document xmlDocument) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(fullFilePath);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(xmlDocument);
        StreamResult result = new StreamResult(outputStream);

        transformer.transform(source, result);
    }
}