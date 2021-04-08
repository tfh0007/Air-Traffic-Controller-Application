import javax.swing.*;
import java.awt.*;

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

                  
               label.setText("$" + i + "." + centD + centS);
               
               try {
                  Thread.sleep(15);
               
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
            
               // We need to ad a comma when the price is over 999 (NOT YET DONE)   
               label.setText("$" + i + "." + centD + centS);
               
               try {
               Thread.sleep(15);
               
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
      
      
      // >>>>>>>>>>>>>>>>>>>>Fill out check for usersStartAirport
      
      // >>>>>>>>>>>>>>>>>>>>Fill out check for usersDestinationAirport                
      
      
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
}