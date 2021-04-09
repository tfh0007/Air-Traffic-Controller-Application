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
import java.util.ArrayList;
import java.util.List;


// External Sources:
// Used https://www.rgagnon.com/javadetails/java-0054.html to figure out how to append text to the end of a file
  /**
  */
public class AirportInformationScanner {
   /**
     * @throws FileNotFoundException from scanning input file.
   */

// Returns: True if a valid customer was identified and false if no valid customer was identified
   static boolean lookForAirport(String airportName, String airportCode)
                                                   throws FileNotFoundException {

      String fileName = "../Databases/airports.txt";

      // userName represents the Name of the customer that we are looking for
      String name = airportName;

      // currentName represents the Name of a customer we are reading in the file
      String currentName = "";
      String currentCode = "";
      String currentCity = "";
      String currentLat = "";
      String currentLon = "";

      Scanner scanFile = new Scanner(new File(fileName));

      //System.out.println("Scanning" + fileName + " for users credentials");

   while (scanFile.hasNext()) {
   // We are at a blank line
   scanFile.nextLine();

   // We are at airport name
   currentName = scanFile.nextLine();
   System.out.println("DEBUG:" + currentName + " is the name of the airport being read");


   // We are at airport code now
   currentCode = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");

   // We are at city now
   currentCity = scanFile.nextLine();

   // Reading the X coordinate
   currentLat = scanFile.nextLine();

   // Reading the Y coordinate
   currentLon = scanFile.nextLine();

   // compare our CurrentName with the inputed customerUserName
  if (airportName.equals(currentName)) {

      if (airportCode.equals(currentCode)) {
      System.out.println("DEBUG: We have found a matching userName and password");
      return true;

      }

  }

  // Transition to the next user in the Customers file
   }

  // If we get here no valid airport was identified
  return false;

   }






//   // Returns: True if a new customer was created and added to the ../Databases/customers.txt file and false if no customer was created
//   static boolean addNewCustomer(String customerUserName, String customerPassword, String customerEmail)
//                                                    throws FileNotFoundException {
//
//       String fileName = "../Databases/customers.txt";
//
//       // userName represents the Name of the customer that we are looking for
//       String userName = customerUserName;
//       String userPassword = customerPassword;
//       String userEmail = customerEmail;
//
//
//       Scanner scanFile = new Scanner(new File(fileName));
//
//       System.out.println("Scanning" + fileName + " for the bottom of the file");
//
//
//    // Check if the same userName or email already exists
//    if (userNameAlreadyExists(userName,userEmail)) {
//
//       System.out.println("** Customer: " + userName + " or email address " + userEmail + " already exists **");
//       return false;
//    }
//
//
//   // Let's Begin writing to the end of the file
//
//   try {
//
//   // Add The user Name to the bottom
//   Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
//   Files.write(Paths.get(fileName),userName.getBytes(),StandardOpenOption.APPEND);
//
//   // Add the user Password to the bottom
//   Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
//   Files.write(Paths.get(fileName),userPassword.getBytes(),StandardOpenOption.APPEND);
//
//   // Add the user Email to the bottom
//   Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
//   Files.write(Paths.get(fileName),userEmail.getBytes(),StandardOpenOption.APPEND);
//
//   // Create a blank line to distinguish from the next user
//   Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
//
//   System.out.println("Added user:" + userName + "To the " + fileName + " file");
//
//   // We know this user has been sucessfully added to the customers database
//   return true;
//
//   }
//   catch (IOException e) {
//   System.out.println("An error occured while writing to" + fileName);
//   }
//
//
//   // If we get here then a new customer was not created
//   return false;
//
//    }
//
//
//
//
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



// Returns: An array containin the users user name, password, and email address
static String[] ReturnInfoOnAirport(String airportName)
                                                   throws FileNotFoundException {

      String fileName = "../Databases/airports.txt";

      // userName represents the Name of the customer that we are looking for
      String name = airportName;

      // currentName represents the Name of a customer we are reading in the file
      String currentName = "";
      String currentCode = "";
      String currentCity = "";
      String currentLat = "";
      String currentLon = "";

      String []airportInfo = {"","","","",""};


      Scanner scanFile = new Scanner(new File(fileName));

      System.out.println("Scanning" + fileName + " for airport details.");


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


   // We are at code now
   currentCode = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");

   // We are at city now
   currentCity = scanFile.nextLine();
   // System.out.println("DEBUG:" + currentEmail + " is the name of the email being read\n");

   // Reading x coords
   currentLat = scanFile.nextLine();

   // Reading y coords
   currentLon = scanFile.nextLine();

   // compare our CurrentName with the inputed customerUserName
  if (airportName.equals(currentName)) {


      airportInfo[0] = currentName;
      airportInfo[1] = currentCode;
      airportInfo[2] = currentCity;
      airportInfo[3] = currentLat;
      airportInfo[4] = currentLon;


      return airportInfo;


  }

  // Transition to the next user in the Customers file
   }

