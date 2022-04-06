import entity.BankDeposit;
import entity.FixedTermedDeposit;
import entity.IndividualDeposit;
import entity.LegalEntityDeposit;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class BankDepositHandler extends DefaultHandler {

    private final static Logger LOGGER = Logger.getLogger(BankDepositHandler.class);

    private List<BankDeposit> bankDeposits;
    private BankDeposit current = null;
    private BankDepositEnum currentEnum = null;
    private EnumSet<BankDepositEnum> withText;

    public BankDepositHandler() {
        bankDeposits = new ArrayList<BankDeposit>();
        withText = EnumSet.range(BankDepositEnum.BANK_NAME, BankDepositEnum.NUMBER_OF_FOUNDERS);
    }

    public List<BankDeposit> getBankDeposits() {
        return bankDeposits;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if ("individual-deposit".equals(localName)) {
            current = new IndividualDeposit();
            current.setId(attrs.getValue(0));
            if (attrs.getLength() == 3) {
                current.setCountry(attrs.getValue(1));
                current.setCurrency(attrs.getValue(2));
            }
            if (attrs.getLength() == 2) {
                current.setCurrency(attrs.getValue(1));
            }

        } else if ("fixed-termed-deposit".equals(localName)) {
            current = new FixedTermedDeposit();
            current.setId(attrs.getValue(0));
            if (attrs.getLength() == 3) {
                current.setCountry(attrs.getValue(1));
                current.setCurrency(attrs.getValue(2));
            }
            if (attrs.getLength() == 2) {
                current.setCurrency(attrs.getValue(1));
            }
        } else if ("legal-entity-deposit".equals(localName)) {
            current = new LegalEntityDeposit();
            current.setId(attrs.getValue(0));
            if (attrs.getLength() == 3) {
                current.setCountry(attrs.getValue(1));
                current.setCurrency(attrs.getValue(2));
            }
            if (attrs.getLength() == 2) {
                current.setCurrency(attrs.getValue(1));
            }
        } else {
            BankDepositEnum temp = BankDepositEnum.valueOf(localName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if ("individual-deposit".equals(localName)) {
            bankDeposits.add(current);
        } else if ("fixed-termed-deposit".equals(localName)) {
            bankDeposits.add(current);
        } else if ("legal-entity-deposit".equals(localName)) {
            bankDeposits.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case BANK_NAME:
                    current.setBankName(s);
                    break;
                case AMOUNT:
                    current.setAmount(Integer.parseInt(s));
                    break;
                case PERCENT:
                    current.setPercent(Integer.parseInt(s));
                    break;
                case DEPOSITOR_NAME:
                    IndividualDeposit individualDeposit = (IndividualDeposit) current;
                    individualDeposit.setDepositorName(s);
                    break;
                case DEPOSITOR_SURNAME:
                    IndividualDeposit individualDeposit_1 = (IndividualDeposit) current;
                    individualDeposit_1.setDepositorSurname(s);
                    break;
                case PASSPORT_NUMBER:
                    IndividualDeposit individualDeposit_2 = (IndividualDeposit) current;
                    individualDeposit_2.setPassportNumber(s);
                    break;
                case NUMBER_OF_YEARS:
                    FixedTermedDeposit fixedTermedDeposit = (FixedTermedDeposit) current;
                    fixedTermedDeposit.setNumberOfYears(Integer.parseInt(s));
                    break;
                case COMPANY_NAME:
                    LegalEntityDeposit legalEntityDeposit = (LegalEntityDeposit) current;
                    legalEntityDeposit.setCompanyName(s);
                    break;
                case NUMBER_OF_FOUNDERS:
                    LegalEntityDeposit legalEntityDeposit_1 = (LegalEntityDeposit) current;
                    legalEntityDeposit_1.setNumberOfNumbers(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }


}
