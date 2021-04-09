import javax.swing.*;


// This class will take in one thread and generate a second thread 
public class ThreadCreator extends Thread {

// This will be the label that we receive as an input
private JLabel ourLabel;
private int oldTicketPrice,newTicketPrice;

// This is our class constructor
public ThreadCreator(JLabel labelIn, int oldTicketPriceIn, int newTicketPriceIn) {
ourLabel = labelIn;
oldTicketPrice = oldTicketPriceIn;
newTicketPrice = newTicketPriceIn;



}


// This is where the new thread will go
public void run() {

// Cause the visual ticket change
   HelperMethodsForScheduleAFlightInterface.visualTicketChange(ourLabel, oldTicketPrice, newTicketPrice);

// We need to wait a little while
   try {
   Thread.sleep(1000);
   }
   catch (InterruptedException f) {
   System.out.println("ERROR: A thread was halted while trying to sleep");
   }
   
// The reason to run this again is to ensure that if the user spams buttons the right output will be displayed afterwords   
   HelperMethodsForScheduleAFlightInterface.visualTicketChange(ourLabel, newTicketPrice, newTicketPrice);
 

// Kill this thread since it has done the task we needed
stop();

}

// This will generate a new thread and return true once a new thread has been created
public boolean generateANewThread() {
   // Create our new thread
   ThreadCreator object = new ThreadCreator(ourLabel, oldTicketPrice, newTicketPrice);
   object.start();
   return true;

}

}