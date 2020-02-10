/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulti;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author THE HIEN
 * @param <E>
 */
public class Ulti {

    public static final long ONE_DAY = 24 * 3600 * 1000;

    
    public static String getDateFormatDetail(Timestamp date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return formatter.format(date);
    }

    public static String getDateFormat(Timestamp date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    public static boolean isFloat(String input) {

        try {
            Float.parseFloat(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static int getInt(String input) throws Exception{
        
        try {
            int integer = Integer.parseInt(input);
            if(integer <= 0){
                throw new Exception("EXCEED_BOUND");
            }
            return integer;
        } finally{
            
        }
    }

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    
    public static String generateCode(int codeLength) {
        Random r = new Random();

        String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
        String result = "";
        for (int i = 0; i < codeLength; i++) {
            result += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return result.toUpperCase();
    }

    public static Timestamp getSearchDate(String dateStr) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        Date date = (Date) sdf.parse(dateStr);
        return new Timestamp(date.getTime());
    }
    
    public static Timestamp getDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf;
        if (dateStr.contains(":")) {
            sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        }else{
            sdf = new SimpleDateFormat("dd-MM-yyyy");
        }
        sdf.setLenient(false);
        Date date = (Date) sdf.parse(dateStr);
        long diff = date.getTime() - System.currentTimeMillis();
        if (diff < ONE_DAY) {
            throw new ParseException("INVALID_TIME", 0);
        }
        return new Timestamp(date.getTime());
    }

  

}
