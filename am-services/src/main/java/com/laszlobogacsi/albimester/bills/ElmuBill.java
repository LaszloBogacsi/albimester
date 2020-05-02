package com.laszlobogacsi.albimester.bills;

import com.laszlobogacsi.albimester.parser.Bill;
import lombok.Builder;

@Builder
public class ElmuBill implements Bill {
    private ElmuBillHeader header;
    private ElmuBillItems items;
    private ElmuBillDetails details;
    private ElmuBillSummary summary;
}
