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


public class CreateNewUserInterface extends JFrame {


// This class handles reading and writing to the customers.txt file which is the database of customer information
   private CustomerInformationScanner UserInformation;

// confirm represents the confirm button that is pushed after filling out the needed fields
// goBackToLogInScreen is the Go Back To Log In Screen that will close the create new user interface and open a new instance of the log in screen
   private JButton confirm, goBackToLogInScreen;
   
   private JLabel usernameMsg, passwordMsg, password2Msg, emailMsg, PasswordForAccountMsg, UserNameForAccountMsg, EmailForAccountMsg, IncorrectCredentials, CorrectCredentials;
   
   // These represent our text boxes that the user will have to fill in to continue
   private JTextField usernameInput, passwordInput, passwordInput2, emailInput;
   
   // These represent our hidden text boxes that the user will have to fill in to continue
   // Note: JPasswordField uses a depreciated API but this should not be a serious concern
   private JPasswordField invisiblePasswordInput, invisiblePasswordInput2;

public CreateNewUserInterface() {

createView();
      
      setTitle("User Creation Setup");
      //Make window exit application on close
      setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      //set display frame size (x-axis, y-axis)
      setSize(800, 600);
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
      
      UserNameForAccountMsg = new JLabel("You need a User Name for your account\n\n");
      PasswordForAccountMsg = new JLabel("You need a Password for your account\n\n");
      EmailForAccountMsg = new JLabel("You need a valid Email for your account\n\n");
      IncorrectCredentials = new JLabel();
      IncorrectCredentials.setForeground (Color.red);
      CorrectCredentials = new JLabel();
      CorrectCredentials.setForeground (Color.green);
      
      
      usernameMsg = new JLabel("User Name");
      passwordMsg = new JLabel("Password");
      password2Msg = new JLabel("Confirm Password");
      
      
      emailMsg = new JLabel("Email Address");
      
      confirm = new JButton("Confirm");
      
      confirm.addActionListener(
         new confirmActionListener());
         
      goBackToLogInScreen = new JButton("<-- Return to the Log In Screen");
      
           goBackToLogInScreen.addActionListener(
            new goBackToLogInScreenActionListener());

      
      usernameInput = new JTextField();
      invisiblePasswordInput = new JPasswordField();
      
      // Make the password show astrecisk symbols when typed through the interface
      invisiblePasswordInput.setEchoChar('*');
      
      
      invisiblePasswordInput2 = new JPasswordField();
      // Make the password2 show astrecisk symbols when typed through the interface
      invisiblePasswordInput2.setEchoChar('*');
      
      emailInput = new JTextField();
      
      
      //Set the visible length of our Input Boxes
      usernameInput.setPreferredSize(new Dimension(400, 40));
      invisiblePasswordInput.setPreferredSize(new Dimension(200, 40));
      invisiblePasswordInput2.setPreferredSize(new Dimension(200, 40));
      
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel
      UserNameForAccountMsg.setBounds(105, 0, 400, 20);
      usernameMsg.setBounds(200, 50, 300, 20);
      usernameInput.setBounds(200, 70, 400, 50);
      
      
      PasswordForAccountMsg.setBounds(105, 145, 600, 50);
      passwordMsg.setBounds(200, 210, 300, 20);
      password2Msg.setBounds(450, 210, 300, 20);
      invisiblePasswordInput.setBounds(200, 230, 200, 50);
      invisiblePasswordInput2.setBounds(450, 230, 200, 50);
      
      
      EmailForAccountMsg.setBounds(105, 305, 600, 50);
      emailMsg.setBounds(200, 370, 300, 20);
      emailInput.setBounds(200, 390, 400, 50);
      
      confirm.setBounds(355, 450, 90, 30);
      goBackToLogInScreen.setBounds(250, 515, 300, 30);
      

      
      IncorrectCredentials.setBounds(120, 485, 700, 20);
      CorrectCredentials.setBounds(70, 485, 700, 20);      
         
          // This is where we make all of our interface objects visible
          
      panel.add(UserNameForAccountMsg);
      panel.add(usernameMsg);
      panel.add(usernameInput);
      
      panel.add(PasswordForAccountMsg);
      panel.add(passwordMsg);
      panel.add(password2Msg);
      panel.add(invisiblePasswordInput);
      panel.add(invisiblePasswordInput2);
      
      panel.add(EmailForAccountMsg);
      panel.add(emailMsg);
      panel.add(emailInput);
      
      panel.add(confirm);
      panel.add(goBackToLogInScreen);
      
      panel.add(IncorrectCredentials);
      panel.add(CorrectCredentials);
      
      setVisible(true);

      
   
   }



// This class is triggered as soon as we select the submit button through the interface
   private class confirmActionListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
      
      
      System.out.println("DEBUG: Confirm button was pressed");
      
      String userName = "";
      String userPassword = "";
      String userPassword2 = "";
      String userEmail = "";
      boolean CustomerAlreadyExists = true;
      boolean NewUserWasAdded = false;
      
       // Collect all of the text box values
      userName = usernameInput.getText();
      
      userPassword = invisiblePasswordInput.getText();
      userPassword2 = invisiblePasswordInput2.getText();
      
      userEmail = emailInput.getText();
      
      

   // Lets verify that all inputs are valid entries  
   // First we will check that there are no null inputs
      if (userName.equals("") || userPassword.equals("") || userPassword2.equals("") || userEmail.equals("")) {
         
         CorrectCredentials.setText("                                        ");
         IncorrectCredentials.setText("** One or more fields was left blank. All fields must be filled in to continue **");
         System.out.println("DEBUG: One or more Fields was left blank");
         return;         
      
      }
      
      
   // Next we will check that the passwords match
   if (!userPassword.equals(userPassword2)) {
      CorrectCredentials.setText("                                        ");
      IncorrectCredentials.setText("** Your passwords do not match.Your Passwords have to match to continue **");
      System.out.println("DEBUG: The passwords did not match");
      return;
   
   }
   
// Finally Lets check if there are any existing customers with the same user name and/or email     
      try {
      
         CustomerAlreadyExists = UserInformation.userNameAlreadyExists(userName,userEmail);
     
     
           if (CustomerAlreadyExists) {
           
               CorrectCredentials.setText("                                        ");   
               IncorrectCredentials.setText("** Your user name or email already exists. Please try something else **");
               System.out.println("DEBUG: The new customer already existed");
               return;
            }
            
// When we get here our we can confirm that valid values have been put into all text fields
// Now lets add this new user to the database
            NewUserWasAdded = UserInformation.addNewCustomer(userName,userPassword,userEmail);
            
            IncorrectCredentials.setText("                                        ");   
            CorrectCredentials.setText("** Success... you can now return to the Customer Login Screen and use these credentials **");
            System.out.println("DEBUG: New User added to the customers database");
        

         
                  
      }
      catch (FileNotFoundException d) {
      System.out.println("DEBUG: File was not found exception was thrown trying to access customers.txt");
      
      }
      
      
      }
   }
   
   private class goBackToLogInScreenActionListener implements ActionListener {
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