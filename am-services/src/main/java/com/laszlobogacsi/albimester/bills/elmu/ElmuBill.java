package com.laszlobogacsi.albimester.bills.elmu;

import com.laszlobogacsi.albimester.parser.Bill;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper=false)
public class ElmuBill extends Bill {
    private ElmuBillHeader header;
    private ElmuBillItems items;
//    private ElmuBillDetails details;
    private ElmuBillSummary summary;
}
