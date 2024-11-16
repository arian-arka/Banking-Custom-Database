package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import java.util.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CMasterMain extends Controller{
    public CMasterMain(){
        Terminal.writeLine("from CMasterMain");
    }
   public void index(){
        view("VMasterMain",false);
   }
}