import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

//External Sources:
// Used https://www.baeldung.com/java-string-to-date 
// as well as https://www.javatpoint.com/java-get-current-date
//to find out how to get the current date and compare if with another date based on user input


public class HelperMethodsForScheduleAFlightInterface {


      // This function will provide a neat graphical effect when the old ticket price
      // is not the same as the new ticket price
      
      public static void visualTicketChange(JLabel label, int oldTicketPrice, int newTicketPrice) {
         
         // When our ticket price increased use red text and increase the value
         
            // These will show the cent values on the screen
               int centD = 0;
               int centS = 0;
                    

         if (oldTicketPrice < newTicketPrice) {
         
            // Change the color to red to show higher cost   
            label.setForeground (Color.red);
            

            for (int i=oldTicketPrice; i<newTicketPrice+1; i++) {

                  
            // We do not want to update cent values when the numbers move to slowly
               if (newTicketPrice-i < 4)
               {
                  label.setText("$" + i + ".00");
               }
               else {
            
               // We need to ad a comma when the price is over 999 (NOT YET DONE)   
               label.setText("$" + i + "." + centD + centS);
               }
               
               try {
               
               // Make the ticket price move fast or slow depending on the difference in value between our current
               // ticket price (i) and the new ticket price
               
               if (newTicketPrice-i > 500)
                  Thread.sleep(1);
                  
               else if (newTicketPrice-i > 250)
                  Thread.sleep(3);
                  
               else if (newTicketPrice-i > 50)
                  Thread.sleep(6);
               else if (newTicketPrice-i > 3)
                  Thread.sleep(12);
               else 
                  Thread.sleep(100);
               
               }
               catch (InterruptedException f) {
                  System.out.println("An error occured while thread tried to sleep");
               }
               // Incriment the cent values 
               centD++;
               centS++;
               // We can not have 1 digit over 9 so reset the values when either is 9 or more
               if (centD >= 9)  {
                  centD = 0;
               }
               if (centS >= 9) {
                  centS = 0;
               }

            
            }  
            label.setForeground (Color.gray);
                 
         
         }
         
            // When our ticket price decreased use green text and increase the value
         else if (oldTicketPrice > newTicketPrice) {
         
            // Change the color to green to show lower cost   
            label.setForeground (Color.green);

            // These will show the cent values on the screen
            
            // Cent prices shoud decrease now since the cost is going down
            
            

            for (int i=oldTicketPrice; i>newTicketPrice-1; i--) {
            
            // We do not want to update cent values when the numbers move to slowly
               if (i-newTicketPrice < 4)
               {
                  label.setText("$" + i + ".00");
               }
               else {
            
               // We need to ad a comma when the price is over 999 (NOT YET DONE)   
               label.setText("$" + i + "." + centD + centS);
               }
               
               try {
               // Make the ticket price move fast or slow depending on the difference in value between our new
               // ticket price and the current ticket price(i) (new ticket price is smaller)
               
               if (i-newTicketPrice > 500)
                  Thread.sleep(1);
                  
               else if (i-newTicketPrice > 250)
                  Thread.sleep(3);
                  
               else if (i-newTicketPrice > 50)
                  Thread.sleep(6);
               else if (i-newTicketPrice > 3)
                  Thread.sleep(12);
               else 
                  Thread.sleep(100);
               
               }
               catch (InterruptedException f) {
               System.out.println("An error occured while thread tried to sleep");
               }
              
              // Decrement the cent values 
               centD--;
               centS--;
               // We can not have 1 digit under 0 so reset the values when either is 0 or less
               if (centD <= 0)  {
                  centD=9;
               }
               if (centS <= 0) {
                  centS = 9;
               }
               
               
            }  
            label.setForeground (Color.gray);

         }
         // Reset the value to the newTicketPrice Incase a bug happens

         label.setText("$" + newTicketPrice + ".00");

      
      }

