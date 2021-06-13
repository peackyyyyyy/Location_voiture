package Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilities {

    public static Date strToDate(String str) throws ParseException {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-ddd");
        return sp.parse(str);
    }
    public static Date GregorieToDate(GregorianCalendar calendar){
        return Date.from(calendar.toZonedDateTime().toInstant());
    }
    public static String dateToString(Date date){
        return date.getYear() +"-"+date.getMonth()+"-"+date.getDay();
    }
}
