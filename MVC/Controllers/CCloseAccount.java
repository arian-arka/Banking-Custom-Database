package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;

public class CCloseAccount extends Controller{
    public CCloseAccount(){
        Terminal.writeLine("from CCloseAccount");
    }
    public void index()
    {
        MAccounts m=(MAccounts)model("MAccounts");
        String []data=m.getAllOpenAccountsId((int)Cache.get("customerId"));
        view("VCloseAccount",false,(String [])data);
    }
    public void close(String id ){
        MAccounts m=(MAccounts)model("MAccounts");
        int idInt=Terminal.convert2int(id);
        m.closeAccount(idInt);
    }
}