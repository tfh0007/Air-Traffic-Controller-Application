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
private int startAirportCount = 0, TicketPrice=223, currentTicketPrice=223;
private String usersStartAirport, usersDestinationAirport, UsersSeatChoice;

   // A list of all of our airports that can be used as arrival and destination locations
   // This array is causing an unsafe warning
private String[] airports = { "Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7" };

   // A list of all of our seat choices that can be used by the customer
private String[] seatChoice = { "Please select a seat Preference", "First-Class", "Business Class", "Premium Economy Class", "Economy Class" };


// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("../Graphics/simpleBrownBackgroundLarge.jpg");

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut, GoBack, PrintOutYourTicket;
   
   private JLabel initialAirportMsg, FinalAirportMsg, SeatPreference, PurchasePriceMsg, PriceSoFarMsg, InvalidSeatChoice, InvalidSeatNumber, TicketResultsMsg, TicketNumberMsg, TicketStartingLocation, TicketEndingLocationMsg,TimeOfFlightMsg, TicketPurchasePriceMsg;
   
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField creditCardNumber;
   
   private JComboBox menuForStartAirports, menuForDestinationAirports, menuForSeatChoice;




public ScheduleAFlightInterface(String userName) {

TheUser = userName;

createView(userName);
      
      setTitle("Info about " + userName + "  **Customer Layout** ");
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
      
      FinalAirportMsg = new JLabel("Please select your destination airport location" );
      FinalAirportMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      FinalAirportMsg.setForeground (Color.white);

      SeatPreference = new JLabel("Please select your seat preference" );
      SeatPreference.setFont(new Font("Arial", Font.PLAIN, 30));
      SeatPreference.setForeground (Color.white);
      
      PurchasePriceMsg = new JLabel("Cost of your flight so far" );
      PurchasePriceMsg.setFont(new Font("Arial", Font.PLAIN, 30));
      PurchasePriceMsg.setForeground (Color.white);
      
      PriceSoFarMsg = new JLabel("$0.00   " );
      PriceSoFarMsg.setFont(new Font("Arial", Font.PLAIN, 50));
      PriceSoFarMsg.setForeground (Color.gray);
      
      // Lets make our menues
      //This causes an unsafe warning somehow 
      menuForStartAirports = new JComboBox(airports);
      menuForStartAirports.setSelectedIndex(0);
      menuForStartAirports.setFont(new Font("Arial", Font.PLAIN, 30));

      menuForStartAirports.addActionListener(
         new menuForStartAirportsActionListener());
         
      menuForDestinationAirports = new JComboBox(airports);
      menuForDestinationAirports.setSelectedIndex(0);
      menuForDestinationAirports.setFont(new Font("Arial", Font.PLAIN, 30));

      menuForDestinationAirports.addActionListener(
         new menuForDestinationAirportsActionListener());
         
         
            menuForSeatChoice = new JComboBox(seatChoice);
      menuForSeatChoice.setSelectedIndex(0);
      menuForSeatChoice.setFont(new Font("Arial", Font.PLAIN, 30));

      menuForSeatChoice.addActionListener(
         new menuForSeatChoiceActionListener());

      
            

      
      //Now lets make the right side of the screen
      TicketResultsMsg = new JLabel("Here is your round trip flight thus far" );
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
          FinalAirportMsg.setBounds(50, 200, 1500, 200);
          SeatPreference.setBounds(50, 370, 1500, 200);
          PurchasePriceMsg.setBounds(50, 540, 1500, 200);
          PriceSoFarMsg.setBounds(50, 625, 1500, 200);
          
            //Lets do right side
          TicketResultsMsg.setBounds(900, 30, 1500, 200);
          
          //Our buttons
          logOut.setBounds(1, 1, 250, 60);
          PrintOutYourTicket.setBounds(1100, 600, 200, 60);
          GoBack.setBounds(50, 800, 1500, 80);
          
          //Our menues
          menuForStartAirports.setBounds(50, 190, 600, 50);
          menuForDestinationAirports.setBounds(50, 360, 600, 50);
          menuForSeatChoice.setBounds(50, 530, 600, 50);

          //Our text fields
          
          
               
          // This is where we make all of our interface objects visible
          
            this.add(initialAirportMsg);
            this.add(FinalAirportMsg);
            this.add(SeatPreference);
            this.add(PurchasePriceMsg);
            this.add(PriceSoFarMsg);
            
            // Now lets do our menues
            this.add(menuForStartAirports);
            this.add(menuForDestinationAirports);
            this.add(menuForSeatChoice);

            
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

  
      } 


   }
   
        private class menuForDestinationAirportsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

         JComboBox cb = (JComboBox)e.getSource();
         usersDestinationAirport = (String)cb.getSelectedItem();

         System.out.println("DEBUG: Option: " + usersDestinationAirport + " was just picked in the menu for destination airport");

  
      } 
      }


        public class menuForSeatChoiceActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

         JComboBox cb = (JComboBox)e.getSource();
         UsersSeatChoice = (String)cb.getSelectedItem();
         
         // If we don't make a new thread here the interface will freeze instead of 
         // giving us our number change animation
         
         
         
         
         
         
         // Increase the prices based on seat upgrade
            if (UsersSeatChoice.equals("First-Class")) {
         
            // User wants first class so increase price x4
            // Change later to be cooler
            
               ThreadCreator thread = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, TicketPrice*4);
               // Generate a new thread
               thread.generateANewThread();
               // Since we have displayed the change update our current Ticket Price
               currentTicketPrice = TicketPrice*4;

            }
            
            else if (UsersSeatChoice.equals("Business Class")) {
         
            
            // User wants Business class so increase price x3
            // Change later to be cooler
            
               ThreadCreator thread = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, TicketPrice*3);
               // Generate a new thread
               thread.generateANewThread();
               // Since we have displayed the change update our current Ticket Price
               currentTicketPrice = TicketPrice*3;

               
            }
         
            else if (UsersSeatChoice.equals("Premium Economy Class")) {
         
            
            // User wants Business class so increase price x2
            // Change later to be cooler
               ThreadCreator thread = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, TicketPrice*2);
               // Generate a new thread
               thread.generateANewThread();
               // Since we have displayed the change update our current Ticket Price
               currentTicketPrice = TicketPrice*2;
               
            }
         
            else if (UsersSeatChoice.equals("Economy Class")) {
         
            
            // User wants Economy class so price is the default
            // Change later to be cooler
            // we need to use casting to ensure that we only get integer values
               ThreadCreator thread2 = new ThreadCreator(PriceSoFarMsg, currentTicketPrice, TicketPrice);
               // Generate a new thread
               thread2.generateANewThread();
           // Since we have displayed the change update our current Ticket Price
               currentTicketPrice = TicketPrice;

            }
         

               
         
         
         

         System.out.println("DEBUG: Option: " + UsersSeatChoice + " was just picked in the menu for seat choices");

  
      } 
      }
      
      
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

   
}