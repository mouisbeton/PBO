package core.game;

public class Player {
    private String name;
    private int position;
    private int playerNumber;
    private boolean isWinner;
    
    public Player(String name, int playerNumber) {
        this.name = name;
        this.playerNumber = playerNumber;
        this.position = 1;
        this.isWinner = false;
    }    public void move(int steps) {
        int newPosition = position + steps;
        
        if (newPosition > GameBoard.getBoardSize()) {
            int overshoot = newPosition - GameBoard.getBoardSize();
            this.position = GameBoard.getBoardSize() - overshoot;
        } else {
            this.position = newPosition;
        }
    }
    
    public void teleport(int newPosition) {
        this.position = newPosition;
    }
    
    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = Math.max(1, Math.min(position, GameBoard.getBoardSize()));
    }    public boolean isWinner() {
        return position == GameBoard.getBoardSize();
    }
    
    public boolean hasWon() {
        return isWinner();
    }
    
    public void setWinner(boolean winner) {
        isWinner = winner;
    }
    
    public String getName() {
        return name;
    }
    
    public int getPlayerNumber() {
        return playerNumber;
    }
    
    @Override
    public String toString() {
        return name + " (Position: " + position + ")";
    }
}
