import java.awt.*;
import java.awt.image.*;

public class OFImage extends BufferedImage {

    public OFImage(BufferedImage image) {
        super(image.getColorModel(), image.copyData(null), image.isAlphaPremultiplied(), null);
    }

    public void setPixel(int x, int y, Color col) {
        setRGB(x, y, col.getRGB());
    }

    public Color getPixel(int x, int y) {
        return new Color(getRGB(x, y));
    }

    public void darker() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                setPixel(x, y, getPixel(x, y).darker());
            }
        }
    }

    public void lighter() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                setPixel(x, y, getPixel(x, y).brighter());
            }
        }
    }

    public void threshold() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                Color pixel = getPixel(x, y);
                int brightness = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3;
                if (brightness <= 85) setPixel(x, y, Color.BLACK);
                else if (brightness <= 170) setPixel(x, y, Color.GRAY);
                else setPixel(x, y, Color.WHITE);
            }
        }
    }
}
