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
                  destinationAirportLocation, userSeatChoiceMsg, UserRoundorSingletripMsg, ticketTypeMsg, flightTimeMsg, flightTimeMsg2, dividerMsg, paymentInfoMsg, InitialflightPriceMsg,
                  flightDistancePremiumMsg,SeatChoicePremiumMsg,multiWayFlightPremiumMsg,divider2Msg,finalPriceWithoutTaxMsg,taxAmountMsg,totalAmuontUserOwesMsg,ticketConfirmedMsg;
   
   private String TheUser, usersStartAirport, usersDestinationAirport, UsersSeatChoice, UsersMultipleFlightChoice, UsersFlightTimeChoice, completeUserFlightDate,
            UsersFlightTimeChoice2,completeUserFlightDate2; 
   
   private double originalticketPrice, finalticketPrice;
   
   // This boolean will denote whether or not this ticket already exists
   private boolean isNewTicket;
 
   // These represent our hidden text boxes that the user will have to fill in to continue
   // we need a lot of inputs for every entry field on the Schedule a flight interface as well as the customers name and the final cost
   // of the customers flight(s)
   public FinalizeTicketInterface(String TheUserIn,String usersStartAirportIn, String usersDestinationAirportIn,String UsersSeatChoiceIn, 
                     String UsersMultipleFlightChoiceIn, int originalticketPriceIn, int finalticketPriceIn,String UsersFlightTimeChoiceIn,
                     String completeUserFlightDateIn, String UsersFlightTimeChoice2In, String completeUserFlightDate2In, boolean isNewTicketIn) {
     
     // Lets initialize all of our input values               
      TheUser = TheUserIn;
      usersStartAirport = usersStartAirportIn;
      usersDestinationAirport = usersDestinationAirportIn;
      UsersSeatChoice = UsersSeatChoiceIn;
      UsersMultipleFlightChoice = UsersMultipleFlightChoiceIn;
      originalticketPrice = originalticketPriceIn;
      finalticketPrice = finalticketPriceIn;
      UsersFlightTimeChoice = UsersFlightTimeChoiceIn;
      completeUserFlightDate = completeUserFlightDateIn;
      UsersFlightTimeChoice2 = UsersFlightTimeChoice2In;
      completeUserFlightDate2 = completeUserFlightDate2In;
      
                          
      isNewTicket = isNewTicketIn;               
      createView();
      
      setTitle(TheUser + "'s ticket");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //set display frame size (x-axis, y-axis)
      setSize(570, 720);
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

      TicketInfoOnUser = new JLabel("Ticket for customer: " + TheUser);
      TicketInfoOnUser.setFont(new Font("Arial", Font.PLAIN, 12));
      
      startAirportNameMsg = new JLabel("Start Airport: " + usersStartAirport);
      startAirportNameMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      startAirportcode = new JLabel();
      startAirportcode.setFont(new Font("Arial", Font.PLAIN, 12));
      
      startAirportLocation = new JLabel();
      startAirportLocation.setFont(new Font("Arial", Font.PLAIN, 12));

      destinationAirportNameMsg = new JLabel("Destination Airport: " + usersDestinationAirport);
      destinationAirportNameMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      destinationAirportcode = new JLabel();
      destinationAirportcode.setFont(new Font("Arial", Font.PLAIN, 12));
      
      destinationAirportLocation = new JLabel();
      destinationAirportLocation.setFont(new Font("Arial", Font.PLAIN, 12));
 
      userSeatChoiceMsg = new JLabel("Seat choice for flight: " + UsersSeatChoice);
      userSeatChoiceMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      ticketTypeMsg = new JLabel("This ticket is a: "  + UsersMultipleFlightChoice);
      ticketTypeMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      flightTimeMsg = new JLabel("Your flight is scheduled for: "  + completeUserFlightDate + " at " + UsersFlightTimeChoice);
      flightTimeMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      flightTimeMsg2 = new JLabel();
      flightTimeMsg2.setFont(new Font("Arial", Font.PLAIN, 12));
      
      
      if (UsersMultipleFlightChoice.equals("Round trip ticket")) {
      
         flightTimeMsg2.setText("Your return flight is scheduled for: "  + completeUserFlightDate2 + " at " + UsersFlightTimeChoice2);
      
      }
      else {
      
         flightTimeMsg2.setText("No return flight time exists");
         
      }
      
      dividerMsg = new JLabel("__________________________________________________");
      dividerMsg.setFont(new Font("Arial", Font.PLAIN, 12));
   
      paymentInfoMsg = new JLabel("Transaction details for this flight");
      paymentInfoMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      InitialflightPriceMsg = new JLabel("Base price of a ticket: $ " + originalticketPrice);
      InitialflightPriceMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      flightDistancePremiumMsg = new JLabel();
      flightDistancePremiumMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      SeatChoicePremiumMsg = new JLabel();
      SeatChoicePremiumMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      // ie the expense of a round trip flight if this was selected
      multiWayFlightPremiumMsg = new JLabel();
      multiWayFlightPremiumMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      divider2Msg = new JLabel("__________________________________________________");
      divider2Msg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      finalPriceWithoutTaxMsg = new JLabel("Final ticket price: $" +  finalticketPrice);
      finalPriceWithoutTaxMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      
      double taxBill = finalticketPrice*0.09; 
      taxAmountMsg = new JLabel("Sales tax of your purchase: $" +  taxBill);
      taxAmountMsg.setFont(new Font("Arial", Font.PLAIN, 12));
      
      // If we + to a string we only concatenate it instead of acutually doing math like we want so we need to define toalCost first
      double totalCost = finalticketPrice+taxBill;
      
      totalAmuontUserOwesMsg = new JLabel("Total price of transaction: $" +  totalCost);
      totalAmuontUserOwesMsg.setFont(new Font("SansSerif", Font.PLAIN, 22));

      ticketConfirmedMsg = new JLabel();
      ticketConfirmedMsg.setFont(new Font("SansSerif", Font.PLAIN, 26));
      ticketConfirmedMsg.setForeground (Color.green);
      
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
         destinationAirportLocation.setText("Airport Location: " + moreInfoOnDestinationAirport[2]);
         
         //Lets handle the ticket premiums
         // First we need to calculate the premiums
         double [] Userpremiums;
         Userpremiums = HelperMethodsForScheduleAFlightInterface.CalculateTicketPrimiums((int)originalticketPrice, usersStartAirport, 
                     usersDestinationAirport, UsersSeatChoice,UsersMultipleFlightChoice);
 
         flightDistancePremiumMsg.setText("Flight Distance Premium: +$" + Userpremiums[0]);
         SeatChoicePremiumMsg.setText("Seat Choice Premium: +$" + Userpremiums[1]);
         
         multiWayFlightPremiumMsg.setText(UsersMultipleFlightChoice + " Premium: +$" + Userpremiums[2]);
         
         
         
      }
      catch (FileNotFoundException f) {
         System.out.println("An error occured while trying to access the airports.txt database");
      }
      
      
      
      
      submit = new JButton("Confirm Sale");
      submit.addActionListener(
         new submitActionListener());
      

      
      
      
      
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
               TicketInfoOnUser.setBounds(2, 0, 400, 20);
            startAirportNameMsg.setBounds(2, 30, 600, 50);
               startAirportcode.setBounds(36, 60, 600, 50);
           startAirportLocation.setBounds(36, 90, 600, 50);
     
       destinationAirportNameMsg.setBounds(2, 140, 600, 50);
         destinationAirportcode.setBounds(36, 170, 600, 50);
     destinationAirportLocation.setBounds(36, 200, 600, 50);
    
               userSeatChoiceMsg.setBounds(2, 250, 600, 50);
                   ticketTypeMsg.setBounds(2, 280, 600, 50);
                   flightTimeMsg.setBounds(2, 310, 600, 50);
                   flightTimeMsg2.setBounds(2, 340, 600, 50);
                   
                      dividerMsg.setBounds(2, 350, 600, 50);
                   
                   paymentInfoMsg.setBounds(2, 370, 600, 50);
            InitialflightPriceMsg.setBounds(2, 400, 600, 50);
         flightDistancePremiumMsg.setBounds(2, 430, 600, 50);
             SeatChoicePremiumMsg.setBounds(2, 460, 600, 50);
         multiWayFlightPremiumMsg.setBounds(2, 490, 600, 50);
                      divider2Msg.setBounds(2, 520, 600, 50);
                      
                      
          finalPriceWithoutTaxMsg.setBounds(2, 540, 600, 50);
                     taxAmountMsg.setBounds(2, 570, 600, 50);
                     
           // This message should have emphasis so it will be bigger and farther away from other messages                  
           totalAmuontUserOwesMsg.setBounds(2, 600, 600, 50);


            // We want to hide this button if this ticket already exists
           if (isNewTicket == true) {
           
           submit.setBounds(175, 650, 200, 30);
           
           }
           ticketConfirmedMsg.setBounds(2,690,1000,50);
      
      
      
          // This is where we make all of our interface objects visible
      panel.add(TicketInfoOnUser);    
      panel.add(startAirportNameMsg);
      panel.add(startAirportcode);
      panel.add(startAirportLocation);
      
      
      panel.add(destinationAirportNameMsg);
      panel.add(destinationAirportcode);
      panel.add(destinationAirportLocation);
      
      panel.add(userSeatChoiceMsg);
      panel.add(ticketTypeMsg);
      panel.add(flightTimeMsg);
      panel.add(flightTimeMsg2);
      
      panel.add(dividerMsg);
      
      panel.add(paymentInfoMsg);
      panel.add(InitialflightPriceMsg);
      panel.add(flightDistancePremiumMsg);
      panel.add(SeatChoicePremiumMsg);
      panel.add(multiWayFlightPremiumMsg);
      panel.add(divider2Msg);
      panel.add(finalPriceWithoutTaxMsg);
      panel.add(taxAmountMsg);
      panel.add(totalAmuontUserOwesMsg);
      
      panel.add(submit);
      panel.add(ticketConfirmedMsg);
      
   
   }
 
 
    
    

   // This class is triggered as soon as we select the submit button through the interface
   private class submitActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      // We want to hide the button so the user can not keep trying to create the exact same ticket over and over                                  
      submit.setBounds(5000, 640, 200, 30);
      String userName ="";
      String password ="";
      boolean isValidCustomer = false;
      
   //set display frame size (x-axis, y-axis)
   //CHANGE DISPLAY SIZE TO INFORM USER AT THE BOTTOM THAT THEIR TICKET WAS CREATED
   //DO THIS WITH A JLABEL UNDERNEATH THE CONFIRM SALE BUTTON
      setSize(770, 770);
      // This will let us know if a ticket was properly added or not
      boolean isTicketAdded = false;
      
      // When we have a one way ticket the date and time for the second flight is not applicable to add to the database
      if (UsersMultipleFlightChoice.equals("One way ticket")) {
         UsersFlightTimeChoice2 = ("N/A");
         completeUserFlightDate2 = ("N/A");
      
      }
      
      
      try {
         isTicketAdded = TicketInformationScanner.addNewTicket(TheUser,usersStartAirport,usersDestinationAirport,
                                          UsersSeatChoice,UsersMultipleFlightChoice,originalticketPrice,
                                          finalticketPrice,UsersFlightTimeChoice,completeUserFlightDate, 
                                          UsersFlightTimeChoice2, completeUserFlightDate2);
         
         // Inform the user that the ticket was added to the database when it is                                
         if (isTicketAdded = true) {
         
            ticketConfirmedMsg.setText("Congratulations your new ticket has been confirmed");
         } 
         
         // Inform the user that the ticket was not added to the ticket database
         else {
         
            ticketConfirmedMsg.setForeground (Color.red);
            ticketConfirmedMsg.setText("Oh no, your new ticket could not be confirmed :(");
         }                                       
                                    
                                          
      }                  
      catch (FileNotFoundException f) {
      System.out.println("An error occured while trying to write to tickets.txt");
      
      }

      System.out.println("DEBUG: Confirm Sale button was pressed");
      
      
     }
   }


}




