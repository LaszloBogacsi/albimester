package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.elmu.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ElmuBillParserTest {
    @Test
    void canParseElmuBill() throws IOException {
        BillParser<ElmuBill> billParser = new ElmuBillParser();
        Path path = Paths.get("src/test/resources/2600279905_842402177280_20200502_193320_elmu.xml");
        final InputStream inputStream = Files.newInputStream(path);
        final Element root = new XMLDocumentParser().parse(inputStream);
        final Bill elmuBill = billParser.parseBill(root);

        Bill expectedElmuBill = ElmuBill.builder()
                .header(ElmuBillHeader.builder()
                        .sellerInfo
                                (SellerInfo.builder()
                                        .name("ELMŰ-ÉMÁSZ Energiaszolgáltató Zrt.")
                                        .taxReference("25366936-2-44")
                                        .address(Address.builder()
                                                .country("Magyarország")
                                                .city("Budapest")
                                                .postCode("1132")
                                                .street("Váci út 72-74.")
                                                .build())
                                        .build())
                        .buyerInfo(BuyerInfo.builder()
                                .name("Bogácsi László")
                                .address(Address.builder()
                                        .country("Magyarország")
                                        .city("Törökbálint")
                                        .postCode("2045")
                                        .street("BEM JÓZSEF utca")
                                        .houseNumber("14")
                                        .build())
                                .build())
                        .invoiceInfo(InvoiceInfo.builder()
                                .serialNumber("842402177280")
                                .createdDate("2020.03.20")
                                .payedDate("2020.04.06")
                                .payByDate("2020.04.06")
                                .payMethod("Egyedi utalás (Saját E-számla)")
                                .invoiceType("Villamos energia elszámoló számla")
                                .currency("HUF")
                                .originBankAccount("10700024-69302288-51200002")
                                .invoicedService("Villamos energia egyetemes szolgáltatás és villamosenergia-elosztás")
                                .build())
                        .build())
                .items(ElmuBillItems.builder()
                        .items(Arrays.asList(
                                ElmuBillItem.builder().id("1").productName("Fogyasztási hely azonosító: 20630373").build(),
                                ElmuBillItem.builder().id("2").productName("ESZ Lakossági \"A1\" kedv. árszabás díja").unit("kWh").amount("1067").netUnitPrice("14,0600").netPrice("15002").vatPercentage("27").grossPrice("19053").timeRange(TimeRange.builder().from("2019.03.12-2019.12.31").build()).build(),
                                ElmuBillItem.builder().id("3").productName("ESZ Lakossági \"A1\" kedv. árszabás díja").unit("kWh").amount("228").netUnitPrice("12,6200").netPrice("2877").vatPercentage("27").grossPrice("3654").timeRange(TimeRange.builder().from("2020.01.01-2020.03.03").build()).build(),
                                ElmuBillItem.builder().id("4").productName("ESZ Lakossági \"A1\" normál árszabás díja").unit("kWh").amount("460").netUnitPrice("15,1000").netPrice("6946").vatPercentage("27").grossPrice("8821").timeRange(TimeRange.builder().from("2019.03.12-2019.12.31").build()).build(),
                                ElmuBillItem.builder().id("5").productName("ESZ Lakossági \"A1\" normál árszabás díja").unit("kWh").amount("99").netUnitPrice("13,6600").netPrice("1352").vatPercentage("27").grossPrice("1717").timeRange(TimeRange.builder().from("2020.01.01-2020.03.03").build()).build(),
                                ElmuBillItem.builder().id("6").productName("Energiadíj összesen").netPrice("26177").grossPrice("33245").build(),
                                ElmuBillItem.builder().id("7").productName("Részszámlákban elszámolt energiadíj").netPrice("-23318").grossPrice("-29613").build(),
                                ElmuBillItem.builder().id("8").productName("Fizetendő energiadíj").netPrice("2859").grossPrice("3632").build(),
                                ElmuBillItem.builder().id("9").productName("Energiaalapú rendszerhasználati díj \"A1\"").unit("kWh").amount("1527").netUnitPrice("14,4650").netPrice("22088").vatPercentage("27").grossPrice("28052").timeRange(TimeRange.builder().from("2019.03.12-2019.12.31").build()).build(),
                                ElmuBillItem.builder().id("10").productName("Energiaalapú rendszerhasználati díj \"A1\"").unit("kWh").amount("327").netUnitPrice("15,9000").netPrice("5199").vatPercentage("27").grossPrice("6603").timeRange(TimeRange.builder().from("2020.01.01-2020.03.03").build()).build(),
                                ElmuBillItem.builder().id("11").productName("Elosztói alapdíj \"A1\"*").unit("db").amount("1").netUnitPrice("120,5000").netPrice("1085").vatPercentage("27").grossPrice("1378").timeRange(TimeRange.builder().from("2019.03.12-2019.12.31").build()).build(),
                                ElmuBillItem.builder().id("12").productName("Elosztói alapdíj \"A1\"*").unit("db").amount("1").netUnitPrice("120,5000").netPrice("362").vatPercentage("27").grossPrice("460").timeRange(TimeRange.builder().from("2020.01.01-2020.03.03").build()).build(),
                                ElmuBillItem.builder().id("13").productName("Rendszerhasználati díjak összesen").netPrice("28735").grossPrice("36493").build(),
                                ElmuBillItem.builder().id("14").productName("Részszámlákban elszámolt rendszerhasználati díjak").netPrice("-25514").grossPrice("-32406").build(),
                                ElmuBillItem.builder().id("15").productName("Fizetendő rendszerhasználati díjak").netPrice("3221").grossPrice("4087").build(),
                                ElmuBillItem.builder().id("16").productName("Fizetési felszólítás díja").unit("db").amount("1").netUnitPrice("175,0000").netPrice("175").vatPercentage("27").grossPrice("222").timeRange(TimeRange.builder().from("2019.07.19-2019.08.20").build()).build(),
                                ElmuBillItem.builder().id("17").productName("Fizetési felszólítás díja").unit("db").amount("1").netUnitPrice("175,0000").netPrice("175").vatPercentage("27").grossPrice("222").timeRange(TimeRange.builder().from("2019.10.18-2019.11.17").build()).build(),
                                ElmuBillItem.builder().id("18").productName("Felszólítási díjak összesen").netPrice("350").grossPrice("444").build(),
                                ElmuBillItem.builder().id("19").productName("Részszámlákban elszámolt felszólítási díjak").netPrice("-350").grossPrice("-444").build(),
                                ElmuBillItem.builder().id("20").productName("Fizetendő felszólítási díjak").netPrice("0").grossPrice("0").build(),
                                ElmuBillItem.builder().id("21").productName("Nettó számlaérték összesen").netPrice("6080").build(),
                                ElmuBillItem.builder().id("22").productName("Kerekítés").netPrice("1").grossPrice("3").build(),
                                ElmuBillItem.builder().id("23").productName("Bruttó számlaérték összesen**").grossPrice("7722").build(),
                                ElmuBillItem.builder().id("24").productName("Fizetendő összeg összesen").grossPrice("7722").build()

                                )).build())
                .summary(ElmuBillSummary.builder()
                        .vatSections(Arrays.asList(
                                VatSection.builder().id("1").vatPercentage("27").netPrice("2859").vatValue("772").grossPrice("3631").timeRange(TimeRange.builder().from("2019.03.12-2020.03.03").build()).build(),
                                VatSection.builder().id("2").vatPercentage("27").netPrice("3221").vatValue("870").grossPrice("4091").timeRange(TimeRange.builder().from("2019.03.12-2020.03.03").build()).build()
                        ))
                        .toPay(ToPay.builder().payTotal("7722").build())
                        .sumTotal(SumTotal.builder().netPriceTotal("6080").vatValueTotal("1642").grossPriceTotal("7722").build())
                        .build())
                .build();

        assertThat(elmuBill).isEqualToComparingFieldByField(expectedElmuBill);
    }
}
