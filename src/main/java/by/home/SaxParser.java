package by.home;

import by.home.entity.BankDeposit;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxParser implements Parser {

    private final static org.apache.log4j.Logger LOGGER = Logger.getLogger(SaxParser.class);

    private BankDepositHandler handler;
    private XMLReader reader;

    public SaxParser(){
        handler = new BankDepositHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            LOGGER.error("ошибка SAX парсера: " + e);
        }
    }

    @Override
    public List<BankDeposit> parse(String file) {
        List<BankDeposit> bankDeposits;
        try {
            reader.parse(file);
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        bankDeposits = handler.getBankDeposits();
        return bankDeposits;

    }
}
