package  MVC.System.Libraries.DataBase;

import MVC.System.Helpers.*;
import java.util.*;

public class Row{
    HashMap<String, Object>values = new HashMap<String, Object>();
    public void add(String column,Object data){
        values.put(column,data);
    }
    public Object get(String column){
        if(values.containsKey(column))
            return values.get(column);
        return null;
    }
    public void clear(){
        values.clear();
    }
    public int size(){
        return values.size();
    }
}