package by.home;

import by.home.entity.BankDeposit;
import by.home.entity.FixedTermedDeposit;
import by.home.entity.IndividualDeposit;
import by.home.entity.LegalEntityDeposit;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {

    private final static Logger LOGGER = Logger.getLogger(DomParser.class);

    private List<BankDeposit> bankDeposits;
    private DocumentBuilder docBuilder;

    public DomParser() {
        this.bankDeposits = new ArrayList<BankDeposit>();
        // создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Ошибка конфигурации парсера: " + e);
        }
    }

    public List<BankDeposit> getBankDeposits() {
        return bankDeposits;
    }

    @Override
    public List<BankDeposit> parse(String file) {
        Document doc = null;
        try {
            doc = docBuilder.parse(file);
            Element root = doc.getDocumentElement();

            NodeList individualDepositsList = root.getElementsByTagName(BankDepositEnum.INDIVIDUAL_DEPOSIT.getValue());
            for (int i = 0; i < individualDepositsList.getLength(); i++) {
                Element individualDepositElement = (Element) individualDepositsList.item(i);
                BankDeposit bankDeposit = buildIndividualDeposit(individualDepositElement);
                bankDeposits.add(bankDeposit);
            }

            NodeList legalEntityDepositList = root.getElementsByTagName(BankDepositEnum.LEGAL_ENTITY_DEPOSIT.getValue());
            for (int i = 0; i < legalEntityDepositList.getLength(); i++) {
                Element legalEntityDepositElement = (Element) legalEntityDepositList.item(i);
                BankDeposit bankDeposit = buildLegalEntityDeposit(legalEntityDepositElement);
                bankDeposits.add(bankDeposit);
            }

            NodeList FixedTermedDepositList = root.getElementsByTagName(BankDepositEnum.FIXED_TERMED_DEPOSIT.getValue());
            for (int i = 0; i < FixedTermedDepositList.getLength(); i++) {
                Element fixedTermedDepositElement = (Element) FixedTermedDepositList.item(i);
                BankDeposit bankDeposit = buildFixedTermedDeposit(fixedTermedDepositElement);
                bankDeposits.add(bankDeposit);
            }

        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
        return bankDeposits;
    }



    private void buildBankDeposit(BankDeposit bankDeposit,Element bankDepositElement ) {

        bankDeposit.setId(bankDepositElement.getAttribute(BankDepositEnum.ID.getValue()));

        if (!bankDepositElement.getAttribute(BankDepositEnum.COUNTRY.getValue()).isEmpty()) {
            bankDeposit.setCountry(bankDepositElement.getAttribute(BankDepositEnum.COUNTRY.getValue()));
        }
        bankDeposit.setCurrency(bankDepositElement.getAttribute(BankDepositEnum.CURRENCY.getValue()));

        bankDeposit.setBankName(getElementTextContent(bankDepositElement, BankDepositEnum.BANK_NAME.getValue()));

        Integer amount = Integer.parseInt(getElementTextContent(bankDepositElement,
                BankDepositEnum.AMOUNT.getValue()));
        bankDeposit.setAmount(amount);

        Integer percent = Integer.parseInt(getElementTextContent(bankDepositElement,
                BankDepositEnum.PERCENT.getValue()));
        bankDeposit.setPercent(percent);
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

    private IndividualDeposit buildIndividualDeposit(Element individualDepositElement) {

        IndividualDeposit individualDeposit = new IndividualDeposit();

        buildBankDeposit(individualDeposit,individualDepositElement);

        individualDeposit.setDepositorName(getElementTextContent(individualDepositElement, BankDepositEnum.DEPOSITOR_NAME.getValue()));
        individualDeposit.setDepositorSurname(getElementTextContent(individualDepositElement, BankDepositEnum.DEPOSITOR_SURNAME.getValue()));
        individualDeposit.setPassportNumber(getElementTextContent(individualDepositElement, BankDepositEnum.PASSPORT_NUMBER.getValue()));

        return individualDeposit;

    }

    private FixedTermedDeposit buildFixedTermedDeposit(Element fixedTermedDepositElement) {

        FixedTermedDeposit fixedTermedDeposit = new FixedTermedDeposit();

        buildBankDeposit(fixedTermedDeposit,fixedTermedDepositElement);

        fixedTermedDeposit.setDepositorName(getElementTextContent(fixedTermedDepositElement, BankDepositEnum.DEPOSITOR_NAME.getValue()));
        fixedTermedDeposit.setDepositorSurname(getElementTextContent(fixedTermedDepositElement, BankDepositEnum.DEPOSITOR_SURNAME.getValue()));
        fixedTermedDeposit.setPassportNumber(getElementTextContent(fixedTermedDepositElement, BankDepositEnum.PASSPORT_NUMBER.getValue()));

        Integer numberOfYears = Integer.parseInt(getElementTextContent(fixedTermedDepositElement,
                BankDepositEnum.NUMBER_OF_YEARS.getValue()));
        fixedTermedDeposit.setNumberOfYears(numberOfYears);

        return fixedTermedDeposit;

    }

    private LegalEntityDeposit buildLegalEntityDeposit(Element legalEntityDepositElement) {

        LegalEntityDeposit legalEntityDeposit = new LegalEntityDeposit();

        buildBankDeposit(legalEntityDeposit,legalEntityDepositElement);

        legalEntityDeposit.setCompanyName(getElementTextContent(legalEntityDepositElement, BankDepositEnum.COMPANY_NAME.getValue()));

        Integer numberOfFounders = Integer.parseInt(getElementTextContent(legalEntityDepositElement,
                BankDepositEnum.NUMBER_OF_FOUNDERS.getValue()));
        legalEntityDeposit.setNumberOfNumbers(numberOfFounders);

        return legalEntityDeposit;

    }
}
