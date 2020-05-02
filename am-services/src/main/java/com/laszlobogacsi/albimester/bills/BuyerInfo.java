package com.laszlobogacsi.albimester.bills;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class BuyerInfo {
    private String name;
    private Address address;
}
