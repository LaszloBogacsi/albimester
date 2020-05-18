package com.laszlobogacsi.albimester.parser;

import org.w3c.dom.Element;

public interface BillParser {
   Bill parseBill(Element root);
}
