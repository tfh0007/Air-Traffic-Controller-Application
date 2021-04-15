// Java Program to create a 
// simple progress bar 
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.Random;

public class ProgressBar extends JFrame { 
 
   // create a frame 
   private JFrame f; 
   
   // Will display underneath the progress bar and decribe everything being compiled
   // NOTE: This is all fake and just a visual trick
   private JLabel Tasks;
   
   int time = 0; 
 
   private JProgressBar b; 
   public ProgressBar() {
      // create a frame 
      setTitle("Loading"); 
      // set the size of the frame 
      setSize(1500, 170); 
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      
      //Disable resize
      setResizable(false);
      
      createProgressBar();
   }
   
  // Create the interface and add everything to it
   public void createProgressBar() 
   { 
   
   
      // create the panel 
      JPanel panel = new JPanel(); 
      panel.setLayout(null);
      getContentPane().add(panel);
      panel.setBackground(Color.BLACK);
   
      // create a progressbar 
      b = new JProgressBar(); 
      
      
      Tasks = new JLabel();
      Tasks.setForeground (Color.green);

   
      // set initial value 
      b.setValue(0); 
   
      b.setStringPainted(true);
      b.setFont(new Font("SansSerif", Font.PLAIN, 40));
      
      
      
      // Set the location for objects on the interface (denoted as X-axis,Y-axis,x-width,y-width)
      // location 0,0 represents the top left corner of the panel

      b.setBounds(0, 2, 1500, 80); 
      Tasks.setBounds(0, 66, 1500, 80); 
      
            
      
   
      // This is where we make all of our interface objects visible
      panel.add(b); 
      panel.add(Tasks);
   
      // add panel 
   
      setVisible(true); 
      
   
      
   } 
   public void addTime(int timeIn) {
      time = time + timeIn;
      b.setValue(time);
      if (time > 100) {
         setVisible(false);
         dispose();
      }
   
   }
   // function to increase progress 
   public  void fill() 
   { 
      int i = 0; 
      try { 
         while (i <= 100) { 
            // fill the menu bar 
            b.setValue(i + 1); 
         
            // delay the thread 
            Thread.sleep(15); 
            i += 1; 
         } 
         setVisible(false); 
         dispose();
      
      } 
      catch (Exception e) { 
      } 
   } 
   
   
      // function to increase progress 
   public  void fillFast() 
   { 
      int i = 0; 
      // Pick a random image for our avaliable backgrounds
      // Lower bound of random number
      Random randomNumber = new Random(5);
      Random randomNumber2 = new Random(700);
      // Upper bound of random number
      int timeToSleep = randomNumber.nextInt(30);
      

      try { 
      
         boolean occuredOnce = false;
         while (i <= 100) { 
         
         // Set the messages of tasks occuring displayed to the user
         switch (i) {
         
         case 1:
            Tasks.setText("Gathering Requirements: ~/Documents/Air-Traffic-Controller-Implementation/");
            break;
         
         case 30:
            Tasks.setText("Gathering Database Information: ~/Documents/Air-Traffic-Controller-Implementation/Databases/customers.txt");
            break;
         
         case 35:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Gathering Database Information: ~/Documents/Air-Traffic-Controller-Implementation/Databases/airlines.txt");
            break;
            
         case 40:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Gathering Database Information: ~/Documents/Air-Traffic-Controller-Implementation/Databases/airports.txt");
            break;
            
         case 45:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Gathering Graphics Information: ~/Documents/Air-Traffic-Controller-Implementation/Graphics/airplaneNight.jpg");
            break;
            
         case 50:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Gathering Graphics Information: ~/Documents/Air-Traffic-Controller-Implementation/Graphics/airplane_wallpaper.jpg");
            break;
            
         case 55:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Gathering Graphics Information: ~/Documents/Air-Traffic-Controller-Implementation/Graphics/DataBackground.jpg");
            break;

         case 60:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/EncryptionAlgorithm.java");
            break;


         case 65:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/AirlineAndAirportInterface.java");
            break;
            
         case 70:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/AirTrafficControllerDriver.java");
            break;
                 
         case 75:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/CreateNewUserInterface.java");
            break;
            
         case 80:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/CustomerInformationScanner.java");
            break;
           
         case 85:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/log_in_Screen.java");
            break;

         case 90:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/log_in_Screen.java");
            break;
            
         case 95:
            Tasks.setText("                                                                                                                                       ");
            Tasks.setText("Compiling Source Files: ~/Documents/Air-Traffic-Controller-Implementation/CustomerInterface/ShowUserInfoInterface.java");
            break;
            
            
         }
            // fill the menu bar 
            b.setValue(i + 1); 
         
            // delay the thread 
            
            Thread.sleep(timeToSleep);
            
           //  if (i % 39 == 1) {
//                Thread.sleep(150); 
//             }
//             
//             if (i % 15 == 1) {
//                Thread.sleep(100); 
//             }
//             
            if (i > 95 && occuredOnce==false) {
               int BigtimeToSleep = randomNumber2.nextInt(1000);
               //i=i-93;
               Thread.sleep(BigtimeToSleep); 
               occuredOnce = true;
            }
//             
//             if (i % 21 == 1) {
//                Thread.sleep(300); 
//             }


            
            
            i += 1; 
         } 
         setVisible(false); 
         dispose();
      
      } 
      catch (Exception e) { 
      } 
   } 

}