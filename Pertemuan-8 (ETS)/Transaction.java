import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Transaction {
    private static int transactionCounter = 1;
    private int transactionId;
    private CoffeeOrder order;
    private int payment;
    private int change;
    private LocalDateTime timestamp;

    public Transaction(CoffeeOrder order, int payment) {
        this.transactionId = transactionCounter++;
        this.order = order;
        this.payment = payment;
        this.change = payment - order.getTotalPrice();
        this.timestamp = LocalDateTime.now();
    }

    public void printReceipt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        System.out.println("\n╔════════════════════════════════════╗");
        System.out.println("║      VENDING COFFEE MACHINE        ║");
        System.out.println("╚════════════════════════════════════╝");
        System.out.println("Transaction ID: #" + transactionId);
        System.out.println("Tanggal: " + timestamp.format(formatter));
        System.out.println("────────────────────────────────────");
        System.out.println("Kopi: " + order.getCoffeeType().getName());
        System.out.println("Ukuran: " + order.getCupSize().getName());
        System.out.println("Gula: Level " + order.getSugarLevel());
        System.out.println("Susu: " + (order.isWithMilk() ? "Ya" : "Tidak"));
        System.out.println("────────────────────────────────────");
        System.out.println("Total: Rp " + String.format("%,d", order.getTotalPrice()));
        System.out.println("Bayar: Rp " + String.format("%,d", payment));
        System.out.println("Kembalian: Rp " + String.format("%,d", change));
        System.out.println("════════════════════════════════════");
        System.out.println("   Terima kasih! Selamat menikmati!");
        System.out.println("════════════════════════════════════\n");
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("#%d | %s | %s (%s) | Rp %,d", 
            transactionId, 
            timestamp.format(formatter),
            order.getCoffeeType().getName(),
            order.getCupSize().getName(),
            order.getTotalPrice());
    }
}