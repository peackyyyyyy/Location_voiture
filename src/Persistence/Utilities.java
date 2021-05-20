package Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public static Date strToDate(String str) throws ParseException {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
        return sp.parse(str);
    }

    public static String dateToString(Date date){
        return date.getYear() +"-"+date.getMonth()+"-"+date.getDay();
    }
}
