package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CPersonalInformation extends Controller{
    public CPersonalInformation(){
        Terminal.writeLine("from CPersonalInformation");
    }
    public void index()
    {
        MCustomers mod=(MCustomers)model("MCustomers");
        String social=mod.getSocialNumber(String.valueOf((int)Cache.get("customerId")));
        String name=mod.getFullName(String.valueOf((int)Cache.get("customerId")));
        String city=mod.getCity(String.valueOf((int)Cache.get("customerId")));
        String address=mod.getAddress(String.valueOf((int)Cache.get("customerId")));
        String balance=mod.getBalance(String.valueOf((int)Cache.get("customerId")));
        String loan=mod.getLoan(String.valueOf((int)Cache.get("customerId")));
        view("VPersonalInformation",false,name,social,city,address,balance,loan);
    }
}