package  MVC.System.Helpers;
import java.util.*;
public class Logger{
    private static boolean disalbed=false;
    public static void disable(){disalbed=true;}
    public static void enable(){disalbed=false;}
    public static void log(String data){if(disalbed || data==null)return;Terminal.writeLine("Log: "+data+"; END");}
    public static void log(String []datas){
        if(disalbed || datas!=null){
            Terminal.writeLine("Log:");
            for(int i=0;i<datas.length;i++)
                Terminal.writeLine(datas[i]);
            Terminal.writeLine("; END");
        }
    }
    public static void log(String prefix,String data){if(disalbed || data==null)return;Terminal.writeLine("Log ("+prefix+"): "+data+"; END");}
    public static void log(String prefix,String []datas){
        if(disalbed || datas!=null){
            Terminal.writeLine("Log ("+prefix+"): ");
            for(int i=0;i<datas.length;i++)
                Terminal.writeLine(datas[i]);
            Terminal.writeLine("; END");
        }
    }
}