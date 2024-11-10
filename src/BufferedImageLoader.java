//Sprite Images etc are loaded using this class
//Yes Needed
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class BufferedImageLoader {

    private BufferedImage image;

    public BufferedImage loadImage(String path) throws IOException {
        try {
            image = ImageIO.read(new File(path));
            return image;
        } catch (IOException e) {
            return null;
        }
    }
}
