import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame() {
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        panel.requestFocusInWindow();
    }
}
