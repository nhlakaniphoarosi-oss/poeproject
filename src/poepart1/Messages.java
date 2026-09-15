/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poepart1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Messages {
    
    ArrayList<String> messageIds = new ArrayList<>();
    ArrayList<String> recipientNumber = new ArrayList<>();
    ArrayList<String> messages = new ArrayList<>();
    ArrayList<String> msgHash = new ArrayList<>();
    ArrayList<String> messageStatus = new ArrayList<>();
    
    
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    
    int numMessageSent = 0;
    
    public void sendingMessages(){
        
        System.out.println("How many messages do you want to send?");
        int numberOfMessages = input.nextInt();
        
        input.nextLine();
        for(int r = 1; r <= numberOfMessages; r++){
            
            System.out.println("MESSAGE: " + r);
            
            String messageID = generateMessageID();
            
            System.out.println("Enter recipient cell number!!");
            String recipient = input.nextLine();
            
            String recipientResult = checkRecipientCell(recipient);
            
            if(!recipientResult.equals("Valid number")){
                System.out.println(recipientResult);
                r = r - 1;
                continue;
                
            }
            recipientNumber.add(recipient);
            
            System.out.println("Enter your message!!");
            String messageText = input.nextLine();
            
            if(messageText.length() > 250 || messageText.trim().isEmpty()){
                
                System.out.println("Your messsage length is to long");
                r = r - 1;
                continue;
                
            }
            
            messages.add(messageText);
            
            String messageHash = createMessageHash(messageID, messages.size(), messageText);
            msgHash.add(messageHash);
            
            String status = SentMessage();
            
            messageStatus.add(status);
            
            if(status.equals("Sent")){
                numMessageSent++;
                
            }
            
        }
        
        printMessages();
        
        System.out.println("Total messages sent: " + returnTotalMessages());
        
        
    }
    
    public int returnTotalMessages(){
        return numMessageSent;
    }
    
    public String generateMessageID(){
        
        String messageID;
        
        do{
            long number = 1000000000L + random.nextLong(900000000L);  
            
            messageID = String.valueOf(number);
            
        }while(messageIds.contains(messageID));
        
        messageIds.add(messageID);
        
        return messageID;
    }
    
    public String checkRecipientCell(String cellphone) { // Start of cellphone method
        
    
        if(cellphone.matches(".*\\+27[0-9]{9}$") 
                || cellphone.matches(".*^0[0-9]{9}")){
        
           return "Valid number";
        } else {
            
            return "Invalid number";
        }    
        
            
    }
    public String createMessageHash(String messageID, int messageNumber, String messageText){
        
        String firstTwoNumbers = messageID.substring(0, 2);
        String trimmedMessage = messageText.trim();
        String[] words = trimmedMessage.split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = firstTwoNumbers + ":" + messageNumber + ":" + firstWord + lastWord; 
        
        return hash.toUpperCase();
    }
    
    public String SentMessage(){
        
        System.out.println("\nSelect what you want to do with this message!");
        
        System.out.println("1. Send Message");
        System.out.println("2. Disregard Message");
        System.out.println("3. Store message to send later");
        
        System.out.println("Enter your selection: ");
        int selection = input.nextInt();
        
        switch(selection){
            
            case 1 -> {
                System.out.println("Message successfully sent");
                return "Sent";
            }
            
            case 2 -> {
                System.out.println("Press 0 to delete the message");
                int deleteChoice = input.nextInt();
                
                input.nextLine();
                
                if(deleteChoice == 0){
                    System.out.println("Message deleted");
                    
                    int lastIndex = messages.size() - 1 ;
                    
                    messageIds.remove(lastIndex);
                    recipientNumber.remove(lastIndex);
                    messages.remove(lastIndex);
                    msgHash.remove(lastIndex);
                    
                    return "Disregarded";
                }else{
                    System.out.println("Invalid option!");
                    return "Disregarded";
                    
                }
            }
            
            case 3 -> {
                System.out.println("Message successfully stored");
                return "Stored";
            }
            
            default -> {
                System.out.println("Invalid option");
                return "Disregarded";
            }
        }
    }
    
    public String printMessages(){
        
        StringBuilder allMessages = new StringBuilder();
        
        System.out.println("\n===== All Messages =====");
        
        for(int r = 0; r < messages.size(); r++){
            allMessages.append("\nMessage Number: ").append(r + 1);
            allMessages.append("\nMessage ID: ").append(messageIds.get(r));
            allMessages.append("\nRecipient: ").append(recipientNumber.get(r));
            allMessages.append("\nMessage: ").append(messages.get(r));
            allMessages.append("\nMessage Hash: ").append(msgHash.get(r));
            allMessages.append("\nStatus: ").append(messageStatus.get(r));
            
            System.out.println("\n=====================");
            
            System.out.println("\nMessage Number: " + (r + 1));
            System.out.println("Message ID: " + messageIds.get(r));
            System.out.println("Recipient: " + recipientNumber.get(r));
            System.out.println("Message: " + messages.get(r));
            System.out.println("Message Hash: " + msgHash.get(r));
            System.out.println("Status: " + messageStatus.get(r));
           
        }
        
        return allMessages.toString();
    }
}


