/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poepart3;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class MessageTest {
    
    private Message message;

    // Creates test message 1 and clears the shared counters before every test
    @Before
    public void setUp() {
        Message.resetMessages();
        message = new Message("+27834557896", "Did you get the cake?", 1);
    }

    @Test
    public void testMessageIDIsTenCharacters() {
        assertTrue(message.checkMessageID());
        assertEquals(10, message.getMessageID().length());
    }

    @Test
    public void testMessageHashFormat() {
        assertTrue(message.getMessageHash().endsWith(":1:DIDCAKE"));
    }

    @Test
    public void testRecipientCellValid() {
        assertTrue(message.recipientCellValid("+27834557896"));
    }

    @Test
    public void testRecipientCellInvalidWithoutInternationalCode() {
        assertFalse(message.recipientCellValid("0838884567"));
    }

    @Test
    public void testMessageLengthValid() {
        Message longMessage = new Message("+27834557896", "a".repeat(250), 2);

        assertTrue(longMessage.messageLengthValid());
        assertEquals("Message ready to send.", longMessage.checkMessageLength());
    }

    @Test
    public void testMessageTooLong() {
        Message longMessage = new Message("+27834557896", "a".repeat(251), 2);

        assertFalse(longMessage.messageLengthValid());
        assertEquals("Message exceeds 250 characters by 1; please reduce the size",
                longMessage.checkMessageLength());
    }

    @Test
    public void testMessageFlags() {
        message.setMessageOption(1);
        assertEquals("Sent", message.getMessageFlag());

        message.setMessageOption(2);
        assertEquals("Disregard", message.getMessageFlag());

        message.setMessageOption(3);
        assertEquals("Stored", message.getMessageFlag());

        message.setMessageOption(0);
        assertEquals("", message.getMessageFlag());
    }

    @Test
    public void testSendMessage() throws IOException {
        message.setMessageOption(1);

        assertEquals("Message successfully sent", message.sentMessage());
        assertEquals(1, message.returnTotalMessages());
    }

    @Test
    public void testDisregardMessage() throws IOException {
        message.setMessageOption(2);

        assertEquals("Press 0 to delete message", message.sentMessage());
        assertEquals(0, message.returnTotalMessages());
    }

    @Test
    public void testInvalidOption() throws IOException {
        message.setMessageOption(9);

        assertEquals("Invalid option.", message.sentMessage());
    }
}