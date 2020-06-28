package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.persistance.bill.BillType;

public class BillParserFactory {
    public static BillParser getParser(BillType billType) {
        switch (billType) {
            case ELMU:
                return new ElmuBillParser();
            case FOTAV:
                throw new UnsupportedOperationException();
            default:
                throw new IllegalStateException("Unexpected value: " + billType);
        }
    }
}
