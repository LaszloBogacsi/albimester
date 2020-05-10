package com.laszlobogacsi.albimester.bills.elmu;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Builder
@EqualsAndHashCode
@ToString
public class InvoiceInfo {
    private String serialNumber;
    private String createdDate;
    private String payedDate;
    private String payByDate;
    private String payMethod;
    private String invoiceType;
    private String currency;
    private String originBankAccount;
    private String invoicedService;
}
