package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class ElmuBillItem {
    private String id;
    private String productName;
    private String unit;
    private String amount;
    private String netUnitPrice;
    private String netPrice;
    private String vatPercentage;
    private String grossPrice;
    private TimeRange timeRange;

}
