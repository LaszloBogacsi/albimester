package com.laszlobogacsi.albimester.bills;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode
public class ElmuBillHeader {
    private SellerInfo sellerInfo;
    private BuyerInfo buyerInfo;
    private InvoiceInfo invoiceInfo;
}
