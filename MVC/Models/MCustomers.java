package MVC.Models;
import MVC.System.Libraries.DataBase.*;
import MVC.System.Libraries.*;
import MVC.System.Helpers.*;
import java.util.*;
public class MCustomers extends Model{
    public MCustomers(){
        Terminal.writeLine("MCustomers ...");
    }
    private static String salt="ke53bffh823y1hfkpwudbn3d";

    public boolean checkUserExists(String social){
        Table custs=table("Customers");
        for (int i=1;i<=custs.rowsCount();i++){
            if(((String)custs.readRowElement(i,"socialNumber")).equals(social))
                return true;
        }
        return false;
    }

    public boolean registerUser(String name,String socialNumber,String city,String address,String password){
        String newPassword=PasswordHashing.hash(password,MCustomers.salt);
        Row row =new Row();
        row.add("socialNumber",socialNumber);
        row.add("fullName",name);
        row.add("city",city);
        row.add("address",address);
        row.add("password",newPassword);
        row.add("balance","0");
        row.add("loanAmount","0");
        return table("Customers").insertRow(row);
    }

    public int validUser(String social,String password){
        Table custs=table("Customers");
        String newPassword=PasswordHashing.hash(password,MCustomers.salt);
        for (int i=1;i<=custs.rowsCount();i++){
            if(((String)custs.readRowElement(i,"socialNumber")).equals(social) && ((String)custs.readRowElement(i,"password")).equals(newPassword))
                return i;
        }
        return -1;
    }

    public boolean changePassword(String id,String password){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return false;
        String newPassword=PasswordHashing.hash(password,MCustomers.salt);
        custs.writeRowElement(Terminal.convert2long(id),newPassword,"password");
        return true;
    }

    public String getSocialNumber(String id){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return null;
        return (String) custs.readRowElement(Terminal.convert2int(id),"socialNumber");
    }

    public String getSocialNumber(int id){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return null;
        return (String) custs.readRowElement(id,"socialNumber");
    }

    public String getCity(String id){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return null;
        return (String) custs.readRowElement(Terminal.convert2int(id),"city");
    }

    public String getCity(int id){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return null;
        return (String) custs.readRowElement(id,"city");
    }

    public String getAddress(String id){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return null;
        return (String)custs.readRowElement(Terminal.convert2int(id),"address");
    }

    public String getAddress(int id){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return null;
        return (String)custs.readRowElement(id,"address");
    }

    public String getFullName(String id){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return null;
        return (String) custs.readRowElement(Terminal.convert2int(id),"fullName");
    }

    public String getFullName(int id){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return null;
        return (String) custs.readRowElement(id,"fullName");
    }

    public String getBalance(String id){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return null;
        return String.valueOf((double)custs.readRowElement(Terminal.convert2int(id),"balance"));
    }

    public String getBalance(int id){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return null;
        return String.valueOf((double)custs.readRowElement(id,"balance"));
    }

    public String getLoan(String id){
        Table custs=table("Customers");
        if(custs.rowsCount()<Terminal.convert2long(id))
            return null;
        return String.valueOf((double) custs.readRowElement(Terminal.convert2int(id),"loanAmount"));
    }

    public String getLoan(int id){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return null;
        return String.valueOf((double) custs.readRowElement(id,"loanAmount"));
    }

    public int getIdOfSocialNumber(String social){
        Table custs=table("Customers");
        for (int i=1;i<=custs.rowsCount();i++){
            if(((String)custs.readRowElement(i,"socialNumber")).equals(social))
                return i;
        }
        return -1;
    }

    public boolean setBalance(int id,double balance){
        Table custs=table("Customers");
        if(custs.rowsCount()<=id)
            return false;
        custs.writeRowElement(id,String.valueOf(balance),"balance");
        return true;
    }

    public boolean setLoanAmount(int id,double amount){
        Table custs=table("Customers");
        if(custs.rowsCount()<id)
            return false;
        custs.writeRowElement(id,String.valueOf(amount),"loanAmount");
        return true;
    }

    private boolean calcOp(String op,double amount2,double amount1){
        Terminal.writeLine("calcing op:"+amount1+" - "+amount2);
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

    public String [][] search(int customerId,String socialNumber,String fullName,String city,String address, String balanceOp,double balanceLimit, String loanOp,double loanLimit, boolean isDebator){
        Table custs=table("Customers");
        if(custs.rowsCount()==0)
            return null;
        ArrayList<String []> rows=new ArrayList<String []>();
        String row[];
        Row r;
        for (int i=1;i<=custs.rowsCount();i++){
            Terminal.writeLine("## : "+i);
            r=custs.getRow(i);
            if(customerId!=0 && customerId!=(int)r.get("id"))
                continue;
            if(isDebator && (double)r.get("loanAmount")<=(double)r.get("balance"))
                continue;
            if(socialNumber!=null && socialNumber.length()!=0 && !socialNumber.equals((String)r.get("socialNumber")))
                continue;
            if(fullName!=null && fullName.length()!=0 && !fullName.equals((String)r.get("fullName")))
                continue;
            if(city!=null && city.length()!=0 && !city.equals((String)r.get("city")))
                continue;
            if(address!=null && address.length()!=0 && !address.equals((String)r.get("address")))
                continue;
            if(loanOp!=null && loanOp.length()!=0 && !calcOp(loanOp,loanLimit,(double)r.get("loanAmount")))
                continue;
            if(balanceOp!=null && balanceOp.length()!=0 && !calcOp(balanceOp,balanceLimit,(double)r.get("balance")))
                continue;
            Terminal.writeLine("## : paased");
            row=new String[7];
            row[0]=String.valueOf((int)r.get("id"));
            row[1]=(String)r.get("socialNumber");
            row[2]=(String)r.get("fullName");
            row[3]=(String)r.get("city");
            row[4]=(String)r.get("address");
            row[5]=""+(double)r.get("balance");
            row[6]=""+(double)r.get("loanAmount");
            rows.add(row);
            if((socialNumber!=null && socialNumber.length()!=0) || customerId!=0)
                break;
        }
        if(rows.size()==0)
            return null;
        String [][] toReturn=new String [rows.size()][];
        for(int i=0;i<rows.size();i++)
            toReturn[i]=Arrays.copyOf(rows.get(i), 7, String[].class);
        Terminal.writeLine("## : return size: "+rows.size());
        return toReturn;
    }

}