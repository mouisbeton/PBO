package core.game;

public abstract class SpecialTile {
    protected int startPosition;
    protected int endPosition;
    
    public SpecialTile(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }
    
    public int getStartPosition() {
        return startPosition;
    }
    
    public int getEndPosition() {
        return endPosition;
    }
    
    public abstract int applyEffect(int currentPosition);
    
    public abstract String getType();
}
