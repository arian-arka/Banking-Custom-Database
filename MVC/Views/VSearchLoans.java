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

public class VSearchLoans extends Controller{
    public void index(View v){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Search Loans");
        v.setSize(560, 600);

        JLabel lbid=new JLabel("Customer Id", JLabel.CENTER);
        lbid.setBounds(0,350,130,30);//80
        JTextField idField=new JTextField("",1);
        idField.setBounds(131,350,130,30);//80

        JLabel lbbranch=new JLabel("Branch", JLabel.CENTER);
        lbbranch.setBounds(270,350,130,30);//80
        JTextField branchField=new JTextField("",1);
        branchField.setBounds(401,350,130,30);//80

        JLabel lbloanid=new JLabel("Loan Id", JLabel.CENTER);
        lbloanid.setBounds(0,385,130,30);//80
        JTextField loanidField=new JTextField("",1);
        loanidField.setBounds(131,385,130,30);//80

        JLabel lbtype=new JLabel("Type", JLabel.CENTER);
        lbtype.setBounds(270,385,130,30);//80
        JComboBox typePicker=new JComboBox(new String[]{"Default","Cleared","Open"});
        typePicker.setBounds(270,385,130,30);//80

        JLabel lbbalance=new JLabel("Amount", JLabel.CENTER);
        lbbalance.setBounds(0,420,130,30);//80
        JComboBox balancePicker=new JComboBox(new String[]{"Default","is","not","greater than","greater than or equals","less than","less than or equals"});
        balancePicker.setBounds(131,420,160,30);//80
        JTextField balanceField=new JTextField("",1);
        balanceField.setBounds(305,420,130,30);//80
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
        tableModel.addColumn("Branch");
        tableModel.addColumn("Amount");
        tableModel.addColumn("CustomerId");

        JTable jt=new JTable(tableModel);
        jt.setSize(560, 320);
        JScrollPane sp=new JScrollPane(jt);
        sp.setSize(560, 320);


        v.add(sp);
        v.add(lbid);
        v.add(idField);
        v.add(lbbranch);
        v.add(branchField);
        v.add(typePicker);
        v.add(lbtype);
        v.add(lbbalance);
        v.add(balancePicker);
        v.add(balanceField);
        v.add(lbloanid);
        v.add(loanidField);
        v.add(searchButton);
        v.add(backButton);

        v.repaint();

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loanid=loanidField.getText();
                String id=idField.getText();
                String branch=branchField.getText();
                String balance=balanceField.getText();
                String opbalance=(String)balancePicker.getItemAt(balancePicker.getSelectedIndex());
                if(opbalance.equals("Default")){
                    balance=null;
                    opbalance=null;
                }
                int status=0;
                switch ((String)typePicker.getItemAt(typePicker.getSelectedIndex())){
                    case "Open":
                        status=-1;
                        break;
                    case "Cleared":
                        status=1;
                        break;
                }

                String msg=(String)self("CSearchLoans","validateInput",false,id,balance,loanid);


                if(msg==null) {
                    int rows = tableModel.getRowCount();
                    for(int i = rows - 1; i >=0; i--)
                        tableModel.removeRow(i);
                    String datas[][]=(String[][]) self("CSearchLoans", "search", false,id,loanid,branch,opbalance,balance,status);
                    if(datas!=null)
                        for(int i = 0 ; i <datas.length; i++)
                            tableModel.insertRow(tableModel.getRowCount(),datas[i]);
                }else
                    v.popUpOk(msg);
            }
        });




    }
}