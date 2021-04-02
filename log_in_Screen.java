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

/* This class will represent a log in screen that allows
 * creation of new users, in addition to allowing currently made users
 * access to their accounts. 
 * User information will be stored in a text file within this repository.
 * All user generated passwords will be encrypted with XOR encryption.
 * When an individual creates a new account their name and information will be added to
 * a users text file.
*/ 
public class log_in_Screen extends JFrame{


// submit represents the submit button that is pushed after filling out the needed fields
// createNewUser is the New User button that will allow an individual to create a new account
   private JButton submit, createNewUser;
   
   private JLabel usernameMsg, passwordMsg, NoAccountYetMsg, AlreadyHaveAnAccount;
   
   // These represent our text boxes that the user will have to fill in to continue
   private JTextField usernameInput, passwordInput;
 
 
   public log_in_Screen() {
      createView();
      
      setTitle("Customer Log in");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //set display frame size (x-axis, y-axis)
      setSize(600, 450);
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      //Disable resize
      setResizable(false);
      
   }
   
  // Create the interface and add everything to it
   private void createView() {
   
   
      JPanel panel = new JPanel();
      getContentPane().add(panel);
      panel.setBackground(Color.WHITE);
      panel.setLayout(null);
      AlreadyHaveAnAccount = new JLabel("If you already have an account you can log in here\n\n");
      NoAccountYetMsg = new JLabel("Alternatively, click Create New User to sign up for a new account\n\n");
      
      
      usernameMsg = new JLabel("User Name");
      passwordMsg = new JLabel("Password");
      
      
      submit = new JButton("Submit");
      createNewUser = new JButton("Create New User");
      
      usernameInput = new JTextField();
      passwordInput = new JTextField();
      
      
      
      //Set the visible length of our Input Boxes
      usernameInput.setPreferredSize(new Dimension(200, 40));
      passwordInput.setPreferredSize(new Dimension(200, 40));
      
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
      AlreadyHaveAnAccount.setBounds(105, 0, 400, 20);
      usernameMsg.setBounds(200, 50, 300, 20);
      usernameInput.setBounds(200, 70, 200, 50);
      
      passwordMsg.setBounds(200, 130, 300, 20);
      passwordInput.setBounds(200, 150, 200, 50);
      submit.setBounds(255, 215, 90, 30);
      
      
      NoAccountYetMsg.setBounds(65, 300, 600, 50);
      createNewUser.setBounds(215, 345, 170, 30);
             
          // This is where we make all of our interface objects visible
          
      panel.add(AlreadyHaveAnAccount);
      panel.add(usernameMsg);
      panel.add(usernameInput);
      
      
      panel.add(passwordMsg);
      panel.add(passwordInput);
      
      panel.add(submit);
      panel.add(createNewUser);
      panel.add(NoAccountYetMsg);
      
   
   }
 
 
 // Signal for the interface to run  
   public static void main (String[] args) {
         
         // Opens a new Instance of JFrame
      SwingUtilities.invokeLater(
         new Runnable() {
            @Override
            public void run() {
               new log_in_Screen().setVisible(true);
            }
         
         
         });
   }
}

