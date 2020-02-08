/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ulti;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 *
 * @author THE HIEN
 */
public class Ulti {

    public static String getDateFotmat(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
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

    public static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String generateCode() {
        Random r = new Random();

        String alphabet = "1234567890abcdefghijklmnopqrstuvwxyz";
        String result="";
        for (int i = 0; i < 5; i++) {
            result += alphabet.charAt(r.nextInt(alphabet.length()));
        } 
        return result.toUpperCase();
    }
    
}
