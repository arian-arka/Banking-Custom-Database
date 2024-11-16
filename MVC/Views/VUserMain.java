package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VUserMain extends Controller{
    public void index(View v,String balance,String name){
        v.getContentPane().removeAll();
        v.setTitle("Main: "+name);
        v.setJMenuBar(null);
        v.setSize(385, 160);
        JMenuBar mb=new JMenuBar();
        JMenuItem  menuItemChangePass=new JMenuItem("Change Password");
        JMenuItem  menuItemInfo=new JMenuItem("Informatoin");
        JMenuItem  menuItemLogOut=new JMenuItem("Log Out");
        menuItemChangePass.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Terminal.writeLine("Change Password");
            }
        });
        menuItemInfo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                control("CPersonalInformation",false);
            }
        });
        menuItemLogOut.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                control("CLogin",false,false);
            }
        });
        mb.add(menuItemChangePass);
        mb.add(menuItemInfo);
        mb.add(menuItemLogOut);
        v.setJMenuBar(mb);

        JLabel lbTask=new JLabel("Task", JLabel.CENTER);
        lbTask.setBounds(10,30,50,30);//80
        v.add(lbTask);

        JComboBox cb=new JComboBox(new String[]{"Accounts","Add Account","Close Account","Loans","Get Loan","Clear Loan"});
        cb.setBounds(65,30,110,30);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch ((String)cb.getItemAt(cb.getSelectedIndex())){
                    case "Accounts":
                        control("CAccounts",false);
                        break;
                    case "Add Account":
                        control("CAddAccount",false);
                        break;
                    case "Close Account":
                        control("CCloseAccount",false);
                        break;
                    case "Loans":
                        control("CLoans",false);
                        break;
                    case "Get Loan":
                        control("CGetLoan",false);
                        break;
                    case "Clear Loan":
                        control("CClearLoan",false);
                        break;
                }
            }
        });
        v.add(cb);

        JLabel lbBalance=new JLabel("Balance: "+balance, JLabel.CENTER);
        lbBalance.setBounds(10,70,230,30);//80
        v.add(lbBalance);

        v.repaint();
    }
}