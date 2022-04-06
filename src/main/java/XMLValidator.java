
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {

    private final String xsdFile;

    private final static Logger LOGGER = Logger.getLogger(XMLValidator.class);

    public XMLValidator(String xsdFile){
        this.xsdFile=xsdFile;
    }

    public boolean isValid(String xmlFile){

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(xsdFile);
        try {
            // создание схемы
            Schema schema = factory.newSchema(schemaLocation);
            // создание валидатора на основе схемы
            Validator validator = schema.newValidator();
            // проверка документа
            Source source = new StreamSource(xmlFile);
            validator.validate(source);
            LOGGER.info(xmlFile + " is valid.");
            return true;
        } catch (SAXException e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            return false;
        }
    }
}
