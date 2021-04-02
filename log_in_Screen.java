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
   
  
 
 
   public log_in_Screen() {
      createView();
      
      setTitle("Customer Log in");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      pack();
      //set display size
      setSize(600, 600);
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
      submit = new JButton("Submit");
      createNewUser = new JButton("Create New User");
             
          // This add a drop down menu for selecting the Lexicon to load
      panel.add(submit);
      panel.add(createNewUser);
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

