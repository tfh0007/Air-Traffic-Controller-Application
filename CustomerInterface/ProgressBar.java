// Java Program to create a 
// simple progress bar 
import java.awt.*; 
import javax.swing.*; 
import java.awt.event.*; 
import java.util.Random;

public class ProgressBar extends JFrame { 
 
   // create a frame 
   private JFrame f; 
   int time = 0; 
 
   private JProgressBar b; 
   public ProgressBar() {
      // create a frame 
      setTitle("Loading"); 
      // set the size of the frame 
      setSize(1500, 150); 
      //Center the frame to middle of screen
      setLocationRelativeTo(null);
      
      //Disable resize
      setResizable(false);
      
      createProgressBar();
   }
 
   public void createProgressBar() 
   { 
   
   
      // create a panel 
      JPanel p = new JPanel(); 
      p.setLayout(null);
      getContentPane().add(p);
      p.setBackground(Color.WHITE);
   
      // create a progressbar 
      b = new JProgressBar(); 
   
      // set initial value 
      b.setValue(0); 
   
      b.setStringPainted(true);
      b.setFont(new Font("SansSerif", Font.PLAIN, 40));
      b.setBounds(0, 2, 1500, 80); 
   
      // add progressbar 
      p.add(b); 
   
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
               int BigtimeToSleep = randomNumber2.nextInt(2000);
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