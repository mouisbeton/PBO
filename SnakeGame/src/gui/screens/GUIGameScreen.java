package gui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import core.game.*;
import java.util.List;

public class GUIGameScreen extends GUIScreen {
    private GameController controller;
    private GameBoard board;
    private List<Player> players;
    private Player winner;
    private GUIResultScreen nextScreen;
    private boolean gameEnded = false;
    private boolean requestingMenuReturn = false;
    private Texture gridTexture;
    private Texture[] playerTextures;    private Texture[] diceTextures;
    private int displayedDiceValue = 0;
    private float diceRollAnimationTimer = 0;
    private final float DICE_ANIMATION_DURATION = 1.5f;
    private float diceChangeDelay = 0;
    private final float DICE_CHANGE_INTERVAL = 0.1f;
    private boolean isShowingDiceRoll = false;
      public GUIGameScreen(SpriteBatch batch, BitmapFont font, List<String> playerNames) {
        super(batch, font);
        this.board = new GameBoard();
        this.players = new java.util.ArrayList<>();
        
        for (int i = 0; i < playerNames.size(); i++) {
            players.add(new Player(playerNames.get(i), i + 1));
        }
        
        this.controller = new GameController(players, board);
        this.gridTexture = new Texture("assets/grid.png");
        
        this.playerTextures = new Texture[4];
        playerTextures[0] = new Texture("assets/player_1.png");
        playerTextures[1] = new Texture("assets/player_2.png");
        playerTextures[2] = new Texture("assets/player_3.png");
        playerTextures[3] = new Texture("assets/player_4.png");
        
        this.diceTextures = new Texture[6];
        for (int i = 0; i < 6; i++) {
            diceTextures[i] = new Texture("assets/dice_" + (i + 1) + ".png");
        }
    }    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.05f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        boolean animationComplete = controller.updateAnimation(delta);
        
        if (isShowingDiceRoll) {
            updateDiceRollAnimation(delta);
        }
        
        renderBoard();
        renderPlayers();
        renderDice();
        
        renderGameInfo();
        
        if (!gameEnded && !controller.isAnimating() && !isShowingDiceRoll) {
            handleInput();
        }
        
        checkForGameEnd();
        
        if (gameEnded && !requestingMenuReturn && winner != null) {
            renderGameOver();
        }
          batch.end();
    }
      private void updateDiceRollAnimation(float delta) {
        diceRollAnimationTimer += delta;
        diceChangeDelay += delta;
        
        if (diceRollAnimationTimer < DICE_ANIMATION_DURATION) {
            if (diceChangeDelay >= DICE_CHANGE_INTERVAL) {
                int randomDice = (int) (Math.random() * 6);
                displayedDiceValue = randomDice + 1;
                diceChangeDelay = 0;
            }
        } else {
            isShowingDiceRoll = false;
            diceRollAnimationTimer = 0;
            diceChangeDelay = 0;
        }
    }    private void renderDice() {
        if (displayedDiceValue > 0 && displayedDiceValue <= 6) {
            float diceX = 775;
            float diceY = 20;
            float diceSize = 120;
            
            Texture diceTex = diceTextures[displayedDiceValue - 1];
            batch.draw(diceTex, diceX, diceY, diceSize, diceSize);
            
            if (isShowingDiceRoll) {
                font.setColor(1, 1, 0, 1);
                font.draw(batch, "Rolling...", diceX - 30, diceY + 130);
            }
        }
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
    }    private void renderBoard() {
        font.setColor(1, 1, 1, 1);
        font.draw(batch, "SNAKES AND LADDERS", 50, screenHeight - 30);
        
        int tileSize = 60;
        int boardStartX = 50;
        int boardStartY = screenHeight - 100;
        
        if (gridTexture != null) {
            batch.draw(gridTexture, boardStartX, boardStartY - 600, 600, 600);
        }
    }private void renderPlayers() {
        int tileSize = 60;
        int boardStartX = 50;
        int boardStartY = screenHeight - 100;
        
        java.util.Map<Integer, java.util.List<Integer>> playersPerTile = new java.util.HashMap<>();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            int pos = p.getPosition();
            playersPerTile.computeIfAbsent(pos, k -> new java.util.ArrayList<>()).add(i);
        }        for (java.util.Map.Entry<Integer, java.util.List<Integer>> entry : playersPerTile.entrySet()) {
            int pos = entry.getKey();
            java.util.List<Integer> playerIndices = entry.getValue();
            
            int row = (pos - 1) / 10;
            int colInRow = (pos - 1) % 10;
            
            int col = (row % 2 == 0) ? (9 - colInRow) : colInRow;
            
            int baseTileX = boardStartX + (col * tileSize);
            int baseTileY = boardStartY - (row * tileSize);
            
            int playersOnTile = playerIndices.size();
            float spriteSize;
            float spacing;
            
            if (playersOnTile == 1) {
                spriteSize = 40;
                spacing = 0;
            } else if (playersOnTile == 2) {
                spriteSize = 24;
                spacing = 6;
            } else {
                spriteSize = 18;
                spacing = 4;
            }
            
            float totalWidth = (playersOnTile * spriteSize) + ((playersOnTile - 1) * spacing);
            float startX = baseTileX + (tileSize - totalWidth) / 2;
            float startY = baseTileY - spriteSize - 5;
            
            for (int i = 0; i < playerIndices.size(); i++) {
                int playerIdx = playerIndices.get(i);
                float x = startX + (i * (spriteSize + spacing));
                batch.draw(playerTextures[playerIdx], x, startY, spriteSize, spriteSize);
            }
        }
    }

    private void renderGameOver() {
        font.setColor(1, 0, 0, 1);
        font.draw(batch, "GAME OVER!", screenWidth / 2 - 100, screenHeight / 2 + 50);
        font.draw(batch, "Winner: " + winner.getName(), screenWidth / 2 - 150, screenHeight / 2);        font.draw(batch, "Press SPACE to exit", screenWidth / 2 - 150, screenHeight / 2 - 50);
    }    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            if (!gameEnded) {
                isShowingDiceRoll = true;
                diceRollAnimationTimer = 0;
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
    }    @Override
    public void dispose() {
        if (gridTexture != null) {
            gridTexture.dispose();
        }
        if (playerTextures != null) {
            for (Texture texture : playerTextures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }
        if (diceTextures != null) {
            for (Texture texture : diceTextures) {
                if (texture != null) {
                    texture.dispose();
                }
            }
        }
    }
}
