package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;

public class CMain extends Controller{
    public CMain(){
        Terminal.writeLine("from cmian");
    }
    public void index(String name,int age)
    {
        model("MCustomers");
    }
}