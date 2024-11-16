package MVC.Models;
import MVC.System.Libraries.DataBase.*;
import MVC.System.Libraries.*;
import MVC.System.Helpers.*;
import java.util.*;
public class MAccounts extends Model{
    public MAccounts(){
        Terminal.writeLine("MAccounts ...");
    }

    public boolean increaseBalance(int accountId,double amount){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0 || accountId>accs.rowsCount())
            return false;
        double balance=(double)accs.readRowElement(accountId,"balance");
        if(balance==-1 || amount<0)
            return false;
        Table custs=table("Customers");

        balance+=amount;
        accs.writeRowElement(accountId,String.valueOf(balance),"balance");

        int customerId=(int)accs.readRowElement(accountId,"customerId");
        double totalBalance=(double)custs.readRowElement(customerId,"balance");
        totalBalance+=amount;
        custs.writeRowElement(customerId,String.valueOf(totalBalance),"balance");
        return true;
    }

    public boolean decreaseBalance(int accountId,double amount){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0 || accountId>accs.rowsCount())
            return false;
        double balance=(double)accs.readRowElement(accountId,"balance");
        if(balance==-1 || amount<0)
            return false;
        Table custs=table("Customers");

        balance-=amount;
        accs.writeRowElement(accountId,String.valueOf(balance),"balance");

        int customerId=(int)accs.readRowElement(accountId,"customerId");
        double totalBalance=(double)custs.readRowElement(customerId,"balance");
        totalBalance-=amount;
        custs.writeRowElement(customerId,String.valueOf(totalBalance),"balance");
        return true;
    }

    public String [][]getAllAccountsOfCutomer(int CustomerId){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return null;
        ArrayList<String []> rows=new ArrayList<String []>();
        String row[];
        Row r;
        for (int i=1;i<=accs.rowsCount();i++){
            r=accs.getRow(i);
            if((int)r.get("customerId")==CustomerId ){
                row=new String[3];
                if((double)r.get("balance")>=0)
                    row[0]=String.valueOf((int)r.get("id"));
                else
                    row[0]=String.valueOf((int)r.get("id"))+"(closed)";
                row[1]=(String)r.get("branch");
                row[2]=String.valueOf(((double)r.get("balance")));
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

    public String [][]getAllOpenAccountsOfCutomer(int CustomerId){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return null;
        ArrayList<String []> rows=new ArrayList<String []>();
        String row[]=new String[3];
        Row r;
        for (int i=1;i<=accs.rowsCount();i++){
            r=accs.getRow(i);
            if((int)r.get("customerId")==CustomerId && (double) r.get("balance") >= 0 ){
                row=new String[3];
                row[0] = String.valueOf((int) r.get("id"));
                row[1] = (String) r.get("branch");
                row[2] = String.valueOf(r.get("balance"));
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

    public String []getAllOpenAccountsId(int CustomerId){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return null;
        ArrayList<String> rows=new ArrayList<String>();
        Row r;
        for (int i=1;i<=accs.rowsCount();i++){
            r=accs.getRow(i);
            if((int)r.get("customerId")==CustomerId && (double)r.get("balance") >= 0 )
                rows.add(String.valueOf((int) r.get("id")));
        }
        if(rows.size()==0)
            return null;
        String [] toReturn=new String [rows.size()];
        for(int i=0;i<rows.size();i++)
            toReturn[i]=rows.get(i);
        return toReturn;
    }

    public boolean addAccount(int CustomerId,String Branch,double Balance){
        Table accs=table("Accounts");
        Row row =new Row();
        row.add("customerId",String.valueOf(CustomerId));
        row.add("branch",Branch);
        row.add("balance",String.valueOf(Balance));
        if(accs.insertRow(row)){
            Table custs=table("Customers");
            double totalBalance=(double)custs.readRowElement(CustomerId,"balance");
            totalBalance+=Balance;
            custs.writeRowElement(CustomerId,String.valueOf(totalBalance),"balance");
            return true;
        }
        return false;
    }

    public boolean closeAccount(int AccountId){
        Table accs=table("Accounts");
        Table custs=table("Customers");
        if(accs.rowsCount()<AccountId)
            return false;

        int customerId=(int)accs.readRowElement(AccountId,"customerId");
        double totalBalance=(double)custs.readRowElement(customerId,"balance");
        totalBalance-=(double)accs.readRowElement(AccountId,"balance");
        custs.writeRowElement(customerId,String.valueOf(totalBalance),"balance");

        accs.writeRowElement(AccountId,"-1","balance");
        return true;
    }

    public double calcBalanceOfCustomer(int CustomerId){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return 0;
        double balance=0;
        Row r;
        for (int i=1;i<=accs.rowsCount();i++){
            r=accs.getRow(i);
            if((double)r.get("balance")<0)
                continue;
            if((int)r.get("customerId")==CustomerId)
                balance+=(double)r.get("balance");
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

    public String [][]search(int customerId,double balance,String op,String branch,int accountId,int status){//string with length and number with
        //status: -1closed 0default 1open
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return null;
        ArrayList<String []> rows=new ArrayList<String []>();
        String row[];
        Row r;
        for (int i=1;i<=accs.rowsCount();i++){
            Terminal.writeLine("I:"+i);
            r=accs.getRow(i);
            if(branch!=null && branch.length()!=0 && !branch.equals((String)r.get("branch")))
                continue;
            if(accountId!=0 && accountId!=(int)r.get("id"))
                continue;
            if(customerId!=0 && (int)r.get("customerId")!=customerId )
                continue;
            if(op!=null && op.length()!=0 && !calcOp(op,balance,(double)r.get("balance")))
                continue;
            row=new String[4];
            if((double)r.get("balance")>=0){
                if(status==-1)
                    continue;
                row[0]=String.valueOf((int)r.get("id"));
            }
            else{
                if(status==1)
                    continue;
                row[0]=String.valueOf((int)r.get("id"))+"(closed)";
            }
            row[1]=(String)r.get("branch");
            row[2]=""+(double)r.get("balance");
            row[3]=""+(int)r.get("customerId");
            rows.add(row);
            if( accountId!=0)
                break;
        }
        if(rows.size()==0)
            return null;
        String [][] toReturn=new String [rows.size()][];
        for(int i=0;i<rows.size();i++)
            toReturn[i]=Arrays.copyOf(rows.get(i), 4, String[].class);
        return toReturn;
    }

    public double averageBalanceOfAllCustomers(){
        Table accs=table("Accounts");
        if(accs.rowsCount()==0)
            return 0;
        double sum=0,tmp;
        double count=0;
        for (int i=1;i<=accs.rowsCount();i++){
            tmp=(double)accs.readRowElement(i,"balance");
            if(tmp>-1){
                sum+=tmp;
                count++;
            }
        }
        return sum/count;
    }

}