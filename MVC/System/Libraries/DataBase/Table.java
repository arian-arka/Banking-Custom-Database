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


class fileChannel{
    FileChannel channel;
    fileChannel(FileChannel channel){
        this.channel=channel;

    }
    public static fileChannel getInstance(String path){
        if(!MyFiles.isFile(path))
            return null;
        try {
            FileChannel channel = (FileChannel) Files.newByteChannel(Paths.get(path), READ, WRITE);
            return new fileChannel(channel);
        }
        catch (Exception e){return null;}
    }
    public void close(){
        try {
            channel.close();
        } catch (Exception e){}
    }
    public void seek(long index){
        try {
            channel.position(index);
        } catch (Exception e){Terminal.writeLine("didnt seeked");}
    }
    public long tell(){
        try {
            return channel.position();
        } catch (Exception e){return -1;}
    }
    public void rewind(){
        seek(0);
    }
    public void write(long index,String data){
        byte[] byteData = data.getBytes();
        ByteBuffer out = ByteBuffer.wrap(byteData);
        seek(index);
        try {
            while(out.hasRemaining())
                channel.write(out);
            channel.force(true);
        }catch (Exception e){}
    }
    public long size(){
        try {
            return channel.size();
        }catch(Exception e){return -1;}

    }
    public void append(String data){
        write(size(),data);
    }
    public void readAll(){

    }
    public String read(long start,long end){
        try{
            long remaining=end-start;
            String data="";
            seek(start);
            ByteBuffer buff;
            while (true){
                //Terminal.writeLine("reading");
                if(remaining>=1024)
                    buff= ByteBuffer.allocate(1024);
                else
                    buff= ByteBuffer.allocate((int)remaining);
                channel.read(buff);
                data+=new String(buff.array(), "UTF-8");
                remaining-=1024;
                if(remaining<=0)
                    return data;
                buff.clear();
            }
        }
        catch (Exception e){
            return null;
        }
    }
    public static String emptyString(int len){
        if(len==0)
            return "";
        char[] array = new char[len];
        Arrays.fill(array, '\0');
        return new String(array);
    }
}

public class Table{
    fileChannel file;

    public String name;

    public Columns columns;

    public  long lenRow;

    public long startIndex;

    Table(String name,fileChannel file,Columns columns,int lenStrDescriptio){
        this.name=name;
        this.file=file;
        this.columns=columns;
        this.lenRow=columns.rowLength();
        startIndex=lenStrDescriptio+20;
    }

    private void write(long row,String data){
        long index=indexOfRow(row);
        file.write(index,data);
    }
    private String read(long row){
        long index=indexOfRow(row);
        return file.read(index,index+lenRow);
    }

    public Object readRowElement(long row,String column){
        Logger.log("reading column("+column+") of row("+row+") in:"+name);
        long start=indexOfRow(row)+columns.indexOfElement(column);
        long end=start+columns.column(column).size;
        return columns.column(column).convertToActualData(file.read(start,end));
    }
    private Object readRowElement(String row,String column){
        Logger.log("reading column("+column+") of row("+row+") in:"+name);
        return columns.column(column).convertToActualData(row);
    }
    private Object readRowElement(String row,int column){
        Logger.log("reading column("+column+") of row("+row+") in:"+name);
        return columns.column(column).convertToActualData(row);
    }

    public void writeRowElement(long row,String value,String column){
        long start=indexOfRow(row)+columns.indexOfElement(column);
        file.write(start,Column.convertToWriteableData(value,columns.column(column).type,columns.column(column).lenType));
    }
    public Row getRow(long rowNumber){
        Logger.log("Getting row("+rowNumber+") in:"+name);
        if(rowNumber>rowsCount() || rowNumber<=0) {
            Logger.log("Invalid row in:"+name);
                return null;
        }
        String data=read(rowNumber);
        Row row=new Row();
        int indexStart=0;
        for(int i=0;i<columns.columnsLen();i++){
            row.add(columns.column(i).name,readRowElement(data.substring(indexStart,indexStart+columns.column(i).size),i));
            indexStart+=columns.column(i).size;
        }
        return row;
    }

    public long indexOfRow(long row){
        return startIndex+(row-1)*lenRow;
    }
    public long rowsCount(){
        return (file.size()-startIndex)/lenRow;
    }

