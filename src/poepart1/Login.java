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
        return (name.equals(this.username) && password.equals(this.password)); 


    }

    public String returnLoginStatus(boolean loginStatus) {
        if (loginStatus) {
            return "Welcome " + firstName + " " + lastName + " Login Sucessful.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
