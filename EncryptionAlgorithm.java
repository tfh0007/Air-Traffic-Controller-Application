
/* This class handles encoding and decoding user passwords
*/

//External Sources: 
//Learned how to convert a string into Binary with https://mkyong.com/java/java-convert-string-to-binary/
//Learned how to XOR two binary string together in java with https://www.geeksforgeeks.org/xor-of-two-binary-strings/


public class EncryptionAlgorithm {



   public static String DecodeRequest(String key, String cipherText) {
   
      String result;
   

   
   
   
      return "";
   }


   public static String EncodeRequest(String plainText, String key) {
   
      int binaryPlainTextSize, binaryKeySize;
         String binaryResult = "";
         String result = "";
      

   
   
      StringBuilder binaryPlainText = new StringBuilder();
      StringBuilder binaryKeyText = new StringBuilder();
   
      StringBuilder binaryCipherText = new StringBuilder();
   
   
      char[] charplainText = plainText.toCharArray();
      char[] charkey = key.toCharArray();
   
      for (char aBit : charplainText) {
         binaryPlainText.append(
            String.format("%8s", Integer.toBinaryString(aBit))   
            .replaceAll(" ", "0"));
                    
      }
      System.out.println("DEBUG: After conversion to binary of text: " + plainText + " we get -> " + binaryPlainText);

   
      for (char aBit : charkey) {
         binaryKeyText.append(
            String.format("%8s", Integer.toBinaryString(aBit))   
            .replaceAll(" ", "0"));
                    
      }
      System.out.println("DEBUG: After conversion to binary of text: " + key + " we get -> " + binaryKeyText);
       
   
   
      binaryPlainTextSize = binaryPlainText.length();
   

      for (int i = 0; i < binaryPlainTextSize; i++) {
      
         if (binaryPlainText.charAt(i) == binaryKeyText.charAt(i)) {
            binaryResult += "0";
            
         }
         else {
            binaryResult += "1";
         
         }
      }
      
      // Convert The binary char array into a string of hexadecimal values
      
      //Convert the binaryResult string builder into a string
      result = binaryResult.toString();
      
      

      

        // System.out.println("DEBUG: After the Encryption process our cipher text is -> " + hexString);
       

         
         
   
      
   
      return result;
   }



}