package com.laszlobogacsi.albimester.persistance;

import com.laszlobogacsi.albimester.bills.elmu.*;
import com.laszlobogacsi.albimester.persistance.bill.BillData;
import com.laszlobogacsi.albimester.persistance.bill.BillRepository;
import com.laszlobogacsi.albimester.persistance.bill.BillTransformer;
import com.laszlobogacsi.albimester.persistance.bill.BillType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class ElmuBillRepositoryTest {

    @Autowired
    BillRepository repository;
    BillData elmuBillData;

    @BeforeEach
    void setUp() {
        ElmuBill bill = getElmuBill();
        elmuBillData = new BillData(BillType.ELMU, BillTransformer.transformToJson(bill));

    }

    @Test
    void canFindASavedAnElmuBillById() {
        elmuBillData = repository.save(elmuBillData);
        assertThat(repository.findById(elmuBillData.getId())).hasValue(elmuBillData);
    }

    private ElmuBill getElmuBill() {
        return ElmuBill.builder()
                .header(buildBillHeader().build())
                .items(buildBillItems().build())
                .summary(buildBillSummary().build())
                .build();
    }

    private ElmuBillHeader.ElmuBillHeaderBuilder buildBillHeader() {
        return ElmuBillHeader.builder()
                .sellerInfo(buildSellerInfo().build())
                .buyerInfo(buildBuyerInfo().build())
                .invoiceInfo(buildInvoiceInfo().build());
    }

    private InvoiceInfo.InvoiceInfoBuilder buildInvoiceInfo() {
        return InvoiceInfo.builder().serialNumber("842402177280").createdDate("2020.03.20").payedDate("2020.04.06").payByDate("2020.04.06").payMethod("Egyedi utalás (Saját E-számla)").invoiceType("Villamos energia elszámoló számla").currency("HUF").originBankAccount("10700024-69302288-51200002").invoicedService("Villamos energia egyetemes szolgáltatás és villamosenergia-elosztás");
    }

    private BuyerInfo.BuyerInfoBuilder buildBuyerInfo() {
        return BuyerInfo.builder()
                .name("Bogácsi László")
                .address(Address.builder().country("Magyarország").city("Törökbálint").postCode("2045").street("BEM JÓZSEF utca").houseNumber("14").build());
    }

    private SellerInfo.SellerInfoBuilder buildSellerInfo() {
        return SellerInfo.builder()
                .name("ELMŰ-ÉMÁSZ Energiaszolgáltató Zrt.").taxReference("25366936-2-44").address(Address.builder().country("Magyarország").city("Budapest").postCode("1132").street("Váci út 72-74.").build());
    }

    private ElmuBillSummary.ElmuBillSummaryBuilder buildBillSummary() {
        return ElmuBillSummary.builder()
                .vatSections(Arrays.asList(
                        VatSection.builder().id("1").vatPercentage("27").netPrice("2859").vatValue("772").grossPrice("3631").timeRange(TimeRange.builder().from("2019.03.12-2020.03.03").build()).build(),
                        VatSection.builder().id("2").vatPercentage("27").netPrice("3221").vatValue("870").grossPrice("4091").timeRange(TimeRange.builder().from("2019.03.12-2020.03.03").build()).build()
                ))
                .toPay(ToPay.builder().payTotal("7722").build())
                .sumTotal(SumTotal.builder().netPriceTotal("6080").vatValueTotal("1642").grossPriceTotal("7722").build());
    }

    private ElmuBillItems.ElmuBillItemsBuilder buildBillItems() {
        return ElmuBillItems.builder()
                .items(Arrays.asList(
                        ElmuBillItem.builder().id("1").productName("Fogyasztási hely azonosító: 20630373").build(),
                        ElmuBillItem.builder().id("2").productName("Fizetendő összeg összesen").grossPrice("7722").build()

                ));
    }
}
