public class ParserFactory {

    public Parser create(ParserType type){
        switch(type) {
            case SAX_PARSER:
                return new SaxParser();
            case DOM_PARSER:
                return new DomParser();
            case JAXB_PARSER:
                return new JaxbParser();
            default:
                throw new UnsupportedOperationException("Unknown type=" + type);
        }
    }
}
