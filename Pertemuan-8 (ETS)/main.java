import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VendingMachine machine = new VendingMachine();

        System.out.println("╔════════════════════════════════════╗");
        System.out.println("║  SELAMAT DATANG DI VENDING COFFEE  ║");
        System.out.println("╚════════════════════════════════════╝");

        while (machine.isOperational()) {
            System.out.println("\n=== Menu Utama ===");
            System.out.println("1. Pesan Kopi");
            System.out.println("2. Menu Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");
            
            if (scanner.hasNextInt()) {
                int mainChoice = scanner.nextInt();

                if (mainChoice == 1) {
                    CoffeeOrder order = machine.createOrder(scanner);
                    machine.processOrder(order, scanner);
                } else if (mainChoice == 2) {
                    System.out.print("Masukkan password admin: ");
                    String password = scanner.next();
                    if (password.equals("admin123")) {
                        machine.adminMenu(scanner);
                    } else {
                        System.out.println("Password salah!");
                    }
                } else if (mainChoice == 3) {
                    System.out.println("\nTerima kasih telah menggunakan Vending Coffee Machine!");
                    machine.shutdown();
                } else {
                    System.out.println("Pilihan tidak valid!");
                }
            } else {
                System.out.println("Input tidak valid! Harap masukkan angka.");
                scanner.nextLine(); 
            }
        }
        scanner.close();
    }
}