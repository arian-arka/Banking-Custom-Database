package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VSearchBranch extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Search Branch");
        v.setSize(270, 280);

        JLabel lbbranch=new JLabel("Branch", JLabel.CENTER);
        lbbranch.setBounds(0,50,130,30);//80

        JTextField branchField=new JTextField("",1);
        branchField.setBounds(131,50,130,30);//80

        JLabel lbloan=new JLabel("Loan avg:", JLabel.CENTER);
        lbloan.setBounds(0,90,130,30);//120

        JLabel lbbalance=new JLabel("Balance avg:", JLabel.CENTER);
        lbbalance.setBounds(0,130,130,30);//80

        JButton searchButton=new JButton("Search");
        searchButton.setBounds(130,165,130,40);//165
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lbloan.setText("");
                lbbalance.setText("");
                String data[]=(String [])self("CSearchBranch","search",false,branchField.getText());
                if(data!=null){
                    lbloan.setText("Loan avg:"+data[2]);
                    lbbalance.setText("Balance avg:"+data[1]);
                }
            }
        });
        JButton backButton=new JButton("Back");
        backButton.setBounds(10,165,120,40);//165
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CMasterMain",false);
            }
        });

        v.add(lbbranch);
        v.add(lbbalance);
        v.add(lbloan);
        v.add(branchField);
        v.add(searchButton);
        v.add(backButton);
        v.repaint();
    }
}