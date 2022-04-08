package by.home;

import by.home.entity.BankDeposit;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser {

    private final static org.apache.log4j.Logger LOGGER = Logger.getLogger(SaxParser.class);

    private BankDepositHandler handler;
    private XMLReader reader;
    private List<BankDeposit> bankDeposits;

    public SaxParser(){
        handler = new BankDepositHandler();
    }

    @Override
    public List<BankDeposit> parse(String file) throws SaxParserException {
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
            reader.parse(file);
        } catch (SAXException e) {
            throw new SaxParserException(e.getMessage(),e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока: " + e);
        }
        bankDeposits = handler.getBankDeposits();
        return bankDeposits;

    }
}
