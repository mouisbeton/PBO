package gui.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

public abstract class GUIScreen implements Screen {
    protected SpriteBatch batch;
    protected BitmapFont font;
    protected int screenWidth;
    protected int screenHeight;
    
    public GUIScreen(SpriteBatch batch, BitmapFont font) {
        this.batch = batch;
        this.font = font;
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void hide() {
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
    @Override
    public void dispose() {
    }
}
