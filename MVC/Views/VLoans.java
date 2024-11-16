package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VLoans extends Controller{
    public void index(View v,String []columns,String [][]rows,String totalAmount){
        v.getContentPane().removeAll();
        v.setJMenuBar(null);
        v.setTitle("Loans");
        v.setSize(300, 330);
        JTable jt;
        if(rows!=null)
            jt=new JTable(rows,columns);
        else
            jt=new JTable(new String[0][0],columns);
        jt.setSize(300, 200);
        JScrollPane sp=new JScrollPane(jt);
        sp.setSize(300, 200);
        JPanel panel=new JPanel(new GridLayout(1,1));
        panel.setSize(300, 200);
        panel.add(sp);

        JLabel lbTotal=new JLabel("Total amount:"+totalAmount, JLabel.CENTER);
        lbTotal.setBounds(10,210,280,30);

        JButton backButton=new JButton("Back");
        backButton.setBounds(10,250,280,35);//285
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                control("CUserMain",false);
            }
        });
        v.add(panel);
        v.add(lbTotal);
        v.add(backButton);
        v.repaint();
    }
}