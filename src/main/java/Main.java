import entity.BankDeposit;
import entity.IndividualDeposit;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        XMLValidator validator = new XMLValidator("data_xml/bankDeposits.xsd");
        System.out.println(validator.isValid("data_xml/bankDeposits.xml"));

        ParserFactory factory = new ParserFactory();
        Parser saxParser = factory.create(ParserType.SAX_PARSER);
        List<BankDeposit> list = saxParser.parse("data_xml/bankDeposits.xml");
        for(BankDeposit deposit:list){
            System.out.println(deposit);
        }




    }
}
