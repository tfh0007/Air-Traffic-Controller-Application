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
private String usersStartAirport, usersDestinationAirport, UsersSeatChoice, UsersMultipleFlightChoice;

   // A list of all of our airports that can be used as arrival and destination locations
   // This array is causing an unsafe warning
private String[] airports = {};

   // A list of all of our seat choices that can be used by the customer
private String[] seatChoice = { "First Class", "Business Class", "Premium Economy Class", "Economy Class" };

   // A list of all of our multiple flight choices
private String[] multipleFlightChoice = { "Round trip ticket","One way ticket"};



// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("../Graphics/simpleBrownBackgroundLarge.jpg");

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut, GoBack, PrintOutYourTicket;
   
   private JLabel initialAirportMsg, FinalAirportMsg, startAirportInvalidMsg, destinationAirportInvalidMsg, SeatPreference, PurchasePriceMsg, PriceSoFarMsg, TicketResultsMsg, TicketNumberMsg, TicketStartingLocation, TicketEndingLocationMsg,TimeOfFlightMsg, TicketPurchasePriceMsg;
   
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField creditCardNumber;
   
   private JComboBox menuForStartAirports, menuForDestinationAirports, menuForSeatChoice, menuForMultipleFlight;




public ScheduleAFlightInterface(String userName) {


// Initialize our variables
   TheUser = userName;

   createView(userName);

// by default UsersSeatChoice will be "Economy Class"
   UsersSeatChoice = "Economy Class";
// by default UsersMultipleFlightChoice will One way ticket
   UsersMultipleFlightChoice = "One way ticket";
   


      
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
      
      PriceSoFarMsg = new JLabel("$223.00   " );
      PriceSoFarMsg.setFont(new Font("Arial", Font.PLAIN, 50));
      PriceSoFarMsg.setForeground (Color.gray);
      
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

            

      
      //Now lets make the right side of the screen
      TicketResultsMsg = new JLabel("Please tell us if you want a round trip ticket" );
      TicketResultsMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      TicketResultsMsg.setForeground (Color.white);

      
      

      
                         
      logOut = new JButton("<-- Log Out");
      GoBack = new JButton("Click here to return to " + userName + "'s dashboard");
      PrintOutYourTicket = new JButton("Print Your Ticket");
     
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
            
      
         
           
           
      
      //Set the visible length of our Input Boxes
   //    usernameInput.setPreferredSize(new Dimension(400, 40));
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
          
          //Our labels
          initialAirportMsg.setBounds(50, 30, 1500, 200);
          startAirportInvalidMsg.setBounds(50, 170, 1500, 200);
          
          FinalAirportMsg.setBounds(50, 200, 1500, 200);
          destinationAirportInvalidMsg.setBounds(50, 340, 1500, 200);
          
          SeatPreference.setBounds(50, 370, 1500, 200);
          PurchasePriceMsg.setBounds(50, 540, 1500, 200);
          PriceSoFarMsg.setBounds(50, 625, 1500, 200);
          
            //Lets do right side
          TicketResultsMsg.setBounds(880, 30, 1500, 200);
          
          //Our buttons
          logOut.setBounds(1, 1, 250, 60);
          PrintOutYourTicket.setBounds(1100, 600, 200, 60);
          GoBack.setBounds(50, 800, 1500, 80);
          
          //Our menues
          menuForStartAirports.setBounds(50, 190, 600, 50);
          menuForDestinationAirports.setBounds(50, 360, 600, 50);
          menuForSeatChoice.setBounds(50, 530, 600, 50);
          menuForMultipleFlight.setBounds(880, 190, 600, 50);

          //Our text fields
          
          
               
          // This is where we make all of our interface objects visible
          
            this.add(initialAirportMsg);
            this.add(FinalAirportMsg);
            this.add(SeatPreference);
            this.add(PurchasePriceMsg);
            this.add(PriceSoFarMsg);
            this.add(startAirportInvalidMsg);
            this.add(destinationAirportInvalidMsg);

            
            // Now lets do our menues
            this.add(menuForStartAirports);
            this.add(menuForDestinationAirports);
            this.add(menuForSeatChoice);
            this.add(menuForMultipleFlight);

            
            //Now lets do right side
            this.add(TicketResultsMsg);
            
            this.add(logOut);
            this.add(GoBack);
            this.add(PrintOutYourTicket);
              
   
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
      
}