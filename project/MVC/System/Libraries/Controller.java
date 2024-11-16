package MVC.System.Libraries;
import java.lang.reflect.*;

import MVC.System.Helpers.*;
import MVC.Controllers.*;
import MVC.Views.*;
import MVC.Models.*;
import MVC.System.Libraries.DataBase.Model;
import MVC.System.Helpers.*;

public abstract class Controller extends Loader{
    protected Object self(String controller,String method,Object ...args){
        if(controller!=null &&method!=null)
            Logger.log("Calling  :"+controller+" method:"+method);
        String actualName="MVC.Controllers."+controller;
        if(!Loader.mehotdExists(actualName,method))
        {
            if(controller!=null &&method!=null)
                Logger.log("Error Calling  :"+controller+" method:"+method);
            return null;
        }
        else {
            if(args==null || args.length<2 ){
                return callMethod(actualName,method,null);
            }
            Object objs[]=new Object[args.length-1];
            for (int i=1;i<args.length;i++)
                objs[i-1]=args[i];
            return callMethod(actualName,method,objs);
        }
    }
    protected void control(String name,Object ...args){
        if(name!=null )
            Logger.log("Calling  controller:"+name);
        String actualName="MVC.Controllers."+name;
        if(!Loader.mehotdExists(actualName,"index"))
        {
            if(name!=null )
                Logger.log("INvalid  controller:"+name);
            return;
        }
        else {
            if(args==null || args.length<2 ){
                callMethod(actualName,null);
                return;
            }
            Object objs[]=new Object[args.length-1];
            for (int i=1;i<args.length;i++)
                objs[i-1]=args[i];
            callMethod(actualName,objs);
        }
    }
    protected void view(String name,Object ...args){
        if(name!=null )
            Logger.log("Calling  view:"+name);
        String actualName="MVC.Views."+name;
        if(!Loader.mehotdExists(actualName,"index"))
        {
            if(name!=null )
                Logger.log("Invalid  view:"+name);
            return;
        }
        else {
            if(args==null || args.length<2 ){
                callMethod(actualName,new Object[]{Cache.get("view")});
                return;
            }
            Object objs[]=new Object[args.length];
            objs[0]=Cache.get("view");
            for (int i=1;i<args.length;i++)
                objs[i]=args[i];
            callMethod(actualName,objs);
        }
    }
    protected Object model(String name){
        if(name!=null )
            Logger.log("Calling  model:"+name);
        String actualName="MVC.Models."+name;
        if(!Loader.classExists(actualName))
        {
            if(name!=null )
                Logger.log("Invalid  model:"+name);
            return null;
        }
        else {
            try {
                Class c = Class.forName(actualName);
                Object iClass = c.newInstance();
                return iClass;
            }
            catch (Exception e)
            {
                if(name!=null )
                    Logger.log("Error Calling model:"+name);
                e.printStackTrace();
                Terminal.writeLine("-----------------------------------------");
                return null;
            }
        }
    }
}