import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.io.IOException;

import java.util.Date;      

//External Sources:
//Used https://www.tutorialspoint.com/how-to-add-background-image-to-jframe-in-java to figure out how to have a background image

// We can make the flight price based on distance using a 2 dimensional array for the scope of the United States
public class ScheduleAFlightInterface extends JFrame {

// This will be the name of our current user/customer
public static String TheUser;

// This is our startAirportCounter for the starting Airport decision in the menu
// TicketPrice is the current price of the customers ticket. The ticket price will start at the lowest value possible
// currentTicketPrice is the price of the ticket with our current selections
private int startAirportCount = 0, originalTicketPrice=223, currentTicketPrice=223, newTicketPrice;
private String usersStartAirport, usersDestinationAirport, UsersSeatChoice, UsersMultipleFlightChoice, UsersFlightTimeChoice;

// We need to gather the current date
private Date currentDate = new Date(); 

   // A list of all of our airports that can be used as arrival and destination locations
   // This array is causing an unsafe warning
private String[] airports = {};

   // A list of all of our seat choices that can be used by the customer
private String[] seatChoice = { "First Class", "Business Class", "Premium Economy Class", "Economy Class" };

   // A list of all of our multiple flight choices
private String[] multipleFlightChoice = { "Round trip ticket","One way ticket"};

private String[] flightTimeChoice = {"12:00 a.m", "12:15 a.m", "12:30 a.m","12:45 a.m","1:00 a.m", "1:15 a.m",
                                     "1:30 a.m","1:45 a.m","2:00 a.m", "2:15 a.m", "2:30 a.m","2:45 a.m","3:00 a.m",
                                     "3:15 a.m", "3:30 a.m","3:45 a.m","4:00 a.m", "4:15 a.m", "4:30 a.m","4:45 a.m",
                                     "5:00 a.m", "5:15 a.m", "5:30 a.m","5:45 a.m","6:00 a.m", "6:15 a.m", "6:30 a.m",
                                     "6:45 a.m","7:00 a.m", "7:15 a.m", "7:30 a.m","7:45 a.m","8:00 a.m", "8:15 a.m",
                                     "8:30 a.m","8:45 a.m","9:00 a.m", "9:15 a.m", "9:30 a.m","9:45 a.m","10:00 a.m",
                                     "10:15 a.m", "10:30 a.m","10:45 a.m","11:00 a.m", "11:15 a.m", "11:30 a.m","11:45 a.m",
                                     
                                     "12:00 p.m", "12:15 p.m", "12:30 p.m","12:45 p.m","1:00 p.m", "1:15 p.m",
                                     "1:30 p.m","1:45 p.m","2:00 p.m", "2:15 p.m", "2:30 p.m","2:45 p.m","3:00 p.m",
                                     "3:15 p.m", "3:30 p.m","3:45 p.m","4:00 p.m", "4:15 p.m", "4:30 p.m","4:45 p.m",
                                     "5:00 p.m", "5:15 p.m", "5:30 p.m","5:45 p.m","6:00 p.m", "6:15 p.m", "6:30 p.m",
                                     "6:45 p.m","7:00 p.m", "7:15 p.m", "7:30 p.m","7:45 p.m","8:00 p.m", "8:15 p.m",
                                     "8:30 p.m","8:45 p.m","9:00 p.m", "9:15 p.m", "9:30 p.m","9:45 p.m","10:00 p.m",
                                     "10:15 p.m", "10:30 p.m","10:45 p.m","11:00 p.m", "11:15 p.m", "11:30 p.m","11:45 p.m"};
                                     
// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("../Graphics/simpleBrownBackgroundLarge.jpg");

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut, GoBack, PrintOutYourTicket;
   
   private JLabel initialAirportMsg, FinalAirportMsg, startAirportInvalidMsg, destinationAirportInvalidMsg, SeatPreference, 
                  PurchasePriceMsg, PriceSoFarMsg, TicketResultsMsg, TicketNumberMsg, TicketStartingLocation, TicketEndingLocationMsg,
                  TimeOfFlightMsg, TicketPurchasePriceMsg, userDateSelectionMsg, dateFormatMsg, userDateInvalidMsg, timeSelectionMsg, finalTicketInvalidMsg;
   
   
   // This represents the field that will assign a particular date to our flight
   private JTextField monthOfFlight,dayOfFlight,yearOfFlight;
   
