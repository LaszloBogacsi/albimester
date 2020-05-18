package com.laszlobogacsi.albimester.parser;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

@Component
public class XMLDocumentParser implements DocumentParser {
    @Override
    public Element parse(InputStream inputStream) {
        try {
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            final Document document = dBuilder.parse(inputStream);
            return document.getDocumentElement();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new XMLDocumentParseException("Failed to parse document: " + e.getMessage(), e);
        }
    }
}
