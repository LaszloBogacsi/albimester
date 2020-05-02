package com.laszlobogacsi.albimester.parser;

import org.w3c.dom.Element;

public interface BillParser<T> {
   T parseBill(Element root);
}
