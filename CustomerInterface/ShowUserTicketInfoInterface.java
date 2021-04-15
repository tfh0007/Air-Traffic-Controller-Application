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

public class ShowUserTicketInfoInterface extends JFrame {

// This will be the name of our current user/customer
public static String TheUser;

// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("../Graphics/DataBackground.jpg");

// This class handles reading and writing to the customers.txt file which is the database of customer information
   private CustomerInformationScanner UserInformation;

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen
// ShowNextTicket will ask the ticket database if the user has any more tickets. If they do then display info on that ticket.
// If the user does not have any more tickets tel them that they do not have any more tickets to look through

   private JButton logOut, GoBack, ShowNextTicket, ViewReceiptForTicket, ViewNextTicket;
   
   private JLabel AboutYourTickets, UserHasNoTicketsMsg, UserHasNoTicketsMsg2, TicketNumberMsg, AirportStartMsg, DestinationStartMsg, TravelDate,
                  FinalTicketPrice, noNextTicketMsg;
   
   private String[] infoOnUserTicket = {null,null,null,null,null,null,null,null,null,null,null};
   
   // This has to be declared outside of the try block
   // We also need to make the size otherwise we will run into errors when the try block that gathers the values fails to find a match
   private String[] infoOnNextUserTicket = {null,null,null,null,null,null,null,null,null,null,null};

   
   // This will signify what number ticket we are currently at
   private int ticketNumber;
   
