package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.ElmuBill;
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
        Bill expectedElmuBill = ElmuBill.builder().build();

        assertThat(elmuBill).isEqualToComparingFieldByField(expectedElmuBill);
    }
}
