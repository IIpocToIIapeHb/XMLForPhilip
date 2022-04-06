package entity;

public class LegalEntityDeposit extends BankDeposit {

    private String companyName;

    public LegalEntityDeposit(String id, String country, String bankName, int amount, int percent, CurrencyType currency, String companyName) {
        super(id, country, bankName, amount, percent, currency);
        this.companyName = companyName;
    }

    public LegalEntityDeposit() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof LegalEntityDeposit)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        LegalEntityDeposit that = (LegalEntityDeposit) o;

        return getCompanyName().equals(that.getCompanyName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCompanyName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LegalEntityDeposit{" +
                "companyName='" + companyName + '\'' +
                '}';
    }
}
