package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CLogin extends Controller{
    public CLogin(){
        Terminal.writeLine("from CLogin");
    }
    public void index()
    {
        view("VLogin",false);
    }
    public void index(boolean showWelconme) {
        view("VLogin",false);
        if(showWelconme)
            ((View)Cache.get("view")).popUpOk("Welcome to the bank please sign in");
    }
    public int validate(String socialNum,String password){
        if(socialNum==null || password==null || socialNum.length()==0 || password.length()==0)
            return -1;
        return ((MCustomers)model("MCustomers")).validUser(socialNum,password);
    }
    public void login(String socialNum,String password){
        if(socialNum.equals(password) && password.equals("root"))
            control("CMasterMain",false);
        int id=validate(socialNum,password);
        if(id>0)
        {
            Terminal.writeLine("logged in!!!!!!!!!!!!!!!!!!");
            Cache.set("error",false);
            Cache.set("customerId",id);
            control("CUserMain",false);
        }
        else{
            Terminal.writeLine("didnt logged in!!!!!!!!!!!!!!!!!!");
            Cache.set("error",true);
        }
    }
}