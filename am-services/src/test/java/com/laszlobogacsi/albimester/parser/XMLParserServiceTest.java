package com.laszlobogacsi.albimester.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class XMLParserServiceTest {
    private XMLParserService parserService;

    @BeforeEach
    void setUp() {
        final DocumentParser xmlParser = new XMLDocumentParser();
        parserService = new XMLParserService(xmlParser);
    }

    @Test
    void canParseABillFromFile() {
        String filename = "dummyBillFile.xml";
        final DummyBill bill = parserService.parseFile(Paths.get("src/test/resources/" + filename), new DummyBillParser());
        assertThat(bill.name).isEqualTo("Dummy Name");
    }

    @Test
    void throwsExceptionIfFileDoesNotExist() {
        String filename = "fileDoesNotExist.xml";
        assertThatThrownBy(() -> parserService.parseFile(Paths.get("src/test/resources/" + filename), new DummyBillParser()))
                .isInstanceOf(RuntimeException.class);
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
            root.getElementsByTagName("name").item(0).getTextContent();
            return new DummyBill().setName(root.getElementsByTagName("name").item(0).getTextContent().trim());
        }
    }
}