   // This will signify whether or not we have tickets to look at
   private boolean ticketAvaliable, nextTicketAvaliable;
   

public ShowUserTicketInfoInterface(String userName) {

// Initialize our variables
   TheUser = userName;
   ticketNumber = 1;
   ticketAvaliable = false; 


createView(userName);
      
      setTitle("Tickets that " + userName + " has  **Customer Layout** ");
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



      this.setLayout(null);
      
      
      AboutYourTickets = new JLabel();
      AboutYourTickets.setFont(new Font("Arial", Font.PLAIN, 32));
      AboutYourTickets.setForeground (Color.white);
      
      
      UserHasNoTicketsMsg = new JLabel();
      UserHasNoTicketsMsg.setFont(new Font("Arial", Font.PLAIN, 39));
      UserHasNoTicketsMsg.setForeground (Color.white);
      
      UserHasNoTicketsMsg2 = new JLabel();
      UserHasNoTicketsMsg2.setFont(new Font("Arial", Font.PLAIN, 29));
      UserHasNoTicketsMsg2.setForeground (Color.red);
      
      

      
      
      
      TicketNumberMsg = new JLabel();
      TicketNumberMsg.setFont(new Font("SansSerif", Font.PLAIN, 30));
      TicketNumberMsg.setForeground (Color.white);
      
      AirportStartMsg = new JLabel();
      AirportStartMsg.setFont(new Font("SansSerif", Font.PLAIN, 30));
      AirportStartMsg.setForeground (Color.white);

      DestinationStartMsg = new JLabel();
      DestinationStartMsg.setFont(new Font("SansSerif", Font.PLAIN, 30));
      DestinationStartMsg.setForeground (Color.white);
      
      TravelDate = new JLabel();
      TravelDate.setFont(new Font("SansSerif", Font.PLAIN, 30));
      TravelDate.setForeground (Color.white);
      
      
      FinalTicketPrice = new JLabel();
      FinalTicketPrice.setFont(new Font("SansSerif", Font.PLAIN, 30));
      FinalTicketPrice.setForeground (Color.white);
      
      noNextTicketMsg = new JLabel();
      noNextTicketMsg.setFont(new Font("SansSerif", Font.PLAIN, 25));
      noNextTicketMsg.setForeground (Color.red);
      
      //READ TICKET DATA BASE AND LOOK FOR THE FIRST TICKET THAT BELONGS TO THIS USER
      // THEN UPDATE THE JLABELS TO INCLUDE THE INFORMATION
      
      
      try {
         infoOnUserTicket = TicketInformationScanner.ReturnInfoForTicket(ticketNumber, TheUser);
         
         // If the info on our ticket is not null then a valid ticket was found so this ticket is avaliable and we can gather the result
         if (infoOnUserTicket != null) {
         
            ticketAvaliable = true;
            TicketNumberMsg.setText("Info about " + TheUser + "'s ticket " + ticketNumber);
            AirportStartMsg.setText ("The start airport is " + infoOnUserTicket[2]);
            DestinationStartMsg.setText( "The destination airport is " + infoOnUserTicket[3]);
            TravelDate.setText( " The scheduled date and time for this ticket is " + infoOnUserTicket[9] + " at " +  infoOnUserTicket[8]);
            FinalTicketPrice.setText( "The price of this ticket without tax was: $" + infoOnUserTicket[7]);
            
            
            
            // We know the first ticket exists so lets check for a second one
            // We already have info on first ticket so lets incriment to a new ticket that may or may not exist
            ticketNumber++;
            infoOnNextUserTicket = TicketInformationScanner.ReturnInfoForTicket(ticketNumber, TheUser);
            
         // If the info on our next ticket is not null then a valid ticket was found so this ticket is avaliable
         
            if (infoOnNextUserTicket != null) {
               nextTicketAvaliable = true;
               
            }
            
      }
         
         
         
         
      if (ticketAvaliable == false) {
      
      
      UserHasNoTicketsMsg.setText("It appears that " + TheUser + " has not created any tickets for a flight");
      UserHasNoTicketsMsg2.setText("**To create a new ticket go back to " + TheUser + "'s dashboard and click Schedule a new flight**"); 
      
      }
      
      
      
      }
      catch (FileNotFoundException f) {
         System.out.println("An error occured while trying to read the tickets database");
      }

      


                         
      logOut = new JButton("<-- Log Out");
      GoBack = new JButton("Click here to return to " + userName + "'s dashboard");
      ViewReceiptForTicket = new JButton("View the receipt for ticket 1");  
      ViewNextTicket = new JButton("View your next ticket");      

      
           
      // Make the log out button Opaque
      logOut.setOpaque(false);
      logOut.setContentAreaFilled(false);
      logOut.setBorderPainted(false);
      logOut.setFont(new Font("Arial", Font.PLAIN, 30));
      logOut.setForeground (Color.red);
      
           logOut.addActionListener(
            new logOutActionListener());
            
  
      ViewReceiptForTicket.setOpaque(false);
      ViewReceiptForTicket.setContentAreaFilled(true);
      ViewReceiptForTicket.setBorderPainted(true);
      ViewReceiptForTicket.setFont(new Font("Arial", Font.PLAIN, 22));
      ViewReceiptForTicket.setForeground (Color.black);
      
           ViewReceiptForTicket.addActionListener(
            new ViewReceiptForTicketActionListener());
            
      ViewNextTicket.setOpaque(false);
      ViewNextTicket.setContentAreaFilled(true);
      ViewNextTicket.setBorderPainted(true);
      ViewNextTicket.setFont(new Font("Arial", Font.PLAIN, 22));
      ViewNextTicket.setForeground (Color.black);
      
           ViewNextTicket.addActionListener(
            new ViewNextTicketActionListener());


            
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
          AboutYourTickets.setBounds(100, 50, 1500, 200);
          UserHasNoTicketsMsg.setBounds(12, 50, 1500, 200);
          UserHasNoTicketsMsg2.setBounds(12, 110, 1500, 200);
          
          logOut.setBounds(1, 1, 250, 60);
          
          
              TicketNumberMsg.setBounds(50, 100, 1500, 80);
              AirportStartMsg.setBounds(50, 200, 1500, 80);
          DestinationStartMsg.setBounds(50, 300, 1500, 80);
                   TravelDate.setBounds(50, 400, 1500, 80);
             FinalTicketPrice.setBounds(50, 500, 1500, 80);
          //HIDE THIS UNLESS THE USER HAS A CURRENT TICKET TO LOOK AT
          
          if (ticketAvaliable == true) {
           ViewReceiptForTicket.setBounds(100, 625, 340, 60);
           
           }
           
          //HIDE THIS UNLESS THE USER HAS A NEXT TICKET TO LOOK AT
          
          if (nextTicketAvaliable == true) {
          
           ViewNextTicket.setBounds(470, 625, 340, 60);
           
           }
           
           noNextTicketMsg.setBounds(470, 625, 800, 60);
           
           
          GoBack.setBounds(50, 800, 1500, 80);
               
          // This is where we make all of our interface objects visible
          
            this.add(AboutYourTickets);
            this.add(UserHasNoTicketsMsg);
            this.add(UserHasNoTicketsMsg2);

            
            this.add(logOut);
            
            this.add(TicketNumberMsg);
            this.add(AirportStartMsg);
            this.add(DestinationStartMsg);
            this.add(FinalTicketPrice);
            this.add(TravelDate);
            this.add(ViewReceiptForTicket);
            this.add(ViewNextTicket);
            this.add(noNextTicketMsg);
            
            this.add(GoBack);
              
   
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
   
   private class ViewReceiptForTicketActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG: View Receipt for Ticket button pressed");
      
      // Lets make sure we actually have a ticket to look at
      
          if ((ticketAvaliable == true) && (infoOnUserTicket != null)) {
          
      String usersStartAirport = infoOnUserTicket[2];
      String usersDestinationAirport = infoOnUserTicket[3];
      String UsersSeatChoice = infoOnUserTicket[4];
      String UsersMultipleFlightChoice = infoOnUserTicket[5];
      // We have to convert the string values into doubles so we will parse the strings to double values
      double originalticketPrice = Double.parseDouble(infoOnUserTicket[6]);
      double finalticketPrice = Double.parseDouble(infoOnUserTicket[7]);
      
      
      String UsersFlightTimeChoice = infoOnUserTicket[8];
      String completeUserFlightDate = infoOnUserTicket[9];
      String UsersFlightTimeChoice2 = infoOnUserTicket[10];
      String completeUserFlightDate2 = infoOnUserTicket[11];
// All we need to do is call the FinalizeTicketInterface with the information we gathered about our ticket
            FinalizeTicketInterface frame7;
            
            // Since we have double values that need to be integers we will have to cast the doubles into integers
            // The false at the end means this ticket should not be creatable since it already exists
            frame7 = new FinalizeTicketInterface(TheUser,usersStartAirport,usersDestinationAirport,UsersSeatChoice, 
                     UsersMultipleFlightChoice,(int)originalticketPrice,(int)finalticketPrice,UsersFlightTimeChoice,completeUserFlightDate,
                     UsersFlightTimeChoice2, completeUserFlightDate2, false);
                     
          }
      
      }
   }
   
   private class ViewNextTicketActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      

      
      
      
      
         System.out.println("DEBUG: View the next ticket button pressed");
      
      
