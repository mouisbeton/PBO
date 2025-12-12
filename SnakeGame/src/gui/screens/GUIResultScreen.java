package gui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;
import core.game.Player;

public class GUIResultScreen extends GUIScreen {
    private Player winner;
    private boolean exitRequested = false;
    private boolean playAgainRequested = false;
    
    public GUIResultScreen(SpriteBatch batch, BitmapFont font, Player winner) {
        super(batch, font);        this.winner = winner;
    }
      @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        batch.begin();
        
        font.setColor(1, 1, 0, 1);
        font.draw(batch, "════════════════════════════════", screenWidth / 2 - 200, screenHeight / 2 + 100);
        font.draw(batch, "          GAME FINISHED!", screenWidth / 2 - 150, screenHeight / 2 + 50);
        font.draw(batch, "════════════════════════════════", screenWidth / 2 - 200, screenHeight / 2);
        
        font.setColor(0, 1, 0, 1);
        font.draw(batch, "🎉 WINNER: " + winner.getName() + " 🎉", screenWidth / 2 - 150, screenHeight / 2 - 50);
        
        font.setColor(1, 1, 1, 1);
        font.draw(batch, "Press SPACE to exit", screenWidth / 2 - 100, screenHeight / 2 - 150);
        
        font.setColor(0, 1, 1, 1);
        font.draw(batch, "Press P to play again", screenWidth / 2 - 120, screenHeight / 2 - 200);
        
        batch.end();
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            exitRequested = true;
        }
        
        if (Gdx.input.isKeyJustPressed(Input.Keys.P)) {
            playAgainRequested = true;        }
    }
      public Screen getNextScreen() {
        if (playAgainRequested) {
            return new GUIMenuScreen(batch, font);
        }
        return exitRequested ? null : this;
    }
    
    @Override
    public void resize(int width, int height) {
        this.screenWidth = width;
        this.screenHeight = height;
    }
}
