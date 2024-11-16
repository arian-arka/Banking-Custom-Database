package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CGetLoan extends Controller{
    public CGetLoan(){
        Terminal.writeLine("from CGetLoan");
    }
    public void index()
    {
        view("VGetLoan",false);
    }
    public  String add(String branch,String amount ){
        if(branch==null || branch.length()==0)
            return "Branch requeired";
        double amountConverted;
        try{amountConverted=Terminal.convert2double(amount);}catch(Exception e){return "Invalid amount";}
        return ((MLoans)model("MLoans")).getLoan((int)Cache.get("customerId"),branch,amountConverted)?"Loan has added succesfully":"Error";
    }
}