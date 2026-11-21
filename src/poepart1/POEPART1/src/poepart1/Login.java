/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

import java.util.regex.Pattern;

/**
 *
 * @author Student
 */
public class Login {
    
    private String firstName = "";
    private String lastName = "";
    private String username = "";
    private String password = "";
    private String cellphoneNumber = "";
    
    private static final Pattern CELLPHONE_PATTERN = Pattern.compile("^\\+27\\d{9}$");
    
    public static boolean checkName(String name) {
        return name != null && name.trim().matches("[A-Za-z]+(?:[ '\\-][A-Za-z]+)*");
    }
    
    public static String nameMessage(String name) {
        return checkName(name)
                ? "Name successfully captured."
                : "Name is not valid. Please use letters only and do not leave it empty.";
    }
    
     public static boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }
     
     public static String usernameMessage(String username) {
        return checkUserName(username)
                ? "Username successfully captured."
                : "Username is not correctly formatted; please ensure that your username "
                  + "contains an underscore and is no more than five characters in length.";
    }
     
     public static boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }
     
     
    public static String passwordMessage(String password) {
        return checkPasswordComplexity(password)
                ? "Password successfully captured."
                : "Password is not correctly formatted; please ensure that the password contains "
                  + "at least eight characters, a capital letter, a number, and a special character.";
    }
    
    public boolean checkCellPhoneNumber(String cell) {
        return cell != null && CELLPHONE_PATTERN.matcher(cell).matches();
    }
 
    public  String cellPhoneMessage(String cellphoneNumber) {
        return checkCellPhoneNumber(cellphoneNumber)
                ? "Cell phone number successfully added."
                : "Cell number is incorrectly formatted or does not contain an international code; "
                  + "please correct the number and try again.";
    }
    
    public String registerUser(String firstName, String lastName, String username,
                                      String password, String cellphoneNumber) {
        if (!checkName(firstName) || !checkName(lastName)) {
            return "Registration failed: invalid name.";
        }
        if (!checkUserName(username)) {
            return "Registration failed: " + usernameMessage(username);
        }
        if (!checkPasswordComplexity(password)) {
            return "Registration failed: " + passwordMessage(password);
        }
        if (!checkCellPhoneNumber(cellphoneNumber)) {
            return "Registration failed: " + cellPhoneMessage(cellphoneNumber);
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellphoneNumber = cellphoneNumber;
        return "User has been registered successfully.";
    }
    
    public boolean loginUser(String inputUserName, String inputPassword){
        return username.equals(inputUserName) && password.equals(inputPassword);
    }
    
    public String returnLoginStatus(boolean loginStatus){
        if(loginStatus){
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        
        return "Username or password incorrect, please try again.";
    }

    
}


    /*
    Reference
    Oracle (n.d.) Pattern. Available at: https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html 
    */