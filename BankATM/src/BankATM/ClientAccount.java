package BankATM;

import java.util.Map;

public class ClientAccount extends Account {
    private boolean  collateral;
    private int loan;
    public ClientAccount(Map<String,Integer> currency, String type, String accountNo, String password){
        super(currency, type, accountNo, password);
        collateral=false;
        loan=0;
    }
    // request a loan
    public int requestLoan(int currency) {
        this.collateral=true;
        if(this.collateral) {
            this.setLoan(this.getLoan() + currency);
            return currency;
        }
        return 0;
    }
    // pay interest
    public void payInterest(){
        Map<String,Integer> cur=this.getCurrency();
        cur.put("dollar",cur.get("dollar")-2);
        cur.put("EUR",cur.get("EUR")-2);
        cur.put("RMB",cur.get("RMB")-2);
    }
    //get the interest of saving
    public void getInterest(){
        Map<String,Integer> cur=this.getCurrency();
        cur.put("dollar",cur.get("dollar")+1);
        cur.put("EUR",cur.get("EUR")+1);
        cur.put("RMB",cur.get("RMB")+1);
    }

    public int transaction(int currency,String type) {
        Map<String, Integer> cur = super.getCurrency();
        currency = new Integer(currency);
        if (cur.get(type) > currency) {
            cur.put(type, cur.get(type) - currency);
            return currency;
        }
        return -1;
    }

    public int withDraw(int currency,String type){
        Map<String,Integer> cur=super.getCurrency();
        currency = new Integer(currency);
        if (cur.get(type) > currency) {
            cur.put(type, cur.get(type) - currency-1);
            return currency;
        }
        return -1;
    }

    public int deposit(int currency,String type){
        Map<String,Integer> cur=super.getCurrency();
        currency = new Integer(currency);
        cur.put(type,cur.get(type)+currency);
        return currency;
    }
    public String toString(){
        return ("Account number is "+this.getAccountNo()+"\n"+"The deposit is "+this.getCurrency().toString()+"\n"+"The loan is "+new Integer(loan).toString()+"\nThe type is "+this.getType());
    }

    public boolean isCollateral() {
        return collateral;
    }

    public void setCollateral(boolean collateral) {
        this.collateral = collateral;
    }

    public int getLoan() {
        return loan;
    }

    public void setLoan(int loan) {
        this.loan = loan;
    }
}
