package BankATM;

import java.util.Map;

public class Account {
    private Map<String,Integer> currency;//three kinds of currencies
    private String type;//type of account
    private String accountNo;
    private String password;

    public Account(){

    }

    public Account(Map<String, Integer> currency, String type,  String accountNo, String password){
        this.currency = currency;
        this.type=type;
        this.accountNo=accountNo;
        this.password=password;
    }

    public Map getCurrency() {
        return currency;
    }

    public void setCurrency(Map currency) {
        this.currency = currency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
