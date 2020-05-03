package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class Address {
    private String country;
    private String city;
    private String postCode;
    private String street;
    private String houseNumber;
}
