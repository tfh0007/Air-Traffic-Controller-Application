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

   private JButton logOut, GoBack, ShowNextTicket, ViewReceiptForTicket;
   
   private JLabel AboutYourTickets, TicketNumberMsg, AirportStartMsg, DestinationStartMsg, FinalTicketPrice;
   
   // This will signify how many tickets we have looked at including what as what number ticket we are on currently
   private String ticketNumber;
   

public ShowUserTicketInfoInterface(String userName) {

TheUser = userName;

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

// We do not need to getContentPane() because we already set the content pane
   //   getContentPane().add(this);
      
      
                     // Wait a little bit to ensure background is made
//       try {
//       Thread.sleep(300); 
//       }
//       catch (InterruptedException f) {
//       
//       }


      //panel.setBackground(Color.GRAY);
      this.setLayout(null);
      
      
      
      AboutYourTickets = new JLabel("Here is all of the information that pertains to your previously created tickets" );
      AboutYourTickets.setFont(new Font("Arial", Font.PLAIN, 40));
      AboutYourTickets.setForeground (Color.white);
      
      
      
      TicketNumberMsg = new JLabel();
      TicketNumberMsg.setFont(new Font("SansSerif", Font.PLAIN, 35));
      TicketNumberMsg.setForeground (Color.white);
      
      AirportStartMsg = new JLabel();
      AirportStartMsg.setFont(new Font("SansSerif", Font.PLAIN, 35));
      AirportStartMsg.setForeground (Color.white);

      DestinationStartMsg = new JLabel();
      DestinationStartMsg.setFont(new Font("SansSerif", Font.PLAIN, 35));
      DestinationStartMsg.setForeground (Color.white);
      
      FinalTicketPrice = new JLabel();
      FinalTicketPrice.setFont(new Font("SansSerif", Font.PLAIN, 35));
      FinalTicketPrice.setForeground (Color.white);
      
      
      //READ TICKET DATA BASE AND LOOK FOR THE FIRST TICKET THAT BELONGS TO THIS USER
      // THEN UPDATE THE JLABELS TO INCLUDE THIS INFORMATION

                         
      logOut = new JButton("<-- Log Out");
      GoBack = new JButton("Click here to return to " + userName + "'s dashboard");
           
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
          AboutYourTickets.setBounds(100, 50, 1500, 200);
          
          logOut.setBounds(1, 1, 250, 60);
          
          
          TicketNumberMsg.setBounds(50, 250, 1500, 80);
          AirportStartMsg.setBounds(50, 350, 1500, 80);
          DestinationStartMsg.setBounds(50, 450, 1500, 80);
          FinalTicketPrice.setBounds(50, 550, 1500, 80);

          

          
          GoBack.setBounds(50, 800, 1500, 80);
               
          // This is where we make all of our interface objects visible
          
            this.add(AboutYourTickets);

            
            this.add(logOut);
            
            this.add(TicketNumberMsg);
            this.add(AirportStartMsg);
            this.add(DestinationStartMsg);
            this.add(FinalTicketPrice);
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