package BankATM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagerGUI implements ActionListener {

    public JFrame jFrame;

    private JPanel mp1,mp2,mp3,mp4;

    private JButton dailyReport;
    private JButton latestReport;
    private JButton checkup;
    private JButton quit;

    private JTextArea show;

    public ManagerGUI(){
        jFrame=new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mp1=new JPanel();
        mp1.add(new JLabel("CHOOSE YOUR OPERATIONS"));
        jFrame.add(mp1);

        mp2=new JPanel();
        dailyReport=new JButton("DailyReport");
        latestReport=new JButton("LatestReport");
        checkup=new JButton("CheckUp");
        mp2.add(dailyReport);
        mp2.add(latestReport);
        mp2.add(checkup);
        mp2.setLayout(new GridLayout(3,1,20,20));
        jFrame.add(mp2);

        mp3=new JPanel();
        quit=new JButton("Quit");
        mp3.add(quit);
        jFrame.add(mp3);

        mp4=new JPanel();
        show=new JTextArea();
        mp4.add(show);
        jFrame.add(mp4);

        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLayout(new GridLayout(4,1,2,2));

        dailyReport.addActionListener(this);
        latestReport.addActionListener(this);
        checkup.addActionListener(this);
        quit.addActionListener(this);
        jFrame.setBounds(500,200,900,750);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("DailyReport")){
            show.setText("");
            show.append(ATM.managerAccount.getReport().getDailyReport());
        }
        else if(e.getActionCommand().equals("LatestReport")){
            show.setText("");
            show.append(ATM.managerAccount.getReport().getLatestReport());
        }
        else if(e.getActionCommand().equals("CheckUp")){
            show.setText("");
            for(ClientAccount ca:ATM.clientAccounts){
                show.append(ca.toString()+"\n");
            }
        }
        else if(e.getActionCommand().equals("Quit")) {
            this.jFrame.setVisible(false);
            Menu menu=new Menu();
        }
    }
}
