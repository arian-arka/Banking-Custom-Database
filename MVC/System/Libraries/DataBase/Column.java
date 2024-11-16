package  MVC.System.Libraries.DataBase;

import MVC.System.Helpers.*;

import java.util.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Column{


    public void print(){
        Terminal.writeLine("---------------");
        Terminal.writeLine("name: "+name);
        Terminal.writeLine("type: "+type);
        Terminal.writeLine("lenType: "+lenType);
        Terminal.writeLine("size: "+size);
        options.print();
        Terminal.writeLine("---------------");
    }

    public String name="";
    public String getName(){
        return this.name;
    }
    public void setName(String name){ this.name=name; }

    public int lenType=0;
    public int getLenType(){
        return this.lenType;
    }
    void setLenType(int lenType){ this.lenType=lenType; }

    public String type="";
    public String getType(){
        return this.type;
    }
    void setType(String type){ this.type=type; }

    public int size=0;
    public int getSize(){
        return this.size;
    }
    void setSize(int size){
        this.size=size;
    }
    public void generateSize(){
        if(lenType>0)
        {
            setSize(lenType);
            return;
        }
        switch (type){
            case "tinytext": setSize(255);
                break;
            case "text":setSize(65535);
                break;
            case "tinyint": setSize(4);
                break;
            case "unsinged tinyint":setSize(3);
                break;
            case "bool":setSize(1);
                break;
            case "meduimint":
            case "unsinged meduimint":setSize(8);
                break;
            case "int":setSize(11);
                break;
            case "unsinged int":setSize(10);
                break;
            case "bigint":
            case "unsinged bigint":setSize(20);
                break;
            case "double":setSize(23);
                break;
            case "date":setSize(8);
                break;
            case "time":setSize(6);
                break;
            case "datetime":setSize(14);
                break;
        }
    }

    public ColumnOptions options;

    private void setOptions(ColumnOptions options){
        this.options=options;
    }

    private static String ValidTypes[]=new String[]{
            "==char",//0-255
            "==varchar",//0-65535
            "==tinytext",//max:255
            "==text",//max:65535
            "==tinyint",//-128 127 0 255
            "==unsinged tinyint",//0 255
            "==bool",//0 1
            "==smallint",//0 65535 -32768 32767
            "==unsinged smallint",//0 65535
            "==meduimint",//0 16777215 -8388608 8388607
            "==unsinged meduimint",//0 16777215
            "==int",//-2147483648 and 2147483647
            "==unsinged int",//0 and  4294967295
            "==bigint",// -9223372036854775808 9223372036854775807 //20
            "==unsinged bigint",// 0 18446744073709551615
            "==double",//-1.7976931348623157E308
            "==date",
            "==datetime",
            "==time"
    };
    public static String convertToWriteableData(String value,String type){

        switch (type.toLowerCase()){
            case "double":
                return value+fileChannel.emptyString(23-value.length());
            case "tinytext":
                return value+fileChannel.emptyString(255-value.length());
            case "text":
                return value+fileChannel.emptyString(65535-value.length());
            case "bool":
                return value.equals("true")?"1":"0";
            case "tinyint":
                return value+fileChannel.emptyString(4-value.length());
            case "unsinged tinyint":
                return value+fileChannel.emptyString(3-value.length());
            case "smallint":
                return value+fileChannel.emptyString(6-value.length());
            case "unsinged smallint":
                return value+fileChannel.emptyString(5-value.length());
            case "meduimint":
            case "unsinged meduimint":
                return value+fileChannel.emptyString(6-value.length());
            case "int":
                return value+fileChannel.emptyString(11-value.length());
            case "unsinged int":
                return value+fileChannel.emptyString(10-value.length());
            case "bigint":
            case "unsinged bigint":
                return value+fileChannel.emptyString(20-value.length());
            case "date":
                return value.substring(0,4)+value.substring(5,7)+value.substring(8,10);
            case "time":
                return value.substring(0,2)+value.substring(3,5)+value.substring(6,8);
            case "datetime":
                return value.substring(0,4)+value.substring(5,7)+value.substring(8,10)+value.substring(11,13)+value.substring(14,16)+value.substring(17,19);
        }
        //Terminal.writeLine("@@@@@@@@@@@");
        return "@@@@@@@@@";
    }
    public static String convertToWriteableData(String value,String type,int lenType){
        switch (type.toLowerCase()){
            case"char":
                return value+fileChannel.emptyString(lenType-value.length());
            case "varchar":
                return value+fileChannel.emptyString(lenType-value.length());
        }

        return convertToWriteableData(value,type);
    }
    public Object convertToActualData(String data){
        String actualDaat=data.replaceAll("\0","");
        switch (type.toLowerCase()){
            case"bool":
                return actualDaat.equals("1")?true:false;
            case"char":
            case"varchar":
            case"tinytext":
            case"text":
                return actualDaat;
            case"tinyint":
                return Byte.parseByte(actualDaat);
            case"unsinged tinyint":
            case"smallint":
                return Short.parseShort(actualDaat);
            case"unsinged  smallint":
            case"int":
            case"meduimint":
            case"unsinged  meduimint":
                return Integer.parseInt(actualDaat);
            case"unsinged int":
            case"bigint":
            case"unsinged bigint":
                return Long.parseLong(actualDaat);
            case"double":
                return Double.parseDouble(actualDaat);
            case"date":
                return actualDaat.substring(0,5)+":"+actualDaat.substring(5,7)+":"+actualDaat.substring(7,9);
            case"time":
                return actualDaat.substring(0,3)+":"+actualDaat.substring(3,5)+":"+actualDaat.substring(5,7);
            case"datetime":
                return actualDaat.substring(0,5)+":"+actualDaat.substring(5,7)+":"+actualDaat.substring(7,9)+":"+actualDaat.substring(9,11)+":"+actualDaat.substring(11,13)+":"+actualDaat.substring(13,15);
        }
        return "@invalid getting data";
    }


    Column(String name,String type,int lenType){
        this.name=name;
        this.type=type;
        this.lenType=lenType;
        generateSize();
    }
    Column(String name,String type){
        this.name=name;
        this.type=type;
        generateSize();
    }
    Column(){

    }

    public void clear(){
        name="";
        lenType=0;
        type="";
        size=0;
    }

    public static boolean validDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean validTime(String date) {
        try {
            DateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean validDateTime(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean validValue(String value,Column column){
        switch (column.type){
            case "double":
                try {Double.parseDouble(value);return true;}catch (Exception e){return false;}
            case "date":
                return validDate(value);
            case "time":
                return validTime(value);
            case "datetime":
                return validDateTime(value);
            case "char":
                //Terminal.writeLine("checking varchar "+value);
                return value.length()<=column.lenType && value.length()!=0;
            case "varchar":
                return value.length()<=column.lenType && value.length()!=0;
            case "tinytext":
                return value.length()<=255 && value.length()!=0;
            case "text":
                return value.length()<=65536 && value.length()!=0;
            case "bool":
                return value.equals("true") || value.equals("false") ;
            case"tinyint":
                try {Byte.parseByte(value);return true;}catch (Exception e){return false;}
            case"unsinged tinyint":
                try {
                    short num=Short.parseShort(value);
                    if (num<=255 &&num>=0)
                        return true;
                }catch (Exception e){return false;}
            case"smallint":
                try {short num=Short.parseShort(value);return true;}catch (Exception e){return false;}
            case"unsinged smallint":
                try {
                    int num=Terminal.convert2int(value);
                    if (num<=65535 &&num>0)
                        return true;
                }catch (Exception e){return false;}
            case"meduimint":
                try {
                    int num=Terminal.convert2int(value);
                    if (num<8388607 &&num>=-8388608)
                        return true;
                }catch (Exception e){return false;}
            case"unsinged meduimint":
                try {
                    int num=Terminal.convert2int(value);
                    if (num<=16777215 &&num>0)
                        return true;
                }catch (Exception e){return false;}
            case"int":
                try {
                    Terminal.convert2int(value);
                    return true;
                }catch (Exception e){return false;}
            case"unsinged int":
                try {
                    long num=Terminal.convert2long(value);
                    if (num<=4294967295L && num>0)
                        return true;
                }catch (Exception e){return false;}
            case"bigint":
                try {
                    Terminal.convert2long(value);
                    return true;
                }catch (Exception e){return false;}
            case"unsinged bigint":
                try {
                    Long.parseUnsignedLong(value);
                    return true;
                }catch (Exception e){return false;}
        }
        return false;
    }
    public static boolean validValue(String value,String type){
        switch (type){
            case "double":
                try {Double.parseDouble(value);return true;}catch (Exception e){return false;}
            case "date":
                return validDate(value);
            case "time":
                return validTime(value);
            case "datetime":
                return validDateTime(value);
            case "tinytext":
                return value.length()<255 && value.length()!=0;
            case "text":
                return value.length()<65536 && value.length()!=0;
            case "bool":
                return value.equals("false") || value.equals("true") ;
            case"tinyint":
                try {Byte.parseByte(value);return true;}catch (Exception e){return false;}
            case"unsinged tinyint":
                try {
                    short num=Short.parseShort(value);
                    if (num<=255 &&num>=0)
                        return true;
                }catch (Exception e){return false;}
            case"smallint":
                try {short num=Short.parseShort(value);return true;}catch (Exception e){return false;}
            case"unsinged smallint":
                try {
                    int num=Terminal.convert2int(value);
                    if (num<=65535 &&num>0)
                        return true;
                }catch (Exception e){return false;}
            case"meduimint":
                try {
                    int num=Terminal.convert2int(value);
                    if (num<8388607 &&num>=-8388608)
                        return true;
                }catch (Exception e){return false;}
            case"unsinged meduimint":
                try {
                    int num=Terminal.convert2int(value);
                    if (num<=16777215 &&num>0)
                        return true;
                }catch (Exception e){return false;}
            case"int":
                try {
                    Terminal.convert2int(value);
                    return true;
                }catch (Exception e){return false;}
            case"unsinged int":
                try {
                    long num=Terminal.convert2long(value);
                    if (num<=4294967295L && num>0)
                        return true;
                }catch (Exception e){return false;}
            case"bigint":
                try {
                    Terminal.convert2long(value);
                    return true;
                }catch (Exception e){return false;}
            case"unsinged bigint":
                try {
                    Long.parseUnsignedLong(value);
                    return true;
                }catch (Exception e){return false;}
        }
        return false;
    }
    public static boolean validValue(String value,String type,int lenType){
        switch (type){
            case "char":
                return value.length()<lenType && value.length()!=0;
            case "varchar":
                return value.length()<lenType && value.length()!=0;
        }
        return false;
    }
    public static boolean validType(String type){
        switch (type.toLowerCase()){
            case "char":
            case "varchar":
                return false;
            default:
                return Validate.multipleValidation(type,ValidTypes,false,false);
        }

    }
    public static boolean validType(String type,int lenType) {
        if(type.toLowerCase().equals("char") && lenType!=0 && lenType<256)
            return true;
        if(type.toLowerCase().equals("varchar") && lenType!=0 && lenType<65536)
            return true;
        return validType(type);
    }

    public static boolean compare(Object obj1,Object obj2,String type){
        switch (type){
            case "date":
            case "time":
            case "datetime":
            case "char":
            case "varchar":
            case "tinytext":
            case "text":
                return String.valueOf(obj1).equals(String.valueOf(obj2));
            default:
                return obj1==obj2;
        }

    }

    public static Column getInstance(String name,String type){
        if(Validate.isAlpha(name) && validType(type))
        {
            ColumnOptions op=new ColumnOptions();
            Column col= new Column(name,type);
            col.setOptions(ColumnOptions.getInstance(false,false,false,""));
            return col;
        }
        return null;
    }
    public static Column getInstance(String name,String type,String options[]){

        if(Validate.isAlpha(name) && validType(type)) {

            ColumnOptions op=new ColumnOptions();
            String para;
            for (String option:options){
                switch (option.toLowerCase()){
                    case"autoincrement":
                        if(Validate.multipleValidation(type,new String[]{"==tinyint","==unsinged tinyint","==smallint","==unsinged smallint","==meduimint","==unsinged meduimint","==int","==unsinged int","==bigint","==unsinged bigint"},false,false))
                        {op.autoIncrement=true;
                            op.acceptNull=true;}
                        else
                            return null;
                        break;
                    case "unique":
                        op.unique=true;
                        break;
                    case "null":
                        op.acceptNull=true;
                        break;
                    case"notnull":
                        if(op.autoIncrement)
                            return null;
                        op.acceptNull=false;
                        break;
                    default:
                        if(option.length()<14)
                            return null;
                        if(!option.substring(0,13).toLowerCase().equals("defaultvalue:"))
                            return null;
                        if(option.charAt(13)=='\"') {
                            if(option.length()<15 || option.charAt(option.length()-1)!='\"')
                                return null;
                            if(!Validate.multipleValidation(type,new String[]{"==tinytext","==text","==date","==time","==datetime"},false,false))
                                return null;
                            para=option.substring(14,option.length()-1);
                            if(!validValue(para,type))
                                return null;
                            op.defaultValue=para;
                        }
                        else{
                            if(Validate.multipleValidation(type,new String[]{"==tinytext","==text","==date","==time","==datetime"},false,false))
                                return null;
                            para=option.substring(13,option.length());
                            if(!validValue(para,type))
                                return null;
                            op.defaultValue=para;
                        }
                }
            }

            Column col= new Column(name,type);
            col.setOptions(op);
            return col;
        }
        return null;
    }
    public static Column getInstance(String name,String type,int lenType,String options[]){
        if(Validate.isAlpha(name) && validType(type,lenType))
        {
            ColumnOptions op=new ColumnOptions();
            String para;
            for (String option:options){
                switch (option.toLowerCase()){
                    case"autoIncrement":
                        return null;
                    case "unique":
                        op.unique=true;
                        break;
                    case "null":
                        op.acceptNull=true;
                        break;
                    case"notnull":
                        op.acceptNull=false;
                        break;
                    default:

                        if(option.length()<14)
                            return null;
                        if(!option.substring(0,13).toLowerCase().equals("defaultvalue:"))
                            return null;
                        if(option.charAt(13)=='\"') {
                            if(option.length()<15 || option.charAt(option.length()-1)!='\"')
                                return null;
                            if(!Validate.multipleValidation(type,new String[]{"==char","==varchar"},false,false))
                                return null;

                            para=option.substring(14,option.length()-1);
                            if(!validValue(para,type,lenType))
                                return null;
                            op.defaultValue=para;
                        }
                        else{
                            return null;
                        }
                }
            }
            Column col= new Column(name,type,lenType);
            col.setOptions(op);
            return col;
        }
        return null;
    }
    public static Column getInstance(String name,String type,int lenType){
        if(Validate.isAlpha(name) && validType(type,lenType)) {
            Column col= new Column(name,type,lenType);
            col.setOptions(ColumnOptions.getInstance(false,false,false,""));
            return col;
        }
        return null;
    }

    public  boolean equals(String name){
        //Terminal.writeLine(name+"=="+name);
        return name.toLowerCase().equals(this.name.toLowerCase());
    }
}