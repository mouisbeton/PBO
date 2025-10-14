class CupSize {
    private String name;
    private int additionalPrice;

    public static final CupSize SMALL = new CupSize("Small", 0);
    public static final CupSize MEDIUM = new CupSize("Medium", 3000);
    public static final CupSize LARGE = new CupSize("Large", 5000);
    
    private static final CupSize[] ALL_SIZES = {SMALL, MEDIUM, LARGE};

    public CupSize(String name, int additionalPrice) {
        this.name = name;
        this.additionalPrice = additionalPrice;
    }

    public String getName() {
        return name;
    }

    public int getAdditionalPrice() {
        return additionalPrice;
    }
    
    public static CupSize[] values() {
        return ALL_SIZES;
    }
    
    public static CupSize getByIndex(int index) {
        if (index >= 0 && index < ALL_SIZES.length) {
            return ALL_SIZES[index];
        }
        return null;
    }
}