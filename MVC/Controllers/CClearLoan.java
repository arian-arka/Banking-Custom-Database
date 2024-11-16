package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CClearLoan extends Controller{
    public CClearLoan(){
        Terminal.writeLine("from CClearLoan");
    }
    public void index()
    {
        MAccounts m=(MAccounts)model("MAccounts");
        MLoans l=(MLoans)model("MLoans");
        view("VClearLoan",false,m.getAllOpenAccountsId((int)Cache.get("customerId")),l.getAllNotClearedIdOfLoans((int)Cache.get("customerId")));
    }
    public  String clear(String loanId,String accountId,String amount){
        MLoans l=(MLoans)model("MLoans");
        if(loanId==null || loanId.length()==0)
            return "No loan selected";
        if(accountId==null || accountId.length()==0)
            return "No account selected";
        double amountConverted;
        try{amountConverted=Terminal.convert2double(amount);}catch(Exception e){return "Invalid amount";}
        return l.clearLoan(Terminal.convert2int(loanId),amountConverted,Terminal.convert2int(accountId))?"Entered amount of the loan has cleared succesfully":"Error";
    }
    public  boolean isClearedCompeletly(String loanId){
        MLoans l=(MLoans)model("MLoans");
        return l.getLoanAmount(Terminal.convert2int(loanId))==0;
    }
}