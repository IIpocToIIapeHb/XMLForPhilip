package by.home;

import by.home.entity.BankDeposit;
import by.home.entity.BankDeposits;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class JaxbParser implements Parser {
    @Override
    public List<BankDeposit> parse(String file) {
        List<BankDeposit> list = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(BankDeposits.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader(file);
            BankDeposits bankDeposits = (BankDeposits) u.unmarshal(reader);
            list = bankDeposits.getList();
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
