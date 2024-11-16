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


public abstract class DataBaseStructure{
    public static String baseOfAll=".\\databases\\";
    protected String basePath;
    protected String name;
    protected String salt;
    protected HashMap<String, String> users=new HashMap<String, String>();
    DataBaseStructure(String name ,String salt){
        this.name=name;
        this.basePath=baseOfAll+name+MyFiles.fileSeparator;
        this.salt=salt;
        loadUsers();
    }

    private void loadUsers(){
        Logger.log("Loading users of "+name+" database");
        if(!MyFiles.isFile(basePath+".conf")){
            MyFiles.createFile(basePath+".conf");
            addUser("root","root");
        }

        String file=MyFiles.readFile(basePath+".conf");
        String user="",pass="";
        boolean flag=false;
        for(char ch:file.toCharArray()) {
            if (flag) {
                if (ch == '\n') {
                    users.put(
                            user,
                            pass
                    );
                    Logger.log("Load user:"+user+" pass:"+pass);
                    flag = false;
                    user = "";
                    pass = "";
                } else
                    pass += ch;
            } else {
                if (ch == '\n')
                    flag = true;
                else
                    user += ch;
            }
        }
    }
    public boolean userExists(String username,String password){
        String hashedUser=PasswordHashing.hash(username,salt);
        if(users.containsKey(hashedUser))
            if(users.get(hashedUser).equals(PasswordHashing.hash(password,salt)))
                return true;
        return false;
    }
    public boolean userNameExists(String username){
        String hashedUser=PasswordHashing.hash(username,salt);
        if(users.containsKey(hashedUser))
            return true;
        return false;
    }
    public boolean addUser(String username,String password){
        Logger.log("adding user:"+username+" pass:"+password);
        if(userNameExists(username)) {
            Logger.log("user:"+username+" exists");
            return false;
        }
        String hashedUser=PasswordHashing.hash(username,salt);
        String hashedPass=PasswordHashing.hash(password,salt);
        try {
            Files.write(Paths.get(basePath+".conf"), (hashedUser+MyFiles.lineSeparator+hashedPass+MyFiles.lineSeparator).getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) { return false; }
        Logger.log("user:"+username+" added");
        users.put(hashedUser,hashedPass);
        return true;
    }
    public String generateTablePath(String table){
        return basePath+table+".table";
    }
    public boolean tableExists(String name){
        return MyFiles.isFile(generateTablePath(name));
    }
}