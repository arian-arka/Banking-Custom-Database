package  MVC.System.Helpers;
import java.util.*;
public class Cache{
    private static final HashMap<String, Object>values = new HashMap<String, Object>();
    public static boolean contains(String key){ return values.containsKey(key); }
    public static void clear(){ values.clear();}
    public static void set(String key,Object value){values.put(key,value);}
    public static Object get(String key){
        if(!contains(key))
            return null;
        return values.get(key);
    }
    public static String getString(String key){
        if(!contains(key))
            return null;
        return (String)values.get(key);
    }
    public static int getInt(String key){
        if(!contains(key))
            return 0;
        return (int)values.get(key);
    }
    public static long getLong(String key){
        if(!contains(key))
            return 0;
        return (long)values.get(key);
    }
    public static double getDouble(String key){
        if(!contains(key))
            return 0;
        return (double)values.get(key);
    }
    public static boolean getBool(String key){
        if(!contains(key))
            return false;
        return (boolean)values.get(key);
    }
    public static boolean remove(String key){
        if(contains(key)){
            values.remove(key);
            return true;
        }
        return false;
    }
}