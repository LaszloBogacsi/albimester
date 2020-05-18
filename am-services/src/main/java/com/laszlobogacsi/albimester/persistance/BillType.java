package com.laszlobogacsi.albimester.persistance;

public enum BillType {
    ELMU, FOTAV;

    public static BillType fromType(String type) {
        return BillType.valueOf(type);
    }
}
