// Thomas Hansknecht

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.io.FileNotFoundException;


public class FinalizeTicketInterface extends JFrame{

   // This class handles reading and writing to the customers.txt file which is the database of customer information
   private CustomerInformationScanner UserInformation;


// submit represents the submit button that is pushed after filling out the needed fields
// createNewUser is the New User button that will allow an individual to create a new account
   private JButton submit, createNewUser;
   
   private JLabel TicketInfoOnUser, startAirportMsg, startAirportNameMsg, startAirportcode, startAirportLocation, destinationAirportNameMsg, destinationAirportcode, 
                  destinationAirportLocation, userSeatChoiceMsg, UserRoundorSingletripMsg, ticketTypeMsg, dividerMsg, paymentInfoMsg, InitialflightPriceMsg,
                  flightDistancePremiumMsg,SeatChoicePremiumMsg,multiWayFlightPremiumMsg,divider2Msg,finalPriceWithoutTaxMsg,taxAmountMsg,totalAmuontUserOwesMsg;
   
   private String TheUser, usersStartAirport, usersDestinationAirport, UsersSeatChoice, UsersMultipleFlightChoice; 
   
   private double originalticketPrice, finalticketPrice;
 
   // These represent our hidden text boxes that the user will have to fill in to continue
   // we need a lot of inputs for every entry field on the Schedule a flight interface as well as the customers name and the final cost
   // of the customers flight(s)
   public FinalizeTicketInterface(String TheUserIn,String usersStartAirportIn, String usersDestinationAirportIn,String UsersSeatChoiceIn, 
                     String UsersMultipleFlightChoiceIn, int originalticketPriceIn, int finalticketPriceIn) {
     
     // Lets initialize all of our input values from the Schedule a flight interface               
      TheUser = TheUserIn;
      usersStartAirport = usersStartAirportIn;
      usersDestinationAirport = usersDestinationAirportIn;
      UsersSeatChoice = UsersSeatChoiceIn;
      UsersMultipleFlightChoice = UsersMultipleFlightChoiceIn;
      originalticketPrice = originalticketPriceIn;
      finalticketPrice = finalticketPriceIn;                    
                     
      createView();
      
      setTitle(TheUser + "'s new ticket");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //set display frame size (x-axis, y-axis)
      setSize(700, 900);
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      //Disable resize
      setResizable(false);
      
      // Make the window visible
      setVisible(true);
   }
   
