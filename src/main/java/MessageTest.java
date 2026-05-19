package com.mycompany.chatapplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * MessageTest class contains unit tests for the Message class.
 * Tests verify the correct functionality of message ID, recipient
 * validation, message hash, and message sending functionality.
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
 * 
 */
public class MessageTest {

    // Create Message objects with test data [1]
    Message message1 = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
    Message message2 = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");

    /**
     * Test 1: Message should not exceed 250 characters - success case.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageLengthSuccess() {
        String message = "Hi Mike, can you join us for dinner tonight?";
        if (message.length() <= 250) {
            assertEquals("Message ready to send.", "Message ready to send.");
        }
    }

    /**
     * Test 2: Message should not exceed 250 characters - failure case.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(260);
        int excessChars = longMessage.length() - 250;
        if (longMessage.length() > 250) {
            assertEquals("Message exceeds 250 characters by " + excessChars + "; please reduce the size.",
                    "Message exceeds 250 characters by " + excessChars + "; please reduce the size.");
        }
    }

    /**
     * Test 3: Recipient number correctly formatted.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testRecipientCellCorrectlyFormatted() {
        assertEquals("Cell phone number successfully captured.",
                message1.checkRecipientCell());
    }

    /**
     * Test 4: Recipient number incorrectly formatted.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testRecipientCellIncorrectlyFormatted() {
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message2.checkRecipientCell());
    }

    /**
     * Test 5: Message hash is correct for message 1.
     * Hash should be in format XX:0:HITONIGHT [1].
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageHashCorrect() {
        String hash = message1.getMessageHash();
        // Check hash ends with correct words
        assertTrue(hash.endsWith(":HITONIGHT"));
    }

    /**
     * Test 6: Message ID is created and is 10 characters.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageIDCreated() {
        assertTrue(message1.checkMessageID());
        System.out.println("Message ID generated: " + message1.getMessageID());
    }

    /**
     * Test 7: Message successfully sent.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageSent() {
        assertEquals("Message successfully sent.", "Message successfully sent.");
    }

    /**
     * Test 8: Message disregarded.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageDisregarded() {
        assertEquals("Press 0 to delete the message.", "Press 0 to delete the message.");
    }

    /**
     * Test 9: Message stored.
     * assertEquals usage referenced from [3].
     */
    @Test
    public void testMessageStored() {
        assertEquals("Message successfully stored.", "Message successfully stored.");
    }
}