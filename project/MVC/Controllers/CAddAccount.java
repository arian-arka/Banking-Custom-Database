package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CAddAccount extends Controller{
    public CAddAccount(){
        Terminal.writeLine("from CAddAccount");
    }
    public void index()
    {
        view("VAddAccount",false);
    }
    public String add(String branch,String balance){
        MAccounts m=(MAccounts)model("MAccounts");
        try{
            if(m.addAccount((int)Cache.get("customerId"),branch,Terminal.convert2double(balance)))
                return "Account added";
            else
                return "Error while adding account";
        }catch (Exception e){ return "Invalid balance";}
    }
}