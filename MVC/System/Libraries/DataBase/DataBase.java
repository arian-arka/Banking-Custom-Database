package  MVC.System.Libraries.DataBase;

import MVC.System.Helpers.*;

import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;


public class DataBase extends DataBaseStructure{

    public Table table(String name){
        if(super.tableExists(name))
            return Table.load(name,this.name);
        return null;
    }
    DataBase(String name){
        super(name,"i853bffh823yuhfkpwudbnx4");
    }
    public static DataBase create(String name){
        Logger.log("creating database:"+name);
        if(exists(name)||!MyFiles.createDirectory(DataBaseStructure.baseOfAll+name)) {
            Logger.log("database:"+name+" exists");
            return null;
        }
        return load(name,"root","root");
    }
    public static boolean exists(String name){
        Logger.log("Check for existence"+name);
        return MyFiles.isDirectory(DataBaseStructure.baseOfAll+MyFiles.fileSeparator+name);
    }
    public static DataBase load(String name,String username,String password){
        Logger.log("loading database:"+name);
        
        if(!exists(name)){
            Logger.log("database:"+name+" does not exist");
            return null;
        }
        DataBase db= new DataBase(name);
        if(db==null || !db.userExists(username,password)){
            Logger.log("database:"+name+" invalid user("+username+") and pass("+password+")");
            return null;
        }
        Logger.log("database:"+name+"loaded");
        return db;
    }
    public boolean createTable(String name,Columns cols){
        Logger.log("creating table:"+name+" in:"+super.name);
        Table tb=Table.create(name,this.name,cols);
        if (tb==null){
            Logger.log("table:"+name+" exists");
            return false;
        }
        Logger.log("table:"+name+" created");
        return true;
    }
}