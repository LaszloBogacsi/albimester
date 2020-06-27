package com.laszlobogacsi.albimester.property.model;

import lombok.Data;

@Data
public class IncomingPropertyAddress {
    private final String city;
    private final String street;
    private final String postCode;
    private final String type;
    private final String houseNumber;
    private final String floor;
    private final String door;
    private final String hrsz;
}
