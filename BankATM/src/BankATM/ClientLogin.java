package BankATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ClientLogin implements ActionListener {
    public JFrame jFrame=new JFrame();

    private JPanel p1,p2,p3,p4,p5,p6,p7,p8;

    private JTextField account;
    private JTextField password,passwordCheck;
    private JComboBox accountType;
    private JButton register,login,quit;

    private ClientAccount ca;

    public ClientLogin(){
        jFrame=new JFrame("Login or Sign up");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        p1=new JPanel();
        p1.add(new JLabel("Hello! My God!"));
        jFrame.add(p1);

        p2=new JPanel();
        p2.add(new JLabel("\tUserName:"));
        account=new JTextField(20);
        p2.add(account);
        jFrame.add(p2);

        p3=new JPanel();
        p3.add(new JLabel("\tPassword"));
        password=new JTextField(20);
        p3.add(password);
        jFrame.add(p3);

        p4=new JPanel();
        p4.add(new JLabel("\tcheck your password"));
        passwordCheck =new JTextField(20);
        p4.add(passwordCheck);
        jFrame.add(p4);

        p6=new JPanel();
        p6.add(new JLabel("\tchoose types of your account "));
        accountType=new JComboBox();
        accountType.addItem("checking");
        accountType.addItem("saving");
        p6.add(accountType);
        jFrame.add(p6);

        p5=new JPanel();
        register=new JButton("Register");
        login=new JButton("Login");
        quit=new JButton("Quit");
        p5.add(register);
        p5.add(login);
        p5.add(quit);
        jFrame.add(p5);

        jFrame.pack();
        jFrame.setVisible(true);
        p4.setVisible(false);
        p6.setVisible(false);
        jFrame.setLayout(new GridLayout(6,1,1,1));
        login.addActionListener(this);
        register.addActionListener(this);
        quit.addActionListener(this);
        jFrame.setBounds(500,200,600,450);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //the registering part
        if(e.getActionCommand().equals("Register")) {
            String userName=account.getText();
            if (p4.isVisible() == false) {
                p4.setVisible(true);
                p6.setVisible(true);
                login.setText("Cancel");
                return;
            }
            if (account.getText().equals("")) {
                JOptionPane.showMessageDialog(jFrame, "Your account cannot be empty");
                return;
            }
            if(password.getText().equals("")){
                JOptionPane.showMessageDialog(jFrame, "The password cannot be empty");
                return;
            }
            if(ATM.findUsername(userName)){
                JOptionPane.showMessageDialog(jFrame, "The account has already existed");
                account.setText("");
                password.setText("");
                passwordCheck.setText("");
                return;
            }
            else{
                if(password.getText().equals(passwordCheck.getText())) {
                    String pw=password.getText();
                    String type=accountType.getSelectedItem().toString();
                    Map<String, Integer> currency=new HashMap() ;
                    currency.put("dollar",45);
                    currency.put("RMB",0);
                    currency.put("EUR",0);
                    ca=new ClientAccount(currency,type,userName,pw);
                    ATM.addClient(ca);
                    account.setText("");
                    password.setText("");
                    passwordCheck.setText("");
                    ClientGUI cg=new ClientGUI(ca);
                    jFrame.dispose();
                    return;
                }
                else{
                    JOptionPane.showMessageDialog(jFrame, "The passwords entered twice do not match, please re-enter");
                    account.setText("");
                    passwordCheck.setText("");
                    password.setText("");
                    return;
                }
            }
        }
        //login part
        if(e.getActionCommand().equals("Login")){
            String userName=account.getText();
            String pw=password.getText();
            if (account.getText().equals("")) {
                JOptionPane.showMessageDialog(jFrame, "Your account cannot be empty");
                return;
            }
            if(password.getText().equals("")){
                JOptionPane.showMessageDialog(jFrame, "The password cannot be empty");
                return;
            }
            ca=ATM.matchPassword(userName,pw);
            if(ca!=null){
                JOptionPane.showMessageDialog(jFrame, "login success");
                ATM.clientInterest(ca);
                ClientGUI cGUI=new ClientGUI(ca);
                jFrame.setVisible(false);
            }
            else{
                JOptionPane.showMessageDialog(jFrame, "The password is wrong");
                account.setText("");
                password.setText("");
                return;
            }
        }
        if(e.getActionCommand().equals("Cancel")){
            p4.setVisible(false);
            p6.setVisible(false);
            login.setText("Login");
            return;
        }
        if(e.getActionCommand().equals("Quit")) {
            jFrame.setVisible(false);
            Menu menu=new Menu();
        }
    }
}
