import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// External Sources:
// Used https://www.rgagnon.com/javadetails/java-0054.html to figure out how to append text to the end of a file
  /**
  */
public class TicketInformationScanner {
   /**
     * @throws FileNotFoundException from scanning input file.   
   */

// Returns: True if a valid Ticket was found. False if no valid ticket was found
   static String[] ReturnInfoForTicket(int ticketNumber, String customerUserName)
                                                   throws FileNotFoundException {     
     
      String fileName = "../Databases/tickets.txt";   
      
      // userName represents the Name of the customer that we are looking for
      String userName = customerUserName;
      
      // currentName represents the Name of a customer we are reading in the file
      String currentName = "";
      
      // currentTicketNumber represents the number of the current ticket we are looking for
      int currentTicketNumber = 1;
      String currentEmail = "";
      
      // an array containing all of the ticket information
      String[] aboutTicketArray = {"","","","","","","","","",""};
      
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for tickets belonging to " + userName);
   
   // We will have 10 lines per ticket 
   // Line 0: is blank
   // Line 1: is userName
   // Line 2: is usersStartAirport
   // Line 3: is usersDestinationAirport
   // Line 4: is UsersSeatChoice
   // Line 5: is UsersMultipleFlightChoice
   // Line 6: is originalticketPrice
   // Line 7: is finalticketPrice
   // Line 8: is UsersFlightTimeChoice
   // Line 9: is completeUserFlightDate
   
   // Every ticket will occur in incriments of 10. The file starts at line 0
    
   while (scanFile.hasNext()) {
   
   // We are at a blank line
   scanFile.nextLine();
   
   // We are at user name
   aboutTicketArray[1] = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentName + " is the name of the user being read");
   
   
   // We are at usersStartAirport now
   aboutTicketArray[2] = scanFile.nextLine();
   
   // We are at usersDestinationAirport now
   aboutTicketArray[3] = scanFile.nextLine();
   
   // We are at UsersSeatChoice now
   aboutTicketArray[4] = scanFile.nextLine();
   
   // We are at UsersMultipleFlightChoice
   aboutTicketArray[5] = scanFile.nextLine();
   
   // We are at originalticketPrice
   aboutTicketArray[6] = scanFile.nextLine();
   
   // We are at finalticketPrice 
   aboutTicketArray[7] = scanFile.nextLine();
   
   // We are at UsersFlightTimeChoice
   aboutTicketArray[8] = scanFile.nextLine();
   
   // We are at completeUserFlightDate
   aboutTicketArray[9] = scanFile.nextLine();
   
   // compare our CurrentName with the inputed customerUserName 
  if (customerUserName.equals(aboutTicketArray[1])) {
  
      // compare our current ticketNumber with the one we are looking for
      if (currentTicketNumber == ticketNumber) {
      System.out.println("DEBUG: We have found a matching userName and ticket Number");
      return aboutTicketArray;
 
      }
      // We found a ticket for our user but we did not find the right number for the ticket so
      // incriment our ticket number and search more
      currentTicketNumber++;
      continue;
      
  
  }
  
  // Transition to the next ticket in the Customers file    
   }
   
  // If we get here no valid ticket was identified 
  return null; 
   
   }
   
   
   
   
  
  
  // Returns: True if a new ticket was created and added to the ../Databases/tickets.txt file and false if no ticket was created 
  static boolean addNewTicket(String customerUserName,String usersStartAirport, String usersDestinationAirport,String UsersSeatChoice, 
                     String UsersMultipleFlightChoice, double originalticketPrice, double finalticketPrice,String UsersFlightTimeChoice,
                     String completeUserFlightDate) throws FileNotFoundException {     
     
      String fileName = "../Databases/tickets.txt";   
      
      // userName represents the Name of the customer that we are looking for
      String userName = customerUserName;
      
      // convert the ticket prices into strings for easy storage
      String orgTicketPrice = String.valueOf(originalticketPrice);
      String finTicketPrice = String.valueOf(finalticketPrice);
            
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for the bottom of the file");
   
   
// MAY WANT TO CHECK IF A TICKET ALREADY EXISTS FIRST  
  
  // Let's Begin writing to the end of the file
   
  try {
  
  // Add The customerUserName the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),customerUserName.getBytes(),StandardOpenOption.APPEND);
  
  // Add the usersStartAirport to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),usersStartAirport.getBytes(),StandardOpenOption.APPEND);
  
  // Add the usersDestinationAirport to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),usersDestinationAirport.getBytes(),StandardOpenOption.APPEND);
  
  // Add the UsersSeatChoice to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),UsersSeatChoice.getBytes(),StandardOpenOption.APPEND);
  
  // Add the UsersMultipleFlightChoice to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),UsersMultipleFlightChoice.getBytes(),StandardOpenOption.APPEND);
  
  // Add the originalticketPrice to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),orgTicketPrice.getBytes(),StandardOpenOption.APPEND);

  // Add the finalticketPrice to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),finTicketPrice.getBytes(),StandardOpenOption.APPEND);

  // Add the UsersFlightTimeChoice to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),UsersFlightTimeChoice.getBytes(),StandardOpenOption.APPEND);

  // Add the completeUserFlightDate to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),completeUserFlightDate.getBytes(),StandardOpenOption.APPEND);
  
  // Create a blank line to distinguish from the next ticket
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  
  System.out.println("Added Ticket for user" + userName + "To the " + fileName + " file");             
  
  // We know this user has been sucessfully added to the tickets database
  return true;
  
  }
  catch (IOException e) {
  System.out.println("An error occured while writing to" + fileName);
  return false;
  } 
   
   
   
   }
   
 //NEED A TICKET ALREADY EXIST FUNCTION THAT CHECKS IF A USER ALREADY HAS A TICKET WITH THE INPUTED VALUES
 // USED AS CHECK DURING THE WRITE A TICKET FUNCTION AS WELL 
 
 
 
 
 
 
 
  
   
 
