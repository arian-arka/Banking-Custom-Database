package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CLoans extends Controller{
    public CLoans(){
        Terminal.writeLine("from CLoans");
    }
    public void index() {
        view("VLoans",false,
                new String[]{"id","branch","amount"},
                ((MLoans)model("MLoans")).getAllLoansOfCutomer((int)Cache.get("customerId")),
                ((MCustomers)model("MCustomers")).getLoan((int)Cache.get("customerId"))
        );
    }

}