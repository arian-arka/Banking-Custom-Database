package  MVC.System.Libraries;
import java.lang.reflect.*;

import MVC.System.Helpers.*;
import MVC.Controllers.*;
import MVC.Views.*;
import MVC.System.Helpers.*;

public abstract class Loader{
    protected Object callMethod(String clas,String met,Object []args){
        if(clas!=null && met!=null && args!=null)
            Logger.log("Calling  class:"+clas+" method:"+met+" number of args:"+args.length);
        try
        {
            Class c = Class.forName(clas);
            Object iClass = c.newInstance();
            Method[] method = c.getDeclaredMethods();
            for (Method method2 : method) {
                Class[] parameterTypes = method2.getParameterTypes();
                if(method2.getName().equals(met)){
                    return method2.invoke(iClass, args);
                }
            }
        }
        catch (Exception e)
        {
            if(clas!=null && met!=null && args!=null)
                Logger.log("Error while Calling  class:"+clas+" method:"+met+" number of args:"+args.length);
            e.printStackTrace();
            Terminal.writeLine("-----------------------------------------");
            return null;
        }
        return null;
    }
    protected void callMethod(String name,Object []args){
        if(name!=null&& args!=null)
            Logger.log("Calling  class:"+name+" method:index number of args:"+args.length);
        try
        {
            Class c = Class.forName(name);
            Object iClass = c.newInstance();
            Method[] method = c.getDeclaredMethods();
            for (Method method2 : method) {
                Class[] parameterTypes = method2.getParameterTypes();
                if(method2.getName().equals("index")){
                    method2.invoke(iClass, args);
                    break;
                }
            }
        }
        catch (Exception e)
        {
            if(name!=null&& args!=null)
                Logger.log("Error Calling  class:"+name+" method:index number of args:"+args.length);
            e.printStackTrace();
            Terminal.writeLine("-----------------------------------------");
        }
    }
    protected void callStaticMethod(String name,Object []args){
        if(name!=null&& args!=null)
            Logger.log("Calling  class:"+name+" method:index number of args:"+args.length);
        try
        {
            Class c = Class.forName(name);
            Method[] method = c.getDeclaredMethods();
            for (Method method2 : method) {
                Class[] parameterTypes = method2.getParameterTypes();
                if(method2.getName().equals("index")){
                    method2.invoke(null, args);
                    break;
                }
                for(Class parameterType: parameterTypes){
                    System.out.println(parameterType.getName());
                }
            }
        }
        catch (Exception e)
        {
            if(name!=null&& args!=null)
                Logger.log("Error Calling  class:"+name+" method:index number of args:"+args.length);
            e.printStackTrace();
            Terminal.writeLine("-----------------------------------------");
        }
    }
    public static boolean classExists(String name){
        try {
            Class.forName(name);
            return true;
        } catch( ClassNotFoundException e ) {
            return false;
        }
    }
    public static boolean mehotdExists(String className,String methodName){
        try  {
            Method[] method = Class.forName(className).getDeclaredMethods();
            for (Method method2 : method) {
                if(method2.getName().equals(methodName))
                    return true;
            }
            return false;
        }  catch (ClassNotFoundException e) {
            return false;
        }
    }
}