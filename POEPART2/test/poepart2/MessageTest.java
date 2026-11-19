/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package poepart2;
 
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
 
public class MessageTest {
 
    // Static counters are shared between tests, so reset them before each one
    @Before
    public void setUp() {
        Message.resetMessages();
    }
 
    @Test
    public void messageIdIsTenDigitsAndPassesCheck() {
        Message m = new Message("+27718693002", "Hi Mike", 0);
        assertEquals(10, m.getMessageID().length());
        assertTrue(m.checkMessageID());
    }
 
    @Test
    public void validRecipientIsAccepted() {
        Message m = new Message("+27718693002", "Hello", 0);
        assertTrue(m.recipientCellValid());
        assertEquals("Cellphone number successfully captured", m.checkRecipientCell());
    }
 
    @Test
    public void recipientWithoutInternationalCodeIsRejected() {
        Message m = new Message("08575975889", "Hello", 0);
        assertFalse(m.recipientCellValid());
    }
 
    @Test
    public void recipientThatIsTooLongIsRejected() {
        Message m = new Message("+270719717169", "Hello", 0);   // 13 characters
        assertFalse(m.recipientCellValid());
    }
 
    @Test
    public void messageOf250CharactersIsAccepted() {
        Message m = new Message("+27718693002", "a".repeat(250), 0);
        assertTrue(m.messageLengthValid());
        assertEquals("Message ready to send.", m.checkMessageLength());
    }
 
    @Test
    public void messageOf251CharactersIsRejected() {
        Message m = new Message("+27718693002", "a".repeat(251), 0);
        assertFalse(m.messageLengthValid());
        assertEquals("Message exceeds 250 characters by 1; please reduce the size",
                m.checkMessageLength());
    }
 
    @Test
    public void messageHashUsesIdNumberFirstAndLastWord() {
        Message m = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight", 0);
        m.setMessageID("0012345678");   // fixes the ID so the hash is predictable
        assertEquals("00:0:HITONIGHT", m.getMessageHash());
    }
 
    @Test
    public void sendingAMessageCountsIt() throws IOException {
        Message m = new Message("+27718693002", "Hello there", 0);
        m.setMessageOption(1);
        assertEquals("Message successfully sent", m.sentMessage());
        assertEquals(1, m.returnTotalMessages());
    }
 
    @Test
    public void disregardingAMessageDoesNotCountIt() throws IOException {
        Message m = new Message("+27718693002", "Hello there", 0);
        m.setMessageOption(2);
        assertEquals("Press 0 to delete message", m.sentMessage());
        assertEquals(0, m.returnTotalMessages());
    }
 
    @Test
    public void twoSentMessagesGiveATotalOfTwo() throws IOException {
        Message a = new Message("+27718693002", "First", 0);
        Message b = new Message("+27718693002", "Second", 1);
        a.setMessageOption(1);
        b.setMessageOption(1);
        a.sentMessage();
        b.sentMessage();
        assertEquals(2, a.returnTotalMessages());
    }
}
 