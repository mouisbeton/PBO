import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class CoffeeOrder {
    private CoffeeType coffeeType;
    private CupSize cupSize;
    private int sugarLevel; 
    private boolean withMilk;
    private int totalPrice;

    public CoffeeOrder(CoffeeType coffeeType, CupSize cupSize, int sugarLevel, boolean withMilk) {
        this.coffeeType = coffeeType;
        this.cupSize = cupSize;
        this.sugarLevel = sugarLevel;
        this.withMilk = withMilk;
        calculatePrice();
    }

    private void calculatePrice() {
        totalPrice = coffeeType.getBasePrice() + cupSize.getAdditionalPrice();
        if (withMilk) {
            totalPrice += 2000;  
        }
    }    public int getCoffeeAmount() {
        if (cupSize == CupSize.SMALL) {
            return 15;
        } else if (cupSize == CupSize.MEDIUM) {
            return 20;
        } else if (cupSize == CupSize.LARGE) {
            return 25;
        } else {
            return 15;
        }
    }

    public int getSugarAmount() {
        return sugarLevel * 5;  
    }    public int getMilkAmount() {
        if (!withMilk) return 0;
        if (cupSize == CupSize.SMALL) {
            return 50;
        } else if (cupSize == CupSize.MEDIUM) {
            return 80;
        } else if (cupSize == CupSize.LARGE) {
            return 100;
        } else {
            return 50;
        }
    }    public int getWaterAmount() {
        if (cupSize == CupSize.SMALL) {
            return 150;
        } else if (cupSize == CupSize.MEDIUM) {
            return 200;
        } else if (cupSize == CupSize.LARGE) {
            return 250;
        } else {
            return 150;
        }
    }

    public void displayOrder() {
        System.out.println("\n=== Detail Pesanan ===");
        System.out.println("Jenis Kopi: " + coffeeType.getName());
        System.out.println("Ukuran: " + cupSize.getName());
        System.out.println("Gula: Level " + sugarLevel);
        System.out.println("Susu: " + (withMilk ? "Ya" : "Tidak"));
        System.out.println("Total Harga: Rp " + String.format("%,d", totalPrice));
    }

    public CoffeeType getCoffeeType() { return coffeeType; }
    public CupSize getCupSize() { return cupSize; }
    public int getSugarLevel() { return sugarLevel; }
    public boolean isWithMilk() { return withMilk; }
    public int getTotalPrice() { return totalPrice; }
}