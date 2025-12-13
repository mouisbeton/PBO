import java.awt.*;
import javax.swing.*;

public class ImagePanel extends JComponent {

    private int width, height;
    private OFImage panelImage;

    public ImagePanel() {
        width = 360;
        height = 240;
        panelImage = null;
    }

    public void setImage(OFImage image) {
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
            panelImage = image;
            repaint();
        }
    }

    public void clearImage() {
        panelImage = null;
        repaint();
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (panelImage != null) {
            g.drawImage(panelImage, 0, 0, null);
        }
    }
}
