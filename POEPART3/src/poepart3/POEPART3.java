/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poepart3;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Student
 */
public class POEPART3{
    
public static void main(String[] args) throws IOException {
        String firstName = "";
        String lastName = "";
        String password = "";
        String username = "";
        String cellphoneNumber = "";
        boolean correctFirstName = false;
        boolean correctLastName = false;
        boolean correctUserName = false;
        boolean correctPassword = false;
        boolean correctCellphoneNumber = false;
        boolean loginStatus = false;
        
        Scanner sa = new Scanner(System.in);
        
        while(!correctFirstName){
            System.out.println("Please enter your first name: ");
            firstName = sa.nextLine();
            
            System.out.println(nameMessage(firstName));
            correctFirstName = checkName(firstName);
        }
        
        while(!correctLastName){
            System.out.println("Please enter your last name: ");
            lastName = sa.nextLine();
            
            System.out.println(nameMessage(lastName));
            correctLastName = checkName(lastName);
        }
        
        while(!correctUserName){
            System.out.println("Please enter your username: ");
            username = sa.nextLine();
            
            System.out.println(usernameMessage(username));
            correctUserName = checkUserName(username);
        }
        
        while(!correctPassword){
            System.out.println("Please create your password: ");
            password = sa.nextLine();
            
            System.out.println(passwordMessage(password));
            correctPassword = checkPasswordComplexity(password);
        }
        
        while(!correctCellphoneNumber){
            System.out.println("Please enter your cell number: ");
            cellphoneNumber = sa.nextLine();
            
            System.out.println(cellPhoneMessage(cellphoneNumber));
            correctCellphoneNumber = checkCellPhoneNumber(cellphoneNumber);
        }
        
        String registrationStatus = registerUser(firstName, lastName, username, password, cellphoneNumber);
        System.out.println(registrationStatus);   
    
       
        System.out.println("\n===== Login =====");
        int loginAttempts = 0; 
        while(!loginStatus && loginAttempts < 3){
           System.out.println("Please enter the username to login: ");
           String loginUsername = sa.nextLine(); 
        
           System.out.println("Enter password to login");
           String loginPassword = sa.nextLine();
           
           loginStatus = loginUser(loginUsername, loginPassword); 
           loginAttempts++;
           
           if(!loginStatus){
               System.out.println("Username or password incorrect, please try again.");
               System.out.println("Login attempt " + loginAttempts + " of 3 failed.");
           }
        }
           if(loginStatus){
            System.out.println(returnLoginStatus(loginStatus));

            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Username: " + username);
            System.out.println("PassWord: " + password);
            System.out.println("Cell Number: " + cellphoneNumber);

            openQuickChat(sa);
            }else{
                System.out.println("Too many failed login attempts. Access denied.");
        }
        
    }    
    
    private static String registeredUsername = "";
    private static String registeredPassword = "";
    private static String registeredFirstName = "";
    private static String registeredLastName = "";
    
    public static boolean checkName(String name) {
        return name != null && name.trim().matches("[A-Za-z][A-Za-z '\\-]*");
    }
    
    public static String nameMessage(String name) {
        return checkName(name)
                ? "Name successfully captured."
                : "Name is not valid. Please use letters only and do not leave it empty.";
    }
    
