package com.laszlobogacsi.albimester.parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class XMLParserService {
    private final DocumentParser parser;

    public XMLParserService(DocumentParser parser) {
        this.parser = parser;
    }


    public <T extends Bill> T parseFile(Path path, BillParser<T> billParser) {
        try (InputStream inputStream = Files.newInputStream(path)){
            return billParser.parseBill(parser.parse(inputStream));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open file: " + path.getFileName(), e);
        }
    }
}
