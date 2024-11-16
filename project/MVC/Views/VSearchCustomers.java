package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class VSearchCustomers extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Search Customers");
        v.setSize(560, 600);

        JLabel lbcustomerid=new JLabel("Customer Id", JLabel.CENTER);
        lbcustomerid.setBounds(0,350,130,30);//80
        JTextField customeridField=new JTextField("",1);
        customeridField.setBounds(131,350,130,30);//80

        JLabel lbsocialnumber=new JLabel("Social Number", JLabel.CENTER);
        lbsocialnumber.setBounds(270,350,130,30);//80
        JTextField socialnumberField=new JTextField("",1);
        socialnumberField.setBounds(401,350,130,30);//80

        JLabel lbfullName=new JLabel("FullName", JLabel.CENTER);
        lbfullName.setBounds(0,385,130,30);//80
        JTextField fullNameField=new JTextField("",1);
        fullNameField.setBounds(131,385,130,30);//80

        JLabel lbcity=new JLabel("City", JLabel.CENTER);
        lbcity.setBounds(270,385,130,30);//80
        JTextField cityField=new JTextField("",1);
        cityField.setBounds(401,385,130,30);//80

        JLabel lbaddress=new JLabel("Address", JLabel.CENTER);
        lbaddress.setBounds(0,420,130,30);//80
        JTextField addressField=new JTextField("",1);
        addressField.setBounds(131,420,130,30);//80

        JCheckBox isDebator=new JCheckBox("Debator");
        isDebator.setBounds(401,420,130,30);

        JLabel lbbalance=new JLabel("Total Balance", JLabel.CENTER);
        lbbalance.setBounds(0,455,130,30);//80
        JComboBox balancePicker=new JComboBox(new String[]{"Default","is","not","greater than","greater than or equals","less than","less than or equals"});
        balancePicker.setBounds(131,455,160,30);//80
        JTextField balanceField=new JTextField("",1);
        balanceField.setBounds(305,455,130,30);//80
        balanceField.setEditable(false);
        balancePicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch ((String)balancePicker.getItemAt(balancePicker.getSelectedIndex())){
                    case "Default":
                        balanceField.setText("");
                        balanceField.setEditable(false);
                        break;
                    default:
                        balanceField.setEditable(true);
                }
            }
        });

        JLabel lbloan=new JLabel("Total Loan", JLabel.CENTER);
        lbloan.setBounds(0,490,130,30);//80
        JComboBox loanPicker=new JComboBox(new String[]{"Default","is","not","greater than","greater than or equals","less than","less than or equals"});
        loanPicker.setBounds(131,490,160,30);//80
        JTextField loanField=new JTextField("",1);
        loanField.setBounds(305,490,130,30);//80
        loanField.setEditable(false);
        loanPicker.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switch ((String)loanPicker.getItemAt(loanPicker.getSelectedIndex())){
                    case "Default":
                        loanField.setText("");
                        loanField.setEditable(false);
                        break;
                    default:
                        loanField.setEditable(true);
                }
            }
        });

        JButton searchButton=new JButton("Search");
        searchButton.setBounds(440,455,91,30);//165
        JButton backButton=new JButton("Back");
        backButton.setBounds(440,490,91,30);//165
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CMasterMain",false);
            }
        });

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("Id");
        tableModel.addColumn("SocialNumber");
        tableModel.addColumn("FullName");
        tableModel.addColumn("City");
        tableModel.addColumn("Address");
        tableModel.addColumn("Balance");
        tableModel.addColumn("Loan");

        JTable jt=new JTable(tableModel);
        jt.setSize(560, 320);
        JScrollPane sp=new JScrollPane(jt);
        sp.setSize(560, 320);


        v.add(sp);
        v.add(isDebator);
        v.add(lbloan);
        v.add(loanPicker);
        v.add(loanField);
        v.add(lbbalance);
        v.add(balancePicker);
        v.add(balanceField);
        v.add(lbfullName);
        v.add(fullNameField);
        v.add(lbcity);
        v.add(cityField);
        v.add(lbaddress);
        v.add(addressField);
        v.add(lbcustomerid);
        v.add(customeridField);
        v.add(lbsocialnumber);
        v.add(socialnumberField);
        v.add(searchButton);
        v.add(backButton);

        v.repaint();

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id=customeridField.getText();
                String social=socialnumberField.getText();
                String name=fullNameField.getText();
                String city=cityField.getText();
                String address=addressField.getText();
                String balance=balanceField.getText();
                String opbalance=(String)balancePicker.getItemAt(balancePicker.getSelectedIndex());
                if(opbalance.equals("Default")){
                    balance=null;
                    opbalance=null;
                }
                String loan=loanField.getText();
                String oploan=(String)loanPicker.getItemAt(loanPicker.getSelectedIndex());
                if(oploan.equals("Default")){
                    loan=null;
                    oploan=null;
                }
                boolean debator=isDebator.isSelected();
                String msg=(String)self("CSearchCustomers","validateInput",false,id,social,balance,loan);


                if(msg==null) {
                    int rows = tableModel.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                        tableModel.removeRow(i);
                    String datas[][]=(String[][]) self("CSearchCustomers", "search", false, id, social, name, city, address, opbalance, balance, oploan, loan, debator);
                    if(datas!=null)
                        for(int i = 0 ; i <datas.length; i++)
                            tableModel.insertRow(tableModel.getRowCount(),datas[i]);
                }else
                    v.popUpOk(msg);
            }
        });




    }
}