   //MAKE A SET TICKET PRICE FUNCTION THAT CHECKS ALL OF THE MENU SELECTED OPTIONS AND THEN KNOWS THE APPROPRIATE TICKET PRICE

// Needs a lot of inputs including every menu selected item, current ticket price, original ticket price
// This method will also update the ticket price based on the airports selected by calling airport checking functions
// Returns the new TicketPrice to update
   public static int SetTicketPrice(int originalTicketPrice, String usersStartAirport,String 
                     usersDestinationAirport,String UsersSeatChoice,String 
                     UsersMultipleFlightChoice) {
                     
      // We want the new ticket price to be the original before we collect all of the changes               
      double newTicketPrice = originalTicketPrice;
      // Make sure our values are not null. If they are null make them ""
      if (usersStartAirport == null || usersDestinationAirport == null) {
         usersStartAirport = "";
         usersDestinationAirport = "";
      
      
      }
      
// Lets handle Airport choice
      if (!usersStartAirport.equals(usersDestinationAirport)) {
         //Since there airports are not the same we can continue
         
         
         // Since neither airport is set default or equals the airport we can continue
            if (!usersStartAirport.equals("Select an airport") || usersDestinationAirport.equals("Select an airport")) {
            
            // We need to retrieve more information on the start and destination airport
            String[] infoOnStartAirport;
            String[] infoOnDestinationAirport;
            
            // Lets gather the info on these two airports
            try {
               infoOnStartAirport = AirportInformationScanner.ReturnInfoOnAirport(usersStartAirport);
               infoOnDestinationAirport = AirportInformationScanner.ReturnInfoOnAirport(usersDestinationAirport);
            // These have to go in the try block otherwise compile error since we may not have gathered
            // data on our airports
            // Since we can't use string.contains with an array we have to convert the values
            // that we need.

               String startlat = infoOnStartAirport[3];
               String endlat = infoOnDestinationAirport[3];
            
               // Now we can test if we should increase price or not with latitude
               if(!startlat.contains("N") || !endlat.contains("N")) {
               
               // Do nothing we are good so far. We do not have two N's
               
                  if (!startlat.contains("S") || !endlat.contains("S")) {
                  
                  // Do nothing we are good so far. We do not have two N's 
                  // We do not have two S's
                     if (!startlat.contains("E") || !endlat.contains("E")) {
                  
                     // Do nothing we are good so far. We do not have two N's
                     // We do not have two S's. We do not have two E's
                     
                        if (!startlat.contains("W") || !endlat.contains("W")) {
                        // Now we need to act. The latitudes for start and end do 
                        // not match so increase price
                           newTicketPrice = newTicketPrice*2;
                     
                        }
                  
                     }  
                  }
               
               }
               
               String startlon = infoOnStartAirport[4];
               String endlon = infoOnDestinationAirport[4];

               
               
               // Now we can test if we should increase price or not with longitude
               if(!startlon.contains("N") || !endlon.contains("N")) {
               
               // Do nothing we are good so far. We do not have two N's
               
                  if (!startlon.contains("S") || !endlon.contains("S")) {
                  
                  // Do nothing we are good so far. We do not have two N's 
                  // We do not have two S's
                     if (!startlon.contains("E") || !endlon.contains("E")) {
                  
                     // Do nothing we are good so far. We do not have two N's
                     // We do not have two S's. We do not have two E's
                     
                        if (!startlon.contains("W") || !endlon.contains("W")) {
                        // Now we need to act. The longitudes for start and end do 
                        // not match so increase price
                           newTicketPrice = newTicketPrice*2;
                     
                        }
                  
                     }  
                  }
               
               }

            }
            catch (FileNotFoundException f) {
            System.out.print("An error occured while trying to access the airports.txt database");
            }
            
         
            }
      
           }
      
// Lets handle Seat choice
         // Increase the prices based on seat upgrade
          if (UsersSeatChoice.equals("First Class")) {
         
            // User wants first class so increase price x3
               newTicketPrice = newTicketPrice*4;

            }
            
            else if (UsersSeatChoice.equals("Business Class")) {
         
            
            // User wants Business Class so increase price x2
               newTicketPrice = newTicketPrice*3;

               
            }
         
            else if (UsersSeatChoice.equals("Premium Economy Class")) {
         
            
            // User wants Business Class so increase price x3
               newTicketPrice = newTicketPrice*1.5;
               
            }
         
         
            // If User wants Economy Class the price stays the same
            // so do nothing


            
// Lets handle multipleFlightChoice
      
            if (UsersMultipleFlightChoice.equals("Round trip ticket")) {
      
               // User wants a Round Trip ticket so increase price x1.5
                  newTicketPrice = newTicketPrice*1.5;
            
               
             }
             
             
              // If User picked One way ticket the price stays the same
              // so do nothing
                        
      
                     
// Lets handle Bag Choice
// NOT DONE YET

// Now we know the real ticket price so send it back
// Since we currently have a double we have to convert it to int
return (int)newTicketPrice;

}


/*
 Needs a lot of inputs including every menu selected item, current ticket price, original ticket price
 This method will create an array of all of the ticket primiums associated with every choice the user made for their final ticket
 Returns an array containing all of the ticket primiums placed into the following sections of the array


 result[0] contains Airports distance Primium
 result[1] contains Seat Choice Premium 
 result[2] contains multiWay Flight Premium
 */
 
