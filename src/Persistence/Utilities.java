package Persistence;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utilities {

    /**
     * Convertit un string en date
     * @param str le string de la date à créer
     * @return
     * @throws ParseException
     */
    public static Date strToDate(String str) throws ParseException {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-ddd");
        return sp.parse(str);
    }

    /**
     * Transformer une gregoriancalenar en date
     * @param calendar
     * @return
     */
    public static Date GregorieToDate(GregorianCalendar calendar){
        return Date.from(calendar.toZonedDateTime().toInstant());
    }

    /**
     * Transformer une date en string
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        return date.getYear() +"-"+date.getMonth()+"-"+date.getDay();
    }
}
