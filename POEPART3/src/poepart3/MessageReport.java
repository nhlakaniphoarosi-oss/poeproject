/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart3;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *
 * @author Student
 */
public class MessageReport {
    
    private String[] sentMessages = new String[20];
    private String[] disregardedMessages = new String[20];
    private String[] storedMessages = new String[20];
    private String[] messageHashes = new String[20];
    private String[] messageIDs = new String[20];
    private String[] recipients = new String[20];
    private String[] allMessages = new String[20];
    private String[] messageFlags = new String[20];
    
    private int sentCount = 0;
    private int disregardedCount = 0;
    private int storedCount = 0;
    private int messageCount = 0;
    
    public MessageReport(){
        fillArray(sentMessages);
        fillArray(disregardedMessages);
        fillArray(storedMessages);
        fillArray(messageHashes);
        fillArray(messageIDs);
        fillArray(recipients);
        fillArray(allMessages);
        fillArray(messageFlags);
    }
    
    private void fillArray(String[] array){
        for(int r = 0; r < array.length; r++){
            array[r] = "";
        }
    }
    
    public void addMessage(String messageID, String messageHash, String recipient, String message, String messageFlag){
        if(messageCount < allMessages.length){
            messageIDs[messageCount] = messageID;
            messageHashes[messageCount] = messageHash;
            recipients[messageCount] = recipient;
            allMessages[messageCount] = message;
            messageFlags[messageCount] = messageFlag;
            messageCount++;
            
            if(messageFlag.equalsIgnoreCase("Sent")){
                sentMessages[sentCount] = message;
                sentCount++;
            }else if(messageFlag.equalsIgnoreCase("Stored")){
                storedMessages[storedCount] = message;
                storedCount++;
            }else if(messageFlag.equalsIgnoreCase("Disregard")){
                disregardedMessages[disregardedCount] = message;
                disregardedCount++;
            }
        }
    }
    
    public String displaySentMessages(){
        return joinMessages(sentMessages, sentCount);
    }
    
    public String displayStoredMessages(){
        return joinMessages(storedMessages, storedCount);
    }
    
    public String displayDisregardedMessages(){
        return joinMessages(disregardedMessages, disregardedCount);
    }
    
    private String joinMessages(String[] messages, int count){
        String result = "";
        String newLine = System.lineSeparator();
        
        for(int r = 0; r < count; r++){
            result += messages[r];
            if(r < count - 1){
                result += newLine;
            }
        }
        return result;
    }
    
    public String displayLongestMessage(){
        String longestMessage = "";
        for(int r = 0; r < messageCount; r++){
            if(allMessages[r].length() > longestMessage.length()){
                longestMessage = allMessages[r];
            }
        }
        return longestMessage;
    }
    
    public String displayLongestStoredMessage(){
        String longestMessage = "";
        for(int r = 0; r < messageCount; r++){
            if(messageFlags[r].equalsIgnoreCase("Stored")){
                if (allMessages[r].length() > longestMessage.length()){
                    longestMessage = allMessages[r];
                }
            }
        }
        if(longestMessage.isEmpty()){
            longestMessage = "No Stored Messages Found.";
        }
        return longestMessage;
    }
    
    public String searchByMessageID(String searchID){
        String foundMessage = "Message ID Not Found.";
        for(int r = 0; r < messageCount; r++){
            if(messageIDs[r].equals(searchID)){
                foundMessage = allMessages[r];
            }
        }
        return foundMessage;
    }
    
    public String searchByMessageIDDetails(String searchID){
        String foundMessage = "Message ID Not Found.";
        String newLine = System.lineSeparator();
        for(int r = 0; r < messageCount; r++){
            if(messageIDs[r].equals(searchID)){
                foundMessage = "Recipient: " + recipients[r]
                        + newLine + "Message: " + allMessages[r];
            }
        }
        return foundMessage;
    }
    
    public String searchByRecipient(String searchRecipient){
        String result = "";
        String newLine = System.lineSeparator();
        for(int r = 0; r < messageCount; r++){
            if(recipients[r].equals(searchRecipient) && messageFlags[r].equalsIgnoreCase("Stored")){
                if(result.isEmpty()){
                    result = allMessages[r];
                }else{
                    result += newLine + allMessages[r];
                }
            }
        }
        if(result.isEmpty()){
            result = "No Stored Messages Found For This Recipient.";
        }
        return result;
    }
    
    public String deleteByMessageHash(String searchHash){
        String result = "Message Hash Not Found.";
        int position = -1;
        String deletedMessage = "";
        
        for(int r = 0; r < messageCount; r++){
            if(messageHashes[r].equals(searchHash)){
                position = r;
                deletedMessage = allMessages[r];
            }
        }
        if(position >= 0){
            removeFromFlagArray(deletedMessage);
            removeFromMainArrays(position);
            result = "Message: " + deletedMessage + " Successfully deleted.";
        }
        return result;
    }
    
    private void removeFromMainArrays(int position){
        for(int r = position; r < messageCount - 1; r++){
            messageIDs[r] = messageIDs[r +1];
            messageHashes[r] = messageHashes[r + 1];
            recipients[r] = recipients[r + 1];
            allMessages[r] = allMessages[r + 1];
            messageFlags[r] = messageFlags[r + 1];           
        }
        messageCount--;
        
        messageIDs[messageCount] = "";
        messageHashes[messageCount] = "";
        recipients[messageCount] = "";
        allMessages[messageCount] = "";
        messageFlags[messageCount] = "";
    }
    
    private void removeFromFlagArray(String deletedMessage){
        sentCount = removeFromArray(sentMessages, sentCount, deletedMessage);
        storedCount = removeFromArray(storedMessages, storedCount, deletedMessage);
        disregardedCount = removeFromArray(disregardedMessages, disregardedCount, deletedMessage);
    }
    
