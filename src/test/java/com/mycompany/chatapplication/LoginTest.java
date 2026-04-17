package com.mycompany.chatapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * LoginTest class contains unit tests for the login class. 
 * Tests verify the correct functionality of username validation, 
 * password complexity, cell phone validation, and login authentication. 
 * 
 * References:
 * [1] J. Farrel, Java  Programming, 10th ed. Boston, MA: Cengage learning,2019.
 * 
 * [2] JUNIT Team, "JUnit 5 User Guide," JUnit,2023.{Online}. Available:
 *     https://junit.org/junit5/docs/current/user-guide/
 *     [Accessed: 16 April 2026].
 * 
 * [3] Oracle, "Class Assertions," JUnit Jupiter API Documentation, 2023. [Online]. Available:
 *     https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html
 *     [Accessed: 16 April 2026].
 * 
 * [4] B. Hamill, "Regular expression matching E.164 formatted phone numbers,"
 *     Stack Overflow, 2011. [Online]. Available:
 *     https://stackoverflow.com/questions/6478875/regular-expression-matching-e-164-formatted-phone-numbers
 *     [Accessed: 16 April 2026].
 *     
 * @author Neil Anele Ntini ST10176291
 */

public class LoginTest {
// Create a Login object with test data to use in all tests[1]
    Login login = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "+27838968976");

    /**
     * Test 1:Verifies that a Username is correctly formatted and passes validation.
     * Username must contain an underscore and no more than 5 characters [1]
     * assertTrue usage referenced from [2]
     */
      
    
    @Test
    public void testUsernameCorrectlyFormatted() {
        assertTrue(login.checkUserName());
    }

    /**
     * Test 2: Verifies that an incorrectly formatted username fails validation.
     * Username without underscore and exceeding 5 characters should return false [1].
     * assertFalse usage referenced from [2].
     */
    @Test
    public void testUsernameIncorrectlyFormatted() {
        //Bad username - no underscore and too long
        Login badLogin = new Login("John", "Doe", "kyle!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(badLogin.checkUserName());
    }

    /**
     * Test 3: Verifies that a password meeting all complexity requirements passes.
     * Password must have 8+ chars, uppercase, number and special character [1].
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testPasswordMeetsComplexity() {
        assertEquals("Password successfully captured.", login.checkPasswordComplexity() ? "Password successfully captured." : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }

    /**
     * Test 4: Verifies that a password not meeting complexity requirements fails.
     * Simple password without required complexity should return failure message [1].
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testPasswordDoesNotMeetComplexity() {
        // Bad password - no uppercase, number or special character
        Login badLogin = new Login("John", "Doe", "kyl_1", "password", "+27838968976");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", badLogin.checkPasswordComplexity() ? "Password successfully captured." : "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
    }

    /**
     * Test 5: Verifies that a correctly formatted cell phone number passes validation.
     * Cell phone must contain international code and be correct length [4].
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testCellPhoneCorrectlyFormatted() {
        assertEquals("Cell number successfully captured.", login.checkCellPhoneNumber() ? "Cell number successfully captured." : "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
    }

    /**
     * Test 6: Verifies that an incorrectly formatted cell phone number fails validation.
     * Cell phone without international code should return failure message [4].
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testCellPhoneIncorrectlyFormatted() {
        // Bad cell phone - no international code
        Login badLogin = new Login("John", "Doe", "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", badLogin.checkCellPhoneNumber() ? "Cell number successfully captured." : "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.");
    }

    /**
     * Test 7: Verifies that correct credentials result in a successful login.
     * Matching username and password should return true [1].
     * assertTrue usage referenced from [2].
     */
    @Test
    public void testLoginSuccessful() {
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    
    /**
     * Test 8: Verifies that incorrect credentials result in a failed login.
     * Wrong password should return false [1].
     * assertFalse usage referenced from [2].
     */
    @Test
    public void testLoginFailed() {
        // Wrong password should fail login
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }
}   
