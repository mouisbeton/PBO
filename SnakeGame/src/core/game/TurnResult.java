package core.game;

public class TurnResult {
    private final Player player;
    private final int diceValue;
    private final int previousPosition;
    private final int positionAfterMove;
    private final int finalPosition;
    private final SpecialTile specialTile;
    private final boolean isWinning;
    
    public TurnResult(Player player, int diceValue, int previousPosition, 
                      int positionAfterMove, int finalPosition, 
                      SpecialTile specialTile, boolean isWinning) {
        this.player = player;
        this.diceValue = diceValue;
        this.previousPosition = previousPosition;
        this.positionAfterMove = positionAfterMove;
        this.finalPosition = finalPosition;
        this.specialTile = specialTile;
        this.isWinning = isWinning;    }
      public Player getPlayer() {
        return player;
    }
    
    public String getPlayerName() {
        return player.getName();
    }
    
    public int getDiceRoll() {
        return diceValue;
    }
    
    public int getNewPosition() {
        return finalPosition;
    }
    
    public int getDiceValue() {
        return diceValue;
    }
    
    public int getPreviousPosition() {
        return previousPosition;
    }
    
    public int getPositionAfterMove() {
        return positionAfterMove;
    }
    
    public int getFinalPosition() {
        return finalPosition;
    }
    
    public SpecialTile getSpecialTile() {
        return specialTile;
    }
    
    public boolean isWinning() {
        return isWinning;
    }
    
    public boolean hasSpecialTile() {
        return specialTile != null;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName()).append(" rolled ").append(diceValue);
        sb.append(" (").append(previousPosition).append(" → ").append(positionAfterMove).append(")");
        
        if (hasSpecialTile()) {
            sb.append(" [").append(specialTile.getType()).append(": ").append(finalPosition).append("]");
        }
        
        if (isWinning) {
            sb.append(" 🎉 WINNER!");
        }
        
        return sb.toString();
    }
}
