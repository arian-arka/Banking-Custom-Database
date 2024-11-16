package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VGetLoan extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Get Loan");
        v.setSize(270, 220);

        JLabel lbbranch=new JLabel("Branch", JLabel.CENTER);
        lbbranch.setBounds(0,50,130,30);//80

        JLabel lbbalance=new JLabel("Amount", JLabel.CENTER);
        lbbalance.setBounds(0,90,130,30);//120


        JTextField branchField=new JTextField("",1);
        branchField.setBounds(131,50,130,30);//80
        JTextField balanceField=new JTextField("",1);
        balanceField.setBounds(131,90,130,30);//120


        JButton getButton=new JButton("Get");
        getButton.setBounds(130,125,130,40);//165
        getButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                v.popUpOk((String)self("CGetLoan","add",false,branchField.getText(),balanceField.getText()));
            }
        });
        JButton backButton=new JButton("Back");
        backButton.setBounds(10,125,120,40);//165
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CUserMain",false);
            }
        });
        v.add(lbbranch);
        v.add(lbbalance);
        v.add(branchField);
        v.add(balanceField);
        v.add(getButton);
        v.add(backButton);
        v.repaint();
    }
}