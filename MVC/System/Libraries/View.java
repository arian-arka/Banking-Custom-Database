package MVC.System.Libraries;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import MVC.System.Helpers.*;

public class View extends JFrame{
    JPanel sheetPanel=new JPanel();
    public View(){
        Logger.log("Initialing View");
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        SHOW();
    }
    public void SHOW(){
        this.setVisible(true);
    }
    public void HIDE(){
        this.setVisible(false);
    }
    public void REMOVE_CONTENT(){
        this.getContentPane().removeAll();
        this.removeAll();
        this.revalidate();
        this.repaint();
    }
    public void popUpOk(String message)
    {
        JOptionPane.showMessageDialog(this,message);
    }

}