   public static double[] CalculateTicketPrimiums(int originalTicketPrice, String usersStartAirport,String 
                     usersDestinationAirport,String UsersSeatChoice,String 
                     UsersMultipleFlightChoice) {
         
      // This array will sotre our results of the premiums once we calculate them
      // We initialize everything to zero because we will assume no primiums per index until we find evidence to have them
      double [] result = {0,0,0};   
      
                     
      // We want the new ticket price to be the original before we collect all of the changes               
      double newTicketPrice = originalTicketPrice;
      
      
      // Make sure our values are not null. If they are null make them ""
      if (usersStartAirport == null || usersDestinationAirport == null) {
         usersStartAirport = "";
         usersDestinationAirport = "";
      
      
      }
      
// Lets handle Airport choice
      if (!usersStartAirport.equals(usersDestinationAirport)) {
         //Since there airports are not the same we can continue
         
         
         // Since neither airport is set default or equals the airport we can continue
            if (!usersStartAirport.equals("Select an airport") || usersDestinationAirport.equals("Select an airport")) {
            
            // We need to retrieve more information on the start and destination airport
            String[] infoOnStartAirport;
            String[] infoOnDestinationAirport;
            
            // Lets gather the info on these two airports
            try {
               infoOnStartAirport = AirportInformationScanner.ReturnInfoOnAirport(usersStartAirport);
               infoOnDestinationAirport = AirportInformationScanner.ReturnInfoOnAirport(usersDestinationAirport);
            // These have to go in the try block otherwise compile error since we may not have gathered
            // data on our airports
            // Since we can't use string.contains with an array we have to convert the values
            // that we need.

               String startlat = infoOnStartAirport[3];
               String endlat = infoOnDestinationAirport[3];
            
               // Now we can test if we should increase price or not with latitude
               if(!startlat.contains("N") || !endlat.contains("N")) {
               
               // Do nothing we are good so far. We do not have two N's
               
                  if (!startlat.contains("S") || !endlat.contains("S")) {
                  
                  // Do nothing we are good so far. We do not have two N's 
                  // We do not have two S's
                     if (!startlat.contains("E") || !endlat.contains("E")) {
                  
                     // Do nothing we are good so far. We do not have two N's
                     // We do not have two S's. We do not have two E's
                     
                        if (!startlat.contains("W") || !endlat.contains("W")) {
                        // Now we need to act. The latitudes for start and end do 
                        // not match so increase price
                        
                           // We can add the distance premium too our result array
                           result[0] += newTicketPrice*2 - newTicketPrice;
                        
                           newTicketPrice = newTicketPrice*2;
                     
                        }
                  
                     }  
                  }
               
               }
               
               String startlon = infoOnStartAirport[4];
               String endlon = infoOnDestinationAirport[4];

               
               
               // Now we can test if we should increase price or not with longitude
               if(!startlon.contains("N") || !endlon.contains("N")) {
               
               // Do nothing we are good so far. We do not have two N's
               
                  if (!startlon.contains("S") || !endlon.contains("S")) {
                  
                  // Do nothing we are good so far. We do not have two N's 
                  // We do not have two S's
                     if (!startlon.contains("E") || !endlon.contains("E")) {
                  
                     // Do nothing we are good so far. We do not have two N's
                     // We do not have two S's. We do not have two E's
                     
                        if (!startlon.contains("W") || !endlon.contains("W")) {
                        
                           // We can add the distance premium too our result array
                           // We can not add distance yet because the the last 4 ifs may have been true so our value will be wrong
                           // The solution is to add the result to our previous result
                           result[0] += newTicketPrice*2 - newTicketPrice;
                           
                        // Now we need to act. The longitudes for start and end do 
                        // not match so increase price
                           newTicketPrice = newTicketPrice*2;
                           
                     
                        }
                  
                     }  
                  }
               
               }

            }
            catch (FileNotFoundException f) {
            System.out.print("An error occured while trying to access the airports.txt database");
            }
            
         
            }
      
           }
      
// Lets handle Seat choice
         // Increase the prices based on seat upgrade
          if (UsersSeatChoice.equals("First Class")) {
         
            // We can add the seat choice premium too our result array
               result[1] += newTicketPrice*4 - newTicketPrice;
               
            // User wants first class so increase price x3
               newTicketPrice = newTicketPrice*4;
               

            }
            
            else if (UsersSeatChoice.equals("Business Class")) {
         
 
            // We can add the seat choice premium too our result array
               result[1] += newTicketPrice*3 - newTicketPrice;
               
            // User wants Business Class so increase price x2
               newTicketPrice = newTicketPrice*3;
               
               
            }
         
            else if (UsersSeatChoice.equals("Premium Economy Class")) {
         
            // We can add the seat choice premium too our result array
               result[1] += newTicketPrice*1.5 - newTicketPrice;
               
               
            // User wants Business Class so increase price x3
               newTicketPrice = newTicketPrice*1.5;
               
               
            }
         
         
            // If User wants Economy Class the price stays the same
            // so do nothing


            
// Lets handle multipleFlightChoice
      
            if (UsersMultipleFlightChoice.equals("Round trip ticket")) {
 
            // We can add the seat choice premium too our result array
               result[2] += newTicketPrice*1.5 - newTicketPrice;
      
               // User wants a Round Trip ticket so increase price x1.5
                  newTicketPrice = newTicketPrice*1.5;
                  
            
               
             }
             
             
              // If User picked One way ticket the price stays the same
              // so do nothing
                        
      
                     

return result;


}

// This function will test an inputted date with Today's current date
// If The inputed date is illegally formatted such as when year is impossible, day is impossible, month is impossible, etc
// then false will be returned
// False will also be returned if the inputed date is not after Today's date
  public static boolean compareTodayWithUserDate(JLabel label, String month,String day,String year) {    
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY");  
   LocalDate now = LocalDate.now();
   LocalDate inputDate;
   System.out.println(dtf.format(now)); 
   
   // We need to compare two diffrent dates (The current date with the customer's picked date
   
      String UserDate = year + "-" + month + "-" + day;
      
      // Reset the error message back to nothing since we do not know if the date is valid or not yet
      label.setText("                                                   ");
   
  
      try {
   
         inputDate = LocalDate.parse(UserDate);
   
   
         if (now.isBefore(inputDate)) {
         
         // This is the case we want/need
            // Since the date is formated correctly get rid of any erro message
            label.setText("                                                   ");
            System.out.println("DEBUG: Date of now: " + dtf.format(now) + " is before the user's selected date: " + dtf.format(inputDate));
            return true;

         }
         else if (now.isAfter(inputDate) || now.isEqual(inputDate)) {
         
            // The  user date is not a valid entry so inform the user
            label.setText("**Your ticket has to be in the future**");
            System.out.println("DEBUG: Uh oh: Your flight has to be in the future");
            return false;
         }
       }
  
        catch (DateTimeParseException f) {
        
            // The  user date is not a valid entry so inform the user
            label.setText("**Your start date is not a valid date**");
            System.out.println("DEBUG: Date format of inputDate was illegal");
            return false;
   
        }
     // we should never get here but in case we do something wrong happened 
            System.out.println("WARNING: There was an issue that occured while trying to compare Today's and the user's date");
     return false;
  
      }
      
