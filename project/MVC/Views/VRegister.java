package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VRegister extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Sign up");
        v.setSize(270, 330);

        JLabel lb=new JLabel("Bank", JLabel.CENTER);
        lb.setBounds(90,20,100,20);//40

        JLabel lbfullname=new JLabel("FullName", JLabel.CENTER);
        lbfullname.setBounds(0,50,130,30);//80

        JLabel lbsocial=new JLabel("SocialNumber", JLabel.CENTER);
        lbsocial.setBounds(0,90,130,30);//120

        JLabel lbcity=new JLabel("City", JLabel.CENTER);
        lbcity.setBounds(0,130,130,30);//160

        JLabel lbaddress=new JLabel("Address", JLabel.CENTER);
        lbaddress.setBounds(0,170,130,30);//200

        JLabel lbpassword=new JLabel("Password", JLabel.CENTER);
        lbpassword.setBounds(0,210,130,30);//240


        JTextField fieldName=new JTextField("",1);
        fieldName.setBounds(131,50,130,30);//80

        JTextField fieldSocial=new JTextField(""+(String)Cache.get("socialNumber"),1);
        fieldSocial.setBounds(131,90,130,30);//120

        JTextField fieldCity=new JTextField("",1);
        fieldCity.setBounds(131,130,130,30);//160

        JTextField fieldAddress=new JTextField("",1);
        fieldAddress.setBounds(131,170,130,30);//200

        JPasswordField fieldPassword=new JPasswordField("",1);
        fieldPassword.setBounds(131,210,130,30);//240


        JButton submit=new JButton("Submit");
        submit.setBounds(130,245,130,40);

        JButton back=new JButton("Back");
        back.setBounds(10,245,120,40);

        submit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String message=(String)self("CRegister","register",false,fieldName.getText(),fieldSocial.getText(),fieldCity.getText(),fieldAddress.getText(),fieldPassword.getText());
                if(message.equals("Welcome to the Bank :)"))
                    control("CLogin",false,true);
                else
                    v.popUpOk(message);
            }
        });
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                control("CLogin",false,false);
            }
        });

        v.add(lb);
        v.add(lbfullname);
        v.add(lbsocial);
        v.add(lbcity);
        v.add(lbaddress);
        v.add(lbpassword);
        v.add(fieldName);
        v.add(fieldSocial);
        v.add(fieldCity);
        v.add(fieldAddress);
        v.add(fieldPassword);
        v.add(back);
        v.add(submit);
        v.repaint();
    }
}