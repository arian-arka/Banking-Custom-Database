package  MVC.System.Helpers;
import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;

public class MyFiles{
    public static long size(String path){
        if(!isFile(path))
            return -1;
        return new File(path).length();
    }
    public static String fileSeparator=System.getProperty("file.separator");
    public static String lineSeparator=System.getProperty("line.separator");
    public static boolean exists(String path){
        return new File(path).exists();
    }
    public static boolean isDirectory(String path){
        File f= new File(path);
        return f.exists() && f.isDirectory();
    }
    public static boolean isFile(String path){
        File f= new File(path);
        return f.exists() && f.isFile();
    }
    public static boolean createFile(String path){
        if(isFile(path))
            return false;
        try {
            File file = new File(path);
            file.createNewFile();
            return true;
        } catch (IOException e) {
            Terminal.writeLine(e.getMessage());
            return false;
        }
    }
    public static boolean removeFile(String path){
        if(!exists(path))
            return false;
        File file = new File(path);
        if(file.delete())
            return true;
        else
            return false;
    }
    public static String readFile(String path){
        try { return new String(Files.readAllBytes(Paths.get(path))).replaceAll("\r\n|\r","\n"); }catch (Exception e){return null;}

    }
    public static boolean createDirectory(String path){
        if(isDirectory(path))
            return false;
        File file = new File(path);
        return file.mkdir();
    }
}