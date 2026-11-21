/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class POEPART1 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
// TODO code application logic here
        String firstName = "";
        String lastName = "";
        String password = "";
        String username = "";
        String cellphoneNumber = "";
        boolean correctFirstName = false;
        boolean correctLastName = false;
        boolean correctUserName = false;
        boolean correctPassword = false;
        boolean correctCellphoneNumber = false;
        boolean loginStatus = false;
        
        Scanner sa = new Scanner(System.in);
        
        Login na = new Login();
        
        
        while(!correctFirstName){
            System.out.println("Please enter your first name: ");
            firstName = sa.nextLine();
            
            System.out.println(na.nameMessage(firstName));
            correctFirstName = na.checkName(firstName);
        }
        
        while(!correctLastName){
            System.out.println("Please enter your last name: ");
            lastName = sa.nextLine();
            
            System.out.println(na.nameMessage(lastName));
            correctLastName = na.checkName(lastName);
        }
        
        while(!correctUserName){
            System.out.println("Please enter your username: ");
            username = sa.nextLine();
            
            System.out.println(na.usernameMessage(username));
            correctUserName = na.checkUserName(username);
        }
        
        while(!correctPassword){
            System.out.println("Please create your password: ");
            password = sa.nextLine();
            
            System.out.println(na.passwordMessage(password));
            correctPassword = na.checkPasswordComplexity(password);
        }
        
        while(!correctCellphoneNumber){
            System.out.println("Please enter your cell number: ");
            cellphoneNumber = sa.nextLine();
            
            System.out.println(na.cellPhoneMessage(cellphoneNumber));
            correctCellphoneNumber = na.checkCellPhoneNumber(cellphoneNumber);
        }
        
        String registrationStatus = na.registerUser(firstName, lastName, username, password, cellphoneNumber);
        System.out.println(registrationStatus);
        
         
        
        
           
         System.out.println("\n===== Login =====");
        int loginAttempts = 0; 
        while(!loginStatus && loginAttempts < 3){
           System.out.println("Please enter the username to login: ");
           String loginUsername = sa.nextLine(); 
        
           System.out.println("Enter password to login");
           String loginPassword = sa.nextLine();
           
           loginStatus = na.loginUser(loginUsername, loginPassword); 
           loginAttempts++;
           
           if(!loginStatus){
               System.out.println("Username or password incorrect, please try again.");
               System.out.println("Login attempt " + loginAttempts + " of 3 failed.");
           }
        }
           if(loginStatus){
            System.out.println(na.returnLoginStatus(loginStatus));

            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Username: " + username);
            System.out.println("PassWord: " + password);
            System.out.println("Cell Number: " + cellphoneNumber);

            
            }else{
                System.out.println("Too many failed login attempts. Access denied.");
        }
        
    }    
        
    
}           
