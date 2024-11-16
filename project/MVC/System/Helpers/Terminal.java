package  MVC.System.Helpers;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Terminal{
    private static final Scanner input=new Scanner(System.in);
    public static  void sleep(int time){
        try {
            Thread.sleep(time);
        }catch (Exception e){
        }
    }
    public static  void showLoading(int interval,int count,String data,String prefix){
        System.out.print(prefix);
        for(int i =0;i<count;i++){
            Terminal.sleep(interval);
            System.out.print(data);
        }
        System.out.print("\n");
    }
    public static void clearScreen(){
        try {
            if (System.getProperty("os.name").contains("Windows")){
                Runtime.getRuntime().exec("cls");
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
        System.out.print("\033\143");
        System.out.flush();
    }
    public static void pressEnter(String error){
        System.out.print(error+"- press enter to continue");
        Terminal.input.nextLine();
    }
    public static void pressEnter(){
        System.out.print("Press enter to continue...");
        Terminal.input.nextLine();
    }
    public static double readDouble(String prefix,String error){
        String data;
        while (true){
            System.out.print(prefix);
            data=Terminal.input.nextLine();
            if(Validate.isValid(data,"number"))
                return Double.parseDouble(data);
            Terminal.pressEnter(error);
        }
    }
    public static double readDouble(){
        String data;
        while (true){
            System.out.print("Enter double number: ");
            data=Terminal.input.nextLine();
            if(Validate.isValid(data,"number"))
                return Double.parseDouble(data);
            Terminal.pressEnter("please enter valid double");
        }
    }
    public static int readInt(String prefix,String error){
        String data;
        while (true){
            System.out.print(prefix);
            data=Terminal.input.nextLine();
            if(Validate.isValid(data,"int"))
                return Integer.parseInt(data);
            Terminal.pressEnter(error);
        }
    }
    public static int readInt(){
        String data;
        while (true){
            System.out.print("Enter integer number: ");
            data=Terminal.input.nextLine();
            if(Validate.isValid(data,"int"))
                return Integer.parseInt(data);
            Terminal.pressEnter("please enter valid int");
        }
    }
    public static String readLine(){
        System.out.print("Enter string: ");
        return Terminal.input.nextLine();
    }
    public static String readLine(String prefix){
        System.out.print(prefix);
        return Terminal.input.nextLine();
    }
    public static boolean multipleValidation(String data,String [] filters,boolean checkAll,boolean caseSensitive) {
        if (checkAll){
            for (String filter : filters)
                if (!Validate.isValid(data, filter,caseSensitive))
                    return false;
            return true;
        }
        else{
            for(String filter:filters)
                if(Validate.isValid(data,filter,caseSensitive))
                    return true;
            return false;
        }

    }
    public static String readLine(String prefix,String error,String []filters,boolean checkAll){
        return Terminal.readLine( prefix, error,filters, checkAll,true);
    }
    public static String readLine(String prefix,String error,String []filters,boolean checkAll,boolean caseSensitive){
        String data;
        while (true){
            System.out.print(prefix);
            data=Terminal.input.nextLine();
            if(Terminal.multipleValidation(data,filters,checkAll,caseSensitive))
                return (caseSensitive?data.toLowerCase():data);
            Terminal.pressEnter(error);
        }
    }
    public static String readLine(String prefix,String error,String filter){
        String data;
        while (true){
            System.out.print(prefix);
            data=Terminal.input.nextLine();
            if(Validate.isValid(data,filter,true))
                return data;
            Terminal.pressEnter(error);
        }
    }
    public static String readLine(String prefix,String error,String filter, boolean caseSensitive){
        String data;
        while (true){
            System.out.print(prefix);
            data=Terminal.input.nextLine();
            if(Validate.isValid(data,filter,caseSensitive))
                return (caseSensitive?data.toLowerCase():data);
            Terminal.pressEnter(error);
        }
    }
    public static double convert2double(String data){
        return  Double.parseDouble(data.replaceAll("\0",""));
    }
    public static int convert2int(String data){
        return Integer.parseInt(data.replaceAll("\0",""));
    }
    public static long convert2long(String data){
        return Long.parseLong(data.replaceAll("\0",""));
    }
    public static void writeLine(String data){
        System.out.println(data);
    }
    public static void write(String data){
        System.out.print(data);
    }

}