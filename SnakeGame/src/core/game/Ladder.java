package core.game;

public class Ladder extends SpecialTile {
    
    public Ladder(int startPosition, int endPosition) {
        super(startPosition, endPosition);
        if (endPosition <= startPosition) {
            throw new IllegalArgumentException("Ladder end position must be greater than start position");
        }
    }
    
    @Override
    public int applyEffect(int currentPosition) {
        if (currentPosition == startPosition) {
            return endPosition;
        }
        return currentPosition;
    }
    
    @Override
    public String getType() {
        return "LADDER";
    }
    
    @Override
    public String toString() {
        return "Ladder: " + startPosition + " → " + endPosition;
    }
}
