package com.mycompany.chatapplication;

import java.util.Scanner;

/**
 * Message class handles the creation and management of messages.
 * This class provides functionality for sending, storing and
 * displaying messages in the QuickChat application.
 *
 * References:
 * [1] J. Farrell, Java Programming, 10th ed. Boston, MA: Cengage Learning, 2019.
 * [2] Oracle, "Class Random," Java SE Documentation, 2023. [Online]. Available:
 *     https://docs.oracle.com/en/java/api/java.base/java/util/Random.html
 *     [Accessed: 16 April 2026].
 * [3] Oracle, "Class FileWriter," Java SE Documentation, 2023. [Online]. Available:
 *     https://docs.oracle.com/en/java/api/java.base/java/io/FileWriter.html
 *     [Accessed: 16 April 2026].
 * [4] JSON.org, "JSON in Java," Maven Repository, 2023. [Online]. Available:
 *     https://mvnrepository.com/artifact/org.json/json
 *     [Accessed: 16 April 2026].
 * [5] Anthropic, "Claude (claude-sonnet-4-6) [Large language model]," Anthropic, 2026.
 *     [Online]. Available: https://www.anthropic.com [Accessed: 16 April 2026].
 *
 * @author virtuousbeardedbro
 * 
 */
public class Message {

    // Variables to store message details
    private String messageID;
    private int messageIndex;
    private String recipient;
    private String messagePayload;
    private String messageHash;
    private String flag;
    private static int totalMessagesSent = 0;
    
    // Static arrays to store all messages [1]
    private static java.util.ArrayList<String> sentMessages = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> disregardedMessages = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> storedMessages = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> messageHashes = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> messageIDs = new java.util.ArrayList<>();
    private static java.util.ArrayList<String> recipients = new java.util.ArrayList<>();

    /**
     * Constructor for Message class.
     * Creates a new message with the provided details.
     *
     * @param messageIndex the number of the message
     * @param recipient the recipient's cell phone number
     * @param messagePayload the actual message text
     */
    public Message(int messageIndex, String recipient, String messagePayload) {
        this.messageIndex = messageIndex;
        this.recipient = recipient;
        this.messagePayload = messagePayload;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
        this.flag = "";
    }

    /**
     * Generates a random 10 digit message ID.
     * Random number generation referenced from [2].
     *
     * @return a random 10 digit string
     */
    private String generateMessageID() {
        long randomID = (long) (Math.random() * 9000000000L) + 1000000000L;
        return String.valueOf(randomID);
    }