    private int removeFromArray(String[] array, int count, String deletedMessage){
        int position = -1;
                
        for(int r = 0; r < count; r++){
            if(array[r].equals(deletedMessage)){
                position = r;
            }
        }
        
        if(position >= 0) {
            for(int r = position; r < count - 1; r++){
                array[r] = array[r + 1];
            }
            count--;
            array[count] = "";
        }
        return count;
    }
    
    public String displayStoredSenderAndRecipient(){
        String result = "";
        String newLine = System.lineSeparator();
        for(int r = 0; r < messageCount; r++){
            if(messageFlags[r].equalsIgnoreCase("Stored")){
                if(!result.isEmpty()){
                    result += newLine;
                }
                result += "Sender: Developer:" + newLine + "Recipient: " + recipients[r];
            }
        }
        if (result.isEmpty()){
            result = "No Stored Messages Found.";
        }
        return result;
    }
    
    public String displayReport(){
        String report = "";
        String newLine = System.lineSeparator();
        for(int r = 0; r < messageCount; r++){
            if(messageFlags[r].equalsIgnoreCase("Sent")){
                if(!report.isEmpty()){
                    report += newLine + newLine;
                }
                report += "Message Hash: " + messageHashes[r] + newLine
                        + "Recipient: " + recipients[r] + newLine
                        + "Message: " + allMessages[r];
            }
        }
        if(report.isEmpty()){
            report = "No Sent Messages To Display.";
        }
        return report;
    }
    
    public String displayStoredReport(){
       String report = ""; 
       String newLine = System.lineSeparator();
       for(int r = 0; r < messageCount; r++){
           if(messageFlags[r].equalsIgnoreCase("Stored")){
               if(!report.isEmpty()){
                   report += newLine +newLine;
               }
               report += "Message ID: " + messageIDs[r] + newLine
                       + "Message Hash: " + messageHashes[r] + newLine
                       + "Recipient: " + recipients[r] + newLine
                       + "Message: " + allMessages[r];
           }
       }
       if(report.isEmpty()){
            report = "No Stored Messages To Display.";
        }
        return report;
    }
    
    public String readStoredMessagesFromFile(String fileName){
        String fileText = "";
        String result = "Stored Messages Loaded.";
        
        try{
            File file = new File(fileName);
            Scanner fileReader = new Scanner(file);
            
            while(fileReader.hasNextLine()){
                fileText += fileReader.nextLine();
            }
            fileReader.close();
            loadJsonText(fileText);
        }catch(FileNotFoundException e){
            result = "Stored Messages File Was Not Found.";
        }
        return result;
    }
    
    public String writeStoredMessagesToFile(String fileName){
        String result = "Stored Message Saved.";
        String newLine = System.lineSeparator();
        boolean firstStoredMessage = true;
        
        try{
            FileWriter writer = new FileWriter(fileName);
            writer.write("[");
            writer.write(newLine);
            
            for(int r = 0; r < messageCount; r++){
                if(messageFlags[r].equalsIgnoreCase("Stored")){
                    if(!firstStoredMessage){
                        writer.write(",");
                        writer.write(newLine);
                    }
                    writer.write("{"
                            + "\"MessageID\":\"" + cleanJsonValue(messageIDs[r]) + "\","                            
                            + "\"MessageHash\":\"" + cleanJsonValue(messageHashes[r]) + "\","                            
                            + "\"Recipient\":\"" + cleanJsonValue(recipients[r]) + "\","                            
                            + "\"Message\":\"" + cleanJsonValue(allMessages[r]) + "\""                            
                            + "}");
                    firstStoredMessage = false;
                }
            }
            writer.write(newLine);
            writer.write("]");
            writer.close();
        }catch(IOException e){
            result = "Stored Messages Could Not Be Saved.";
        }
        return result;
    }
    
    private void loadJsonText(String fileText){
        int start = fileText.indexOf("{");
        
        while(start >= 0){
            int end = fileText.indexOf("}", start);
            if(end >= 0){
                String jsonMessage = fileText.substring(start, end + 1);
                String messageID = getJsonValue(jsonMessage, "MessageID");
                String messageHash = getJsonValue(jsonMessage, "MessageHash");
                String recipient = getJsonValue(jsonMessage, "Recipient");
                String message = getJsonValue(jsonMessage, "Message");
                if(!messageIDExists(messageID)){
                    addMessage(messageID, messageHash, recipient, message, "Stored");
                }
                start = fileText.indexOf("{", end);
            }else{
                start = -1;
            }
        }
    }
    
    private String getJsonValue(String jsonMessage, String fieldName){
        char quote = '"';
        String searchText = "" + quote + fieldName + quote + ":" + quote;
        String value = "";
        int start = jsonMessage.indexOf(searchText);
        
        if(start >= 0){
            start += searchText.length();
            int end = jsonMessage.indexOf(quote, start);
            if(end >= 0){
                value = jsonMessage.substring(start, end);
            }
        }
        return value;
    }
    
    private String cleanJsonValue(String value){
        String cleanValue = "";
        
        for(int r = 0; r < value.length(); r++){
            char currentCharacter = value.charAt(r);
            
            if(currentCharacter == '"'){
               cleanValue += "'";
            }else if(currentCharacter == '\\'){
                cleanValue += "/";
            }else{
                cleanValue += currentCharacter;
            }
        }
        return cleanValue;
    }
    
    // Checks whether a message ID is already in the arrays
private boolean messageIDExists(String searchID){
    for(int r = 0; r < messageCount; r++){
        if(messageIDs[r].equals(searchID)){
            return true;
        }
    }
    return false;
}
    
}    