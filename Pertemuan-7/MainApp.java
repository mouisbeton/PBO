 public class MainApp {

   public static void main(String[] args) {
     TechSupport techSupport = new TechSupport();
     InputReader reader = new InputReader();

     System.out.println("🤖 Selamat datang di Dukungan Teknis! Ketik 'bye' untuk keluar.");
     System.out.println("------------------------------------------------------------------");

     String userInput;

     do {
       userInput = reader.readInput();

       if (userInput.equalsIgnoreCase("bye") || userInput.equalsIgnoreCase("exit")) {
         break;
       }

       String response = techSupport.getResponse(userInput);
       System.out.println("Teknisi: " + response);
       System.out.println("------------------------------------------------------------------");

     } while (true); 

     System.out.println("🤖 Terima kasih telah menghubungi Dukungan Teknis. Sampai jumpa!");
     reader.close(); 
   }
 }
