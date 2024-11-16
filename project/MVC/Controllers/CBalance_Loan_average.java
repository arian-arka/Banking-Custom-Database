package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CBalance_Loan_average extends Controller{
    public CBalance_Loan_average(){
        Terminal.writeLine("from CBalance_Loan_average");
    }
    public void index() {
    }
    public String result(){
        String loans=((MSearch)model("MSearch")).loanAverage();
        String balances=((MSearch)model("MSearch")).balanceAverage();
        Terminal.writeLine("loans avg:"+loans+" - Balances avg"+balances);
        return "loans avg:"+loans+" - Balances avg"+balances;
    }

}