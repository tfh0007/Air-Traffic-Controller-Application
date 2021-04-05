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
public class CustomerInformationScanner {
   /**
     * @throws FileNotFoundException from scanning input file.   
   */

// Returns: True if a valid customer was identified and false if no valid customer was identified
   static boolean lookForCustomer(String customerUserName, String customerPassword)
                                                   throws FileNotFoundException {     
     
      String fileName = "customers.txt";   
      
      // userName represents the Name of the customer that we are looking for
      String userName = customerUserName;
      
      // currentName represents the Name of a customer we are reading in the file
      String currentName = "";
      String currentPassword = "";
      String currentEmail = "";
      
      
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for users credentials");
   
   
   // We will have 4 lines per user 
   // Line 1: is user name
   // Line 2: is password
   // Line 3: is email
   // Line 4: is blank 
   // Every user will occur in incriments of 3. The file starts at line 1
    
   while (scanFile.hasNext()) {
   // We are at a blank line
   scanFile.nextLine();
   
   // We are at user name
   currentName = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentName + " is the name of the user being read");
   
   
   // We are at password now
   currentPassword = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");
   
   // We are at email now
   currentEmail = scanFile.nextLine();
   System.out.println("DEBUG:" + currentEmail + " is the name of the email being read\n");
   
  
   // compare our CurrentName with the inputed customerUserName 
  if (customerUserName.equals(currentName)) {
  
      if (customerPassword.equals(currentPassword)) {
      System.out.println("DEBUG: We have found a matching userName and password");
      return true;
 
      }
  
  }
  
  // Transition to the next user in the Customers file    
   }
   
  // If we get here no valid customer was identified 
  return false; 
   
   }
   
   
   
   
  
  
  // Returns: True if a new customer was created and added to the customers.txt file and false if no customer was created 
  static boolean addNewCustomer(String customerUserName, String customerPassword, String customerEmail)
                                                   throws FileNotFoundException {     
     
      String fileName = "customers.txt";   
      
      // userName represents the Name of the customer that we are looking for
      String userName = customerUserName;
      String userPassword = customerPassword;
      String userEmail = customerEmail;
      
            
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for the bottom of the file");
   
   
   // Check if the same userName or email already exists
   if (userNameAlreadyExists(userName,userEmail)) {
   
      System.out.println("** Customer: " + userName + " or email address " + userEmail + " already exists **");
      return false;
   }
   
  
  // Let's Begin writing to the end of the file
   
  try {
  
  // Add The user Name to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),userName.getBytes(),StandardOpenOption.APPEND);
  
  // Add the user Password to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),userPassword.getBytes(),StandardOpenOption.APPEND);
  
  // Add the user Email to the bottom
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  Files.write(Paths.get(fileName),userEmail.getBytes(),StandardOpenOption.APPEND);
  
  // Create a blank line to distinguish from the next user
  Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
  
  System.out.println("Added user:" + userName + "To the " + fileName + " file");             
  
  // We know this user has been sucessfully added to the customers database
  return true;
  
  }
  catch (IOException e) {
  System.out.println("An error occured while writing to" + fileName);
  } 
   
   
  // If we get here then a new customer was not created 
  return false; 
   
   }
   
   
   
 
// Returns: True if the string inputed matches the user name or email of someone already in the customer database
// Returns: False if the string inputed is unique and does not represent an already existing user or email found in the customer database
   static boolean userNameAlreadyExists(String customerUserName,String customerEmail)
                                                   throws FileNotFoundException {     
     
      String fileName = "customers.txt";   
      
      // userName represents the Name of the customer that we are looking for
      String userName = customerUserName;
      // userEmail represents the email of the customer that we are looking for
      String userEmail = customerEmail;
      // currentName represents the Name of a customer we are reading in the file
      String currentName = "";
      String currentEmail = "";
        
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for matching user name");
   
   
   // We will have 4 lines per user 
   // Line 1: is user name
   // Line 2: is password
   // Line 3: is email
   // Line 4: is blank 
   // Every user will occur in incriments of 3. The file starts at line 1
    
      while (scanFile.hasNext()) {
   // We are at a blank line
         scanFile.nextLine();
   
   // We are at user name
         currentName = scanFile.nextLine();
   
   // We are at password now
         scanFile.nextLine();

   // We are at email now
         currentEmail = scanFile.nextLine();
    
   // compare our CurrentName or currentEmail with the inputed customerUserName or customerEmail
         if (customerUserName.equals(currentName) || customerEmail.equals(currentEmail)) {
 
  // If we get here then this customer already exists and is not unique
            return true;
 
            } 
         }
  
  // Transition to the next user in the Customers file    
   
   
  // If we get here no matching customer was identified 
      return false; 
   
      }



// Returns: An array containin the users user name, password, and email address
static String[] ReturnInfoOnCustomer(String customerUserName)
                                                   throws FileNotFoundException {     
     
      String fileName = "customers.txt";   
      
      // userName represents the Name of the customer that we are looking for
      String userName = customerUserName;
      
      // currentName represents the Name of a customer we are reading in the file
      String currentName = "";
      String currentPassword = "";
      String currentEmail = "";
      
      String []customerInfo = {"","",""};
      
      
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for users credentials");
   
   
   // We will have 4 lines per user 
   // Line 1: is user name
   // Line 2: is password
   // Line 3: is email
   // Line 4: is blank 
   // Every user will occur in incriments of 3. The file starts at line 1
    
   while (scanFile.hasNext()) {
   // We are at a blank line
   scanFile.nextLine();
   
   // We are at user name
   currentName = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentName + " is the name of the user being read");
   
   
   // We are at password now
   currentPassword = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");
   
   // We are at email now
   currentEmail = scanFile.nextLine();
   System.out.println("DEBUG:" + currentEmail + " is the name of the email being read\n");
   
  
   // compare our CurrentName with the inputed customerUserName 
  if (customerUserName.equals(currentName)) {
  
  
      customerInfo[0] = currentName;
      customerInfo[1] = currentPassword;
      customerInfo[2] = currentEmail;
  
      
      return customerInfo;
 
      
  
  }
  
  // Transition to the next user in the Customers file    
   }
   
  // If we get here no valid customer was identified 
  return customerInfo; 
   
   }


}

