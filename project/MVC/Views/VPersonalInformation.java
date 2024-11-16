package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VPersonalInformation extends Controller{

    public void index(View v,String name,String socialNumber,String city,String address,String balance,String loan){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Personal Information");
        v.setSize(300, 330);

        JLabel lbFullName=new JLabel("FullName: "+name, JLabel.CENTER);
        lbFullName.setBounds(20,20,250,20);//40

        JLabel lbSocialNumber=new JLabel("SocialNumber: "+socialNumber, JLabel.CENTER);
        lbSocialNumber.setBounds(20,45,250,20);//40

        JLabel lbCity=new JLabel("City: "+city, JLabel.CENTER);
        lbCity.setBounds(20,70,250,20);//40

        JLabel lbAddress=new JLabel("Address: "+address, JLabel.CENTER);
        lbAddress.setBounds(20,95,250,20);//40

        JLabel lbBalane=new JLabel("Total balance: "+balance, JLabel.CENTER);
        lbBalane.setBounds(20,120,250,20);//40

        JLabel lbLoan=new JLabel("Total loan: "+loan, JLabel.CENTER);
        lbLoan.setBounds(20,150,250,20);//40

        JButton backButton=new JButton("Back");
        backButton.setBounds(20,180,200,100);//285

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CUserMain",false);
            }
        });

        v.add(lbFullName);
        v.add(lbSocialNumber);
        v.add(lbCity);
        v.add(lbAddress);
        v.add(lbBalane);
        v.add(lbLoan);
        v.add(backButton);
        v.repaint();
    }
}