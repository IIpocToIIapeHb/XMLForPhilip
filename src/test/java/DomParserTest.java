import by.home.DomParser;
import by.home.SaxParser;
import by.home.SaxParserException;
import by.home.entity.BankDeposit;
import by.home.entity.FixedTermedDeposit;
import by.home.entity.IndividualDeposit;
import by.home.entity.LegalEntityDeposit;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DomParserTest {

    private final static String VALID_XML_FILE = "src/test/resources/testBankDeposits.xml";
    private final static String INVALID_XML_FILE = "not_existed_file.xml";

    private final static List<BankDeposit>  EXPECTED_LIST = Arrays.asList(
            new IndividualDeposit("GE0M354123", "China","JPMorganChase" , 10000, 5 , "EURO", "Adolf","Hittler","kh1111"),
            new FixedTermedDeposit("a333vvB3", "USA","BankOfAmerica" , 50000, 1 , "DOLLAR", "Oscar","Coul","mp3333",5),
            new LegalEntityDeposit("HJ555555", "BankofSpain", 9000 , 3, "DOLLAR","Bubbles",2));

    @Test
    public void testParseShouldReturnListOfBankDeposits() throws SaxParserException {
        //given
        DomParser domParser = new DomParser();
        //when
        List<BankDeposit> realList = domParser.parse(VALID_XML_FILE);
        //then
        assertEquals(new ArrayList<>(EXPECTED_LIST),realList);
    }

}
