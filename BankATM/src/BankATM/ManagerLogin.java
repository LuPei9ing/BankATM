package BankATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerLogin implements ActionListener {
    public JFrame jFrame;

    private JTextField account,password;
    private JButton login,quit;
    private JPanel p1,p2,p3;

    public ManagerLogin(){
        jFrame=new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        account=new JTextField(20);
        password=new JTextField(20);
        p1=new JPanel();
        p1.add(new JLabel("\tusername"));
        p1.add(account);
        jFrame.add(p1);

        p3=new JPanel();
        p3.add(new JLabel("\tpassword"));
        p3.add(password);
        jFrame.add(p3);

        p2=new JPanel();
        login=new JButton("Login");
        quit=new JButton("Quit");
        p2.add(login);
        p2.add(quit);
        jFrame.add(p2);

        jFrame.pack();
        jFrame.setBounds(500,500,450,300);
        jFrame.setLayout(new GridLayout(3,1,2,2));
        jFrame.setVisible(true);
        login.addActionListener(this);
        quit.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //manager login part
        if(e.getActionCommand().equals("Login")){
            String useName=account.getText();
            String pw=password.getText();
            if(ATM.matchManagerPassword(useName,pw)){
                JOptionPane.showMessageDialog(jFrame, "login success");
                ATM.savingInterest();
                ManagerGUI managerGUI=new ManagerGUI();
                jFrame.dispose();
            }
            else{
                JOptionPane.showMessageDialog(jFrame, "The password is wrong");
                password.setText("");
                account.setText("");
                return;
            }
        }

        if(e.getActionCommand().equals("Quit")){
            jFrame.setVisible(false);
            Menu menu=new Menu();
        }
    }
}
