package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class BuyerInfo {
    private String name;
    private Address address;
}
