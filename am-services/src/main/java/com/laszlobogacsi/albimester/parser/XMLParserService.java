package com.laszlobogacsi.albimester.parser;

import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class XMLParserService {
    private final DocumentParser parser;

    public XMLParserService(DocumentParser parser) {
        this.parser = parser;
    }

    public Bill parseInput(InputStream inputStream, BillParser billParser) {
        return billParser.parseBill(parser.parse(inputStream));
    }
}
