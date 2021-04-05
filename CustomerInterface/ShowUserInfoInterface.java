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

public class ShowUserInfoInterface extends JFrame {

// This will be the name of our current user/customer
public static String TheUser;

// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("DataBackground.jpg");

// This class handles reading and writing to the customers.txt file which is the database of customer information
   private CustomerInformationScanner UserInformation;

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut, GoBack;
   
   private JLabel AboutYourAccontMsg, FullUserName, FullPassword, FullEmail, AccountType;
   
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField invisiblePasswordInput, invisiblePasswordInput2;

public ShowUserInfoInterface(String userName) {

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

// We do not need to getContentPane() because we already set the content pane
   //   getContentPane().add(this);
      
      


      //panel.setBackground(Color.GRAY);
      this.setLayout(null);
      
      // This array will contain information including username, password, and email of our signed in user
      String []customerInfo = {"","",""};
      
      try { 
      // This function will provide us the username, password, and email of our signed in user     
         customerInfo = CustomerInformationScanner.ReturnInfoOnCustomer(TheUser);
         }
         
      catch (FileNotFoundException d) {
      System.out.println("An error occured while trying to read the customers database");
      
      }
      
      AboutYourAccontMsg = new JLabel("Here is all of the information that pertains to your user account" );
      AboutYourAccontMsg.setFont(new Font("Arial", Font.PLAIN, 40));
      AboutYourAccontMsg.setForeground (Color.white);
      
      FullUserName = new JLabel("Your Full User Name: " + customerInfo[0] );
      FullUserName.setFont(new Font("SansSerif", Font.PLAIN, 35));
      FullUserName.setForeground (Color.white);
      
      FullPassword = new JLabel("Your Account Password: " + customerInfo[1]);
      FullPassword.setFont(new Font("SansSerif", Font.PLAIN, 35));
      FullPassword.setForeground (Color.white);

      FullEmail = new JLabel("The Email Address provided to us: " + customerInfo[2]);
      FullEmail.setFont(new Font("SansSerif", Font.PLAIN, 35));
      FullEmail.setForeground (Color.white);
      
      AccountType = new JLabel("Account Type: Your account is classified as Customer");
      AccountType.setFont(new Font("SansSerif", Font.PLAIN, 35));
      AccountType.setForeground (Color.white);

                         
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
          AboutYourAccontMsg.setBounds(100, 50, 1500, 200);
          
          logOut.setBounds(1, 1, 250, 60);
          
          
          FullUserName.setBounds(50, 250, 1500, 80);
          FullPassword.setBounds(50, 350, 1500, 80);
          FullEmail.setBounds(50, 450, 1500, 80);
          AccountType.setBounds(50, 550, 1500, 80);

          

          
          GoBack.setBounds(50, 800, 1500, 80);
               
          // This is where we make all of our interface objects visible
          
            this.add(AboutYourAccontMsg);

            
            this.add(logOut);
            
            this.add(FullUserName);
            this.add(FullPassword);
            this.add(FullEmail);
            this.add(AccountType);
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