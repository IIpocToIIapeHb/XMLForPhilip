package entity;

public class LegalEntityDeposit extends BankDeposit {

    private String companyName;
    private int numberOfNumbers;

    public LegalEntityDeposit(String id, String country, String bankName, int amount, int percent, String currency, String companyName,int numberOfNumbers) {
        super(id, country, bankName, amount, percent, currency);
        this.companyName = companyName;
        this.numberOfNumbers = numberOfNumbers;
    }

    public LegalEntityDeposit() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getNumberOfNumbers() {
        return numberOfNumbers;
    }

    public void setNumberOfNumbers(int numberOfNumbers) {
        this.numberOfNumbers = numberOfNumbers;
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

        if (getNumberOfNumbers() != that.getNumberOfNumbers()){
            return false;
        }
        return getCompanyName().equals(that.getCompanyName());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCompanyName().hashCode();
        result = 31 * result + getNumberOfNumbers();
        return result;
    }

    @Override
    public String toString() {
        return "LegalEntityDeposit{" + super.toString()+
                "companyName='" + companyName + '\'' +
                ", numberOfNumbers=" + numberOfNumbers +
                '}';
    }
}