    /**
     * Method 1: Checks if the message ID is not more than 10 characters.
     *
     * @return true if message ID is valid, false otherwise
     */
    public boolean checkMessageID() {
        if (messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method 2: Checks if the recipient cell number is correctly formatted.
     *
     * @return appropriate message based on validation result
     */
    public String checkRecipientCell() {
        String regex = "^\\+[0-9]{10,11}$";
        if (recipient.matches(regex)) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    /**
     * Method 3: Creates and returns the message hash.
     * Hash format: first two digits of ID : message number : first and last word
     *
     * @return the generated message hash in uppercase
     */
    public String createMessageHash() {
        // Get first two digits of message ID
        String firstTwoDigits = messageID.substring(0, 2);

        // Get the message number
        String messageNumber = String.valueOf(messageIndex);

        // Get first and last word of message
        String[] words = messagePayload.trim().split(" ");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        // Remove any punctuation from last word
        lastWord = lastWord.replaceAll("[^a-zA-Z0-9]", "");

        // Build the hash and convert to uppercase
        String hash = firstTwoDigits + ":" + messageNumber + ":" + firstWord + lastWord;

        return hash.toUpperCase();
    }

        /**
         * Method 4: Allows user to choose to send, store or disregard the message.
         * Loop and decision structure adapted from [1].
         *
         * @param scanner the scanner object for user input
         * @return appropriate message based on user choice
         */
         public String sentMessage(Scanner scanner) {
         System.out.println("\nWhat would you like to do with this message?");
         System.out.println("1) Send Message");
         System.out.println("2) Disregard Message");
         System.out.println("3) Store Message");
         System.out.print("Enter your choice: ");

         int choice = Integer.parseInt(scanner.nextLine());

         switch (choice) {
         case 1:
            flag = "Sent";
            totalMessagesSent++;
            // Add to sent arrays [1]
            sentMessages.add(messagePayload);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            recipients.add(recipient);
            return "Message successfully sent.";
        case 2:
            flag = "Disregarded";
            // Add to disregarded array [1]
            disregardedMessages.add(messagePayload);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            recipients.add(recipient);
            return "Press 0 to delete the message.";
        case 3:
            flag = "Stored";
            // Add to stored arrays [1]
            storedMessages.add(messagePayload);
            messageHashes.add(messageHash);
            messageIDs.add(messageID);
            recipients.add(recipient);
            storeMessage();
            return "Message successfully stored.";
        default:
            return "Invalid option selected.";
    }
}
        /**
         * Method 5: Returns all message details in the correct format.
         *
         * @return formatted string containing all message details
         */
       public String printMessages() {
        return "\nMessage ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipient +
               "\nMessage: " + messagePayload +
               "\nStatus: " + flag;
    }

    /**
     * Method 6: Returns the total number of messages sent.
     *
     * @return total number of messages sent
     */
       public int returnTotalMessages() {
            return totalMessagesSent;
       }
        /**
         * Static getter for total messages sent.
         * @return total messages sent
         */
          public static int getTotalMessagesSent() {
           return totalMessagesSent;
  }

    /**
     * Getter method for messageID.
     * @return the message ID
     */
    public void populateArrays(String flag) {
        this.flag = flag;
        messageHashes.add(messageHash);
        messageIDs.add(messageID);
        recipients.add(recipient);
        if (flag.equals("Sent")) {
            sentMessages.add(messagePayload);
        } else if (flag.equals("Stored")) {
            storedMessages.add(messagePayload);
        } else if (flag.equals("Disregarded")) {
            disregardedMessages.add(messagePayload);
        }
    }

    public static java.util.ArrayList<String> getSentMessages() {
        return sentMessages;
    }

    public String getMessageID() {
        return messageID;
    }

    /**
     * Getter method for messageHash.
     * @return the message hash
     */
    public String getMessageHash() {
        return messageHash;
    }

    /**
     * Getter method for recipient.
     * @return the recipient cell number
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     * Getter method for messagePayload.
     * @return the message text
     */
    public String getMessagePayload() {
        return messagePayload;
    }

    /**
     * Getter method for flag.
     * @return the message flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * Method 7: Stores the message in a JSON file.
     * JSON storage referenced from [3] and [4].
     *
     * @return confirmation message
     */
    public String storeMessage() {
        try {
            // Create JSON object with message details
            org.json.JSONObject jsonMessage = new org.json.JSONObject();
            jsonMessage.put("messageID", messageID);
            jsonMessage.put("messageIndex", messageIndex);
            jsonMessage.put("recipient", recipient);
            jsonMessage.put("messagePayload", messagePayload);
            jsonMessage.put("messageHash", messageHash);
            jsonMessage.put("flag", flag);

            // Read existing messages from file if it exists
            org.json.JSONArray jsonArray = new org.json.JSONArray();
            java.io.File file = new java.io.File("messages.json");

            if (file.exists()) {
                java.util.Scanner fileScanner = new java.util.Scanner(file);
                StringBuilder content = new StringBuilder();
                while (fileScanner.hasNextLine()) {
                    content.append(fileScanner.nextLine());
                }
                fileScanner.close();
                if (content.length() > 0) {
                    jsonArray = new org.json.JSONArray(content.toString());
                }
            }

            // Add new message to array
            jsonArray.put(jsonMessage);

            // Write updated array back to file
            java.io.FileWriter fileWriter = new java.io.FileWriter("messages.json");
            fileWriter.write(jsonArray.toString(2));
            fileWriter.close();

            return "Message successfully stored in JSON file.";

        } catch (Exception e) {
            return "Error storing message: " + e.getMessage();
        }
    }
        /**
         * Method 8: Displays all sent and stored messages with sender and recipient.
         * Array manipulation adapted from [1].
         *
         * @return formatted string of all messages
         */
         public static String displayAllMessages() {
         StringBuilder result = new StringBuilder();
         result.append("\n=== All Sent Messages ===\n");
          if (sentMessages.isEmpty()) {
        result.append("No sent messages.\n");
    } else {
        for (int i = 0; i < sentMessages.size(); i++) {
            result.append("Recipient: ").append(recipients.get(i))
                  .append(" | Message: ").append(sentMessages.get(i)).append("\n");
        }
    }
    result.append("\n=== All Stored Messages ===\n");
    if (storedMessages.isEmpty()) {
        result.append("No stored messages.\n");
    } else {
        for (int i = 0; i < storedMessages.size(); i++) {
            result.append("Recipient: ").append(recipients.get(sentMessages.size() + i))
                  .append(" | Message: ").append(storedMessages.get(i)).append("\n");
        }
    }
    return result.toString();
}

/**
 * Method 9: Displays the longest stored message.
 * Array search adapted from [1].
 *
 * @return the longest message
 */
public static String getLongestMessage() {
    if (sentMessages.isEmpty() && storedMessages.isEmpty()) {
        return "No messages found.";
    }
    // Combine sent and stored messages
    java.util.ArrayList<String> allMessages = new java.util.ArrayList<>();
    allMessages.addAll(sentMessages);
    allMessages.addAll(storedMessages);

    String longest = "";
    for (String message : allMessages) {
        if (message.length() > longest.length()) {
            longest = message;
        }
    }
    return "Longest message: " + longest;
}

/**
 * Method 10: Searches for a message by ID and returns recipient and message.
 * Array search adapted from [1].
 *
 * @param searchID the message ID to search for
 * @return recipient and message for the given ID
 */
public static String searchByMessageID(String searchID) {
    for (int i = 0; i < messageIDs.size(); i++) {
        if (messageIDs.get(i).equals(searchID)) {
            return "Recipient: " + recipients.get(i) +
                   "\nMessage: " + getMessageByIndex(i);
        }
    }
    return "Message ID not found.";
}

/**
 * Helper method to get message by index across all arrays.
 *
 * @param index the index to search
 * @return the message at that index
 */
private static String getMessageByIndex(int index) {
    if (index < sentMessages.size()) {
        return sentMessages.get(index);
    } else if (index < sentMessages.size() + storedMessages.size()) {
        return storedMessages.get(index - sentMessages.size());
    } else {
        return disregardedMessages.get(index - sentMessages.size() - storedMessages.size());
    }
}

/**
 * Method 11: Searches for all messages sent to a particular recipient.
 * Array search adapted from [1].
 *
 * @param searchRecipient the recipient to search for
 * @return all messages sent to that recipient
 */
public static String searchByRecipient(String searchRecipient) {
    StringBuilder result = new StringBuilder();
    boolean found = false;
    for (int i = 0; i < recipients.size(); i++) {
        if (recipients.get(i).equals(searchRecipient)) {
            result.append(getMessageByIndex(i)).append("\n");
            found = true;
        }
    }
    if (!found) {
        return "No messages found for recipient: " + searchRecipient;
    }
    return result.toString();
}

/**
 * Method 12: Deletes a message using the message hash.
 * Array manipulation adapted from [1].
 *
 * @param hash the message hash to delete
 * @return confirmation message
 */
public static String deleteByHash(String hash) {
    for (int i = 0; i < messageHashes.size(); i++) {
        if (messageHashes.get(i).equals(hash)) {
            String deletedMessage = getMessageByIndex(i);
            // Remove from all arrays
            messageHashes.remove(i);
            messageIDs.remove(i);
            recipients.remove(i);
            // Remove from appropriate message array
            if (i < sentMessages.size()) {
                sentMessages.remove(i);
            } else if (i < sentMessages.size() + storedMessages.size()) {
                storedMessages.remove(i - sentMessages.size());
            } else {
                disregardedMessages.remove(i - sentMessages.size() - storedMessages.size());
            }
            return "Message: \"" + deletedMessage + "\" successfully deleted.";
        }
    }
    return "Message hash not found.";
}

/**
 * Method 13: Displays a full report of all sent messages.
 * Report generation adapted from [1].
 *
 * @return full report of all messages
 */
public static String displayReport() {
    StringBuilder result = new StringBuilder();
    result.append("\n=== Full Message Report ===\n");
    if (messageHashes.isEmpty()) {
        return "No messages to display.";
    }
    for (int i = 0; i < messageHashes.size(); i++) {
        result.append("\n--- Message ").append(i + 1).append(" ---\n");
        result.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
        result.append("Recipient: ").append(recipients.get(i)).append("\n");
        result.append("Message: ").append(getMessageByIndex(i)).append("\n");
    }
    return result.toString();
}
}
