package BankATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class ClientGUI implements ActionListener {
    public JFrame jFrame;
    private JButton loan,transaction,withdraw,deposit,close,account,quit;
    private JTextField field,accountNo;
    private JTextArea area;
    private JComboBox type;
    private JPanel p1,p2,p3,p4,p5,p6,p7;
    private ClientAccount clientAccount;
    public ClientGUI(ClientAccount ca){
        jFrame=new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        clientAccount=ca;
        p1=new JPanel();
        p1.add(new JLabel("Choose your operation"));
        jFrame.add(p1);

        p2=new JPanel();
        loan=new JButton("Loan");
        transaction=new JButton("Transaction");
        withdraw=new JButton("Withdraw");
        deposit=new JButton("Deposit");
        account=new JButton("Account");
        close=new JButton("Close");
        p2.add(loan);
        p2.add(deposit);
        p2.add(withdraw);
        p2.add(transaction);
        p2.add(account);
        p2.add(close);
        p2.setLayout(new GridLayout(2,3,20,20));
        jFrame.add(p2);

        p3=new JPanel();
        p3.add(new JLabel("\tInput Your Currency"));
        field=new JTextField(20);
        p3.add(field);
        jFrame.add(p3);
        p3.setVisible(false);

        p6=new JPanel();
        p6.add(new JLabel("\tChoose the currency type or if you have any mortgage"));
        type=new JComboBox();
        p6.add(type);
        jFrame.add(p6);
        p6.setVisible(false);

        p5=new JPanel();
        p5.add(new JLabel("\tInput The Account you send to"));
        accountNo=new JTextField(20);
        p5.add(accountNo);
        jFrame.add(p5);
        p5.setVisible(false);

        p7=new JPanel();
        p7.add(new JLabel("\tThis is your info"));
        area=new JTextArea(5,20);
        p7.add(area);
        jFrame.add(p7);
        p7.setVisible(false);

        p4=new JPanel();
        quit=new JButton("Quit");
        p4.add(quit);
        jFrame.add(p4);

        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLayout(new GridLayout(7,1,20,20));
        jFrame.setBounds(500,200,1000,800);
        loan.addActionListener(this);
        transaction.addActionListener(this);
        close.addActionListener(this);
        withdraw.addActionListener(this);
        deposit.addActionListener(this);
        account.addActionListener(this);
        quit.addActionListener(this);
    }
    public void removeItem(){
        type=new JComboBox();
        p6.remove(1);
        p6.add(type);
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //the loan part
        if(e.getActionCommand().equals("Loan")){
            if(!p3.isVisible()) {
                p6.setVisible(true);
                removeItem();
                type.addItem("yes");
                type.addItem("no");
                p3.setVisible(true);
                return;
            }
            if(field.getText().equals("")||!(isNumeric(field.getText()))||Integer.parseInt(field.getText().toString())<0){
                JOptionPane.showMessageDialog(jFrame, "Wrong Input, Please try again");
                field.setText("");
                return ;
            }
            else{
                int amount=Integer.parseInt(field.getText());
                if(type.getSelectedItem().equals("yes")){
                    clientAccount.requestLoan(amount);
                    p3.setVisible(false);
                    p6.setVisible(false);
                    field.setText("");
                    JOptionPane.showMessageDialog(jFrame, "Success");
                    return ;
                }
                else{
                    JOptionPane.showMessageDialog(jFrame, "Sorry！ you don't have any mortgage");
                    return;
                }
            }
        }
        //the transaction part
        else if(e.getActionCommand().equals("Transaction")){
            if(!p3.isVisible()){
                p3.setVisible(true);
                p6.setVisible(true);
                p5.setVisible(true);
                removeItem();
                type.addItem("dollar");
                type.addItem("EUR");
                type.addItem("RMB");
                return ;
            }
            if(field.getText().equals("")||!(isNumeric(field.getText()))||Integer.parseInt(field.getText().toString())<0){
                JOptionPane.showMessageDialog(jFrame, "Wrong Input, Please try again");
                field.setText("");
                accountNo.setText("");
                return ;
            }
            else{
                int amount=Integer.parseInt(field.getText());
                String t=type.getSelectedItem().toString();
                String acc=accountNo.getText();
                ClientAccount cacc=ATM.findClient(acc);
                if(cacc==null){
                    JOptionPane.showMessageDialog(jFrame, "The client does not exist!");
                    field.setText("");
                    accountNo.setText("");
                    return ;
                }
                else{
                    int amo=ATM.transaction(clientAccount,cacc,amount,t);
                    if(amo==-1){
                        JOptionPane.showMessageDialog(jFrame, "Failure");
                        accountNo.setText("");
                        field.setText("");
                        return;
                    }else {
                        JOptionPane.showMessageDialog(jFrame, "Success");
                        p3.setVisible(false);
                        p5.setVisible(false);
                        p6.setVisible(false);
                        field.setText("");
                        return;
                    }
                }
            }
        }
        //the withdrawal part
        else if(e.getActionCommand().equals("Withdraw")){
            if(!p3.isVisible()){
                p3.setVisible(true);
                p6.setVisible(true);
                removeItem();
                type.addItem("dollar");
                type.addItem("EUR");
                type.addItem("RMB");
                return;
            }
            if(field.getText().equals("")||!(isNumeric(field.getText()))||Integer.parseInt(field.getText().toString())<0){
                JOptionPane.showMessageDialog(jFrame, "Wrong Input, Please try again");
                field.setText("");
                return ;
            }
            else{
                int amount=Integer.parseInt(field.getText());
                String t=type.getSelectedItem().toString();
                int amo =ATM.withdraw(clientAccount,amount,t);
                if(amo==-1){
                    JOptionPane.showMessageDialog(jFrame, "Failure");
                    field.setText("");
                    return;
                }else {
                    JOptionPane.showMessageDialog(jFrame, "Success");
                    p3.setVisible(false);
                    p6.setVisible(false);
                    field.setText("");
                    return;
                }
            }
        }
        //the deposit part
        else if(e.getActionCommand().equals("Deposit")){
            if(!p3.isVisible()){
                p3.setVisible(true);
                p6.setVisible(true);
                removeItem();
                type.addItem("dollar");
                type.addItem("EUR");
                type.addItem("RMB");
                return;
            }
            if(field.getText().equals("")||!(isNumeric(field.getText()))||Integer.parseInt(field.getText().toString())<0){
                JOptionPane.showMessageDialog(jFrame, "Wrong Input, Please try again");
                field.setText("");
                return ;
            }
            else{
                int amount=Integer.parseInt(field.getText());
                String t=type.getSelectedItem().toString();
                ATM.deposit(clientAccount,amount,t);
                JOptionPane.showMessageDialog(jFrame, "Success");
                p3.setVisible(false);
                p6.setVisible(false);
                field.setText("");
                return;
            }
        }
        //the check-up account part
        else if(e.getActionCommand().equals("Account")){
            if(!p7.isVisible()){
                p7.setVisible(true);
                area.append(clientAccount.toString());
                return;
            }
            else{
                area.setText("");
                area.append(clientAccount.toString());
                return;
            }
        }
        else if(e.getActionCommand().equals("Quit")){
            jFrame.dispose();
            Menu menu=new Menu();
        }
        //closing account part
        else if(e.getActionCommand().equals("Close")){
            ATM.clientAccounts.remove(clientAccount);
            JOptionPane.showMessageDialog(jFrame, "Success");
            jFrame.dispose();
            Menu menu=new Menu();
        }
    }
}
