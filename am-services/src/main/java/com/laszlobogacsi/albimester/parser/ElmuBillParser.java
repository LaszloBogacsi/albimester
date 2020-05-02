package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ElmuBillParser implements BillParser<ElmuBill> {
    @Override
    public ElmuBill parseBill(Element root) {
        final NodeList fejlec = root.getElementsByTagName("fejlec").item(0).getChildNodes();
        final ElmuBillHeader.ElmuBillHeaderBuilder headerBuilder = ElmuBillHeader.builder();

        for (int i = 0; i < fejlec.getLength(); i++) {
            Node node = fejlec.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if ("elado".equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    final SellerInfo.SellerInfoBuilder sellerInfoBuilder = SellerInfo.builder()
                            .name(element.getElementsByTagName("nev").item(0).getTextContent())
                            .taxReference(element.getElementsByTagName("adoszam").item(0).getTextContent());
                    final Element cim = (Element) element.getElementsByTagName("cim").item(0);
                    final Address address = Address.builder()
                            .country(cim.getElementsByTagName("orszag").item(0).getTextContent())
                            .city(cim.getElementsByTagName("telepules").item(0).getTextContent())
                            .postCode(cim.getElementsByTagName("irszam").item(0).getTextContent())
                            .street(cim.getElementsByTagName("kozternev").item(0).getTextContent())
                            .build();
                    headerBuilder.sellerInfo(sellerInfoBuilder.address(address).build());

                }

                if ("vevo".equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    final BuyerInfo.BuyerInfoBuilder buyerInfoBuilder = BuyerInfo.builder()
                            .name(element.getElementsByTagName("nev").item(0).getTextContent());
                    final Element cim = (Element) element.getElementsByTagName("cim").item(0);
                    final Address address = Address.builder()
                            .country(cim.getElementsByTagName("orszag").item(0).getTextContent())
                            .city(cim.getElementsByTagName("telepules").item(0).getTextContent())
                            .postCode(cim.getElementsByTagName("irszam").item(0).getTextContent())
                            .street(cim.getElementsByTagName("kozternev").item(0).getTextContent())
                            .houseNumber(cim.getElementsByTagName("hazszam").item(0).getTextContent())
                            .build();
                    headerBuilder.buyerInfo(buyerInfoBuilder.address(address).build());
                }

                if ("szamlainfo".equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    headerBuilder.invoiceInfo(InvoiceInfo.builder()
                            .serialNumber(element.getElementsByTagName("sorszam").item(0).getTextContent())
                            .createdDate(element.getElementsByTagName("kialldatum").item(0).getTextContent())
                            .payedDate(element.getElementsByTagName("teljdatum").item(0).getTextContent())
                            .payByDate(element.getElementsByTagName("fizhatarido").item(0).getTextContent())
                            .payMethod(element.getElementsByTagName("fizmod").item(0).getTextContent())
                            .invoiceType(element.getElementsByTagName("szamlatipus").item(0).getTextContent())
                            .currency(element.getElementsByTagName("penznem").item(0).getTextContent())
                            .originBankAccount(element.getElementsByTagName("kibocsato_bankszamlaszama").item(0).getTextContent())
                            .invoicedService(element.getElementsByTagName("szamlazott_szolgaltatas").item(0).getTextContent())
                    .build());
                }
            }

        }
        return ElmuBill.builder().header(headerBuilder.build()).build();
    }
}
