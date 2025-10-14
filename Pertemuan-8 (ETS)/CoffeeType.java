class CoffeeType {
    private String name;
    private int basePrice;
    
    public static final CoffeeType ESPRESSO = new CoffeeType("Espresso", 15000);
    public static final CoffeeType AMERICANO = new CoffeeType("Americano", 18000);
    public static final CoffeeType CAPPUCCINO = new CoffeeType("Cappuccino", 22000);
    public static final CoffeeType LATTE = new CoffeeType("Latte", 25000);
    public static final CoffeeType MOCHA = new CoffeeType("Mocha", 28000);
    
    private static final CoffeeType[] ALL_TYPES = {ESPRESSO, AMERICANO, CAPPUCCINO, LATTE, MOCHA};

    public CoffeeType(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }
    
    public static CoffeeType[] values() {
        return ALL_TYPES;
    }
    
    public static CoffeeType getByIndex(int index) {
        if (index >= 0 && index < ALL_TYPES.length) {
            return ALL_TYPES[index];
        }
        return null;
    }
}