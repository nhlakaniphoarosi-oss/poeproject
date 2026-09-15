/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class POEPART1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Login loginApp = new Login();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\n===== Register =====");
        
        System.out.println("Please enter the username: ");
        String username = scan.nextLine();
        
        System.out.println("Enter password");
        String password = scan.nextLine();
        
        System.out.println("Enter your phone number");
        String cellPhone = scan.nextLine();
        
        String regStatus = loginApp.registerUser(username, password, cellPhone);
        System.out.println(regStatus);
        
         
        
        
           
           
         System.out.println("\n===== Login =====");
        
        System.out.println("Please enter the username to login: ");
        String loginUsername = scan.nextLine();
        
        System.out.println("Enter password to login");
        String loginPassword = scan.nextLine();
        
        boolean status = loginApp.loginUser(loginUsername, loginPassword);
        String message = loginApp.returnLoginStatus(status);
        System.out.println(message);
           
        
        
       
    }
    
}
