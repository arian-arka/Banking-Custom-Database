 package MVC.System.Libraries.DataBase;

import MVC.System.Helpers.*;
import java.util.*;
public class Rows{
    ArrayList<Row> rows=new ArrayList<Row>();
    public void add(Row row){
        rows.add(row);
    }
    public void row(int index){
        rows.get(index);
    }
    public int size(){
        return rows.size();
    }
    public void clear(){
        rows.clear();
    }

}