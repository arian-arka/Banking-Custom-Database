package  MVC.System.Libraries.DataBase;

import MVC.System.Helpers.*;
import java.util.*;


public class Columns{

    public void print(){
        Terminal.writeLine("len:"+columnsLen());
        for(int i=0;i<columnsLen();i++)
        {
            column(i).print();
            Terminal.writeLine("**********************************");
        }
    }
    ArrayList<Column> columns=new ArrayList<Column>();

    public int exists(String name) {
        for (int i=0;i<columns.size();i++){
            if(column(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public boolean add(String name,String type,int lenType){
        if(exists(name)!=-1)
            return false;
        Column col=Column.getInstance(name,type,lenType);
        if(col==null)
            return false;
        columns.add(col);
        return true;
    }
    public boolean add(String name,String type,int lenType,String options[]){
        if(exists(name)!=-1)
            return false;
        Column col=Column.getInstance(name,type,lenType,options);
        if(col==null)
            return false;
        columns.add(col);
        return true;
    }
    public boolean add(String name,String type,String options[]){
        if(exists(name)!=-1)
            return false;
        Column col=Column.getInstance(name,type,options);
        if(col==null)
            return false;
        columns.add(col);
        return true;
    }
    public boolean add(String name,String type){
        if(exists(name)!=-1)
            return false;
        Column col=Column.getInstance(name,type);
        if(col==null)
            return false;
        columns.add(col);
        return true;
    }
    public boolean add(Column col){
        if(exists(col.name)!=-1)
            return false;
        columns.add(col);
        return true;
    }

    public int columnsLen(){
        return columns.size();
    }

    public int rowLength(){
        int len=0;
        for(int i=0;i<columnsLen();i++)
            len+=columns.get(i).size;
        return len;
    }

    public Column column(String name){
        int index=exists(name);
        if(index!=-1)
            return column(index);
        return null;
    }
    public Column column(int index){
        if(index<columnsLen())
            return columns.get(index);
        return null;
    }

    public long indexOfElement(String column){
        long index=0;
        for (int i=0;i<columnsLen();i++)
        {
            if(this.column(i).name.equals(column))
                return index;
            index+=this.column(i).size;
        }
        return -1;
    }
}
