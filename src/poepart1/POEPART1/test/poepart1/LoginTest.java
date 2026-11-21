/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poepart1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        login = new Login();
        login.registerUser("Kyle", "Smith", "kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }

    // --- assertEquals tests ---

    @Test
    public void testWelcomeMessageCorrectLogin() {
        boolean status = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        assertEquals("Welcome Kyle, Smith it is great to see you again.",
                login.returnLoginStatus(status));
    }

    @Test
    public void testUsernameIncorrectlyFormattedMessage() {
        assertEquals("Username is not correctly formatted; please ensure that your username "
                + "contains an underscore and is no more than five characters in length.",
                Login.usernameMessage("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexityMessage() {
        assertEquals("Password successfully captured.",
                Login.passwordMessage("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityMessage() {
        assertEquals("Password is not correctly formatted; please ensure that the password "
                + "contains at least eight characters, a capital letter, a number, and a special character.",
                Login.passwordMessage("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormattedMessage() {
        assertEquals("Cell phone number successfully added.",
                login.cellPhoneMessage("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormattedMessage() {
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; "
                + "please correct the number and try again.",
                login.cellPhoneMessage("08966553"));
    }

    // --- assertTrue / assertFalse tests ---

    @Test
    public void testLoginSuccessful() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        assertFalse(login.loginUser("wrong_1", "wrongPass1!"));
    }

    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(Login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        assertFalse(Login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexity() {
        assertTrue(Login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        assertFalse(Login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}
