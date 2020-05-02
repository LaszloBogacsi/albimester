package com.laszlobogacsi.albimester.parser;

import org.w3c.dom.Element;

import java.io.InputStream;

public interface DocumentParser {
    Element parse(InputStream xmlString);
}