  // If we get here no valid customer was identified
  return airportInfo;

   }



// Returns: An array containing all of the airports contained in the airports.txt database
// The airports will start at index 1 since we do not want index zero to be set yet
// Index 0 will be "Select an airport"
static String[] GatherListOfAirports() throws FileNotFoundException {

      String fileName = "../Databases/airports.txt";


      // currentAirport represents the Name of the current airport we are reading in the file
      String currentAirport = "";
      // We will never know the needed size of our array so we have to make it a list first
      List<String> airportInfo = new ArrayList<String>();
      // We do not want the first value to be an airpor so initialize it to null
      airportInfo.add(null);
      

      Scanner scanFile = new Scanner(new File(fileName));

      System.out.println("Scanning" + fileName + " for airport details.");

// We will view one airport for every cycle of this while so set an index accordingly
   int airportIndex = 1;
   while (scanFile.hasNext()) {
   
   // We are at a blank line
   scanFile.nextLine();

   // We are at airport name
   currentAirport = scanFile.nextLine();
   //System.out.println("DEBUG:" + currentName + " is the name of the user being read");


   // We are at code now
   scanFile.nextLine();
   //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");

   // We are at city now
   scanFile.nextLine();
   // System.out.println("DEBUG:" + currentEmail + " is the name of the email being read\n");

   // Reading x coords
   scanFile.nextLine();

   // Reading y coords
   scanFile.nextLine();

   airportInfo.add(currentAirport);
   // We are moving to a new airport so incrimet the airport index
   
   }
// Convert our array list into an array so we can actually use it   
   String[] airportArray = new String[airportInfo.size()];
        airportArray = airportInfo.toArray(airportArray);

      return airportArray;


  }
  

// Returns: An array containing all of the airport locations contained in the airports.txt database
// The airport locations will start at index 1 since we do not want index zero to be set yet
// Index 0 will be used as a placeholder
static String[] GatherListOfAirportLocations() throws FileNotFoundException {

      String fileName = "../Databases/airports.txt";


      // currentLocation represents the Name of the current airport we are reading in the file
      String currentLocation = "";
      // We will never know the needed size of our array so we have to make it a list first
      List<String> airportLocationsInfo = new ArrayList<String>();
      // We do not want the first value to be an airport location so initialize it to null
      airportLocationsInfo.add(null);
      

      Scanner scanFile = new Scanner(new File(fileName));

      System.out.println("Scanning" + fileName + " for airport details.");

// We will view one airport for every cycle of this while so set an index accordingly
   int airportIndex = 1;
   while (scanFile.hasNext()) {
   
   // We are at a blank line
   scanFile.nextLine();

   // We are at airport name
   scanFile.nextLine();
   //System.out.println("DEBUG:" + currentName + " is the name of the user being read");


   // We are at code now
   scanFile.nextLine();
   //System.out.println("DEBUG:" + currentPassword + " is the name of the password being read");

   // We are at city now
   currentLocation = scanFile.nextLine();
   // System.out.println("DEBUG:" + currentEmail + " is the name of the email being read\n");

   // Reading x coords
   scanFile.nextLine();

   // Reading y coords
   scanFile.nextLine();

   airportLocationsInfo.add(currentLocation);
   // We are moving to a new airport so incrimet the airport index
   
   }
// Convert our array list into an array so we can actually use it   
   String[] airportLocationArray = new String[airportLocationsInfo.size()];
        airportLocationArray = airportLocationsInfo.toArray(airportLocationArray);

      return airportLocationArray;


  }


}