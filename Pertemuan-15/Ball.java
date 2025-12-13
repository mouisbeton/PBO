import java.awt.*;
import java.util.Random;

public class Ball {
    int x, y;
    int xVelocity = 3;
    int yVelocity = 3;
    int diameter = 30;

    private final int startX;
    private final int startY;
    private final Random rng = new Random();

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.startX = x;
        this.startY = y;
        randomizeDirection();
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;

        if (y <= 0 || y >= GamePanel.GAME_HEIGHT - diameter) {
            yVelocity = -yVelocity;
        }
    }

    public void bounceX() {
        xVelocity = -xVelocity;
    }

    public void bounceY() {
        yVelocity = -yVelocity;
    }

    public void reset() {
        x = startX;
        y = startY;
        randomizeDirection();
    }

    private void randomizeDirection() {
        int dirX = rng.nextBoolean() ? 1 : -1;
        int dirY = rng.nextBoolean() ? 1 : -1;
        xVelocity = 4 * dirX;
        yVelocity = 4 * dirY;
    }
}
