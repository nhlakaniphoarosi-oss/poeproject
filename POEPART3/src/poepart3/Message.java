/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart3;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Student
 */
public class Message {
    
    private  String recipient;
    private  String message;
    private String messageID;
    private String messageHash;
    private int messageNumber;
    private int messageOption;
    
    
    
    private static int numMessageSent = 0;
    private static String storedMessages = "";
    private static String sentMessages = "";
    
    public Message (String recipient, String message, int messageNumber){
        this.message = message;
        this.recipient = recipient;
        this.messageNumber = messageNumber;
        this.messageID = createMessageID();
        this.messageHash = createMessageHash();
    }
    
    public int returnTotalMessages(){
        return numMessageSent;
    }
    
    public String createMessageID(){
    long randomId = (long) (Math.random() * 9000000000L) + 1000000000L;
    return String.valueOf(randomId);
}
    
    public boolean checkMessageID(){
        return messageID.length() <= 10;
    }
    
    // Checks the number starts with +27 and is 12 characters with digits only after it
    public boolean recipientCellValid(String cellphone) {
        if(cellphone.length() != 12){
            return false;
        }
        if(!cellphone.startsWith("+27")){
            return false;
        }
        for(int r = 3; r < cellphone.length(); ++r){
            if(!Character.isDigit(cellphone.charAt(r))){
               return false;
            }
        }
        return true;
   }
    
    public String checkRecipientCell(){
        if(recipientCellValid(recipient)){
            return "Cellphone number successfully captured";
        }
        return "Cellphone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }
    
    public boolean messageLengthValid(){
        return message.length() <= 250;
    }
    
 
    public String checkMessageLength(){
        if(messageLengthValid()){
            return "Message ready to send.";
        }
        
        int extraCharacters = message.length() - 250;
        return "Message exceeds 250 characters by " + extraCharacters + "; please reduce the size";
    }
    
    public String taskMessageLengthResult(){
        if(messageLengthValid()){
            return "Message sent";
        }
        return "please enter a message of less than 250 characters";
    }
    
    public String createMessageHash(){
        String cleanMessage = "";
        
        for(int r = 0; r < message.length(); r++){
            char currentCharacter = message.charAt(r);
                    
            if(Character.isLetterOrDigit(currentCharacter)        
                    || Character.isWhitespace(currentCharacter)){
               cleanMessage += currentCharacter;            
           }   
        }
        
        cleanMessage = cleanMessage.trim();
        
        while(cleanMessage.contains("  ")){
            cleanMessage = cleanMessage.replace(" ", " ");
        }
        
        if(cleanMessage.isEmpty()){
            return messageID.substring(0, 2) + ":" + messageNumber + ":";
        }
        
        String firstWord;
        String lastWord;
        int firstSpace = cleanMessage.indexOf(" ");
        int lastSpace = cleanMessage.lastIndexOf(" ");
        
        if(firstSpace == -1){
           firstWord = cleanMessage.toUpperCase();
           lastWord = cleanMessage.toUpperCase();
        }else{
            firstWord = cleanMessage.substring(0, firstSpace).toUpperCase();
            lastWord = cleanMessage.substring(lastSpace + 1).toUpperCase();   
        }
        return messageID.substring(0, 2) + ":" + messageNumber + ":" + firstWord + lastWord;
    }
    
    public void setMessageOption(int messageOption){
        this.messageOption = messageOption;
    }
    
    public String sentMessage() throws IOException{
        if(messageOption == 1){
            numMessageSent++;
            sentMessages += printSingleMessage() + "\n";
            
            return "Message successfully sent";
        }
        if(messageOption == 2){
            return "Press 0 to delete message";
        }
        if(messageOption == 3){
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid option.";
    }
    
     public String printMessages(){
         return sentMessages;
     }
     

    public void storeMessage() throws IOException{
        String jsonMessage = "{"
               +"\"MessageID\":\"" + messageID + "\","
               +"\"MessageHash\":\"" + messageHash + "\","        
               +"\"Recipient\":\"" + recipient + "\","        
               +"\"Message\":\"" + message + "\""        
               + "}";        
               
        if(storedMessages.isEmpty()){
            storedMessages = jsonMessage;
        }else{
            storedMessages += ",\n" + jsonMessage;
        }    
        
        try{
            FileWriter writer = new FileWriter("storedMessages.json");
            writer.write("[\n");
            writer.write(storedMessages);
            writer.write("\n]");
            writer.close();
        }catch(IOException e){
            System.out.println("Error storing message.");
        }
    }    
    
    public String printSingleMessage(){
       return "MessageID: " + messageID 
               +"\nMessage Hash: " + messageHash
               +"\nRecipient: " + recipient
               +"\nMessage: " + message;
    }
    
    public String getMessageID(){
        return messageID;
    }
    
    public String messageIDMessage(){
        return "Message ID generated: " + messageID;
    }
    
    public String getMessageHash(){
        return messageHash;
    }
    
    public void setMessageID(String messageID){
        this.messageID = messageID;
        this.messageHash = createMessageHash();
    }
    
    public static void resetMessages(){
        numMessageSent = 0;
        sentMessages = "";
        storedMessages = "";
    }

    public boolean recipientCellValid() {
        return recipientCellValid(recipient);
    }    
    
    public String getRecipient() {
       return recipient;
    }

    public String getMessage() {
       return message;
    }

    public String getMessageFlag() {
      if (messageOption == 1) {
        return "Sent";
      } else if (messageOption == 2) {
        return "Disregard";
      } else if (messageOption == 3) {
        return "Stored";
      }
       return "";
    }
}
