package com.laszlobogacsi.albimester.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class XMLDocumentParserTest {
    DocumentParser parser;

    @BeforeEach
    void setUp() {
        parser = new XMLDocumentParser();
    }

    @Test
    void parseASimpleNode() {
        final String xmlString = "<?xml version=\"1.0\"?><aNode>test text</aNode>";
        final InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        final Element element = parser.parse(inputStream);
        assertThat(element.getTextContent()).isEqualTo("test text");
    }

    @Test
    void parseAnEmptyNode() {
        final String xmlString = "<?xml version=\"1.0\"?><aNode></aNode>";
        final InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        final Element element = parser.parse(inputStream);
        assertThat(element.getTextContent()).isEqualTo("");
    }

    @Test
    void parseAnEmptyXML() {
        final String xmlString = "";
        final InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
        assertThatThrownBy(() -> parser.parse(inputStream)).isInstanceOf(XMLDocumentParseException.class);
    }


}
