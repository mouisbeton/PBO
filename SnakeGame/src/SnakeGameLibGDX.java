import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import gui.screens.GUIMenuScreen;
import com.badlogic.gdx.Screen;

public class SnakeGameLibGDX extends Game {
    private SpriteBatch batch;
    private BitmapFont font;
    private Screen currentScreen;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(com.badlogic.gdx.graphics.Texture.TextureFilter.Linear, com.badlogic.gdx.graphics.Texture.TextureFilter.Linear);
        
        currentScreen = new GUIMenuScreen(batch, font);
    }
    
    @Override
    public void render() {
        currentScreen.render(Gdx.graphics.getDeltaTime());
        
        if (currentScreen instanceof gui.screens.GUIMenuScreen) {
            Screen nextScreen = ((gui.screens.GUIMenuScreen) currentScreen).getNextScreen();
            if (nextScreen != null && nextScreen != currentScreen) {
                currentScreen = nextScreen;
            }
        } else if (currentScreen instanceof gui.screens.GUIGameScreen) {
            Screen nextScreen = ((gui.screens.GUIGameScreen) currentScreen).getNextScreen();
            if (nextScreen != null && nextScreen != currentScreen) {
                currentScreen = nextScreen;
            }
        } else if (currentScreen instanceof gui.screens.GUIResultScreen) {
            Screen nextScreen = ((gui.screens.GUIResultScreen) currentScreen).getNextScreen();
            if (nextScreen == null) {
                Gdx.app.exit();
            }
        }
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
