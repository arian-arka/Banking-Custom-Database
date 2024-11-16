package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CSearchBranch extends Controller{
    public CSearchBranch(){
        Terminal.writeLine("from CSearchBranch");
    }
    public void index() {
        view("VSearchBranch",false);
    }
    public String[] search(String branch){
        if(branch==null || branch.length()==0)
            return null;
        return (String[]) ((MSearch)model("MSearch")).branchAverage(branch);
    }
}