   private JComboBox menuForStartAirports, menuForDestinationAirports, menuForSeatChoice, menuForMultipleFlight, menuForDateSelection, menuForFlightTime;

   

public ScheduleAFlightInterface(String userName) {


// Initialize our variables
   TheUser = userName;

   createView(userName);

// by default UsersSeatChoice will be "Economy Class"
   UsersSeatChoice = "Economy Class";
// by default UsersMultipleFlightChoice will One way ticket
   UsersMultipleFlightChoice = "One way ticket";
// by default UsersFlightTimeChoice will be 12:00 p.m
   UsersFlightTimeChoice = "12:00 p.m";
   


      
      setTitle("Schedule a flight for " + userName + "  **Customer Layout** ");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //set display frame size (x-axis, y-axis)
      setSize(1600, 1000);
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      //Disable resize
      setResizable(false);
      // Make this window visible
      setVisible(true);

}



// Create the interface and add everything to it
   private void createView(String userName) {
   
      
// this will represent our JPanel
      
      // Lets add our image as a background object
      this.setContentPane(new JPanel() {
         @Override
         public void paintComponent(Graphics g) {
         
         

         
         //Lets paint the image so that it becomes visible
            super.paintComponent(g);
            g.drawImage(img, 0, 0, null);
         }
      });



      //panel.setBackground(Color.GRAY);
      this.setLayout(null);
      
      
      
      initialAirportMsg = new JLabel("Please select your starting airport location" );
      initialAirportMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      initialAirportMsg.setForeground (Color.white);
      
      startAirportInvalidMsg = new JLabel();
      startAirportInvalidMsg.setFont(new Font("Arial", Font.PLAIN, 20));
      startAirportInvalidMsg.setForeground (Color.red);
      
      FinalAirportMsg = new JLabel("Please select your destination airport location" );
      FinalAirportMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      FinalAirportMsg.setForeground (Color.white);
      
      
      
      destinationAirportInvalidMsg = new JLabel();
      destinationAirportInvalidMsg.setFont(new Font("Arial", Font.PLAIN, 20));
      destinationAirportInvalidMsg.setForeground (Color.red);

      SeatPreference = new JLabel("Please select your seat preference" );
      SeatPreference.setFont(new Font("Arial", Font.PLAIN, 30));
      SeatPreference.setForeground (Color.white);
      
      PurchasePriceMsg = new JLabel("Cost of your flight so far" );
      PurchasePriceMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      PurchasePriceMsg.setForeground (Color.white);
      
      PriceSoFarMsg = new JLabel("$ " + originalTicketPrice + ".00");
      PriceSoFarMsg.setFont(new Font("Arial", Font.PLAIN, 50));
      PriceSoFarMsg.setForeground (Color.gray);
      
      finalTicketInvalidMsg = new JLabel();
      finalTicketInvalidMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      finalTicketInvalidMsg.setForeground (Color.red);

      
      // Lets make our menues
      //This causes an unsafe warning somehow 
      
      // We need to calculate the values that should be stored in our airports array
      // We can use this same array for Start and Destination Airport
      try {
      airports = AirportInformationScanner.GatherListOfAirports();
      
      }
      catch (FileNotFoundException f) {
      System.out.println("An error occured while trying to access the airports.txt database");
      }
      
      // We know that the first element in our array will be null since we specifed inside the function
      // So we need to change index 0 to "Select an airport"
      airports[0] = "Select an airport";
      
      menuForStartAirports = new JComboBox(airports);
      menuForStartAirports.setSelectedIndex(0);
      menuForStartAirports.setFont(new Font("Arial", Font.PLAIN, 20));

      menuForStartAirports.addActionListener(
         new menuForStartAirportsActionListener());
         
         
      menuForDestinationAirports = new JComboBox(airports);
      menuForDestinationAirports.setSelectedIndex(0);
      menuForDestinationAirports.setFont(new Font("Arial", Font.PLAIN, 20));

      menuForDestinationAirports.addActionListener(
         new menuForDestinationAirportsActionListener());
         
         
            menuForSeatChoice = new JComboBox(seatChoice);
      menuForSeatChoice.setSelectedIndex(3);
      menuForSeatChoice.setFont(new Font("Arial", Font.PLAIN, 30));

      menuForSeatChoice.addActionListener(
         new menuForSeatChoiceActionListener());

            menuForMultipleFlight = new JComboBox(multipleFlightChoice);
      menuForMultipleFlight.setSelectedIndex(1);
      menuForMultipleFlight.setFont(new Font("Arial", Font.PLAIN, 30));

      menuForMultipleFlight.addActionListener(
         new menuForMultipleFlightActionListener());
 
            menuForFlightTime = new JComboBox(flightTimeChoice);
      menuForFlightTime.setSelectedIndex(48);
      menuForFlightTime.setFont(new Font("Arial", Font.PLAIN, 30));

      menuForFlightTime.addActionListener(
         new menuForFlightTimeActionListener());
 
         

            

      
      //Now lets make the right side of the screen
      TicketResultsMsg = new JLabel("Please tell us if you want a round trip ticket" );
      TicketResultsMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      TicketResultsMsg.setForeground (Color.white);
      
      timeSelectionMsg = new JLabel("Please select a time for your flight" );
      timeSelectionMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      timeSelectionMsg.setForeground (Color.white);
      
      userDateSelectionMsg = new JLabel("Please give a date and time for your flight" );
      userDateSelectionMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      userDateSelectionMsg.setForeground (Color.white);
      
      dateFormatMsg = new JLabel("MM     DD      YYYY");
      dateFormatMsg.setFont(new Font("Arial", Font.PLAIN, 22));
      dateFormatMsg.setForeground (Color.white);

      
      userDateInvalidMsg = new JLabel();
      userDateInvalidMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      userDateInvalidMsg.setForeground (Color.red);
      
      monthOfFlight = new JTextField();
      monthOfFlight.setFont(new Font("Arial", Font.PLAIN, 34));
      
      dayOfFlight = new JTextField();
      dayOfFlight.setFont(new Font("Arial", Font.PLAIN, 34));
      
      yearOfFlight = new JTextField();
      yearOfFlight.setFont(new Font("Arial", Font.PLAIN, 34));

      
                         
      logOut = new JButton("<-- Log Out");
      GoBack = new JButton("Click here to return to " + userName + "'s dashboard");
      PrintOutYourTicket = new JButton("Click here to finalize ticket");
      
     
      // Make the log out button Opaque
      logOut.setOpaque(false);
      logOut.setContentAreaFilled(false);
      logOut.setBorderPainted(false);
      logOut.setFont(new Font("Arial", Font.PLAIN, 30));
      logOut.setForeground (Color.red);
      
           logOut.addActionListener(
            new logOutActionListener());
            
      GoBack.setOpaque(false);
      GoBack.setContentAreaFilled(false);
      GoBack.setBorderPainted(true);
      GoBack.setFont(new Font("SansSerif", Font.PLAIN, 50));
      GoBack.setForeground (Color.white);
         
           GoBack.addActionListener(
            new GoBackActionListener());
            
      PrintOutYourTicket.setOpaque(false);
      PrintOutYourTicket.setContentAreaFilled(true);
      PrintOutYourTicket.setBorderPainted(true);
      PrintOutYourTicket.setFont(new Font("SansSerif", Font.PLAIN, 16));
      PrintOutYourTicket.setForeground (Color.black);
         
           PrintOutYourTicket.addActionListener(
            new PrintOutYourTicketActionListener());
         
           
           
      
      //Set the visible length of our Input Boxes
   //    usernameInput.setPreferredSize(new Dimension(400, 40));
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
          
          //Our labels
          initialAirportMsg.setBounds(50, 30, 1500, 200);
          startAirportInvalidMsg.setBounds(50, 170, 1000, 200);
          
          FinalAirportMsg.setBounds(50, 200, 1500, 200);
          destinationAirportInvalidMsg.setBounds(50, 340, 1000, 200);
          
          SeatPreference.setBounds(50, 370, 1500, 200);
        PurchasePriceMsg.setBounds(50, 540, 1500, 200);
           PriceSoFarMsg.setBounds(50, 625, 1500, 200);
      
   finalTicketInvalidMsg.setBounds(840, 630, 1500, 200);
          
          
            //Lets do right side
          TicketResultsMsg.setBounds(880, 30, 1500, 200);
         userDateSelectionMsg.setBounds(880, 200, 1500, 200);
                dateFormatMsg.setBounds(880, 340, 1500, 200);
          userDateInvalidMsg.setBounds(880, 380, 1500, 200);
          
          //Our buttons
          logOut.setBounds(1, 1, 250, 60);
          PrintOutYourTicket.setBounds(950, 625, 350, 60);
          GoBack.setBounds(50, 800, 1500, 80);
          
          //Our menues
          menuForStartAirports.setBounds(50, 190, 600, 50);
    menuForDestinationAirports.setBounds(50, 360, 600, 50);
             menuForSeatChoice.setBounds(50, 530, 600, 50);
         menuForMultipleFlight.setBounds(880, 190, 600, 50);
             menuForFlightTime.setBounds(1170, 360, 270, 55);

          //Our text fields
          
                 monthOfFlight.setBounds(880,360,70,55);
                 dayOfFlight.setBounds(950,360,70,55);
                 yearOfFlight.setBounds(1020,360,140,55);
          
          
               
          // This is where we make all of our interface objects visible
          
            this.add(initialAirportMsg);
            this.add(FinalAirportMsg);
            this.add(SeatPreference);
            this.add(PurchasePriceMsg);
            this.add(PriceSoFarMsg);
            this.add(startAirportInvalidMsg);
            this.add(destinationAirportInvalidMsg);
            this.add(finalTicketInvalidMsg);

            
            // Now lets do our menues
            this.add(menuForStartAirports);
            this.add(menuForDestinationAirports);
            this.add(menuForSeatChoice);
            this.add(menuForMultipleFlight);
            this.add(menuForFlightTime);
            
            //Now lets do right side
            this.add(TicketResultsMsg);
            
            this.add(logOut);
            this.add(GoBack);
            this.add(PrintOutYourTicket);
            this.add(userDateSelectionMsg);
            this.add(dateFormatMsg);
            this.add(monthOfFlight);
            this.add(dayOfFlight);
            this.add(yearOfFlight);
            this.add(userDateInvalidMsg);
   
   }



   
   private class logOutActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG: log Out button was pressed");
      
