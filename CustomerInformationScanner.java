import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
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
   System.out.println("DEBUG:" + currentName + " is the name of the user being read");
   
   
   // We are at password now
   currentPassword = scanFile.nextLine();
   System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");
   
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
   
   


}

