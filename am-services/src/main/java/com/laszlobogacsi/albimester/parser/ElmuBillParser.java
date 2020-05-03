package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.elmu.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                            .name(Optional.ofNullable(element.getElementsByTagName("nev").item(0)).map(Node::getTextContent).orElse(null))
                            .taxReference(Optional.ofNullable(element.getElementsByTagName("adoszam").item(0)).map(Node::getTextContent).orElse(null));
                    final Element cim = (Element) element.getElementsByTagName("cim").item(0);
                    final Address address = cim != null ? buildAddress(cim) : null;
                    headerBuilder.sellerInfo(sellerInfoBuilder.address(address).build());

                }

                if ("vevo".equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    final BuyerInfo.BuyerInfoBuilder buyerInfoBuilder = BuyerInfo.builder()
                            .name(Optional.ofNullable(element.getElementsByTagName("nev").item(0)).map(Node::getTextContent).orElse(null));
                    final Element cim = (Element) element.getElementsByTagName("cim").item(0);
                    final Address address = cim != null ? buildAddress(cim) : null;
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

        final NodeList tetelek = root.getElementsByTagName("tetelek").item(0).getChildNodes();
        List<ElmuBillItem> items = new ArrayList<>();
        for (int j = 0; j < tetelek.getLength(); j++) {
            Node nodeTetel = tetelek.item(j);
            if (nodeTetel.getNodeType() == Node.ELEMENT_NODE) {
                if ("tetel".equalsIgnoreCase(nodeTetel.getNodeName())) {
                    Element tetelElement = (Element) nodeTetel;
                    items.add(ElmuBillItem.builder()
                            .id(tetelElement.getAttribute("id"))
                            .productName(Optional.ofNullable(tetelElement.getElementsByTagName("termeknev").item(0)).map(Node::getTextContent).orElse(null))
                            .unit(Optional.ofNullable(tetelElement.getElementsByTagName("mennyegys").item(0)).map(Node::getTextContent).orElse(null))
                            .amount(Optional.ofNullable(tetelElement.getElementsByTagName("menny").item(0)).map(Node::getTextContent).orElse(null))
                            .netUnitPrice(Optional.ofNullable(tetelElement.getElementsByTagName("nettoegysegar").item(0)).map(Node::getTextContent).orElse(null))
                            .netPrice(Optional.ofNullable(tetelElement.getElementsByTagName("nettoar").item(0)).map(Node::getTextContent).orElse(null))
                            .vatPercentage(Optional.ofNullable(tetelElement.getElementsByTagName("afakulcs").item(0)).map(Node::getTextContent).orElse(null))
                            .grossPrice(Optional.ofNullable(tetelElement.getElementsByTagName("bruttoar").item(0)).map(Node::getTextContent).orElse(null))
                            .timeRange(Optional.ofNullable(tetelElement.getElementsByTagName("idoszak").item(0)).map(n -> TimeRange.builder()
                                    .from(Optional.ofNullable(((Element) n).getElementsByTagName("tol").item(0)).map(Node::getTextContent).orElse(null))
                                    .build()).orElse(null))
                            .build());
                }
            }
        }

        final NodeList sums = root.getElementsByTagName("osszesites").item(0).getChildNodes();
        List<VatSection> vatSections = new ArrayList<>();
        final ElmuBillSummary.ElmuBillSummaryBuilder elmuBillSummaryBuilder = ElmuBillSummary.builder();
        for (int j = 0; j < sums.getLength(); j++) {
            Node nodeSum = sums.item(j);
            if (nodeSum.getNodeType() == Node.ELEMENT_NODE) {
                if ("afarovat".equalsIgnoreCase(nodeSum.getNodeName())) {
                    Element afarovatElement = (Element) nodeSum;
                    vatSections.add(VatSection.builder()
                            .id(afarovatElement.getAttribute("id"))
                            .vatPercentage(Optional.ofNullable(afarovatElement.getElementsByTagName("afakulcs").item(0)).map(Node::getTextContent).orElse(null))
                            .netPrice(Optional.ofNullable(afarovatElement.getElementsByTagName("nettoar").item(0)).map(Node::getTextContent).orElse(null))
                            .vatValue(Optional.ofNullable(afarovatElement.getElementsByTagName("afaertek").item(0)).map(Node::getTextContent).orElse(null))
                            .grossPrice(Optional.ofNullable(afarovatElement.getElementsByTagName("bruttoar").item(0)).map(Node::getTextContent).orElse(null))
                            .timeRange(Optional.ofNullable(afarovatElement.getElementsByTagName("idoszak").item(0)).map(n -> TimeRange.builder()
                                    .from(Optional.ofNullable(((Element) n).getElementsByTagName("tol").item(0)).map(Node::getTextContent).orElse(null))
                                    .build()).orElse(null))
                            .build());
                }

                if ("fizetendo".equalsIgnoreCase(nodeSum.getNodeName())) {
                    Element fizetendoElement = (Element) nodeSum;
                    elmuBillSummaryBuilder.toPay(ToPay.builder()
                            .payTotal(Optional.ofNullable(fizetendoElement.getElementsByTagName("fizetendoossz").item(0)).map(Node::getTextContent).orElse(null))
                            .build());
                }

                if ("vegosszeg".equalsIgnoreCase(nodeSum.getNodeName())) {
                    Element sumTotal = (Element) nodeSum;
                    elmuBillSummaryBuilder.sumTotal(SumTotal.builder()
                            .netPriceTotal(Optional.ofNullable(sumTotal.getElementsByTagName("nettoarossz").item(0)).map(Node::getTextContent).orElse(null))
                            .vatValueTotal(Optional.ofNullable(sumTotal.getElementsByTagName("afaertekossz").item(0)).map(Node::getTextContent).orElse(null))
                            .grossPriceTotal(Optional.ofNullable(sumTotal.getElementsByTagName("bruttoarossz").item(0)).map(Node::getTextContent).orElse(null))
                            .build());
                }
            }
        }

        return ElmuBill.builder()
                .header(headerBuilder.build())
                .items(ElmuBillItems.builder().items(items).build())
                .summary(elmuBillSummaryBuilder.vatSections(vatSections).build())
                .build();
    }

    private Address buildAddress(Element cim) {
        return Address.builder()
                .country(Optional.ofNullable(cim.getElementsByTagName("orszag").item(0)).map(Node::getTextContent).orElse(null))
                .city(Optional.ofNullable(cim.getElementsByTagName("telepules").item(0)).map(Node::getTextContent).orElse(null))
                .postCode(Optional.ofNullable(cim.getElementsByTagName("irszam").item(0)).map(Node::getTextContent).orElse(null))
                .street(Optional.ofNullable(cim.getElementsByTagName("kozternev").item(0)).map(Node::getTextContent).orElse(null))
                .houseNumber(Optional.ofNullable(cim.getElementsByTagName("hazszam").item(0)).map(Node::getTextContent).orElse(null))
                .build();
    }
}
