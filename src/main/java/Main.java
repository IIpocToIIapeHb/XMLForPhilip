public class Main {
    public static void main(String[] args) {


        XMLValidator validator = new XMLValidator("data_xml/bankDeposits.xsd");
        System.out.println(validator.isValid("data_xml/bankDeposits.xml"));


    }
}
