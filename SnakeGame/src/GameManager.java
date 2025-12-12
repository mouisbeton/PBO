import core.game.*;
import java.util.*;

public class GameManager {
    private GameController controller;
    private GameBoard board;
    private List<Player> players;
    private TurnResult lastTurnResult;
    private Player winner;
    private boolean gameStarted = false;
      public GameManager() {
        this.board = new GameBoard();
    }
    
    public void startGame(List<String> playerNames) {
        this.players = new ArrayList<>();
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), i + 1));
        }
        
        this.controller = new GameController(players, board);
        this.gameStarted = true;
        this.winner = null;
    }
    
    public void executeTurn() {
        if (!gameStarted || winner != null) return;
          this.lastTurnResult = controller.executeTurn();
        
        for (Player player : players) {
            if (player.hasWon()) {
                this.winner = player;
                break;
            }
        }
    }
    
    public boolean hasCurrentTurn() {
        return lastTurnResult != null;
    }
    
    public TurnResult getLastTurnResult() {
        return lastTurnResult;
    }
    
    public Player getWinner() {
        return winner;
    }
    
    public GameBoard getBoard() {
        return board;
    }
    
    public List<Player> getAllPlayers() {
        return new ArrayList<>(players);
    }
    
    public void reset() {
        this.controller = null;
        this.players = null;
        this.lastTurnResult = null;
        this.winner = null;
        this.gameStarted = false;
        this.board = new GameBoard();
    }
    
    public boolean isGameOver() {
        return winner != null;
    }
}
