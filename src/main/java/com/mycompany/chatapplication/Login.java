
package com.mycompany.chatapplication;

/**
 *Login class handles user registration and authentication.
 * This class validates username, password, and cell phone number 
 * and provides login functionality for the QuikChat Application.
 * Note: Code structure and debugging assisted by [5].
 * 
 * References:
 * [1] B.Hamill, "Regular expression matching E.164 formatted phone numbers,"
 * Stack Overflow, 2011.[Online]. Available:
 * https://stackflow.com/questions/6478875/regular-expression-matching-e-164-formatted-phone-numbers
 * [Accessed: 16 April 2026].
 * 
 * [2] J. Farrell, Java Programming, 10th ed. Boston, MA: Cengage Learning, 2019.
 * 
 * [3] O. J. Dahl, E. W. Dijkstra, and C. A. R.  Hoare, "Object-Oriented Programming"
 *     in Structured Programming. London, Uk: Academic Press 1972, pp.1-82.
 * 
 * [4] Oracle, "Class Character,"Java SE Documentation, 2023. [Online]. Available:
 *     https://docs.oracle.com/en/java/api/java.base/java/lang/Character.html
 *     [Accessed: 16 April 2026].
 * 
 * [5] Anthropic, "Claude (claude-sonnet-4-6) [Large language model]," Anthropic, 2026. 
       [Online]. Available: https://www.anthropic.com 
       [Accessed: 16 April 2026].
 * 
 * @author Neil Anele Ntini ST10176291
 */
public class Login {
    
    // Variables to store user details
   
    // username - stores the user's chosen username 
    private final String username;
    
    // password - stores the user's password 
    private final String password; 
    
    // cellPhoneNumber - stores their SA phone number
    private final String cellPhoneNumber;
    
    // FirstName & LastName - stores their name (used in the welcome messsage) 
    private String firstName;
    
   
    private final String lastName;
    
    
   // Constructor 
   public Login(String firstName, String lastName, String username,String password,String cellPhoneNumber){
       this.firstName = firstName;
       this.lastName = lastName;
       this.username = username;
       this.password = password;
       this.cellPhoneNumber = cellPhoneNumber;
          
   }
    
   // METHOD 1 : check if username is correctly formatted contains an underscore and is no more than 5 characters.
   // Username validation logic adapted from [2]
   
   public boolean checkUserName() {
       if (username.contains ("_")&& username.length()<=5){
           return true;
       } else{
           return false;
       }
   }
   
   // METHOD 2 ; CHECK THE COMPLEXITY OF THE PASSWORD AND REQUIREMENTS- min 8 chars, uppercase , number and special char.
   // Password complexity logic adapted from [2]
   // Character class methods referenced from [4].
   public boolean checkPasswordComplexity(){
       boolean hasUpperCase = false;
       boolean hasNumber = false; 
       boolean hasSpecialChar = false;
       boolean hasMinLength = password.length()>=8;
       
       for (int i = 0; i< password.length (); i++) {
       char c = password.charAt(i);
       if (Character.isUpperCase(c)){
           hasUpperCase = true;
       }
       if (Character.isDigit(c)){
           hasNumber = true;
       }
       if (!Character.isLetterOrDigit(c)){
           hasSpecialChar = true;
       }
   }
      
       if (hasMinLength && hasUpperCase && hasNumber && hasSpecialChar){
           return true;
       } else {
           return false;
       }
       
   }
   
   // METHOD 3 checks if cellphone is correctly formatted using regex- must contain international code. 
   // Regex pattern adapted from [1]
   // E.164 international phone number format referenced from [1]
   public boolean checkCellPhoneNumber(){
       String regex = "^\\+[0-9]{10,11}$";
       if (cellPhoneNumber.matches(regex)){
           return true;
       } else {
       return false;
   }
       
  }
  // METHOD 4: registers the user and return appropriate messages. 
   // Registration logic adapted from [2]
   public String registerUser(){
       if (!checkUserName()){
           return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
       }
       if (!checkPasswordComplexity()){
           return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter , a number, and a special character.";    
       }
       if (!checkCellPhoneNumber()){
           return "Cell phone number incorrectly formatted or does not contain international code.";
       }
       return "Registration successful!";
   }
   // METHOD 5: Verify login details verifies entered username and password match stored credentials.
   // login verification logic adapted from [2]
   public boolean loginUser(String enteredUsername, String enteredPassword){
       if (enteredUsername.equals(username)&& enteredPassword.equals(password)){
           return true;
       }  else {
           return false;
       }
          
   }
   // METHOD 6: return login status message and welcome message on successful login or an error message on failure 
   // OOP method design princples referenced from [3].
   public String returnLoginStatus(String enteredUsername, String enteredPassword){
     if (loginUser (enteredUsername, enteredPassword)){ 
             return "Welcome "  + firstName + "," + lastName +  " it is great to see you again.";
       }else { 
           return "Username or password incorrect, please try again.";
       }  
   }

}