  // Create the interface and add everything to it
   private void createView() {
   
   
      JPanel panel = new JPanel();
      getContentPane().add(panel);
      panel.setBackground(Color.WHITE);
      panel.setLayout(null);
      
      TicketInfoOnUser = new JLabel();
      TicketInfoOnUser.setFont(new Font("SansSerif", Font.PLAIN, 18));
      TicketInfoOnUser.setForeground (Color.white);
      TicketInfoOnUser = new JLabel("Ticket for customer: " + TheUser);
      
      
      startAirportNameMsg = new JLabel();
      startAirportNameMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      startAirportNameMsg.setForeground (Color.white);
      startAirportNameMsg = new JLabel("Starting Airport Name: " + usersStartAirport);
      
      startAirportcode = new JLabel();
      startAirportcode.setFont(new Font("SansSerif", Font.PLAIN, 18));
      startAirportcode.setForeground (Color.white);
      startAirportcode = new JLabel();
      
      startAirportLocation = new JLabel();
      startAirportLocation.setFont(new Font("SansSerif", Font.PLAIN, 18));
      startAirportLocation.setForeground (Color.white);
      startAirportLocation = new JLabel();

      
      destinationAirportNameMsg = new JLabel();
      destinationAirportNameMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      destinationAirportNameMsg.setForeground (Color.white);
      destinationAirportNameMsg = new JLabel("Destination Airport Name: " + usersDestinationAirport);
      
      destinationAirportcode = new JLabel();
      destinationAirportcode.setFont(new Font("SansSerif", Font.PLAIN, 18));
      destinationAirportcode.setForeground (Color.white);
      destinationAirportcode = new JLabel();
      
      destinationAirportLocation = new JLabel();
      destinationAirportLocation.setFont(new Font("SansSerif", Font.PLAIN, 18));
      destinationAirportLocation.setForeground (Color.white);
      destinationAirportLocation = new JLabel();
 
      userSeatChoiceMsg = new JLabel();
      userSeatChoiceMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      userSeatChoiceMsg.setForeground (Color.white);
      userSeatChoiceMsg = new JLabel("Seat choice for flight: " + UsersSeatChoice);
      
      ticketTypeMsg = new JLabel();
      ticketTypeMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      ticketTypeMsg.setForeground (Color.white);
      ticketTypeMsg = new JLabel("This ticket: is a "  + UsersMultipleFlightChoice);
      
      
      dividerMsg = new JLabel();
      dividerMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      dividerMsg.setForeground (Color.white);
      dividerMsg = new JLabel("__________________________________________________");
   
      paymentInfoMsg = new JLabel();
      paymentInfoMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      paymentInfoMsg.setForeground (Color.white);
      paymentInfoMsg = new JLabel("Transaction details for this flight");
      
      InitialflightPriceMsg = new JLabel();
      InitialflightPriceMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      InitialflightPriceMsg.setForeground (Color.white);
      InitialflightPriceMsg = new JLabel("Base price of a ticket: $ " + originalticketPrice + ".00");
      
      flightDistancePremiumMsg = new JLabel();
      flightDistancePremiumMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      flightDistancePremiumMsg.setForeground (Color.white);
      flightDistancePremiumMsg = new JLabel();
      
      SeatChoicePremiumMsg = new JLabel();
      SeatChoicePremiumMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      SeatChoicePremiumMsg.setForeground (Color.white);
      SeatChoicePremiumMsg = new JLabel();
      
      // ie the expense of a round trip flight if this was selected
      multiWayFlightPremiumMsg = new JLabel();
      multiWayFlightPremiumMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      multiWayFlightPremiumMsg.setForeground (Color.white);
      multiWayFlightPremiumMsg = new JLabel();
      
      divider2Msg = new JLabel();
      divider2Msg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      divider2Msg.setForeground (Color.white);
      divider2Msg = new JLabel("__________________________________________________");
      
      finalPriceWithoutTaxMsg = new JLabel();
      finalPriceWithoutTaxMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      finalPriceWithoutTaxMsg.setForeground (Color.white);
      finalPriceWithoutTaxMsg = new JLabel("Final ticket price: $" +  finalticketPrice + ".00");
      
      
      double taxBill = finalticketPrice*0.09; 
      taxAmountMsg = new JLabel();
      taxAmountMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      taxAmountMsg.setForeground (Color.white);
      taxAmountMsg = new JLabel("Sales tax of your purchase: $" +  taxBill);
      
      totalAmuontUserOwesMsg = new JLabel();
      totalAmuontUserOwesMsg.setFont(new Font("SansSerif", Font.PLAIN, 18));
      totalAmuontUserOwesMsg.setForeground (Color.white);
      totalAmuontUserOwesMsg = new JLabel("Total price of transaction: $" +  finalticketPrice+taxBill);
      
      
      // We need more information on the start and destination airport
      try {
         String [] moreInfoOnStartAirport;
         String [] moreInfoOnDestinationAirport;
         
      
         moreInfoOnStartAirport = AirportInformationScanner.ReturnInfoOnAirport(usersStartAirport);
         moreInfoOnDestinationAirport = AirportInformationScanner.ReturnInfoOnAirport(usersDestinationAirport);
         
         //Lets handle start airport extra details
         startAirportcode.setText("Airport Code: " + moreInfoOnStartAirport[1]);
         startAirportLocation.setText("Airport Location: " + moreInfoOnStartAirport[2]);
         
         //Lets handle destination airport extra details
         destinationAirportcode.setText("Airport Code: " + moreInfoOnDestinationAirport[1]);
         destinationAirportcode.setText("Airport Location: " + moreInfoOnDestinationAirport[2]);
         
         
         
         
         
         
         
      }
      catch (FileNotFoundException f) {
         System.out.println("An error occured while trying to access the airports.txt database");
      }
      
      
      
      
      submit = new JButton("Confirm Sale");
      submit.addActionListener(
         new submitActionListener());
      

      
      
      
      
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
               TicketInfoOnUser.setBounds(1, 0, 400, 20);
            startAirportNameMsg.setBounds(1, 20, 600, 50);
               startAirportcode.setBounds(36, 40, 600, 50);
           startAirportLocation.setBounds(36, 60, 600, 50);
     
      destinationAirportNameMsg.setBounds(1, 100, 600, 50);
         destinationAirportcode.setBounds(36, 120, 600, 50);
     destinationAirportLocation.setBounds(36, 140, 600, 50);
    
              userSeatChoiceMsg.setBounds(1, 180, 600, 50);
      

      submit.setBounds(250, 600, 200, 30);
      
      
      
          // This is where we make all of our interface objects visible
      panel.add(TicketInfoOnUser);    
      panel.add(startAirportNameMsg);
      panel.add(startAirportcode);
      panel.add(startAirportLocation);
      
      
      panel.add(destinationAirportNameMsg);
      panel.add(destinationAirportcode);
      panel.add(destinationAirportLocation);
      panel.add(userSeatChoiceMsg);
      
      panel.add(submit);
      
   
   }
 
 
    
    

   // This class is triggered as soon as we select the submit button through the interface
   private class submitActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      String userName ="";
      String password ="";
      boolean isValidCustomer = false;

      System.out.println("DEBUG: Confirm Sale button was pressed");
      
      
     }
   }


}




