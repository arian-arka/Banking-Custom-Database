package  MVC.System.Helpers;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Validate{
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
    public static boolean isValid(String value,String filter,boolean caseSensitive) {
        if(caseSensitive)
            return Validate.preDefined(value,filter);
        return Validate.preDefined(value.toLowerCase(),filter);
    }
    public static boolean isValid(String value,String filter) {
        return Validate.isValid(value,filter,false);
    }
    public static boolean preDefined(String value,String filter){

        switch (filter){
            case "boolean":
            case "bool":
                return (value=="true"||value=="false")?true:false;
            case "number":
            case "float":
                try {
                    double tmp=Double.parseDouble(value);
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "negetivenumber":
            case "negetivefloat":
                try {
                    double tmp=Double.parseDouble(value);
                    if(tmp<0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
            case "positivenumber":
            case "positivefloat"://Integer.parseInt
                try {
                    double tmp=Double.parseDouble(value);
                    if(tmp>=0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
            case "int":
                try {
                    int tmp=Integer.parseInt(value);
                    return true;
                }catch (Exception e){
                    return false;
                }
            case "negetiveint":
                try {
                    int tmp=Integer.parseInt(value);
                    if(tmp<0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
            case "positiveint":
                try {
                    int tmp=Integer.parseInt(value);
                    if(tmp>=0)
                        return true;
                    return false;
                }catch (Exception e){
                    return false;
                }
        }
        double fil;
        double val;
        char first=filter.charAt(0);
        char second=filter.charAt(1);
        if(first=='>'&&second=='='){
            try {
                fil=Double.parseDouble(filter.substring(2));
                val=Double.parseDouble(value);

                if(val>=fil)
                    return true;

                return false;
            }catch (Exception e){

                return false;
            }
        }
        else if(first=='>'){
            try {
                fil=Double.parseDouble(filter.substring(1));
                val=Double.parseDouble(value);
                if(val>fil)
                    return true;
                return false;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='<'&&second=='='){
            try {
                fil=Double.parseDouble(filter.substring(2));
                val=Double.parseDouble(value);
                if(val<=fil)
                    return true;
                return false;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='<'){
            try {
                fil=Double.parseDouble(filter.substring(1));
                val=Double.parseDouble(value);
                if(val<fil)
                    return true;
                return false;
            }catch (Exception e){
                return false;
            }
        }
        else if(first=='='&&second=='='){
            return value.equals(filter.substring(2));
        }
        else if(first=='!'&&second=='='){
            return !(value.equals(filter.substring(2)));
        }
        return Validate.range(value,filter);
    }
    public static boolean range(String value,String filter){
        int indexRange=filter.indexOf("range(");

        if(indexRange!=0 || filter.charAt(filter.length()-1)!=')')
            return false;
        try {
            String range=filter.substring(6,filter.length()-1);
            String []ranges=range.split(",");
            if(ranges.length<2)
                return false;
            //io.writeLine("#1");
            double val1;
            double val2;
            double val=Double.parseDouble(value);
            if(ranges[0].length()==0 &&ranges[1].length()==0)
                return false;
            if(ranges[0].length()==0){
                val2=Double.parseDouble(ranges[1]);
                if(val<=val2)
                    return true;
                return false;
            }
            if(ranges[1].length()==0){
                val1=Double.parseDouble(ranges[0]);
                if(val>=val1)
                    return true;
                return false;
            }
            val1=Double.parseDouble(ranges[0]);
            val2=Double.parseDouble(ranges[1]);
            if(val>=val1 && val<=val2)
                return true;
            return false;

        }
        catch (Exception e ){
            return false;
        }
    }
    public static boolean isAlpha(String str){
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[a-zA-Z]*$")));
    }
    public static boolean isNumber(String str){
        if(str==null)
            return false;
        return ((str != null)
                && (!str.equals(""))
                && (str.matches("^[0-9]*$")));
    }

}