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
import java.util.Random;

//External Sources:
//Used https://www.tutorialspoint.com/how-to-add-background-image-to-jframe-in-java to figure out how to have a background image

public class AirlineAndAirportInterface extends JFrame {

// This will be the name of our current user/customer
public static String TheUser;

// Pick a random image for our avaliable backgrounds
Random randomNumber = new Random();
int imageToPick = randomNumber.nextInt(2);





// This class handles reading and writing to the customers.txt file which is the database of customer information
   private CustomerInformationScanner UserInformation;

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut, ScheduleAFlight, ReviewUserInfo, CheckStatusOfExistingFlight;
   
   private JLabel WelcomeUserMsg, SelectActionMsg;
   
   // These represent our text boxes that the user will have to fill in to continue
   private JTextField usernameInput;
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField invisiblePasswordInput, invisiblePasswordInput2;
   
   
         // Grab a background image
      
   Image img = Toolkit.getDefaultToolkit().getImage("../Graphics/airplane_Day.jpg");;


public AirlineAndAirportInterface(String userName) {


TheUser = userName;
createView(userName);
      
      setTitle(userName + "'s Dashboard **Customer Layout** ");
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
      

// Lets pick a random image based on our random number called imageToPick
   switch (imageToPick) {

      case 0:
      img = Toolkit.getDefaultToolkit().getImage("../Graphics/airplaneNight.jpg");
      break;

      case 1:
      img = Toolkit.getDefaultToolkit().getImage("../Graphics/airplane_wallpaper.jpg");
      break;
    //   case 2:
//       img = Toolkit.getDefaultToolkit().getImage("../Graphics/airplane_vibrant.jpg");
//       break;
//       case 3:
//       img = Toolkit.getDefaultToolkit().getImage("../Graphics/airplane_wallpaper.jpg");
//       break;

   }
      
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
      
      


      //panel.setBackground(Color.GRAY);
      this.setLayout(null);
      
            
      
      WelcomeUserMsg = new JLabel("Welcome " + userName + ". Hope you are having a wonderful day");
      WelcomeUserMsg.setFont(new Font("Arial", Font.PLAIN, 43));
      WelcomeUserMsg.setForeground (Color.white);
      WelcomeUserMsg.setBackground(Color.blue);



      SelectActionMsg = new JLabel("Please click an action below to get started or click log out to exit");
      SelectActionMsg.setFont(new Font("Arial", Font.PLAIN, 35));
      SelectActionMsg.setForeground (Color.white);

                    
      logOut = new JButton("<-- Log Out");
      ScheduleAFlight = new JButton("Click here to schedule a new flight");
      ReviewUserInfo = new JButton("Click here to view your user profile");
      CheckStatusOfExistingFlight = new JButton("Click here to check the status on any existing flights");
      
      // Make the log out button Opaque
      logOut.setOpaque(false);
      logOut.setContentAreaFilled(false);
      logOut.setBorderPainted(false);
      logOut.setFont(new Font("Arial", Font.PLAIN, 30));
      logOut.setForeground (Color.red);
      
           logOut.addActionListener(
            new logOutActionListener());
            
      ScheduleAFlight.setOpaque(false);
      ScheduleAFlight.setContentAreaFilled(false);
      ScheduleAFlight.setBorderPainted(true);
      ScheduleAFlight.setFont(new Font("SansSerif", Font.PLAIN, 50));
      ScheduleAFlight.setForeground (Color.white);
         
           ScheduleAFlight.addActionListener(
            new ScheduleAFlightActionListener());
            
      
      ReviewUserInfo.setOpaque(false);
      ReviewUserInfo.setContentAreaFilled(false);
      ReviewUserInfo.setBorderPainted(true);
      ReviewUserInfo.setFont(new Font("SansSerif", Font.PLAIN, 50));
      ReviewUserInfo.setForeground (Color.white);
      
         ReviewUserInfo.addActionListener(
            new ReviewUserInfoActionListener());
      
      CheckStatusOfExistingFlight.setOpaque(false);
      CheckStatusOfExistingFlight.setContentAreaFilled(false);
      CheckStatusOfExistingFlight.setBorderPainted(true);
      CheckStatusOfExistingFlight.setFont(new Font("SansSerif", Font.PLAIN, 50));
      CheckStatusOfExistingFlight.setForeground (Color.white);

         CheckStatusOfExistingFlight.addActionListener(
            new CheckStatusOfExistingFlightActionListener());
      
           
           
      
      //Set the visible length of our Input Boxes
   //    usernameInput.setPreferredSize(new Dimension(400, 40));
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
          WelcomeUserMsg.setBounds(100, 50, 1500, 200);
          SelectActionMsg.setBounds(200, 190, 1500, 150);
          
          logOut.setBounds(1, 1, 250, 60);
          
          ScheduleAFlight.setBounds(50, 700, 1500, 80);
          
          ReviewUserInfo.setBounds(50, 780, 1500, 80);
          
          CheckStatusOfExistingFlight.setBounds(50, 860, 1500, 80);
         
          // This is where we make all of our interface objects visible
          
            this.add(WelcomeUserMsg);
            this.add(SelectActionMsg);
            
            this.add(logOut);
            this.add(ScheduleAFlight);
            this.add(ReviewUserInfo);

            this.add(CheckStatusOfExistingFlight);
      
   
   }



   
   private class logOutActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG: goBackToLogInScreen button was pressed");
      
      // Hide the create new user interface window
      setVisible(false);
      
      // Destroy the create new user interface window. We can create a new one if needed later
      dispose();
      log_in_Screen frame1;
      frame1 = new log_in_Screen();
      
      }
   }
   
   
   private class ScheduleAFlightActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG:  Schedule A Flight button was pressed");


      }
  }
  
   private class ReviewUserInfoActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      // Hide the create new user interface window
      setVisible(false);
      
      // Destroy the AirlineAndAirport interface window. We can create a new one if needed later
      dispose();
      ShowUserInfoInterface frame4;
      frame4 = new ShowUserInfoInterface(TheUser);

      
      System.out.println("DEBUG:  Review User Info button was pressed");


      }
  }
  
   private class CheckStatusOfExistingFlightActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG:   Check Status Of Existing Flight button was pressed");


      }
  }


}