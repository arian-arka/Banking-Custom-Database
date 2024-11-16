package  MVC.System.Helpers;
import java.util.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PasswordHashing {
    public static Boolean equals(String hashed,String password,String SALT ) {
        Boolean isAuthenticated = false;

        // remember to use the same SALT value use used while storing password
        // for the first time.
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);

        String storedPasswordHash =hashed;
        if(hashedPassword.equals(storedPasswordHash)){
            isAuthenticated = true;
        }else{
            isAuthenticated = false;
        }
        return isAuthenticated;
    }
    public static String generateHash(String input ) {
        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(input.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for (int idx = 0; idx < hashedBytes.length; ++idx) {
                byte b = hashedBytes[idx];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            // handle error here.
        }

        return hash.toString();
    }
    public static String hash(String password,String SALT){
        return generateHash(SALT + password);
    }
    public static String addSlashes(String data,String chars){
        String slashed="";
        for(char ch:data.toCharArray()){
            if(chars.indexOf(ch)==-1 || ch=='\\')
                slashed+='\\';
            slashed+=ch;
        }
        return slashed;
    }
    public static String removeSlashes(String data,String chars){
        String removed="";
        for (int i=0;i<data.length();i++){
            if(data.charAt(i)=='\\')
                i++;
            removed+=data.charAt(i);
        }
        return removed;
    }
}