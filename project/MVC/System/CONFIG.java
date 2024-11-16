package MVC.System;
import MVC.System.Helpers.*;
import MVC.System.Libraries.*;
import  MVC.Controllers.*;
import  MVC.System.*;
import  MVC.Views.*;
import  MVC.Models.*;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import  MVC.System.Libraries.DataBase.*;
import java.lang.reflect.*;

public class CONFIG{
    public static String dbName="db1";
    public static String dbUser="root";
    public static String dbPass="root";
    public static boolean testMode=true;
    public static void init(){
        Logger.log("Hello world");
        if (!testMode)
            Logger.disable();
        Cache.set("view",new View());
        Cache.set("database",DataBase.load(dbName,dbUser,dbUser));
        //userInit();
    }
    public static void userInit(){
        //Cache.set("database",DataBase.load("db1","root","root"));

        Columns cols=new Columns();
        cols.add("id","int",new String[]{"autoincrement"});
        cols.add("socialNumber","char",10,new String[]{"unique"});
        cols.add("fullName","char",64);
        cols.add("city","char",64);
        cols.add("address","tinytext");
        cols.add("password","char",40);
        cols.add("balance","double");
        cols.add("loanAmount","double");
        ((DataBase)Cache.get("database")).createTable("Customers",cols);
        ((DataBase)Cache.get("database")).table("Customers").columns.print();
        cols=new Columns();
        cols.add("id","int",new String[]{"autoincrement"});
        cols.add("customerId","int");
        cols.add("branch","char",64);
        cols.add("balance","double");
        ((DataBase)Cache.get("database")).createTable("Accounts",cols);
        ((DataBase)Cache.get("database")).table("Accounts").columns.print();
        cols=new Columns();
        cols.add("id","int",new String[]{"autoincrement"});
        cols.add("customerId","int");
        cols.add("branch","char",64);
        cols.add("amount","double");
        ((DataBase)Cache.get("database")).createTable("Loans",cols);
        ((DataBase)Cache.get("database")).table("Loans").columns.print();

        //Cache.set("view",new View());

        MCustomers m1=new MCustomers();
        MLoans m2=new MLoans();
        MAccounts m3=new MAccounts();
        MSearch m4=new MSearch();

        CCloseAccount c4=new CCloseAccount();
        CAccounts c1=new CAccounts();
        CAddAccount c2=new CAddAccount();
        CClearLoan c3=new CClearLoan();
        CGetLoan c5=new CGetLoan();
        CLoans c6=new CLoans();
        CLogin c7=new CLogin();
        CPersonalInformation c8=new CPersonalInformation();
        CRegister c9=new CRegister();
        CUserMain c10=new CUserMain();
        CMasterMain c11=new CMasterMain();
        CSearchCustomers c12=new CSearchCustomers();
        CSearchAccounts c13=new CSearchAccounts();
        CSearchLoans c14=new CSearchLoans();
        CSearchBranch c15=new CSearchBranch();
        CBalance_Loan_average c16=new CBalance_Loan_average();

        VAccounts  v1=new VAccounts();
        VAddAccount v2=new VAddAccount();
        VClearLoan v3=new VClearLoan();
        VCloseAccount v4=new VCloseAccount();
        VGetLoan v5=new VGetLoan();
        VLoans v6=new VLoans();
        VLogin v7=new VLogin();
        VPersonalInformation v8=new VPersonalInformation();
        VRegister v9=new VRegister();
        VUserMain v10=new VUserMain();
        VMasterMain v11=new VMasterMain();
        VSearchCustomers v12=new VSearchCustomers();
        VSearchAccounts v13=new VSearchAccounts();
        VSearchLoans v14=new VSearchLoans();
        VSearchBranch v15=new VSearchBranch();
        //VBalance_Loan_average v16=new VBalance_Loan_average();
    }
}