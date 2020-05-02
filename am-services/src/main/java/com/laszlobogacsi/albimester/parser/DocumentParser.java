package com.laszlobogacsi.albimester.parser;

import org.w3c.dom.Element;

import java.io.InputStream;
import java.util.Optional;

public interface DocumentParser {
    Optional<Element> parse(InputStream xmlString);
}
