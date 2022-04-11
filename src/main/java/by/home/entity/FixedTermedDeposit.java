package by.home.entity;

public class FixedTermedDeposit extends  IndividualDeposit{

    private int numberOfYears;

    public FixedTermedDeposit(String id, String country, String bankName, int amount, int percent, String currency, String depositorName, String depositorSurname, String passportNumber, int numberOfYears) {
        super(id, country, bankName, amount, percent, currency, depositorName, depositorSurname, passportNumber);
        this.numberOfYears = numberOfYears;
    }

    public FixedTermedDeposit(String id, String bankName, int amount, int percent, String currency, String depositorName, String depositorSurname, String passportNumber, int numberOfYears) {
        super(id, bankName, amount, percent, currency, depositorName, depositorSurname, passportNumber);
        this.numberOfYears = numberOfYears;
    }

    public FixedTermedDeposit() {
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FixedTermedDeposit)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        FixedTermedDeposit that = (FixedTermedDeposit) o;

        return getNumberOfYears() == that.getNumberOfYears();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getNumberOfYears();
        return result;
    }

    @Override
    public String toString() {
        return "FixedTermedDeposit{" + super.toString()+
                "numberOfYears=" + numberOfYears +
                '}';
    }
}
