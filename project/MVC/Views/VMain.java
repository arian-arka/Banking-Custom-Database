package MVC.Views;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VMain extends Controller{
    public void index(View v,String name){
        Terminal.writeLine("main view...index arg:"+name);
    }
}