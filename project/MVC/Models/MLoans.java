package MVC.Models;
import MVC.System.Libraries.DataBase.*;
import MVC.System.Libraries.*;
import MVC.System.Helpers.*;
import java.util.*;
public class MLoans extends Model{
    public MLoans(){
        Terminal.writeLine("MLoans ...");
    }

    public boolean getLoan(int CustomerId,String Branch,double Amount){
        Table loan=table("Loans");
        Row row =new Row();
        row.add("customerId",String.valueOf(CustomerId));
        row.add("branch",Branch);
        row.add("amount",String.valueOf(Amount));
        if(loan.insertRow(row)){
            Table custs=table("Customers");
            double totalLoan=(double)custs.readRowElement(CustomerId,"loanAmount");
            totalLoan+=Amount;
            custs.writeRowElement(CustomerId,String.valueOf(totalLoan),"loanAmount");
            return true;
        }
        return false;
    }

    public boolean canClearLoan(int accountId,double amount){
        return (double)table("Accounts").readRowElement(accountId,"balance")>=amount;
    }

    public double getLoanAmount(int loanId){
        return (double)table("Loans").readRowElement(loanId,"amount");
    }

    public boolean clearLoan(int loanId,double amount,int accountId){
        Table loan=table("Loans");
        Table accs=table("Accounts");
        Table custs=table("Customers");
        if(loan.rowsCount()==0|| loanId>loan.rowsCount() || accs.rowsCount()<accountId || !canClearLoan(accountId,amount) || amount>getLoanAmount(loanId))
            return false;
        double loanAmount=(double)loan.readRowElement(loanId,"amount");
        if(loanAmount==0)
            return false;
        loanAmount-=amount;
        loan.writeRowElement(loanId,String.valueOf(loanAmount),"amount");

        double newBalance=(double)accs.readRowElement(accountId,"balance");
        newBalance-=amount;
        accs.writeRowElement(accountId,String.valueOf(newBalance),"balance");

        int customerId=(int)accs.readRowElement(accountId,"customerId");
        double totalLoan=(double)custs.readRowElement(customerId,"loanAmount");
        totalLoan-=amount;
        custs.writeRowElement(customerId,String.valueOf(totalLoan),"loanAmount");

        return true;
    }

    public double calcSumOfAllLoansOfCustomer(int CustomerId){
        Table loan=table("Loans");
        if(loan.rowsCount()==0)
            return 0;
        double balance=0;
        Row r;
        for (int i=1;i<=loan.rowsCount();i++){
            r=loan.getRow(i);
            if((int)r.get("customerId")==CustomerId)
                balance+=(double)r.get("amount");
        }
        return balance;
    }

    private boolean calcOp(String op,double amount2,double amount1){
        switch (op.toLowerCase()){
            case "=":
            case "equals":
            case "is": return amount1==amount2;
            case "not":
            case "!=": return amount1!=amount2;
            case"greater than":
            case">":return amount1>amount2;
            case"greater than or equals":
            case">=":return amount1>=amount2;
            case"less than":
            case"<":return amount1<amount2;
            case"less than or equals":
            case"<=":return amount1<=amount2;
        }
        return false;
    }

    public String [][]search(int customerId,double amount,String op,String branch,int loanId,int status){
        //status 1:clear 0:default -1:notcleared
        Table loan=table("Loans");
        if(loan.rowsCount()==0)
            return null;
        ArrayList<String []> rows=new ArrayList<String []>();
        String row[];
        Row r;
        for (int i=1;i<=loan.rowsCount();i++){
            r=loan.getRow(i);
            if(branch!=null && branch.length()!=0 && !branch.equals((String)r.get("branch")))
                continue;
            if(loanId!=0 && (int)r.get("id")!=loanId)
                continue;
            if(customerId!=0  && (int)r.get("customerId")!=customerId )
                continue;
            if(op!=null && op.length()!=0 && !calcOp(op,amount,(double)r.get("amount")))
                continue;
            row=new String[4];
            if((double)r.get("amount")>0){
                if(status==1)
                    continue;
                row[0]=String.valueOf((int)r.get("id"));
            }
            else{
                if(status==-1)
                    continue;
                row[0]=String.valueOf((int)r.get("id"))+"(cleared)";
            }
            row[1]=(String)r.get("branch");
            row[2]=""+(double)r.get("amount");
            row[3]=""+(int)r.get("customerId");
            rows.add(row);
            if( loanId!=0)
                break;
        }
        if(rows.size()==0)
            return null;
        String [][] toReturn=new String [rows.size()][];
        for(int i=0;i<rows.size();i++)
            toReturn[i]=Arrays.copyOf(rows.get(i), 4, String[].class);
        return toReturn;
    }

    public String [][]getAllLoansOfCutomer(int CustomerId){
        Table loan=table("Loans");
        if(loan.rowsCount()==0)
            return null;
        ArrayList<String []> rows=new ArrayList<String []>();
        String row[];
        Row r;
        for (int i=1;i<=loan.rowsCount();i++){
            r=loan.getRow(i);
            row=new String[3];
            if((int)r.get("customerId")==CustomerId ){
                if((double)r.get("amount")<=0)
                    row[0]=String.valueOf((int)r.get("id"))+"(cleared)";
                else
                    row[0]=String.valueOf((int)r.get("id"));
                row[1]=(String)r.get("branch");
                row[2]=String.valueOf((double)r.get("amount"));
                rows.add(row);
            }
        }
        if(rows.size()==0)
            return null;
        String [][] toReturn=new String [rows.size()][];
        for(int i=0;i<rows.size();i++)
            toReturn[i]=Arrays.copyOf(rows.get(i), 3, String[].class);

        return toReturn;
    }

    public String []getAllNotClearedIdOfLoans(int CustomerId){
        Table loan=table("Loans");
        if(loan.rowsCount()==0)
            return null;
        ArrayList<String > rows=new ArrayList<String >();
        Row r;
        for (int i=1;i<=loan.rowsCount();i++){
            r=loan.getRow(i);
            if((int)r.get("customerId")==CustomerId && (double)r.get("amount")>0)
                rows.add(String.valueOf((int)r.get("id")));
        }
        if(rows.size()==0)
            return null;
        Terminal.writeLine("got clear loans!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String [] toReturn=new String [rows.size()];
        for(int i=0;i<rows.size();i++)
            toReturn[i]=rows.get(i);
        return toReturn;
    }

}