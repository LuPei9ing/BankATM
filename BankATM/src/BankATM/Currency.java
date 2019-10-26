package BankATM;

public class Currency {
    private int amount;
    private String currency;
    public Currency(int amount, String currency){
        this.currency=currency;
        this.amount=amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
