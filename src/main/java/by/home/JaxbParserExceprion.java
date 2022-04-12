package by.home;

import javax.xml.bind.JAXBException;

public class JaxbParserExceprion extends Exception {

    public JaxbParserExceprion(String message, Exception e) {
            super(message,e);
        }
    }

