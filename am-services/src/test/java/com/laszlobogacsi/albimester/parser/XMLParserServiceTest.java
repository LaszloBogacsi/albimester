package com.laszlobogacsi.albimester.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class XMLParserServiceTest {
    private XMLParserService parserService;

    @BeforeEach
    void setUp() {
        final DocumentParser xmlParser = new XMLDocumentParser();
        parserService = new XMLParserService(xmlParser);
    }

    @Test
    void canParseABillFromFile() throws IOException {
        String filename = "dummyBillFile.xml";

        final InputStream input = Files.newInputStream(Paths.get("src/test/resources/" + filename));
        final DummyBill bill = parserService.parseInput(input, new DummyBillParser());
        assertThat(bill.name).isEqualTo("Dummy Name");
    }

    private static class DummyBill implements Bill {
        private String name;

        public DummyBill setName(String name) {
            this.name = name;
            return this;
        }
    }

    private static class DummyBillParser implements BillParser<DummyBill> {
        @Override
        public DummyBill parseBill(Element root) {
            return new DummyBill().setName(root.getElementsByTagName("name").item(0).getTextContent().trim());
        }
    }
}
