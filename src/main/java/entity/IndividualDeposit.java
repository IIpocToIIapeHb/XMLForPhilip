package entity;

public class IndividualDeposit extends BankDeposit {

    private String depositorName;
    private String depositorSurname;
    private String passportNumber;

    public IndividualDeposit(String id, String country, String bankName, int amount, int percent, CurrencyType currency, String depositorName, String depositorSurname, String passportNumber) {
        super(id, country, bankName, amount, percent, currency);
        this.depositorName = depositorName;
        this.depositorSurname = depositorSurname;
        this.passportNumber = passportNumber;
    }

    public IndividualDeposit() {
    }

    public String getDepositorName() {
        return depositorName;
    }

    public String getDepositorSurname() {
        return depositorSurname;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setDepositorName(String depositorName) {
        this.depositorName = depositorName;
    }

    public void setDepositorSurname(String depositorSurname) {
        this.depositorSurname = depositorSurname;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof IndividualDeposit)){
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        IndividualDeposit that = (IndividualDeposit) o;

        if (!getDepositorName().equals(that.getDepositorName())){
            return false;
        }
        if (!getDepositorSurname().equals(that.getDepositorSurname())){
            return false;
        }
        return getPassportNumber().equals(that.getPassportNumber());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getDepositorName().hashCode();
        result = 31 * result + getDepositorSurname().hashCode();
        result = 31 * result + getPassportNumber().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "IndividualDeposit{" +
                "depositorName='" + depositorName + '\'' +
                ", depositorSurname='" + depositorSurname + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