     public static boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }
     
     public static String usernameMessage(String username) {
        return checkUserName(username)
                ? "Username successfully captured."
                : "Username is not correctly formatted, please ensure that your username "
                  + "contains an underscore and is no more than five characters in length.";
    }
     
     public static boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }
     
     
    public static String passwordMessage(String password) {
        return checkPasswordComplexity(password)
                ? "Password successfully captured."
                : "Password is not correctly formatted, please ensure that the password contains "
                  + "at least eight characters, a capital letter, a number and a special character.";
    }
    
    public static boolean checkCellPhoneNumber(String cell) {
        return cell != null && cell.matches("^\\+\\d{1,3}\\d{1,10}$");
    }
 
    public static String cellPhoneMessage(String cell) {
        return checkCellPhoneNumber(cell)
                ? "Cell number successfully added."
                : "Cell number is incorrectly formatted or does not contain an international code, "
                  + "please correct the number and try again.";
    }
    
    public static String registerUser(String first, String last, String username,
                                      String password, String cell) {
        if (!checkName(first) || !checkName(last)) {
            return "Registration failed: invalid name.";
        }
        if (!checkUserName(username)) {
            return "Registration failed: " + usernameMessage(username);
        }
        if (!checkPasswordComplexity(password)) {
            return "Registration failed: " + passwordMessage(password);
        }
        if (!checkCellPhoneNumber(cell)) {
            return "Registration failed: " + cellPhoneMessage(cell);
        }
        registeredFirstName = first;
        registeredLastName = last;
        registeredUsername = username;
        registeredPassword = password;
        return "User has been registered successfully.";
    }
 
    public static boolean loginUser(String username, String password) {
        return !registeredUsername.isEmpty()
                && registeredUsername.equals(username)
                && registeredPassword.equals(password);
    }
 
    public static String returnLoginStatus(boolean loginStatus) {
        return loginStatus
                ? "Welcome " + registeredFirstName + " " + registeredLastName
                  + ", it is great to see you again."
                : "Username or password incorrect, please try again.";
    }
        
   private static void openQuickChat(Scanner sa) throws IOException {
        System.out.println("Welcome to QuickChat.");
        
        MessageReport messageReport = new MessageReport();
        String loadStatus = messageReport.readStoredMessagesFromFile("storedMessages.json");
        System.out.println(loadStatus);

        String loadedMessages = messageReport.displayStoredMessages();
        if(!loadedMessages.isEmpty()){
        System.out.println("Stored messages loaded from file:");
        System.out.println(loadedMessages);
    }
        
        int option = 0;
        int numMessages = 0;
        int messagesEntered = 0;
        String sentMessagesText = "";
        
        while(option != 3){
            option = getMenuOption(sa);
        
        
            if(option == 1) {
                if(numMessages == 0){
                    numMessages = getNumberOfMessages(sa);
                }   if(messagesEntered >= numMessages){
                    System.out.println("You have entered all the messages.");
                }else{
                    for(int r = messagesEntered; r < numMessages; r++){
                        Message newMessage = createMessage(sa, r);
                        
                        boolean messageFinished = finishMessage(sa, newMessage);
                        
                        if(messageFinished){
                            addMessageToReport(messageReport, newMessage);
                            sentMessagesText = newMessage.printMessages();
                            messagesEntered++;
                        }else{
                            r--;
                        }
                    }
                    
            
                }      
            }else if(option == 2){
                showSentMessages(sentMessagesText);
            }else if(option == 3){
                System.out.println("Goodbye.");
            }else if(option == 4){
                openStoredMessages(sa, messageReport);
            }else{
                System.out.println("Invalid Option.");
            }    
        }    
    }        
    
    private static int getMenuOption(Scanner sa){
         System.out.println("\n==== Select any of the option below =====");
         System.out.println("1) Send message");
         System.out.println("2) Show recently sent messages");
         System.out.println("3) Quit"); 
         System.out.println("4) Stored Messages");
         
    
         String menuInput = sa.nextLine();
         int option = 0;
    
        if(isNumber(menuInput)){
        option = Integer.parseInt(menuInput);
    }
    return option;
}
    
    private static int getNumberOfMessages(Scanner sa){
    System.out.println("Enter the number of messages you would like to send");
    String numMessagesInput = sa.nextLine();
    boolean validNumberOfMessages = false;
    int numMessages = 0;

    while(!validNumberOfMessages){
        if(isNumber(numMessagesInput)){
            numMessages = Integer.parseInt(numMessagesInput);

            if(numMessages > 0){
                validNumberOfMessages = true;
            }else{
                System.out.println("Please enter a valid number of messages:");
                numMessagesInput = sa.nextLine();
            }
        }else{
            System.out.println("Please enter a valid number of messages:");
            numMessagesInput = sa.nextLine();
        }
    }

    return numMessages;
}

    private static Message createMessage(Scanner sa, int messageNumber){
        System.out.println("Enter recipient cell number: ");
        String recipient = sa.nextLine();
        
        System.out.println("Enter your message: ");
        String messageText = sa.nextLine();
        
        return new Message(recipient, messageText, messageNumber);
    }
    
    private static boolean finishMessage(Scanner sa, Message newMessage) throws IOException{
    boolean messageFinished = false;

    if(!newMessage.checkMessageID()){
        System.out.println("Message ID is not correctly generated.");
    }else{
        System.out.println(newMessage.messageIDMessage());
        System.out.println(newMessage.checkRecipientCell());

        if(newMessage.recipientCellValid()){
            if(!newMessage.messageLengthValid()){
                System.out.println(newMessage.taskMessageLengthResult());
            }else{
                System.out.println(newMessage.taskMessageLengthResult());
                System.out.println("Message Hash: " + newMessage.getMessageHash());

                int messageOption = getMessageOption(sa);
                newMessage.setMessageOption(messageOption);

                System.out.println(newMessage.sentMessage());
                messageFinished = true;
            }
        }
    }

    return messageFinished;
}       
    
    private static int getMessageOption(Scanner sa){
       System.out.println("\n==== Select any of the option below =====");
            System.out.println("1. Send message");
            System.out.println("2. Disregard Message");
            System.out.println("3. Store Message to send later"); 
            
            String messageOptionInput = sa.nextLine();
            boolean validMessageOption = false;
            int messageOption = 0;
            
            while(!validMessageOption){
                if(isNumber(messageOptionInput)){
                    messageOption = Integer.parseInt(messageOptionInput);
                    
                    if(messageOption >= 1){
                        if(messageOption <= 3){
                            validMessageOption = true;
                        }else{
                            System.out.println("Please choose a valid message option: ");
                            messageOptionInput = sa.nextLine();
                        }
                    }else{
                        System.out.println("Please choose a valid message option: ");
                        messageOptionInput = sa.nextLine();
                    }
                }else{
                    System.out.println("Please choose a valid message option: ");
                    messageOptionInput = sa.nextLine();
                }
            }
              return messageOption;    
    }       

    private static void showSentMessages(String sentMessagesText){
        if(sentMessagesText.isEmpty()){
            System.out.println("No messages have been sent yet.");
        }else{
            System.out.println(sentMessagesText);
        }
            
    }
    
    private static void addMessageToReport(MessageReport messageReport, Message newMessage){
        messageReport.addMessage(newMessage.getMessageID(),
               newMessage.getMessageHash(),
               newMessage.getRecipient(),
               newMessage.getMessage(),
               newMessage.getMessageFlag());
    }
    
    private static void openStoredMessages(Scanner sa, MessageReport messageReport){
        int storedOption = 0;
        
        while(storedOption != 7){
            storedOption =getStoredMessagesOption(sa);
            
            if(storedOption == 1){
                System.out.println(messageReport.displayStoredSenderAndRecipient());
            }else if(storedOption == 2){
                System.out.println(messageReport.displayLongestStoredMessage());
            }else if(storedOption == 3){
                System.out.println("Enter The Message ID:");
                String searchID = sa.nextLine();
                System.out.println(messageReport.searchByMessageIDDetails(searchID));
            }else if(storedOption == 4){
                System.out.println("Enter the Recipient Number:");
                String searchRecipient = sa.nextLine();
                System.out.println(messageReport.searchByRecipient(searchRecipient));
            }else if(storedOption == 5){
                System.out.println("Enter The Message Hash:");
                String searchHash = sa.nextLine();
                String deleteStatus = messageReport.deleteByMessageHash(searchHash);
                
                if(!deleteStatus.equals("Message Hash Not Found.")){
                    String saveStatus = messageReport.writeStoredMessagesToFile("storedMessages.json");
                    
                    if(!saveStatus.equals("Stored Messages Saved.")){
                        System.out.println(saveStatus);
                    }
                }
                System.out.println(deleteStatus);
            }else if(storedOption == 6){
                System.out.println(messageReport.displayStoredReport());
            }else if(storedOption == 7){
                System.out.println("Returning To Main Menu.");
            }else{
                System.out.println("Invalid Option.");
            }
        }    
    }    
    
    private static int getStoredMessagesOption(Scanner sa){
        System.out.println("Stored Messages");
        System.out.println("1) Display Stored Message Sender and Recipient");
        System.out.println("2) Display The Longest Stored Message");
        System.out.println("3) Search For a Message ID");
        System.out.println("4) Search Messages For A Recipient");
        System.out.println("5) Delete A Message Using A Message Hash");
        System.out.println("6) Display Stored Message Report");
        System.out.println("7) Return To Main Menu");
        
         String input = sa.nextLine().trim();      // always read the input first

    while (!isNumber(input) || Integer.parseInt(input) < 1 || Integer.parseInt(input) > 7) {
        System.out.println("Invalid option!");
        input = sa.nextLine().trim();
    }
    return Integer.parseInt(input);
}

    
    private static boolean isNumber (String input){
        boolean numberStatus = true;
        
        if(input.isEmpty()){
            numberStatus = false;
        }else{
            for(int r = 0; r < input.length(); r++){
                if(!Character.isDigit(input.charAt(r))){
                    numberStatus = false;
                }
            }
        }
        return numberStatus;
    }

    
}    
    

    

