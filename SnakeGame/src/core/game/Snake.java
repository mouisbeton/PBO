package core.game;

public class Snake extends SpecialTile {
    
    public Snake(int startPosition, int endPosition) {
        super(startPosition, endPosition);
        if (endPosition >= startPosition) {
            throw new IllegalArgumentException("Snake end position must be less than start position");
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
        return "SNAKE";
    }
    
    @Override
    public String toString() {
        return "Snake: " + startPosition + " → " + endPosition;
    }
}
