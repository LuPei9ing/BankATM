package BankATM;

import com.sun.security.ntlm.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ManagerAccount extends Account{
    private Report report;
    public ManagerAccount(){
        Map<String, Integer> currency=new HashMap() ;
        currency.put("dollar",0);
        currency.put("RMB",0);
        currency.put("EUR",0);
        this.setCurrency(currency);
        this.setType("manager");
        this.setAccountNo("Peiqing");
        this.setPassword("lpq123");
        report=new Report();
    }
    //charge opening fee
    public void addClient(){
        Map<String, Integer> cur=super.getCurrency();
        cur.put("dollar",cur.get("dollar")+5);
    }
    //charge closing fee
    public void reduceClient(){
        Map<String, Integer> cur=super.getCurrency();
        cur.put("dollar",cur.get("dollar")+5);
    }
    //charge transaction fee
    public void chargeTransaction(String type){
        Map<String, Integer> cur=super.getCurrency();
        cur.put(type,cur.get(type)+2);
    }
    //charge withdrawal fee
    public void chargeWitdrawal(String type){
        Map<String, Integer> cur=super.getCurrency();
        cur.put(type,cur.get(type)+1);
    }
    //charge interest
    public void getInterest(){
        Map<String,Integer> cur=this.getCurrency();
        cur.put("dollar",cur.get("dollar")+2);
        cur.put("EUR",cur.get("EUR")+2);
        cur.put("RMB",cur.get("RMB")+2);
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
