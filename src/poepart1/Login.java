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

    public boolean loginUser(String name, String password) {
        return (name.equals(this.userName) && password.equals(this.passWord)); 


    }

    public String returnLoginStatus(boolean status, String firstName, String lastName) {
        if (status) {
            return "Welcome " + getFirstName() + "," + getLastName() + "It is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
