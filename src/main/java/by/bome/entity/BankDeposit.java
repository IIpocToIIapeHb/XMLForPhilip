package by.bome.entity;

public abstract class BankDeposit {

    private String id;
    private String country = "Earth";
    private String bankName;
    private int amount;
    private int percent;
    private String currency;

    public BankDeposit(String id, String country, String bankName, int amount, int percent, String currency) {
        this.id = id;
        this.country = country;
        this.bankName = bankName;
        this.amount = amount;
        this.percent = percent;
        this.currency = currency;
    }

    public BankDeposit(String id, String bankName, int amount, int percent, String currency) {
        this.id = id;
        this.bankName = bankName;
        this.amount = amount;
        this.percent = percent;
        this.currency = currency;
    }

    public BankDeposit() {
    }

    public String getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getBankName() {
        return bankName;
    }

    public int getAmount() {
        return amount;
    }

    public int getPercent() {
        return percent;
    }

    public String getCurrency() {
        return currency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof BankDeposit)){
            return false;
        }

        BankDeposit that = (BankDeposit) o;

        if (getAmount() != that.getAmount()){
            return false;
        }
        if (getPercent() != that.getPercent()){
            return false;
        }
        if (!getId().equals(that.getId())){
            return false;
        }
        if (!getCountry().equals(that.getCountry())){
            return false;
        }
        if (!getBankName().equals(that.getBankName())){
            return false;
        }
        return getCurrency().equals(that.getCurrency());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCountry().hashCode();
        result = 31 * result + getBankName().hashCode();
        result = 31 * result + getAmount();
        result = 31 * result + getPercent();
        result = 31 * result + getCurrency().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BankDeposit{" +
                "id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", bankName='" + bankName + '\'' +
                ", amount=" + amount +
                ", percent=" + percent +
                ", currency=" + currency +
                '}';
    }
}
