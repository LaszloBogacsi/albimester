package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.ElmuBill;
import org.w3c.dom.Element;

public class ElmuBillParser implements BillParser<ElmuBill> {
    @Override
    public ElmuBill parseBill(Element root) {
        return ElmuBill.builder().build();
    }
}
