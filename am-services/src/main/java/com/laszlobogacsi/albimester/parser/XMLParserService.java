package com.laszlobogacsi.albimester.parser;

import java.io.InputStream;

public class XMLParserService {
    private final DocumentParser parser;

    public XMLParserService(DocumentParser parser) {
        this.parser = parser;
    }

    public <T extends Bill> T parseInput(InputStream inputStream, BillParser<T> billParser) {
        return billParser.parseBill(parser.parse(inputStream));
    }
}
