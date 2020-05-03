package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class VatSection {
    private String id;
    private String vatPercentage;
    private String netPrice;
    private String vatValue;
    private String grossPrice;
    private TimeRange timeRange;

}
