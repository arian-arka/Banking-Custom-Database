package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VLogin extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Login");
        v.setSize(270, 220);
        JLabel lb=new JLabel("Bank", JLabel.CENTER);
        lb.setBounds(90,20,100,20);//40

        JLabel lbuser=new JLabel("Social Number", JLabel.CENTER);
        lbuser.setBounds(0,50,130,30);//80

        JLabel lbpass=new JLabel("Password", JLabel.CENTER);
        lbpass.setBounds(0,90,130,30);//120

        JTextField textField=new JTextField("",1);
        textField.setBounds(131,50,130,30);//80
        JPasswordField passwordField=new JPasswordField("",1);
        passwordField.setBounds(131,90,130,30);//120


        JButton loginButton=new JButton("Login");
        loginButton.setBounds(130,125,130,40);//165
        JButton registerButton=new JButton("Sign up");
        loginButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                self("CLogin","login",false,textField.getText(),passwordField.getText());
            }
        });

        registerButton.setBounds(10,125,120,40);//165
        registerButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                Cache.set("socialNumber",textField.getText()==null?"":textField.getText());
                control("CRegister",false);
            }
        });

        v.add(lb);
        v.add(lbuser);
        v.add(lbpass);
        v.add(textField);
        v.add(passwordField);
        v.add(loginButton);
        v.add(registerButton);

        v.repaint();
    }
}