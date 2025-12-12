package core.screens;

/**
 * Interface for game screens
 */
public interface Screen {
    /**
     * Show this screen
     */
    void show();
    
    /**
     * Hide this screen
     */
    void hide();
    
    /**
     * Get the next screen to transition to (null if stay on same screen)
     */
    Screen getNextScreen();
}