// // Returns: True if the string inputed matches the user name or email of someone already in the customer database
// // Returns: False if the string inputed is unique and does not represent an already existing user or email found in the customer database
//    static boolean userNameAlreadyExists(String customerUserName,String customerEmail)
//                                                    throws FileNotFoundException {     
//      
//       String fileName = "../Databases/customers.txt";   
//       
//       // userName represents the Name of the customer that we are looking for
//       String userName = customerUserName;
//       // userEmail represents the email of the customer that we are looking for
//       String userEmail = customerEmail;
//       // currentName represents the Name of a customer we are reading in the file
//       String currentName = "";
//       String currentEmail = "";
//         
//       Scanner scanFile = new Scanner(new File(fileName));
//       
//       System.out.println("Scanning" + fileName + " for matching user name");
//    
//    
//    // We will have 4 lines per user 
//    // Line 1: is user name
//    // Line 2: is password
//    // Line 3: is email
//    // Line 4: is blank 
//    // Every user will occur in incriments of 3. The file starts at line 1
//     
//       while (scanFile.hasNext()) {
//    // We are at a blank line
//          scanFile.nextLine();
//    
//    // We are at user name
//          currentName = scanFile.nextLine();
//    
//    // We are at password now
//          scanFile.nextLine();
// 
//    // We are at email now
//          currentEmail = scanFile.nextLine();
//     
//    // compare our CurrentName or currentEmail with the inputed customerUserName or customerEmail
//          if (customerUserName.equals(currentName) || customerEmail.equals(currentEmail)) {
//  
//   // If we get here then this customer already exists and is not unique
//             return true;
//  
//             } 
//          }
//   
//   // Transition to the next user in the Customers file    
//    
//    
//   // If we get here no matching customer was identified 
//       return false; 
//    
//       }
// 
// 
// 
// // Returns: An array containin the users user name, password, and email address
// static String[] ReturnInfoOnCustomer(String customerUserName)
//                                                    throws FileNotFoundException {     
//      
//       String fileName = "../Databases/customers.txt";   
//       
//       // userName represents the Name of the customer that we are looking for
//       String userName = customerUserName;
//       
//       // currentName represents the Name of a customer we are reading in the file
//       String currentName = "";
//       String currentPassword = "";
//       String currentEmail = "";
//       
//       String []customerInfo = {"","",""};
//       
//       
//       Scanner scanFile = new Scanner(new File(fileName));
//       
//       System.out.println("Scanning" + fileName + " for users credentials");
//    
//    
//    // We will have 4 lines per user 
//    // Line 1: is user name
//    // Line 2: is password
//    // Line 3: is email
//    // Line 4: is blank 
//    // Every user will occur in incriments of 3. The file starts at line 1
//     
//    while (scanFile.hasNext()) {
//    // We are at a blank line
//    scanFile.nextLine();
//    
//    // We are at user name
//    currentName = scanFile.nextLine();
//    //System.out.println("DEBUG:" + currentName + " is the name of the user being read");
//    
//    
//    // We are at password now
//    currentPassword = scanFile.nextLine();
//    //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");
//    
//    // We are at email now
//    currentEmail = scanFile.nextLine();
//    System.out.println("DEBUG:" + currentEmail + " is the name of the email being read\n");
//    
//   
//    // compare our CurrentName with the inputed customerUserName 
//   if (customerUserName.equals(currentName)) {
//   
//   
//       customerInfo[0] = currentName;
//       customerInfo[1] = currentPassword;
//       customerInfo[2] = currentEmail;
//   
//       
//       return customerInfo;
//  
//       
//   
//   }
//   
//   // Transition to the next user in the Customers file    
//    }
//    
//   // If we get here no valid customer was identified 
//   return customerInfo; 
//    
//    }


}

