
package com.mycompany.chatapplication;

import java.util.Scanner;

/**
 * ChatApplication is the main entry point for the QuickChat application.
 * This class handles user input for registration and login functionality.
 * Note: Code structure and debugging assisted by [3].
 * 
 * References:
 * [1] J. Farell, Java Programming, 10th ed. Boston, MA: Cengage Learning. 2019.
 * 
 * [2] Oracle, "Class Scanner," Java SE Documentation, 2023. [Online]. Available;
 *     https://docs.oracle.com/en/java/api/java.base/java/lang/String.html
 *     [Accessed: 16 April 2026] 
 * [3] Anthropic, "Claude (claude-sonnet-4-6) [Large language model]," Anthropic, 2026. 
       [Online]. Available: https://www.anthropic.com 
       [Accessed: 16 April 2026].
 * 
 * @author Neil Anele Ntini ST10176291
 */

public class ChatApplication {
    
    /**
     *  Main method - entry point of the QuickChat application.
     *  Handles user registration and login via console input.
     *  Input handling adapted [1]
     *  Scanner usage reference [2] 
     *  
     * @param args command line arguments (not used)
     */

    public static void main(String[] args) {
        // Scanner used to read user input from console [2]
        Scanner scanner = new Scanner (System.in);
        
        System.out.println("===WElcome to Quickchat===");
        System.out.println("Please register to continue.");
        
        // Get user details- string handling referenced from [3] 
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();
        
       System.out.print("Enter Username: ");
       String username = scanner.nextLine();
       
       System.out.print("Enter Password: ");
       String password = scanner.nextLine();
       
       System.out.print("Enter Cell Phone Number: ");
       String cellPhone = scanner.nextLine();
       
       // Create Login object- OOP object instantiation adapted from [1]
       
       Login login = new Login (firstName, lastName, username, password, cellPhone);
       
       // Attempt registration ad display result
       String registrationResult = login.registerUser();
       System.out.println(registrationResult);
       
       // only allow login if registration was successful [1]
       if (registrationResult.equals("Registration successful!")){
           System.out.println("\nPlease login to continue.");
           
           System.out.print("Enter Username: ");
           String enteredUsername = scanner.nextLine();
           
           System.out.print("Enter Password: ");
           String enteredPassword = scanner.nextLine();
           
           // return and display  login status 
           System.out.println(login.returnLoginStatus(enteredUsername, enteredPassword));
       }
       //Close scanner to free resources [2]
        scanner.close();
     }
}
