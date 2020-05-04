package com.laszlobogacsi.albimester.parser;

import com.laszlobogacsi.albimester.bills.elmu.*;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ElmuBillParser implements BillParser<ElmuBill> {

    public static final String FEJLEC = "fejlec";
    public static final String ELADO = "elado";
    public static final String NEV = "nev";
    public static final String ADOSZAM = "adoszam";
    public static final String CIM = "cim";
    public static final String VEVO = "vevo";
    public static final String SZAMLAINFO = "szamlainfo";
    public static final String SORSZAM = "sorszam";
    public static final String KIALLDATUM = "kialldatum";
    public static final String TELJDATUM = "teljdatum";
    public static final String FIZHATARIDO = "fizhatarido";
    public static final String FIZMOD = "fizmod";
    public static final String SZAMLATIPUS = "szamlatipus";
    public static final String PENZNEM = "penznem";
    public static final String KIBOCSATO_BANKSZAMLASZAMA = "kibocsato_bankszamlaszama";
    public static final String SZAMLAZOTT_SZOLGALTATAS = "szamlazott_szolgaltatas";
    public static final String TETELEK = "tetelek";
    public static final String TETEL = "tetel";
    public static final String ID = "id";
    public static final String TERMEKNEV = "termeknev";
    public static final String MENNYEGYS = "mennyegys";
    public static final String MENNY = "menny";
    public static final String NETTOEGYSEGAR = "nettoegysegar";
    public static final String NETTOAR = "nettoar";
    public static final String AFAKULCS = "afakulcs";
    public static final String BRUTTOAR = "bruttoar";
    public static final String IDOSZAK = "idoszak";
    public static final String TOL = "tol";
    public static final String OSSZESITES = "osszesites";
    public static final String AFAROVAT = "afarovat";
    public static final String AFAERTEK = "afaertek";
    public static final String FIZETENDO = "fizetendo";
    public static final String FIZETENDOOSSZ = "fizetendoossz";
    public static final String VEGOSSZEG = "vegosszeg";
    public static final String NETTOAROSSZ = "nettoarossz";
    public static final String AFAERTEKOSSZ = "afaertekossz";
    public static final String BRUTTOAROSSZ = "bruttoarossz";
    public static final String ORSZAG = "orszag";
    public static final String TELEPULES = "telepules";
    public static final String IRSZAM = "irszam";
    public static final String KOZTERNEV = "kozternev";
    public static final String HAZSZAM = "hazszam";

    @Override
    public ElmuBill parseBill(Element root) {
        final NodeList fejlec = root.getElementsByTagName(FEJLEC).item(0).getChildNodes();
        final ElmuBillHeader.ElmuBillHeaderBuilder headerBuilder = ElmuBillHeader.builder();

        for (int i = 0; i < fejlec.getLength(); i++) {
            Node node = fejlec.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                if (ELADO.equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    final SellerInfo.SellerInfoBuilder sellerInfoBuilder = SellerInfo.builder().
                            name(getTextContent(element, NEV))
                            .taxReference(getTextContent(element, ADOSZAM));
                    final Element cim = (Element) element.getElementsByTagName(CIM).item(0);
                    final Address address = cim != null ? buildAddress(cim) : null;

                    headerBuilder.sellerInfo(sellerInfoBuilder.address(address).build());
                }

                if (VEVO.equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    final BuyerInfo.BuyerInfoBuilder buyerInfoBuilder = BuyerInfo.builder().name(getTextContent(element, NEV));
                    final Element cim = (Element) element.getElementsByTagName(CIM).item(0);
                    final Address address = cim != null ? buildAddress(cim) : null;
                    headerBuilder.buyerInfo(buyerInfoBuilder.address(address).build());
                }

                if (SZAMLAINFO.equalsIgnoreCase(node.getNodeName())) {
                    Element element = (Element) node;
                    headerBuilder.invoiceInfo(InvoiceInfo.builder()
                            .serialNumber(getTextContent(element, SORSZAM))
                            .createdDate(getTextContent(element, KIALLDATUM))
                            .payedDate(getTextContent(element, TELJDATUM))
                            .payByDate(getTextContent(element, FIZHATARIDO))
                            .payMethod(getTextContent(element, FIZMOD))
                            .invoiceType(getTextContent(element, SZAMLATIPUS))
                            .currency(getTextContent(element, PENZNEM))
                            .originBankAccount(getTextContent(element, KIBOCSATO_BANKSZAMLASZAMA))
                            .invoicedService(getTextContent(element, SZAMLAZOTT_SZOLGALTATAS))
                            .build());
                }
            }
        }

        final NodeList tetelek = root.getElementsByTagName(TETELEK).item(0).getChildNodes();
        List<ElmuBillItem> items = new ArrayList<>();
        for (int j = 0; j < tetelek.getLength(); j++) {
            Node nodeTetel = tetelek.item(j);
            if (nodeTetel.getNodeType() == Node.ELEMENT_NODE) {
                if (TETEL.equalsIgnoreCase(nodeTetel.getNodeName())) {
                    Element tetelElement = (Element) nodeTetel;
                    items.add(ElmuBillItem.builder()
                            .id(tetelElement.getAttribute(ID))
                            .productName(getTextContent(tetelElement, TERMEKNEV))
                            .unit(getTextContent(tetelElement, MENNYEGYS))
                            .amount(getTextContent(tetelElement, MENNY))
                            .netUnitPrice(getTextContent(tetelElement, NETTOEGYSEGAR))
                            .netPrice(getTextContent(tetelElement, NETTOAR))
                            .vatPercentage(getTextContent(tetelElement, AFAKULCS))
                            .grossPrice(getTextContent(tetelElement, BRUTTOAR))
                            .timeRange(Optional.ofNullable(tetelElement.getElementsByTagName(IDOSZAK).item(0)).map(n -> TimeRange.builder()
                                    .from(getTextContent((Element) n, TOL))
                                    .build()).orElse(null))
                            .build());
                }
            }
        }

        final NodeList sums = root.getElementsByTagName(OSSZESITES).item(0).getChildNodes();
        List<VatSection> vatSections = new ArrayList<>();
        final ElmuBillSummary.ElmuBillSummaryBuilder elmuBillSummaryBuilder = ElmuBillSummary.builder();
        for (int j = 0; j < sums.getLength(); j++) {
            Node nodeSum = sums.item(j);
            if (nodeSum.getNodeType() == Node.ELEMENT_NODE) {
                if (AFAROVAT.equalsIgnoreCase(nodeSum.getNodeName())) {
                    Element afarovatElement = (Element) nodeSum;
                    vatSections.add(VatSection.builder()
                            .id(afarovatElement.getAttribute(ID))
                            .vatPercentage(getTextContent(afarovatElement, AFAKULCS))
                            .netPrice(getTextContent(afarovatElement, NETTOAR))
                            .vatValue(getTextContent(afarovatElement, AFAERTEK))
                            .grossPrice(getTextContent(afarovatElement, BRUTTOAR))
                            .timeRange(Optional.ofNullable(afarovatElement.getElementsByTagName(IDOSZAK).item(0)).map(n -> TimeRange.builder()
                                    .from(getTextContent((Element) n, TOL))
                                    .build()).orElse(null))
                            .build());
                }

                if (FIZETENDO.equalsIgnoreCase(nodeSum.getNodeName())) {
                    Element fizetendoElement = (Element) nodeSum;
                    elmuBillSummaryBuilder.toPay(ToPay.builder().payTotal(getTextContent(fizetendoElement, FIZETENDOOSSZ)).build());
                }

                if (VEGOSSZEG.equalsIgnoreCase(nodeSum.getNodeName())) {
                    Element sumTotal = (Element) nodeSum;
                    elmuBillSummaryBuilder.sumTotal(SumTotal.builder()
                            .netPriceTotal(getTextContent(sumTotal, NETTOAROSSZ))
                            .vatValueTotal(getTextContent(sumTotal, AFAERTEKOSSZ))
                            .grossPriceTotal(getTextContent(sumTotal, BRUTTOAROSSZ))
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

    private String getTextContent(Element element, String tagName) {
        return Optional.ofNullable(element.getElementsByTagName(tagName).item(0)).map(Node::getTextContent).orElse(null);
    }

    private Address buildAddress(Element cim) {
        return Address.builder()
                .country(getTextContent(cim, ORSZAG))
                .city(getTextContent(cim, TELEPULES))
                .postCode(getTextContent(cim, IRSZAM))
                .street(getTextContent(cim, KOZTERNEV))
                .houseNumber(getTextContent(cim, HAZSZAM))
                .build();
    }
}
