package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.persistance.BillType;

public class BillParserFactory {
    public static BillParser getParser(BillType billType) {
        switch (billType) {
            case ELMU:
                return new ElmuBillParser();
            default:
                throw new IllegalStateException("Unexpected value: " + billType);
        }
    }
}
