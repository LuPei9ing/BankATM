package BankATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements ActionListener {
    public JFrame mframe;
    private JPanel mp1,mp2,mp3;
    private JButton managerLogin;
    private JButton clientLogin;
    private JButton quit;
    public Menu(){
        mframe=new JFrame("Menu");
        mframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton managerLogin=new JButton("Manager Login");
        JButton clientLogin=new JButton("Client Login");
        JButton quit=new JButton("Quit");

        mp2=new JPanel();
        mp2.add(new JLabel("CHOOSE YOUR ROLE"));
        mp2.add(managerLogin);
        mp2.add(clientLogin);
        mp2.add(quit);
        mp2.setLayout(new GridLayout(4,1,20,20));
        mframe.add(mp2);

        mframe.pack();
        mframe.setVisible(true);
        mframe.setLayout(new FlowLayout());

        managerLogin.addActionListener(this);
        clientLogin.addActionListener(this);
        quit.addActionListener(this);
        mframe.setBounds(500,200,600,450);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd=e.getActionCommand();
        if(cmd.equals("Manager Login")){
            mframe.dispose();
            ManagerLogin mGUI=new ManagerLogin();
        }
        else if(cmd.equals("Client Login")){
            mframe.dispose();
            ClientLogin cGUI=new ClientLogin();
        }
        else if(cmd.equals("Quit")){
            mframe.dispose();
            JOptionPane.showMessageDialog(null,"Please remove your card");
        }
    }
}
