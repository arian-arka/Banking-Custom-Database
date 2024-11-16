package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import java.util.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CUserMain extends Controller{
    public CUserMain(){
        Terminal.writeLine("from CUserMain");
    }
   public void index(){
        MCustomers m=(MCustomers)model("MCustomers");
        view("VUserMain",false,m.getBalance((int)Cache.get("customerId")),m.getFullName((int)Cache.get("customerId")));
   }
}