      // This function will test an inputted date with another inputted date
// If The inputed date is illegally formatted such as when year is impossible, day is impossible, month is impossible, etc
// then false will be returned
// False will also be returned if the second date is not after the first date
  public static boolean compareOriginalWithNewUserDate(JLabel label, String month1,String day1,String year1, String month2, String day2, String year2) {    
   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-YYYY");  
   LocalDate firstDate;
   LocalDate secondDate;
   
   // We need to compare two diffrent dates (The first date with the second date
   
      String UserDate1 = year1 + "-" + month1 + "-" + day1;
      String UserDate2 = year2 + "-" + month2 + "-" + day2;
      
      // Reset the error message back to nothing since we do not know if the date is valid or not yet
      label.setText("                                                   ");
   
  
      try {
   
         firstDate = LocalDate.parse(UserDate1);
         secondDate = LocalDate.parse(UserDate2);
   
   
         if (firstDate.isBefore(secondDate)) {
         
         // This is the case we want/need
            // Since the date is formated correctly get rid of any erro message
            label.setText("                                                                  ");
            System.out.println("DEBUG: Date of user's first selected date: " + dtf.format(firstDate) + " is before the user's second selected date: " + dtf.format(secondDate));
            return true;

         }
         else if (firstDate.isAfter(secondDate) || firstDate.isEqual(secondDate)) {
         
            // The  user date is not a valid entry so inform the user
            label.setText("**Return date has to be later than original date**");
            System.out.println("DEBUG: Uh oh: Your return has to be in the future of the original date");
            return false;
         }
       }
  
        catch (DateTimeParseException f) {
        
            // The  user date is not a valid entry so inform the user
            label.setText("**Your return date is not valid **");
            System.out.println("DEBUG: Date format of inputDate was illegal");
            return false;
   
        }
     // we should never get here but in case we do something wrong happened 
            System.out.println("WARNING: There was an issue that occured while trying to compare Today's and the user's date");
     return false;
  
      }


}