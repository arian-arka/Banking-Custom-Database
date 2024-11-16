package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CAccounts extends Controller{
    public CAccounts(){
        Terminal.writeLine("from CAccounts");
    }
    public void index() {
        MAccounts m=(MAccounts)model("MAccounts");
        MCustomers c=(MCustomers)model("MCustomers");
        view("VAccounts",false,new String[]{"id","branch","balance"},m.getAllAccountsOfCutomer((int)Cache.get("customerId")),c.getBalance((int)Cache.get("customerId")));
    }

}