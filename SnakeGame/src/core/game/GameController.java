package core.game;

import java.util.*;

public class GameController {
    private List<Player> players;
    private GameBoard board;
    private Dice dice;
    private int currentPlayerIndex;
    private boolean gameOver;
    private Player winner;
    
    private boolean isAnimating = false;
    private Player animatingPlayer = null;
    private int remainingMoves = 0;
    private float animationTimer = 0;
    private final float ANIMATION_DELAY = 0.15f;
    
    private TurnResult lastTurnResult = null;
    
    public GameController(List<String> playerNames) {
        this.players = new ArrayList<>();
        this.board = new GameBoard();
        this.dice = new Dice();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.winner = null;
        
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), i + 1));
        }
    }
    
    public GameController(List<Player> playerList, GameBoard gameBoard) {        
        this.players = new ArrayList<>(playerList);
        this.board = gameBoard;
        this.dice = new Dice();
        this.currentPlayerIndex = 0;
        this.gameOver = false;
        this.winner = null;
    }
    
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public TurnResult executeTurn() {
        return nextTurn();
    }
    
    public TurnResult nextTurn() {
        if (gameOver) {
            throw new IllegalStateException("Game is already over!");
        }
        
        Player currentPlayer = getCurrentPlayer();
        
        int diceValue = dice.roll();
        int previousPosition = currentPlayer.getPosition();
        
        currentPlayer.move(diceValue);
        int positionAfterMove = currentPlayer.getPosition();
        
        int finalPosition = positionAfterMove;
        SpecialTile specialTile = null;
        
        if (board.hasSpecialTile(positionAfterMove)) {
            specialTile = board.getSpecialTile(positionAfterMove);
            finalPosition = specialTile.applyEffect(positionAfterMove);
            currentPlayer.teleport(finalPosition);
        }
        
        boolean isWinning = currentPlayer.isWinner();
        if (isWinning) {
            gameOver = true;
            winner = currentPlayer;
        }
        
        TurnResult result = new TurnResult(
            currentPlayer,
            diceValue,
            previousPosition,
            positionAfterMove,
            finalPosition,
            specialTile,
            isWinning
        );
        
        if (!gameOver) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        
        return result;
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public Player getWinner() {
        return winner;
    }
    
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
    
    public GameBoard getBoard() {
        return board;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }    
    public void applySpecialTileEffects() {
        Player currentPlayer = getCurrentPlayer();
        int currentPos = currentPlayer.getPosition();
        
        if (board.hasSpecialTile(currentPos)) {
            SpecialTile specialTile = board.getSpecialTile(currentPos);
            int finalPosition = specialTile.applyEffect(currentPos);
            currentPlayer.teleport(finalPosition);
        }
        
        if (currentPlayer.isWinner()) {
            gameOver = true;
            winner = currentPlayer;
        }
        
        if (!gameOver) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }
    
    public void startAnimatedTurn() {
        if (gameOver || isAnimating) {
            return;
        }
        
        Player currentPlayer = getCurrentPlayer();
        int diceValue = dice.roll();
        int startPos = currentPlayer.getPosition();
        
        lastTurnResult = new TurnResult(
            currentPlayer,
            diceValue,
            startPos,
            startPos + diceValue,
            startPos + diceValue,
            null,
            false
        );
        
        animatingPlayer = currentPlayer;
        remainingMoves = diceValue;
        animationTimer = ANIMATION_DELAY;
        isAnimating = true;
    }
    
    public boolean updateAnimation(float delta) {
        if (!isAnimating || animatingPlayer == null) {
            return true;
        }
        
        animationTimer -= delta;
        if (animationTimer <= 0) {
            int currentPos = animatingPlayer.getPosition();
            if (currentPos < 100) {
                animatingPlayer.setPosition(currentPos + 1);
                remainingMoves--;
                
                if (remainingMoves <= 0) {
                    completeAnimation();
                    return true;
                } else {
                    animationTimer = ANIMATION_DELAY;
                }
            }
        }
        
        return false;
    }
    
    private void completeAnimation() {
        if (animatingPlayer == null) {
            return;
        }
        
        applySpecialTileEffects();
        
        animatingPlayer = null;
        isAnimating = false;
    }
    
    public boolean isAnimating() {
        return isAnimating;
    }
    
    public Player getAnimatingPlayer() {
        return animatingPlayer;
    }
    
    public TurnResult getLastTurnResult() {
        return lastTurnResult;
    }
}
