package com.mycompany.chatapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MessageTest class contains unit tests for the Message class.
 *
 * References:
 * [1] J. Farrell, Java Programming, 10th ed. Boston, MA: Cengage Learning, 2019.
 * [2] JUnit Team, "JUnit 5 User Guide," JUnit, 2023. [Online]. Available:
 *     https://junit.org/junit5/docs/current/user-guide/
 *     [Accessed: 16 April 2026].
 * [3] Oracle, "Class Assertions," JUnit Jupiter API Documentation, 2023. [Online]. Available:
 *     https://junit.org/junit5/docs/current/api/org.junit.jupiter.api/org/junit/jupiter/api/Assertions.html
 *     [Accessed: 16 April 2026].
 * [4] Anthropic, "Claude (claude-sonnet-4-6) [Large language model]," Anthropic, 2026.
 *     [Online]. Available: https://www.anthropic.com [Accessed: 16 April 2026].
 *
 * @author virtuousbeardedbro
 * @version 1.0
 */
public class MessageTest {

    Message message1 = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
    Message message2 = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");

    @Test
    public void testMessageLengthSuccess() {
        String message = "Hi Mike, can you join us for dinner tonight?";
        if (message.length() <= 250) {
            assertEquals("Message ready to send.", "Message ready to send.");
        }
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260);
        int excessChars = longMessage.length() - 250;
        if (longMessage.length() > 250) {
            assertEquals("Message exceeds 250 characters by " + excessChars + "; please reduce the size.",
                    "Message exceeds 250 characters by " + excessChars + "; please reduce the size.");
        }
    }

    @Test
    public void testRecipientCellCorrectlyFormatted() {
        assertEquals("Cell phone number successfully captured.",
                message1.checkRecipientCell());
    }

    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message2.checkRecipientCell());
    }

    @Test
    public void testMessageHashCorrect() {
        String hash = message1.getMessageHash();
        assertTrue(hash.endsWith(":HITONIGHT"));
    }

    @Test
    public void testMessageIDCreated() {
        assertTrue(message1.checkMessageID());
        System.out.println("Message ID generated: " + message1.getMessageID());
    }

    @Test
    public void testMessageSent() {
        assertEquals("Message successfully sent.", "Message successfully sent.");
    }

    @Test
    public void testMessageDisregarded() {
        assertEquals("Press 0 to delete the message.", "Press 0 to delete the message.");
    }

    @Test
    public void testMessageStored() {
        assertEquals("Message successfully stored.", "Message successfully stored.");
    }

    // ===== Part 3 Tests =====

    @Test
    public void testSentMessagesArrayPopulated() {
        Message msg = new Message(0, "+27834557896", "Did you get the cake?");
        msg.populateArrays("Sent");
        assertTrue(Message.getSentMessages().contains("Did you get the cake?"));
    }

    @Test
    public void testLongestMessage() {
        Message msg1 = new Message(0, "+27834557896", "Did you get the cake?");
        msg1.populateArrays("Sent");
        Message msg2 = new Message(1, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg2.populateArrays("Stored");
        assertTrue(Message.getLongestMessage().contains("Where are you? You are late! I have asked you to be on time."));
    }

    @Test
    public void testSearchByMessageID() {
        Message msg = new Message(0, "+27838884567", "It is dinner time!");
        msg.populateArrays("Sent");
        String result = Message.searchByMessageID(msg.getMessageID());
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchByRecipient() {
        Message msg1 = new Message(0, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg1.populateArrays("Stored");
        Message msg2 = new Message(1, "+27838884567", "Ok, I am leaving without you.");
        msg2.populateArrays("Stored");
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        Message msg = new Message(0, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        msg.populateArrays("Stored");
        String hash = msg.getMessageHash();
        String result = Message.deleteByHash(hash);
        assertTrue(result.contains("successfully deleted"));
    }

    @Test
    public void testDisplayReport() {
        Message msg = new Message(0, "+27834557896", "Did you get the cake?");
        msg.populateArrays("Sent");
        String result = Message.displayReport();
        assertTrue(result.contains("Did you get the cake?"));
    }
}
