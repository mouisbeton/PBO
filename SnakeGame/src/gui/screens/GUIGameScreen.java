package gui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import core.game.*;
import java.util.List;

public class GUIGameScreen extends GUIScreen {
    private GameController controller;
    private GameBoard board;
    private List<Player> players;
    private Player winner;
    private GUIResultScreen nextScreen;    private boolean gameEnded = false;
    private boolean requestingMenuReturn = false;
    
    public GUIGameScreen(SpriteBatch batch, BitmapFont font, List<String> playerNames) {
        super(batch, font);
        this.board = new GameBoard();
        this.players = new java.util.ArrayList<>();
        
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), i + 1));
        }
        
        this.controller = new GameController(players, board);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.05f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        boolean animationComplete = controller.updateAnimation(delta);
        
        renderBoard();
        renderPlayers();
        
        renderGameInfo();
        
        if (!gameEnded && !controller.isAnimating()) {
            handleInput();
        }
        
        checkForGameEnd();
        
        if (gameEnded && !requestingMenuReturn && winner != null) {
            renderGameOver();
        }
          batch.end();
    }

    private void renderGameInfo() {
        int infoX = 750;
        int infoY = screenHeight - 50;
        
        font.setColor(1, 1, 0, 1);
        font.draw(batch, "=== GAME STATUS ===", infoX, infoY);
        infoY -= 50;
        
        font.setColor(1, 1, 1, 1);
        Player currentPlayer = controller.getCurrentPlayer();
        for (Player p : players) {
            if (p == currentPlayer) {
                font.setColor(1, 1, 0, 1);
                font.draw(batch, "> " + p.getName(), infoX, infoY);
            } else {
                font.setColor(0.7f, 0.7f, 0.7f, 1);
                font.draw(batch, "  " + p.getName(), infoX, infoY);
            }
            font.setColor(0.5f, 1, 1, 1);
            font.draw(batch, "    Pos: " + p.getPosition(), infoX + 10, infoY - 25);
            infoY -= 60;
        }
        
        TurnResult lastTurnResult = controller.getLastTurnResult();
        if (lastTurnResult != null) {
            font.setColor(0, 1, 0, 1);
            infoY -= 20;
            font.draw(batch, "Last Dice: " + lastTurnResult.getDiceRoll(), infoX, infoY);
            infoY -= 30;
            font.draw(batch, "Moved to: " + lastTurnResult.getNewPosition(), infoX, infoY);
        }
        
        if (!gameEnded) {
            font.setColor(0, 1, 1, 1);
            infoY -= 60;
            font.draw(batch, "Press SPACE", infoX, infoY);
            font.draw(batch, "to roll dice", infoX, infoY - 30);
        }
        
        font.setColor(1, 0, 1, 1);
        infoY -= 80;
        font.draw(batch, "Press R to", infoX, infoY);        font.draw(batch, "return to menu", infoX, infoY - 30);
    }

    private void renderBoard() {
        font.setColor(1, 1, 1, 1);
        font.draw(batch, "SNAKES AND LADDERS", 50, screenHeight - 30);
        
        int tileSize = 60;
        int boardStartX = 50;
        int boardStartY = screenHeight - 100;
        
        for (int i = 1; i <= 100; i++) {
            int row = (i - 1) / 10;
            int col = (i - 1) % 10;
            
            int x = boardStartX + (col * tileSize);
            int y = boardStartY - (row * tileSize);
            
            if (board.hasSpecialTile(i)) {
                SpecialTile tile = board.getSpecialTile(i);
                if (tile instanceof Snake) {
                    font.setColor(1, 0, 0, 1);
                } else {
                    font.setColor(0, 1, 0, 1);
                }
            } else {
                font.setColor(1, 1, 1, 1);
            }
            
            String num = i < 10 ? " " + i : String.valueOf(i);            font.draw(batch, num, x + 15, y - 15);
        }
    }

    private void renderPlayers() {
        int tileSize = 60;
        int boardStartX = 50;
        int boardStartY = screenHeight - 100;
        
        Color[] colors = {
            new Color(1, 0, 0, 1),
            new Color(0, 1, 0, 1),
            new Color(0, 0, 1, 1),
            new Color(1, 1, 0, 1)
        };
        
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int pos = p.getPosition();
            
            int row = (pos - 1) / 10;
            int col = (pos - 1) % 10;
            
            int x = boardStartX + (col * tileSize) + 20;
            int y = boardStartY - (row * tileSize) - 25;
            
            font.setColor(colors[i]);
            font.draw(batch, p.getName().charAt(0) + "", x, y);
        }
    }

    private void renderGameOver() {
        font.setColor(1, 0, 0, 1);
        font.draw(batch, "GAME OVER!", screenWidth / 2 - 100, screenHeight / 2 + 50);
        font.draw(batch, "Winner: " + winner.getName(), screenWidth / 2 - 150, screenHeight / 2);        font.draw(batch, "Press SPACE to exit", screenWidth / 2 - 150, screenHeight / 2 - 50);
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!gameEnded) {
                controller.startAnimatedTurn();
            }
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            requestingMenuReturn = true;
        }
    }
    
    private void checkForGameEnd() {
        if (controller.isGameOver()) {
            winner = controller.getWinner();
            gameEnded = true;
        }
    }

    public Screen getNextScreen() {
        if (requestingMenuReturn && Gdx.input.isKeyJustPressed(Input.Keys.R)) {
            return new GUIMenuScreen(batch, font);
        }
        
        if (gameEnded && controller.isGameOver() && winner != null && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            return new GUIResultScreen(batch, font, winner);
        }
        
        return null;
    }
    
    @Override
    public void resize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
