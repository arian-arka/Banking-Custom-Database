package  MVC.System.Libraries.DataBase;

import MVC.System.Helpers.*;

import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;
import java.text.NumberFormat;



public abstract class Model {
    protected Table table(String name){
        return ((DataBase)Cache.get("database")).table(name);
    }
}