//WE NEED TO CONSULT THE TICKET DATABASE WITH OUR CURRENT TICKET NUMBER + 1 TO RETRIEVE THE NEEDED INFORMATION HERE
// WE NEED TO USE A FUNCTION WITH A TICKET NUMBER AND USERNAME AS AN INPUT. THE FUNCTION WILL LOOK FOR TICKET NUMBER X BASED ON A LINEAR SCAN OF THE DATABASE
// IF WE CAN NOT FIND A NEW TICKET THEN HIDE THE VIEW NEXT TICKET BUTTON AND TELL THE USER THAT NO NEW TICKETS ARE AVALIABLE TO LOOK AT

         // Everything we knew about the tickets is gone now since we are pointing somewhere different
      TicketNumberMsg.setText("                                                                                        ");
      AirportStartMsg.setText ("                                                                                    ");
      DestinationStartMsg.setText( "                                                                                    ");
            TravelDate.setText( "                                                                                        ");
      FinalTicketPrice.setText( "                                                                                          ");
      ticketAvaliable = false;
      nextTicketAvaliable = false;
      // We are pointing to the next ticket so update the number on the ViewReceiptForTicket button;
      ViewReceiptForTicket.setText("View the receipt for ticket " + ticketNumber);  
      

      try {
         infoOnUserTicket = infoOnNextUserTicket;
         
         
         // If the info on our ticket is not null then a valid ticket was found so this ticket is avaliable and we can gather the result
         if (infoOnUserTicket != null) {
         
            ticketAvaliable = true;
            TicketNumberMsg.setText("Info about " + TheUser + "'s ticket " + ticketNumber);
            AirportStartMsg.setText ("The start airport is " + infoOnUserTicket[2]);
            DestinationStartMsg.setText( "The destination airport is " + infoOnUserTicket[3]);
            TravelDate.setText( " The scheduled date and time for this ticket is " + infoOnUserTicket[9] + " at " +  infoOnUserTicket[8]);
            FinalTicketPrice.setText( "The price of this ticket without tax was: $" + infoOnUserTicket[7]);
            
            
            
            // We know this ticket exists so lets check for the next one
            // We already have info on this ticket so lets incriment to a new ticket that may or may not exist
            ticketNumber++;
            
            infoOnNextUserTicket = TicketInformationScanner.ReturnInfoForTicket(ticketNumber, TheUser);
            
            // We know there is a next ticket so update that there is a next ticket
            if (infoOnNextUserTicket != null) {
               nextTicketAvaliable = true;
            
            }
            
            
            
         }
            
      }
      
            
            
      catch (FileNotFoundException f) {
         System.out.println("An error occured while trying to read the tickets database");
      }
            
            
         // If the info on our next ticket is not null then a valid ticket was found so this ticket is avaliable
         
            
      


         if (nextTicketAvaliable == false) {
         
            // Make the View New Ticket button invisible because it can't do anything else
            ViewNextTicket.setBounds(5000, 625, 340, 60);
            
            noNextTicketMsg.setText("**No more tickets in your name could be found**"); 
         
         }
         
      
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
  
 } 