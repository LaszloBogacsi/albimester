package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.*;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Element;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
                        .build()).build();

        assertThat(elmuBill).isEqualToComparingFieldByField(expectedElmuBill);
    }
}
