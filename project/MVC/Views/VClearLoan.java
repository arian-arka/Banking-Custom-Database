package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VClearLoan extends Controller{
    public void index(View v,String accounts[],String loans[]){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Clear Loan");
        v.setSize(280, 250);

        JLabel lbaccount=new JLabel("Account", JLabel.CENTER);
        lbaccount.setBounds(0,50,130,30);//80

        JLabel lbloan=new JLabel("Loan", JLabel.CENTER);
        lbloan.setBounds(0,90,130,30);//120

        JLabel lbamount=new JLabel("Amount", JLabel.CENTER);
        lbamount.setBounds(0,130,130,30);//160

        JComboBox accountPicker;
        if(accounts==null || accounts.length==0)
            accountPicker=new JComboBox();
        else
            accountPicker=new JComboBox(accounts);
        accountPicker.setBounds(131,50,130,30);//80

        JComboBox loanPicker;
        if(loans==null || loans.length==0)
            loanPicker=new JComboBox();
        else
            loanPicker=new JComboBox(loans);
        loanPicker.setBounds(131,90,130,30);

        JTextField amountField=new JTextField("",1);
        amountField.setBounds(131,130,130,30);//80

        JButton clearButton=new JButton("Clear");
        clearButton.setBounds(130,165,130,40);//165

        JButton backButton=new JButton("Back");
        backButton.setBounds(10,165,120,40);//165
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CUserMain",false);
            }
        });
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(accountPicker.getItemCount()!=0 && loanPicker.getItemCount()!=0){
                    String accountId =  (String)accountPicker.getItemAt(accountPicker.getSelectedIndex());
                    String loanId =  (String)loanPicker.getItemAt(loanPicker.getSelectedIndex());
                    String message=(String)self("CClearLoan","clear",false,loanId,accountId,amountField.getText());
                    if(message.equals("Entered amount of the loan has cleared succesfully")){
                        if((boolean)self("CClearLoan","isClearedCompeletly",false,loanId))
                            loanPicker.removeItem(Terminal.convert2int(loanId));
                    }
                    v.popUpOk(message);
                }
            }
        });
        v.add(lbaccount);
        v.add(lbloan);
        v.add(lbamount);
        v.add(accountPicker);
        v.add(loanPicker);
        v.add(amountField);
        v.add(clearButton);
        v.add(backButton);
        v.repaint();
    }
}