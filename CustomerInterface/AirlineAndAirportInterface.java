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

public class AirlineAndAirportInterface extends JFrame {

// Grab a background image
Image img = Toolkit.getDefaultToolkit().getImage("airplane_wallpaper.jpg");

// This class handles reading and writing to the customers.txt file which is the database of customer information
   private CustomerInformationScanner UserInformation;

// logOut represents the logOut button that is pushed when the user wants to return to the log in screen

   private JButton logOut;
   
   private JLabel WelcomeUserMsg;
   
   // These represent our text boxes that the user will have to fill in to continue
   private JTextField usernameInput;
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField invisiblePasswordInput, invisiblePasswordInput2;

public AirlineAndAirportInterface(String userName) {

createView(userName);
      
      setTitle(userName + "'s Main Dashboard");
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
      
            
      
      WelcomeUserMsg = new JLabel("Welcome ");
      WelcomeUserMsg.setFont(new Font("Arial", Font.PLAIN, 30));

                    
      logOut = new JButton("<-- Log Out");
      
           logOut.addActionListener(
            new logOutActionListener());

      
      usernameInput = new JTextField();
      invisiblePasswordInput = new JPasswordField();
      
           
      
      //Set the visible length of our Input Boxes
   //    usernameInput.setPreferredSize(new Dimension(400, 40));
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
          WelcomeUserMsg.setBounds(200, 70, 300, 20);
          logOut.setBounds(200, 30, 300, 20);
         
          // This is where we make all of our interface objects visible
          
            this.add(WelcomeUserMsg);
            this.add(logOut);
      


      
   
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


}