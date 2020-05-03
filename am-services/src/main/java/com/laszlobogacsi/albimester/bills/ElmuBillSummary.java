package com.laszlobogacsi.albimester.bills;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@EqualsAndHashCode
public class ElmuBillSummary {
    private List<VatSection> vatSections;
    private ToPay toPay;
    private SumTotal sumTotal;
}
