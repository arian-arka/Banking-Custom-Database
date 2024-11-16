package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VMasterMain extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setTitle("Master");
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
                //control("CPersonalInformation",false);
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

        JComboBox cb=new JComboBox(new String[]{"Customers","Accounts","Loans","Branches","Average"});
        cb.setBounds(65,30,110,30);

        v.add(cb);
        cb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch ((String)cb.getItemAt(cb.getSelectedIndex())){
                    case "Customers":
                        control("CSearchCustomers",false);
                        break;
                    case "Accounts":
                        control("CSearchAccounts",false);
                        break;
                    case "Loans":
                        control("CSearchLoans",false);
                        break;
                    case "Branches":
                        control("CSearchBranch",false);
                        break;
                    case "Average":
                        v.popUpOk((String)self("CBalance_Loan_average","result",false));
                        break;
                }
            }
        });
//        JLabel lbBalance=new JLabel("Balance: "+balance, JLabel.CENTER);
//        lbBalance.setBounds(10,70,230,30);//80
//        v.add(lbBalance);

        v.repaint();
    }
}