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
private int startAirportCount = 0;
private String usersStartAirport;

   // A list of all of our airports that can be used as arrival and destination locations
   // This array is causing an unsafe warning
private String[] airports = { "Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7","Airport1", "Airport2", "Airport3",
      "Airport4", "Airport5", "Airport6", "Airport7" };



// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("../Graphics/simpleBrownBackgroundLarge.jpg");

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut, GoBack, PrintOutYourTicket;
   
   private JLabel initialAirportMsg, FinalAirportMsg, SeatPreference, PurchasePriceMsg, PriceSoFarMsg, InvalidSeatChoice, InvalidSeatNumber, TicketResultsMsg, TicketNumberMsg, TicketStartingLocation, TicketEndingLocationMsg,TimeOfFlightMsg, TicketPurchasePriceMsg;
   
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField creditCardNumber;
   
   private JComboBox menuForStartAirports;




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
      
            

      
      //Now lets make the right side of the screen
      TicketResultsMsg = new JLabel("Here is you flight thus far" );
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
          TicketResultsMsg.setBounds(1000, 30, 1500, 200);
          
          //Our buttons
          logOut.setBounds(1, 1, 250, 60);
          PrintOutYourTicket.setBounds(1100, 600, 200, 60);
          GoBack.setBounds(50, 800, 1500, 80);
          
          //Our menues
          menuForStartAirports.setBounds(50, 150, 400, 200);
          
          
          //Our text fields
          
          
               
          // This is where we make all of our interface objects visible
          
            this.add(initialAirportMsg);
            this.add(FinalAirportMsg);
            this.add(SeatPreference);
            this.add(PurchasePriceMsg);
            this.add(PriceSoFarMsg);
            
            // Now lets do our menues
            this.add(menuForStartAirports);

            
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
}