class Inventory {
    private int coffee;      
    private int sugar;       
    private int milk;        
    private int water;       
    private int cups;        

    private static final int MIN_STOCK = 100;

    public Inventory() {
        this.coffee = 1000;
        this.sugar = 1000;
        this.milk = 2000;
        this.water = 5000;
        this.cups = 20;
    }

    public boolean checkAvailability(int coffeeNeeded, int sugarNeeded, int milkNeeded, int waterNeeded) {
        return coffee >= coffeeNeeded && sugar >= sugarNeeded && 
               milk >= milkNeeded && water >= waterNeeded && cups > 0;
    }

    public void consumeIngredients(int coffeeUsed, int sugarUsed, int milkUsed, int waterUsed) {
        coffee -= coffeeUsed;
        sugar -= sugarUsed;
        milk -= milkUsed;
        water -= waterUsed;
        cups--;
    }

    public void refill() {
        coffee = 1000;
        sugar = 1000;
        milk = 2000;
        water = 5000;
        cups = 20;
        System.out.println("\nSemua bahan berhasil diisi ulang!");
    }

    public boolean needsRefill() {
        return coffee < MIN_STOCK || sugar < MIN_STOCK || 
               milk < MIN_STOCK || water < MIN_STOCK || cups < 5;
    }

    public void displayStatus() {
        System.out.println("\n=== Status Stok Mesin ===");
        System.out.println("Kopi: " + coffee + " gram");
        System.out.println("Gula: " + sugar + " gram");
        System.out.println("Susu: " + milk + " ml");
        System.out.println("Air: " + water + " ml");
        System.out.println("Gelas: " + cups + " buah");
        
        if (needsRefill()) {
            System.out.println("\nPERINGATAN: Stok bahan rendah! Perlu refill.");
        }
    }

    public int getCoffee() { return coffee; }
    public int getSugar() { return sugar; }
    public int getMilk() { return milk; }
    public int getWater() { return water; }
    public int getCups() { return cups; }
}