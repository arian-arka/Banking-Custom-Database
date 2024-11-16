package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VCloseAccount extends Controller{
    public void index(View v,String []accounts){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Close Account");
        v.setSize(190, 130);
        JComboBox accountPicker;
        if(accounts==null || accounts.length==0)
            accountPicker=new JComboBox();
        else
            accountPicker=new JComboBox(accounts);
        accountPicker.setBounds(10,10,160,30);//80



        JButton closeButton=new JButton("Close");
        closeButton.setBounds(90,50,80,40);//165

        JButton backButton=new JButton("Back");
        backButton.setBounds(10,50,80,40);//165

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CUserMain",false);
            }
        });
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(accountPicker.getItemCount()!=0){
                    String accountId =  (String)accountPicker.getItemAt(accountPicker.getSelectedIndex());
                    accountPicker.removeItem(accountId);
                    self("CCloseAccount","close",false,accountId);
                    v.popUpOk("Account closed");
                }
            }
        });
        v.add(accountPicker);
        v.add(closeButton);
        v.add(backButton);

        v.repaint();
    }
}