//Sprite sheet class to locate the specific frame
//Yes Needed
import java.awt.image.BufferedImage;

public class SpriteSheet {
    private BufferedImage image;

    public SpriteSheet(BufferedImage image){ this.image = image; }

    public BufferedImage grabImage(int col, int row, int width, int height){

        BufferedImage img = image.getSubimage(col, row, width, height);         //column, row, width and height is used to pinpoint the frame in the spritesheet
        return img;
    }
}
