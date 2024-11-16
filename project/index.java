import  MVC.Controllers.*;
import  MVC.System.*;
import  MVC.Views.*;
import  MVC.Models.*;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import java.lang.reflect.*;
public class index extends Controller{

    index(){
         control("CLogin",false,false);
    }
    public static void main(String[] args) {
        CONFIG.init();
        index i=new index();
    }
}