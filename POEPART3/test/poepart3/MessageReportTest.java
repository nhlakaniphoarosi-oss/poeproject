/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package poepart3;

 
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class MessageReportTest {
    
   
    
    private static final String NEW_LINE = System.lineSeparator();

    private static final String MESSAGE_1 = "Did you get the cake?";
    private static final String MESSAGE_2 = "Where are you? You are late! I have asked you to be on time.";
    private static final String MESSAGE_3 = "Yohoooo, I am at your gate.";
    private static final String MESSAGE_4 = "It is dinner time !";
    private static final String MESSAGE_5 = "Ok, I am leaving without you.";

    private MessageReport report;

    // Loads the five test messages into a fresh report before every test
    @Before
    public void setUp() {
        report = new MessageReport();

        report.addMessage("0000000001", "00:1:DIDCAKE", "+27834557896", MESSAGE_1, "Sent");
        report.addMessage("0000000002", "00:2:WHERETIME", "+27838884567", MESSAGE_2, "Stored");
        report.addMessage("0000000003", "00:3:YOHOOOOGATE", "+27834484567", MESSAGE_3, "Disregard");
        // Message 4: the task lists 0838884567 as its number, which is also used as its ID
        report.addMessage("0838884567", "08:4:ITTIME", "0838884567", MESSAGE_4, "Sent");
        report.addMessage("0000000005", "00:5:OKYOU", "+27838884567", MESSAGE_5, "Stored");
    }

    // ---- Arrays correctly populated ----

    @Test
    public void testSentMessagesArrayPopulated() {
        String expected = MESSAGE_1 + NEW_LINE + MESSAGE_4;
        assertEquals(expected, report.displaySentMessages());
    }

    @Test
    public void testStoredMessagesArrayPopulated() {
        String expected = MESSAGE_2 + NEW_LINE + MESSAGE_5;
        assertEquals(expected, report.displayStoredMessages());
    }

    @Test
    public void testDisregardedMessagesArrayPopulated() {
        assertEquals(MESSAGE_3, report.displayDisregardedMessages());
    }

    // ---- Display the longest message ----

    @Test
    public void testLongestMessage() {
        assertEquals(MESSAGE_2, report.displayLongestMessage());
    }

    @Test
    public void testLongestStoredMessage() {
        assertEquals(MESSAGE_2, report.displayLongestStoredMessage());
    }

    // ---- Search for a message ID ----

    @Test
    public void testSearchByMessageID() {
        assertEquals(MESSAGE_4, report.searchByMessageID("0838884567"));
    }

    @Test
    public void testSearchByMessageIDNotFound() {
        assertEquals("Message ID Not Found.", report.searchByMessageID("9999999999"));
    }

    // ---- Search all messages for a recipient ----

    @Test
    public void testSearchByRecipient() {
        String expected = MESSAGE_2 + NEW_LINE + MESSAGE_5;
        assertEquals(expected, report.searchByRecipient("+27838884567"));
    }

    @Test
    public void testSearchByRecipientNotFound() {
        assertEquals("No Stored Messages Found For This Recipient.",
                report.searchByRecipient("+27000000000"));
    }

    // ---- Delete a message using the message hash ----

    @Test
    public void testDeleteByMessageHash() {
        String expected = "Message: " + MESSAGE_2 + " Successfully deleted.";
        assertEquals(expected, report.deleteByMessageHash("00:2:WHERETIME"));
    }

    @Test
    public void testDeleteRemovesMessageFromArrays() {
        report.deleteByMessageHash("00:2:WHERETIME");

        assertEquals(MESSAGE_5, report.displayStoredMessages());
        assertEquals("Message ID Not Found.", report.searchByMessageID("0000000002"));
    }

    @Test
    public void testDeleteByMessageHashNotFound() {
        assertEquals("Message Hash Not Found.", report.deleteByMessageHash("99:9:NOHASH"));
    }

    // ---- Display report (all sent messages: hash, recipient, message) ----

    @Test
    public void testDisplayReport() {
        String expected = "Message Hash: 00:1:DIDCAKE" + NEW_LINE
                + "Recipient: +27834557896" + NEW_LINE
                + "Message: " + MESSAGE_1 + NEW_LINE + NEW_LINE
                + "Message Hash: 08:4:ITTIME" + NEW_LINE
                + "Recipient: 0838884567" + NEW_LINE
                + "Message: " + MESSAGE_4;
        assertEquals(expected, report.displayReport());
    }

    @Test
    public void testDisplayReportWhenEmpty() {
        assertEquals("No Sent Messages To Display.", new MessageReport().displayReport());
    }

    // ---- Stored messages written to and read back from the JSON file ----

    @Test
    public void testStoredMessagesWrittenAndReadFromFile() throws IOException {
        File file = File.createTempFile("storedTest", ".json");
        file.deleteOnExit();

        assertEquals("Stored Message Saved.", report.writeStoredMessagesToFile(file.getAbsolutePath()));

        MessageReport loadedReport = new MessageReport();
        assertEquals("Stored Messages Loaded.", loadedReport.readStoredMessagesFromFile(file.getAbsolutePath()));
        assertEquals(MESSAGE_2 + NEW_LINE + MESSAGE_5, loadedReport.displayStoredMessages());
    }

    @Test
    public void testReadFromMissingFile() {
        assertEquals("Stored Messages File Was Not Found.",
                new MessageReport().readStoredMessagesFromFile("doesNotExist.json"));
    }
}