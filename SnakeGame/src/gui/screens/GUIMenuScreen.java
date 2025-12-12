package gui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input;
import java.util.ArrayList;
import java.util.List;

public class GUIMenuScreen extends GUIScreen {    private List<String> playerNames;
    private GUIGameScreen nextScreen;
    private ShapeRenderer shapeRenderer;
    private int selectedPlayers = 0;
    private boolean gameStarted = false;
    
    private int buttonWidth = 150;
    private int buttonHeight = 70;
    private int button2X, button2Y;
    private int button3X, button3Y;
    private int button4X, button4Y;
    
    public GUIMenuScreen(SpriteBatch batch, BitmapFont font) {
        super(batch, font);
        this.playerNames = new ArrayList<>();
        this.shapeRenderer = new ShapeRenderer();
    }    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.05f, 0.05f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        int centerX = screenWidth / 2;
        int buttonY = screenHeight / 2;
        
        button2X = centerX - 200;
        button2Y = buttonY - 35;
        
        button3X = centerX - 75;
        button3Y = buttonY - 35;
        
        button4X = centerX + 50;
        button4Y = buttonY - 35;
        
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        drawButtonShape(button2X, button2Y);
        drawButtonShape(button3X, button3Y);
        drawButtonShape(button4X, button4Y);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
        
        batch.begin();
        renderMenu();
        batch.end();
        
        handleInput();
    }
    
    private void drawButtonShape(int x, int y) {
        boolean isHovered = isMouseOverButton(x, y, buttonWidth, buttonHeight);
        
        if (isHovered) {
            shapeRenderer.setColor(0, 0.8f, 0, 1);
        } else {
            shapeRenderer.setColor(0.3f, 0.3f, 0.8f, 1);
        }
        
        shapeRenderer.rect(x, y, buttonWidth, buttonHeight);
    }
    
    private void renderMenu() {
        font.setColor(1, 1, 0, 1);
        font.draw(batch, "SNAKES AND LADDERS", 350, screenHeight - 100);
        
        font.setColor(1, 1, 1, 1);
        font.draw(batch, "Select number of players:", 300, screenHeight - 250);
        
        drawButtonLabel(button2X, button2Y, "2 Players");
        drawButtonLabel(button3X, button3Y, "3 Players");
        drawButtonLabel(button4X, button4Y, "4 Players");
        
        font.setColor(0, 1, 1, 1);
        font.draw(batch, "Click on a button to select the number of players", 200, 80);
    }
      private void drawButtonLabel(int x, int y, String label) {
        font.setColor(1, 1, 1, 1);
        int textX = x + (buttonWidth - (int)(label.length() * 8)) / 2;
        int textY = y + buttonHeight - 20;
        font.draw(batch, label, textX, textY);
    }
      private boolean isMouseOverButton(int x, int y, int width, int height) {
        int mouseX = Gdx.input.getX();
        int mouseY = screenHeight - Gdx.input.getY();
        
        return mouseX >= x && mouseX <= x + width && 
               mouseY >= y && mouseY <= y + height;
    }
      private void handleInput() {
        if (Gdx.input.isButtonJustPressed(0)) {
            int mouseX = Gdx.input.getX();
            int mouseY = screenHeight - Gdx.input.getY();
            
            if (isMouseOverButton(button2X, button2Y, buttonWidth, buttonHeight)) {
                startGame(2);
            } else if (isMouseOverButton(button3X, button3Y, buttonWidth, buttonHeight)) {
                startGame(3);
            } else if (isMouseOverButton(button4X, button4Y, buttonWidth, buttonHeight)) {
                startGame(4);
            }
        }
    }
    
    private void startGame(int numPlayers) {
        playerNames.clear();
        for (int i = 1; i <= numPlayers; i++) {
            playerNames.add("Player " + i);
        }
        nextScreen = new GUIGameScreen(batch, font, playerNames);
        gameStarted = true;
    }
    
    public Screen getNextScreen() {
        return nextScreen;
    }
    
    @Override
    public void resize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
