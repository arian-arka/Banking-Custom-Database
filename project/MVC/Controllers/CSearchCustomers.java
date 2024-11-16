package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CSearchCustomers extends Controller{
    public CSearchCustomers(){
        Terminal.writeLine("from CSearchCustomers");
    }
    public void index() {
        view("VSearchCustomers",false);
    }
    public String validateInput(String id,String socialNumber,String balance,String loan){
        if(id!=null && id.length()!=0 && !Validate.isNumber(id))
            return "invalid id";
        if(socialNumber!=null && socialNumber.length()!=0  && (!Validate.isNumber(socialNumber) || socialNumber.length()!=10))
            return "invalid social number";
        try{if(balance!=null && balance.length()!=0)Terminal.convert2double(balance);}catch (Exception e){return "invalid balance";}
        try{if(loan!=null && loan.length()!=0)Terminal.convert2double(loan);}catch (Exception e){return "invalid loan";}
        return null;
    }
    public String[][] search(
            String customerId,
            String socialNumber,
            String fullName,
            String city,
            String address,
            String balanceOp,
            String balanceLimit,
            String loanOp,
            String loanLimit,
            boolean isDebator
    ){
        return (String [][])((MCustomers)model("MCustomers")).search(
                Terminal.convert2int(customerId==null||customerId.length()==0?"0":customerId)
                ,socialNumber,fullName,city,address,balanceOp,
                Terminal.convert2double(balanceLimit==null||balanceLimit.length()==0?"0":balanceLimit),loanOp,
                Terminal.convert2double(loanLimit==null||loanLimit.length()==0?"0":loanLimit),
                isDebator
        );
    }
}