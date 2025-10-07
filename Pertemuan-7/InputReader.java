import java.util.Scanner;

 public class InputReader {
   private Scanner scanner;

   public InputReader() {
     this.scanner = new Scanner(System.in);
   }

   public String readInput() {
     System.out.print("Anda: ");
     return scanner.nextLine();
   }

   public void close() {
     scanner.close();
   }
 }
