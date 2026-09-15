/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart1;

/**
 *
 * @author Student
 */
public class Login extends Registration {

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return (enteredUsername.equals(userName) && enteredPassword.equals(passWord)); 


    }

    public String returnLoginStatus(boolean status) {
        if (status) {
            return "Welcome " + userName + "," + " It is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
