import by.home.XMLValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XMLValidatorTest {

    private final static String VALID_XML_FILE = "src/test/resources/testBankDeposits.xml";
    private final static String INVALID_XML_FILE = "src/test/resources/InvalidTestBankDeposits.xml";
    private final static String NOT_EXISTED_XML_FILE = "not_existed_file.xml";
    private final static String XSD_FILE ="src/test/resources/testBankDeposits.xsd";


    @Test
    public void testIsValidShouldReturnTrueWhenValidFile() {
        //given
        XMLValidator xmlValidator = new XMLValidator(XSD_FILE);
        //when
        boolean result = xmlValidator.isValid(VALID_XML_FILE);
        //then
        assertTrue(result);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenINValidFile() {
        //given
        XMLValidator xmlValidator = new XMLValidator(XSD_FILE);
        //when
        boolean result = xmlValidator.isValid(INVALID_XML_FILE);
        //then
        assertFalse(result);
    }

    @Test
    public void testIsValidShouldReturnFalseWhenNotExistedXML() {
        //given
        XMLValidator xmlValidator = new XMLValidator(XSD_FILE);
        //when
        boolean result = xmlValidator.isValid(NOT_EXISTED_XML_FILE);
        //then
        assertFalse(result);

    }
}
