package com.laszlobogacsi.albimester.bills;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class SellerInfo {
    private String name;
    private String taxReference;
    private Address address;
}
