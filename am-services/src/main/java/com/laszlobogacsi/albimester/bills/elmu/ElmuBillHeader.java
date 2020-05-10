package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class ElmuBillHeader {
    private SellerInfo sellerInfo;
    private BuyerInfo buyerInfo;
    private InvoiceInfo invoiceInfo;
}
