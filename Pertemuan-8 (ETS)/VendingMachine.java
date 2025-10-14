import java.util.*;

class VendingMachine {
    private Inventory inventory;
    private List<Transaction> transactionLog;
    private boolean isOperational;

    public VendingMachine() {
        this.inventory = new Inventory();
        this.transactionLog = new ArrayList<>();
        this.isOperational = true;
    }

    public void displayMenu() {
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║   VENDING COFFEE MACHINE MENU      ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("\n=== Jenis Kopi ===");
        int index = 1;
        for (CoffeeType type : CoffeeType.values()) {
            System.out.println(index++ + ". " + type.getName() + " - Rp " + 
                String.format("%,d", type.getBasePrice()));
        }
    }

    public CoffeeOrder createOrder(Scanner scanner) {
        displayMenu();
        System.out.print("\nPilih jenis kopi (1-" + CoffeeType.values().length + "): ");
        int coffeeChoice = scanner.nextInt();
        CoffeeType selectedCoffee = CoffeeType.values()[coffeeChoice - 1];

        System.out.println("\n=== Ukuran Gelas ===");
        int index = 1;
        for (CupSize size : CupSize.values()) {
            System.out.println(index++ + ". " + size.getName() + " (+Rp " + 
                String.format("%,d", size.getAdditionalPrice()) + ")");
        }
        System.out.print("Pilih ukuran (1-" + CupSize.values().length + "): ");
        int sizeChoice = scanner.nextInt();
        CupSize selectedSize = CupSize.values()[sizeChoice - 1];

        System.out.print("\nLevel gula (0-3): ");
        int sugarLevel = scanner.nextInt();
        sugarLevel = Math.max(0, Math.min(3, sugarLevel));

        System.out.print("Tambah susu? (1=Ya, 0=Tidak): ");
        boolean withMilk = scanner.nextInt() == 1;

        return new CoffeeOrder(selectedCoffee, selectedSize, sugarLevel, withMilk);
    }

    public boolean processOrder(CoffeeOrder order, Scanner scanner) {
        order.displayOrder();
        int flag = 0;

        if (!inventory.checkAvailability(
                order.getCoffeeAmount(),
                order.getSugarAmount(),
                order.getMilkAmount(),
                order.getWaterAmount())) {
            System.out.println("\nMaaf, bahan tidak mencukupi!");
            inventory.displayStatus();
            return false;
        }
        int payment = 0;
        while (flag == 0){
            System.out.print("\nMasukkan pembayaran (Rp): ");
            payment = scanner.nextInt();
    
            if (payment < order.getTotalPrice()) {
                System.out.println("\nPembayaran tidak cukup!");
                continue;
            }
            flag = 1;
        }

        System.out.println("\nMembuat kopi...");
        makeCoffee(order);

        inventory.consumeIngredients(
            order.getCoffeeAmount(),
            order.getSugarAmount(),
            order.getMilkAmount(),
            order.getWaterAmount()
        );

        Transaction transaction = new Transaction(order, payment);
        transactionLog.add(transaction);
        transaction.printReceipt();

        if (inventory.needsRefill()) {
            System.out.println("PERINGATAN ADMIN: Stok bahan rendah!");
        }

        return true;
    }

    private void makeCoffee(CoffeeOrder order) {
        try {
            System.out.println("Memanaskan air...");
            Thread.sleep(1000);
            System.out.println("Menyeduh kopi...");
            Thread.sleep(1000);
            if (order.getSugarLevel() > 0) {
                System.out.println("Menambahkan gula...");
                Thread.sleep(500);
            }
            if (order.isWithMilk()) {
                System.out.println("Menambahkan susu...");
                Thread.sleep(500);
            }
            System.out.println("Kopi siap!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void displayTransactionLog() {
        System.out.println("\n=== Riwayat Transaksi ===");
        if (transactionLog.isEmpty()) {
            System.out.println("Belum ada transaksi.");
        } else {
            for (Transaction t : transactionLog) {
                System.out.println(t);
            }
        }
    }

    public void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Lihat Status Stok");
            System.out.println("2. Refill Bahan");
            System.out.println("3. Lihat Riwayat Transaksi");
            System.out.println("4. Kembali");
            System.out.print("Pilih menu: ");
              int choice = scanner.nextInt();
            
            if (choice == 1) {
                inventory.displayStatus();
            } else if (choice == 2) {
                inventory.refill();
            } else if (choice == 3) {
                displayTransactionLog();
            } else if (choice == 4) {
                return;
            } else {
                System.out.println("Pilihan tidak valid!");
            }
        }
    }

    public boolean isOperational() {
        return isOperational;
    }

    public void shutdown() {
        isOperational = false;
    }
}