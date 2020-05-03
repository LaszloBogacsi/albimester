package com.laszlobogacsi.albimester.bills;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.List;

@Builder
@EqualsAndHashCode
public class ElmuBillItems {
    private List<ElmuBillItem> items;
}
