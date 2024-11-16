package  MVC.Controllers;
import  MVC.System.Helpers.*;
import  MVC.System.Libraries.*;
import java.util.*;
import  MVC.System.Libraries.DataBase.*;
import  MVC.Models.*;
import  MVC.Views.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CRegister extends Controller{
    public CRegister(){
        Terminal.writeLine("from CRegister");
    }
    public void index()
    {
        view("VRegister",false);
    }
    public String register(String name,String socialNumber,String city,String address,String password){
        if(name==null || name.length()==0)
            return "Name Required";
        if(socialNumber==null || socialNumber.length()==0)
            return "Social Number Required";
        if(socialNumber.length()!=10)
            return "Social Number Must Be 10 Digits";
        Terminal.writeLine("scoial$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ : "+socialNumber.length());
        if(!Validate.isNumber(socialNumber))
            return "Social Number Must Be Digit only";
        if(city==null || city.length()==0)
            return "City Required";
        if(address==null || address.length()==0)
            return "Address Required";
        if(password==null || password.length()==0)
            return "Password Required";
        if(password.length()<8)
            return "Password length must be at least 8 character";
        if(password.length()>64)
            return "Password length must be less than 64";
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,64}$";
        Pattern p = Pattern.compile(regex);
        if(!p.matcher(password).matches())
            return "Password must at least contains one digit and one lowecase and one uppercase character";
        if(((MCustomers)model("MCustomers")).registerUser(name,socialNumber,city,address,password))
            return "Welcome to the Bank :)";
        else
            return "User with this social number has been registered before";
    }
}