package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.persistance.BillType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class BillParserFactory {
    public static BillParser getParser(BillType billType) {
        switch (billType) {
            case ELMU:
                return new ElmuBillParser();
            case FOTAV:
                throw  new NotImplementedException();
            default:
                throw new IllegalStateException("Unexpected value: " + billType);
        }
    }
}
