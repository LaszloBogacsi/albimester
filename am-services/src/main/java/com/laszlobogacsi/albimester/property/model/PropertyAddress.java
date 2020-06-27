package com.laszlobogacsi.albimester.property.model;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class PropertyAddress {
    private final String city;
    private final String street;
    private final String postCode;
    private final String type;
    private final String houseNumber;
    private final Optional<String> floor;
    private final Optional<String> door;
    private final Optional<String> hrsz;
}
