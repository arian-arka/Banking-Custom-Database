package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;

public class CTest extends Controller{
    public CTest(){
        Terminal.writeLine("from CTest");
    }
    public void index()
    {
        Terminal.writeLine("this is from CTest:");
    }
}