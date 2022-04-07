package by.bome.entity;

public enum BankDepositEnum {
    BANK_DEPOSITS("bank-deposits"),
    INDIVIDUAL_DEPOSIT("individual-deposit"),
    FIXED_TERMED_DEPOSIT("fixed-termed-deposit"),
    LEGAL_ENTITY_DEPOSIT("legal-entity-deposit"),
    ID("id"),
    COUNTRY("country"),
    CURRENCY("currency"),
    BANK_NAME("bank-name"),
    AMOUNT("amount"),
    PERCENT("percent"),
    DEPOSITOR_NAME("depositor-name"),
    DEPOSITOR_SURNAME("depositor-surname"),
    PASSPORT_NUMBER("passport-number"),
    NUMBER_OF_YEARS("number-of-years"),
    COMPANY_NAME("company-name"),
    NUMBER_OF_FOUNDERS("number-of-founders");

    private String value;

    private BankDepositEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
