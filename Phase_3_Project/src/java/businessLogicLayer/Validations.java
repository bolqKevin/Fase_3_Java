/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package businessLogicLayer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Kevin Bolaños V.
 */
public class Validations {
        //Validacion de campos
    public static boolean validatePhoneNumber(String phoneNumber) {
        // Validating that the phone number contains only digits
        return phoneNumber.matches("\\d+");
    }
    
 public static boolean validateQuantity(String input) {
    return input.matches("\\d{1,2}");
}
    
public static boolean validateBirthDate(String birthDate) {
    if (birthDate.isEmpty()) {
        return false;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);

    try {
        java.util.Date parsedDate = dateFormat.parse(birthDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(parsedDate);

        int birthYear = cal.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        if (birthYear < 1990 || birthYear > currentYear) {
            return false; // Birth year is not within the valid range
        }

        java.sql.Date sqlDate = new java.sql.Date(parsedDate.getTime()); // Convert to java.sql.Date

        return true;
    } catch (ParseException e) {
        return false;
    }
}

    public static boolean validateId(String id) {
        // Validating that the ID contains exactly 9 digits
        return id.matches("\\d{9}");
    }
    public static boolean validateIdToSearch(String id) {
        // Validating that the ID contains exactly 9 digits
        return id.matches("\\d{1,9}");
    }
    public static boolean validateEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return email.matches( "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
}
