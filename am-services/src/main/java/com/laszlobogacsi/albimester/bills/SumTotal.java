package com.laszlobogacsi.albimester.bills;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class SumTotal {
    private String netPriceTotal;
    private String vatValueTotal;
    private String grossPriceTotal;
}
