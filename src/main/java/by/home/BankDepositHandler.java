package by.home;

import by.home.entity.*;
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

    private final String INDIVIDUAL_DEPOSIT_VALUE = BankDepositEnum.INDIVIDUAL_DEPOSIT.getValue();
    private final String FIXED_TERMED_DEPOSIT_VALUE = BankDepositEnum.FIXED_TERMED_DEPOSIT.getValue();
    private final String LEGAL_ENTITY_DEPOSIT_VALUE = BankDepositEnum.LEGAL_ENTITY_DEPOSIT.getValue();

    public BankDepositHandler() {
        bankDeposits = new ArrayList<BankDeposit>();
        withText = EnumSet.range(BankDepositEnum.BANK_NAME, BankDepositEnum.NUMBER_OF_FOUNDERS);
    }

    public List<BankDeposit> getBankDeposits() {
        return bankDeposits;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (INDIVIDUAL_DEPOSIT_VALUE.equals(localName)) {
            current = new IndividualDeposit();
            fillingBankDepositWithAttributes(attrs);
        } else if (FIXED_TERMED_DEPOSIT_VALUE.equals(localName)) {
            current = new FixedTermedDeposit();
            fillingBankDepositWithAttributes(attrs);
        } else if (LEGAL_ENTITY_DEPOSIT_VALUE.equals(localName)) {
            current = new LegalEntityDeposit();
            fillingBankDepositWithAttributes(attrs);
        } else {
            BankDepositEnum temp = BankDepositEnum.valueOf(localName.toUpperCase().replace("-", "_"));
            if (withText.contains(temp)) {
                currentEnum = temp;
            }
        }
    }

    private void fillingBankDepositWithAttributes(Attributes attrs) {
        current.setId(attrs.getValue(0));
        if (attrs.getLength() == 3) {
            current.setCountry(attrs.getValue(1));
            current.setCurrency(attrs.getValue(2));
        }
        if (attrs.getLength() == 2) {
            current.setCurrency(attrs.getValue(1));
        }
    }

    public void endElement(String uri, String localName, String qName) {
        if (INDIVIDUAL_DEPOSIT_VALUE.equals(localName)) {
            bankDeposits.add(current);
        } else if (FIXED_TERMED_DEPOSIT_VALUE.equals(localName)) {
            bankDeposits.add(current);
        } else if (LEGAL_ENTITY_DEPOSIT_VALUE.equals(localName)) {
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
                    ((IndividualDeposit) current).setDepositorName(s);
                    break;
                case DEPOSITOR_SURNAME:
                    ((IndividualDeposit) current).setDepositorSurname(s);
                    break;
                case PASSPORT_NUMBER:
                    ((IndividualDeposit) current).setPassportNumber(s);
                    break;
                case NUMBER_OF_YEARS:
                    ((FixedTermedDeposit) current).setNumberOfYears(Integer.parseInt(s));
                    break;
                case COMPANY_NAME:
                    ((LegalEntityDeposit) current).setCompanyName(s);
                    break;
                case NUMBER_OF_FOUNDERS:
                    ((LegalEntityDeposit) current).setNumberOfNumbers(Integer.parseInt(s));
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }


}
