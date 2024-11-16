package MVC.Models;
import MVC.System.Libraries.DataBase.*;
import MVC.System.Libraries.*;
import MVC.System.Helpers.*;
import java.util.*;
import java.lang.Math;
public class MSearch extends Model{
    public MSearch(){
        Terminal.writeLine("MSearch ...");
    }

    public String [] branchAverage(String branch){
        Table accs=table("Accounts");
        Table loans=table("Loans");
        double loansSum=0,balanceSum=0;
        int loansCount=(int)loans.rowsCount();
        int accountsCount=(int)accs.rowsCount();
        Terminal.writeLine("loan count: "+loansCount);
        Terminal.writeLine("account Count: "+accountsCount);
        double tmp;
        int loanCounter=0,accountCounter=0;
        if(loansCount>accountsCount){
            for(int i=1;i<=accountsCount;i++){
                if(((String)accs.readRowElement(i,"branch")).equals(branch)){
                    tmp = (double)accs.readRowElement(i,"balance");
                    if (tmp > -1){
                        accountCounter++;
                        balanceSum += tmp;
                    }
                }
                if(((String)loans.readRowElement(i,"branch")).equals(branch)){
                    tmp = (double) loans.readRowElement(i, "amount");
                    if (tmp > 0){
                        loanCounter++;
                        loansSum += tmp;
                    }
                }
            }
            if(accountCounter!=0)
                balanceSum/=(double) accountCounter;
            balanceSum=(double)Math.round(balanceSum * 10d) / 10d;
            for(int i=accountsCount+1;i<=loansCount;i++) {
                if (((String) loans.readRowElement(i, "branch")).equals(branch)) {
                    tmp = (double) loans.readRowElement(i, "amount");
                    if (tmp > 0){
                        loanCounter++;
                        loansSum += tmp;
                    }
                }
            }
            if(loanCounter!=0)
                loansSum/=(double) loanCounter;
            loansSum=(double)Math.round(loansSum * 10d) / 10d;
            return new String[]{branch,String.valueOf(balanceSum),String.valueOf(loansSum)};
        }
        for(int i=1;i<=loansCount;i++){
            if(((String)accs.readRowElement(i,"branch")).equals(branch)){
                tmp = (double)accs.readRowElement(i,"balance");
                if (tmp > -1){
                    accountCounter++;
                    balanceSum += tmp;
                }
            }
            if(((String)loans.readRowElement(i,"branch")).equals(branch)){
                tmp = (double) loans.readRowElement(i, "amount");
                Terminal.writeLine(branch+":"+tmp);
                if (tmp > 0){
                    loanCounter++;
                    loansSum += tmp;
                }
            }
        }
        if(loanCounter!=0)
            loansSum/=(double) loanCounter;
        loansSum=(double)Math.round(loansSum * 10d) / 10d;
        Terminal.writeLine("#####"+branch+"  amount:"+loansSum+" count:"+loanCounter);
        for(int i=loansCount+1;i<=accountsCount;i++) {
            if(((String)accs.readRowElement(i,"branch")).equals(branch)){
                tmp = (double)accs.readRowElement(i,"balance");
                if (tmp > -1){
                    accountCounter++;
                    balanceSum += tmp;
                }
            }
        }
        Terminal.writeLine("#####"+branch+"  balance:"+balanceSum+" count:"+accountCounter);
       
        if(accountCounter!=0)
            balanceSum/=(double) accountCounter;
        balanceSum=(double)Math.round(balanceSum * 10d) / 10d;
        return new String[]{branch,String.valueOf(balanceSum),String.valueOf(loansSum)};
    }

    public String balanceAverage(){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return "None";
        double sum=0;
        for(int i=1;i<=accs.rowsCount();i++)
            sum+=(double)accs.readRowElement(i,"balance");
        sum/=accs.rowsCount();
        sum=(double)Math.round(sum * 10d) / 10d;
        return String.valueOf(sum);
    }

    public String loanAverage(){
        Table loans=table("Loans");
        if(loans.rowsCount()==0)
            return "None";
        double sum=0;
        for(int i=1;i<=loans.rowsCount();i++)
            sum+=(double)loans.readRowElement(i,"amount");
        sum/=loans.rowsCount();
        sum=(double)Math.round(sum * 10d) / 10d;
        return String.valueOf(sum);
    }
}