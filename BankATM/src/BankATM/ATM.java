package BankATM;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class ATM {
    public  static ArrayList<ClientAccount> clientAccounts=new ArrayList<>();
    public  static ManagerAccount managerAccount=new ManagerAccount();
    //search user by account number
    public static boolean findUsername(String name){
        for(ClientAccount ca:ATM.clientAccounts){
            if(ca.getAccountNo().equals(name)){
                return true;
            }
        }
        return false;
    }
    //get client by account number
    public static ClientAccount findClient(String name){
        for(ClientAccount ca:ATM.clientAccounts){
            if(ca.getAccountNo().equals(name)){
                return ca;
            }
        }
        return null;
    }
    //match client's password and account
    public static ClientAccount matchPassword(String name,String password){
        for(ClientAccount ca:ATM.clientAccounts){
            if(ca.getAccountNo().equals(name)){
                if(ca.getPassword().equals(password))
                    return ca;
            }
        }
        return null;
    }
    //match manager's password and account
    public static boolean matchManagerPassword(String name,String password){
        if(managerAccount.getAccountNo().equals(name)){
            if(managerAccount.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    //add a client
    public static  void addClient(ClientAccount ca){
        clientAccounts.add(ca);
        managerAccount.addClient();//charge opening fee
    }
    //pay client's interest
    public static void clientInterest(ClientAccount ca){
        ca.payInterest();
        managerAccount.getInterest();
    }
    //make transaction
    public static int transaction(ClientAccount ca,ClientAccount ca1,int amount,String type){
        int amo=ca.transaction(amount,type);
        if(amo!=-1) {
            ca1.deposit(amount, type);
            Report report = managerAccount.getReport();
            report.addReport(ca.getAccountNo() + " to " + ca1.getAccountNo() + " " + new Integer(amount).toString() + " in " + type + "\n");
        }
        return amo;
    }

    public static void deposit(ClientAccount ca,int amount,String type){
        ca.deposit(amount,type);
    }
    //pay saving interest
    public static void savingInterest(){
        for(ClientAccount ca:clientAccounts){
            if(ca.getType().equals("saving")){
                ca.getInterest();
            }
        }
    }
    public static int withdraw(ClientAccount ca,int amount,String type){
        int amo=ca.withDraw(amount,type);
        if(amo!=-1)
            ATM.managerAccount.chargeWitdrawal(type);
        return amo;
    }
    public static void main(String[] args){
        Locale.setDefault(new Locale("en", "US"));
        Menu menu=new Menu();
    }
}
