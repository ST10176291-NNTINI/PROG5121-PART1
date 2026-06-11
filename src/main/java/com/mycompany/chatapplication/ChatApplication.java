package com.mycompany.chatapplication;

import java.util.Scanner;

/**
 * ChatApplication is the main entry point for the QuickChat application.
 * This class handles user input for registration, login and messaging.
 *
 * References:
 * [1] J. Farrell, Java Programming, 10th ed. Boston, MA: Cengage Learning, 2019.
 * [2] Oracle, "Class Scanner," Java SE Documentation, 2023. [Online]. Available:
 *     https://docs.oracle.com/en/java/api/java.base/java/util/Scanner.html
 *     [Accessed: 16 April 2026].
 * [3] Oracle, "Class String," Java SE Documentation, 2023. [Online]. Available:
 *     https://docs.oracle.com/en/java/api/java.base/java/lang/String.html
 *     [Accessed: 16 April 2026].
 * [4] Anthropic, "Claude (claude-sonnet-4-6) [Large language model]," Anthropic, 2026.
 *     [Online]. Available: https://www.anthropic.com [Accessed: 16 April 2026].
 *
 * @author virtuousbeardedbro
 * 
 */
public class ChatApplication {

   /**
     * Main method - entry point of the QuickChat application.
     * Handles user registration, login and messaging via console input.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Scanner used to read user input from console [2]
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Welcome to QuickChat ===");
        System.out.println("Please register to continue.");

        // Get user details
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cell Phone Number (e.g. +27838968976): ");
        String cellPhone = scanner.nextLine();

        // Create Login object
        Login login = new Login(firstName, lastName, username, password, cellPhone);

        // Attempt registration
        String registrationResult = login.registerUser();
        System.out.println(registrationResult);

        // Only allow login if registration was successful
        if (registrationResult.equals("Registration successful!")) {
            System.out.println("\nPlease login to continue.");

            System.out.print("Enter Username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String enteredPassword = scanner.nextLine();

            // Check login status
            String loginStatus = login.returnLoginStatus(enteredUsername, enteredPassword);
            System.out.println(loginStatus);

            // Only show menu if login was successful
            if (loginStatus.startsWith("Welcome")) {
                
                // Show welcome message
                System.out.println("\nWelcome to QuickChat.");

                // Ask user how many messages they want to send
                System.out.print("How many messages would you like to send? ");
                int numMessages = Integer.parseInt(scanner.nextLine());

                // Menu loop - runs until user selects quit
boolean running = true;
while (running) {
    // Display menu
    System.out.println("\n=== Menu ===");
    System.out.println("1) Send Messages");
    System.out.println("2) Show recently sent messages");
    System.out.println("3) Stored Messages");
    System.out.println("4) Quit");
    System.out.print("Enter your choice: ");

    int menuChoice = Integer.parseInt(scanner.nextLine());

    switch (menuChoice) {
        case 1:
            // Send messages using for loop
            for (int i = 0; i < numMessages; i++) {
                System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");

                // Get recipient number
                System.out.print("Enter recipient cell number (e.g. +27838968976): ");
                String recipient = scanner.nextLine();

                // Get message
                System.out.print("Enter your message (max 250 characters): ");
                String messagePayload = scanner.nextLine();

                // Check message length
                if (messagePayload.length() > 250) {
                    int excess = messagePayload.length() - 250;
                    System.out.println("Message exceeds 250 characters by " + excess + "; please reduce the size.");
                    i--;
                    continue;
                }

                // Create message object
                Message message = new Message(i, recipient, messagePayload);

                // Check recipient number
                System.out.println(message.checkRecipientCell());

                // Display message hash
                System.out.println("Message Hash: " + message.getMessageHash());

                // Ask user what to do with message
                String result = message.sentMessage(scanner);
                System.out.println(result);

                // Display full message details
                System.out.println(message.printMessages());
            }

            // Display total messages sent
            System.out.println("\nTotal messages sent: " + Message.getTotalMessagesSent());
            break;

        case 2:
            System.out.println("Coming Soon.");
            break;

        case 3:
            // Stored Messages submenu
            boolean storedMenuRunning = true;
            while (storedMenuRunning) {
                System.out.println("\n=== Stored Messages Menu ===");
                System.out.println("a) Display all messages");
                System.out.println("b) Display longest message");
                System.out.println("c) Search by message ID");
                System.out.println("d) Search by recipient");
                System.out.println("e) Delete message by hash");
                System.out.println("f) Display report");
                System.out.println("g) Back to main menu");
                System.out.print("Enter your choice: ");

                String subChoice = scanner.nextLine().toLowerCase();

                switch (subChoice) {
                    case "a":
                        System.out.println(Message.displayAllMessages());
                        break;
                    case "b":
                        System.out.println(Message.getLongestMessage());
                        break;
                    case "c":
                        System.out.print("Enter Message ID to search: ");
                        String searchID = scanner.nextLine();
                        System.out.println(Message.searchByMessageID(searchID));
                        break;
                    case "d":
                        System.out.print("Enter recipient number to search: ");
                        String searchRecipient = scanner.nextLine();
                        System.out.println(Message.searchByRecipient(searchRecipient));
                        break;
                    case "e":
                        System.out.print("Enter message hash to delete: ");
                        String hash = scanner.nextLine();
                        System.out.println(Message.deleteByHash(hash));
                        break;
                    case "f":
                        System.out.println(Message.displayReport());
                        break;
                    case "g":
                        storedMenuRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            break;

        case 4:
            running = false;
            System.out.println("Goodbye!");
            break;

        default:
            System.out.println("Invalid option. Please try again.");
    }
}
            }
        }
        scanner.close();
    }
}