      // Hide the create new user interface window
      setVisible(false);
      
      // Destroy the create new user interface window. We can create a new one if needed later
      dispose();
      log_in_Screen frame1;
      frame1 = new log_in_Screen();
      
      }
   }
   
   
   private class GoBackActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG: Back to users dashboard button was pressed");
      
      // Hide the create new user interface window
      setVisible(false);
      
      // Destroy the create new user interface window. We can create a new one if needed later
      dispose();

      AirlineAndAirportInterface frame3;
      frame3 = new AirlineAndAirportInterface(TheUser);


      }
  }
  
   private class PrintOutYourTicketActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG: Finalize user ticket button pushed");
      
      // We do not want to dispose the previous page because this will be a min interface displaying ticket results
      
      // We need to check that all fields are filled in before the ticket result interface can work
      if (TheUser == null || usersStartAirport == null || usersDestinationAirport == null || UsersSeatChoice == null || UsersMultipleFlightChoice == null || UsersFlightTimeChoice == null) {
      
         finalTicketInvalidMsg.setText("**One or more of your entries was invalid**");
         return;
         }
      if (usersStartAirport.equals("Select an airport") || usersDestinationAirport.equals("Select an airport") || usersStartAirport.equals(usersDestinationAirport)) {
      
         finalTicketInvalidMsg.setText("**One or more airport entries is invalid**");
         return;
         }
      // Now lets check that the date is valid
      // First lets gather what the user has typed for the date pieces
      
      String monthOfUserFlight = monthOfFlight.getText();
      String dayOfUserFlight = dayOfFlight.getText();
      String yearOfUserFlight = yearOfFlight.getText();
      
      boolean dateTest = HelperMethodsForScheduleAFlightInterface.compareTodayWithUserDate(finalTicketInvalidMsg,monthOfUserFlight,
                         dayOfUserFlight,yearOfUserFlight);
      // If the users date is not a valid date we can not continue
      // The helper message will display the error to our user                   
      if (dateTest == false) {
      
         return;
      }                     
         
         
         
      
      // We made it past the check conditions so get rid of any error message here
         finalTicketInvalidMsg.setText("                                           ");
         
      // We want to make things simple so we will just send the entire date, instead of the pieces, to the finalize ticket interface   
      String completeUserFlightDate = (monthOfUserFlight + "/" + dayOfUserFlight + "/" + yearOfUserFlight);

      FinalizeTicketInterface frame6;
      frame6 = new FinalizeTicketInterface(TheUser,usersStartAirport,usersDestinationAirport,UsersSeatChoice, 
                     UsersMultipleFlightChoice,originalTicketPrice,currentTicketPrice,UsersFlightTimeChoice,completeUserFlightDate);
      
      }
   }
  
  
     private class menuForStartAirportsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

         JComboBox cb = (JComboBox)e.getSource();
         usersStartAirport = (String)cb.getSelectedItem();
         
         System.out.println("DEBUG: Option: " + usersStartAirport + " was just picked in the menu for start airport");
         
         // In order to use .equals below neither parameter can be null
         if (usersStartAirport == null || usersDestinationAirport == null) {
         // If one or more entries is null than atleast one airport has not been selected yet
            startAirportInvalidMsg.setText("**The start and destination airport must be picked**");
            return;
            }
         
         // Since this check is not working in the Helper methods for schedule a flight lets try it here
         // Lets check that our airport choice is a valid entry
         if (usersStartAirport.equals("Select an airport")) {
            startAirportInvalidMsg.setText("**The start and destination airport must be picked**");
            return;
         }
         // The user could have changed the destination value to an ivalid option earlier so we need to check for this
         else if (usersDestinationAirport.equals("Select an airport")) {
         return;
         
         
         }
         else if (usersStartAirport.equals(usersDestinationAirport)) {
            startAirportInvalidMsg.setText("**The start and destination airports can not be the same**");

            return;
         }
         
         // The airport was a valid entry so lets get rid of any error message
            startAirportInvalidMsg.setText("                                                                    ");
            destinationAirportInvalidMsg.setText("                                                                    ");
                           // We do not want a newTicketPrice to conflict with our result so reset newTicketPrice
         newTicketPrice = 0;
         // Set up the new ticket price based on all of the user's choices thus far
          newTicketPrice = HelperMethodsForScheduleAFlightInterface.SetTicketPrice(originalTicketPrice,
                           usersStartAirport,usersDestinationAirport,UsersSeatChoice,
                           UsersMultipleFlightChoice);
 
         
         // If we don't make a new thread here the interface will freeze instead of 
         // giving us our number change animation
         // We are visually showing the current ticket price change into the new ticket price
         ThreadCreator thread2 = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, newTicketPrice);
         // Generate a new thread
         thread2.generateANewThread();
         
         // Now we need to update our currentTicketPrice to the new ticket price
         currentTicketPrice = newTicketPrice;



  
      } 


   }
   
        private class menuForDestinationAirportsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
        
         
   
         JComboBox cb = (JComboBox)e.getSource();
         usersDestinationAirport = (String)cb.getSelectedItem();
         
         System.out.println("DEBUG: Option: " + usersDestinationAirport + " was just picked in the menu for destination airport");
         
         // In order to use .equals below neither parameter can be null
         if (usersStartAirport == null || usersDestinationAirport == null) {
         // If one or more entries is null than atleast one airport has not been selected yet
            destinationAirportInvalidMsg.setText("**The start and destination airport must be picked**");
            return;
            }
         // Since this check is not working in the Helper methods for schedule a flight lets try it here
         
         if (usersDestinationAirport.equals("Select an airport")) {
            destinationAirportInvalidMsg.setText("**The start and destination airport must be picked**");
            return;
         }
         // The user could have changed the start value to an ivalid option earlier so we need to check for this
         else if (usersStartAirport.equals("Select an airport")) {
         return;
         
         }
         else if (usersDestinationAirport.equals(usersStartAirport)) {
            destinationAirportInvalidMsg.setText("**The start and destination airports can not be the same**");

            return;
         }
         
                  // The airport was a valid entry so lets get rid of any error message
            startAirportInvalidMsg.setText("                                                                    ");
            destinationAirportInvalidMsg.setText("                                                                    ");
                  // We do not want a newTicketPrice to conflict with our result so reset newTicketPrice
         newTicketPrice = 0;
         // Set up the new ticket price based on all of the user's choices thus far
          newTicketPrice = HelperMethodsForScheduleAFlightInterface.SetTicketPrice(originalTicketPrice,
                           usersStartAirport,usersDestinationAirport,UsersSeatChoice,
                           UsersMultipleFlightChoice);
 
         
         // If we don't make a new thread here the interface will freeze instead of 
         // giving us our number change animation
         // We are visually showing the current ticket price change into the new ticket price
         ThreadCreator thread2 = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, newTicketPrice);
         // Generate a new thread
         thread2.generateANewThread();
         
         // Now we need to update our currentTicketPrice to the new ticket price
         currentTicketPrice = newTicketPrice;


  
      } 
      }


        public class menuForSeatChoiceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

         JComboBox cb = (JComboBox)e.getSource();
         UsersSeatChoice = (String)cb.getSelectedItem();
         
         // We do not want a newTicketPrice to conflict with our result so reset newTicketPrice
         newTicketPrice = 0;
         // Set up the new ticket price based on all of the user's choices thus far
          newTicketPrice = HelperMethodsForScheduleAFlightInterface.SetTicketPrice(originalTicketPrice,
                           usersStartAirport,usersDestinationAirport,UsersSeatChoice,
                           UsersMultipleFlightChoice);
 
         
         // If we don't make a new thread here the interface will freeze instead of 
         // giving us our number change animation
         // We are visually showing the current ticket price change into the new ticket price
         ThreadCreator thread2 = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, newTicketPrice);
         // Generate a new thread
         thread2.generateANewThread();
         
         // Now we need to update our currentTicketPrice to the new ticket price
         currentTicketPrice = newTicketPrice;



         
         System.out.println("DEBUG: Option: " + UsersSeatChoice + " was just picked in the menu for seat choices");

  
      } 
      }
      
      private class menuForMultipleFlightActionListener implements ActionListener {
         @Override
         public void actionPerformed(ActionEvent e) {
      
            JComboBox cb = (JComboBox)e.getSource();
            UsersMultipleFlightChoice = (String)cb.getSelectedItem();
            
         // We do not want a newTicketPrice to conflict with our result so reset newTicketPrice
         newTicketPrice = 0;
         // Set up the new ticket price based on all of the user's choices thus far
          newTicketPrice = HelperMethodsForScheduleAFlightInterface.SetTicketPrice(originalTicketPrice,
                           usersStartAirport,usersDestinationAirport,UsersSeatChoice,
                           UsersMultipleFlightChoice);
 
            
         // If we don't make a new thread here the interface will freeze instead of 
         // giving us our number change animation
         // We are visually showing the current ticket price change into the new ticket price
         ThreadCreator thread2 = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, newTicketPrice);
         // Generate a new thread
         thread2.generateANewThread();
         
         // Now we need to update our currentTicketPrice to the new ticket price
         currentTicketPrice = newTicketPrice;

            System.out.println("DEBUG: Option: " + UsersMultipleFlightChoice + " was just picked in the menu for multiple flights");
 
         }
      }
 
        public class menuForFlightTimeActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

         JComboBox cb = (JComboBox)e.getSource();
         UsersFlightTimeChoice = (String)cb.getSelectedItem();
      
         System.out.println("DEBUG: Option: " + UsersFlightTimeChoice + " was just picked in the menu for flight time");
         
         }
      }
      
}