package com.laszlobogacsi.albimester.persistance;

import com.google.gson.Gson;
import com.laszlobogacsi.albimester.parser.Bill;

public class BillTransformer {
    static Gson GSON = new Gson();

    public static String transformToJson(Bill bill) {
        return GSON.toJson(bill);
    }
}
