package  MVC.System.Libraries.DataBase;
import MVC.System.Helpers.*;
public class ColumnOptions{
    ColumnOptions(){}
    ColumnOptions(boolean autoIncrement, boolean unique, boolean acceptNull, String defaultValue){
        this.autoIncrement=autoIncrement;
        this.unique=unique;
        this.acceptNull=acceptNull;
        this.defaultValue=defaultValue;
    }
    public boolean autoIncrement=false;
    public boolean unique=false;
    public boolean acceptNull=false;
    public String defaultValue="";
    public static ColumnOptions getInstance(boolean autoIncrement, boolean unique, boolean acceptNull, String defaultValue){
        if(defaultValue==null)
            return new ColumnOptions(autoIncrement,unique,acceptNull,"");
        return new ColumnOptions(autoIncrement,unique,acceptNull,defaultValue);
    }
    public void print(){
        Terminal.writeLine("options: ");
        Terminal.writeLine("  autoIncrement: "+autoIncrement);
        Terminal.writeLine("  unique: "+unique);
        Terminal.writeLine("  acceptNull: "+acceptNull);
        Terminal.writeLine("  defaultValue: "+defaultValue);
    }
}