import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FlightInformationScanner {
  /**
   * Search for the flight to make sure it exists.
   *
   * @throws FileNotFoundException for scanning the input file.
   * @param flightNumber string uniquely associated with a certain flight.
   * @return returns true if the flight exists, false otherwise. 
   */
   static boolean lookForFlight(String flightNumber, String departingAirportCode,
                               String arrivingAirportCode, String occupancy) 
                               throws FileNotFoundException {
   
      String fileName = "../Databases/flights.txt";
      
      String currentFlightNumber = "";
      String currentDepartingAirportCode = "";
      String currentArrivingAirportCode = "";
      String currentOccupancy = "";
      
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for flight credentials");
   
   
   // We will have 5 lines per user 
   // Line 1: is flight number
   // Line 2: is departing airport code
   // Line 3: is arriving airport code
   // Line 4: is occupancy
   // Line 5: is blank 
   // Every user will occur in incriments of 4. The file starts at line 1
    
      while (scanFile.hasNext()) {
      // We are at a blank line
         scanFile.nextLine();
      
      // We are at flight number
         currentFlightNumber = scanFile.nextLine();
      //System.out.println("DEBUG:" + currentFlightNumber + " is the name of the flight number being read");
      
      
      // We are at departing airport code now
         currentDepartingAirportCode = scanFile.nextLine();
      //System.out.println("DEBUG:" + currentDepartingAirportCode + " is the name of the airport code being read");
      
      // We are at arriving airport code now
         currentArrivingAirportCode = scanFile.nextLine();
         System.out.println("DEBUG:" + currentArrivingAirportCode + " is the name of the airport code being read\n");
      
      
      // compare our currentFlightNumber with the inputed flightNumber. 
         if (flightNumber.equals(currentFlightNumber)) {
         
            if (departingAirportCode.equals(currentDepartingAirportCode) && arrivingAirportCode.equals(currentArrivingAirportCode)) {
               System.out.println("DEBUG: We have found a matching flight");
               return true;
            
            }
         
         }
      
      // Transition to the next user in the flight file    
      }
   
   // If we get here no valid flight was identified
   
      return false;
   }
   
  /**
   * Add a new flight to the database. 
   *
   * @throws FileNotFoundException for scanning the input file. 
   * @param flightNumber string uniquely associated with a certain flight.
   * @param departingAirportCode unique code for the 
   *        airport the flight is leaving from.
   * @param arrivingAirportCode unique code for the 
   *        airport that the flight is going to. 
   * @param occupancy int that holds the total occupancy of the flight.
   * @return returns true if a new flight was added to the database, 
   *         false otherwise. 
   */
   static boolean addNewFlight(String flightNumber, String departingAirportCode,
                               String arrivingAirportCode, String occupancy) 
                               throws FileNotFoundException {
      String fileName = "../Databases/flights.txt";
        
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for the bottom of the file");
      
      if (flightAlreadyExists(flightNumber, departingAirportCode, arrivingAirportCode)) {
      
         System.out.println("** Flight: " + flightNumber + " already exists **");
         return false;
      }
      
      // Let's Begin writing to the end of the file
   
      try {
      
      // Add The flight number to the bottom
         Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
         Files.write(Paths.get(fileName),flightNumber.getBytes(),StandardOpenOption.APPEND);
      
      // Add the departing airport code to the bottom
         Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
         Files.write(Paths.get(fileName),departingAirportCode.getBytes(),StandardOpenOption.APPEND);
      
      // Add the arriving airport code to the bottom
         Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
         Files.write(Paths.get(fileName),arrivingAirportCode.getBytes(),StandardOpenOption.APPEND);
         
      // Add the occupancy to the bottom
         Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
         Files.write(Paths.get(fileName),occupancy.getBytes(),StandardOpenOption.APPEND);
      
      // Create a blank line to distinguish from the next user
         Files.write(Paths.get(fileName),"\n".getBytes(),StandardOpenOption.APPEND);
      
         System.out.println("Added flight:" + flightNumber + "To the " + fileName + " file");             
      
      // We know this user has been sucessfully added to the customers database
         return true;
      
      }
      catch (IOException e) {
         System.out.println("An error occured while writing to" + fileName);
      }
      
      // If we get here then a new flight was not created 
      return false;                          
   }
   
   /**
   * Checks to see if the inputted information is already in the database.  
   *
   * @throws FileNotFoundException for scanning the input file. 
   * @param flightNumber string uniquely associated with a certain flight.
   * @param departingAirportCode unique code for the 
   *        airport the flight is leaving from.
   * @param arrivingAirportCode unique code for the 
   *        airport that the flight is going to. 
   * @return returns true if the flight already exists in the database, 
   *         false if it does not.  
   */
   static boolean flightAlreadyExists(String flightNumber, String departingAirportCode, 
                                      String arrivingAirportCode) 
                                      throws FileNotFoundException {
                                      
      String fileName = "../Databases/flights.txt";
      
      String currentFlightNumber = "";
        
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for matching flight number.");
   
      // We will have 5 lines per user 
      // Line 1: is flight number
      // Line 2: is departing airport code
      // Line 3: is arriving airport code
      // Line 4: is occupancy
      // Line 5: is blank 
      // Every user will occur in incriments of 4. The file starts at line 1
      
      while (scanFile.hasNext()) {
      // We are at a blank line
         scanFile.nextLine();
      
      // We are at flight number
         currentFlightNumber = scanFile.nextLine();
      
      // We are at departing airport code now
         scanFile.nextLine();
      
      // We are at arriving airport code now
         scanFile.nextLine();
         
      // We are at occupancy now
         scanFile.nextLine();
      
      // compare our CurrentName or currentEmail with the inputed customerUserName or customerEmail
         if (flightNumber.equals(currentFlightNumber)) {
         
         // If we get here then this flight already exists and is not unique
            return true;
         
         } 
      }
      
      // No matching flight was found. 
      return false;
   }
   
   /**
    * Adds information on a particular flight to an array. 
    *
    * @throws FileNotFoundException for scanning the input file.
    * @param flightNumber string uniquely associated with a certain flight.
    * @return returns a string array containing the flight number, departing
              airport code, arriving airport code, and occupancy. 
    */
   static String[] returnInfoOnFlight(String flightNumber) throws FileNotFoundException {
   
      String fileName = "../Databases/flights.txt";
      
      String currentFlightNumber = "";
      String currentDepartingAirportCode = "";
      String currentArrivingAirportCode = "";
      String currentOccupancy = "";
      
      String[] flightInfo = {"", "", "", ""};
      
      Scanner scanFile = new Scanner(new File(fileName));
      
      System.out.println("Scanning" + fileName + " for flight credentials");
      
      // We will have 5 lines per user 
      // Line 1: is flight number
      // Line 2: is departing airport code
      // Line 3: is arriving airport code
      // Line 4: is occupancy
      // Line 5: is blank 
      // Every user will occur in incriments of 4. The file starts at line 1
      
      while (scanFile.hasNext()) {
      // We are at a blank line
         scanFile.nextLine();
      
      // We are at flight number
         currentFlightNumber = scanFile.nextLine();
      
      // We are at departing airport code now
         currentDepartingAirportCode = scanFile.nextLine();
      
      // We are at arriving airport code now
         currentArrivingAirportCode = scanFile.nextLine();
         
      // We are at occupancy now
         currentOccupancy = scanFile.nextLine();
      
      // compare our flightNumber with the current flight number. 
         if (flightNumber.equals(currentFlightNumber)) {
            
            flightInfo[0] = currentFlightNumber;
            flightInfo[1] = currentDepartingAirportCode;
            flightInfo[2] = currentArrivingAirportCode;
            flightInfo[3] = currentOccupancy;
            return flightInfo;
         
         } 
      }
      // No valid flight was found. 
      return flightInfo;
   }
   
}