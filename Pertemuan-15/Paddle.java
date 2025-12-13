import java.awt.*;
import java.awt.event.*;

public class Paddle {
    int x, y;
    int yVelocity = 0;
    int speed = 5;
    int width = 20;
    int height = 100;

    private final boolean isLeft;
    private boolean botEnabled;

    public Paddle(int x, int y, boolean isLeft) {
        this.x = x;
        this.y = y;
        this.isLeft = isLeft;
        this.botEnabled = false;
    }

    public void setBotEnabled(boolean enabled) {
        this.botEnabled = enabled;
        if (enabled) {
            yVelocity = 0;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
    }

    public void move() {
        y += yVelocity;

        int minY = 0;
        int maxY = GamePanel.GAME_HEIGHT - height;
        if (y < minY) y = minY;
        if (y > maxY) y = maxY;
    }

    public void followBall(Ball ball) {
        if (!botEnabled || ball == null) return;

        int target = ball.y + (ball.diameter / 2) - (height / 2);
        if (y + (height / 2) < target) {
            yVelocity = speed;
        } else if (y + (height / 2) > target) {
            yVelocity = -speed;
        } else {
            yVelocity = 0;
        }
    }

    public void keyPressed(KeyEvent e) {
        if (botEnabled) return;

        int code = e.getKeyCode();
        if (isLeft) {
            if (code == KeyEvent.VK_W) yVelocity = -speed;
            if (code == KeyEvent.VK_S) yVelocity = speed;
        } else {
            if (code == KeyEvent.VK_UP) yVelocity = -speed;
            if (code == KeyEvent.VK_DOWN) yVelocity = speed;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (botEnabled) return;

        int code = e.getKeyCode();
        if (isLeft) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_S) yVelocity = 0;
        } else {
            if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) yVelocity = 0;
        }
    }
}
