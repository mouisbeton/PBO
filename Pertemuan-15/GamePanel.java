import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {
    static final int GAME_WIDTH = 800;
    static final int GAME_HEIGHT = 600;

    private static final int FPS = 60;
    private static final int SCORE_LIMIT = 7;
    private static final Color BG = Color.BLACK;

    private enum GameState {
        MENU,
        PLAYING,
        WIN
    }

    private GameState state = GameState.MENU;
    private boolean botEnabled = false;

    Thread gameThread;
    Image image;
    Graphics graphics;

    Paddle player1, player2;
    Ball ball;

    private int score1 = 0;
    private int score2 = 0;
    private String winnerText = "";

    public GamePanel() {
        player1 = new Paddle(0, (GAME_HEIGHT / 2) - 50, true);
        player2 = new Paddle(GAME_WIDTH - 20, (GAME_HEIGHT / 2) - 50, false);
        ball = new Ball((GAME_WIDTH / 2) - 15, (GAME_HEIGHT / 2) - 15);

        this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
        this.setFocusable(true);
        this.setBackground(BG);
        this.addKeyListener(new AL());

        gameThread = new Thread(this);
        gameThread.start();
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    public void draw(Graphics g) {
        g.setColor(BG);
        g.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);

        if (state == GameState.MENU) {
            drawMenu(g);
            return;
        }

        drawCourt(g);
        drawScore(g);

        player1.draw(g);
        player2.draw(g);
        ball.draw(g);

        if (state == GameState.WIN) {
            drawWin(g);
        }
    }

    private void drawMenu(Graphics g) {
        g.setColor(Color.WHITE);

        Font title = new Font("SansSerif", Font.BOLD, 44);
        Font text = new Font("SansSerif", Font.BOLD, 16);

        drawCenteredString(g, "PONG", title, GAME_WIDTH, 120);

        g.setFont(text);
        drawCenteredString(g, "Press Space to Play", text, GAME_WIDTH, 260);
        drawCenteredString(g, "Press Shift to Play with Bot", text, GAME_WIDTH, 290);
        drawCenteredString(g, "<< Score Limit: " + SCORE_LIMIT + " >>", text, GAME_WIDTH, 330);
    }

    private void drawWin(Graphics g) {
        g.setColor(Color.WHITE);

        Font title = new Font("SansSerif", Font.BOLD, 44);
        Font text = new Font("SansSerif", Font.BOLD, 16);

        drawCenteredString(g, "PONG", title, GAME_WIDTH, 120);
        drawCenteredString(g, winnerText, new Font("SansSerif", Font.BOLD, 28), GAME_WIDTH, 250);

        g.setFont(text);
        drawCenteredString(g, "Press Space to Play Again", text, GAME_WIDTH, 320);
        drawCenteredString(g, "Press ESC for Menu", text, GAME_WIDTH, 350);
    }

    private void drawCourt(Graphics g) {
        g.setColor(Color.WHITE);

        Graphics2D g2 = (Graphics2D) g;
        Stroke old = g2.getStroke();
        g2.setStroke(new BasicStroke(3));

        g2.drawLine(GAME_WIDTH / 2, 0, GAME_WIDTH / 2, GAME_HEIGHT);

        int circleDiameter = 180;
        g2.drawOval((GAME_WIDTH - circleDiameter) / 2, (GAME_HEIGHT - circleDiameter) / 2, circleDiameter, circleDiameter);

        g2.setStroke(old);
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        Font scoreFont = new Font("SansSerif", Font.BOLD, 30);
        g.setFont(scoreFont);

        String s1 = String.valueOf(score1);
        String s2 = String.valueOf(score2);

        int y = 45;
        g.drawString(s1, (GAME_WIDTH / 2) - 80, y);
        g.drawString(s2, (GAME_WIDTH / 2) + 60, y);
    }

    private void drawCenteredString(Graphics g, String text, Font font, int width, int y) {
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics(font);
        int x = (width - fm.stringWidth(text)) / 2;
        g.drawString(text, x, y);
    }

    public void move() {
        if (state != GameState.PLAYING) return;

        if (botEnabled) {
            player2.followBall(ball);
        }

        player1.move();
        player2.move();
        ball.move();

        handleCollisions();
        handleScoring();
    }

    private void handleCollisions() {
        Rectangle b = ball.getBounds();
        Rectangle p1 = new Rectangle(player1.x, player1.y, player1.width, player1.height);
        Rectangle p2 = new Rectangle(player2.x, player2.y, player2.width, player2.height);

        if (b.intersects(p1) && ball.xVelocity < 0) {
            ball.bounceX();
            ball.x = player1.x + player1.width;
        } else if (b.intersects(p2) && ball.xVelocity > 0) {
            ball.bounceX();
            ball.x = player2.x - ball.diameter;
        }
    }

    private void handleScoring() {
        if (ball.x + ball.diameter < 0) {
            score2++;
            afterPoint();
        } else if (ball.x > GAME_WIDTH) {
            score1++;
            afterPoint();
        }

        if (score1 >= SCORE_LIMIT || score2 >= SCORE_LIMIT) {
            state = GameState.WIN;
            winnerText = (score1 >= SCORE_LIMIT) ? "Player 1 Wins!" : "Player 2 Wins!";
        }
    }

    private void afterPoint() {
        ball.reset();
        player1.y = (GAME_HEIGHT / 2) - (player1.height / 2);
        player2.y = (GAME_HEIGHT / 2) - (player2.height / 2);
        player1.yVelocity = 0;
        player2.yVelocity = 0;
    }

    private void startNewGame(boolean withBot) {
        botEnabled = withBot;
        player2.setBotEnabled(withBot);

        score1 = 0;
        score2 = 0;
        winnerText = "";

        ball.reset();
        afterPoint();
        state = GameState.PLAYING;

        requestFocusInWindow();
    }

    public void run() {
        double nsPerFrame = 1000000000 / (double) FPS;
        long lastTime = System.nanoTime();
        double delta = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerFrame;
            lastTime = now;

            while (delta >= 1) {
                move();
                repaint();
                delta--;
            }
        }
    }

    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            if (state == GameState.MENU) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    startNewGame(false);
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    startNewGame(true);
                    return;
                }
            }

            if (state == GameState.WIN) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    startNewGame(botEnabled);
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    state = GameState.MENU;
                    return;
                }
            }

            if (state == GameState.PLAYING) {
                player1.keyPressed(e);
                player2.keyPressed(e);
            }
        }

        public void keyReleased(KeyEvent e) {
            if (state == GameState.PLAYING) {
                player1.keyReleased(e);
                player2.keyReleased(e);
            }
        }
    }
}
