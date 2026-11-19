/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poepart2;
 
import org.junit.Test;
import static org.junit.Assert.*;
 
public class POEPART2Test {
 
    // ---------- Username ----------
    @Test
    public void usernameWithUnderscoreAndFiveCharsIsValid() {
        assertTrue(POEPART2.checkUserName("kyl_1"));
    }
 
    @Test
    public void usernameWithoutUnderscoreIsInvalid() {
        assertFalse(POEPART2.checkUserName("kyle1"));
    }
 
    @Test
    public void usernameLongerThanFiveCharsIsInvalid() {
        assertFalse(POEPART2.checkUserName("kyle_smith"));
    }
 
    // ---------- Password ----------
    @Test
    public void complexPasswordIsValid() {
        assertTrue(POEPART2.checkPasswordComplexity("Portal!1"));
    }
 
    @Test
    public void passwordWithoutSpecialCharacterIsInvalid() {
        assertFalse(POEPART2.checkPasswordComplexity("Portal12"));
    }
 
    @Test
    public void passwordThatIsTooShortIsInvalid() {
        assertFalse(POEPART2.checkPasswordComplexity("Po!1"));
    }
 
    // ---------- Cell number ----------
    @Test
    public void cellNumberWithInternationalCodeIsValid() {
        assertTrue(POEPART2.checkCellPhoneNumber("+27719717169"));
    }
 
    @Test
    public void cellNumberWithoutInternationalCodeIsInvalid() {
        assertFalse(POEPART2.checkCellPhoneNumber("0719717169"));
    }
 
    // ---------- Registration and login ----------
    @Test
    public void registeredUserCanLogIn() {
        String result = POEPART2.registerUser("Siya", "Arosi", "User_", "Portal!1", "+27719717169");
        assertEquals("User has been registered successfully.", result);
        assertTrue(POEPART2.loginUser("User_", "Portal!1"));
    }
 
    @Test
    public void wrongPasswordFailsLogin() {
        POEPART2.registerUser("Siya", "Arosi", "User_", "Portal!1", "+27719717169");
        assertFalse(POEPART2.loginUser("User_", "wrong"));
    }
}