    public boolean insertRow(Row row){
        Logger.log("Inserting row in:"+name);
        String data="";
        Object obj;
        for(int i=0;i<columns.columnsLen();i++){
            obj=row.get(columns.column(i).name);
            if(obj==null){
                Logger.log("Column : "+columns.column(i).name+" data(null)");
                if(!columns.column(i).options.acceptNull)
                {
                    Logger.log("Error got null while does not accept null");
                    return false;
                }
                if(columns.column(i).options.autoIncrement)
                    data+=Column.convertToWriteableData(String.valueOf(rowsCount()+1),columns.column(i).type,columns.column(i).lenType);
                else
                    data+=Column.convertToWriteableData(columns.column(i).options.defaultValue,columns.column(i).type,columns.column(i).lenType);
            }
            else {
                Logger.log("Column : "+columns.column(i).name+" data:"+Column.convertToWriteableData(String.valueOf(obj),columns.column(i).type,columns.column(i).lenType));
                if(columns.column(i).options.unique)
                {
                    for (int j=1;j<=rowsCount();j++){
                        if(Column.compare(readRowElement(j,columns.column(i).name),obj,columns.column(i).type))
                            {
                                Logger.log("Error 2 unique values");
                                return false;
                            }
                    }
                }
                if(columns.column(i).options.autoIncrement || !Column.validValue(String.valueOf(obj),columns.column(i)) || (!columns.column(i).options.acceptNull && (obj==null || String.valueOf(obj).length()==0)))
                {
                    Logger.log("Error autoIncrement");
                    return false;
                }
                //Terminal.writeLine("converted:("+Column.convertToWriteableData(String.valueOf(obj),columns.column(i).type,columns.column(i).lenType)+")len: "+Column.convertToWriteableData(String.valueOf(obj),columns.column(i).type,columns.column(i).lenType).length());
                data+=Column.convertToWriteableData(String.valueOf(obj),columns.column(i).type,columns.column(i).lenType);
            }
        }
        write((rowsCount()+1),data);
        return true;
    }


    public static Table load(String name,String database){
        Logger.log("Loading table:"+name+" from database:"+database);
        Column column=new Column();
        Columns columns=new Columns();
        fileChannel file=fileChannel.getInstance(DataBaseStructure.baseOfAll+database+MyFiles.fileSeparator+name+".table");
        if(file==null)
            return null;
        long lenDescirption=Terminal.convert2long(file.read(0,20));
        String description=file.read(20,20+lenDescirption);
        int status=0;
        String tmp="";
        for(int i=0;i<(int)lenDescirption;i++){
            if (status==0){
                tmp="";
                column=new Column();
                column.options=ColumnOptions.getInstance(false,false,false,"");
                i--;
                status++;
            }
            else if(status==1){
                if(description.charAt(i)=='-')
                {
                    column.setName(tmp);
                    status++;
                    tmp="";
                }
                else
                    tmp+=description.charAt(i);
            }
            else if(status==2){
                if(description.charAt(i)=='-')
                {
                    column.setType(tmp);
                    status++;
                    tmp="";
                }
                else
                    tmp+=description.charAt(i);
            }
            else if(status==3){
                if(description.charAt(i)=='-')
                {
                    column.setLenType(Terminal.convert2int(tmp));
                    tmp="";
                    status++;
                }
                else
                    tmp+=description.charAt(i);
            }
            else if(status==4){
                column.options.unique=description.charAt(i)=='1'?true:false;
                status++;
                i++;
            }
            else if(status==5){
                column.options.autoIncrement=description.charAt(i)=='1'?true:false;
                status++;
                i++;
            }
            else if(status==6){
                column.options.acceptNull=description.charAt(i)=='1'?true:false;
                status++;
                i++;
            }
            else if(status==7){
                if(description.charAt(i)=='\"')
                {
                    status=0;
                    column.generateSize();
                    columns.add(column);
                }
                else if(description.charAt(i)=='\\'){
                    i++;
                    column.options.defaultValue+=description.charAt(i);
                }
                else {
                    column.options.defaultValue+=description.charAt(i);
                }
            }
        }
        return new Table(name,file,columns,(int)lenDescirption);
    }

    public static Table create(String name,String database,Columns columns){
        Logger.log("Createing table:"+name+" in database:"+database);
        columns.print();

        if(MyFiles.isFile(DataBaseStructure.baseOfAll+database+MyFiles.fileSeparator+name+".table")){
            Logger.log("Table exists while creating");
            return null;
        }
        if(!MyFiles.createFile(DataBaseStructure.baseOfAll+database+MyFiles.fileSeparator+name+".table")) {
            Logger.log("Couldn\'t create table");
            return null;
        }
        String description="";
        Column col;
        for(int i=0;i<columns.columnsLen();i++){
            col=columns.column(i);
            description+=col.name+"-"+col.type+"-"+Column.convertToWriteableData(String.valueOf(col.lenType),"int")+"-"+(col.options.unique?"1":"0")+"-"+(col.options.autoIncrement?"1":"0")+"-"+(col.options.acceptNull?"1":"0")+"\""+PasswordHashing.addSlashes(col.options.defaultValue,"\"")+"\"";
        }
        fileChannel file=fileChannel.getInstance(DataBaseStructure.baseOfAll+database+MyFiles.fileSeparator+name+".table");
        if(file==null)
            return null;
        file.write(0,Column.convertToWriteableData(String.valueOf(description.length()),"bigint"));
        file.append(description);
        Logger.log("Table created");
        return new Table(name,file,columns,description.length());
    }
}