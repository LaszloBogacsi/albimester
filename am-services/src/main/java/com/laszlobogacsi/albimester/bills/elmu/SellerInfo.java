package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class SellerInfo {
    private String name;
    private String taxReference;
    private Address address;
}
