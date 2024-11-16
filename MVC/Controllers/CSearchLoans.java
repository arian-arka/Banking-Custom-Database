package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
public class CSearchLoans extends Controller{
    public CSearchLoans(){
        Terminal.writeLine("from CSearchLoans");
    }
    public void index() {
        view("VSearchLoans",false);
    }
    public String validateInput(String id,String balance,String customerId){
        if(id!=null && id.length()!=0 && !Validate.isNumber(id))
            return "invalid id";
        if(customerId!=null && customerId.length()!=0 && !Validate.isNumber(customerId))
            return "invalid id";
        try{if(balance!=null && balance.length()!=0)Terminal.convert2double(balance);}catch (Exception e){return "invalid balance";}
        Terminal.writeLine("validation true");
        return null;
    }
    public String[][] search(
            String customerId,
            String id,
            String branch,
            String balanceOp,
            String balanceLimit,
            int status
    ){
        return (String [][])
                ((MLoans)model("MLoans")).search(
                Terminal.convert2int(customerId==null||customerId.length()==0?"0":customerId),
                Terminal.convert2double(balanceLimit==null||balanceLimit.length()==0?"0":balanceLimit),
                balanceOp,branch,
                Terminal.convert2int(id==null||id.length()==0?"0":id),
                status
        );
    }
}